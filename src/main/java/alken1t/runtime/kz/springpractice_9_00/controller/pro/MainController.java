package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Role;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class MainController {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationPage(){
        return "/pro/registration_page";
    }

    @PostMapping("/registration")
    public String registrationAction(@RequestParam(name = "login") String login,
                                     @RequestParam(name = "password") String password){
        Users users = new Users();
        users.setRole(Role.USER);
        users.setLogin((((login))));
        users.setPassword(passwordEncoder.encode(password));
        usersRepository.save(users);
        return "redirect:/login";
    }
}
