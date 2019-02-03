package net.prosavage.carsclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {


   public static Stage primaryStage;
   public static String[] args;

   public static void main(String[] args) {
      Main.args = args;
      launch(args);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
      primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Icon (1).png")));

      primaryStage.setTitle("CARS: Client");
      primaryStage.setScene(new Scene(root));
      primaryStage.show();
      Main.primaryStage = primaryStage;
   }

   public static void stopApp() {
      primaryStage.close();
   }

}
