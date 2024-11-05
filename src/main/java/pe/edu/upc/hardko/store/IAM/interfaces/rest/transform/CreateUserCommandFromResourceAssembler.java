package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.commands.CreateUserCommand;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.password(),
                resource.country(),
                resource.city(),
                resource.street(),
                resource.zip()
        );
    }
}
