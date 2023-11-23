package expertostech.autenticacao.jwt.controller;


import expertostech.autenticacao.jwt.model.AdmModel;
import expertostech.autenticacao.jwt.repository.AdmRepository;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adm")
public class AdmController {
    private final AdmRepository repository;


    public AdmController(AdmRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/salvar")
    public ResponseEntity<AdmModel> salvar(@RequestBody AdmModel adm){
        return ResponseEntity.ok(this.repository.save(adm));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<AdmModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarAdm/{id}")
    public ResponseEntity<AdmModel> retornaAdm(@PathVariable Integer id){
        Optional<AdmModel> admId = this.repository.findById(id);
        if(admId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }

    @DeleteMapping("deletarAdm/{id}")
    public MensagemDeSucesso deletarAdm(@PathVariable Integer id) {
        Optional<AdmModel> admId = this.repository.findById(id);
        if (admId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }
}
