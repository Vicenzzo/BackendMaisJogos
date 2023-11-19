package expertostech.autenticacao.jwt.services;


import expertostech.autenticacao.jwt.model.AvatarModel;
import expertostech.autenticacao.jwt.model.JogoModel;
import expertostech.autenticacao.jwt.repository.JogoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class JogoService {

    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private final JogoRepository repository;
    private final Environment environment = null;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }
    public JogoModel atualizarJogo(Integer id, MultipartFile arquivo) throws IOException {
        Optional<JogoModel> jogoOptional = this.repository.findById(id);

        if (jogoOptional.isPresent()) {
            JogoModel jogo = jogoOptional.get();

            // Obter informações sobre o novo arquivo
            String nome = arquivo.getOriginalFilename();
            byte[] conteudo = arquivo.getBytes();

            // Atualize os campos necessários do Avatar
            //avatar.setNome(nome);
            jogo.setJogo(conteudo);

            // Salve as mudanças no banco de dados
            jogo = repository.save(jogo);

            logger.info(">>>>>> Serviço para atualizar Avatar executado");
            return jogo;
        } else {
            // O Avatar com o ID especificado não foi encontrado
            return null;
        }
    }
}
