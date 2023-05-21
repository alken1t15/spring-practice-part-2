package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
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

    private LocalDateTime publicationDate;
}
