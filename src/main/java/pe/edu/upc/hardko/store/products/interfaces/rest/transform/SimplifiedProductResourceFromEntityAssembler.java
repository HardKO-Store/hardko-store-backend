package pe.edu.upc.hardko.store.products.interfaces.rest.transform;

import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.SimplifiedProductResource;

public class SimplifiedProductResourceFromEntityAssembler {
    public static SimplifiedProductResource toResourceFromEntity(Product entity){
        return new SimplifiedProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice()
        );
    }
}
