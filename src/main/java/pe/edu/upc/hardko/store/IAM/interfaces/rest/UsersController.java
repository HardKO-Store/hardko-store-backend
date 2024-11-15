package pe.edu.upc.hardko.store.IAM.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.IAM.application.internal.outboundservices.acl.ExternalProductService;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserFavoriteProductsQuery;
import pe.edu.upc.hardko.store.IAM.domain.services.UserCommandService;
import pe.edu.upc.hardko.store.IAM.domain.services.UserQueryService;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.*;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.LoginUserCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Management Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;
    private final ExternalProductService externalProductService;

    public UsersController(UserCommandService userCommandService,
                           UserQueryService userQueryService,
                           ExternalProductService externalProductService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
        this.externalProductService = externalProductService;
    }


    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Get a user by id")
    @ApiResponse(responseCode = "200", description = "User found")
    public ResponseEntity<UserResource> getUserById(@PathVariable String userId){
        var getUserByIdQuery = new GetUserByIdQuery(userId);

        var user = this.userQueryService.handle(getUserByIdQuery);

        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());

        return ResponseEntity.ok(userResource);
    }


    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Login of a user for the store")
    @ApiResponse(responseCode = "201", description = "User logged in")
    public ResponseEntity<UserResource> loginUser(@RequestBody LoginUserResource resource){
        var loginUserCommand = LoginUserCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var user = this.userCommandService.handle(loginUserCommand);

        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());

        return ResponseEntity.ok(userResource);
    }


    @GetMapping
    @Operation(summary = "Get all users", description = "Get all users registered")
    @ApiResponse(responseCode = "200", description = "Users found")
    public ResponseEntity<List<UserResource>> getAllUsers(){
        var getAllUsersQuery = new GetAllUsersQuery();

        var users = this.userQueryService.handle(getAllUsersQuery);

        if (users.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userResources);

    }

    @PutMapping("/{userId}")
    @Operation(summary = "Update user", description = "Update a user by its id")
    @ApiResponse(responseCode = "201", description = "User updated")
    public ResponseEntity<UserResource> updateUser(@RequestBody UpdateUserResource resource, @PathVariable String userId){
        var updateUserCommand = UpdateUserCommandFromResourceAssembler
                .toCommandFromResource(resource, userId);

        var user = this.userCommandService.handle(updateUserCommand);

        if (user.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }


    @GetMapping("/{userId}/favorites")
    @Operation(summary = "Get user favorite products", description = "Get the favorite products of a user with the list of ids a user has")
    @ApiResponse(responseCode = "200", description = "Favorite products found")
    public ResponseEntity<List<FavoriteProductResource>> getUserFavoriteProducts(@PathVariable String userId){
        var getUserFavoriteProductsQuery = new GetUserFavoriteProductsQuery(userId);
        var FavoriteProductsIds = this.userQueryService.handle(getUserFavoriteProductsQuery);
        if (FavoriteProductsIds.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var favoriteProductsResources = this.externalProductService.fetchProductsByTheirIds(FavoriteProductsIds);

        return ResponseEntity.ok(favoriteProductsResources);
    }


    @PostMapping
    @Operation(summary = "Create user", description = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource){
        var createUserCommand = CreateUserCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var user = this.userCommandService.handle(createUserCommand);

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());

        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }


    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Delete a user by id")
    @ApiResponse(responseCode = "200", description = "User deleted")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){

        var deleteUserCommand = new DeleteUserCommand(userId);

        this.userCommandService.handle(deleteUserCommand);

        return ResponseEntity.ok("the user with the given id was deleted");
    }

}
