package pe.edu.upc.hardko.store.products.interfaces.rest.transform;

import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity){
        return new ProductResource(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getCategories(),
                entity.getPrice(),
                entity.getBrand(),
                entity.getStock(),
                entity.getImageurl(),
                ProductOptionsResourceFromEntityAssembler.toResourceFromEntity(entity.getOptions())
        );
    }
}
