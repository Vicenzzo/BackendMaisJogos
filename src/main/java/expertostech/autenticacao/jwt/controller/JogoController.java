package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.AvatarModel;
import expertostech.autenticacao.jwt.model.JogoModel;
import expertostech.autenticacao.jwt.repository.JogoRepository;
import expertostech.autenticacao.jwt.services.JogoService;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jogo")
public class JogoController {
    private final JogoRepository repository;
    private final JogoService jogoService;
    Logger logger = LogManager.getLogger(this.getClass());
    public JogoController(JogoRepository repository, JogoService jogoService) {
        this.repository = repository;
        this.jogoService = jogoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<JogoModel> salvar(@RequestBody JogoModel avatar){
        return ResponseEntity.ok(this.repository.save(avatar));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<JogoModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarJogo/{id}")
    public ResponseEntity<JogoModel> retornaJogo(@PathVariable Integer id){
        Optional<JogoModel> jogoId = this.repository.findById(id);
        if(jogoId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }
    @PutMapping("/alterarJogo/{id}")
    public ResponseEntity<JogoModel> alterarJogo(@PathVariable Integer id, @RequestBody JogoModel jogo){
        Optional<JogoModel> jogoId = this.repository.findById(id);

        if (jogoId.isPresent()) {
            JogoModel jogoNovo = this.repository.findById(id).get();

            jogo.setId(id);

            if(jogo.getTitulo() == null){
                jogo.setTitulo(jogoNovo.getTitulo());
            }
           if(jogo.getDescricao() == null){
               jogo.setDescricao(jogoNovo.getDescricao());
           }
           if(jogo.getGenero() == null){
               jogo.setGenero(jogoNovo.getGenero());
           }
           if(jogo.getPlataforma() == null){
               jogo.setPlataforma(jogoNovo.getPlataforma());
           }
           if(jogo.getSO() == null){
               jogo.setSO(jogoNovo.getSO());
           }
           if(jogo.getProcessador() == null){
               jogo.setProcessador(jogoNovo.getProcessador());
           }
           if(jogo.getPlacaDeVideo() == null){
               jogo.setPlacaDeVideo(jogoNovo.getPlacaDeVideo());
           }
           if(jogo.getQuantMemoria() == null){
               jogo.setQuantMemoria(jogoNovo.getQuantMemoria());
           }
           if(jogo.getTipoMemoria() == null){
               jogo.setTipoMemoria(jogoNovo.getTipoMemoria());
           }
           if(jogo.getQuantArmazenamento() == null){
               jogo.setQuantArmazenamento(jogoNovo.getQuantArmazenamento());
           }
           if(jogo.getTipoArmazenamento() == null)
           {
               jogo.setTipoArmazenamento(jogoNovo.getTipoArmazenamento());
           }
           if(jogo.getJogo() == null){
               jogo.setJogo(jogoNovo.getJogo());
            }
           if (jogo.getIdDev() == null){
               jogo.setIdDev(jogoNovo.getIdDev());
           }

            return ResponseEntity.ok(repository.save(jogo));

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

                jogoService.atualizarJogo(id, file);
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

    @DeleteMapping("deletarJogo/{id}")
    public MensagemDeSucesso deletarJogo(@PathVariable Integer id) {
        Optional<JogoModel> jogoId = this.repository.findById(id);
        if (jogoId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }
}