package alken1t.runtime.kz.springpractice_9_00.pojo;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product2 {
    private Long id;

    private Long category;

    private String name;

    private Integer price;

    public Product2(Long category, String name, Integer price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }
}