package com.rodrigomilitaobackend.restapi.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import com.rodrigomilitaobackend.restapi.model.Token;
import com.rodrigomilitaobackend.restapi.model.Usuario;
import com.rodrigomilitaobackend.restapi.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
	
	// EXPIRATION_TIME = 5 minutos
	private final long EXPIRATION_TIME = 300000;
	private final String SECRET = "SuperSecret";
	
	public String addAuthentication(Usuario usuario) {
		String JWT = Jwts.builder()
				.setSubject(usuario.getLogin())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        Token currentToken = new Token();
        currentToken.setLogin(usuario.getLogin());
        currentToken.setToken(JWT);
        currentToken.setAdministrador(usuario.getAdministrador());

        Timestamp expiracao = new Timestamp(
				Jwts.parser()
				.setSigningKey(SECRET)
				.parseClaimsJws(JWT)
				.getBody().getExpiration().getTime());
        
        currentToken.setExpiracao(expiracao);

        tokenRepository.save(currentToken);
                
		return JWT;
    }
    
    public Boolean reAuthentication(String token) {
		Token persistedToken = tokenRepository.findByToken(token);
		if(persistedToken == null) return false;

		Timestamp novaExpiracao = new Timestamp(
			new Date(System.currentTimeMillis() + EXPIRATION_TIME)
			.getTime());

		persistedToken.setExpiracao(novaExpiracao);
		tokenRepository.save(persistedToken);

		return true;

	}
	
	public void verifyAuthentication(String token, Boolean adminRequired) {
		Token persistedToken = tokenRepository.findByToken(token);
		Timestamp currentTimestamp = new Timestamp(new Date(System.currentTimeMillis()).getTime());
		if(persistedToken == null || persistedToken.getExpiracao().before(currentTimestamp)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
		}

		if(adminRequired) {
			if(!persistedToken.getAdministrador())
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
		}
	}
	
	
}
