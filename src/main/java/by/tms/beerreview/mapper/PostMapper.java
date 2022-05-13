package by.tms.beerreview.mapper;


import by.tms.beerreview.entity.Post;
import by.tms.beerreview.dto.PostDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDTO postDTO);
}