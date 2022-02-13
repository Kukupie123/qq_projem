package kukukode.progem.authmicroservice.jpaRepo;

import kukukode.progem.authmicroservice.jpaEntity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
