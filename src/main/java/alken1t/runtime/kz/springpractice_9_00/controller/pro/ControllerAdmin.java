package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Reviews;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OrdersService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ReviewsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class ControllerAdmin {
    private final UserService userService;
    private final ReviewsService reviewsService;
    private final OrdersService ordersService;

    @GetMapping
    public String mainPage(Model model) {
        List<Users> usersList = userService.findAll();
        model.addAttribute("usersList", usersList);
        return "pro/admin/main_page_admin";
    }

    @GetMapping("/{id}")
    public String oneUser(@PathVariable(name = "id") long id, Model model) {
        Users user = userService.findById(id);
        List<Reviews> reviews = reviewsService.findByUserAndPublished(user, false);
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);
        return "pro/admin/user_page_admin";
    }

    @PostMapping("/comment")
    public String publishedComment(@RequestParam(name = "id") long id, @RequestParam("published") Boolean published) {
        Reviews reviews = reviewsService.findById(id);
        reviews.setPublished(published);
        reviewsService.save(reviews);
        return "redirect:/admin";
    }

    @PostMapping("/order")
    public String editStatus(@RequestParam(name = "id") long id, @RequestParam("status") String status) {
        ordersService.editStatus(id, status);
        return "redirect:/admin";
    }

}