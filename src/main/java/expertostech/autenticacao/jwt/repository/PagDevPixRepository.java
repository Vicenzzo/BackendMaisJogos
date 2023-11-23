package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.PagDevPixModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagDevPixRepository extends JpaRepository<PagDevPixModel, Integer> {
}
