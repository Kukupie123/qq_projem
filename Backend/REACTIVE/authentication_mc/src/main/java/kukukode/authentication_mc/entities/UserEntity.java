package kukukode.authentication_mc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;



@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("users")
public class UserEntity implements Persistable<String> {
    @Id
    private String email;
    private String cred;

    @Transient //Import from Springframework.data.annotation NOT javax.persistence
    //@Transient means not part of database
    private boolean isNew = false;//This is so that we can add new record by using this isNew variable to determine if we want to update a record or update


    @Override
    public String getId() {
        return this.email;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
