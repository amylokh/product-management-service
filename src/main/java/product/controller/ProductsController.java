package product.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;
import product.model.Product;
import product.service.ProductService;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product")
@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Optional<Product> getProduct(@PathVariable String id) {
        Optional<Product> product= productService.getProduct(id);
        if (product.isPresent()){
            return product;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found");
        }
    }

    @PostMapping(value = "/{id}")
    public void createProduct(@PathVariable("id") String id, @RequestBody Product product) {
        productService.addProduct(product);
    }

    @PutMapping(value = "/{id}")
    public void updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

}
