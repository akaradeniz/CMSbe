package net.yorksolutions.adilekaradeniz.services;

import net.yorksolutions.adilekaradeniz.dto.PostsDto;
import net.yorksolutions.adilekaradeniz.entities.Posts;
import net.yorksolutions.adilekaradeniz.entities.UserCMS;
import net.yorksolutions.adilekaradeniz.repositories.PostRepository;
import net.yorksolutions.adilekaradeniz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class PostsService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

@Autowired
    public PostsService(PostRepository postRepository,
                        UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository=userRepository;
    }

    public Posts createPost(Posts posts, String id) {
Posts newPost= new Posts(posts.body, posts.getTitle(), posts.getCreatedDate(),posts.getUpdatedDate(),posts.getAuthor(),posts.getCommend());
//author connected with post from user id
UserCMS author=this.userRepository.findById( Long.parseLong(id)).get();
newPost.setAuthor(author);
newPost.setBody(posts.getBody());
newPost.setTitle(posts.getTitle());
newPost.setCreatedDate(posts.getCreatedDate());
        Posts savedPosts= this.postRepository.save(newPost);
        return savedPosts;
    }

    public Posts getPosts(Long id) {
        Optional<Posts> postOpt=this.postRepository.findById(id);
if(postOpt.isEmpty()){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
return postOpt.get();
    }

    public Iterable<Posts> getAllPosts() {
        Iterable<Posts> postsOptional= this.postRepository.findPostsByOrderByCreatedDateDesc();
return postsOptional;

    }
    public void delete(Posts id) {
        this.postRepository.delete(id);
    }


    public Posts updatePost(Posts updatedPosts) {
        Long reqId= updatedPosts.getId();
        Optional<Posts> postOpt = this.postRepository.findById(reqId);
        Optional<UserCMS> userOpt=this.userRepository.findById(updatedPosts.getAuthor().getId());
if(postOpt.isEmpty()){
    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
}
        Posts update=postOpt.get();
        UserCMS author=userOpt.get();
        update.setAuthor(author);
//        String newTitle=updatedPosts.getTitle();
//        update.setTitle(newTitle); //Alttaki bunun kisaltmasi
        update.setTitle(updatedPosts.getTitle());
        update.setBody(updatedPosts.getBody());
        Date updatedDate= new Date();
        update.setUpdatedDate(updatedDate);
        update.setCommend(updatedPosts.getCommend());
Posts updatedPost=this.postRepository.save(update);
        return updatedPost;

    }


}
