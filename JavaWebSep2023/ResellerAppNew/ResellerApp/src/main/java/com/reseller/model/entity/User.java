package com.reseller.model.entity;


import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_offers",
            joinColumns = @JoinColumn(name = "createdBy_id"),
            inverseJoinColumns = @JoinColumn(name = "offer_id"))
    private List<Offer> userOffers;

    @OneToMany(mappedBy = "boughtBy")
    @Transient
    private List<Offer> boughtOffers;

    public User() {
        this.userOffers = new ArrayList<>();
        this.boughtOffers = new ArrayList<>();
    }



    public void addOfferToLoggedUser(Offer offer) {
        this.userOffers.add(offer);
    }

    public void removeOfferFromLoggedUser(Offer offer) {
        this.userOffers.remove(offer);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Offer> getUserOffers() {
        return userOffers;
    }

    public void setUserOffers(List<Offer> userOffers) {
        this.userOffers = userOffers;
    }

    public List<Offer> getBoughtOffers() {
        return boughtOffers;
    }

    public void setBoughtOffers(List<Offer> boughtOffers) {
        this.boughtOffers = boughtOffers;
    }
}
