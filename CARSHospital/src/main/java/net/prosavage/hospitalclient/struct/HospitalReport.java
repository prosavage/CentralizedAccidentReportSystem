package net.prosavage.hospitalclient.struct;

import java.util.List;

public class HospitalReport {





   private String name;
   private Severity severity;
   private List<Injuries> injuries;
   private long time;
   private String description;

   public HospitalReport(String name, Severity severity, List<Injuries> injuries, long time, String description) {
      this.name = name;
      this.severity = severity;
      this.injuries = injuries;
      this.time = time;
      this.description = description;
   }


   public Severity getSeverity() {
      return severity;
   }

   public void setSeverity(Severity severity) {
      this.severity = severity;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Injuries> getInjuries() {
      return injuries;
   }

   public void setInjuries(List<Injuries> injuries) {
      this.injuries = injuries;
   }

   public long getTime() {
      return time;
   }

   public void setTime(long time) {
      this.time = time;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
