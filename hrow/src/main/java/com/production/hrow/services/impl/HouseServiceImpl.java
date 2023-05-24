package com.production.hrow.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.production.hrow.models.House;
import com.production.hrow.models.User;
import com.production.hrow.repos.HouseRepo;
import com.production.hrow.repos.PurchaseRepo;
import com.production.hrow.repos.UserRepo;
import com.production.hrow.services.HouseService;


@Service
public class HouseServiceImpl implements HouseService{

    @Autowired
    HouseRepo houseRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public House createHouseByAdmin(String username, House house) {
        User cur_user=userRepo.findByUsername(username);
        if(cur_user==null)
        return null;

        house.setUser(cur_user);
        house.setOwner(username);
        house.setStatus("available");
        return houseRepo.save(house);
    }

    @Override
    public House deleteHouseByAdmin(String username, Long id) {

    
        House cur_house = houseRepo.findById(id).orElse(null);

        House response = new House();

        if(!cur_house.getUser().getUsername().equals(username) || cur_house==null)
            {
                    response.setCity("Invalid Request");
            }
        else if(!cur_house.getStatus().equals("available"))
            {
                    response.setCity("It is already booked");
            }
        else{
                    response.setCity("Successful");
                    cur_house.setStatus("Unavailable");
                    houseRepo.save(cur_house);
            }
                
            return response;
        
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepo.findByStatusNotOrderByStatus("Unavailable");
    }

    @Override
    public List<House> getFreeHouses() {
        return houseRepo.findByStatus("available");
    }

    @Override
    public List<House> getHousesByAdmin(String username)
    {
        return houseRepo.findByOwnerAndStatusNotOrderByStatus(username,"unavailable");
    }

    @Override
    public List<House> getHousesByCity(String city) {
        return houseRepo.findByCityAndStatusNotOrderByStatus(city, "unavailable");
    }

    @Autowired
    PurchaseRepo purchaseRepo;

    @Override
    public House freeHouseByAdmin(String username, Long id) {
                House cur_house = houseRepo.findById(id).orElse(null);

                House response = new House();
                if(!cur_house.getUser().getUsername().equals(username) || cur_house==null ||cur_house.getStatus().equals("available"))
                {
                    response.setCity("Invalid Request");
                    return response;
                }
                else{
                    cur_house.setStatus("available");
                    purchaseRepo.deleteByHouseAndPstatus(cur_house, "booked");
                    return houseRepo.save(cur_house);
                }
        }

    @Override
    public House getOneHouse(Long id) {
        return houseRepo.findById(id).orElse(null);
    }
    
}
