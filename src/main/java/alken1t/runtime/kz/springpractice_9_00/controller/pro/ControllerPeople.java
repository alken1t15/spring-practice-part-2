package alken1t.runtime.kz.springpractice_9_00.controller.pro;


import alken1t.runtime.kz.springpractice_9_00.entity.Role;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.UsersRepository;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ServiceUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/people")
@AllArgsConstructor
public class ControllerPeople {

    private final ServiceUser serviceUser;


    @GetMapping()
    public String createPeoplePage(Model model) {
        model.addAttribute("user", new Users());
        return "people/create_people_page";
    }

    @PostMapping()
    public String createPeople(@ModelAttribute("user") Users users) {
        serviceUser.save(users);
        return "redirect:/people";
    }

}