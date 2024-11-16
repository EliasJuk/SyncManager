module com.sm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    exports com.sm;
    exports com.sm.controllers;
    exports com.sm.controllers.empresa to javafx.fxml;

    opens com.sm to javafx.fxml;
    opens com.sm.controllers to javafx.fxml;
    opens com.sm.controllers.empresa to javafx.fxml;
    opens com.sm.controllers.colaboradores to javafx.fxml;
    
    //BASES
    opens com.sm.utils to javafx.base;
    opens com.sm.models to javafx.base;

    //opens com.sm.models.pessoas to javafx.fxml;
    //opens com.sm.controllers.colaboradores to javafx.fxml;
}