package com.canteen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.*;

public class Price {

    private SimpleStringProperty name;
    private SimpleIntegerProperty price;

    public Price(String name, int price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
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

}
