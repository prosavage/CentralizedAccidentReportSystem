package net.prosavage.carsserver;

import com.mongodb.*;
import net.prosavage.carsserver.Collections.Accident;
import net.prosavage.carsserver.Collections.Hospital;
import net.prosavage.carsserver.util.HospitalName;
import net.prosavage.carsserver.util.Injuries;
import net.prosavage.carsserver.util.Sex;

import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class Main {

   public static void main(String[] args) {
      System.out.println("Connecting to the server....");
      try {
         MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://198.50.227.17:27017"));
         DB database = mongoClient.getDB("CARS");
         database.dropDatabase();
         DBCollection Accidents = database.getCollection("Accidents");
         DBCollection Hospitals = database.getCollection("Hospitals");
         Hospitals.insert(new Hospital(1, 99, 100, HospitalName.HOSPITAL1, Arrays.asList()).toDBObject());
         Hospitals.insert(new Hospital(2, 100, 100, HospitalName.HOSPITAL2, Arrays.asList()).toDBObject());
         Hospitals.insert(new Hospital(3, 0, 100, HospitalName.HOSPITAL3, Arrays.asList()).toDBObject());
         Hospitals.insert(new Hospital(4, 0, 100, HospitalName.HOSPITAL4, Arrays.asList()).toDBObject());
         Hospitals.insert(new Hospital(5, 0, 100, HospitalName.HOSPITAL5, Arrays.asList()).toDBObject());
         Hospitals.insert(new Hospital(6, 0, 100, HospitalName.HOSPITAL6, Arrays.asList()).toDBObject());
//         Accidents.insert(new Accident("test name", false, Arrays.asList(), System.currentTimeMillis(), "Test desc", HospitalName.HOSPITAL1).toDBObject());
//         Accidents.insert(new Accident("test name 2", false, Arrays.asList(), System.currentTimeMillis(), "Test desc", HospitalName.HOSPITAL2).toDBObject());

         monitor(Accidents, Hospitals);
         //mongoClient.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }

   }

//
//   public static void dummyObj(DBCollection collection) {
//      BasicDBObject accident = new BasicDBObject();
//      accident.put("accident",
//              new Accident("Naman Gupta",
//                      false,
//                      Arrays.asList(Injuries.HEAD, Injuries.LEFT_ARM),
//                      System.currentTimeMillis(),
//                      "He got hurt by watching too much anime :(",
//                      HospitalName.HOSPITAL1,));
//
//
//      BasicDBObject hospital = new BasicDBObject();
//
//      hospital.put("hospital", new Hospital(1, 0, 100, HospitalName.HOSPITAL1, Arrays.asList()));
//
//   }

   public static void monitor(DBCollection accidents, DBCollection hospitals) {
      try {
         Thread.sleep(1000);
      } catch (InterruptedException ex) {
         ex.printStackTrace();
      }
      System.out.println("Checking Requests....");
      Cursor cursor = accidents.find();
      while (cursor.hasNext()) {
         DBObject object = cursor.next();
         Accident accident = new Accident(object.get("patientName").toString(),
                 (boolean) object.get("isProcessed"),
                 (List<String>) object.get("injuries"),
                 (long) object.get("time"),
                 object.get("description").toString(),
                 HospitalName.valueOf(object.get("hospitalName").toString()),
                 Sex.valueOf(object.get("sex").toString()),
                 Integer.parseInt(object.get("severity").toString()));
            if (!accident.isProcessed()) {
               process(accident, hospitals);
               object.put("isProcessed", true);
               accidents.save(object);
         }
      }
      System.out.println("Finished Checking.... Will check again in 5 seconds.");
      monitor(accidents, hospitals);
   }


   public static void process(Accident accident, DBCollection hospitalCollection) {
      System.out.println("Processing Accident");
      System.out.println("Name: " + accident.getPatientName());
      System.out.println("Time" + accident.getTime());
      List<DBObject> hospitalDBObjects = hospitalCollection.find().toArray();
      final List<Hospital> hospitals = new ArrayList<>();
      hospitalDBObjects.forEach(elem -> hospitals.add(
              new Hospital((int)elem.get("id"), (int) elem.get("numPatients"), (int) elem.get("maxOccupancy"), HospitalName.valueOf(elem.get("hospitalName").toString()), (List<Accident>) elem.get("accidents"))));
      Hospital hospitalToUse = null;
      for (Hospital hospital : hospitals) {
         if (accident.getHospitalName().equals(hospital.getHospitalName())) {
            hospitalToUse = hospital;
         }
      }
      if (hospitalToUse.isfull()) {
         for (Hospital hospital : hospitals) {
            if (!hospital.isfull()) {
               hospitalToUse = hospital;
               break;
            }
         }
      }
      accident.setHospitalName(hospitalToUse.getHospitalName());
      accident.setProcessed(true);
      if(hospitalToUse.getAccidents() == null){
         hospitalToUse.setAccidents(new ArrayList<Accident>());
      }
      //hospitalToUse.addAccident(accident);
      DBObject hospitalToUpdate = null;

      for (DBObject dbObject : hospitalDBObjects) {
         if (dbObject.get("hospitalName").toString().equals(hospitalToUse.getHospitalName().toString())){
            hospitalToUpdate = dbObject;
            //not sure why code above doesnt automatically apply numpatient when it autoupdated accidents ? ? ?
            hospitalToUpdate.put("numPatients", (int)hospitalToUpdate.get("numPatients")+1);
            List<DBObject> accidents = (List<DBObject>) hospitalToUpdate.get("accidents");
            accidents.add(accident.toDBObject());
            hospitalToUpdate.put("accidents", accidents);
            break;
         }
      }
      hospitalCollection.save(hospitalToUpdate);
   }

}
