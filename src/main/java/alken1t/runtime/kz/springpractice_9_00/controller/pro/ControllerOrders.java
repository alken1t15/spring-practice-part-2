package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.*;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CartService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class ControllerOrders {
    private final UserService userService;
    private final OrdersService ordersService;
    private final CartService cartService;

    @GetMapping
    public String mainPage(){

        return "pro/orders/orders_main_page";
    }
    @PostMapping
    public String ordersUsers(@RequestParam("address") String address){
        System.out.println("Попал");
        Users currentUser = userService.getCurrentUser();
        Users users = userService.findByLogin(currentUser.getLogin());
        List<Cart> carts = users.getCarts();
        List<Product> products = new ArrayList<>();
        for (Cart cart: carts){
            Product product = cart.getProduct();
            int i = 0;
            do {
                products.add(product);
                i++;
            }while (cart.getCount() != i);
            cartService.delete(cart);
        }
        Orders orders = new Orders();
        orders.setUser(users);
        orders.setOrderDate(LocalDateTime.now());
        orders.setDeliveryAddress(address);
        orders.setProducts(products);
        orders.setStatus(Status.WAIT);
        ordersService.save(orders);
        return "redirect:/product?page=1";
    }
}
