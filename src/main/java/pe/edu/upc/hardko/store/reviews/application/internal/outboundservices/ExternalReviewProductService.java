package pe.edu.upc.hardko.store.reviews.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;

@Service
public class ExternalReviewProductService {
    private final ProductsContextFacade productsContextFacade;

    public ExternalReviewProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public boolean existsByProductId(String productId) {
        return this.productsContextFacade.existsProductById(productId);
    }

}
