package com.kukode.progem.member_mc.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

@Table(name = "members")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    public Member(boolean isNull) {
        if (isNull) {
            this.id = null;
            this.members = null;
        }
    }

    public boolean isValid() {
        if (id == null || members == null) return false;
        if (members.trim().isEmpty() || id.trim().isEmpty()) return false;
        return true;
    }


    @Id
    private String id; //ID format is projectID_membertype eg 213_leader
    String members;

}
