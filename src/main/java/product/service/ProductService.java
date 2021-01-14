package product.service;

import org.springframework.stereotype.Service;
import product.model.Product;
import product.repository.DataRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final DataRepository dataRepository;

    private List<Product> myProducts = new ArrayList<>(Arrays.asList(new Product("123", "lays", "amazing chips")
            , new Product("1234", "balaji", "good chips"),
            new Product("12313", "nestle", "amazing chocolates")));

    public ProductService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public List<Product> getProducts() {
        return dataRepository.findAll();
    }

    public Optional<Product> getProduct(String id) {
        Optional<Product> product = dataRepository.findById(id);
        return product;
    }

    public void addProduct(Product product) {
        dataRepository.save(product);
    }

    public void updateProduct(String id, Product product) {
        for(int i=0; i<myProducts.size();i++) {
            Product p = myProducts.get(i);
            if (p.getId().equals(id)) {
                myProducts.set(i, product);
                return;
            }
        }
    }

    public void deleteProduct(String id) {
        myProducts.removeIf(p -> p.getId().equals(id));

    }
}
