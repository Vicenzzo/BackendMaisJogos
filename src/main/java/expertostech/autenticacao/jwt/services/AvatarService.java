package expertostech.autenticacao.jwt.services;

import expertostech.autenticacao.jwt.model.AvatarModel;
import expertostech.autenticacao.jwt.repository.AvatarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class AvatarService {
    Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private final AvatarRepository avatarRepository;
    private final Environment environment = null;


    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public AvatarModel atualizarAvatar(Integer id, MultipartFile arquivo) throws IOException {
        Optional<AvatarModel> avatarOptional = this.avatarRepository.findById(id);

        if (avatarOptional.isPresent()) {
            AvatarModel avatar = avatarOptional.get();

            // Obter informações sobre o novo arquivo
            String nome = arquivo.getOriginalFilename();
            byte[] conteudo = arquivo.getBytes();

            // Atualize os campos necessários do Avatar
            //avatar.setNome(nome);
            avatar.setArquivo(conteudo);

            // Salve as mudanças no banco de dados
            avatar = avatarRepository.save(avatar);

            logger.info(">>>>>> Serviço para atualizar Avatar executado");
            return avatar;
        } else {
            // O Avatar com o ID especificado não foi encontrado
            return null;
        }
    }
}
