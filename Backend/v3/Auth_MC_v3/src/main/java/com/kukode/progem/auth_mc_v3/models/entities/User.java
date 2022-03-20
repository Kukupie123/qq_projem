package com.kukode.progem.auth_mc_v3.models.entities;


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
@Table("users") //TODO: Make table name dynamically placed instead of it being hardcoded
public class User implements Persistable<String> {

    @Transient
    private boolean isNew = false;

    //TODO: Make the column name for ID and password be dynamic.

    @Id
    private String email;
    private String cred;


    @Override
    public String getId() {
        return this.email;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
