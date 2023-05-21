package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

//    @Enumerated(EnumType.STRING)
//    private String status;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
}