package alken1t.runtime.kz.springpractice_9_00.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class CityService {

    private Map<String, Integer> cityToCount = new HashMap<>();

    {
        cityToCount.put("Москва", 12_100);
        cityToCount.put("Астана", 1_300);
        cityToCount.put("Нью-Йорк", 7_700);
    }

    public int getAverageHumanOfCity() {
        if (cityToCount.isEmpty()) return 0;
        int average = 0;
        Collection<Integer> integerIterator = cityToCount.values();
        for (int count : integerIterator) {
            average += count;
        }
        return average / cityToCount.size();
    }
}