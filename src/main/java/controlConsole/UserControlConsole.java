package controlConsole;

import lombok.RequiredArgsConstructor;
import user.dao.UserDao;
import user.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static util.Menu.menuRequestForUser;


@RequiredArgsConstructor
public class UserControlConsole {
    public final Scanner scanner;
    private UserDao userDao = new UserDao();

    public void aboutUser() {
        userList.clear();
        System.out.println(menuRequestForUser);
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {

                case "1":
                    System.out.println("Create list of users = " + createListUsers());
                    break;
                case "2":
                    System.out.println("Create array of users = " + createListUsers());
                    break;
                case "3":
                    System.out.println("Get user by user name = " + getUserByUserName());
                    break;
                case "4":
                    System.out.println("Update user = " + updateUser());
                    break;
                case "5":
                    System.out.println("Delete user = " + deleteUserByName());
                    break;
                case "6":
                    System.out.println("Logs user into the system = " + logsUserIntoSystem());
                    break;
                case "7":
                    System.out.println("Logs out current loggent in user session = " + logsOut());
                    break;
                case "8":
                    System.out.println("Create User = " + createNewUser());
                    break;
                case "9":
                    break;
                default:
                    System.out.println("Incorect, please try again ");
            }
        }
    }

    List<User> userList = new ArrayList<>();

    private String logsOut() {
        return userDao.logout();

    }

    private String logsUserIntoSystem() {
        String responce = "";
        try {
            String userName = enterString("user name: ");
            String paswword = enterString("password for login in clear text:");

            responce = userDao.logsUserIntoSystem(userName, paswword);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;

    }

    private String createListUsers() {

        String responce = "";
        try {
            int id = Integer.parseInt(enterNumber("id:"));
            String userName = enterString("user name:");
            String firstName = enterString("firstName:");
            String lastName = enterString("lastName:");
            String email = enterString("email:");
            String password = enterString("password:");
            int userStatus = Integer.parseInt(enterNumber("user status:"));
            userList.add(User.builder()
                    .id(id)
                    .username(userName)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .userStatus(userStatus)
                    .build());

            System.out.println("add next user? (Y,N)");
            if (scanner.hasNextLine()) {
                if (scanner.nextLine().equals("Y")) {
                    createListUsers();
                } else {
                    User[] users = new User[userList.size()];
                    for (int i = 0; i < users.length; i++) {
                        users[i] = userList.get(i);
                    }
                    responce = userDao.createUsersList(users);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;
    }

    private String updateUser() {
        String responce = "";
        try {
            String oldUserName = enterString("user name that need to be update:");
            int id = Integer.parseInt(enterNumber("id:"));
            String userName = enterString("user name:");
            String firstName = enterString("firstName:");
            String lastName = enterString("lastName:");
            String email = enterString("email:");
            String password = enterString("password:");
            int userStatus = Integer.parseInt(enterNumber("user status:"));

            responce = userDao.updateUser(User.builder()
                    .id(id)
                    .username(userName)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .userStatus(userStatus)
                    .build(),oldUserName
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;
    }

    private String createNewUser() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterNumber("id:"));
            String userName = enterString(" user name:");
            String firstName = enterString("firstName:");
            String lastName = enterString("lastName:");
            String email = enterString("email:");
            String password = enterString("password:");
            int userStatus = Integer.parseInt(enterNumber("user status:"));

            responce = userDao.addUser(User.builder()
                    .id(id)
                    .username(userName)
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
                    .userStatus(userStatus)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;
    }

    private String getUserByUserName() {
        String responce = "";
        try {
            String userName = enterString(" user name:");
            responce = userDao.getUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;
    }

    private String deleteUserByName() {
        String responce = "";
        try {
            String userName = enterString(" user name:");
            responce = userDao.deleteUser(userName);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutUser();
        }
        return responce;
    }

    private String enterNumber(String nameVar) {
        System.out.println("Enter " + nameVar);
        String id = "";

        if (scanner.hasNextLine()) {
            id = scanner.nextLine();
        }
        if (id.matches("\\d+"))
            System.out.println("Ok");
        else {
            id = "";
        }
        return id;
    }

    private String enterString(String nameVar) {
        System.out.println("Enter " + nameVar);
        String readData = "";

        if (scanner.hasNextLine()) {
            readData = scanner.nextLine();
        }
        return readData;
    }
}
