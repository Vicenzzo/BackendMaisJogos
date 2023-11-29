package expertostech.autenticacao.jwt.controller;


import expertostech.autenticacao.jwt.model.AdmModel;
import expertostech.autenticacao.jwt.model.LoginAdm;
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

    @PostMapping("/login")
    public String login(@RequestBody LoginAdm loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Busca o usuário no banco de dados pelo email
        Optional<AdmModel> userOptional = repository.findByEmail(email);

        if (userOptional.isPresent()) {
            AdmModel user = userOptional.get();
            // Verifica a senha (não recomendado em produção, use bibliotecas de segurança)
            if (user.getPassword().equals(password)) {
                return "Login bem-sucedido. Bem-vindo, " + user.getEmail();
            } else {
                return "Credenciais inválidas";
            }
        } else {
            return "Credenciais inválidas";
        }
    }

    @PutMapping("/alterarAdmin/{id}")
    public ResponseEntity<AdmModel> alterarusuario(@PathVariable Integer id, @RequestBody AdmModel admin){
        Optional<AdmModel> adminId = this.repository.findById(id);

        if (adminId.isPresent()) {
            AdmModel adminNovo = this.repository.findById(id).get();

            admin.setId(id);

            if(admin.getNome() == null){
                admin.setNome(adminNovo.getNome());
            }

            if(admin.getEmail() == null){
                admin.setNome(adminNovo.getEmail());
            }

            if(admin.getPassword() == null){
                admin.setPassword(adminNovo.getPassword());
            }

            return ResponseEntity.ok(repository.save(admin));

        }else{
            return null;
        }
    }
}
