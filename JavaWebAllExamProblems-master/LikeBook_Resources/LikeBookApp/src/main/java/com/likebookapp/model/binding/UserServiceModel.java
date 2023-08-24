package com.likebookapp.model.binding;

import com.likebookapp.model.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.cfg.SetBasicValueTypeSecondPass;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private Long id;
    private String username;

    private String password;

    private String email;

    private String confirmPassword;

    private Set<Post> likedPosts;
}
