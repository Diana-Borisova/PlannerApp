package com.reseller.repository;


import com.reseller.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query("select o, u from Offer o join o.createdBy u on u.id = :id")
List<Offer> findAllByUserId(Long id);

    @Query("select o, u from Offer o join o.boughtBy u on u.id != :id")
    List<Offer> findAllByUserIdNot(Long id);

    @Query("select o, u from Offer o join o.boughtBy u on u.id = :id")
    List<Offer> findAllBoughtOffers(Long id);

    @Query("select o, u from Offer o join o.createdBy u on u.id != :id")
    Optional<List<Offer>> findAllByCreatedByIdNot(Long id);
}
