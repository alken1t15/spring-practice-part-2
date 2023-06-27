package alken1t.runtime.kz.springpractice_9_00.exception;

public class CreateNewCategory extends RuntimeException{
    public CreateNewCategory(String message) {
        super(message);
    }
}