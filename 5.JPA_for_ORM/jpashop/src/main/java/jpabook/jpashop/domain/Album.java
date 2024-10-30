package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item{
    public String Artist;
    public String stc;

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getStc() {
        return stc;
    }

    public void setStc(String stc) {
        this.stc = stc;
    }
}
