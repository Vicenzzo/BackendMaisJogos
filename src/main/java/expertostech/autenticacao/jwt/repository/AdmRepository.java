package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.AdmModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmRepository extends JpaRepository<AdmModel, Integer> {
    Optional<AdmModel> findByEmail(String email);

}
