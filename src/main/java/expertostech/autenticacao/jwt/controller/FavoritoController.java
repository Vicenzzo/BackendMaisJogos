package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.FavoritoModel;
import expertostech.autenticacao.jwt.model.UsuarioModel;
import expertostech.autenticacao.jwt.repository.FavoritoRepository;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/favorito")
public class FavoritoController {
    private final FavoritoRepository repository;


    public FavoritoController(FavoritoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<FavoritoModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarFavorito/{id}")
    public ResponseEntity<FavoritoModel> retornaUsuario(@PathVariable Integer id){
        Optional<FavoritoModel> favoritoId = this.repository.findById(id);
        if(favoritoId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }

    @PostMapping("/salvar")
    public ResponseEntity<FavoritoModel> salvar(@RequestBody FavoritoModel favorito) {
        return ResponseEntity.ok(repository.save(favorito));
    }


    @DeleteMapping("deletarUser/{id}")
    public MensagemDeSucesso deletarUsuario(@PathVariable Integer id) {
        Optional<FavoritoModel> favoritoId = this.repository.findById(id);
        if (favoritoId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }

}
