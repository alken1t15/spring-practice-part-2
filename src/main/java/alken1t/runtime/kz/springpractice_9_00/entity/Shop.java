package alken1t.runtime.kz.springpractice_9_00.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "shop")
@Getter
@Setter
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @NotEmpty(message = "Поле должно быть заполненным")
    private String name;
    @NotEmpty(message = "Поле должно быть заполненным")
    private String address;
    @Min(value = 0,message = "Значение не может быть меньше нуля")
    private Integer count;
    @OneToMany(mappedBy = "shop")
    private List<Cart> carts;
}