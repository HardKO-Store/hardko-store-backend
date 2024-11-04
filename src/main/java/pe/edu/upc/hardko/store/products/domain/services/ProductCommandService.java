package pe.edu.upc.hardko.store.products.domain.services;

import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.commands.DeleteProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.commands.UpdateProductCommand;

import java.util.Optional;

public interface ProductCommandService {
    Long handle(CreateProductCommand command);
    Boolean handle(DeleteProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);

}
