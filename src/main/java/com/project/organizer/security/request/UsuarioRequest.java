package com.project.organizer.security.request;

import com.project.organizer.security.enums.PerfilEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioRequest {

    @NotEmpty(message = "Email é obrigatório")
    private String email;
    @NotEmpty(message = "Senha é obrigatória")
    private String senha;

    @Enumerated(EnumType.STRING)
    private PerfilEnum perfil;

}
