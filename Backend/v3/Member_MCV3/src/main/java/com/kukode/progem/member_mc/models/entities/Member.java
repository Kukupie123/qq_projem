package com.kukode.progem.member_mc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("members")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member implements Persistable<String> {
    @Transient
    private boolean isNew;

    public Member(boolean isNull) {
        if (isNull) {
            this.id = null;
            this.members = null;
            isNew = true;
        }
    }


    public boolean isValid() {
        return !(id == null || id.trim().isEmpty());
    }


    @Id
    private String id; //ID format is projectID_membertype eg 213_leader
    String members;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
