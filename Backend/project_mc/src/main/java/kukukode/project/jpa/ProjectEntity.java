package kukukode.project.jpa;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "projects")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String desc;
    @Column(name = "ancestry")
    private String ancestry;
    @Column(name = "timestamp")
    private Date createdAt;
    @Column(name = "iscomplete")
    private boolean isComplete;
    @Column(name = "rulesid")
    private int rulesID;
    @Column(name = "userid")
    private String userID;
}
