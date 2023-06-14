package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Boolean published;

    private Integer rating;

    private String comment;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    public Reviews(Users user, Product product, Boolean published, Integer rating, String comment, LocalDateTime publicationDate) {
        this.user = user;
        this.product = product;
        this.published = published;
        this.rating = rating;
        this.comment = comment;
        this.publicationDate = publicationDate;
    }
}