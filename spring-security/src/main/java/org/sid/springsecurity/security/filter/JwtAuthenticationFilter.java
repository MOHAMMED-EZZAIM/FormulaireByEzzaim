package org.sid.springsecurity.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("la methode:attemptAuthentication");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        return authenticationManager.authenticate(authenticationToken);
    }



    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("la methode:successfulAuthentication");
        User user= (User) authResult.getPrincipal();
        Algorithm algo1=Algorithm.HMAC256("mySecret1234");
        String jwtAccessToken= JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+1*60*1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algo1);

        String jwtRefreshToken= JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000))
        .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(ga->ga.getAuthority()).collect(Collectors.toList()))
        .sign(algo1);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("accessToken", jwtAccessToken);
        tokenMap.put("refreshToken", jwtRefreshToken);

        System.out.println(tokenMap);

        ObjectMapper objectMapper = new ObjectMapper();
        String tokensJson = objectMapper.writeValueAsString(tokenMap);

        response.setHeader("Authorization", tokensJson);

    }
}
