package info.lifeify.book.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.lifeify.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
}
