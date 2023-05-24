package com.production.hrow.models;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String username;
    private String address;
    private Long mobile;
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    private Image profilePic;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Purchase> purchases;
    
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<House> houses;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonIgnore
    private List<Cab> cabs;

    public User(){

    }
    


    public User(String name, String email, String password, String username, String address, Long mobile, String role,
            Image profilePic, List<Purchase> purchases, List<House> houses, List<Cab> cabs) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.address = address;
        this.mobile = mobile;
        this.role = role;
        this.profilePic = profilePic;
        this.purchases = purchases;
        this.houses = houses;
        this.cabs = cabs;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public List<Cab> getCabs() {
        return cabs;
    }

    public void setCabs(List<Cab> cabs) {
        this.cabs = cabs;
    }



    public Image getProfilePic() {
        return profilePic;
    }



    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }


    
    

    
}
