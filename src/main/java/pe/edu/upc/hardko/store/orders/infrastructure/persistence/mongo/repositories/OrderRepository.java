package pe.edu.upc.hardko.store.orders.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrdersByUserId(String userId);

    @Query("{ 'items.productId': ?0 }")
    List<Order> findOrdersByProductId(String productId);
}
