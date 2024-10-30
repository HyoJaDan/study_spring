package com.ssafy.ws.step3.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String pass;
    private String recId;

 
    @Builder
    public User(String id, String name, String pass, String recId) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.recId = recId;
    }

  

}
