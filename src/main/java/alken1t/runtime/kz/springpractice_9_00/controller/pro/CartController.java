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
    public String cartPage(Model model){
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        int totalPrice = 0 ;
        for (Cart cart : carts){
            int temp = cart.getCount() * cart.getProduct().getPrice();
            totalPrice+=temp;
        }
        model.addAttribute("carts",carts);
        model.addAttribute("totalPrice",totalPrice);
        return "/pro/cart_page";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam(name = "plus",required = false) boolean plus, @RequestParam(name = "minus",required = false) boolean minus,
                             @RequestParam(name = "delete",required = false) boolean delete,
                             @RequestParam(name = "product") String product){
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        for (Cart cart : carts){
            if(cart.getProduct().getName().equals(product)){
                if (delete){
                    cartService.delete(cart);
                }
                else if(plus){
                    cart.setCount(cart.getCount()+1);
                    cartService.save(cart);
                }
                else if(cart.getCount()!=1){
                    cart.setCount(cart.getCount()-1);
                    cartService.save(cart);
                }

            }
        }
        return "redirect:/cart";
    }

    @DeleteMapping("/clear")
    public String clearCart(){
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        for (Cart cart : carts){
            cartService.delete(cart);
        }
        return "redirect:/product?page=1";
    }
}
