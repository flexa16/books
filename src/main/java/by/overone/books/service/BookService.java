package by.overone.books.service;

import by.overone.books.model.dto.BookDto;
import by.overone.books.model.entitity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void buyBook(Long id);

    void updateBook(BookDto dto);

    void addBook(BookDto dto);

    void removeBook(long id);
}
