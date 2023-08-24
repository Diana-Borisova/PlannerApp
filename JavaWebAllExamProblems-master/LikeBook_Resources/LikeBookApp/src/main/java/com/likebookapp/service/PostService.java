package com.likebookapp.service;


import com.likebookapp.model.binding.PostServiceModel;
import com.likebookapp.model.binding.UserServiceModel;
import com.likebookapp.model.entity.Post;
import com.likebookapp.repository.MoodRepository;
import com.likebookapp.repository.PostRepository;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MoodRepository moodRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    public PostService(PostRepository postRepository, UserRepository userRepository, MoodRepository moodRepository, LoggedUser loggedUser, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.moodRepository = moodRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
    }

    public void addPost(PostServiceModel postServiceModel) {
        Post post = modelMapper.map(postServiceModel, Post.class);
        Optional<UserServiceModel> user = Optional.ofNullable(modelMapper.map(this.userRepository.findById(loggedUser.getId()), UserServiceModel.class));
        post.setContent(post.getContent());
        post.setMood(this.moodRepository.findMoodByName(postServiceModel.getMood()));
        post.setCreator(post.getCreator());
        this.postRepository.save(post);

        List<Post> myPosts = this.postRepository.findAllByUserId(loggedUser.getId()).stream().collect(Collectors.toList());
        myPosts.add(post);
    }

    public List<Post> getMyPosts() {
return this.postRepository.findAllByUserId(loggedUser.getId());
    }
}