package by.overone.books.service.impl;

import by.overone.books.model.dto.BookDto;
import by.overone.books.model.entitity.Book;
import by.overone.books.repository.BookRepository;
import by.overone.books.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return StreamSupport
                .stream(bookRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void buyBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            log.error("Book with id {} not found!", id);
            return;
        }
        Book storedBook = book.get();
        int bookCount = storedBook.getCount();
        if (bookCount >= 1) {
            storedBook.setCount(storedBook.getCount() - 1);
            bookRepository.save(storedBook);
            return;
        }
        log.error("Book with id {} out of stock!", id);
    }

    @Override
    public void updateBook(BookDto dto) {
        Optional<Book> book = bookRepository.findById(dto.getId());
        if (book.isEmpty()) {
            log.error("Book with id {} not found!", dto.getId());
            return;
        }
        Book storedBook = book.get();
        storedBook.setAuthor(dto.getAuthor());
        storedBook.setTitle(dto.getTitle());
        storedBook.setPages(dto.getPages());
        storedBook.setPrice(dto.getPrice());
        storedBook.setCount(dto.getCount());
        bookRepository.save(storedBook);
        log.info("Book with id {} updated!", dto.getId());
    }

    @Override
    public void addBook(BookDto dto) {
        Book book = new Book();
        book.setCount(dto.getCount());
        book.setPages(dto.getPages());
        book.setPrice(dto.getPrice());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        bookRepository.save(book);
        log.info("Book with id {} added!", dto.getId());
    }

    @Override
    public void removeBook(long id) {
        bookRepository.deleteById(id);
        log.info("Book with id {} removed!", id);
    }
}
