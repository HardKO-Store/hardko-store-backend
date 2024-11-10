package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.ProductItem;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.ProductItemResource;

public class ProductItemResourceFromEntityAssembler {
    public static ProductItemResource toResourceFromEntity(ProductItem entity){
        return new ProductItemResource(
                entity.productId(),
                entity.quantity()
        );
    }
}
