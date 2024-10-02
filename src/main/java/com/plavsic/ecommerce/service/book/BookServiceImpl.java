package com.plavsic.ecommerce.service.book;

import com.plavsic.ecommerce.dto.book.BookDTO;
import com.plavsic.ecommerce.model.book.Book;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import com.plavsic.ecommerce.generic.service.AbstractService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends AbstractService<BookDTO, Book> {

    @Autowired
    public BookServiceImpl(ModelMapper modelMapper, GenericRepository<Book> repository) {
        super(modelMapper, repository,BookDTO.class,Book.class);
    }
}
