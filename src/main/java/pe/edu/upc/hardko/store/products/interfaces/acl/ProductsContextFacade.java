package pe.edu.upc.hardko.store.products.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.domain.model.aggregates.Product;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductsByTheirIdsQuery;
import pe.edu.upc.hardko.store.products.domain.services.ProductCommandService;
import pe.edu.upc.hardko.store.products.domain.services.ProductQueryService;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.ProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.ProductResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsContextFacade {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsContextFacade(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    public boolean existsProductById(String productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);

        var optionalProduct = this.productQueryService.handle(getProductByIdQuery);

        return optionalProduct.isPresent();
    }

    public Optional<ProductResource> fetchProductById(String productId) {
        var getProductByIdQuery = new GetProductByIdQuery(productId);
        var optionalProduct = this.productQueryService.handle(getProductByIdQuery);

        if (optionalProduct.isEmpty()) {
            return Optional.empty();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(optionalProduct.get());

        return Optional.of(productResource);
    }

    public List<ProductResource> fetchProductsByTheirIds(List<String> productsIds){
        var getProductsByTheirIdsQuery = new GetProductsByTheirIdsQuery(productsIds);
        var products = this.productQueryService.handle(getProductsByTheirIdsQuery);

        var productResources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return productResources;
    }



}
