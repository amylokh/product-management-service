package product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<Product> allProducts = dataRepository.findAll();
        for (Product allProduct : allProducts) {
            Map<String, Float> exchangedRates = getExchangedRates(allProduct.getPrice());
            allProduct.setExchangedPrices(exchangedRates);
        }
        return allProducts;
    }

    public Product getProduct(String id) {
        Product product = dataRepository.findById(id);
        Map<String, Float> exchangedRates = getExchangedRates(product.getPrice());
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

    private Map<String, Float> getExchangedRates(Float productPrice) {
        Map<String, Float> exchangeRates = iPriceService.retrieveCurrentExchangeRates().getRates();
        for (Map.Entry<String, Float> entry : exchangeRates.entrySet()) {
            entry.setValue(entry.getValue() * productPrice);
        }
        return exchangeRates;
    }

}
