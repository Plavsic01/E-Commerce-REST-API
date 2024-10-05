package com.plavsic.ecommerce.dto.book;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String title;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private String isbn;
    @NotNull
    private int pages;
    @NotNull
    private int yearOfPublication;
    @NotNull
    private int edition;
    @NotNull
    private int quantity;
    @NotNull
    private int price;
}
