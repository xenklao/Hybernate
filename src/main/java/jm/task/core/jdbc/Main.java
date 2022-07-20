package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    private static final UserService userService = new UserServiceImpl();
//           private static final UserService userService1 = new UserServiceImpl();
//    private static final UserDaoJDBCImpl userService = new UserDaoJDBCImpl();
    //    private static final User user1 = new User("Mitroy", "Gopit", (byte) 60);
//    private static final User user2 = new User("Jija", "Top", (byte) 15);
//    private static final User user3 = new User("Stariy", "Pes", (byte) 126);
//    private static final User user4 = new User("Legkiy", "Qyqer", (byte) 76);

    public static void main(String[] args)  {
        userService.createUsersTable();

        userService.saveUser("Джо", "Байден", (byte) 78);
        userService.saveUser("Трамп", "Дональд", (byte) 74);
        userService.saveUser("Барак", "Обама", (byte) 59);
        userService.saveUser("Джордж", "Буш", (byte) 74);

        List<User> strings = userService.getAllUsers();
        for (User s : strings)
            System.out.println(s);

        userService.removeUserById(2);

        userService.getAllUsers();

        userService.cleanUsersTable();

         userService.dropUsersTable();
    }
}
