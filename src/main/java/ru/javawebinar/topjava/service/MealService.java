package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

public class MealService {
    Integer userId;

    private MealRepository repository;

    public Meal create(Meal meal) {
        return repository.save(meal, userId);
    }

    public boolean delete(Integer mealId, Integer userId) {
        return repository.delete(meal, userId);
    }


}