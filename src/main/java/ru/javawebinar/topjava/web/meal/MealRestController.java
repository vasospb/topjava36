package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public void save(Meal meal, Integer userId) {
        mealService.create(meal, userId);
    }

    public void delete(int id, Integer userId) {
        mealService.delete(id, userId);
    }

    public Meal get(int id, Integer userId) {
        return mealService.get(id, userId);
    }

    public Collection<Meal> getAll(Integer userId) {
        return mealService.getAll(userId);
    }
}