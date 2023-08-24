package com.likebookapp.model.binding;

import com.likebookapp.model.entity.MoodEnum;
import com.likebookapp.model.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostServiceModel {

    private Long id;
    private String content;
    private MoodEnum mood;
    private Set<User> userLikes;
}
