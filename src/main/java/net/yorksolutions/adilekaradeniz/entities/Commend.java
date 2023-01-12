package net.yorksolutions.adilekaradeniz.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Commend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String body;

    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();
private int index;
@ManyToOne()
    UserCMS follower;

@ManyToOne()
public Posts post;


    public Commend() {
    }

    public Commend(String body, Date createdDate, int index, UserCMS follower,Posts post) {
        this.body = body;
        this.createdDate = createdDate;
        this.index = index;
        this.follower = follower;
        this.post=post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        index+=1;
        this.index = index;
    }

    public UserCMS getFollower() {
        return follower;
    }

    public void setFollower(UserCMS follower) {
        this.follower = follower;
    }

    public Posts getPost() {
        return post;
    }

    public void setPost(Posts post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Commend{" +
                "body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", index=" + index +
                ", follower=" + follower +
                ", post=" + post +
                '}';
    }
}
