package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Orders;
import alken1t.runtime.kz.springpractice_9_00.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public void save(Orders orders){
        ordersRepository.save(orders);
    }

    public Orders findById(long id){
        return ordersRepository.findById(id).orElseThrow();
    }
}
