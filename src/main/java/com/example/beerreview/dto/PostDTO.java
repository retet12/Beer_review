package com.example.beerreview.dto;

import com.example.beerreview.entity.User;
import com.example.beerreview.enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @NotNull
    private String title;

    @NotNull
    private PostType postType;

    @NotNull
    private String contents;
    private User user;
}
