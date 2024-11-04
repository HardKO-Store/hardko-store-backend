package pe.edu.upc.hardko.store.products.interfaces.rest.transform;

import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource){
        return new CreateProductCommand(
                resource.name(),
                resource.description(),
                resource.category(),
                resource.price(),
                resource.brand(),
                resource.stock(),
                resource.image(),
                resource.size(),
                resource.color()
        );
    }
}
