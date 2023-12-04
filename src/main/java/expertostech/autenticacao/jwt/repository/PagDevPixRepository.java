package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.PagDevPixModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagDevPixRepository extends JpaRepository<PagDevPixModel, Integer> {

    Optional<PagDevPixModel> findByIdDev(Integer id);
}
