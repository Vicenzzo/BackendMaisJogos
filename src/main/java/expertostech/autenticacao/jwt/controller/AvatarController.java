package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.AvatarModel;
import expertostech.autenticacao.jwt.repository.AvatarRepository;
import expertostech.autenticacao.jwt.services.AvatarService;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avatar")
public class AvatarController {
    private final AvatarRepository repository;
    private final AvatarService avatarService;
    Logger logger = LogManager.getLogger(this.getClass());

    public AvatarController(AvatarRepository repository, AvatarService avatarService) {
        this.repository = repository;
        this.avatarService = avatarService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<AvatarModel> salvar(@RequestBody AvatarModel avatar){
        return ResponseEntity.ok(this.repository.save(avatar));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<AvatarModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarAvatar/{id}")
    public ResponseEntity<AvatarModel> retornaAvatar(@PathVariable Integer id){
        Optional<AvatarModel> AvatarId = this.repository.findById(id);
        if(AvatarId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }


    @PutMapping("/alterarAvatar/{id}")
    public ResponseEntity<AvatarModel> alterarAvatar(@PathVariable Integer id, @RequestBody AvatarModel avatar){
        Optional<AvatarModel> avatarId = this.repository.findById(id);

        if (avatarId.isPresent()) {
            AvatarModel avatarNovo = this.repository.findById(id).get();

            avatar.setId(id);
            if(avatar.getNome() == null){
                avatar.setNome(avatarNovo.getNome());
            }
            if(avatar.getValor() == 0){
                avatar.setValor(avatarNovo.getValor());
            }
            if(avatar.getCaminho() == null){
                avatar.setCaminho(avatarNovo.getCaminho());
            }
            if(avatar.getArquivo() == null){
                avatar.setArquivo(avatarNovo.getArquivo());
            }

            return ResponseEntity.ok(repository.save(avatar));

        }else{
            return null;
        }
    }
    @PatchMapping("atualizaFile/{id}")
    public ResponseEntity<String> handleFileUpload(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        logger.info(">>>>>> api manipula file upload chamado");
        if (file != null && !file.isEmpty()) {
            logger.info(">>>>>> api manipula file upload file nao esta vazio");
            try {
                logger.info(">>>>>> api manipula file upload chamou servico salvar");

                avatarService.atualizarAvatar(id, file);
                return ResponseEntity.ok().body("Imagem enviada com sucesso");
            } catch (FileNotFoundException e) {
                logger.info(">>>>>> api manipula file upload arquivo não encontrado");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Arquivo nao encontrado");
            } catch (FileUploadException e) {
                logger.info(">>>>>> api manipula file upload erro no upload");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao enviar o arquivo");
            } catch (IOException e) {
                logger.info(">>>>>> api manipula file upload erro de i/o => " + e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha erro de I/O");
            }
        } else {
            logger.info(">>>>>> api manipula file arquivo vazio ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Arquivo vazio");
        }
    }

    @DeleteMapping("deletarUser/{id}")
    public MensagemDeSucesso deletarAvatar(@PathVariable Integer id) {
        Optional<AvatarModel> avatarId = this.repository.findById(id);
        if (avatarId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }

}
