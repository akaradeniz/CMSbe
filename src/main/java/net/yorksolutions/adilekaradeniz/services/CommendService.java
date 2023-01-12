package net.yorksolutions.adilekaradeniz.services;

import net.yorksolutions.adilekaradeniz.entities.Commend;
import net.yorksolutions.adilekaradeniz.entities.Posts;
import net.yorksolutions.adilekaradeniz.entities.UserCMS;
import net.yorksolutions.adilekaradeniz.repositories.CommendRepository;
import net.yorksolutions.adilekaradeniz.repositories.PostRepository;
import net.yorksolutions.adilekaradeniz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

@Service
public class CommendService {
    private CommendRepository comRepo;
    private  UserRepository userRepo;
    private PostRepository postRepo;
@Autowired
    public CommendService(CommendRepository comRepo, UserRepository userRepo,
PostRepository postRepository) {

        this.comRepo = comRepo;
        this.postRepo=postRepository;
        this.userRepo=userRepo;
    }

    public Commend createCommend(Commend commend,String userId, String postId ){
//    Posts postId=this.postRepo.findById(id)
    // This id give a bound to the user
        UserCMS follower=this.userRepo.findById(Long.parseLong(userId)).get();
        Commend newCommend= new Commend( commend.getBody(),  commend.getCreatedDate(),
                commend.getIndex(),commend.getFollower(),commend.getPost());
        newCommend.setFollower(follower);
        newCommend.setBody(commend.getBody());
        newCommend.setCreatedDate(commend.getCreatedDate());
        newCommend.setId(commend.getId());
        Posts post=this.postRepo.findById(Long.parseLong(postId)).get();
        newCommend.setPost(post);
        return this.comRepo.save(newCommend);
    }

    public Commend getCommends(Long id) {
        Optional<Commend> commendOptional= this.comRepo.findById(id);
        if(commendOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return  commendOptional.get();
    }

    public Iterable<Commend> getAllCommends() {
        Iterable<Commend> commendsOpt=this.comRepo.findAllByOrderByCreatedDateDesc();
        return commendsOpt;
    }


    public Commend update(Commend commend) {
        Long reqId= commend.getId();
        Optional<Commend> commOpt= this.comRepo.findById(reqId);
        if(commOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        //to update requestBody
        Commend comEntity= commOpt.get();
        String newBody= commend.getBody();
        //We are updating date
        comEntity.setBody(newBody);
        Date createdDate= new  Date();
        comEntity.setCreatedDate(createdDate);
        Commend savedCommends= this.comRepo.save(comEntity);
        return savedCommends;
    }

    public void delete(Commend id) {
        this.comRepo.delete(id);
    }
}
