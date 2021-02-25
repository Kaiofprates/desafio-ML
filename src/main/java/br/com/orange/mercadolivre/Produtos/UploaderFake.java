package br.com.orange.mercadolivre.Produtos;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {
    /**
     * @param images
     * @return retorna um set de likns das imanges que foram salvas em algum lugar do universo
     */
    public static Set<String> envia(List<MultipartFile> images) {

        return images.stream().map(imagem -> "http://firebase.io/"
                +imagem.getOriginalFilename()+"-"+
                UUID.randomUUID().toString())
                .collect(Collectors.toSet());

    }

}
