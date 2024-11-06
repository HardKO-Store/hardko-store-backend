package pe.edu.upc.hardko.store.products.domain.services;

import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
    List<Product> handle(GetProductsByCategoryQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
    List<String> handle(GetAllCategoriesQuery query);
    List<Product> handle(GetProductsByTheirIdsQuery query);
}
