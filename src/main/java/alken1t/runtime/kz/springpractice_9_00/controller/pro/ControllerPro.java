package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import alken1t.runtime.kz.springpractice_9_00.pojo.Product2;
import alken1t.runtime.kz.springpractice_9_00.repository.CategoryRepository;
import alken1t.runtime.kz.springpractice_9_00.repository.OptionRepository;
import alken1t.runtime.kz.springpractice_9_00.repository.ProductRepository;
import alken1t.runtime.kz.springpractice_9_00.repository.ValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/controller_pro")
public class ControllerPro {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final OptionRepository optionRepository;

    private final ValueRepository valueRepository;

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

    @GetMapping("/choice_category_new")
    public String createProductPage(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "pro/choice_category_page";
    }

    @GetMapping("/product_new")
    public String createProduct(@RequestParam(name = "category") Long categoryId,Model model){
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        List<Option> options = optionRepository.findAllByCategory(category);
        model.addAttribute("options",options);
        model.addAttribute("categoryId",categoryId);
        return "pro/create_product_page";
    }

    @PostMapping("/product_new")
    public String createProduct(@RequestParam(name = "name") String name
    ,@RequestParam(name = "price") Integer price
            ,@RequestParam(name = "categoryId") Long categoryId
    ,@RequestParam(name = "options") List<String> options){
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        productRepository.save(product);
        List<Option> options1 = optionRepository.findAllByCategory(category);
        for(int i = 0; i<options1.size();i++){
            Value value = new Value();
            value.setProduct(product);
            value.setValue(options.get(i));
            value.setOption(options1.get(i));
            valueRepository.save(value);
        }

        return "redirect:/controller_pro/product";
    }

    @GetMapping("/category_new")
    public String createCategoryPage(){

        return "pro/category_page_create";
    }

    @PostMapping("/category_new")
    public String createProduct(@RequestParam String category){
        Category category1 = new Category();
        category1.setName(category);
        categoryRepository.save(category1);

        return "redirect:/controller_pro/product";
    }

    @GetMapping("/option_new")
    public String createOptionPage(Model model){
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "pro/option_page_create";
    }

    @PostMapping("/option_new")
    public String createOption(@RequestParam(name = "category") Long id,@RequestParam(name = "option") String name){
        Option optional = new Option();
        optional.setName(name);
        Category category = categoryRepository.findById(id).orElseThrow();
        optional.setCategory(category);
        optionRepository.save(optional);
        return "redirect:/controller_pro/product";
    }

    @GetMapping("/edit_product/{id}")
    private String editProductPage(@PathVariable("id") Long id, Model model){
        Product product = productRepository.findById(id).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        List<Value> values = valueRepository.findAllByProduct(product);
        Product2 product2 = new Product2(product.getId(),product.getCategory().getId(),product.getName(),product.getPrice(),values);
        model.addAttribute("product",product2);
        model.addAttribute("categories", categories);
     //   model.addAttribute("values", values);
        for(Value str : values){
            System.out.println(str.getValue());
        }
        return "pro/product_page_edit";
    }

    @PatchMapping("/edit_product/{id}")
    private String editProduct(@PathVariable("id") Long id,@ModelAttribute("product") Product2 product){
        Category category = categoryRepository.findById(product.getCategory()).orElseThrow();
        Product product1 = productRepository.findById(id).orElseThrow();
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        product1.setCategory(category);
        for(Value str : product.getValues()){
            System.out.println(str.getValue());
        }

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