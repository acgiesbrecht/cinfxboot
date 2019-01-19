/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chortitzer.cin.cinfxboot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class CinApplication extends Application {

    private ConfigurableApplicationContext springContext;
    private static final Logger log = LoggerFactory.getLogger(CinApplication.class);
    private Scene scene;
    private Workbench workbench;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(CinApplication.class);
        BootFxController controller = springContext.getBean(BootFxController.class);
        scene = new Scene(controller.getView());
        scene.getStylesheets().add(BootFxConstants.CSS_PATH);
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        startApplication(stage);
    }

    private void startApplication(final Stage primaryStage) {
        log.info("Starting {}!", BootFxConstants.PROJECT_TITLE);
        primaryStage.setTitle(BootFxConstants.PROJECT_TITLE);
        primaryStage.setHeight(BootFxConstants.HEIGHT);
        primaryStage.setWidth(BootFxConstants.WIDTH);
        primaryStage.centerOnScreen();
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
