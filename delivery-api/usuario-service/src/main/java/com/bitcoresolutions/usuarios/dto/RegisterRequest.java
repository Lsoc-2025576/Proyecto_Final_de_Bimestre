package com.bitcoresolutions.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Nota: no incluye "rol". El registro publico SIEMPRE crea un CLIENTE;
 * si el cliente envia un campo "rol" en el JSON, se ignora.
 */
public record RegisterRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La direccion no puede superar 255 caracteres")
        String direccion,

        @Size(max = 20, message = "El telefono no puede superar 20 caracteres")
        String telefono,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email no tiene un formato valido")
        @Size(max = 150, message = "El email no puede superar 150 caracteres")
        String email,

        @NotBlank(message = "La contrasena es obligatoria")
        @Size(min = 6, max = 72, message = "La contrasena debe tener entre 6 y 72 caracteres")
        String password
) {
}
