package alken1t.runtime.kz.springpractice_9_00.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewProductCart extends RuntimeException{
    private Long id;
    public AddNewProductCart(String message, Long id) {
        super(message);
        this.id = id;
    }
}