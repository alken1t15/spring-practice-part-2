package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import alken1t.runtime.kz.springpractice_9_00.service.TaskService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/task_controller")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @GetMapping(path = "/first_resource")
//    public List<Human> firstResource(
//            @RequestParam(name = "from", required = false) Integer from,
//            @RequestParam(name = "to", required = false) Integer to
//    ) {
//        if (from == null && to == null) {
//            return Arrays.stream(taskService.getHUMANS()).collect(Collectors.toList());
//        } else if (from != null && to == null) {
//            return taskService.getByHumansMore(from);
//        } else if (from == null && to != null) {
//            return taskService.getByHumansLess(to);
//        } else {
//            return taskService.getHumansByAge(from, to);
//        }
//    }

    @GetMapping(path = "/first_resource")
    public List<Human> firstResource(
            @RequestParam(name = "from", required = false) Integer from,
            @RequestParam(name = "to", required = false) Integer to
    ) {
        return taskService.getHumansByAge(from,to);
    }

    @GetMapping(path = "/second_resource", produces = "text/html")
    public ResponseEntity response() {
        ResponseEntity response = new ResponseEntity("fdsfsdfsd", HttpStatus.OK);
//        ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body();
        return response;
    }
}