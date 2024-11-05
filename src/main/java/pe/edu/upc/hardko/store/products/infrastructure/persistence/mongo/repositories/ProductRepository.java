package pe.edu.upc.hardko.store.products.infrastructure.persistence.mongo.repositories;


import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    boolean existsById(String productId);
    List<Product> findByCategoriesContaining(String category);

    @Aggregation(pipeline = {
            "{ $unwind: '$categories' }",
            "{ $group: { _id: '$categories' } }"
    })
    List<String> findDistinctCategories();
}
