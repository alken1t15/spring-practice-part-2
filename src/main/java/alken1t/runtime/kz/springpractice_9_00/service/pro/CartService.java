package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Cart;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    public void delete(Cart cart) {
        cartRepository.delete(cart);
    }

    public int getTotalPrice(Users users) {
        List<Cart> carts = users.getCarts();
        int totalPrice = 0;
        for (Cart cart : carts) {
            int temp = cart.getCount() * cart.getProduct().getPrice();
            totalPrice += temp;
        }
        return totalPrice;
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart findByUsersAndProduct(long users, long product) {
        return cartRepository.findByUsersAndProduct(users, product);
    }

    public void updateCount(boolean plus, boolean delete, long product, Users users) {
        Cart cart = findByUsersAndProduct(users.getId(), product);
        if (delete) {
            delete(cart);
        } else if (plus) {
            cart.setCount(cart.getCount() + 1);
            save(cart);
        } else if (cart.getCount() != 1) {
            cart.setCount(cart.getCount() - 1);
            save(cart);
        }
    }

    public void clearCart(Users users) {
        List<Cart> carts = users.getCarts();
        for (Cart cart : carts) {
            delete(cart);
        }
    }
}