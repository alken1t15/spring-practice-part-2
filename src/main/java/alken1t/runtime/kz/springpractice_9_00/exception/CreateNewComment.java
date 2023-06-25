package alken1t.runtime.kz.springpractice_9_00.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewComment extends RuntimeException {
    private Long id;
    public CreateNewComment(String message, Long idProduct) {
        super(message);
        id=idProduct;
    }
}