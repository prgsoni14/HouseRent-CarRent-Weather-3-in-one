package com.production.hrow.services;

import java.util.List;

import com.production.hrow.models.House;

public interface HouseService {
    
    public House createHouseByAdmin(String username, House house);
    public House deleteHouseByAdmin(String username, Long id);
    public House freeHouseByAdmin(String username, Long id);
    

    public List<House> getAllHouses();
    public List<House> getFreeHouses();
    public List<House> getHousesByCity(String city);
    public House getOneHouse(Long id);

    public List<House> getHousesByAdmin(String username);
}
