package ba.menit.nbp.services;


import ba.menit.nbp.entities.User;
import ba.menit.nbp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

//    public User update(Long id, User updatedUser) {
//        User existingUser = getById(id);
//        existingUser.setFirstName(updatedUser.getFirstName());
//        existingUser.setLastName(updatedUser.getLastName());
//        existingUser.setEmail(updatedUser.getEmail());
//        existingUser.setPassword(updatedUser.getPassword());
//        existingUser.setUsername(updatedUser.getUsername());
//        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
//        existingUser.setBirthDate(updatedUser.getBirthDate());
//        existingUser.setAddressId(updatedUser.getAddressId());
////        existingUser.setRole(updatedUser.getRole());
//        return userRepository.save(existingUser);
//    }

    @Transactional
    public void delete(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User with id " + id + " not found");
        }

        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Cannot delete user because related records exist.", e);
        }
    }

}