package br.com.orange.mercadolivre.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/mercadolivre")
public class UsuarioController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/usuario")
    @Transactional
    public ResponseEntity<?> casdastroNovoUsuario(@Valid @RequestBody UsuarioRequest request) {
        Usuario usuario = request.paraUsuario();
        if(usuario != null){
            manager.persist(usuario);
            return  ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
