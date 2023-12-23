package com.example.testjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cinema!!!");
        stage.getIcons().add(new Image("C:\\Users\\beta\\Desktop\\Apps\\" +
                "Projects\\InteliiJ_projects\\testJavafx\\src\\main\\" +
                "resources\\com\\example\\testjavafx\\movie-symbol-of-video-camera_icon-icons.com_72981.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        DataBase dataBase = new DataBase();
        dataBase.initializeAPP();

        launch();


    }
}