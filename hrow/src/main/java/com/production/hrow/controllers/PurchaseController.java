package com.production.hrow.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.production.hrow.models.Purchase;
import com.production.hrow.repos.PurchaseRepo;
import com.production.hrow.services.PurchaseService;
import com.production.hrow.services.impl.ImageServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    PurchaseRepo purchaseRepo;

    @Autowired
    PurchaseService purchaseService;

    @Autowired
    ImageServiceImpl imageService;

    @PostMapping("/makepurchase/{username}/{houseId}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> makePurchase(@RequestBody Purchase purchase, @PathVariable("username") String username, @PathVariable("houseId") Long houseId)
    {
        return ResponseEntity.ok().body(purchaseService.makePurchaseByUser(purchase, username, houseId));
    }

    @DeleteMapping("/delete/{username}/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Purchase> deletePurchase(@PathVariable("username") String username,@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(purchaseService.deletePurchase(id, username));
    }

    @GetMapping("/purchases/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> purchasesByUser(@PathVariable("username") String username)
    {
        Map<String,Object>response = new HashMap<>();
        List<Purchase> purchases = purchaseService.getPurchasesByUser(username);
        response.put("purchases", purchases);

        List<byte[]>imagesBytes= new ArrayList<>();
        for(Purchase purchase:purchases)
        {
            imagesBytes.add(imageService.downloadImage(purchase.getHouse().getImages().get(0).getId()));
        }
        response.put("imagesBytes", imagesBytes);
        return ResponseEntity.ok().body(response);
    }

}
