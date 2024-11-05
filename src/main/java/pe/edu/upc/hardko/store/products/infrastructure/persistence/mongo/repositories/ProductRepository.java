package pe.edu.upc.hardko.store.products.infrastructure.persistence.mongo.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
