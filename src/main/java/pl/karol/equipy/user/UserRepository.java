package pl.karol.equipy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPesel(String pesel);
    List<User> findAllByLastNameContainingIgnoreCase(String lastName);
}
