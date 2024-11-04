package pe.edu.upc.hardko.store.products.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllCategories;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllProductsQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductsByCategory;
import pe.edu.upc.hardko.store.products.domain.services.ProductQueryService;
import pe.edu.upc.hardko.store.products.infrastructure.persistence.mongo.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetAllProductsQuery query) {
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> handle(GetProductsByCategory query) {
        return List.of();
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return this.productRepository.findById(query.productId());
    }

    @Override
    public List<String> handle(GetAllCategories query) {
        return List.of();
    }
}
