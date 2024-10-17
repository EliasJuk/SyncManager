module com.sm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    exports com.sm;
    exports com.sm.controllers;

    opens com.sm to javafx.fxml;
    opens com.sm.controllers to javafx.fxml;
}
