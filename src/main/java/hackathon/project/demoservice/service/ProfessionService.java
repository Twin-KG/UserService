package hackathon.project.demoservice.service;

import hackathon.project.demoservice.model.Professions;

import java.util.Optional;

public interface ProfessionService {
    Optional<Professions> findProfessionsById(Long id);
    Optional<Professions> findProfessionsByUsername(String username);
    Optional<Professions> findProfessionsByIdOrUsernameOrEmail(Long id, String username, String email);
    Professions saveUser(Professions professions);
    Professions validateNewUserAndEmail(String currentUsername, String newUsername, String newEmail);
    Professions resetPasswordByEmail(String email, String oldPassword, String newPassword);
}
