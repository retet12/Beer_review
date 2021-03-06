package by.tms.beerreview.controller;

import by.tms.beerreview.entity.Category;
import by.tms.beerreview.exception.ExistsException;
import by.tms.beerreview.exception.InvalidException;
import by.tms.beerreview.exception.NotFoundException;
import by.tms.beerreview.repository.CategoryRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@PropertySource("classpath:messages.properties")
@RestController
@Api(tags = "Category", description = "Operations with category")
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Already exists")
    })
    @ApiOperation(value = "Create category", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @PostMapping(produces = "application/json")
    public ResponseEntity<Category> save(@Valid @ApiParam(value = "Create category object")
                                         @RequestBody Category category,
                                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new InvalidException();
        }

        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new ExistsException();
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ApiOperation(value = "Get all categories", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Get category by ID", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> getById(@PathVariable("id") Long id) {
        if (id < 1) {
            throw new InvalidException();
        }

        if (categoryRepository.findById(id).isEmpty()){
            throw new NotFoundException();
        }
        Category category = categoryRepository.findById(id).get();

        return ResponseEntity.ok(category);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Delete category by ID", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        if (id < 1) {
            throw new InvalidException();
        }

        if (categoryRepository.findById(id).isEmpty()){
            throw new NotFoundException();
        }
        categoryRepository.deleteById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @ApiOperation(value = "Update category by ID", notes = "This can only be done by the logged in user", authorizations = { @Authorization(value="apiKey") })
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Category> update(@PathVariable("id") Long id,
                                           @Valid @RequestBody Category category,
                                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()  ) {
            throw new InvalidException();
        }

        if (categoryRepository.findById(id).isEmpty()) {
            throw new NotFoundException();
        }
        category.setId(id);

        return ResponseEntity.ok(categoryRepository.save(category));
    }
}

