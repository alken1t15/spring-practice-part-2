package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Cart;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final UserService userService;
    private final CartService cartService;

    @GetMapping
    public String cartPage(Model model) {
        Users users = userService.getCurrentUser();
        List<Cart> carts = users.getCarts();
        int totalPrice = cartService.getTotalPrice(users);
        model.addAttribute("carts", carts);
        model.addAttribute("totalPrice", totalPrice);
        return "/pro/cart_page";
    }

    //TODO orderItems

    @PostMapping("/add")
    public String addProduct(@RequestParam(name = "plus", required = false) boolean plus,
                             @RequestParam(name = "delete", required = false) boolean delete,
                             @RequestParam(name = "id") Long product) {
        Users users = userService.getCurrentUser();
        cartService.updateCount(plus, delete, product, users);
        return "redirect:/cart";
    }

    @DeleteMapping("/clear")
    public String clearCart() {
        Users users = userService.getCurrentUser();
        cartService.clearCart(users);
        return "redirect:/product?page=1";
    }
}