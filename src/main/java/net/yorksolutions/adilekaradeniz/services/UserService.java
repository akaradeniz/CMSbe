package net.yorksolutions.adilekaradeniz.services;

import net.yorksolutions.adilekaradeniz.dto.UserDTO;
import net.yorksolutions.adilekaradeniz.entities.UserCMS;
import net.yorksolutions.adilekaradeniz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Observable;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
@Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public UserCMS register(UserDTO userDTO) {
        try {
            return this.userRepo.save(new UserCMS(
                    userDTO.firstname,
                    userDTO.lastname,
                    userDTO.email,
                    userDTO.username,
                    userDTO.password
            ));

        }catch (RuntimeException exception){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public UserCMS authenticate(String username, String password){
        return userRepo.findByUsernameAndPassword(username, password).orElse(null);
    }

    public UserCMS login(String username, String password){
        Optional<UserCMS> userOpt= this.userRepo.findByUsernameAndPassword(username, password);
        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userOpt.get();
    }

    public Iterable<UserCMS> getListOfUsers(){
        return this.userRepo.findAll();
    }


    public void deleteById(Long id) {
        this.userRepo.deleteById(id);
    }

    public UserCMS updateUser(UserCMS reqBody) {
        Long id=reqBody.getId();
        Optional<UserCMS> userOpt= this.userRepo.findById(id);
        if(userOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        UserCMS newUser=userOpt.get();
        String newFirstname= reqBody.getFirstname();
        newUser.setFirstname(newFirstname);
        String newLastname= reqBody.getLastname();
        newUser.setLastname(newLastname);
        String newEmail= reqBody.getEmail();
        newUser.setEmail(newEmail);
        String newUsername= reqBody.getUsername();
        newUser.setUsername(newUsername);
        String newPassword= reqBody.getPassword();
        newUser.setPassword(newPassword);

        return this.userRepo.save(newUser);
    }




}
