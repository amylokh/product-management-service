package product.service;

import product.model.Product;

import java.util.List;

public interface IProductService {

    public List<Product> getAllProducts();

    public Product getProduct(String id);

    public Product addProduct(Product product);

    public Product updateProduct(String id, Product product);

    public void deleteProduct(String id);
}
