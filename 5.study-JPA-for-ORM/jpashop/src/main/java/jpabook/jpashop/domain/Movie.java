package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String author;
    private String director;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
