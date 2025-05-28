package ba.menit.nbp.controllers;

import ba.menit.nbp.dtos.LoginUserDto;
import ba.menit.nbp.dtos.RegisterUserDto;
import ba.menit.nbp.dtos.UserDto;
import ba.menit.nbp.entities.User;
import ba.menit.nbp.response.LoginResponse;
import ba.menit.nbp.services.AuthenticationService;
import ba.menit.nbp.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signupAdmin(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        UserDto userDto = new UserDto(
                authenticatedUser.getId(),
                authenticatedUser.getFirstName(),
                authenticatedUser.getLastName(),
                authenticatedUser.getEmail(),
                authenticatedUser.getRole().getName().name()
        );
        loginResponse.setUser(userDto);

        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/request-reset-password")
    public ResponseEntity<String> requestPasswordReset(@RequestParam String email) {
        String baseUrl = "http://localhost:4200"; // Or read from config
        authenticationService.initiatePasswordReset(email, baseUrl);
        return ResponseEntity.ok("If your email exists, a password reset link has been sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean result = authenticationService.resetPassword(token, newPassword);
        return result
                ? ResponseEntity.ok("Password successfully reset.")
                : ResponseEntity.badRequest().body("Invalid or expired token.");
    }


}