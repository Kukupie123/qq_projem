package kukukode.progem.project_mcv2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@Entity WE CAN'T ADD THIS WHEN USING R2DBC
@Table("projects")//Table from Springframework NOT javax
public class ProjectEntity {
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
