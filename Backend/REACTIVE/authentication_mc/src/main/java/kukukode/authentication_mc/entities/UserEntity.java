package kukukode.authentication_mc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("users")
public class UserEntity {
    @Id
    private String email;
    private String cred;
}
