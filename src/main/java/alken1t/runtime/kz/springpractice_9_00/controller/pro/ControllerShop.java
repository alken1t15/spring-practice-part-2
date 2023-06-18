package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Shop;
import alken1t.runtime.kz.springpractice_9_00.service.ProductService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ProductServiceMain;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/shop")
@AllArgsConstructor
public class ControllerShop {
    private final ProductServiceMain productService;
    private final ShopService shopService;

    @GetMapping
    public String mainPage(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "/pro/shop/shop_main_page";
    }

    @GetMapping("/{id}")
    public String pageProductShop(@PathVariable Long id,Model model){
        Product product = productService.findById(id).orElseThrow();
        model.addAttribute("product",product);
        return "pro/shop/shop_id_page";
    }

    @GetMapping("/new")
    private String createNewShopPage(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "pro/shop/create_new_shop";
    }

    @PostMapping()
    public String createNewShop(@RequestParam Long id, @RequestParam String name, @RequestParam String address){
        Product product = productService.findById(id).orElseThrow();
        Shop shop = new Shop();
        shop.setName(name);
        shop.setAddress(address);
        shop.setProduct(product);
        shopService.save(shop);
        return "redirect:/shop";
    }

    @PostMapping("/count")
    private String updateCount(@RequestParam Long id, @RequestParam Integer count,@RequestParam(name = "product_id")Long idProduct){
        Shop shop = shopService.findById(id);
        shop.setCount(count);
        shopService.save(shop);
        return "redirect:/shop/"+idProduct;
    }
}