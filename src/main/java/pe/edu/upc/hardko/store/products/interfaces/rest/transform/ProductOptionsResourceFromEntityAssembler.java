package pe.edu.upc.hardko.store.products.interfaces.rest.transform;

import pe.edu.upc.hardko.store.products.domain.model.entities.ProductOptions;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.ProductOptionsResource;


public class ProductOptionsResourceFromEntityAssembler {
    public static ProductOptionsResource toResourceFromEntity(ProductOptions entity) {
        return new ProductOptionsResource(entity.getSizes(), entity.getColors());
    }
}
