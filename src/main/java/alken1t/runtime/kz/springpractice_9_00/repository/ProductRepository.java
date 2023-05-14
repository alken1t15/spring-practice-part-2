package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceBefore(int value);

    List<Product> findAllByPriceAfter(int value);

    List<Product> findAllByPriceIsBetween(int from, int to);

    List<Product> findAllByCategoryName(String categoryName);
    List<Product> findAllByCategoryNameOrderByPriceDesc(String categoryName);

    List<Product> findAllByCategoryNameAndPriceBetween(String categoryName,int from, int to);

    List<Product> findAllByCategoryNameOrderByPriceAsc(String categoryName);

    List<Product> findAllByOrderByPriceAsc();
    List<Product> findAllByOrderByPriceDesc();

    List<Product> findAllByCategoryName(String categoryName, Sort sort);

    List<Product> findAllByPriceAfter(Integer price);

    List<Product> findAllByPriceAfterAndPriceBefore(int price, int price2);

    List<Product> findAllByPriceBetween(int price, int price2);

    @Query("select p from Product  p where p.category.name = ?1 and p.price between ?2 and  ?3 order by p.price desc ")
    List<Product> abc(String categoryName,int price, int price2);

    @Query("select p from  Product  p where p.category.name = ?1 and p.price between ?2 and  ?3 ")
    List<Product> findAllByCategoryAndPrice(String categoryName,int from, int to);


    @Transactional
    @Modifying
    @Query("update Product  p set p.price = p.price + (p.price * ?1 / 100) where  p.category.id = ?2")
    void updateProductsPriceByCategory(int percent, long categoryId);
}