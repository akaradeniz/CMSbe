package net.yorksolutions.adilekaradeniz.controllers;

import net.yorksolutions.adilekaradeniz.dto.PostsDto;
import net.yorksolutions.adilekaradeniz.entities.Posts;
import net.yorksolutions.adilekaradeniz.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.Observable;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostsController {
    private PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping
    public Posts create(@RequestBody Posts posts, @RequestHeader("Authorization") String id){

        return this.postsService.createPost(posts, id);

    }
    @GetMapping
    public Posts getPosts(@RequestParam Long id){

        return this.postsService.getPosts(id);
    }

    @GetMapping("/posts/all")
    public Iterable<Posts> getBlogPost(){
        Iterable<Posts> postsBlog= this.postsService.getAllPosts();
        return postsBlog;
    }
    @GetMapping("/{author}")
    public Posts getPostsByName(@PathVariable String author){
        // TODO get user all posts
        return new Posts();
    }
    @PutMapping
    public Posts update(@RequestBody Posts posts){

        return this.postsService.updatePost(posts);
    }
    @DeleteMapping
    public void delete(@RequestParam Posts id){

        this.postsService.delete(id);
    }


}
