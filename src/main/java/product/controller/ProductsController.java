package product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.model.Product;
import product.service.ProductService;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all", produces = "application/json")
    public List<Product> getProducts() {
        return productService.getMyProducts();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
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
