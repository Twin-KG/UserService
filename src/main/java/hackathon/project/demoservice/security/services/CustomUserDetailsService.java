package hackathon.project.demoservice.security.services;

import hackathon.project.demoservice.model.Professions;
import hackathon.project.demoservice.repo.ProfessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfessionRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Professions> userOptional = userRepository.findProfessionsByUsernameOrEmail(username, username);
        Professions professions = userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid Username ... !"));

        return new SecurityUser(professions);
    }
}