package ba.menit.nbp.services;


import ba.menit.nbp.entities.User;
import ba.menit.nbp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
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

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}