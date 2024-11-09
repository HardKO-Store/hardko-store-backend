package pe.edu.upc.hardko.store.reviews.application.internal.outboundservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.interfaces.acl.UserContextFacade;

@Service
public class ExternalReviewUserService {

    private final UserContextFacade userContextFacade;

    public ExternalReviewUserService(UserContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public boolean existsByUserId(String userId){
        return this.userContextFacade.existsUserById(userId);
    }

}
