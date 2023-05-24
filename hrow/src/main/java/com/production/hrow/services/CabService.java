package com.production.hrow.services;

import java.util.List;

import com.production.hrow.models.Cab;

public interface CabService {

        public List<Cab> allCabRequests();
        public Cab bookCabByUser(Cab cab,String username);
        public List<Cab> bookedCabByUser(String username);
        public Cab deleteCab(Long id, String username);
        
        
        public Cab updateCabByAdmin(Long id, String username);
        public List<Cab> cabApprovalsByAdmin(String username);
}
