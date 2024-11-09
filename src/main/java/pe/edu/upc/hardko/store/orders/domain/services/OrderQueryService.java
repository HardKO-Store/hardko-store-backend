package pe.edu.upc.hardko.store.orders.domain.services;

import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrderByIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByProductIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface OrderQueryService {
    Optional<Order> handle(GetOrderByIdQuery query);
    List<Order> handle(GetOrdersByProductIdQuery query);
    List<Order> handle(GetOrdersByUserIdQuery query);
}
