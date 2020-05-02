package id.ac.binus.programming.kost.user.repository;

import id.ac.binus.programming.kost.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query(value = "SELECT m FROM User m ")
    List<User> findAll();

    @Query(value = "SELECT m FROM User m where m.userid=:id ")
    User find(@Param("id") String id);

    @Query(value = "delete from User m WHERE m.userid=:id ")
    User delete(@Param("id") String id);

    User findByUsernameAndPassword(String username,String password);

    User findByEmailAndUsername(String email,String username);
}
