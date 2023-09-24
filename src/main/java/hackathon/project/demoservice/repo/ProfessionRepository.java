package hackathon.project.demoservice.repo;

import hackathon.project.demoservice.enumeration.Role;
import hackathon.project.demoservice.model.Professions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Professions, String> {
    Optional<Professions> findProfessionsById(Long id);
    Optional<Professions> findProfessionsByUsername(String username);
    Optional<Professions> findProfessionsByEmail(String email);
    Optional<Professions> findProfessionsByIdOrUsernameOrEmailOrCategoryId(Long id, String username, String email, Long categoryId);
    Optional<Professions> findProfessionsByUsernameOrEmail(String username, String email);
    List<Professions> getProfessionsByUsernameLikeAndRole(String username, Role role);
}
