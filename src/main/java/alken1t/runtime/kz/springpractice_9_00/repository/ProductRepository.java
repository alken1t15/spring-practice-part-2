package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBefore(int value);

    List<Product> findAllByPriceAfter(int value);

    List<Product> findAllByPriceIsBetween(int from, int to);

    List<Product> findAllByCategoryName(String categoryName);

    List<Product> findAllByCategoryNameAndPriceBetween(String categoryName,int from, int to);

    @Query("select p from  Product  p where p.category.name = ?1 and p.price between ?2 and  ?3 ")
    List<Product> findAllByCategoryAndPrice(String categoryName,int from, int to);


    @Transactional
    @Modifying
    @Query("update Product  p set p.price = p.price + (p.price * ?1 / 100) where  p.category.id = ?2")
    void updateProductsPriceByCategory(int percent, long categoryId);
}