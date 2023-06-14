package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Category;
import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import alken1t.runtime.kz.springpractice_9_00.repository.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;

    public List<Option> findAllByCategory(Category category){
        return optionRepository.findAllByCategory(category);
    }

    public void createNewOption(Category category, String name) {
        Option optional = new Option(category,name);
        optionRepository.save(optional);
    }
}
