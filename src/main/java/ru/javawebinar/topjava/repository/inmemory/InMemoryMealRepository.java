package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class InMemoryMealRepository implements MealRepository {
    private final AtomicInteger counter = new AtomicInteger(0);

    private final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private final Map<Integer, Map<Integer, Meal>> mealsMapByUser = new ConcurrentHashMap<>();

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, Integer userId) {
        Map<Integer, Meal> userMeals = mealsMapByUser.computeIfAbsent(userId, k -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        userMeals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (mealsMapByUser.containsKey(userId)) {
            return mealsMapByUser.get(userId).remove(id) != null;
        } else {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeals = mealsMapByUser.get(userId);
        return userMeals != null ? userMeals.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return mealsMapByUser.getOrDefault(userId, null).values().stream().sorted(Comparator.comparing(Meal::getDate).reversed()).collect(Collectors.toList());
    }
}

