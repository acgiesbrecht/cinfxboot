module chortitzer.cinfxboot {

    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.context;
    requires spring.data.commons;
    requires spring.boot.starter;
    requires spring.boot.autoconfigure;
    requires java.xml.bind;
    requires net.bytebuddy;
    requires javafx.base;
    requires javafx.graphics;
    requires java.persistence;
    requires slf4j.api;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens com.chortitzer.cin.cinfxboot;
    opens com.chortitzer.cin.cinfxboot.model;
    exports com.chortitzer.cin.cinfxboot;
}
