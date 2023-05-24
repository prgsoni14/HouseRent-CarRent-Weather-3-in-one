package com.production.hrow.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.production.hrow.models.House;
import com.production.hrow.models.Purchase;
import com.production.hrow.models.User;

import jakarta.transaction.Transactional;

@Component
public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
    
    public List<Purchase> findByUser(User user);
    public List<Purchase> findByHouseOwnerAndPstatusNot(String username, String status);

    public Purchase findByUserAndHouse(User user, House house);

    @Transactional
    public void deleteByHouseAndPstatus(House house, String pstatus);
}
