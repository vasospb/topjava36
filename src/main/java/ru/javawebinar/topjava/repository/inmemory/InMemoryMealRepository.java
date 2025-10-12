package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InMemoryMealRepository implements MealRepository {
    private final AtomicInteger counter = new AtomicInteger(0);

    private final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private final Map<Integer, Map<Integer, Meal>> mealsMapByUser = new ConcurrentHashMap<>();

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        mealsMapByUser.put(1, mealsMap);
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (mealsMapByUser.containsKey(userId)) {
                mealsMapByUser.get(userId).put(meal.getId(), meal);
            } else {
                mealsMapByUser.put(userId, new ConcurrentHashMap<>());
                mealsMapByUser.get(userId).put(meal.getId(), meal);
            }
            return meal;
        }
        // handle case: update, but not present in storage
        return mealsMap.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return mealsMapByUser.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id) {
        return mealsMap.get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return mealsMap.values();
    }
}

