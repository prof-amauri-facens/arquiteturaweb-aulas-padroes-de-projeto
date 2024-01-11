package facens.arquiteturaweb.padroesdeprojeto.repositorio;

import facens.arquiteturaweb.padroesdeprojeto.modelo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
