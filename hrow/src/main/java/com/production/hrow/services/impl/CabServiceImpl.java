package com.production.hrow.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.hrow.models.Cab;
import com.production.hrow.models.User;
import com.production.hrow.repos.CabRepo;
import com.production.hrow.repos.UserRepo;
import com.production.hrow.services.CabService;

@Service
public class CabServiceImpl implements CabService {

    @Autowired
    CabRepo cabRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public List<Cab> allCabRequests() {
        return cabRepo.findByStatus("requested");
    }

    @Override
    public Cab bookCabByUser(Cab cab, String username) {
        User cur_user=userRepo.findByUsername(username);
        if(cur_user==null)
        return null;
      
        cab.setUser(cur_user);
        return cabRepo.save(cab);
    }

    @Override
    public List<Cab> bookedCabByUser(String username) {
        User cur_user=userRepo.findByUsername(username);
        if(cur_user==null)
        return null;

        return cabRepo.findByUser(cur_user);
    }

    @Override
    public Cab deleteCab(Long id, String username) {
        Cab response = new Cab();
        try {
            Cab cab=cabRepo.findById(id).orElse(null);
            if(cab==null)
            {
                response.setFromPlace("Invalid Id");
            }
            else if(!cab.getStatus().equals("requested"))
            {        
                response.setFromPlace("Already Approved, Please Contact Admin");
            }
            else if(username.equals(cab.getUser().getUsername()))
            {        
                cabRepo.deleteById(id);
                response.setFromPlace("Successfully deleted");
            }
            else
            {
                response.setFromPlace("You are not authorized");
            }
            return response;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Cab updateCabByAdmin(Long id, String username) {

        Cab cab = cabRepo.findById(id).orElse(null);
        User admin = userRepo.findByUsername(username);
        if(cab==null || admin==null)
            return null;
        else{
            cab.setApprover(username);
            cab.setStatus("Approved by " + username + " || Contact : " + admin.getMobile());
            return cabRepo.save(cab);
        }
    }

    @Override
    public List<Cab> cabApprovalsByAdmin(String username) {
        return cabRepo.findByApprover(username);
    }
    
}
