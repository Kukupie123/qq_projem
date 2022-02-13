package kukukode.progem.authmicroservice.jpaEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email")
    public String email;

    @Column(name = "cred")
    public String hashedPassword;

    public User() {

    }

    public User(String email, String password) {
        this.email = email;
        this.hashedPassword = password;
    }

}
