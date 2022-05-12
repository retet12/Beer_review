package com.example.beerreview.entity;

import com.example.beerreview.enums.PostType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POSTS")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String title;

    @NotNull
    private LocalDateTime createDate;

    @NotNull
    private long numberOfReads;

    @NotNull
    private PostType postType;

    @NotNull
    private String contents;

    @ManyToOne
    private Rating rating;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Tag> tags;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Category> categories;
}