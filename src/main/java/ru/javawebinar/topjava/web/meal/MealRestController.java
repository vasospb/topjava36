package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

@Controller
public class MealRestController {
    private MealService mealService;

    public MealRestController() {
    }

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public void save(Meal meal, Integer userId) {
        mealService.create(meal, userId);
    }

    public void delete(int id, Integer userId) {

    }

    public Meal get(int id, Integer userId) {
        return null;
    }

    public Collection<Meal> getAll(Integer userId) {

        return null;
    }
}