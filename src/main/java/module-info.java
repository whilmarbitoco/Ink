module com.whilmarbitoco.inkspace {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jbcrypt;
    requires jdk.jshell;

    opens com.whilmarbitoco.inkspace.model to javafx.base;
    opens com.whilmarbitoco.inkspace.utils to javafx.base;

    opens com.whilmarbitoco.inkspace.view.controller.admin to javafx.fxml;
    exports com.whilmarbitoco.inkspace.view.controller.admin;

    opens com.whilmarbitoco.inkspace.view.controller.auth to javafx.fxml;
    exports com.whilmarbitoco.inkspace.view.controller.auth;

    opens com.whilmarbitoco.inkspace.view.controller.user to javafx.fxml;
    exports com.whilmarbitoco.inkspace.view.controller.user;


    opens com.whilmarbitoco.inkspace.view.controller.seller to javafx.fxml;
    exports com.whilmarbitoco.inkspace.view.controller.seller;

    opens com.whilmarbitoco.inkspace.view.controller.seller.create to javafx.fxml;
    exports com.whilmarbitoco.inkspace.view.controller.seller.create;

    opens com.whilmarbitoco.inkspace to javafx.fxml;
    exports com.whilmarbitoco.inkspace;

    exports com.whilmarbitoco.inkspace.view.controller;
    opens com.whilmarbitoco.inkspace.view.controller to javafx.fxml;

}