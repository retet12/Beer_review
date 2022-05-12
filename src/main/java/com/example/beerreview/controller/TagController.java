package com.example.beerreview.controller;

import com.example.beerreview.entity.Tag;
import com.example.beerreview.exception.InvalidException;
import com.example.beerreview.exception.NotFoundException;
import com.example.beerreview.repository.TagRepository;
import io.swagger.annotations.*;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "Tag", description = "Access to tag")
@RequestMapping("/api/v1/tag")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Tag not found")
    @ApiResponse(responseCode = "405", description = "Invalid input")
    @ApiOperation(value = "Save tag", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @PostMapping
    public ResponseEntity<Tag> save(@Valid @RequestBody Tag tag, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new InvalidException();
        }
        if (tagRepository.findByName(tag.getName()).isPresent()){
            throw new NotFoundException();
        }

        Tag save = tagRepository.save(tag);
        return ResponseEntity.ok(save);
    }

    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Tag not found")
    @ApiOperation(value = "Delete tag", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @DeleteMapping(produces = "application/json")
    public void delete(@RequestBody Tag tag){
        if (tagRepository.findByName(tag.getName()).isEmpty()){
            throw  new NotFoundException();
        }

        tagRepository.delete(tag);
    }
}