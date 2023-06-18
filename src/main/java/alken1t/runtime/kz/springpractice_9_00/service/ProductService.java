package alken1t.runtime.kz.springpractice_9_00.service;

import alken1t.runtime.kz.springpractice_9_00.pojo.Product;
import jdk.jfr.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private List<Product> categories = new ArrayList<>();

    {
      categories.add(new Product("Смартфон","Samsung",100_000));
      categories.add(new Product("Смартфон","Samsung",200_000));
      categories.add(new Product("Смартфон","Samsung",300_000));
      categories.add(new Product("Смартфон","Apple",650_000));
      categories.add(new Product("Смартфон","Apple",630_000));
      categories.add(new Product("Телевизор","LG",1_000_000));
    }

    public List<Product> findProductCategory(String categoryName){
        if (categoryName== null){
            return categories;
        }
        else{
            return categories.stream().filter(categories -> categories.getCategory().equals(categoryName)).collect(Collectors.toList());
        }
    }

}
