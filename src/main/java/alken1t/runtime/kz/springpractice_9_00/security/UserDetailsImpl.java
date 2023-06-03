package alken1t.runtime.kz.springpractice_9_00.security;

import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import alken1t.runtime.kz.springpractice_9_00.security.*;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = users.getRole().name().toLowerCase();
        GrantedAuthorityImpl authority = new GrantedAuthorityImpl(roleName,true);
        return List.of(authority);
    }

    @Override
    public String getUsername() {
        return users.getLogin();
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
