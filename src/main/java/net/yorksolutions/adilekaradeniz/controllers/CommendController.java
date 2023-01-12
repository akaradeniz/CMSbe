package net.yorksolutions.adilekaradeniz.controllers;

import net.yorksolutions.adilekaradeniz.entities.Commend;
import net.yorksolutions.adilekaradeniz.services.CommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commend")
@CrossOrigin
public class CommendController {
    private final CommendService comService;

    @Autowired
    public CommendController(CommendService comService) {
        this.comService = comService;
    }
    @PostMapping
    public Commend create(@RequestBody Commend commend,  @RequestHeader("Authorization") String userId, @RequestParam("postId")String postId){
        return this.comService.createCommend(commend,userId, postId);
    }

    @GetMapping
    public Commend getPosts(@RequestParam Long id){
        return this.comService.getCommends(id);
    }

    @GetMapping("/commend/all")
    public Iterable<Commend> getCommendList(){
        Iterable<Commend> commendsList= this.comService.getAllCommends();
        return commendsList;
    }

    @PutMapping
    public Commend update(@RequestBody Commend commend){
        return this.comService.update(commend);
    }

    @DeleteMapping
    public void delete(@RequestParam Commend id){
        this.comService.delete(id);
    }

}
