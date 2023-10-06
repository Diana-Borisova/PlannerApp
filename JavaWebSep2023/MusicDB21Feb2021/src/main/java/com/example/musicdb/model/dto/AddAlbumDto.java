package com.example.musicdb.model.dto;

import com.example.musicdb.model.entity.ArtistEnum;
import com.example.musicdb.model.entity.GenreEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddAlbumDto {

    @NotNull
    @Size(min= 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String name;

    @NotNull
    @Size(min= 5, message = "Image url must be minimum 5 characters")
    private String imageUrl;

    @NotNull
    @Size(min= 5, message = "Description length must be minimum 5 characters")
    private String description;

    @NotNull
    @Min(value = 10, message = "Copies must be more than 10")
    private int copies;

    @NotNull
    @Positive(message = "Price must be positive number")
    private BigDecimal price;


    private String producer;

    @NotNull
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotNull(message = "You must select artist")
    private ArtistEnum artistEnum;

    @NotNull(message = "You must select genre")
    private GenreEnum genreEnum;

    public AddAlbumDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArtistEnum getArtistEnum() {
        return artistEnum;
    }

    public void setArtistEnum(ArtistEnum artistEnum) {
        this.artistEnum = artistEnum;
    }

    public GenreEnum getGenreEnum() {
        return genreEnum;
    }

    public void setGenreEnum(GenreEnum genreEnum) {
        this.genreEnum = genreEnum;
    }


}
