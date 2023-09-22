package hackathon.project.demoservice.security.services;

import hackathon.project.demoservice.enumeration.ActiveStatus;
import hackathon.project.demoservice.model.Professions;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private final Professions professions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return professions.getPassword();
    }

    @Override
    public String getUsername() {
        return professions.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ActiveStatus.ACTIVE.equals(professions.getActiveStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return ActiveStatus.ACTIVE.equals(professions.getActiveStatus());
    }
}
