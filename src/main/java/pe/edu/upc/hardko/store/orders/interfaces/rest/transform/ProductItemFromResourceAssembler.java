package pe.edu.upc.hardko.store.orders.interfaces.rest.transform;

import pe.edu.upc.hardko.store.orders.domain.model.valueobjects.ProductItem;
import pe.edu.upc.hardko.store.orders.interfaces.rest.resources.ProductItemResource;

public class ProductItemFromResourceAssembler {
    public static ProductItem toCommandFromResource(ProductItemResource resource){
        return new ProductItem(
                resource.productId(),
                resource.quantity()
        );
    }
}
