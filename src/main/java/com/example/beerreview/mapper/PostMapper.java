package com.example.beerreview.mapper;


import com.example.beerreview.dto.PostDTO;
import com.example.beerreview.entity.Post;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PostMapper {
    Post postDTOToPost(PostDTO postDTO);
}