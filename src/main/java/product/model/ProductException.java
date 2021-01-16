package product.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ProductException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    String message = "Unexpected error occurred while processing your request. Please try again later.";
    HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;

    public ProductException() {
        super();
    }

    public ProductException(String errorMessage) {
        super();
        this.message = errorMessage;
    }

    public ProductException(String errorMessage, Throwable throwable) {
        super();
        this.message = errorMessage + ": "+ throwable.getMessage();
    }

    public ProductException(String errorMessage, HttpStatus code) {
        super();
        this.message = errorMessage;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ProductServiceException [message=" + message + ", code=" + code + "]";
    }
}
