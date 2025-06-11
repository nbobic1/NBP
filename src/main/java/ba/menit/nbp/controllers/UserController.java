package ba.menit.nbp.controllers;

import ba.menit.nbp.dtos.UserCreateDto;
import ba.menit.nbp.dtos.UserDto;
import ba.menit.nbp.entities.*;
import ba.menit.nbp.repositories.RoleRepository;
import ba.menit.nbp.services.DoctorService;
import ba.menit.nbp.services.PatientService;
import ba.menit.nbp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreateDto dto) {
        Role role = roleRepository.findById(dto.roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User(dto.email, dto.firstName, dto.lastName, dto.password, role);
        User createdUser = userService.create(user);
        if(role.getName() == RoleEnum.PATIENT) {
            Patient patient = new Patient(
                createdUser
            );
            patientService.create(patient);
        }
        else if(role.getName() == RoleEnum.DOCTOR) {
            Doctor doctor = new Doctor(
                    createdUser,
                    1L, "Porodicni"
            );
            doctorService.create(doctor);
        }
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRoleString()
        );
        return ResponseEntity.ok(userDto);
    }


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAll().stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
//        return ResponseEntity.ok(userService.update(id, user));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/profile-picture")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            User user = userService.getById(id);
            user.setProfilePicture(file.getBytes());
            userService.create(user);
            return ResponseEntity.ok("Profile picture uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading profile picture.");
        }
    }
    @GetMapping("/{id}/profile-picture")
    public ResponseEntity<byte[]> getProfilePicture(@PathVariable Long id) {
        User user = userService.getById(id);
        byte[] imageData = user.getProfilePicture();
        if (imageData == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().header("Content-Type", "image/jpeg").body(imageData);
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> createUsersBulk(@RequestBody List<UserCreateDto> dtos) {
        for (UserCreateDto dto : dtos) {
            Role role = roleRepository.findById(dto.roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found"));

            User user = new User(dto.email, dto.firstName, dto.lastName, dto.password, role);
            User createdUser = userService.create(user);

            if (role.getName() == RoleEnum.PATIENT) {
                patientService.create(new Patient(createdUser));
            } else if (role.getName() == RoleEnum.DOCTOR) {
                doctorService.create(new Doctor(createdUser, 1L, "Porodicni"));
            }
        }
        return ResponseEntity.ok("Users imported successfully");
    }



}
