package product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import product.model.Product;

@Repository
public interface DataRepository extends MongoRepository<Product, String> {

}
