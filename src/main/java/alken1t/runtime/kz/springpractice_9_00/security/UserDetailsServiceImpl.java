package alken1t.runtime.kz.springpractice_9_00.security;

import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new UserDetailsImpl(usersRepository
//                .findByLogin(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
        return usersRepository
                .findByLogin(username)
                .map(UserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


}