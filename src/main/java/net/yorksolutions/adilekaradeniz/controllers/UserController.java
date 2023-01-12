package net.yorksolutions.adilekaradeniz.controllers;

import net.yorksolutions.adilekaradeniz.dto.UserDTO;
import net.yorksolutions.adilekaradeniz.entities.UserCMS;
import net.yorksolutions.adilekaradeniz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usercms")
@CrossOrigin
public class UserController {

    private  final UserService userService;

   @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public UserCMS register(@RequestBody UserDTO  newUser){
        return this.userService.register(newUser);

    }
    @GetMapping
    public UserCMS login(@RequestParam String username, String password){
       return this.userService.login(username, password);
    }

    @GetMapping("/ListOfUsers")
    public Iterable<UserCMS> getListOfUsers(){
       return this.userService.getListOfUsers();
    }


    @DeleteMapping
    public void delete(@RequestParam Long id){
       this.userService.deleteById(id);
   }

   @PutMapping
    public UserCMS updateUserInfo(@RequestBody UserCMS reqBody){
     return  this.userService.updateUser(reqBody);
   }


}
