package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.exception.CreateNewShop;
import alken1t.runtime.kz.springpractice_9_00.exception.ShopCountUpdate;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Shop;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ProductServiceMain;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ShopService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
        model.addAttribute("err",null);
        return "pro/shop/shop_id_page";
    }

    @GetMapping("/new")
    private String createNewShopPage(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        model.addAttribute("shop",new Shop());
        model.addAttribute("err",null);
        return "pro/shop/create_new_shop";
    }

    @PostMapping()
    public String createNewShop(@RequestParam Long id, Model model, @ModelAttribute(name = "shop") @Valid Shop shop ,BindingResult bindingResult) throws CreateNewShop {
        if (bindingResult.hasErrors()){
            List<Product> products = productService.findAll();
            model.addAttribute("products",products);
            return "pro/shop/create_new_shop";
        }
        Product product = productService.findById(id).orElseThrow();
        Shop shopCheck = shopService.findByProductAndName(product,shop.getName());
        if (shopCheck != null){
            throw new CreateNewShop("Такой магазин уже есть");
        }
        shop.setProduct(product);
        shop.setCount(1);
        shopService.save(shop);
        return "redirect:/shop";
    }

    @PostMapping("/count")
    private String updateCount(@RequestParam Long id, @RequestParam(required = false) Integer count,@RequestParam(name = "product_id")Long idProduct){
        if(count == null){
            throw new ShopCountUpdate("Поле не может быть пустым", idProduct);
        }
       else if (count < 0){
            throw new ShopCountUpdate("Количество товара не может быть ниже 0",idProduct);
        }
        Shop shop = shopService.findById(id);
        shop.setCount(count);
        shopService.save(shop);
        return "redirect:/shop/"+idProduct;
    }

    @ExceptionHandler(ShopCountUpdate.class)
    public String shopCountUpdate(ShopCountUpdate e,Model model){
        Product product = productService.findById(e.getId()).orElseThrow();
        model.addAttribute("product",product);
        model.addAttribute("err",e.getMessage());
        return "pro/shop/shop_id_page";
    }

    @ExceptionHandler(CreateNewShop.class)
    public String createNewShop(CreateNewShop e,Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        model.addAttribute("shop",new Shop());
        model.addAttribute("err",e.getMessage());
        return "pro/shop/create_new_shop";
    }
}