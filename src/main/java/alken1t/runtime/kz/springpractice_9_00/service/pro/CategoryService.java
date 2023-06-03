package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private  final CategoryRepository categoryRepository;

    public Category findById(Long category){
        return categoryRepository.findById(category).orElseThrow();
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
