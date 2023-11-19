package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel, Integer> {
}
