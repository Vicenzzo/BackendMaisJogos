package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.FavoritoModel;
import expertostech.autenticacao.jwt.model.ReviewModel;
import expertostech.autenticacao.jwt.model.UsuarioModel;
import expertostech.autenticacao.jwt.repository.ReviewRepository;
import expertostech.autenticacao.jwt.services.MensagemDeSucesso;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ReviewModel>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/listarReview/{id}")
    public ResponseEntity<ReviewModel> retornaReview(@PathVariable Integer id){
        Optional<ReviewModel> reviewId = this.repository.findById(id);
        if(reviewId.isPresent()) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return null;
    }
    @PostMapping("/salvar")
    public ResponseEntity<ReviewModel> salvar(@RequestBody ReviewModel review) {
        return ResponseEntity.ok(repository.save(review));
    }
    @PutMapping("/alterarReview/{id}")
    public ResponseEntity<ReviewModel> alterarReview(@PathVariable Integer id, @RequestBody ReviewModel review){
        Optional<ReviewModel> reviewId = this.repository.findById(id);

        if (reviewId.isPresent()) {
            ReviewModel reviewNovo = this.repository.findById(id).get();

            review.setId(id);
            if(review.getTituloReview() == null){
                review.setTituloReview(reviewNovo.getTituloReview());
            }
            if(review.getDescricaoReview() == null){
                review.setDescricaoReview(reviewNovo.getDescricaoReview());
            }
            if(review.getDataReview() == null){
                review.setDataReview(reviewNovo.getDataReview());
            }
            if(review.getNotaReview() == 0){
                review.setNotaReview(reviewNovo.getNotaReview());
            }
            if(review.getIdJogo() == null){
                review.setIdJogo(reviewNovo.getIdJogo());
            }
            if(review.getIdUser() == null){
                review.setIdUser(reviewNovo.getIdUser());
            }

            return ResponseEntity.ok(repository.save(review));

        }else{
            return null;
        }
    }

    @DeleteMapping("deletarReview/{id}")
    public MensagemDeSucesso deletarReview(@PathVariable Integer id) {
        Optional<ReviewModel> reviewId = this.repository.findById(id);
        if (reviewId.isPresent()) {
            this.repository.deleteById(id);
            MensagemDeSucesso mensagem = new MensagemDeSucesso();
            mensagem.setMensagem("Deletado com sucesso");
            return mensagem;

        }

        return null;
    }
}
