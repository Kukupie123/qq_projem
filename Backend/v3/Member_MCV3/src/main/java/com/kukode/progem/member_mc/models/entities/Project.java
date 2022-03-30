package com.kukode.progem.member_mc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

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

    public Project(boolean isNull) {
        if (isNull) {
            id = -1;
        }
    }

    public boolean isValid() {
        if (id < 0) return false;
        return true;
    }
}
