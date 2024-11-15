package pe.edu.upc.hardko.store.IAM.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.valueobjects.UserAddress;
import pe.edu.upc.hardko.store.IAM.domain.services.UserCommandService;
import pe.edu.upc.hardko.store.IAM.domain.services.UserQueryService;

import java.util.Optional;

@Service
public class UserContextFacade {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserContextFacade(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    public boolean existsUserById(String userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);

        var optionalUser = this.userQueryService.handle(getUserByIdQuery);

        return optionalUser.isPresent();
    }

    public Optional<UserAddress> GetUserAddressById(String userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);

        var optionalUser = this.userQueryService.handle(getUserByIdQuery);

        if (optionalUser.isEmpty()){
            return Optional.empty();
        }

        var userAddress = optionalUser.get().getAddress();

        return Optional.of(userAddress);
    }

}
