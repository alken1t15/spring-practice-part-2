package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Role;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceUser {
    private final UsersRepository usersRepository;


    public void save(Users users){
        users.setRole(Role.USER);
        users.setRegistrationDate(LocalDateTime.now());
        usersRepository.save(users);
    }
}
