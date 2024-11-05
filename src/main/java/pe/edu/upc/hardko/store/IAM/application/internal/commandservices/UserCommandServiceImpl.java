package pe.edu.upc.hardko.store.IAM.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.application.internal.outboundservices.acl.ExternalProductService;
import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.LoginUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.hardko.store.IAM.domain.services.UserCommandService;
import pe.edu.upc.hardko.store.IAM.infrastructure.persistence.mongo.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final ExternalProductService externalProductService;

    public UserCommandServiceImpl(UserRepository userRepository, ExternalProductService externalProductService) {
        this.userRepository = userRepository;
        this.externalProductService = externalProductService;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {

        if (this.userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        }

        var user = new User(command);

        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving user: " + e.getMessage());
        }

        return Optional.of(user);
    }

    @Override
    public void handle(DeleteUserCommand command) {
        if (!this.userRepository.existsById(command.userId())) {
            throw new IllegalArgumentException("User with id " + command.userId() + " does not exist");
        }

        try {
            this.userRepository.deleteById(command.userId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting user: " + e.getMessage());
        }
    }

    @Override
    public Optional<User> handle(LoginUserCommand command) {
        if (!this.userRepository.existsByEmailAndPassword(command.email(),command.password())) {
            throw new IllegalArgumentException("Login failed");
        }

        return this.userRepository.findByEmailAndPassword(command.email(),command.password());
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {

        if (!this.userRepository.existsById(command.userId())) {
            throw new IllegalArgumentException("User with id " + command.userId() + " does not exist");
        }

        var userToUpdate = this.userRepository.findById(command.userId()).get();

        var userFavoriteProductsIds = userToUpdate.getFavoriteProducts();

        if (!userFavoriteProductsIds.contains(command.newFavoriteProductId()) && this.externalProductService.existsProductById(command.newFavoriteProductId())) {
            userFavoriteProductsIds.add(command.newFavoriteProductId());
        }

        var updatedUser = userToUpdate.updateInformation(
                command.firstName(),
                command.lastName(),
                command.email(),
                command.password(),
                command.country(),
                command.city(),
                command.street(),
                command.zip(),
                userFavoriteProductsIds
        );

        try {
            var savedUser = this.userRepository.save(updatedUser);
            return Optional.of(savedUser);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Error while updating user: " + e.getMessage());
        }

    }
}
