package com.bitcoresolutions.usuarios.security;

import com.bitcoresolutions.usuarios.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Emite los tokens JWT. Contrato del token (lo consumen gateway y demas servicios):
 *   sub    -> email del usuario
 *   userId -> id del usuario
 *   rol    -> ADMIN | REPARTIDOR | CLIENTE
 *   nombre -> nombre del usuario
 *   iat/exp
 * Firma: HS256 con el secreto compartido (jwt.secret).
 */
@Service
public class JwtService {

    private final SecretKey key;
    private final long expirationMs;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms}") long expirationMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }

    public String generateToken(Usuario usuario) {
        Date ahora = new Date();
        return Jwts.builder()
                .setSubject(usuario.getEmail())
                .claim("userId", usuario.getId())
                .claim("rol", usuario.getRol().name())
                .claim("nombre", usuario.getNombre())
                .setIssuedAt(ahora)
                .setExpiration(new Date(ahora.getTime() + expirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public long getExpirationMs() {
        return expirationMs;
    }
}
