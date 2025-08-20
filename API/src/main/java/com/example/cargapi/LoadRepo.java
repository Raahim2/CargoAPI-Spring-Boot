package com.example.cargapi;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LoadRepo extends JpaRepository<Load, UUID> {
    
}
