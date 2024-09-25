package com.example.BookMyShow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass // by defn this all basemodel attribute
// which are extend in its child class create table in child class aswell
public class BaseModel {
//    Basemodel class will contain the attribute which
//    are commmon to all attribute

    @Id
    //creates auto-generated id 12345
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //primary Key

    private Date createdAt;

    private Date modifiedAt;
}
