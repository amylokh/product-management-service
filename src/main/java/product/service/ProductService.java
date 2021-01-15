package product.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.model.Price;
import product.model.Product;
import product.repository.DataRepository;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    @Autowired
    IPriceService iPriceService;

    @Autowired
    DataRepository dataRepository;

    public List<Product> getAllProducts() {
        return dataRepository.findAll();
    }

    public Product getProduct(String id) {
        Price prices = iPriceService.retrieveCurrentExchangeRates();
        Product product = dataRepository.findById(id);
        Map<String, Float> currentExchangeRates = prices.getRates();
        for (Map.Entry<String, Float> entry : currentExchangeRates.entrySet()) {
            entry.setValue(entry.getValue() * product.getPrice());
        }
        product.setExchangedPrices(currentExchangeRates);
        return product;
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
