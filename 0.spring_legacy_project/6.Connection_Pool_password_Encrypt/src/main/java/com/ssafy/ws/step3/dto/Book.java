package com.ssafy.ws.step3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int price;
    private String desc;
    private String img;
 
    @Builder
    public Book(String isbn, String title, String author, int price, String desc, String img) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
        this.desc = desc;
        this.img = img;
    }

  

}
