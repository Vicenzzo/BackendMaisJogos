package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.CheckModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepository extends JpaRepository<CheckModel, Long> {
}
