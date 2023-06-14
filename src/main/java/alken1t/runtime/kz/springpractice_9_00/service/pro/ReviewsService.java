package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Product;
import alken1t.runtime.kz.springpractice_9_00.entity.Reviews;
import alken1t.runtime.kz.springpractice_9_00.entity.Users;
import alken1t.runtime.kz.springpractice_9_00.repository.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    public double getRating(long id) {
        int count = reviewsRepository.getCountForRating(id);
        if (count == 0) {
            return 0.0;
        }
        int rating = reviewsRepository.getRating(id);
        return (double) rating / count;
    }

    public Reviews findByProductAndUser(Product product, Users user) {
        return reviewsRepository.findByProductAndUser(product, user);
    }

    public void save(Reviews reviews) {
        reviewsRepository.save(reviews);
    }

    public List<Reviews> findAllByProduct(Product product) {
        return reviewsRepository.findAllByProduct(product);
    }

    public List<Reviews> findByUserAndPublished(Users users, Boolean published) {
        return reviewsRepository.findByUserAndPublished(users, published);
    }

    public Reviews findById(Long id) {
        return reviewsRepository.findById(id).orElseThrow();
    }

    public void createNewReviews(Users users, Product product, Integer rating, String comment) {
        Reviews reviews = new Reviews(users, product, false, rating, comment, LocalDateTime.now());
        save(reviews);
    }
}