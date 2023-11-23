package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.AdmModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmRepository extends JpaRepository<AdmModel, Integer> {
}
