package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Cart;
import alken1t.runtime.kz.springpractice_9_00.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public void delete(Cart cart){
        cartRepository.delete(cart);
    }
}