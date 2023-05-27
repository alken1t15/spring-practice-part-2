package alken1t.runtime.kz.springpractice_9_00.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "orders")
    private List<OrderItems> orderItems;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_date")
    private LocalDateTime orderDate;
}