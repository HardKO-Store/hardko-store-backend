package pe.edu.upc.hardko.store.products.interfaces.rest.transform;

import pe.edu.upc.hardko.store.products.domain.model.commands.UpdateProductCommand;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(String productId, UpdateProductResource resource) {
        return new UpdateProductCommand(
                productId,
                resource.name(),
                resource.description(),
                resource.price(),
                resource.category(),
                resource.brand(),
                resource.stock(),
                resource.image(),
                resource.size(),
                resource.color()
        );
    }
}
