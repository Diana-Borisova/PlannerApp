package com.example.musicdb.service;



import com.example.musicdb.model.dto.AddAlbumDto;
import com.example.musicdb.model.entity.Album;
import com.example.musicdb.model.entity.Artist;
import com.example.musicdb.model.entity.User;
import com.example.musicdb.repository.AlbumRepository;
import com.example.musicdb.repository.ArtistRepository;
import com.example.musicdb.repository.UserRepository;
import com.example.musicdb.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;




    public AlbumService(UserRepository userRepository, LoggedUser loggedUser, ArtistRepository artistRepository, AlbumRepository albumRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.artistRepository = artistRepository;

        this.albumRepository = albumRepository;
    }

    public boolean create(AddAlbumDto addAlbumDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Album album = new Album();
        album.setName(addAlbumDto.getName());
        album.setReleaseDate(addAlbumDto.getReleaseDate());
        album.setArtist(this.artistRepository.findByName(addAlbumDto.getArtistEnum()));
        album.setCopies(addAlbumDto.getCopies());
        album.setAddedFrom(user.get());
        album.setDescription(addAlbumDto.getDescription());
        album.setGenreEnum(addAlbumDto.getGenreEnum());
        album.setProducer(addAlbumDto.getProducer());
        album.setImageUrl(addAlbumDto.getImageUrl());
        album.setPrice(addAlbumDto.getPrice());
        this.albumRepository.save(album);
        return true;
    }

    public int getCountOfAllCopies(){

        return this.albumRepository.findTotalSumOfCopies();
    }
    

    public List<Album> getAllAlbums(){
        return new ArrayList<>(this.albumRepository.findAll());
    }

    public void removeAlbum(Long albumId) {
        this.albumRepository.deleteById(albumId);
    }
}
