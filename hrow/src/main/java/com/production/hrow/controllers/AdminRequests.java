package com.production.hrow.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.hrow.models.Cab;
import com.production.hrow.models.Purchase;
import com.production.hrow.services.CabService;
import com.production.hrow.services.PurchaseService;
import com.production.hrow.services.impl.ImageServiceImpl;

@RestController
@RequestMapping("adminr")
@CrossOrigin("*")
public class AdminRequests {
    
    @Autowired
    CabService cabService;

    @GetMapping("/cab/requests/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public List<Cab> getCabRequest(@PathVariable("username") String username)
    {
        return cabService.allCabRequests();
    }

    @PutMapping("/cab/approve/{username}/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public Cab updateCab(@PathVariable("id") Long id, @PathVariable("username") String username)
    {
        return cabService.updateCabByAdmin(id, username);
    }

    @GetMapping("/cab/approvals/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public List<Cab> getApprovalsByMe(@PathVariable("username") String username)
    {
        return cabService.cabApprovalsByAdmin(username);
    }


    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ImageServiceImpl imageService;

    @GetMapping("/purchase/purchases/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> purchasesForAdmin(@PathVariable("username") String username)
    {
        Map<String,Object>response = new HashMap<>();
        List<Purchase> purchases = purchaseService.purchasesForAdmin(username);
        response.put("purchases", purchases);

        List<byte[]>imagesBytes = new ArrayList<>();
        for(Purchase purchase:purchases)
        {
            imagesBytes.add(imageService.downloadImage(purchase.getHouse().getImages().get(0).getId()));
        }
        response.put("imagesBytes", imagesBytes);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/purchase/update/{username}/{id}/{houseId}")
    @PreAuthorize("#username == authentication.principal.username")
    public Purchase updatePurchase(@PathVariable("id") Long id, @PathVariable("username") String username, @PathVariable("houseId") Long houseId)
    {
        return purchaseService.updatePurchaseByAdmin(id, houseId, username);
    }

    @PutMapping("/purchase/reject/{username}/{id}/{houseId}")
    @PreAuthorize("#username == authentication.principal.username")
    public Purchase rejectPurchase(@PathVariable("id") Long id, @PathVariable("username") String username, @PathVariable("houseId") Long houseId)
    {
        return purchaseService.rejectPurchaseByAdmin(id, houseId, username);
    }

    
}
