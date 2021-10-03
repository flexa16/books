package by.overone.books.controller;

import by.overone.books.model.dto.BookDto;
import by.overone.books.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final BookService bookService;

    @RequestMapping(method = RequestMethod.GET, path = "/books")
    public String booksListGet(Model model) {
        model.addAttribute("bookLists", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/books")
    public String booksListPost(Model model) {
        model.addAttribute("bookLists", bookService.getAllBooks());
        return "books";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addBook")
    public String addBookForm(Model model) {
        model.addAttribute("dto", new BookDto());
        return "addBook";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/updateBook")
    public String updateBookForm(Model model) {
        model.addAttribute("dto", new BookDto());
        return "updateBook";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addBook")
    public String addBookSubmit(@ModelAttribute("dto") BookDto dto) {
        bookService.addBook(dto);
        return "addBook";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/updateBook")
    public String updateBookSubmit(@ModelAttribute("dto") BookDto dto) {
        bookService.updateBook(dto);
        return "updateBook";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/buyBook/{id}")
    public String buyBookPost(@PathVariable("id") Long id) {
        bookService.buyBook(id);
        return "redirect:/books";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/delBook")
    public String delBookForm(Model model) {
        model.addAttribute("dto", new BookDto());
        return "delBook";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/delBook")
    public String delBookPost(@ModelAttribute("dto") BookDto dto) {
        try {
            bookService.removeBook(dto.getId());
        } catch (Exception e){
            log.info("Book with id {} not found", dto.getId());
        }
        return "redirect:/books";
    }
}
