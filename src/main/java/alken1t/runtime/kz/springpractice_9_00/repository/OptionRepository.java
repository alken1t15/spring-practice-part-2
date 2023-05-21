package alken1t.runtime.kz.springpractice_9_00.repository;


import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option,Long> {

    List<Option> findAllByCategory(Category category);
}