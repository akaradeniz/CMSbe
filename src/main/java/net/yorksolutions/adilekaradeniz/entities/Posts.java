package net.yorksolutions.adilekaradeniz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    public String body;
    @Temporal(TemporalType.DATE)
    private Date createdDate= new Date();
    @Temporal(TemporalType.DATE)
    private Date updatedDate= new Date();
 @ManyToOne
     UserCMS author;

   @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
   private Set<Commend> commend= new HashSet<>();

    public Posts() {
    }

    public Posts(String title, String body, Date createdDate, Date updatedDate, UserCMS author, Set<Commend> commend) {
        this.title = title;
        this.body = body;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.author = author;
        this.commend = commend;
    }

    public Long getId() {
        return id;
    }

//    public void setAuthor(String author) {
//        this.author = author;
//    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        if(updatedDate.equals(createdDate)) {
            updatedDate=null;
        }
        this.updatedDate = updatedDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCMS getAuthor() {
        return author;
    }

    public void setAuthor(UserCMS author) {
        this.author = author;
    }

    public Set<Commend> getCommend() {
        return commend;
    }

    public void setCommend(Set<Commend> commend) {
        this.commend = commend;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                ", commend=" + commend +
                '}';
    }
}
