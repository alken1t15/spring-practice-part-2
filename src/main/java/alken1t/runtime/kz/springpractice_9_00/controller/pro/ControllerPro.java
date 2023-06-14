package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CategoryService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/controller_pro")
public class ControllerPro {

    private final CategoryService categoryService;

    private final OptionService optionService;

    @GetMapping("/choice_category_new")
    public String createProductPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "pro/choice_category_page";
    }

    @GetMapping("/category_new")
    public String createCategoryPage() {
        return "pro/category_page_create";
    }

    @PostMapping("/category_new")
    public String createCategory(@RequestParam String categoryName) {
        categoryService.createNewCategory(categoryName);
        return "redirect:/controller_pro/product";
    }

    @GetMapping("/option_new")
    public String createOptionPage(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "pro/option_page_create";
    }

    @PostMapping("/option_new")
    public String createOption(@RequestParam(name = "category") Long id, @RequestParam(name = "option") String name) {
        Category category = categoryService.findById(id);
        optionService.createNewOption(category, name);
        return "redirect:/controller_pro/product";
    }

}