package expertostech.autenticacao.jwt.controller;

import expertostech.autenticacao.jwt.model.CheckModel;
import expertostech.autenticacao.jwt.model.JogoModel;
import expertostech.autenticacao.jwt.repository.CheckRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/check")
public class CheckController {

    private final CheckRepository repository;

    public CheckController(CheckRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/salvar")
    public ResponseEntity<CheckModel> salvar(@RequestBody CheckModel check){
        return ResponseEntity.ok(this.repository.save(check));
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<CheckModel>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }
}
