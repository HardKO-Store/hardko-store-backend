package pe.edu.upc.hardko.store.products.application.internal.commandservices;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.commands.CreateProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.commands.DeleteProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.commands.UpdateProductCommand;
import pe.edu.upc.hardko.store.products.domain.services.ProductCommandService;
import pe.edu.upc.hardko.store.products.infrastructure.persistence.mongo.repositories.ProductRepository;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String handle(CreateProductCommand command) {

        var product = new Product(command);

        try {
            this.productRepository.save(product);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error while saving profile: " + e.getMessage());
        }

        return product.getId();
    }

    @Override
    public Boolean handle(DeleteProductCommand command) {

        if(!this.productRepository.existsById(command.productId())){
            throw new RuntimeException("Product not found");
        }

        this.productRepository.deleteById(command.productId());

        return !this.productRepository.existsById(command.productId());
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {
        return Optional.empty();
    }
}
