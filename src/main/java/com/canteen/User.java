package com.canteen;

import static com.canteen.App.dbHost;
import static com.canteen.App.dbLogin;
import static com.canteen.App.dbName;
import static com.canteen.App.dbPassword;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {

    public static void addUser(String fio, String date_of_birth, String login, String password, int role) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
        Statement statement = conn.createStatement();
        statement.executeUpdate("INSERT INTO `users` (`fio`, `date_of_birth`, `login`, `password`, `role`) VALUES ('" + fio + "', " + "'" + date_of_birth + "', " + "'" + login + "', " + "'" + password + "', " + role + ");");
    }

    public static void removeUser(String login) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
        Statement statement = conn.createStatement();
        statement.executeUpdate("DELETE FROM `users` WHERE `users`.`login` = " + "'" + login + "'");
    }

    public static boolean findUser(String login) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://" + dbHost + "/" + dbName, dbLogin, dbPassword);
        Statement statement = conn.createStatement();
        ResultSet userData = statement.executeQuery("SELECT * FROM `users` WHERE `users`.`login` = " + "'" + login + "'");
        if (userData.next()) {
            return true;
        }
        return false;
    }

}
