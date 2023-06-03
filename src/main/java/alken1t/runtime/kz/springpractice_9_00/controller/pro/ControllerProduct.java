package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.*;
import alken1t.runtime.kz.springpractice_9_00.pojo.Product2;
import alken1t.runtime.kz.springpractice_9_00.service.pro.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
@AllArgsConstructor
public class ControllerProduct {
    private final ProductServiceMain productService;
    private final CategoryService categoryService;
    private final OptionService optionService;
    private final ValueService valueService;
    private final ReviewsService reviewsService;

    @GetMapping()
    public String mainPage(@RequestParam(name = "page", required = false) Integer page, Model model) {
        Pageable pageable = PageRequest.of(page - 1, 3);
        Page<Product> productPage = productService.findAll(pageable);
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("currentPage", productPage.getNumber() + 1);
        return "pro/product/product_page_main";
    }

    @GetMapping("/{id}")
    public String pageProduct(@PathVariable("id") long id,Model model){
        Product product = productService.findById(id).orElseThrow();
        double rating = reviewsService.getRating(product.getId());
        Reviews reviews = reviewsService.findByProduct(product);
        model.addAttribute("product",product);
        model.addAttribute("rating",rating);
        model.addAttribute("reviews",reviews);
        return "pro/product/product_page_id";
    }


    @PostMapping("/new")
    public String createProduct(@RequestParam(name = "name") String name
            , @RequestParam(name = "price") Integer price
            , @RequestParam(name = "categoryId") Long categoryId
            , @RequestParam(name = "options") List<String> options) {
        Category category = categoryService.findById(categoryId);
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        productService.save(product);
        List<Option> options1 = optionService.findAllByCategory(category);
        for (int i = 0; i < options1.size(); i++) {
            Value value = new Value();
            value.setProduct(product);
            value.setValue(options.get(i));
            value.setOption(options1.get(i));
            valueService.save(value);
        }

        return "redirect:/product?page=1";
    }


    @GetMapping("/edit_product/{id}")
    private String editProductPage(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id).orElseThrow();
        List<Category> categories = categoryService.findAll();
        List<Value> values = valueService.findAllByProduct(product);
        Product2 product2 = new Product2(product.getId(), product.getCategory().getId(), product.getName(), product.getPrice(), values);
        model.addAttribute("product", product2);
        model.addAttribute("categories", categories);
        model.addAttribute("values", values);
        return "pro/product/product_page_edit";
    }

    @PatchMapping("/edit_product/{id}")
    private String editProduct(@PathVariable("id") Long id,
                               @RequestParam(name = "options") List<String> valuesUpdate,
                               @ModelAttribute("product") Product2 product) {
        Product product1 = productService.findById(id).orElseThrow();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        List<Value> values = product1.getValues();
        for (int i = 0; i<values.size();i++){
            String valueName = valuesUpdate.get(i);
            Value value = values.get(i);
            value.setValue(valueName);
            valueService.save(value);
        }
        productService.save(product1);
        return "redirect:/product?page=1";
    }

    @DeleteMapping("/delete_product/{id}")
    private String deleteProduct(@PathVariable("id") Long id) {
        Product product = productService.findById(id).orElseThrow();
        productService.delete(product);
        return "redirect:/product?page=1";
    }
}