package pe.edu.upc.hardko.store.reviews.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;

@Service
public class ExternalProductService {
    private final ProductsContextFacade productsContextFacade;

    public ExternalProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public boolean existsByProductId(String productId) {
        return this.productsContextFacade.existsProductById(productId);
    }

}
