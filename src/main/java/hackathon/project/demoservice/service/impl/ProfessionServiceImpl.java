package hackathon.project.demoservice.service.impl;

import hackathon.project.demoservice.client.ContentServiceClient;
import hackathon.project.demoservice.exception.domain.EmailAlreadyExistException;
import hackathon.project.demoservice.exception.domain.IncorrectPasswordException;
import hackathon.project.demoservice.exception.domain.UserNotFoundException;
import hackathon.project.demoservice.exception.domain.UsernameAlreadyExistException;
import hackathon.project.demoservice.model.Professions;
import hackathon.project.demoservice.repo.ProfessionRepository;
import hackathon.project.demoservice.service.ProfessionService;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static hackathon.project.demoservice.constant.UserConstant.*;

@Service
@Transactional
@AllArgsConstructor
public class ProfessionServiceImpl implements ProfessionService {

    private final ProfessionRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContentServiceClient contentServiceClient;

    @Override
    public Optional<Professions> findProfessionsById(Long id) {
        return userRepository.findProfessionsById(id);
    }

    @Override
    public Optional<Professions> findProfessionsByUsername(String username){
        return userRepository.findProfessionsByUsername(username);
    }

    @Override
    public Optional<Professions> findProfessionsByIdOrUsernameOrEmail(Long id, String username, String email) {
        return userRepository.findProfessionsByIdOrUsernameOrEmail(id, username, email);
    }

    @Override
    public Professions saveUser(Professions professions){
        Professions profession = null;
        try{
            profession = userRepository.save(professions);
            contentServiceClient.insertDefaultTires(profession.getId());
        } catch (DataIntegrityViolationException e){
            throw new EmailAlreadyExistException("Email already exists...");
        }
        return profession;
    }

    @Override
    public Professions validateNewUserAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameAlreadyExistException, EmailAlreadyExistException {
        Optional<Professions> ProfessionsByUsername = userRepository.findProfessionsByUsername(newUsername);
        Optional<Professions> ProfessionsByEmail = userRepository.findProfessionsByEmail(newEmail);

        if(StringUtils.isNotBlank(currentUsername)){
            Optional<Professions> currentUser = userRepository.findProfessionsByUsername(currentUsername);
            if (currentUser.isEmpty()){
                throw new UserNotFoundException("User is not found");
            }
            if(ProfessionsByUsername.isPresent() && !currentUser.get().getId().equals(ProfessionsByUsername.get().getId())){
                throw new UsernameAlreadyExistException("Username already exists");
            }
            if(ProfessionsByEmail.isPresent() && !currentUser.get().getId().equals(ProfessionsByEmail.get().getId())){
                throw new EmailAlreadyExistException("Email already exists");
            }
            return currentUser.get();
        }else{
            if(ProfessionsByUsername.isPresent()){
                throw new UsernameAlreadyExistException("Username already exists");
            }
            if(ProfessionsByEmail.isPresent()){
                throw new EmailAlreadyExistException("Email already exists");
            }
            return null;
        }
    }

    @Override
    public Professions resetPasswordByEmail(String email, String oldPassword, String newPassword) {
        Optional<Professions> userOptional = userRepository.findProfessionsByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(USER_NOT_FOUND_BY_EMAIL + " " + email);
        }

        Professions professions = userOptional.get();

        boolean isCorrectPwd = passwordEncoder.matches(oldPassword, newPassword);
        if(isCorrectPwd){
            throw new IncorrectPasswordException("Password is incorrect");
        }

        professions.setPassword(encodePassword(newPassword));
        userRepository.save(professions);

        return professions;
    }

    private String generatePassword() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
