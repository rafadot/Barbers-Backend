package com.Barbers.BarbersBackend.security;

import com.Barbers.BarbersBackend.V1.model.Users;
import com.Barbers.BarbersBackend.data.UsersDetailData;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager;

    public static final int TOKEN_EXPIRATION = 86400000;

    public static final String TOKEN_PASSWORD = "37b7de97-b0e2-4053-bcfe-ec5328969c60";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            Users users = new ObjectMapper().readValue(request.getInputStream() , Users.class);

            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    users.getEmail(),
                    users.getPassword(),
                    new ArrayList<>()
            ));

        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar usuário");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UsersDetailData usersDetailData = (UsersDetailData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usersDetailData.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        jsonObject.put("id", usersDetailData.getId());

        response.getWriter().write(jsonObject.toString());
        response.getWriter().flush();
    }
}
