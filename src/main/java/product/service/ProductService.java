package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.model.Product;
import product.repository.DataRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {

    @Autowired
    IPriceService iPriceService;

    @Autowired
    DataRepository dataRepository;

    public List<Product> getAllProducts() {
        Map<String, Float> exchangeRates = iPriceService.retrieveCurrentExchangeRates().getRates();
        List<Product> allProducts = dataRepository.findAll();
        for (Product allProduct : allProducts) {
            Map<String, Float> exchangedRates = computeExchangeRates(exchangeRates, allProduct.getPrice());
            allProduct.setExchangedPrices(exchangedRates);
        }
        return allProducts;
    }

    public Product getProduct(String id) {
        Map<String, Float> exchangeRates = iPriceService.retrieveCurrentExchangeRates().getRates();
        Product product = dataRepository.findById(id);
        Map<String, Float> exchangedRates = computeExchangeRates(exchangeRates, product.getPrice());
        product.setExchangedPrices(exchangedRates);
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

    private Map<String, Float> computeExchangeRates(Map<String, Float> exchangeRates, Float productPrice) {
        Map<String, Float> computedExchangeRates = new HashMap<>();
        for (Map.Entry<String, Float> entry : exchangeRates.entrySet()) {
            computedExchangeRates.put(entry.getKey(), entry.getValue() * productPrice);
        }
        return computedExchangeRates;
    }

}
