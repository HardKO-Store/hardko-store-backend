package pe.edu.upc.hardko.store.IAM.domain.model.aggregates;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.entities.UserAddress;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableModel {

    @NotNull
    @Field("first_name")
    private String firstName;

    @NotNull
    @Field("last_name")
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    //TODO: refactor user address into a value object
    @NotNull
    private UserAddress address;

    @NotNull
    @Field("favorite_products")
    private List<String> favoriteProducts;

    public User(CreateUserCommand command) {
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.email = command.email();
        this.password = command.password();
        this.address = new UserAddress(
                command.country(),
                command.city(),
                command.street(),
                command.zip()
        );
        this.favoriteProducts = List.of();
    }

    public User updateInformation(
            String firstName,
            String lastName,
            String email,
            String password,
            String country,
            String city,
            String street,
            String zip,
            List<String> favoriteProducts
    ){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = new UserAddress(
                country,
                city,
                street,
                zip
        );
        this.favoriteProducts = favoriteProducts;
        return this;
    }





}
