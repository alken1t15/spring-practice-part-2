package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.entity.Role;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    @ResponseBody
    public String registrationAction(@RequestParam(name = "login") String login,
                                     @RequestParam(name = "password") String password){
        Users users = new Users();
        users.setRole(Role.USER);
        users.setLogin((((login))));
        users.setPassword(passwordEncoder.encode(password));
        usersRepository.save(users);
        return "Registration success";
    }
}
