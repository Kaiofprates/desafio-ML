package br.com.orange.mercadolivre.Usuario;

import br.com.orange.mercadolivre.handlers.exceptions.validation.UniqueValues.UniqueValue;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Clock;
import java.time.LocalDateTime;

public class UsuarioRequest {

    @NotBlank(message = "O campo email não pode estar vazio")
    @Email(message = "O campo email deve ter um formato válido")
    @UniqueValue(domainClass = Usuario.class,fieldName = "email")
    private String email;
    @NotBlank(message = "O campo senha não deve estar em branco")
    @Size(min = 6, message = "O campo senha deve ter no mínimo 6 caracteres")
    private String senha;
    @NotNull
    private LocalDateTime registro = LocalDateTime.now(Clock.systemDefaultZone());

    public UsuarioRequest(@NotBlank(message = "O campo email não pode estar vazio") @Email String email, @NotBlank @Min(6) String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario paraUsuario() {
        return new Usuario(email, senha);
    }
}
