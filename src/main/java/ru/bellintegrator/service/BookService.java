package ru.bellintegrator.service;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.db.Tables;
import ru.bellintegrator.model.Book;
import ru.bellintegrator.model.mapper.MapperFacade;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    DSLContext dsl;

    public List<Book> getBooks() {
        return dsl
                .selectFrom(Tables.BOOKS)
                .fetch()
                .stream()
                .map(e -> mapperFacade.map(e, Book.class))
                .collect(Collectors.toList());
    }
}
