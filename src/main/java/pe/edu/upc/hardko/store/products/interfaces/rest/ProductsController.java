package pe.edu.upc.hardko.store.products.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.products.domain.model.commands.DeleteProductCommand;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllCategoriesQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetAllProductsQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductByIdQuery;
import pe.edu.upc.hardko.store.products.domain.model.queries.GetProductsByCategoryQuery;
import pe.edu.upc.hardko.store.products.domain.services.ProductCommandService;
import pe.edu.upc.hardko.store.products.domain.services.ProductQueryService;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.CreateProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.ProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.SimplifiedProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.resources.UpdateProductResource;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.SimplifiedProductResourceFromEntityAssembler;
import pe.edu.upc.hardko.store.products.interfaces.rest.transform.UpdateProductCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Products Management Endpoints")
public class ProductsController {

    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;

    public ProductsController(ProductQueryService productQueryService, ProductCommandService productCommandService){
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
    }

    @GetMapping
    @Operation(summary = "Get all products" , description = "Get all the existing products in the database")
    public ResponseEntity<List<SimplifiedProductResource>> getAllProducts(){
        var getAllProductsQuery = new GetAllProductsQuery();

        var products = this.productQueryService.handle(getAllProductsQuery);

        var productsResources = products.stream()
                .map(SimplifiedProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsResources);
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get product by id" , description = "Get the product with the given id")
    public ResponseEntity<ProductResource> getProductById(@PathVariable String productId){
        var getProductByIdQuery = new GetProductByIdQuery(productId);

        var product = this.productQueryService.handle(getProductByIdQuery);

        if (product.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(product.get());

        return ResponseEntity.ok(productResource);
    }


    @GetMapping("/categories")
    @Operation(summary = "Get all categories" , description = "Get all the existing categories in the database")
    public ResponseEntity<List<String>> getAllCategories(){
        var getAllCategoriesQuery = new GetAllCategoriesQuery();

        var categories = this.productQueryService.handle(getAllCategoriesQuery);


        return ResponseEntity.ok(categories);
    }


    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category" , description = "Get all the products with the given category")
    public ResponseEntity<List<SimplifiedProductResource>> getProductsByCategory(@PathVariable String category){
        var getProductsByCategory = new GetProductsByCategoryQuery(category);

        var products = this.productQueryService.handle(getProductsByCategory);

        var productsResources = products.stream()
                .map(SimplifiedProductResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(productsResources);
    }


    //TODO: refactor post method and command service
    @PostMapping
    @Operation(summary = "Create product" , description = "Create a new product")
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


    @PutMapping("/{productId}")
    @Operation(summary = "Update product" , description = "Update the product with the given id")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable String productId, @RequestBody UpdateProductResource resource){
        var updateProductCommand = UpdateProductCommandFromResourceAssembler.toCommandFromResource(productId, resource);


        var optionalProduct = this.productCommandService.handle(updateProductCommand);
        if (optionalProduct.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var productResource = ProductResourceFromEntityAssembler.toResourceFromEntity(optionalProduct.get());

        return ResponseEntity.ok(productResource);
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete product" , description = "Delete the product with the given id")
    public ResponseEntity<?> deleteProductById(@PathVariable String productId){
        var deleteProductCommand = new DeleteProductCommand(productId);

        var isDeleted = this.productCommandService.handle(deleteProductCommand);

        if (!isDeleted){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok("The product with the given id has been deleted");
    }

}
