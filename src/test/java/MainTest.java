import lib.AccessDeniedException;
import lib.User;
import lib.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка на наличие пользователя (equals по имени)")
    void getUserByLoginAndPassword() {
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("Maxim", "maxim@yandex.ru", "pass2", 17);
        User[] users = {user1, user2};

        try {
            final User result = Main.getUserByLoginAndPassword(user1.getLogin(), user1.getPassword(), users);

            assertThat(user1, equalTo(result));
            assertThat(result, isA(User.class));
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Проверка корректности формирования массива пользователей через функцию")
    void validateUser() {
        User user = new User("jhon", "jhon@gmail.com", "pass", 16);

        Assertions.assertThrows(AccessDeniedException.class, () -> Main.validateUser(user));
    }

    @Test
    @DisplayName("Проверка сравнение массива пользователей")
    void getUser() {
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("Maxim", "maxim@yandex.ru", "pass2", 17);
        User user3 = new User("Jo", "jo@jo.net", "pass3", 18);
        User[] users = {user1, user2, user3};

        final User[] result = Main.getUsers(user1, user2, user3);

        assertThat(users, is(result));
        //Assertions.assertArrayEquals(users, result);
    }

    @Test
    @DisplayName("Нет старше 18")
    void moreEighteen() {
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("Maxim", "maxim@yandex.ru", "pass2", 17);
        User user3 = new User("Jo", "jo@jo.net", "pass3", 19);
        User[] users = {user1, user2, user3};

        final List<User> usersList = Main.moreEighteen(users);

        for (User user : usersList) {
            assertThat(user.getAge(), not(lessThan(18)));
        }
    }

    @Test
    @DisplayName("Проверка, корректный ли почтовый адрес")
    void emailFine() {
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("Maxim", "maxim@yandex.ru", "pass2", 17);
        User user3 = new User("Jo", "jo@jo.net", "pass3", 19);
        User[] users = {user1, user2, user3};

        final List<User> control = Main.emailFine(users);

        for (User user : control) {
            assertThat(user.getEmail(), containsString("@"));
            assertThat(user.getEmail(), containsString("."));
        }
    }
}


//Подскажите почему не получилась данная реализации. Пытался по разному но выходилj одна и таже ошибка ...getConfigurationParameter
    /*@ParameterizedTest
    @MethodSource("factory")
    void getUser (User user1, User user2, User user3) {
        //final User user = new User();
        User[] users = {user1, user2, user3};

        final User[] result = Main.getUsers(user1, user2, user3);

         Assertions.assertEquals(users, result);

    }

    public static Stream<Arguments> factory(){
        return Stream.of(
                Arguments.of(new User("jhon", "jhon@gmail.com", "pass", 16), new User("Maxim", "maxim@yandex.ru", "pass2", 17), new User("Petya", "petya@gmail.com", "pass", 20)),
                Arguments.of(new User("Jo", "jo@jo.net", "pass3", 18), new User("Maxim", "maxim@yandex.ru", "pass2", 17), new User("Petya", "petya@gmail.com", "pass", 20))
        );
    }*/








