package it.alessiorossato.sagra;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by magemello on 14/05/2017.
 */

@RestController
@RequestMapping("/")
@CrossOrigin
@PreAuthorize("hasRole('USER,ADMIN')")
public class TokenRestController {

    @Autowired
    private TokenStore tokenStore;

    @GetMapping(value = "/oauth/revoke-token")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null){
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }
}
