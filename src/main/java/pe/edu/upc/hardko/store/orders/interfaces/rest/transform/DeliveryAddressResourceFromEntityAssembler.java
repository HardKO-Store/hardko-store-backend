package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.DeliveryAddress;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.DeliveryAddressResource;

public class DeliveryAddressResourceFromEntityAssembler {
    public static DeliveryAddressResource toResorceFromEntity(DeliveryAddress entity){
        return new DeliveryAddressResource(
                entity.country(),
                entity.city(),
                entity.street(),
                entity.zipCode()
        );
    }
}
