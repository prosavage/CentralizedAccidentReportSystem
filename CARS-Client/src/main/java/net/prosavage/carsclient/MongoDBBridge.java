package net.prosavage.carsclient;

import com.mongodb.*;
import net.prosavage.carsclient.util.util.HospitalName;
import org.bson.types.ObjectId;

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


   public void sendShit(DBObject accident) {
      DB database = mongoClient.getDB("CARS");
      DBCollection accidents = database.getCollection("Accidents");
      accidents.insert(accident);
   }

   public HospitalName getNewInfo(DBObject dbObject) {
      DB database = mongoClient.getDB("CARS");
      DBCollection accidents = database.getCollection("Accidents");
      ObjectId id = (ObjectId) dbObject.get("_id");
      for (DBObject accidentbj : accidents.find()) {
         if (accidentbj.get("_id").equals(id)) {
            accidents.remove(accidentbj);
            return HospitalName.valueOf(accidentbj.get("hospitalName").toString());
         }
      }
      return null;
   }


}
