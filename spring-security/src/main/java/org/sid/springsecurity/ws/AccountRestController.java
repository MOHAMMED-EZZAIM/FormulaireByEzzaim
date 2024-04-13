package org.sid.springsecurity.ws;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.sid.springsecurity.bean.AppRole;
import org.sid.springsecurity.bean.AppUser;
import org.sid.springsecurity.service.AccountService;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AccountRestController {
    @PostMapping("/user")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public AppUser addNewUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @PostAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/role")
    public AppRole addNewRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @PostAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    public AppUser loadUserByUsername(String username) {
        return accountService.loadUserByUsername(username);
    }

    @GetMapping("/user")
    @PostAuthorize("hasAnyAuthority('ADMIN')")
    public List<AppUser> listUsers() {
        return accountService.listUsers();
    }

    private AccountService accountService;

    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/refreshToken/")
    public void refreshToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String authenticationTken=request.getHeader("Authorization");
        if(authenticationTken!=null && authenticationTken.startsWith("Bearer")){
            try {
                String jwt = authenticationTken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecret1234");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();

               AppUser appUser=accountService.loadUserByUsername(username);

                String jwtAccessToken= JWT.create()
                        .withSubject(appUser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appUser.getAppRols().stream().map(r->r.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("refreshToken", jwt);
                tokenMap.put("new accessToken",jwtAccessToken);
                ObjectMapper objectMapper = new ObjectMapper();
                String tokensJson = objectMapper.writeValueAsString(tokenMap);

                response.setHeader("Authorization", tokensJson);

            } catch (Exception ex) {
                throw ex;
            }

        }else{
            throw  new RuntimeException("Rfersh token required!!");
        }
    }
}
@Data
class RoleUserForm{
    private String username;
    private String roleName;
}
