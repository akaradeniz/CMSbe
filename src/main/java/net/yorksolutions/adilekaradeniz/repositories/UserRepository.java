package net.yorksolutions.adilekaradeniz.repositories;

import net.yorksolutions.adilekaradeniz.entities.UserCMS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserCMS, Long> {
 Optional<UserCMS> findByUsernameAndPassword(String username, String password);
}
