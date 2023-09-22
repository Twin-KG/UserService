package hackathon.project.demoservice.repo;

import hackathon.project.demoservice.model.Professions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Professions, String> {
    Optional<Professions> findProfessionsById(Long id);
    Optional<Professions> findProfessionsByUsername(String username);
    Optional<Professions> findProfessionsByEmail(String email);
    Optional<Professions> findProfessionsByIdOrUsernameOrEmail(Long id, String username, String email);
    Optional<Professions> findProfessionsByUsernameOrEmail(String username, String email);
}
