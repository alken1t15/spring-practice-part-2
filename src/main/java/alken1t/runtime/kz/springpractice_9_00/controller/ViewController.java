package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {

    @GetMapping(path = "/first_resource")
    public String firstResource(Model model) {
        String message = "Message from ViewController.firstResource";
        model.addAttribute("mess", message);
        return "/view/first_resource";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource(Model model) {
        Human human = new Human("Марк", 34);
        model.addAttribute("human", human);
        return "/view/second_resource";
    }
}