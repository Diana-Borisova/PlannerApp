package com.example.battleships.repository;

import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.entity.User;
import jakarta.persistence.SecondaryTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {

    Set<Ship> findAllByUserId(Long userId);

    @Query("SELECT s FROM Ship s JOIN s.user u WHERE u.id <> :userId")
    Set<Ship> findAllByUserIdNot(@Param("userId") Long userId);
}
