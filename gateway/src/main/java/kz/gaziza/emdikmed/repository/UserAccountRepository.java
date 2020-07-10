package kz.gaziza.emdikmed.repository;

import kz.gaziza.emdikmed.model.State;
import kz.gaziza.emdikmed.model.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
    @Query("{username: '?0', state: '?1'}")
    Optional<UserAccount> findByUsername(String username, State state);

    @Query("{username: '?0', state: '?1'}")
    UserAccount getByUsername(String username, State state);

    @Query("{username: '?0', state: '?1'}")
    Boolean existsByUsername(String username, State state);

    @Query("{email: '?0', state: '?1'}")
    Boolean existsByEmail(String email, State state);

}
