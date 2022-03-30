package com.kukode.progem.project_mc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;
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
    Timestamp timestamp;
    boolean iscomplete;
    int rulesid;
    String userid;
}
