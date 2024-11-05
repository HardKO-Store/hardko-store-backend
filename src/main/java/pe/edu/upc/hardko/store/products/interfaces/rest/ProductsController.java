package pe.edu.upc.hardko.store.products.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllProductsQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.services.ProductCommandService;
import pe.edu.upc.hardko.store.products.domain.services.ProductQueryService;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.CreateProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.SimplifiedProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.SimplifiedProductResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProductsController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductsController(ProductQueryService productQueryService, ProductCommandService productCommandService){
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    @GetMapping
    public ResponseEntity<List<SimplifiedProductResource>> getAllProducts(){
        var getAllProductsQuery = new GetAllProductsQuery();

        var products = this.productQueryService.handle(getAllProductsQuery);

        var productsResources = products.stream()
                .map(SimplifiedProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsResources);
    }

    //TODO: Get all products (with Limit results)

    @GetMapping("/{productId}")
    public ResponseEntity<SimplifiedProductResource> getProductById(@PathVariable String productId){
        var getProductByIdQuery = new GetProductByIdQuery(productId);

        var product = this.productQueryService.handle(getProductByIdQuery);

        if (product.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var productResource = SimplifiedProductResourceFromEntityAssembler.toResourceFromEntity(product.get());

        return ResponseEntity.ok(productResource);
    }

    //TODO: GetAllCategories

    //TODO: GetProductsByCategory

    @PostMapping
    public ResponseEntity<SimplifiedProductResource> createProduct(@RequestBody CreateProductResource resource){
        var createProductCommand = CreateProductCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var productId = this.productCommandService.handle(createProductCommand);

        if (productId.equals("")){
            return ResponseEntity.badRequest().build();
        }

        var getProductByIdQuery = new GetProductByIdQuery(productId);

        var product = this.productQueryService.handle(getProductByIdQuery);

        var productResource = SimplifiedProductResourceFromEntityAssembler.toResourceFromEntity(product.get());

        return new ResponseEntity<>(productResource, HttpStatus.CREATED);
    }

    //TODO: UpdateProduct

    //TODO: DeleteProduct

}
