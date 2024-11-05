package pe.edu.upc.hardko.store.IAM.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.hardko.store.IAM.domain.services.UserCommandService;
import pe.edu.upc.hardko.store.IAM.domain.services.UserQueryService;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.CreateUserResource;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.LoginUserResource;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.UserResource;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.LoginUserCommandFromResourceAssembler;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Users Management Endpoints")
public class UserController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService){
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }


    @GetMapping("/{userId}")
    @Operation(summary = "Get user by id", description = "Get a user by id")
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
    public ResponseEntity<List<UserResource>> getAllusers(){
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

    //TODO: Add product to user favorites products

    //TODO: Get user favorites products


    @PostMapping
    @Operation(summary = "Create user", description = "Create a new user")
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource){
        var createUserCommand = CreateUserCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var user = this.userCommandService.handle(createUserCommand);

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());

        return ResponseEntity.ok(userResource);
    }


    @DeleteMapping("/{userId}")
    @Operation(summary = "Delete user", description = "Delete a user by id")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){

        var deleteUserCommand = new DeleteUserCommand(userId);

        this.userCommandService.handle(deleteUserCommand);

        return ResponseEntity.ok("the user with the given id was deleted");
    }

}
