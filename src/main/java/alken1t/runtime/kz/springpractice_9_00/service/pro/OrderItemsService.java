package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.OrderItems;
import alken1t.runtime.kz.springpractice_9_00.repository.OrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemsService {
    private final OrderItemsRepository orderItemsRepository;
    public void save(OrderItems orderItems){
        orderItemsRepository.save(orderItems);
    }
}
