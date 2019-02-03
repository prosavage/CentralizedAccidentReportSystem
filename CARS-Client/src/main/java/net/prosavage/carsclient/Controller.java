package net.prosavage.carsclient;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import net.prosavage.carsclient.util.Body;
import net.prosavage.carsclient.util.util.HospitalName;
import net.prosavage.carsclient.util.util.Injuries;
import net.prosavage.carsclient.util.util.Sex;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Controller {


   @FXML
   public AnchorPane pageOne, pageTwo, pageThree;
   public JFXButton descNextButton, infoNextButton;
   public Button submitButton;
   public JFXTextField nameDescTextField, injuryTextField, nameInfoTextField, locationTextField;
   public JFXSlider levelSlider;
   public JFXTextArea descriptionTextArea;
   public ToggleButton femaleToggle, maleToggle;
   public ImageView headButton, chestButton, leftArmButton, rightArmButton, rightLegButton, leftLegButton, waistButton;



   @FXML
   public void toggleClicked() {
      if (maleToggle.isSelected()) {
         maleToggle.setStyle("-fx-background-color: #27ae60; -fx-background-radius: 10;");
      }
      if (femaleToggle.isSelected()) {
         femaleToggle.setStyle("-fx-background-color: #27ae60; -fx-background-radius: 10;");
      }
      if (!maleToggle.isSelected()) {
         maleToggle.setStyle("-fx-background-color:  #0984e3; -fx-background-radius: 10;");
      }
      if (!femaleToggle.isSelected()) {
         femaleToggle.setStyle("-fx-background-color:  #0984e3; -fx-background-radius: 10;");

      }

   }


   public void onInfoNextClick() {
      pageOne.setOpacity(0);
      pageThree.setOpacity(0);
      pageTwo.setOpacity(1);
      pageTwo.toFront();
      // JANK CODE
      {
         headButton.setOnMouseClicked(t -> {
            if (Body.HEAD.isStatus()) {
               Body.HEAD.setStatus(false);
               headButton.setImage(new Image(new File("/img/HeadRegular.png").getPath()));
            } else {
               Body.HEAD.setStatus(true);
               headButton.setImage(new Image(new File("/img/headRed.png").getPath()));
            }
         });
         chestButton.setOnMouseClicked(t -> {
            if (Body.CHEST.isStatus()) {
               Body.CHEST.setStatus(false);
               chestButton.setImage(new Image(new File("/img/ChestRegular.png").getPath()));
            } else {
               Body.CHEST.setStatus(true);
               chestButton.setImage(new Image(new File("/img/ChestRed.png").getPath()));
            }
         });
         waistButton.setOnMouseClicked(t -> {
            if (Body.WAIST.isStatus()) {
               Body.WAIST.setStatus(false);
               waistButton.setImage(new Image(new File("/img/AbdomenRegular.png").getPath()));
            } else {
               Body.WAIST.setStatus(true);
               waistButton.setImage(new Image(new File("/img/AbdomenRed.png").getPath()));
            }
         });
         leftArmButton.setOnMouseClicked(t -> {
            if (Body.LEFTARM.isStatus()) {
               Body.LEFTARM.setStatus(false);
               leftArmButton.setImage(new Image(new File("/img/LeftArmRegular.png").getPath()));
            } else {
               Body.LEFTARM.setStatus(true);
               leftArmButton.setImage(new Image(new File("/img/LeftArmRed.png").getPath()));
            }
         });
         rightArmButton.setOnMouseClicked(t -> {
            if (Body.RIGHTARM.isStatus()) {
               Body.RIGHTARM.setStatus(false);
               rightArmButton.setImage(new Image(new File("/img/ArmRegular.png").getPath()));
            } else {
               Body.RIGHTARM.setStatus(true);
               rightArmButton.setImage(new Image(new File("/img/RightArmRed.png").getPath()));
            }
         });
         rightLegButton.setOnMouseClicked(t -> {
            if (Body.RIGHTLEG.isStatus()) {
               Body.RIGHTLEG.setStatus(false);
               rightLegButton.setImage(new Image(new File("/img/RightLegRegular.png").getPath()));
            } else {
               Body.RIGHTLEG.setStatus(true);
               rightLegButton.setImage(new Image((new File("/img/RightLegRed.png").getPath())));
            }
         });
         leftLegButton.setOnMouseClicked(t -> {
            if (Body.LEFTLEG.isStatus()) {
               Body.LEFTLEG.setStatus(false);
               leftLegButton.setImage(new Image(new File("/img/LeftLegRegular.png").getPath()));
            } else {
               Body.LEFTLEG.setStatus(true);
               leftLegButton.setImage(new Image(new File("/img/LeftLegRed.png").getPath()));
            }
         });
      }
   }

   public void onDescNextClick() {
      pageOne.setOpacity(0);
      pageTwo.setOpacity(0);
      pageThree.setOpacity(1);
      pageThree.toFront();
   }

   public void onSubmitButtonClick() {
      String name = nameInfoTextField.getText();
      long time = System.currentTimeMillis();
      String description = descriptionTextArea.getText();
      int level = (int) levelSlider.getValue();
      HospitalName hospitalName;
      if (level == 1) {
         hospitalName = HospitalName.HOSPITAL1;
      } else if (level == 2) {
         hospitalName = HospitalName.HOSPITAL3;
      } else if (level == 3) {
         hospitalName = HospitalName.HOSPITAL5;
      } else {
         hospitalName = HospitalName.HOSPITAL6;
      }
      Sex sex;
      if (maleToggle.isSelected()) {
         sex = Sex.MALE;
      } else if (femaleToggle.isSelected()) {
         sex = Sex.FEMALE;
      } else {
         sex = Sex.ATTACK_HELICOPTER;
      }
      List<String> injuriesList = new ArrayList<>();
      if (Body.HEAD.isStatus()) {
         injuriesList.add(Injuries.HEAD.name());
      }
      if (Body.LEFTARM.isStatus()) {
         injuriesList.add(Injuries.LEFT_ARM.name());
      }
      if (Body.CHEST.isStatus()) {
         injuriesList.add(Injuries.CHEST.name());
      }
      if (Body.LEFTLEG.isStatus()) {
         injuriesList.add(Injuries.LEFT_LEG.name());
      }
      if (Body.RIGHTLEG.isStatus()) {
         injuriesList.add(Injuries.RIGHT_LEG.name());
      }
      if (Body.WAIST.isStatus()) {
         injuriesList.add(Injuries.TORSO.name());
      }
         System.out.println("DB update attempted"); //hi
      MongoDBBridge mongoDBBridge = new MongoDBBridge(new MongoClientURI());
      DBObject accidentObj = new Accident(name, false, injuriesList, System.currentTimeMillis(), description, hospitalName, sex, level).toDBObject();
      mongoDBBridge.sendShit(accidentObj);
      System.out.println("DB updated");
      try {
         Thread.sleep(10000);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      HospitalName hospitalName1 = mongoDBBridge.getNewInfo(accidentObj);
      JDialog jDialog = new JDialog();
      JOptionPane.showMessageDialog(jDialog, "Your Destination: " + hospitalName1);
      jDialog.hide();
      Main.stopApp();
   }

}
