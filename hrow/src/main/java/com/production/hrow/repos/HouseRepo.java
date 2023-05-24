package com.production.hrow.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.production.hrow.models.House;

@Component
public interface HouseRepo extends JpaRepository <House,Long>{
    
    public List<House> findByStatus(String status);
    public List<House> findByOwnerAndStatusNotOrderByStatus(String username, String status);
    public List<House> findByCityAndStatusNotOrderByStatus(String city, String status);
    
    public List<House>findByStatusNotOrderByStatus(String status);
}
