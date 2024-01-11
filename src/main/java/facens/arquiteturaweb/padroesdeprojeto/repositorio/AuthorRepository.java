package facens.arquiteturaweb.padroesdeprojeto.repositorio;

import facens.arquiteturaweb.padroesdeprojeto.modelo.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author, Long> {
}
