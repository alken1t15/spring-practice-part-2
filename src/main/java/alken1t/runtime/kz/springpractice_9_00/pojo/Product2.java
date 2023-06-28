package alken1t.runtime.kz.springpractice_9_00.pojo;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Это поле не может быть пустым")
    private String name;
    @Min(message = "Цена не может быть меньше 0",value = 0)
    private Integer price;

    private List<Value> values;

    public Product2(Long category, String name, Integer price, List<Value> values) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.values = values;
    }
}