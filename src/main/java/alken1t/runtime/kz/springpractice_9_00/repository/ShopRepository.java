package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository  extends JpaRepository<Shop,Long> {
}