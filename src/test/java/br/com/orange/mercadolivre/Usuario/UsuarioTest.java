package br.com.orange.mercadolivre.Usuario;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    String usuarioRequest = "{\n" +
            "    \"email\" : \"johndoe@email.com\",\n" +
            "    \"senha\" : \"123456\"\n" +
            "}";

    @DisplayName("Deveria lidar com o sucesso no cadastro de usuário")
    @Test()
    public void criaUsuarioTeste() throws  Exception{

        /*
        * Nesse desafio seguiremos a risca todas as especificações
        * retornaremos apenas o status code 200 e não mais um objeto!
        */

        mockMvc.perform(MockMvcRequestBuilders.post("/mercadolivre/usuario")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.usuarioRequest)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com login (email) branco ou nulo")
    @Test
    public void emailVazioTeste() throws  Exception{

        String usuarioNullouVazio = "{\n" +
                "    \"email\" : \"\",\n" +
                "    \"senha\" : \"123456\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/mercadolivre/usuario")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(usuarioNullouVazio)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.field").isString())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.error").value("O campo email não pode estar vazio"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com login (email) com formato inválido")
    @Test
    public void emailFormatoInvalidoTeste() throws  Exception{

        String emailMalFormatado = "{\n" +
                "    \"email\" : \"qualquer.comemail\",\n" +
                "    \"senha\" : \"123456\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/mercadolivre/usuario")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(emailMalFormatado)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.field").isString())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.error").value("O campo email deve ter um formato válido"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com a senha branca ou nula")
    @Test
    public void senhaBrancaOuNulaTeste() throws  Exception{

        String senhaBrancaOuNula = "{\n" +
                "    \"email\" : \"john@email.com\",\n" +
                "    \"senha\" : \"\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/mercadolivre/usuario")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(senhaBrancaOuNula)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.field").isString())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.error").value("O campo de senha não deve ser nulo ou em branco"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("Deveria lidar com uma senha com o tamanho menor que 6 caracteres")
    @Test
    public void senhaSemTamanhoMinimo() throws  Exception{

        String senhaMenor = "{\n" +
                "    \"email\" : \"john@email.com\",\n" +
                "    \"senha\" : \"12345\"\n" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders.post("/mercadolivre/usuario")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(senhaMenor)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(jsonPath("$.field").isString())
                .andExpect(jsonPath("$.status").isNumber())
                .andExpect(jsonPath("$.error").value("O campo de senha deve ter no minimo 6 caracteres"))
                .andDo(MockMvcResultHandlers.print());
    }

}


