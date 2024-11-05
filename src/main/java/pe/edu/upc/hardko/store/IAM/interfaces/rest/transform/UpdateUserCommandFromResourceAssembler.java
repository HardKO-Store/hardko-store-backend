package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.commands.UpdateUserCommand;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource, String userId) {
        return new UpdateUserCommand(
                userId,
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.password(),
                resource.country(),
                resource.city(),
                resource.street(),
                resource.zip(),
                resource.newFavoriteProductId()
        );
    }
}
