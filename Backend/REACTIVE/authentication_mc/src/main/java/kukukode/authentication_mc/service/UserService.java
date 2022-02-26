package kukukode.authentication_mc.service;

import kukukode.authentication_mc.entities.UserEntity;
import kukukode.authentication_mc.repo.UserRepo;
import kukukode.authentication_mc.response.BaseResponse;
import kukukode.authentication_mc.response.BaseResponseImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    final
    UserRepo repo;

    @Autowired
    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    /**
     * Returns JWT token on successful authentication
     *
     * @return True as data if successful, false if wrong cred or error
     */
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signIn(UserEntity user) {
        if (user.getEmail() == null || user.getCred() == null) {
            BaseResponse<Boolean> resp = new BaseResponseImp<>(false, "No information provided");
            System.out.println("Bad request");
            return Mono.just(ResponseEntity.badRequest().body(resp));
        }
        var monoUser = repo.findById(user.getEmail());
        //Return appropriate mono after validating password
        return monoUser.flatMap(userEntity -> {
                    if (user.getCred().equals(userEntity.getCred())) {
                        //Correct credentials
                        System.out.println("cc cred");
                        BaseResponse<Boolean> resp = new BaseResponseImp<>(true, "");
                        return Mono.just(ResponseEntity.ok().body(resp));
                    }
                    //Wrong credentials
                    System.out.println("Wrong cred");
                    BaseResponse<Boolean> resp = new BaseResponseImp<>(false, "");
                    return Mono.just(ResponseEntity.ok().body(resp));
                }
        )
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    /**
     * Adds the user record to the database
     *
     * @return true if successful, else false.
     */
    public Mono<ResponseEntity<BaseResponse<Boolean>>> signup(UserEntity user) {
        if (user.getEmail() == null || user.getCred() == null) {
            {
                BaseResponse<Boolean> resp = new BaseResponseImp<>(false, "");
                return Mono.just(ResponseEntity.badRequest().body(resp));
            }
        }
        repo.save(user);
        BaseResponse<Boolean> resp = new BaseResponseImp<>(true, "");
        return Mono.just(ResponseEntity.ok(resp));

    }


}
