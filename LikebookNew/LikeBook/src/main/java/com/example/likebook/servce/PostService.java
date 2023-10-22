package com.example.likebook.servce;


import com.example.likebook.model.dto.AddPostDto;
import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.Post;
import com.example.likebook.model.entity.User;
import com.example.likebook.repository.MoodRepository;
import com.example.likebook.repository.PostRepository;
import com.example.likebook.repository.UserRepository;
import com.example.likebook.util.LoggedUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PostService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final MoodRepository moodRepository;
    private final PostRepository postRepository;



    public PostService(UserRepository userRepository, LoggedUser loggedUser, MoodRepository moodRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.moodRepository = moodRepository;
        this.postRepository = postRepository;

    }

    public boolean create(AddPostDto addPostDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
        Mood mood = this.moodRepository.findByName(addPostDto.getMoodEnum()).orElseThrow();
        Post post = new Post();
        post.setContent(addPostDto.getContent());
        post.setCreator(user.get());
        post.setMood(mood);
        this.postRepository.save(post);

        return true;
    }

    public List<Post> getMyPosts(){
        Optional<List<Post>> myPosts = this.postRepository.findAllByCreator_Id(loggedUser.getId());

        return myPosts.get();

    }

    public List<Post> getOtherPosts(){
        Optional<List<Post>> otherPosts = this.postRepository.findAllByCreator_IdNot(loggedUser.getId());
        return otherPosts.get();

    }

    public void addLike(Long id) {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();
        Optional<Post> post = this.postRepository.findById(id);
        if (post.isPresent() && post.get().getUserLikes().size() == 0) {
            post.get().getUserLikes().add(user);
            this.postRepository.save(post.get());
        }

    }

    public void removePost(Long id) {
        this.postRepository.deleteById(id);
    }

}
