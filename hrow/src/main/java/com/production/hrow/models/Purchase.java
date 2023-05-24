package com.production.hrow.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Purchase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(nullable = false)
    private User user;

    @OneToOne()
    @JoinColumn(nullable = false)
    private House house;

    private Long contact;
    private String buyer;
    private String leaving;
    private String pstatus;
    private String houseOwner;

    public Purchase()
    {
        
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


    public House getHouse() {
        return house;
    }


    public void setHouse(House house) {
        this.house = house;
    }


    public Long getContact() {
        return contact;
    }


    public void setContact(Long contact) {
        this.contact = contact;
    }


    public String getBuyer() {
        return buyer;
    }


    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }


    public String getLeaving() {
        return leaving;
    }


    public void setLeaving(String leaving) {
        this.leaving = leaving;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }



    public String getHouseOwner() {
        return houseOwner;
    }



    public void setHouseOwner(String houseOwner) {
        this.houseOwner = houseOwner;
    }

    
    

}
