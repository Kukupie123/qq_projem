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
                        BaseResponse<Boolean> resp = new BaseResponseImp<>(true, "");
                        return Mono.just(ResponseEntity.ok().body(resp));
                    }
                    //Wrong credentials
                    System.out.println("Wrong cred");
                    BaseResponse<Boolean> resp = new BaseResponseImp<>(false, "Wrong credentials");
                    return Mono.just(ResponseEntity.badRequest().body(resp));
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
        System.out.println(user.toString());

        if (user.getEmail() == null || user.getCred() == null) {
            {
                BaseResponse<Boolean> resp = new BaseResponseImp<>(false, "Invalid body");
                return Mono.just(ResponseEntity.badRequest().body(resp));
            }
        }
        try {
            //Saving data needs to be done this way

            //Save function is ALWAYS going to update if we provide ID value by default
            //To override this we implemented Persistable<ID> to our user entity
            //This allows us to write our own logic to determine if the object is supposed to update or insert into the db
            //In our case we simply set the isNew variable of UserEntity Class to true and it is going to be used for the
            //Save function to determine if it needs to be updated or saved

            //Set isNew value to true
            user.setNew(true);
            //Save it, since it is a mono. The further logic needs to be done inside the lambda
            return repo.save(user)
                    .flatMap(userEntity -> {
                                BaseResponse<Boolean> resp = new BaseResponseImp<>(true, "Added record");
                                return Mono.just(ResponseEntity.ok(resp));
                            }
                    )
                    .defaultIfEmpty((ResponseEntity.internalServerError().body(new BaseResponseImp<>(false, "Something went wrong"))));


        } catch (Exception e) {
            BaseResponse<Boolean> resp = new BaseResponseImp<>(false, e.getMessage());
            return Mono.just(ResponseEntity.internalServerError().body(resp));
        }

    }


}
