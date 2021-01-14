package product.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import product.model.Product;
import java.util.List;

@Repository
public class DataRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Product> findAll(){
        return mongoTemplate.findAll(Product.class);
    }

    public Product findById(String id){
        return mongoTemplate.findById(id, Product.class);
    }

    public Product save(Product product) {
        Product data = mongoTemplate.findById(product.getId(), Product.class);
        if (data==null) return mongoTemplate.save(product);
        else return null;
    }

    public void deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, Product.class);
    }

    public Product updateProduct(String id, Product product){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.findAndReplace(query, product);
    }
}
