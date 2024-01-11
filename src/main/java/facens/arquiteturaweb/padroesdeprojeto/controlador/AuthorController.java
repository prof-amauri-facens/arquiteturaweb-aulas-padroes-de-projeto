package facens.arquiteturaweb.padroesdeprojeto.controlador;

import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityAlreadyExistsException;
import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityNotFoundException;
import facens.arquiteturaweb.padroesdeprojeto.modelo.Author;
import facens.arquiteturaweb.padroesdeprojeto.servico.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        try{
            return ResponseEntity.ok(authorService.saveAuthor(author));
        }catch (EntityAlreadyExistsException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(author);
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try{
            authorService.deleteAuthor(id);
            return ResponseEntity.ok("Item deletetado com sucesso");
        }catch(EntityNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author não encontrado para deleção");
        }
    }
}
