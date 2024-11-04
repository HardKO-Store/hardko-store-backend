package pe.edu.upc.hardko.store.products.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;

public interface ProductRepository extends MongoRepository<Product, Long> {
}
