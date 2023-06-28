package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import alken1t.runtime.kz.springpractice_9_00.exception.CreateNewCategory;
import alken1t.runtime.kz.springpractice_9_00.exception.CreateNewOption;
import alken1t.runtime.kz.springpractice_9_00.pojo.Product2;
import alken1t.runtime.kz.springpractice_9_00.service.ProductService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CategoryService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OptionService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ProductServiceMain;
import alken1t.runtime.kz.springpractice_9_00.service.pro.ValueService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/controller_pro")
public class ControllerPro {

    private final CategoryService categoryService;

    private final OptionService optionService;

    private final ValueService valueService;

    private final ProductServiceMain productService;

    @GetMapping("/choice_category_new")
    public String createProductPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "pro/choice_category_page";
    }

    @GetMapping(path = "/create_product")
    public String createProduct(@RequestParam(name = "category") Long id,Model model){
        Category category = categoryService.findById(id);
        List<Option> options = optionService.findAllByCategory(category);
        Product2 product = new Product2();
        product.setCategory(id);
        model.addAttribute("product",product);
        model.addAttribute("options",options);
        return "pro/product_create_page";
    }

    @PostMapping("/create_product")
    public String createProduct(@RequestParam(value = "value",required = false) List<String> values, Model model, @ModelAttribute("product") @Valid Product2 product2, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Category category = categoryService.findById(product2.getCategory());
            List<Option> options = optionService.findAllByCategory(category);
            model.addAttribute("options",options);
            return "pro/product_create_page";
        }
        Category category = categoryService.findById(product2.getCategory());
        Product product = new Product();
        product.setName(product2.getName());
        product.setPrice(product2.getPrice());
        product.setCategory(category);
        productService.save(product);
        List<Option> options = optionService.findAllByCategory(category);
        for (int i = 0; i < values.size(); i++) {
            Value value = new Value();
            value.setValue(values.get(i));
            value.setProduct(product);
            value.setOption(options.get(i));
            valueService.save(value);
        }
        return "redirect:/product?page=1";
    }

    @GetMapping("/category_new")
    public String createCategoryPage(Model model) {
        model.addAttribute("err",null);
        return "pro/category_page_create";
    }

    @PostMapping("/category_new")
    public String createCategory(@RequestParam(required = false) String category) {
        if (category== null || category.isEmpty()){
            throw new CreateNewCategory("Поле не может быть пустым");
        }
        categoryService.createNewCategory(category);
        return "redirect:/product?page=1";
    }

    @GetMapping("/option_new")
    public String createOptionPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("err",null);
        return "pro/option_page_create";
    }

    @PostMapping("/option_new")
    public String createOption(@RequestParam(name = "category") Long id, @RequestParam(name = "option",required = false) String name) {
        if(name == null || name.isEmpty()){
            throw new CreateNewOption("Поле не может быть пустым");
        }
        Category category = categoryService.findById(id);
        optionService.createNewOption(category, name);
        return "redirect:/product?page=1";
    }

    @ExceptionHandler(CreateNewOption.class)
    public String errorCreateNewOption(CreateNewOption e,Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("err",e.getMessage());
        return "pro/option_page_create";
    }

    @ExceptionHandler(CreateNewCategory.class)
    public String errorCreateNewCategory(CreateNewCategory e, Model model){
        model.addAttribute("err",e.getMessage());
        return "pro/category_page_create";
    }
}