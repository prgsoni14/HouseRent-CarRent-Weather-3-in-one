package com.production.hrow.controllers;

import java.util.List;

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

import com.production.hrow.models.Cab;
import com.production.hrow.services.CabService;

@RestController
@RequestMapping("cab")
@CrossOrigin("*")
public class CabController {

    @Autowired
    CabService cabService;

    @PostMapping("/create/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Cab> createCab(@RequestBody Cab cab, @PathVariable("username") String username)
    {
        return ResponseEntity.ok().body(cabService.bookCabByUser(cab, username));
    }


    @GetMapping("/bookings/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<List<Cab>> cabRequestsByUser(@PathVariable("username") String username)
    {
        return ResponseEntity.ok().body(cabService.bookedCabByUser(username));
    }


    @DeleteMapping("/delete/{username}/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<Cab> deleteCab(@PathVariable("username") String username, @PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(cabService.deleteCab(id, username));
    }


}
