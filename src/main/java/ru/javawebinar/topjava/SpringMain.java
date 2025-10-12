package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            List<User> all = adminUserController.getAll();
            System.out.println(all);

            InMemoryMealRepository inMemoryMealRepository = appCtx.getBean(InMemoryMealRepository.class);

            System.out.println("Удаляем еду 1 для юзера 1 " + inMemoryMealRepository.delete(2, 1));
            Collection<Meal> all1 = inMemoryMealRepository.getAll(1);
            System.out.println(all1);

            System.out.println("Поулучение еды 1 для 1" + inMemoryMealRepository.get(1, 1));
        }
    }
}
