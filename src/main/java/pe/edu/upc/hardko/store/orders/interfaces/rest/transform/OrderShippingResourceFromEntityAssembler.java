package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.entities.OrderShipping;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.OrderShippingResource;

public class OrderShippingResourceFromEntityAssembler {
    public static OrderShippingResource toResourceFromEntity(OrderShipping entity){
        return new OrderShippingResource(
                DeliveryAddressResourceFromEntityAssembler.toResorceFromEntity(entity.getDeliveryAddress()),
                entity.getTrackingNumber()
        );
    }
}
