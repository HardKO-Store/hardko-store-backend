package pe.edu.upc.hardko.store.IAM.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;

@Service
public class ExternalProductService {

    private final ProductsContextFacade productsContextFacade;

    public ExternalProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public boolean existsProductById(String productId) {
        return this.productsContextFacade.existsProductById(productId);
    }

}
