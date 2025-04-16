package ba.menit.nbp.services;


import ba.menit.nbp.dtos.LoginUserDto;
import ba.menit.nbp.dtos.RegisterUserDto;
import ba.menit.nbp.entities.Role;
import ba.menit.nbp.entities.RoleEnum;
import ba.menit.nbp.entities.User;
import ba.menit.nbp.repositories.RoleRepository;
import ba.menit.nbp.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder, RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public User signupAdmin(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }
        User user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.PATIENT);

        if (optionalRole.isEmpty()) {
            return null;
        }
        User user = new User();
        user.setEmail(input.getEmail());
        user.setFullName(input.getFullName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}

