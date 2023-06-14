package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Option;
import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Value;
import alken1t.runtime.kz.springpractice_9_00.repository.ValueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ValueService {
    private final ValueRepository valueRepository;

    public void save(Value value) {
        valueRepository.save(value);
    }

    public List<Value> findAllByProduct(Product product) {
        return valueRepository.findAllByProduct(product);
    }

    public void createNewValue(Product product, Option option, String s) {
        Value value = new Value(product, option, s);
        save(value);
    }
}