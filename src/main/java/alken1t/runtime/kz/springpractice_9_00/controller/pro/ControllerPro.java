package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.pojo.Product2;
import alken1t.runtime.kz.springpractice_9_00.repository.CategoryRepository;
import alken1t.runtime.kz.springpractice_9_00.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
@RequestMapping("/controller_pro")
public class ControllerPro {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @GetMapping("/product")
    public String mainPage(@RequestParam(name = "page",required = false) Integer page, Model model){
        List<Product> products;
        if (page==null){
            products = productRepository.findAll();
        }
        else{
            Pageable pageable = PageRequest.of(page-1,3);
            products = productRepository.findAll(pageable).getContent();
        }
        model.addAttribute("products",products);
        return "pro/product_page_main";
    }

    @GetMapping("/product_new")
    public String createProductPage(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product", new Product2());
        model.addAttribute("categories", categories);
        return "pro/product_page_create";
    }

    @PostMapping("/product_new")
    public String createProduct(@ModelAttribute("product") Product2 product){
        Category category = categoryRepository.findById(product.getCategory()).orElseThrow();
        Product product1 = new Product(category,product.getName(),product.getPrice());
        productRepository.save(product1);

        return "redirect:/controller_pro/product";
    }

    @GetMapping("/edit_product/{id}")
    private String editProductPage(@PathVariable("id") Long id, Model model){
        Product product = productRepository.findById(id).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        Product2 product2 = new Product2(product.getId(),product.getCategory().getId(),product.getName(),product.getPrice());
        model.addAttribute("product",product2);
        model.addAttribute("categories", categories);
        return "pro/product_page_edit";
    }

    @PatchMapping("/edit_product/{id}")
    private String editProduct(@PathVariable("id") Long id,@ModelAttribute("product") Product2 product){
        Category category = categoryRepository.findById(product.getCategory()).orElseThrow();
        Product product1 = productRepository.findById(id).orElseThrow();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(category);
        productRepository.save(product1);
        return "redirect:/controller_pro/product";
    }

    @DeleteMapping("/delete_product/{id}")
    private String deleteProduct(@PathVariable("id") Long id){
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/controller_pro/product";
    }
}