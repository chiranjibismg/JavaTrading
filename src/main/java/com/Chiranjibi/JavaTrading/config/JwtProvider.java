package com.Chiranjibi.JavaTrading.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * JwtProvider is responsible for generating JWT tokens based on user authentication details.
 * It uses a predefined secret key to sign the tokens.
 */
public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());


    /**
     * Generates a JWT token for the provided authentication.
     *
     * @param auth the authentication object containing user details and authorities
     * @return a signed JWT token as a string
     */
    public static String generatetoken(Authentication auth){

        Collection<?extends GrantedAuthority>authorities =auth.getAuthorities();

        String roles=populateAuthorities(authorities) ;

        String jwt = Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() +86400000))
                .claim("email",auth.getName())
                .claim("authorities",roles)
                .signWith(key)
                .compact() ;


        return jwt;

    }

    /**
     * Retrieves the email from the provided JWT token.
     *
     * @param token the JWT token from which the email needs to be extracted
     * @return the email extracted from the token
     */
    public static String getEmailfromToken(String token){

         token=token.substring(7) ;

        String email =String.valueOf(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("email"));

        return email ;

    }


    /**
     * Converts a collection of GrantedAuthority objects into a comma-separated string of their authorities.
     *
     * @param authorities the collection of GrantedAuthority objects to process
     * @return a comma-separated string of the authorities
     */
    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auth = new HashSet<String>();

        for(GrantedAuthority ga : authorities){
            auth.add(ga.getAuthority());
        }

        return  String.join(",",auth);
    }
}

