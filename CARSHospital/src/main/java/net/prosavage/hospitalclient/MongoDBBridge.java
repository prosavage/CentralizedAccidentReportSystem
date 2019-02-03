package net.prosavage.hospitalclient;


import com.mongodb.*;
import com.sun.corba.se.spi.ior.ObjectId;
import net.prosavage.hospitalclient.struct.HospitalName;

import java.net.UnknownHostException;

public class MongoDBBridge {


   MongoClient mongoClient;

   public MongoDBBridge(MongoClientURI mongoClientURI) {
      try {
         this.mongoClient = new MongoClient(mongoClientURI);
      } catch (UnknownHostException ex) {
         ex.printStackTrace();
      }
   }


   public DBObject get(HospitalName name) {
      DB database = mongoClient.getDB("CARS");
      DBCollection accidents = database.getCollection("Hospitals");
      for (DBObject dbObject : accidents.find()) {
         System.out.println(dbObject.get("hospitalName"));
         if (dbObject.get("hospitalName").equals(name.toString())) {
            return dbObject;
         }
      }
      return null;
   }



}
