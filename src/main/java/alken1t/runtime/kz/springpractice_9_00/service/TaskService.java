package alken1t.runtime.kz.springpractice_9_00.service;

import alken1t.runtime.kz.springpractice_9_00.pojo.Human;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private static final Human[] HUMANS = new Human[]{
            new Human("Овсянников Святослав Елисеевич", 32),
            new Human("Богданова Екатерина Дмитриевна", 22),
            new Human("Гусев Дамир Антонович", 16),
            new Human("Столярова Арина Ильинична", 19),
            new Human("Шевелева Варвара Степановна", 24),
            new Human("Ушакова Каролина Матвеевна", 43),
            new Human("Борисова Виктория Тимофеевна",60),
            new Human("Бобров Степан Артёмович",54),
            new Human("Сергеев Илья Давидович",14),
            new Human("Постникова Александра Егоровна",23)
    };

    public List<Human> getByHumansMore(int from){
        return Arrays.stream(HUMANS)
                .filter(human -> human.getAge()>=from).collect(Collectors.toList());
    }

    public List<Human> getByHumansLess(int to){
        return Arrays.stream(HUMANS)
                .filter(human -> human.getAge()<=to).collect(Collectors.toList());
    }

    public List<Human> getHumansByAge(int from, int to){
       return Arrays.stream(HUMANS)
                .filter(human -> human.getAge()>=from && human.getAge()<=to).collect(Collectors.toList());
    }

    public Human[] getHUMANS() {
        return HUMANS;
    }
}