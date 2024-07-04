module Tugas_6_JavaFX{

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires javafx.media;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires annotations;

    opens com.main to javafx.fxml;
    opens controller to javafx.fxml;
    opens books to javafx.base;
    opens data to javafx.base;

    exports com.main;
    exports books;
    exports data;
    exports exception.custom;
    exports controller;
}