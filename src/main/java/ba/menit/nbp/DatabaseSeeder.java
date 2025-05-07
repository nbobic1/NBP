//package ba.menit.nbp;
//
//import ba.menit.nbp.entities.*;
//import ba.menit.nbp.repositories.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Configuration
//public class DatabaseSeeder {
//
//    @Bean
//    public CommandLineRunner initData(
//            UserRepository userRepo,
//            RoleRepository roleRepo,
////            NpbLogRepository logRepo,
////            NpbAppsRepository appsRepo,
////            HospitalRepository hospitalRepo,
//            DoctorRepository doctorRepo,
//            PatientRepository patientRepo,
//            InsuranceRepository insuranceRepo,
//            AppointmentRepository appointmentRepo,
//            MedicalRecordRepository medicalRecordRepo
////            ServiceRepository serviceRepo,
////            PaymentRepository paymentRepo,
////            MedicationRepository medicationRepo,
////            PrescriptionRepository prescriptionRepo
//    ) {
//        return args -> {
//
//            // Roles
////            Role adminRole = new Role();
////            adminRole.setName(RoleEnum.ADMIN);
////
//////            Role doctorRole = new Role();
//////            adminRole.setName(RoleEnum.PATIENT);
////
////
////            Role patientRole = new Role();
////            adminRole.setName(RoleEnum.PATIENT);
//
////            roleRepo.saveAll(List.of(adminRole, doctorRole, patientRole));
//
//            // Users
//            User user1 = new User();
//            user1.setFirstName("John");
//            user1.setLastName("Doe");
//            user1.setEmail("john@example.com");
//            user1.setPassword("secret");
////            user1.set("johnd");
////            user1.setPhoneNumber("123456789");
////            user1.set(LocalDate.of(1990, 1, 1));
////            user1.setRole(adminRole);
//
//            User user2 = new User();
//            user2.setFirstName("Jane");
//            user2.setLastName("Smith");
//            user2.setEmail("jane@example.com");
//            user2.setPassword("secret");
////            user2.setUsername("janes");
////            user2.setPhoneNumber("987654321");
////            user2.setBirthDate(LocalDate.of(1985, 6, 15));
////            user2.setRole(patientRole);
//
//            userRepo.saveAll(List.of(user1, user2));
//
//            // Insurance
//            Insurance insurance = new Insurance();
//            insurance.setInsuranceName("Basic Health Plan");
//            insurance.setCoverageDetails("Full outpatient and inpatient coverage.");
//            insuranceRepo.save(insurance);
//
//            // Hospital
////            Hospital hospital = new Hospital();
////            hospital.setName("Central Hospital");
////            hospital.setStreetName("Main Avenue 123");
////            hospitalRepo.save(hospital);hospital
//
//            // Doctor
//            Doctor doctor = new Doctor();
//            doctor.setUser(user1);
////            doctor.setHospital(hospital);
//            doctor.setSeniority("Senior");
//            doctorRepo.save(doctor);
//
//            // Patient
//            Patient patient = new Patient();
//            patient.setUser(user2);
//            patient.setMedicalRecordNumber("MRN12345");
////            patient.setInsurance(insurance);
//            patientRepo.save(patient);
//
//            // Appointment
//            Appointment appointment = new Appointment();
//            appointment.setDoctor(doctor);
//            appointment.setStartTime(LocalDateTime.now().plusDays(1));
//            appointmentRepo.save(appointment);
//
//            // Medical Record
//            MedicalRecord record = new MedicalRecord();
//            record.setPatient(patient);
////            record.setDoctor(doctor);
////            record.setAppointment(appointment);
//            record.setDiagnosis("Common Cold");
//            record.setTreatment("Rest and fluids");
//            record.setRecordDate(LocalDateTime.now());
//            medicalRecordRepo.save(record);
//
//            // Medication
////            Medication medication = new Medication();
////            medication.setName("Paracetamol");
////            medication.setDescription("Used for pain relief and fever reduction");
////            medication.setDosage("500mg");
////            medicationRepo.save(medication);
//
//            // Prescription
////            Prescription prescription = new Prescription();
////            prescription.setMedicalRecord(record);
////            prescription.setMedication(medication);
////            prescription.setQuantity(10);
////            prescription.setInstructions("Take two tablets daily after meals");
////            prescriptionRepo.save(prescription);
//
//            // Services
////            Service service = new Service();
////            service.setName("General Consultation");
////            service.setPrice(50.0);
////            service.setDurationInMin(30);
////            serviceRepo.save(service);
//
//            // Payment
////            Payment payment = new Payment();
////            payment.setAmount(75.0);
////            payment.setUser(user2);
////            paymentRepo.save(payment);
////
////            // Apps
////            NpbApp app = new NpbApp();
////            app.setAppId("APP001");
////            app.setManager(user1);
////            app.setExpiryDate(LocalDate.now().plusYears(1));
////            appsRepo.save(app);
////
////            // Logs
////            NpbLog log = new NpbLog();
////            log.setActionName("INSERT");
////            log.setTableName("NPB_USER");
////            log.setDateTime(LocalDateTime.now());
////            log.setDbUser(user1.getEmail());
////            logRepo.save(log);
//        };
//    }
//}