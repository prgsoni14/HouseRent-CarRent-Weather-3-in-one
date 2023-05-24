package com.production.hrow.models;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 

    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    private User user;

    private String name;
    private String description;
    private String description2;
    private String status;
    private String city;
    private String location;
    private Long contactus;
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "house")
    private List<Image> images;

    @Column(nullable = false)
    private String owner;


    public House()
    {
        
    }
    

    public House(User user, String name, List<Image> images, String description, String description2, String status,
            String city, String location, Long contactus, BigDecimal price, String owner) {
        this.user = user;
        this.name = name;
        this.images = images;
        this.description = description;
        this.description2 = description2;
        this.status = status;
        this.city = city;
        this.location = location;
        this.contactus = contactus;
        this.price = price;
        this.owner = owner;
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public Long getContactus() {
        return contactus;
    }



    public void setContactus(Long contactus) {
        this.contactus = contactus;
    }

    
}
