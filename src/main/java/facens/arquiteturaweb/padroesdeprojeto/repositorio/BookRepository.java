package facens.arquiteturaweb.padroesdeprojeto.repositorio;

import facens.arquiteturaweb.padroesdeprojeto.modelo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthorId(String title, Long authorId);
}
