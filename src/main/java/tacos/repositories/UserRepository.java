package tacos.repositories;

import org.springframework.data.repository.CrudRepository;
import tacos.entities.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
