package com.plavsic.ecommerce.repository.book;

import com.plavsic.ecommerce.model.book.Book;
import com.plavsic.ecommerce.generic.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends GenericRepository<Book> {

}
