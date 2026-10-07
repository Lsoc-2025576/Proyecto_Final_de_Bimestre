package com.bitcoresolutions.usuarios.service;

import com.bitcoresolutions.usuarios.dto.AuthResponse;
import com.bitcoresolutions.usuarios.dto.LoginRequest;
import com.bitcoresolutions.usuarios.dto.RegisterRequest;
import com.bitcoresolutions.usuarios.dto.UsuarioResponse;
import com.bitcoresolutions.usuarios.exception.EmailAlreadyExistsException;
import com.bitcoresolutions.usuarios.model.Rol;
import com.bitcoresolutions.usuarios.model.Usuario;
import com.bitcoresolutions.usuarios.repository.UsuarioRepository;
import com.bitcoresolutions.usuarios.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public UsuarioResponse register(RegisterRequest request) {
        String email = normalizar(request.email());

        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.nombre().trim());
        usuario.setDireccion(request.direccion());
        usuario.setTelefono(request.telefono());
        usuario.setEmail(email);
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setRol(Rol.CLIENTE); // el registro publico nunca elige rol

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    public AuthResponse login(LoginRequest request) {
        String email = normalizar(request.email());

        // Lanza BadCredentialsException si el email o el password no coinciden
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, request.password()));

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException("Credenciales invalidas"));

        String token = jwtService.generateToken(usuario);

        return new AuthResponse(
                token,
                "Bearer",
                jwtService.getExpirationMs() / 1000,
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getRol().name());
    }

    private String normalizar(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
