package com.plavsic.ecommerce.controller.book;

import com.plavsic.ecommerce.generic.controller.GenericController;
import com.plavsic.ecommerce.dto.book.BookDTO;
import com.plavsic.ecommerce.model.book.Book;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/api/books")
public class BookController extends GenericController<BookDTO, Book> {

    @Autowired
    public BookController(AbstractService<BookDTO, Book> service) {
        super(service);
    }
}
