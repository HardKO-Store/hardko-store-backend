package pe.edu.upc.hardko.store.orders.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findOrdersByUserId(String userId);

    //TODO: verify if this is the correct way to get the orders by product id
    List<Order> findOrdersByItemsContains(String productId);
}
