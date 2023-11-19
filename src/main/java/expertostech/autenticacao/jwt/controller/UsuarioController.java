package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.UsuarioModel;
import expertostech.autenticacao.jwt.repository.UsuarioRepository;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<UsuarioModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarCliente/{id}")
    public ResponseEntity<UsuarioModel> retornaUsuario(@PathVariable Integer id){
        Optional<UsuarioModel> userId = this.repository.findById(id);
        if(userId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioModel> salvar(@RequestBody UsuarioModel usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(repository.save(usuario));
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                                @RequestParam String password) {

        Optional<UsuarioModel> optUsuario = repository.findByLogin(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        UsuarioModel usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @PutMapping("/alterarusuario/{id}")
    public ResponseEntity<UsuarioModel> alterarusuario(@PathVariable Integer id, @RequestBody UsuarioModel user){
        Optional<UsuarioModel> userId = this.repository.findById(id);

        if (userId.isPresent()) {
            UsuarioModel userNovo = this.repository.findById(id).get();

            user.setId(id);
            if(user.getNome() == null){
                user.setNome(userNovo.getNome());
            }
            if(user.getMoeda() == null){
                user.setMoeda(userNovo.getMoeda());
            }
            if(user.getIdAvatar() == null){
                user.setIdAvatar(userNovo.getIdAvatar());
            }

            if(user.getSobre() == null){
                user.setSobre(userNovo.getSobre());
            }
            if(user.getSobrenome() == null){
                user.setSobrenome(userNovo.getSobrenome());
            }
            if(user.getDataNasc() == null){
                user.setDataNasc(userNovo.getDataNasc());
            }
            if(user.getPassword() == null){
                user.setPassword(userNovo.getPassword());
            }
            if(user.getConfirmarPassword() == null){
                user.setConfirmarPassword(userNovo.getConfirmarPassword());
            }
            if(user.getLogin() == null){
                user.setLogin(userNovo.getLogin());
            }
            return ResponseEntity.ok(repository.save(user));

        }else{
            return null;
        }
    }

    @DeleteMapping("deletarUser/{id}")
    public MensagemDeSucesso deletarUsuario(@PathVariable Integer id) {
        Optional<UsuarioModel> userId = this.repository.findById(id);
        if (userId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

       return null;
    }

}
