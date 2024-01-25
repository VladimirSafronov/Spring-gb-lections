package ru.safronov.mySpringProject.Library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.safronov.mySpringProject.Library.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Book findBookById(Long id);

}
