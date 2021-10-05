import lib.AccessDeniedException;
import lib.User;
import lib.UserNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws UserNotFoundException, AccessDeniedException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        User user1 = new User("jhon", "jhon@gmail.com", "pass", 24);
        User user2 = new User("Maxim", "maxim@yandex.ru", "pass2", 17);
        User user3 = new User("Jo", "jo@jo.net", "pass3", 18);
        User[] users = getUsers(user1, user2, user3);
        System.out.println(users);
        User user = getUserByLoginAndPassword(login, password, users);

        validateUser(user);
        System.out.println("Доступ предоставлен");
    }

    public static User[] getUsers(User user1, User user2, User user3) {

        return new User[]{user1, user2, user3};
    }

    public static User getUserByLoginAndPassword(String login, String password, User[] users) throws UserNotFoundException {
        for (User user : users) {
            if (login.equals(user.getLogin()) & password.equals(user.getPassword())) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found");
    }

    public static void validateUser (User user) throws AccessDeniedException{
        if (user.getAge()<18)
        {
            throw new AccessDeniedException("Access denied! Age restriction 18+");
        }
    }

    public static List<User> moreEighteen (User[] users){
        List<User> usersList = Arrays.asList(users);
        usersList = usersList.stream()
                .filter(user -> user.getAge() > 18)
                .collect(Collectors.toList());
        return usersList;
    }

    public static List<User> emailFine (User[] users){
        List<User> usersList = Arrays.asList(users);
        List<User> resultList = new ArrayList<>();
        for(User user:usersList){
            if(user.getEmail().contains("@") && user.getEmail().contains(".")){
                resultList.add(user);
            }
        }
        return resultList;
    }
}

