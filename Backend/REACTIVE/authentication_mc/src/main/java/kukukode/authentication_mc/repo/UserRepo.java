package kukukode.authentication_mc.repo;

import kukukode.authentication_mc.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserEntity, String> {
}