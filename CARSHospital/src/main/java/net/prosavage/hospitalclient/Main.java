package net.prosavage.hospitalclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.prosavage.hospitalclient.struct.HospitalReport;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

   private static List<HospitalReport> hospitalReport = new ArrayList<>();

   public static void main(String[] args) {
      launch(args);
   }

   public void start(Stage primaryStage) throws Exception {
      primaryStage.setTitle("CARS: Hospital Client");
      Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/LogoHackathonsmall.png")));
      // TODO: Set icon image for the app!
      primaryStage.setResizable(false);
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
   }


}
