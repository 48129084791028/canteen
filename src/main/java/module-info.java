module com.canteen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.canteen to javafx.fxml;
    exports com.canteen;
}
