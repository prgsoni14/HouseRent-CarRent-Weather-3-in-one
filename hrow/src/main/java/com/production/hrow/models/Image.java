package com.production.hrow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @JsonIgnore
    private String path;

    @ManyToOne()
    @JsonIgnore
    private House house;

    public Image()
    {

    }

    
    public Image(String name, String type, String path, House house) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.house = house;
    }




    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }




    public House getHouse() {
        return house;
    }




    public void setHouse(House house) {
        this.house = house;
    }

    

    
}
