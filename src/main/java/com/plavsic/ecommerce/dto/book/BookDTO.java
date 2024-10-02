package com.plavsic.ecommerce.dto.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int pages;
    private int yearOfPublication;
    private int edition;
}
