package pe.edu.upc.hardko.store.orders.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;


@Service
public class ExternalOrderProductService {

    private final ProductsContextFacade productsContextFacade;


    public ExternalOrderProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public boolean existsProductById(String productId) {
        return this.productsContextFacade.existsProductById(productId);
    }



}
