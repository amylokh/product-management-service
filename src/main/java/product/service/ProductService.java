package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.model.Product;
import product.repository.DataRepository;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    DataRepository dataRepository;

    public List<Product> getAllProducts() {
        return dataRepository.findAll();
    }

    public Product getProduct(String id) {
        return dataRepository.findById(id);
    }

    public Product addProduct(Product product) {
       return dataRepository.save(product);
    }

    public Product updateProduct(String id, Product product) {
        return dataRepository.updateProduct(id, product);
    }

    public void deleteProduct(String id) {
        dataRepository.deleteById(id);
    }
}
