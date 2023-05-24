package com.production.hrow.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.production.hrow.models.Image;

@Component
public interface ImageRepo extends JpaRepository<Image,Long> {
    
}
