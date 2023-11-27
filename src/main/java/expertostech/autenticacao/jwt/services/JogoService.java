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


    public JogoModel atualizarJogo(Integer id, MultipartFile jogoWin, MultipartFile bannerUm,
                              MultipartFile bannerDois, MultipartFile bannerTres,
                              MultipartFile bannerQuatro, MultipartFile bannerCinco,
                              MultipartFile licenca)  throws IOException{
        Optional<JogoModel> jogoOptional = this.repository.findById(id);

        if (jogoOptional.isPresent()) {
            JogoModel jogo = jogoOptional.get();

            // Obter informações sobre o novo arquivo
            String nome = jogoWin.getOriginalFilename();
            byte[] conteudo = jogoWin.getBytes();
            byte[] conteudo3 = bannerUm.getBytes();
            byte[] conteudo4 = bannerDois.getBytes();
            byte[] conteudo5 = bannerTres.getBytes();
            byte[] conteudo6 = bannerQuatro.getBytes();
            byte[] conteudo7 = bannerCinco.getBytes();
            byte[] conteudo8 = licenca.getBytes();

            // Atualize os campos necessários do Avatar
            //avatar.setNome(nome);
            jogo.setJogoWin(conteudo);
            jogo.setBannerUm(conteudo3);
            jogo.setBannerDois(conteudo4);
            jogo.setBannerTres(conteudo5);
            jogo.setBannerQuatro(conteudo6);
            jogo.setBannerCinco(conteudo7);
            jogo.setLicenca(conteudo8);

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
