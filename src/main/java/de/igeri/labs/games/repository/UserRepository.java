package de.igeri.labs.games.repository;

import de.igeri.labs.games.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.activationKey = ?1")
    User getUserByActivationKey(String activationKey);

    User findByLogin(String login);

//    @Query("select u from User u where u.activated = false and u.createdDate > ?1")
//    List<User> findNotActivatedUsersByCreationDateBefore(DateTime dateTime);

}
