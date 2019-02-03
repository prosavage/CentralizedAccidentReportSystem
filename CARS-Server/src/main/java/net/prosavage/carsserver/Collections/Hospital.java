package net.prosavage.carsserver.Collections;


import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import net.prosavage.carsserver.util.HospitalName;

import java.util.ArrayList;
import java.util.List;

public class Hospital extends BasicDBObject {

   private int id;
   private int numPatients;
   private int maxOccupancy;
   private HospitalName hospitalName;
   private List<Accident> accidents = new ArrayList<Accident>();

   public Hospital(int id, int numPatients, int maxOccupancy, HospitalName hospitalName, List<Accident> accidents) {
      super.put("id", id);
      super.put("numPatients", numPatients);
      super.put("maxOccupancy", maxOccupancy);
      super.put("hospitalName", hospitalName.toString());
      super.put("accidents", accidents);
   }

   public int getId() {
      return (int)super.get("id");
   }

   public int getNumPatients() {
      return (int)super.get("numPatients");
   }

   public void setNumPatients(int numPatients) {
      this.put("numPatients", numPatients);
   }

   public int getMaxOccupancy() {
      return (int)super.get("maxOccupancy");
   }

   public HospitalName getHospitalName() {
      return HospitalName.valueOf(super.get("hospitalName").toString());
   }

   public void addAccident(Accident accident) {
      this.getAccidents().add(accident);
   }

   public void setHospitalName(HospitalName hospitalName) {
      this.put("hospitalName", hospitalName.toString());
   }

   public boolean isfull() {
      return this.getNumPatients() / this.getMaxOccupancy() >= 1;
   }

//   public static HospitalName assignNewHospital(HospitalName target) {
//      int hospitalID = Integer.parseInt(target.name().replace("HOSPITAL", ""));
//      if (hospitalID == 6) {
//         return target;
//      }
//      hospitalID++;
//      HospitalName hospitalName = HospitalName.valueOf("HOSPITAL" + hospitalID);
//      return hospitalName;
//   }

   public List<Accident> getAccidents() {
      return (List<Accident>)super.get("accidents");
   }

   public void setAccidents(List<Accident> accidents) {
      this.put("accidents", accidents);
   }
   public DBObject toDBObject() {
      BasicDBObjectBuilder builder = BasicDBObjectBuilder
              .start("id", this.getId())
              .append("numPatients", this.getNumPatients())
              .append("maxOccupancy",this.getMaxOccupancy())
              .append("hospitalName",this.getHospitalName().toString())
              .append("accidents",this.getAccidents());
      return builder.get();
   }

//   public String toJson(){
//         return String.format("{id:%s, numPatients:%s, maxOccupancy: %s, hospitalName: %s, accidents:%s}", this.id, this.numPatients, this.maxOccupancy, this.hospitalName, this.accidents);
//   }
}