package pe.edu.upc.hardko.store.IAM.interfaces.rest.transform;

import pe.edu.upc.hardko.store.IAM.domain.model.commands.LoginUserCommand;
import pe.edu.upc.hardko.store.IAM.interfaces.rest.resoruces.LoginUserResource;

public class LoginUserCommandFromResourceAssembler {
    public static LoginUserCommand toCommandFromResource(LoginUserResource resource){
        return new LoginUserCommand(resource.email(), resource.password());
    }
}
