package expertostech.autenticacao.jwt.repository;

import expertostech.autenticacao.jwt.model.AvatarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarRepository extends JpaRepository<AvatarModel, Integer> {
}
