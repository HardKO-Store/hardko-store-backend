package pe.edu.upc.hardko.store.IAM.domain.services;

import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.DeleteUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    void handle(DeleteUserCommand command);
}
