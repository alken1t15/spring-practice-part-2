package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Shop;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository  extends JpaRepository<Shop,Long> {
    Shop findByProductAndName(Product product, @NotEmpty(message = "Поле должно быть заполненным") String name);
}