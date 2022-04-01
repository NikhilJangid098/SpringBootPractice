package synodsa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import synodsa.entity.UserDetailsEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Long> {

    Optional<UserDetailsEntity> findById(Long id);
    List<UserDetailsEntity> findAll();
    List<UserDetailsEntity> findByFirstName(String firstName);
}
