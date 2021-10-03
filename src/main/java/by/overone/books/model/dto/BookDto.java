package by.overone.books.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookDto {
    private long id;
    private String title;
    private String author;
    private int pages;
    private double price;
    private int count;
}
