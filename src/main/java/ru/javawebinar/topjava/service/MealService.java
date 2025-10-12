package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@Service
public class MealService {

    private final MealRepository repository;
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, Integer userId) {

        return repository.save(meal, userId);
    }

    public boolean delete(Integer mealId, Integer userId) {
        if (!repository.delete(mealId, userId)){
            throw new NotFoundException("Еда не найдена");
        }
        return true;
    }




}