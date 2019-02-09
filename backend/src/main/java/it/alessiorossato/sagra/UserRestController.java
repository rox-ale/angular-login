package it.alessiorossato.sagra;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by magemello on 14/05/2017.
 */

@RestController
@RequestMapping("/private")
@CrossOrigin
@PreAuthorize("hasRole('USER,ADMIN')")
public class UserRestController {

    @GetMapping("/user")
    public Principal user(Principal user){
        System.out.println("Entrato in user");
        return user;
    }
}

