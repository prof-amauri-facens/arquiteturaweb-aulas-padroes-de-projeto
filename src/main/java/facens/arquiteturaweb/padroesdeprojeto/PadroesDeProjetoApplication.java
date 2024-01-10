package facens.arquiteturaweb.padroesdeprojeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@Validated
public class PadroesDeProjetoApplication {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(PadroesDeProjetoApplication.class, args);
	}

	// Endpoints para Author
	@PostMapping("/authors")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
		if (author.getId() != null) {
			throw new EntityAlreadyExistsException("Author", "id", author.getId().toString());
		}
		return ResponseEntity.ok(authorRepository.save(author));
	}

	@GetMapping("/authors")
	public ResponseEntity<List<Author>> getAllAuthors() {
		return ResponseEntity.ok(authorRepository.findAll());
	}

	@DeleteMapping("/authors/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
		Author existingAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Author", id));

		if (!existingAuthor.getBooks().isEmpty()) {
			throw new IllegalStateException("Cannot delete author with associated books");
		}

		authorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
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

