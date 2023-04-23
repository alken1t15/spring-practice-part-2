package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import alken1t.runtime.kz.springpractice_9_00.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/task_controller_pro")
public class TaskControllerPro {

    private final TaskService taskService;

    public TaskControllerPro(TaskService taskService) {
        this.taskService = taskService;
    }

//    @GetMapping("/")
//    public String mainPage(@RequestParam(name = "from", required = false) Integer from, @RequestParam(name = "to",required = false) Integer to , Model model){
//        List<Human> humans = null;
//        if (from == null && to == null) {
//            humans = Arrays.stream(taskService.getHUMANS()).collect(Collectors.toList());
//        } else if (from != null && to == null) {
//            humans = taskService.getByHumansMore(from);
//        } else if (from == null && to != null) {
//            humans = taskService.getByHumansLess(to);
//        } else {
//            humans = taskService.getHumansByAge(from, to);
//        }
//        model.addAttribute("humans",humans);
//        return "index";
//    }


}
