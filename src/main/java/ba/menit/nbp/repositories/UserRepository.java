package ba.menit.nbp.repositories;


import ba.menit.nbp.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "Select  e from User e ",nativeQuery = true)
    List<User> findByLastName(String lastName);
    User findById(long id);
    Optional<User> findByEmail(String email);
}