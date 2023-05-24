package com.production.hrow.services;

import java.util.List;

import com.production.hrow.models.Purchase;

public interface PurchaseService {
    
    public Purchase makePurchaseByUser(Purchase purchase, String username, Long product_id);
    public List<Purchase> getPurchasesByUser(String username);
    public Purchase deletePurchase(Long id, String username);

    public Purchase updatePurchaseByAdmin(Long id, Long houseId, String username);
    public Purchase rejectPurchaseByAdmin(Long id, Long houseId, String username);
    
    public List<Purchase> purchasesForAdmin(String username);
}
