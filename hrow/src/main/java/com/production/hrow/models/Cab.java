package com.production.hrow.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private String fromPlace;
    private String toPlace;
    private String departureDate;
    private String arrivalDate;
    private String bookingDate;
    private String carType;
    private Long contact;
    private BigDecimal cost;
    private BigDecimal distance;
    private String status;
    private String approver;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(nullable = false)
    private User user;

    public Cab()
    {

    }


    public Cab(String fromPlace, String toPlace, String departureDate, String arrivalDate, String bookingDate,
            String carType, Long contact, BigDecimal cost, BigDecimal distance, String status, String approver,
            User user) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.bookingDate = bookingDate;
        this.carType = carType;
        this.contact = contact;
        this.cost = cost;
        this.distance = distance;
        this.status = status;
        this.approver = approver;
        this.user = user;
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }




    public Long getContact() {
        return contact;
    }




    public void setContact(Long contact) {
        this.contact = contact;
    }





    public String getBookingDate() {
        return bookingDate;
    }





    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }



    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }




    public String getApprover() {
        return approver;
    }




    public void setApprover(String approver) {
        this.approver = approver;
    } 
}
