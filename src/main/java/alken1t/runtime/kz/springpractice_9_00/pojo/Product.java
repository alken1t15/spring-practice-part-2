package alken1t.runtime.kz.springpractice_9_00.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String category;
    private String name;
    private int price;
}