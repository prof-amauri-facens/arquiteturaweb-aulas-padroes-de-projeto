package facens.arquiteturaweb.padroesdeprojeto.servico;

import facens.arquiteturaweb.padroesdeprojeto.modelo.Author;

import java.util.List;

public interface AuthorService {

    Author saveAuthor(Author author);

    List<Author> getAllAuthors();

    void deleteAuthor(Long id);


}
