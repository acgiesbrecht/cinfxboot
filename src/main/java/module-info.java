open module chortitzer.cinfxboot {

    requires javafx.controls;
    requires javafx.fxml;
    requires spring.boot;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.beans;
    requires spring.orm;
    requires spring.tx;
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

    requires org.apache.logging.log4j;
    requires com.google.common;
    requires de.jensd.fx.glyphs.commons;
    requires de.jensd.fx.glyphs.fontawesome;
    requires de.jensd.fx.glyphs.materialdesignicons;
    //opens com.chortitzer.cin.cinfxboot;
    //opens com.chortitzer.cin.cinfxboot.datasource.bascula;
    //opens com.chortitzer.cin.cinfxboot.datasource.bascula.repository;
    exports com.chortitzer.cin.cinfxboot;
}
