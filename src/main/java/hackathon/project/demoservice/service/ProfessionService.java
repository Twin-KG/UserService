package hackathon.project.demoservice.service;

import hackathon.project.demoservice.dto.ProfessionDto;
import hackathon.project.demoservice.model.Professions;

import java.util.List;
import java.util.Optional;

public interface ProfessionService {
    Optional<Professions> findProfessionsById(Long id);
    Optional<Professions> findProfessionsByUsername(String username);
    Optional<Professions> findProfessionsByMail(String mail);
    List<Professions> getProfessionsByUsernameLike(String username);
    Optional<ProfessionDto> findProfessionsByIdOrUsernameOrEmailOrCategoryId(Long id, String username, String email, Long categoryId);
    ProfessionDto saveUser(Professions professions);
    Professions validateNewUserAndEmail(String currentUsername, String newUsername, String newEmail);
    Professions resetPasswordByEmail(String email, String oldPassword, String newPassword);
}
