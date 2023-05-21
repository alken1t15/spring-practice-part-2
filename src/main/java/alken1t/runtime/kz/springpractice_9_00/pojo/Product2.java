package alken1t.runtime.kz.springpractice_9_00.pojo;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product2 {
    private Long id;

    private Long category;

    private String name;

    private Integer price;

    private List<Value> values;

    public Product2(Long category, String name, Integer price, List<Value> values) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.values = values;
    }
}