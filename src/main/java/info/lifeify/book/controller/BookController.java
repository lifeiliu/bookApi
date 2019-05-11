package info.lifeify.book.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import info.lifeify.book.exceptions.ResourceNotFoundException;
import info.lifeify.book.model.Book;
import info.lifeify.book.repos.BookRepository;

@RestController
@RequestMapping("/bookapi")
public class BookController {
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping("/books")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/books/{id}")
	public Book getById(@PathVariable(value = "id") Long id) {
		return bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book", "id",id));
	}
	
	@PostMapping("/books/{id}")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	@PutMapping("/books/{id}")
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		Book toUpdateBook = bookRepository.getOne(id);
		toUpdateBook.setTitle(book.getTitle());
		toUpdateBook.setAuthor(book.getAuthor());
		toUpdateBook.setPrice(book.getPrice());
		
		Book updatedBook = bookRepository.save(toUpdateBook);
		return updatedBook;
	}
	
	@DeleteMapping("/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
		Book toDelete = bookRepository.getOne(id);
		bookRepository.delete(toDelete);
		return ResponseEntity.ok().build();
	}
}
