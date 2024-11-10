package pe.edu.upc.hardko.store.orders.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.orders.application.internal.outboundservices.ExternalOrderProductService;
import pe.edu.upc.hardko.store.orders.application.internal.outboundservices.ExternalOrderUserService;
import pe.edu.upc.hardko.store.orders.domain.model.aggregates.Order;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrderByIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByProductIdQuery;
import pe.edu.upc.hardko.store.orders.domain.model.queries.GetOrdersByUserIdQuery;
import pe.edu.upc.hardko.store.orders.domain.services.OrderQueryService;
import pe.edu.upc.hardko.store.orders.infrastructure.persistence.mongo.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderQueryServiceImpl implements OrderQueryService {

    private final OrderRepository orderRepository;
    private final ExternalOrderUserService externalUserService;
    private final ExternalOrderProductService externalProductService;

    public OrderQueryServiceImpl(OrderRepository orderRepository,
                                 ExternalOrderUserService externalUserService,
                                 ExternalOrderProductService externalProductService) {
        this.orderRepository = orderRepository;
        this.externalUserService = externalUserService;
        this.externalProductService = externalProductService;
    }

    @Override
    public Optional<Order> handle(GetOrderByIdQuery query) {
        return this.orderRepository.findById(query.orderId());
    }

    @Override
    public List<Order> handle(GetOrdersByProductIdQuery query) {

        if(!this.externalProductService.existsProductById(query.productId())) {
            throw new IllegalArgumentException("Product not found");
        }

        return this.orderRepository.findOrdersByItemsContains(query.productId());
    }

    @Override
    public List<Order> handle(GetOrdersByUserIdQuery query) {

        if(!this.externalUserService.existsUserById(query.userId())) {
            throw new IllegalArgumentException("User not found");
        }

        return this.orderRepository.findOrdersByUserId(query.userId());

    }
}
