package com.bitcoresolutions.usuarios.dto;

import com.bitcoresolutions.usuarios.model.Usuario;

/** Representacion publica de un usuario: nunca expone el password. */
public record UsuarioResponse(
        Long id,
        String nombre,
        String direccion,
        String telefono,
        String email,
        String rol
) {
    public static UsuarioResponse from(Usuario u) {
        return new UsuarioResponse(
                u.getId(),
                u.getNombre(),
                u.getDireccion(),
                u.getTelefono(),
                u.getEmail(),
                u.getRol().name());
    }
}
