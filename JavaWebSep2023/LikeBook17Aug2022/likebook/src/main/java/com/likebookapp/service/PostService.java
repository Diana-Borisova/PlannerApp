package com.likebookapp.service;

import com.likebookapp.model.dto.AddPostDto;
import com.likebookapp.model.entity.Post;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final MoodRepository moodRepository;
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    @Autowired
    public PostService(PostRepository postRepository, MoodRepository moodRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.postRepository = postRepository;
        this.moodRepository = moodRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }




    public boolean create(AddPostDto addPostDto) {
        Optional<User> user = this.userRepository.findById(this.loggedUser.getId());
            Post post = new Post();
            post.setMood(this.moodRepository.findByName(addPostDto.getName()));
            post.setContent(addPostDto.getContent());
            post.setUser(user.get());
            this.postRepository.save(post);
            return true;
        }


    public void removePost(Long use, Long postId) {
        Post post = this.postRepository.findById(postId).orElseThrow();
        this.postRepository.delete(post);
    }

    public void addLike(Long postId) {
        User user = this.userRepository.findById(loggedUser.getId()).orElseThrow();

        Optional<Post> post = this.postRepository.findById(postId);

        if (post.isPresent() && !post.get().getUserLikes().contains(user)){
           if(post.get().getUserLikes().size() == 0){
               post.get().setUserLikes(user);
           }
            this.postRepository.save(post.get());
        }
    }
}
