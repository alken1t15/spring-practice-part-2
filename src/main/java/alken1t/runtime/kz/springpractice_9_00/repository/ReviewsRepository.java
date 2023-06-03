package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Reviews;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewsRepository extends JpaRepository<Reviews,Long> {
    @Query("select count(*) from Reviews s where s.product.id = ?1 and  s.rating!= null")
    int getCountForRating(long id);
    @Query("select sum(s.rating) from Reviews s where s.product.id = ?1 and  s.rating!= null")
    int getRating(long id);

    Reviews findByProduct(Product product);

    Reviews findByProductAndUser(Product product, Users user);
}
