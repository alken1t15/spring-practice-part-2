package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_users")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "id_shop")
    private Shop shop;

    public Cart(Users users, Product product, Integer count,Shop shop) {
        this.users = users;
        this.product = product;
        this.count = count;
        this.shop = shop;
    }
}