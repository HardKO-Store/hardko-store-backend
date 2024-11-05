package pe.edu.upc.hardko.store.products.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.services.ProductCommandService;
import pe.edu.upc.hardko.store.products.domain.services.ProductQueryService;

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



}
