package kukukode.progem.authmicroservice.services;

import kukukode.progem.authmicroservice.jpaEntity.User;
import kukukode.progem.authmicroservice.models.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    final
    UserServiceImp userServiceImp;

    public MyUserDetailsService(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("LOAD USER BY NAME");
        Optional<User> user = userServiceImp.getUser(email);
        if (user.isPresent()) {
            MyUserDetails userDetails = new MyUserDetails();
            userDetails.setEmail(user.get().email);
            userDetails.setPassword(user.get().hashedPassword);
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User has not signed up");
        }
    }
}
