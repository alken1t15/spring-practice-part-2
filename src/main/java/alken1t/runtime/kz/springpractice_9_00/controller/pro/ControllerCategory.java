package alken1t.runtime.kz.springpractice_9_00.controller.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import alken1t.runtime.kz.springpractice_9_00.service.pro.CategoryService;
import alken1t.runtime.kz.springpractice_9_00.service.pro.OptionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class ControllerCategory {
    private final CategoryService categoryService;
    private final OptionService optionService;
    private final EntityManager entityManager;

    @GetMapping
    public String selectCategory(Model model){
        List<Category> category = categoryService.findAll();
        model.addAttribute("categories",category);
        return "pro/category/select_category_page";
    }

    @GetMapping(path = "/find")
    public String findProductOnCategory(@RequestParam(name = "category") Long id,@RequestParam(name = "sort",required = false) List<String> sorts ,Model model){
        if (sorts != null){
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);

            Root<Product> root = criteriaQuery.from(Product.class);
            Join<Product, Value> valueJoin = root.join("values");

       //     Predicate predicate1 = builder.equal(valueJoin.get("id"),1);

            List<Predicate> predicates =  new ArrayList<>();

            for(String str : sorts){
                Predicate predicate1 = builder.equal(valueJoin.get("id"),str);
                predicates.add(predicate1);
            }

//            criteriaQuery.select(root).where(predicate1);

            criteriaQuery.select(root).where(builder.and(predicates.toArray(new Predicate[0])));

            TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);
            List<Product> results = query.getResultList();
            System.out.println(results.get(0).getName());

        }


        Category category = categoryService.findById(id);
        List<Option> options = optionService.findAllByCategory(category);
        model.addAttribute("options",options);
        model.addAttribute("id",id);
        return "pro/category/main_page";
    }
}
