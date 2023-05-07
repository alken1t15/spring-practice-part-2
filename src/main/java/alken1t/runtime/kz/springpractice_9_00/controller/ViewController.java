package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import alken1t.runtime.kz.springpractice_9_00.service.CityService;
import alken1t.runtime.kz.springpractice_9_00.service.HumanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/view_controller")
public class ViewController {

    private final CityService cityService;

    private final HumanService humanService;

    public ViewController(HumanService humanService, CityService cityService) {
        this.humanService = humanService;
        this.cityService = cityService;
    }

    @GetMapping(path = "/first_resource")
    public String firstResource(Model model) {
        String message = "Message from ViewController.firstResource";
        model.addAttribute("mess", message);
        return "/view/first_resource";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource(Model model) {
        Human human = new Human("Марк", 15);
        model.addAttribute("human", human);
        return "/view/second_resource";
    }

    @GetMapping(path = "/third_resource")
    public String thirdResource(Model model){
        model.addAttribute("age", humanService.getAverageAgeHuman());
        model.addAttribute("humans", humanService.getHumans());
        //model.addAttribute("tasks",List.of("#Task#1","Task#2","Task#3"));
        return "view/third_resource";
    }

    @GetMapping(path = "/fourth_resource")
    public String fourthResource(Model model){
        model.addAttribute("cityToCount",cityService.getCityToCount());
        model.addAttribute("age",cityService.getAverageHumanOfCity());
        return "view/fourth_resource";
    }
}