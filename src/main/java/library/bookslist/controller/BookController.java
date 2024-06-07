package library.bookslist.controller;

import library.bookslist.model.Book;
import library.bookslist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "bookslist";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "editbook";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/books";
    }

}
