package controller;

import entity.Rating;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/","/rating"})
public class RatingController {
    @GetMapping
    public String addRating(Model model) {
        model.addAttribute("rating", new Rating());
        return "index";
    }

    @PostMapping
    public String save(Rating rating, Model model) {
        model.addAttribute("rating", rating);
        return "saved";
    }
}
