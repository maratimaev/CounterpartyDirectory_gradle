package ru.bellintegrator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.model.Book;
import ru.bellintegrator.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    BookService bookService;

    @GetMapping(value ="/books", produces = "application/json")
    @ResponseBody
    public List<Book> getBooks() {
        return this.bookService.getBooks();
    }

}
