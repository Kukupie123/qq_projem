package kukukode.progem.projectleadermc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table("projectmembers")
public class MemberEntity {
    @Id
    int id;
    int projectid;
    //Each members are separated by ",./"
    String members;
    //Either leaders or members
    String membertype;


}
