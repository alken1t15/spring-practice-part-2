package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ValueRepository extends JpaRepository<Value,Long> {
    List<Value> findAllByProduct(Product product);
}