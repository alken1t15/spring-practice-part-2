package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/security_controller")
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityController {
    private final UserService userService;

    @GetMapping(path = "/first_resource")
    public String firstResource() {
        return "SecurityController.firstResource()";
    }

    @GetMapping(path = "/second_resource")
    public String secondResource() {
        return "SecurityController.secondResource()";
    }

    @GetMapping(path = "/third_resource")
    @PreAuthorize("isAuthenticated() && hasRole('admin')")
    public String thirdResource(){
        return "Security.thirdResource";
    }

    @GetMapping(path = "/current_user")
    public String currentUser(){
        Users currentUser = userService.getCurrentUser();
        return currentUser.getLogin();
    }
}