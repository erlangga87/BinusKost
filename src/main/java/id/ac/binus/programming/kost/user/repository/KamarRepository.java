package id.ac.binus.programming.kost.user.repository;

import id.ac.binus.programming.kost.user.entity.Kamar;
import id.ac.binus.programming.kost.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KamarRepository extends JpaRepository<Kamar, String> {
    @Query(value = "SELECT m FROM Kamar m ")
    List<Kamar> findAll();

    @Query(value = "SELECT m FROM Kamar m where m.kamarid=:id ")
    Kamar find(@Param("id") String id);

    @Query(value = "delete from Kamar m WHERE m.kamarid=:id ")
    Kamar delete(@Param("id") String id);
}
