module com.sm {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sm to javafx.fxml;
    exports com.sm;
}
