package product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import product.model.ProductException;
import product.model.ProductExceptionResponse;

@ControllerAdvice
public class ProductServiceCustomExceptionHandler {

    String message;
    HttpStatus code;

    public ProductServiceCustomExceptionHandler() {
        super();
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductExceptionResponse> handleException(ProductException productException) {
        ResponseEntity<ProductExceptionResponse> responseEntity = new ResponseEntity<ProductExceptionResponse>(
                new ProductExceptionResponse(productException.getMessage(), productException.getCode().value()), productException.getCode());
        return responseEntity;
    }
}
