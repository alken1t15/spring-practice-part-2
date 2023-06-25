package alken1t.runtime.kz.springpractice_9_00.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopCountUpdate extends RuntimeException {
    private final Long id;
    public ShopCountUpdate(String message, Long id) {
        super(message);
        this.id = id;
    }
}