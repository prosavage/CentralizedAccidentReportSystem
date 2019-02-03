package net.prosavage.hospitalclient;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mongodb.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import net.prosavage.hospitalclient.struct.Accident;
import net.prosavage.hospitalclient.struct.HospitalName;
import net.prosavage.hospitalclient.struct.Sex;
import org.bson.types.ObjectId;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Controller {

    private HospitalName hospitalName = HospitalName.HOSPITAL5;
    private HashMap<String, Accident> accidentHashMap = new HashMap<>();
    private int total, mild, moderate, severe;

    @FXML
    public JFXTextField nameField, sevField, injField, timeField, totalField, mildField, moderateField, severeField;
    public JFXTextArea descField;
    public JFXListView<Label> patientList;



    @FXML
    public void onRefresh() {
        total = 0;
        mild = 0;
        moderate = 0;
        severe = 0;
        MongoDBBridge mongoDBBridge = new MongoDBBridge(new MongoClientURI("mongodb://198.50.227.17:27017"));
        DBObject dbObject = mongoDBBridge.get(hospitalName);
        List<Accident> accidentList = new ArrayList<>();
        BasicDBList accidentCollection = (BasicDBList) dbObject.get("accidents");
        Iterator<Object> iterator = accidentCollection.iterator();
        while (iterator.hasNext()) {
            BasicDBObject object = (BasicDBObject) iterator.next();
            Accident accident = new Accident(object.get("patientName").toString(),
                    (boolean) object.get("isProcessed"),
                    (List<String>) object.get("injuries"),
                    (long) object.get("time"),
                    object.get("description").toString(),
                    HospitalName.valueOf(object.get("hospitalName").toString()),
                    Sex.valueOf(object.get("sex").toString()),
                    Integer.parseInt(object.get("severity").toString()));
            accidentList.add(accident);
        }

        for (Accident accident : accidentList) {
            total++;
            accidentHashMap.put(accident.getPatientName(), accident);
            int severity = accident.getSeverity();
            switch (severity) {
                case 1:
                    mild++;
                    break;
                case 2:
                    moderate++;
                    break;
                case 3:
                    severe++;
                    break;
            }
        }
        totalField.setText(total + " people");
        mildField.setText(mild + " people");
        moderateField.setText(moderate + " people");
        severeField.setText(severe + " people");
        patientList.getItems().clear();

        for (String key : accidentHashMap.keySet()) {
            patientList.getItems().add(new Label(key));
        }

    }


    @FXML
    public void onInfo() {
        if (patientList.getItems().size() == 0 || patientList.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        String name = patientList.getSelectionModel().getSelectedItem().getText();
        Accident accident = accidentHashMap.get(name);
        nameField.setText(name);
        String sevString = "";
        switch (accident.getSeverity()) {
            case 1:
                sevString = "Mild";
                break;
            case 2:
                sevString = "Moderate";
                break;
            case 3:
                sevString = "Severe";
                break;
        }
        sevField.setText(sevString);
        String injuries = "";
        for (String injury : accident.getInjuries()) {
            injuries += injury + ", ";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS",Locale.US);

        injField.setText(injuries);
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Central"));
        calendar.setTimeInMillis(accident.getTime());

        timeField.setText(sdf.format(calendar.getTime()));
        descField.setText(accident.getDescription());

    }





}
