package com.production.hrow.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.hrow.models.House;
import com.production.hrow.models.Purchase;
import com.production.hrow.models.User;
import com.production.hrow.repos.HouseRepo;
import com.production.hrow.repos.PurchaseRepo;
import com.production.hrow.repos.UserRepo;
import com.production.hrow.services.HouseService;
import com.production.hrow.services.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PurchaseRepo purchaseRepo;

    @Autowired
    HouseRepo houseRepo;

    @Override
    public Purchase makePurchaseByUser(Purchase purchase, String username, Long houseId) {

        House cur_house=houseRepo.findById(houseId).orElse(null);
        User cur_user=userRepo.findByUsername(username);

        Purchase response = new Purchase();
        Purchase thisUserThisHouse = purchaseRepo.findByUserAndHouse(cur_user, cur_house);
        if(cur_house==null || !cur_house.getStatus().equals("available"))
        {
            response.setBuyer("Invalid request");
            return response;
        }
        else if(thisUserThisHouse!=null)
        {
            response.setBuyer("Already Requested");
            return response;
        }
        else
        {
            purchase.setUser(cur_user);
            purchase.setHouse(cur_house);
            purchase.setBuyer(username);
            purchase.setPstatus("requested");
            purchase.setHouseOwner(cur_house.getUser().getUsername());
            return purchaseRepo.save(purchase);
        }
    }

    @Override
    public List<Purchase> getPurchasesByUser(String username) {
        return purchaseRepo.findByUser(userRepo.findByUsername(username));
    }

    @Override
    public Purchase deletePurchase(Long id, String username) {

        Purchase cur_purchase = purchaseRepo.findById(id).orElse(null);
        Purchase response = new Purchase();
        if(cur_purchase==null || !cur_purchase.getBuyer().equals(username))
        {
            response.setBuyer("Invalid request");
        }
        else if(cur_purchase.getPstatus().equals("requested") || cur_purchase.getPstatus().equals("rejected"))
        {
            purchaseRepo.deleteById(id);
            response.setBuyer("Successful");
        }
        else
        {
            response.setBuyer("already booked");
        }
            
        return response;
    }

    @Autowired
    HouseService houseService;


    @Override
    public List<Purchase> purchasesForAdmin(String username)
    {
       return  purchaseRepo.findByHouseOwnerAndPstatusNot(username,"rejected");
    }

    @Override
    public Purchase updatePurchaseByAdmin(Long id, Long houseId, String username) {

        Purchase cur_purchase = purchaseRepo.findById(id).orElse(null);
        House cur_house = houseRepo.findById(houseId).orElse(null);

        Purchase response = new Purchase();
        if(cur_house ==null || cur_purchase==null || !cur_house.getOwner().equals(username) || !cur_purchase.getPstatus().equals("requested") || !cur_house.getStatus().equals("available"))
        {
            response.setBuyer("Invalid request");
        }
        else
        {
            cur_purchase.setPstatus("booked");
            cur_house.setStatus("Booked");
            houseRepo.save(cur_house);
            purchaseRepo.save(cur_purchase);
            response.setBuyer("Successful");
        }
        return response;
    }

    @Override
    public Purchase rejectPurchaseByAdmin(Long id, Long houseId, String username) {

        Purchase cur_purchase = purchaseRepo.findById(id).orElse(null);
        House cur_house = houseRepo.findById(houseId).orElse(null);

        Purchase response = new Purchase();
        if(cur_house ==null || cur_purchase==null || !cur_house.getOwner().equals(username) || !cur_purchase.getPstatus().equals("requested"))
        {
            response.setBuyer("Invalid request");
        }
        else
        {
            cur_purchase.setPstatus("rejected");
            purchaseRepo.save(cur_purchase);
            response.setBuyer("Successful");
        }
        return response;
    }
    
}
