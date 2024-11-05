package pe.edu.upc.hardko.store.IAM.domain.model.commands;

public record LoginUserCommand(
        String email,
        String password
) {
}
