package alken1t.runtime.kz.springpractice_9_00.service;

import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UsersRepository usersRepository;
    public Users getCurrentUser(){
        SecurityContext  context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return usersRepository.findByLogin(authentication.getName()).orElse(null);
    }
}
