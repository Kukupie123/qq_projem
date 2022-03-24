package com.kukode.progem.gateway_mc_v3.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
//NEEDED FOR USING ANNOTATIONS LIKE GENERATED VALUE
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//     <dependency>
//             <groupId>jakarta.persistence</groupId>
//             <artifactId>jakarta.persistence-api</artifactId>
//             <version>2.2.3</version>
//     </dependency>
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("projects")//Table from Springframework NOT javax
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String description;
    String ancestry;
    Date timestamp;
    boolean iscomplete;
    int rulesid;
    String userid;
}
