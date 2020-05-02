package id.ac.binus.programming.kost.user.repository;

import id.ac.binus.programming.kost.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
    @Query(value = "SELECT m FROM UserRole m ")
    List<UserRole> findAll();

    @Query(value = "SELECT m FROM UserRole m where m.roleid=:id ")
    UserRole find(@Param("id") String id);

    @Query(value = "delete from UserRole m WHERE m.roleid=:id ")
    UserRole delete(@Param("id") String id);
}
