package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.*;
import alken1t.runtime.kz.springpractice_9_00.service.UserService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CartService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OrderItemsService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OrdersService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class ControllerOrders {
    private final UserService userService;
    private final OrdersService ordersService;
    private final CartService cartService;
    private final OrderItemsService orderItemsService;
    private final ShopService shopService;

    @GetMapping
    public String mainPage() {

        return "pro/orders/orders_main_page";
    }

    @PostMapping
    public String ordersUsers(@RequestParam("address") String address) {
        Users users = userService.getCurrentUser();
        List<Cart> carts = users.getCarts();
        Orders orders = new Orders(users, Status.WAIT, address, LocalDateTime.now());
        ordersService.save(orders);
        for (Cart cart : carts) {
            Product product = cart.getProduct();
            Shop shop = cart.getShop();
            shop.setCount(shop.getCount()-cart.getCount());
            OrderItems orderItems = new OrderItems(orders, product, cart.getCount());
            orderItemsService.save(orderItems);
            cartService.delete(cart);
        }
        return "redirect:/product?page=1";
    }
}