package ba.menit.nbp.repository;

import ba.menit.nbp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}