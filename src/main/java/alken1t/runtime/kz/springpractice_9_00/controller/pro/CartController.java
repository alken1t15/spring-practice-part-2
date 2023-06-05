package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Cart;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final UserService userService;

    @GetMapping
    public String cartPage(Model model){
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        model.addAttribute("carts",carts);
        return "/pro/cart_page";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam(name = "plus",required = false) boolean plus, @RequestParam(name = "minus",required = false) boolean minus,
                             @RequestParam(name = "product") String product ,Model model){
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        for (Cart cart : carts){
            if(cart.getProduct().getName().equals(product)){
                if(plus){
                    cart.setCount(cart.getCount()+1);
                }
                else if(cart.getCount()!=0){

                }
            }
        }
        model.addAttribute("carts",carts);
        return "/pro/cart_page";
    }
}
