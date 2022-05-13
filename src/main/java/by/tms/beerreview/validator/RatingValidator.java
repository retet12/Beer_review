package by.tms.beerreview.validator;

import by.tms.beerreview.exception.InvalidException;
import by.tms.beerreview.exception.NotFoundException;
import by.tms.beerreview.repository.PostRepository;
import by.tms.beerreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class RatingValidator {
    @Value("${userOrPostNotFound}")
    private String msgUserOrPostNotFound;

    @Value("${invalidUserIdOrPostId}")
    private String msgInvalidUserIdOrPostId;

    private UserRepository userRepository;
    private PostRepository postRepository;

    public RatingValidator(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public RatingValidator() {
    }

    public void validateID (long userId, long postId) {
        if(userId < 1 | postId < 1) {
            throw new InvalidException(msgInvalidUserIdOrPostId);
        }
    }

    public boolean existsByUserIdAndPostId(long userId, long postId) {
        if (userRepository.findById(userId).isEmpty() || postRepository.findById(postId).isEmpty()) {
            throw new NotFoundException(msgUserOrPostNotFound);
        }
        return true;
    }
}
