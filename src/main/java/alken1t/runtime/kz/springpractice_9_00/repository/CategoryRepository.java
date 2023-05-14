package alken1t.runtime.kz.springpractice_9_00.repository;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}