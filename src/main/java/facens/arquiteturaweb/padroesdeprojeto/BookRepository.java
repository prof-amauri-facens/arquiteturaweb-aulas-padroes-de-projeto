package facens.arquiteturaweb.padroesdeprojeto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByTitleAndAuthorId(String title, Long authorId);
}
