package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @JsonIgnore
    @ManyToOne
    private Rating rating;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Tag> tags;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Category> categories;
}