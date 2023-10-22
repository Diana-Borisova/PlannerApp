package com.reseller.service;


import com.reseller.model.dto.AddOfferDto;
import com.reseller.model.entity.Condition;
import com.reseller.model.entity.Offer;
import com.reseller.model.entity.User;
import com.reseller.repository.ConditionRepository;
import com.reseller.repository.OfferRepository;
import com.reseller.repository.UserRepository;
import com.reseller.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ConditionRepository conditionRepository;
    private final OfferRepository offerRepository;



    public OfferService(UserRepository userRepository, LoggedUser loggedUser, OfferRepository offerRepository, ConditionRepository conditionRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.offerRepository = offerRepository;
        this.conditionRepository = conditionRepository;

    }

    public boolean create(AddOfferDto addOfferDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Condition condition = this.conditionRepository.findByConditionEnum(addOfferDto.getConditionEnum()).orElseThrow();
       Offer offer = new Offer();
       offer.setCondition(condition);
       offer.setDescription(addOfferDto.getDescription());
       offer.setPrice(addOfferDto.getPrice());
       offer.setCreatedBy(user.get());
      // offer.setBoughtBy(null);
        user.get().addOfferToLoggedUser(offer);

        this.offerRepository.save(offer);

        return true;
    }

    public List<Offer> getAllOtherOffers()  {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        Optional<List<Offer>> otherOffers = this.offerRepository.findAllByCreatedByIdNot(user.getId());

        return otherOffers.get();
   }

    public List<Offer> getAllOffers() {
        return new ArrayList<>(this.offerRepository.findAll());
    }
public List<Offer> getAllMyOffers(){
        return this.offerRepository.findAllByUserId(loggedUser.getId()).stream().collect(Collectors.toList());

    }

    public void removeOffer(Long offerId) {
       Offer offer = this.offerRepository.findById(offerId).orElseThrow();
       this.offerRepository.delete(offer);

    }

    public void buyOffer(Long offerId) {
        Offer offer = this.offerRepository.findById(offerId).orElseThrow();
        User user= this.userRepository.findById(loggedUser.getId()).get();
       offer.setCreatedBy(null);
       offer.setBoughtBy(user);
       user.getBoughtOffers().add(offer);
       user.getUserOffers().remove(offer);

       this.offerRepository.save(offer);

    }


    public List<Offer> getBoughtOffers(){
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();

        return this.offerRepository.findAllBoughtOffers(loggedUser.getId());

    }
    public List<Offer> getMyOffers(){
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();

        return this.offerRepository.findAllByUserId(user.getId()).stream().collect(Collectors.toList());

    }

//    public void removeAlbum(Long albumId) {
//        this.albumRepository.deleteById(albumId);
//    }
}
