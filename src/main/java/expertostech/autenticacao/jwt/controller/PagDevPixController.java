package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.PagDevPixModel;
import expertostech.autenticacao.jwt.repository.PagDevPixRepository;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pixDev")
public class PagDevPixController {
    private final PagDevPixRepository repository;

    public PagDevPixController(PagDevPixRepository repository) {
        this.repository = repository;
    }
    @PostMapping("/salvar")
    public ResponseEntity<PagDevPixModel> salvar(@RequestBody PagDevPixModel pag){
        return ResponseEntity.ok(this.repository.save(pag));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<PagDevPixModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/listarPix/{id}")
    public ResponseEntity<PagDevPixModel> retornaPagDevPix(@PathVariable Integer id){
        Optional<PagDevPixModel> pagPixId = this.repository.findById(id);
        if(pagPixId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }
    @GetMapping("/listaPixDev/{id}")
    public ResponseEntity<PagDevPixModel> retornaDevPix(@PathVariable Integer id){
        Optional<PagDevPixModel> pagPixId = this.repository.findByIdDev(id);
        if(pagPixId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }
    @PutMapping("/alterarReview/{id}")
    public ResponseEntity<PagDevPixModel> alterarReview(@PathVariable Integer id, @RequestBody PagDevPixModel pag){
        Optional<PagDevPixModel> pagDevId = this.repository.findById(id);

        if (pagDevId.isPresent()) {
            PagDevPixModel pagNovo = this.repository.findById(id).get();

            pag.setId(id);
            if(pag.getPix() == null){
                pag.setPix(pagNovo.getPix());
            }
            if(pag.getValorPag() == 0){
                pag.setValorPag(pagNovo.getValorPag());
            }
            if(pag.getIdDev() == null){
                pag.setIdDev(pagNovo.getIdDev());
            }
            if(pag.getTipoPix() == null){
                pag.setTipoPix(pagNovo.getTipoPix());
            }


            return ResponseEntity.ok(repository.save(pag));

        }else{
            return null;
        }
    }

    @DeleteMapping("deletarPix/{id}")
    public MensagemDeSucesso deletarReview(@PathVariable Integer id) {
        Optional<PagDevPixModel> pagDevId = this.repository.findById(id);
        if (pagDevId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }
}
