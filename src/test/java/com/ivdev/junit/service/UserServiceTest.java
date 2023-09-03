package com.ivdev.junit.service;

import com.ivdev.junit.dto.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//имя класса оканчивает на суффикс -Test
//все тесты лежат в папке /src/test, независимо от системы сборки
class UserServiceTest {

    private UserService userService;

    @BeforeAll
    static void init() {
        System.out.println("Before all ");
    }

    //BeforeEach выполнится перед каждым тестом в контейнере
    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
        userService = new UserService();
    }

    //цель Unit-тестов - сверить актуальное поведение методов с ожидаемым
    //имя метода должно отражать суть происходящего в нем
    @Test
    void userEmptyIfNoUsersAdded() {
        System.out.println("Test 1 " + this);
        var users = userService.getAll();
        assertTrue(users.isEmpty());
    }

    @Test
    void usersSizeIfUserAdded() {
        System.out.println("Test 2 " + this);
        userService.add(new User());
        userService.add(new User());

        //проверка ожидаемого размера коллекции через assertEquals()
        var users = userService.getAll();
        assertEquals(2, users.size());
    }

    //BeforeEach выполнится после каждого теста в контейнере
    //удаление тестовых данных из БД во избежание использования их другими тестами
    @AfterEach
    void deleteDataFromDatabase() {
        System.out.println("After each " + this);
        System.out.println("**********************");
    }

    @AfterAll
    static void closeConnectionPool() {
        System.out.println("After all ");
    }
}
