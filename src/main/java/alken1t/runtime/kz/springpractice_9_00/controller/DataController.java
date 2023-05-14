package alken1t.runtime.kz.springpractice_9_00.controller;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.repository.CategoryRepository;
import alken1t.runtime.kz.springpractice_9_00.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/data_controller")
@AllArgsConstructor
public class DataController {
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


    @ResponseBody
    @GetMapping(path = "/first_resource")
    public String firstResource(){
        Category category = categoryRepository.findById(2L).orElseThrow();
        return  category.getName();
    }

    @GetMapping("/second_resource")
    public String secondResource(Model model){
        Pageable pageable =  PageRequest.of(4,2);
        Page<Product> products = productRepository.findAll(pageable);

//       Sort sort = Sort.by(Sort.Order.desc("price")
//               , Sort.Order.asc("name"));
     //  Sort sort1 = Sort.by(Sort.Direction.ASC, "price");
    //    List<Product> products = productRepository.findAll(sort);
        //products.sort(Comparator.comparingInt(Product::getPrice));
        model.addAttribute("products",products);
        return "data/second_resource_page";
    }

    @GetMapping("/product_page_task_controller")
    public String thirdResource(@RequestParam(name = "page",required = false) Integer page, Model model){
        List<Product> products;
        if (page==null){
            products = productRepository.findAll();
        }
        else{
            Pageable pageable = PageRequest.of(page-1,3);
            products = productRepository.findAll(pageable).getContent();
        }
        model.addAttribute("products",products);
        return "data/product_page_task";
    }

    @GetMapping(path = "/fourth_resource")
    public String fourthResource(Model model){
       // List<Product> products = productRepository.findAllByCategoryName("Смартфоны");
       // List<Product> products = productRepository.findAllByCategoryNameOrderByPriceDesc("Смартфоны");
      //  Sort sort = Sort.by(Sort.Order.desc("price"));
        // List<Product> products = productRepository.findAllByCategoryName("Смартфоны",sort);
      //  List<Product> products = productRepository.findAllByPriceAfter(300_000);
       // List<Product> products = productRepository.findAllByPriceBetween(200_000,400_000);
        List<Product> products = productRepository.abc("Смартфоны",200_000,400_000);
        model.addAttribute("products",products);
        return "data/second_resource_page";
    }

    @GetMapping(path = "/product_resource")
    public String shopProduct(@RequestParam(name = "category_name",required = false)String categoryName,
                                  @RequestParam(name = "sort" ,required = false) String sort,Model model){
        List<Product> products;
        if (categoryName==null){
            products = productRepository.findAll();
        }
        else if(categoryName!=null && sort == null){
            products = productRepository.findAllByCategoryName(categoryName);
        }
        else if(!categoryName.isEmpty() && sort.equals("asc")){
            products = productRepository.findAllByCategoryNameOrderByPriceAsc(categoryName);
        }
        else if(!categoryName.isEmpty() && sort.equals("desk")){
            products = productRepository.findAllByCategoryNameOrderByPriceDesc(categoryName);
        }
        else if (sort.equals("asc") ){
            products = productRepository.findAllByOrderByPriceAsc();
        }
        else if(sort.equals("desk")){
            products = productRepository.findAllByOrderByPriceDesc();
        }
        else {
            products = null;
        }
        model.addAttribute("category",categoryName);
        model.addAttribute("products",products);
        return "data/product_page_task_sort";
    }
}
