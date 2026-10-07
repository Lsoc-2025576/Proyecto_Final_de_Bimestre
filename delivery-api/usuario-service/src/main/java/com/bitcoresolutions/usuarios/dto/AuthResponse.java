package com.bitcoresolutions.usuarios.dto;

public record AuthResponse(
        String token,
        String tipo,
        long expiraEnSegundos,
        Long usuarioId,
        String nombre,
        String email,
        String rol
) {
}
