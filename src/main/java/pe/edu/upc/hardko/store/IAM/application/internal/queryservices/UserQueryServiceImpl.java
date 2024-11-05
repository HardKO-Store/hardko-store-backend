package pe.edu.upc.hardko.store.IAM.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.hardko.store.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.hardko.store.IAM.domain.services.UserQueryService;
import pe.edu.upc.hardko.store.IAM.infrastructure.persistence.mongo.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return this.userRepository.findById(query.userId());
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return this.userRepository.findAll();
    }
}
