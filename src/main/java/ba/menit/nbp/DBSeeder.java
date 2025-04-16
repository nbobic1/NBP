package ba.menit.nbp;


import ba.menit.nbp.entities.Role;
import ba.menit.nbp.entities.RoleEnum;
import ba.menit.nbp.entities.User;
import ba.menit.nbp.repositories.DoctorRepository;
import ba.menit.nbp.repositories.RoleRepository;
import ba.menit.nbp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;


@Component
public class DBSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public DBSeeder(UserRepository userRepository, DoctorRepository doctorRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository=doctorRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        loadRoles();
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);
        Optional<Role> optionalRole1 = roleRepository.findByName(RoleEnum.PATIENT);
        if (userRepository.count() == 0) {
            User user = new User("admin@gmail.com", passwordEncoder.encode("pass12"), optionalRole.get());
            user.setFullName("admin admin");
            userRepository.save(user);
            User user2 = new User("pacijent@gmail.com", passwordEncoder.encode("pass12"), optionalRole1.get());
            user2.setFullName("pacijent pacijent");
            userRepository.save(user2);
//            User User1 = new User("sdf","dsf");
//                   userRepository.save(User1);
//
//            User user2 = new User("dsfjkl","dsff");
//            userRepository.save(user2);
//
//            doctorRepository.save(new Doctor(new User("dsfjkldasf","dsffmmm"),2,"soe"));

        }
    }
    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.PATIENT, RoleEnum.ADMIN, RoleEnum.DOCTOR };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
                RoleEnum.PATIENT, "Pacijent role",
                RoleEnum.ADMIN, "Administrator role",
                RoleEnum.DOCTOR, "Doktor role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName);
                roleToCreate.setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}

