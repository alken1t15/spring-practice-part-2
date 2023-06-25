package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Shop;
import alken1t.runtime.kz.springpractice_9_00.repository.ShopRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;


    private List<Shop> findAll() {
        return shopRepository.findAll();
    }

    public Shop findById(Long id) {
        return shopRepository.findById(id).orElseThrow();
    }

    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    public Shop findByProductAndName(Product product, @NotEmpty(message = "Поле должно быть заполненным") String name) {
        return shopRepository.findByProductAndName(product, name);
    }
}