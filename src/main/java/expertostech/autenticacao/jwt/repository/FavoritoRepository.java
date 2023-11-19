package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.FavoritoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<FavoritoModel, Integer> {
}
