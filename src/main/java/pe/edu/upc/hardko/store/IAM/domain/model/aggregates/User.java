package pe.edu.upc.hardko.store.IAM.domain.model.aggregates;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.lang.NonNull;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.entities.UserAddress;
import pe.edu.upc.hardko.store.shared.domain.model.entities.AuditableModel;

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
    private String email;

    @NonNull
    private String password;

    @NonNull
    private UserAddress address;

    //TODO: implement user favorite products

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
    }



}
