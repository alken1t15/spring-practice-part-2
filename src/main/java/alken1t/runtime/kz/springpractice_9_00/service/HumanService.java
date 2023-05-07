package alken1t.runtime.kz.springpractice_9_00.service;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class HumanService {
    private List<Human> humans = new ArrayList<>();

    {
        humans.add(new Human("Марк", 32));
        humans.add(new Human("Джефф", 22));
        humans.add(new Human("Мик", 15));
        humans.add(new Human("Алекс", 32));
    }

    public int getAverageAgeHuman() {
        if (humans.isEmpty()) return 0;
        int average = 0;
        for (Human human : humans) {
            average += human.getAge();
        }
        return average / humans.size();
    }

}