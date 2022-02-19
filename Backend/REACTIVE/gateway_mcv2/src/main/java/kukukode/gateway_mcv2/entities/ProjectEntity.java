package kukukode.gateway_mcv2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("projects")
public class ProjectEntity {
    @Id
    int id;
    String title;
    String description;
    String ancestry;
    Date timestamp;
    boolean iscomplete;
    int rulesid;
    String userid;
}
