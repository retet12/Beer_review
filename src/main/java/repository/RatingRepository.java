package repository;

import entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Optional<Rating> findByRating (float stars);
}
