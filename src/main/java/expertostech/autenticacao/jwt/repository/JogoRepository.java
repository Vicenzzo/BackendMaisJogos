package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.JogoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<JogoModel, Integer> {
}
