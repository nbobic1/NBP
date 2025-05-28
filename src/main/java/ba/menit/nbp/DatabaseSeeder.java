//package ba.menit.nbp;
//
//import ba.menit.nbp.entities.*;
//import ba.menit.nbp.repositories.*;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import jakarta.persistence.EntityManager;
//
//@Configuration
//public class DatabaseSeeder {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Bean
//    @Transactional
//    public CommandLineRunner initData(
//            UserRepository userRepo,
//            RoleRepository roleRepo,
//            NbpAppsRepository appsRepo,
//            HospitalRepository hospitalRepo,
//            DoctorRepository doctorRepo,
//            PatientRepository patientRepo,
//            InsuranceRepository insuranceRepo,
//            AppointmentRepository appointmentRepo,
//            MedicalRecordRepository medicalRecordRepo,
//            ServiceRepository serviceRepo,
//            PaymentRepository paymentRepo,
//            MedicationRepository medicationRepo,
//            PrescriptionRepository prescriptionRepo
//    ) {
//        return args -> {
//            // Create roles if they don't exist
//            for (RoleEnum roleEnum : RoleEnum.values()) {
//                roleRepo.findByName(roleEnum).orElseGet(() -> roleRepo.save(new Role(roleEnum)));
//            }
//
//            // Fetch managed roles
//            Role adminRole = roleRepo.findByName(RoleEnum.ADMIN).orElseThrow();
//            Role doctorRole = roleRepo.findByName(RoleEnum.DOCTOR).orElseThrow();
//            Role patientRole = roleRepo.findByName(RoleEnum.PATIENT).orElseThrow();
//
//            entityManager.detach(adminRole); // If needed
//            entityManager.detach(doctorRole); // If needed
//            entityManager.detach(patientRole); // If needed
//
//
//            // Users
//            User admin = new User("admin@med.org", "Alice", "Admin", "pass123", adminRole);
//            User doc1 = new User("doc1@med.org", "Gregory", "House", "pass123", doctorRole);
//            User doc2 = new User("doc2@med.org", "Meredith", "Grey", "pass123", doctorRole);
//            User pat1 = new User("pat1@med.org", "Charlie", "Brown", "pass123", patientRole);
//            User pat2 = new User("pat2@med.org", "Lucy", "Heart", "pass123", patientRole);
//            User pat3 = new User("pat3@med.org", "Michael", "Stone", "pass123", patientRole);
//
//            userRepo.saveAll(List.of(admin, doc1, doc2, pat1, pat2, pat3));
//
//            // Hospitals
//            Hospital h1 = new Hospital("Central Hospital", "Main St 1");
//            Hospital h2 = new Hospital("Green Valley Clinic", "Oak Avenue 12");
//            hospitalRepo.saveAll(List.of(h1, h2));
//
//            // Insurances
//            Insurance ins1 = new Insurance("Premium Plan", "Covers surgery and hospitalization");
//            Insurance ins2 = new Insurance("Basic Plan", "Covers general outpatient visits");
//            insuranceRepo.saveAll(List.of(ins1, ins2));
//
//            // Doctors
//            Doctor d1 = new Doctor(doc1, h1.getId(), "Chief Surgeon");
//            Doctor d2 = new Doctor(doc2, h2.getId(), "Resident");
//            doctorRepo.saveAll(List.of(d1, d2));
//
//            // Patients
//            Patient p1 = new Patient(pat1, "MRN10001", ins1.getId());
//            Patient p2 = new Patient(pat2, "MRN10002", ins2.getId());
//            Patient p3 = new Patient(pat3, "MRN10003", ins1.getId());
//            patientRepo.saveAll(List.of(p1, p2, p3));
//
//            // Appointments
//            Appointment appt1 = new Appointment(LocalDateTime.now().plusDays(1), d1);
//            Appointment appt2 = new Appointment(LocalDateTime.now().plusDays(2), d2);
//            Appointment appt3 = new Appointment(LocalDateTime.now().plusDays(3), d2);
//            appointmentRepo.saveAll(List.of(appt1, appt2, appt3));
//
//            // Medical Records
//            MedicalRecord rec1 = new MedicalRecord(p1, d1.getId(), appt1.getId(), "Flu", "Rest & hydration", LocalDateTime.now());
//            MedicalRecord rec2 = new MedicalRecord(p2, d2.getId(), appt2.getId(), "Allergy", "Antihistamines", LocalDateTime.now());
//            MedicalRecord rec3 = new MedicalRecord(p3, d2.getId(), appt3.getId(), "Sprained Ankle", "Cold compress", LocalDateTime.now());
//            medicalRecordRepo.saveAll(List.of(rec1, rec2, rec3));
//
//            // Medications
//            Medication m1 = new Medication("Ibuprofen", "Painkiller", "400mg");
//            Medication m2 = new Medication("Loratadine", "Antihistamine", "10mg");
//            medicationRepo.saveAll(List.of(m1, m2));
//
//            // Prescriptions
//            Prescription pr1 = new Prescription(rec1, m1, 10, "Take after meals");
//            Prescription pr2 = new Prescription(rec2, m2, 5, "Once daily");
//            prescriptionRepo.saveAll(List.of(pr1, pr2));
//
//            // Services
//            Service s1 = new Service("Blood Test", 25.0, 15);
//            Service s2 = new Service("MRI Scan", 300.0, 60);
//            Service s3 = new Service("Vaccination", 20.0, 10);
//            serviceRepo.saveAll(List.of(s1, s2, s3));
//
//            // Payments
//            Payment pay1 = new Payment(100.0, pat1);
//            Payment pay2 = new Payment(200.0, pat2);
//            paymentRepo.saveAll(List.of(pay1, pay2));
//
//            // App
//            NbpApp app = new NbpApp("APP001", admin, LocalDate.now().plusYears(1));
//            appsRepo.save(app);
//        };
//    }
//}