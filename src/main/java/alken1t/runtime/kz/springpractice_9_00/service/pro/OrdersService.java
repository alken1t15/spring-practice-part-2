package alken1t.runtime.kz.springpractice_9_00.service.pro;

import alken1t.runtime.kz.springpractice_9_00.entity.Orders;
import alken1t.runtime.kz.springpractice_9_00.entity.Reviews;
import alken1t.runtime.kz.springpractice_9_00.entity.Status;
import alken1t.runtime.kz.springpractice_9_00.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    public void editStatus( long id,Status status) {
        Orders orders = findById(id);
        orders.setStatus(status);
        save(orders);
    }
}
