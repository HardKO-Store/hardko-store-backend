package pe.edu.upc.hardko.store.IAM.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.FavoriteProductResource;
import pe.edu.upc.hardko.store.products.interfaces.acl.ProductsContextFacade;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExternalProductService {

    private final ProductsContextFacade productsContextFacade;

    public ExternalProductService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public boolean existsProductById(String productId) {
        return this.productsContextFacade.existsProductById(productId);
    }

    public List<FavoriteProductResource> fetchProductsByTheirIds(List<String> productsIds){

        var productsResources = this.productsContextFacade.fetchProductsByTheirIds(productsIds);

        var favoriteProductResources =  productsResources.stream()
                .map(product -> new FavoriteProductResource(
                        product.id(),
                        product.name(),
                        product.description(),
                        product.categories(),
                        product.price(),
                        product.brand(),
                        product.imageurl())
                )
                .collect(Collectors.toList());

        return favoriteProductResources;
    }


}
