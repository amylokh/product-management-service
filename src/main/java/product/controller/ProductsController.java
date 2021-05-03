package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import product.model.Product;
import product.model.ProductException;
import product.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProduct(@PathVariable String id) {
        Product product= productService.getProduct(id);
        if (product!=null) {
            return product;
        }
        else {
            throw new ProductException("Product not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/{id}")
    public Product createProduct(@PathVariable("id") String id, @Valid @RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);
        if (addedProduct!=null) return addedProduct;
        throw new ProductException("Product already exits", HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public void updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
       Product prod = productService.updateProduct(id, product);
       if (prod==null) {
           throw new ProductException("Product not found", HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

}
