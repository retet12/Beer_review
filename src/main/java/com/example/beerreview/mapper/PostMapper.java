package com.example.beerreview.mapper;


import com.example.beerreview.dto.PostDTO;
import com.example.beerreview.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDTO postToPostDTO(Post post);
    Post postDTOToPost(PostDTO postDTO);
}