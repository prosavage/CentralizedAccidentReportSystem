package net.prosavage.hospitalclient.struct;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import java.util.List;

public class Accident extends BasicDBObject {

   List<Injuries> injuries;
   String description;
   private String patientName;
   private long time;
   private boolean isProcessed = false;
   private boolean sex;
   private int severity;


   private HospitalName hospitalName;

   public Accident(String patientName, boolean isProcessed, List<String> injuries, long time, String description, HospitalName hospitalName, Sex sex, int severity) {
      super.put("isProcessed", isProcessed);
      super.put("injuries", injuries);
      super.put("time", time);
      super.put("description", description);
      super.put("patientName", patientName);
      super.put("hospitalName", hospitalName.toString());
      super.put("sex", sex.toString());
      super.put("severity", severity);
   }

   public int getSeverity() {
      return Integer.parseInt(super.get("severity").toString());
   }

   public void setSeverity(int severity) {
      super.put("severity",severity);
   }

   public Sex getSex() {
      return (Sex) Sex.valueOf(super.get("sex").toString());
   }

   public void setSex(Sex sex) {
      this.put("sex", sex.toString());
   }

   public String getPatientName() {
      return super.get("patientName").toString();
   }

   public void setPatiendName(String patientName) {
      this.put("patientName", patientName);
   }

   public boolean isProcessed() {
      return (boolean) super.get("isProcessed");
   }

   public void setProcessed(boolean processed) {
      this.put("isProcessed", processed);
   }

   public HospitalName getHospitalName() {
      return HospitalName.valueOf(super.get("hospitalName").toString());
   }

   public void setHospitalName(HospitalName hospitalName) {
      this.put("hospitalName", hospitalName.toString());
   }

   public long getTime() {
      return (long) super.get("time");
   }

   public void setTime(long time) {
      this.put("time", time);
   }

   public String getDescription() {
      return super.get("description").toString();
   }

   public List<String> getInjuries() {
      return (List<String>) super.get("injuries");
   }

   public DBObject toDBObject() {
      BasicDBObjectBuilder builder = BasicDBObjectBuilder
              .start("patientName", this.getPatientName())
              .append("isProcessed", this.isProcessed())
              .append("injuries", this.getInjuries())
              .append("time", this.getTime())
              .append("description", this.getDescription())
              .append("hospitalName", this.getHospitalName().toString())
              .append("sex", this.getSex().toString())
              .append("severity", this.getSeverity());
      return builder.get();
   }
//   public String toJson(){
//      return String.format("{patientName:%s, isProcessed:%s, injuries: %s, time: %s, description:%s, hospitalName:%S}", this.patientName, this.isProcessed, this.injuries, this.time, this.description,this.hospitalName);
//   }
}
