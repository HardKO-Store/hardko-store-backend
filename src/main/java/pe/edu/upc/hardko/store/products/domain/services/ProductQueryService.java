package pe.edu.upc.hardko.store.products.domain.services;

import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllCategories;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllProductsQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductsByCategory;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetAllProductsQuery query);
    List<Product> handle(GetProductsByCategory query);
    Optional<Product> handle(GetProductByIdQuery query);
    List<String> handle(GetAllCategories query);
}
