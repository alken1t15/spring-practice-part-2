package alken1t.runtime.kz.springpractice_9_00.exception;

public class AddNewAddress extends RuntimeException{
    public AddNewAddress(String message) {
        super(message);
    }
}