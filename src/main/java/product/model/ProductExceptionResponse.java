package product.model;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ProductExceptionResponse implements Serializable {
    String message = "An Error occurred while processing your request. Please try again later.";
    int code = 500;

    public ProductExceptionResponse(String message, int code) {
        super();
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "ProductExceptionResponse [message=" + message + ", code=" + code + "]";
    }

}
