package gr.aueb.cf.eclassapp.repository;

import gr.aueb.cf.eclassapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameEquals(String username);

    @Query("select count(*) > 0 from User U WHERE U.username = ?1 AND U.password = ?2")
    boolean isUserValid(String username, String password);
}
