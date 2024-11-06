package pe.edu.upc.hardko.store.IAM.domain.model.aggregates;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.NonNull;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.entities.UserAddress;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

import java.util.List;

@Getter
@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditableModel {

    @NonNull
    @Field("first_name")
    private String firstName;

    @NonNull
    @Field("last_name")
    private String lastName;

    @NonNull
    @Email
    private String email;

    @NonNull
    private String password;

    @NonNull
    private UserAddress address;


    @NonNull
    private List<String> favoriteProducts;

    //TODO: implement order history


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
