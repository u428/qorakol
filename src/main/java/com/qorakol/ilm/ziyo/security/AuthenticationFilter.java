package com.qorakol.ilm.ziyo.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.qorakol.ilm.ziyo.constant.SecurityConstants;
import com.qorakol.ilm.ziyo.model.dto.AuthDto;
import com.qorakol.ilm.ziyo.service.interfaces.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;


    @Autowired
    public AuthenticationFilter(AuthenticationManager authenticationManager, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            AuthDto creds=new ObjectMapper().readValue(request.getInputStream(), AuthDto.class);
            System.out.println(creds);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                creds.getLogin(),
                creds.getPassword(),
                new ArrayList<SimpleGrantedAuthority>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String userName=((User) authResult.getPrincipal()).getUsername();


        String token = Jwts.builder()
            .setSubject(userName)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis()+ SecurityConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
            .compact();

        Map<String, Object>  list = authService.getCurrentUser(userName);

        list.put("token", token);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Gson gson = new Gson();
        String a = gson.toJson(list);
        out.print(a);
        out.flush();
//        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+token);
    }
}
