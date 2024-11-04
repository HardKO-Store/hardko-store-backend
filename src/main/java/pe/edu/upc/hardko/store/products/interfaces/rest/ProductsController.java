package pe.edu.upc.hardko.store.products.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profile Management Endpoints")
public class ProductsController {


    //TODO: Get all products (with Limit results)

    //TODO: Get product by id

    //TODO: GetAllCategories

    //TODO: GetProductsByCategory

    //TODO: CreateProduct

    //TODO: UpdateProduct

    //TODO: DeleteProduct

}
