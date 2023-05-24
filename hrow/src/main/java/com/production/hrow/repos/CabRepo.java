package com.production.hrow.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.production.hrow.models.Cab;
import com.production.hrow.models.User;

@Component
public interface CabRepo extends JpaRepository<Cab, Long>{
    
    public List<Cab> findByUser(User user);
    public List<Cab> findByStatus(String status);
    public List<Cab> findByApprover(String username);
}
