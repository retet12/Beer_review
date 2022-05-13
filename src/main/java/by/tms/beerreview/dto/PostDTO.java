package by.tms.beerreview.dto;

import by.tms.beerreview.entity.Rating;
import by.tms.beerreview.entity.User;
import by.tms.beerreview.enums.PostType;
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
    private Rating rating;

    @NotNull
    private String contents;
    private User user;
}
