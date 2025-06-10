package ba.menit.nbp.services;


import ba.menit.nbp.dtos.AppointmentDto;
import ba.menit.nbp.dtos.CreateAppointmentDto;
import ba.menit.nbp.entities.Appointment;
import ba.menit.nbp.entities.Doctor;
import ba.menit.nbp.entities.Patient;
import ba.menit.nbp.repositories.AppointmentRepository;
import ba.menit.nbp.repositories.DoctorRepository;
import ba.menit.nbp.repositories.PatientRepository;
import ba.menit.nbp.repositories.ServiceRepository;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ServiceRepository serviceRepository;

    public Appointment create(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment getById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }

    public Appointment update(Long id, Appointment updatedAppointment) {
        Appointment existingAppointment = getById(id);
        existingAppointment.setStartTime(updatedAppointment.getStartTime());
        existingAppointment.setDoctor(updatedAppointment.getDoctor());
        return appointmentRepository.save(existingAppointment);
    }

    public void delete(Long id) {
        appointmentRepository.deleteById(id);
    }
    public List<Appointment> getByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
    public List<Appointment> getByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    public List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AppointmentDto createAppointment(CreateAppointmentDto createDto) throws Exception {
        Doctor doctor = doctorRepository.findById(createDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + createDto.getDoctorId()));

        Patient patient = patientRepository.findById(createDto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with ID: " + createDto.getPatientId()));

        ba.menit.nbp.entities.Service service = serviceRepository.findById(createDto.getServiceId())
                .orElseThrow(() -> new RuntimeException("Service not found with ID: " + createDto.getServiceId()));

        LocalDateTime newAppointmentStartTime = createDto.getStartTime();
        LocalDateTime newAppointmentEndTime = newAppointmentStartTime.plusMinutes(service.getDurationInMin());

        LocalDateTime startOfDay = newAppointmentStartTime.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = newAppointmentStartTime.toLocalDate().atTime(23, 59, 59);

        List<Appointment> existingAppointments = appointmentRepository.findByDoctorIdAndDay(
                createDto.getDoctorId(), startOfDay, endOfDay);

        for (Appointment existingAppt : existingAppointments) {
            LocalDateTime existingApptStartTime = existingAppt.getStartTime();
            LocalDateTime existingApptEndTime = existingApptStartTime.plusMinutes(existingAppt.getService().getDurationInMin());

            boolean overlaps = newAppointmentStartTime.isBefore(existingApptEndTime) &&
                    newAppointmentEndTime.isAfter(existingApptStartTime);

            if (overlaps) {
//                throw new AppointmentOverlapException("Zauzeti termin: Doktor " + doctor.getUser().getFirstName() + " " + doctor.getUser().getLastName() +
//                        " je već zauzet od " + existingApptStartTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) +
//                        " do " + existingApptEndTime.format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")) + ".");
                throw new Exception("Zauzet termin");
            }
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setStartTime(newAppointmentStartTime);
        newAppointment.setDoctor(doctor);
        newAppointment.setPatient(patient);
        newAppointment.setService(service);

        Appointment savedAppointment = appointmentRepository.save(newAppointment);


        String appointmentDetails = String.format(
                "Novi termin zakazan:\n" +
                        "Datum: %s\n" +
                        "Vrijeme: %s - %s\n" +
                        "Usluga: %s (trajanje: %d min, cijena: %.2f KM)\n",
                newAppointmentStartTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                newAppointmentStartTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                newAppointmentEndTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                service.getName(),
                service.getDurationInMin(),
                service.getPrice()
        );

        if (patient.getUser() != null && patient.getUser().getEmail() != null) {
            String patientSubject = "Potvrda termina - " + service.getName();
            String patientBody = String.format(
                    "Poštovani/a %s %s,\n\n" +
                            "Vaš termin je uspješno zakazan sa doktorom %s %s.\n\n" +
                            "%s\n" +
                            "Radujemo se Vašem dolasku!\n\n" +
                            "Srdačan pozdrav,\n" +
                            "Vaša Ordinacija",
                    patient.getUser().getFirstName(), patient.getUser().getLastName(),
                    doctor.getUser().getFirstName(), doctor.getUser().getLastName(),
                    appointmentDetails
            );
            emailService.sendEmail("efejzagic2@etf.unsa.ba", patientSubject, patientBody);
        } else {
            System.err.println("Pacijent nema email adresu za slanje obavijesti: " + patient.getId());
        }

        if (doctor.getUser() != null && doctor.getUser().getEmail() != null) {
            String doctorSubject = "Novi termin zakazan - " + service.getName();
            String doctorBody = String.format(
                    "Poštovani/a doktore/doktorice %s %s,\n\n" +
                            "Nov termin je zakazan kod Vas.\n\n" +
                            "Pacijent: %s %s (%s)\n\n" +
                            "%s\n" +
                            "Hvala na Vašem radu!\n\n" +
                            "Srdačan pozdrav,\n" +
                            "Vaša Ordinacija",
                    doctor.getUser().getFirstName(), doctor.getUser().getLastName(),
                    patient.getUser().getFirstName(), patient.getUser().getLastName(), patient.getUser().getEmail(),
                    appointmentDetails
            );
            emailService.sendEmail("efejzagic2@etf.unsa.ba", doctorSubject, doctorBody);
        } else {
            System.err.println("Doktor nema email adresu za slanje obavijesti: " + doctor.getId());
        }

        return convertToDto(savedAppointment);
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getId(),
                appointment.getStartTime(),
                appointment.getPatient() != null ? appointment.getPatient().getUser().getFirstName() : "N/A",
                appointment.getPatient() != null ? appointment.getPatient().getUser().getLastName() : "N/A",
                appointment.getPatient() != null ? appointment.getPatient().getUser().getEmail() : "N/A",
                appointment.getService() != null ? appointment.getService().getName() : "N/A",
                appointment.getService() != null ? appointment.getService().getPrice() : 0.0
        );
    }
}
