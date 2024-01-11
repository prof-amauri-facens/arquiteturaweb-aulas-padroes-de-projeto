package facens.arquiteturaweb.padroesdeprojeto;

import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityAlreadyExistsException;
import facens.arquiteturaweb.padroesdeprojeto.excecoes.EntityNotFoundException;
import facens.arquiteturaweb.padroesdeprojeto.modelo.Author;
import facens.arquiteturaweb.padroesdeprojeto.modelo.Book;
import facens.arquiteturaweb.padroesdeprojeto.modelo.Review;
import facens.arquiteturaweb.padroesdeprojeto.repositorio.BookRepository;
import facens.arquiteturaweb.padroesdeprojeto.repositorio.ReviewRepository;
import facens.arquiteturaweb.padroesdeprojeto.servico.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
// Isso deve sair ao final
@RestController
// Isso deve sair ao final
@RequestMapping("/api")
public class PadroesDeProjetoApplication {

	/*
	Quando você usa @Autowired em um campo, construtor ou método de configuração em uma classe gerenciada pelo Spring,
	o contêiner Spring procura automaticamente por um bean (instância de uma classe gerenciada pelo Spring) correspondente
	e o injeta no ponto especificado.

	Em outras palavras é uma outra forma de Injeção sem precisar do atributo final e do construtor
	 */


	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(PadroesDeProjetoApplication.class, args);
	}

	// Endpoints para Book
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		if (book.getId() != null) {
			throw new EntityAlreadyExistsException("Book", "id", book.getId().toString());
		}

		if (bookRepository.existsByTitleAndAuthorId(book.getTitle(), book.getAuthor().getId())) {
			throw new EntityAlreadyExistsException("Book", "title", book.getTitle());
		}

		return ResponseEntity.ok(bookRepository.save(book));
	}

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookRepository.findAll());
	}

	@DeleteMapping("/books/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		Book existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Book", id));

		bookRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	// Endpoints para Review
	@PostMapping("/reviews")
	public ResponseEntity<Review> addReview(@RequestBody Review review) {
		if (review.getId() != null) {
			throw new EntityAlreadyExistsException("Review", "id", review.getId().toString());
		}

		return ResponseEntity.ok(reviewRepository.save(review));
	}

	@GetMapping("/reviews")
	public ResponseEntity<List<Review>> getAllReviews() {
		return ResponseEntity.ok(reviewRepository.findAll());
	}

	@DeleteMapping("/reviews/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Review", id));

		reviewRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}

