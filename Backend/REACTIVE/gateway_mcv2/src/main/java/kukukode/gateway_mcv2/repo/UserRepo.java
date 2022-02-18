package kukukode.gateway_mcv2.repo;

import kukukode.gateway_mcv2.entities.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends ReactiveCrudRepository<UserEntity, String> {
}