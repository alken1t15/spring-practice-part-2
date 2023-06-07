package alken1t.runtime.kz.springpractice_9_00.service;

import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {
    private final UsersRepository usersRepository;
    public Users getCurrentUser(){
        SecurityContext  context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return usersRepository.findByLogin(authentication.getName()).orElse(null);
    }

    public Users findByLogin(String login){
        return usersRepository.findByLogin(login).orElseThrow();
    }

    public Users findById(Long id) {
      return usersRepository.findById(id).orElseThrow();
    }

    public List<Users> findAll() {
      return usersRepository.findAll();
    }
}
