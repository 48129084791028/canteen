package com.canteen;

import static com.canteen.SigninController.currentUserLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Order {

    private SimpleStringProperty name, status, user;
    private SimpleIntegerProperty id, price;

    public Order(int id, String name, int price, String status, String user) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(status);
        this.price = new SimpleIntegerProperty(price);
        this.user = new SimpleStringProperty(user);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public int getPrice() {
        return price.get();
    }

    public void setPrice(int value) {
        price.set(value);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String value) {
        status.set(value);
    }

    public String getUser() {
        return user.get();
    }

    public void setUser(String value) {
        user.set(value);
    }

}
