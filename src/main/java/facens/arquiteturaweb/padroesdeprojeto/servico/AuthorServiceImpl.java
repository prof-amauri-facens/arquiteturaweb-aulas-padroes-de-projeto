package facens.arquiteturaweb.padroesdeprojeto.servico;

import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityAlreadyExistsException;
import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityNotFoundException;
import facens.arquiteturaweb.padroesdeprojeto.modelo.Author;
import facens.arquiteturaweb.padroesdeprojeto.repositorio.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author saveAuthor(Author author) {
        if (author.getId() != null) {
            throw new EntityAlreadyExistsException("Author", "id", author.getId().toString());
        }
        return authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long id) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author", id));

        if (!existingAuthor.getBooks().isEmpty()) {
            throw new IllegalStateException("Cannot delete author with associated books");
        }

        authorRepository.deleteById(id);
    }
}
