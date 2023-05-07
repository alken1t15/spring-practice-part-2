package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.pojo.Product;
import alken1t.runtime.kz.springpractice_9_00.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/product_task_controller")
@Controller
public class ProductTaskController {

    private final ProductService productService;

    public ProductTaskController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String firstResource(@RequestParam(name = "categoryName", required = false) String categoryName, Model model) {
        List<Product> products = productService.findProductCategory(categoryName);
        model.addAttribute("products", products);
        return "view/product_resource";
    }
}