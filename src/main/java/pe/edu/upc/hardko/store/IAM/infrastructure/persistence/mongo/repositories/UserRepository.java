package pe.edu.upc.hardko.store.IAM.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.hardko.store.IAM.domain.model.aggregates.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<User> findByEmailAndPassword (String email, String password);
}
