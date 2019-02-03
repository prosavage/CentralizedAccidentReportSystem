package net.prosavage.hospitalclient.struct;

public enum Severity {
   NORMAL(0),
   MILD(1),
   MODERATE(2),
   SEVERE(3);

   private int severity;
   Severity(int severity) {
      this.severity = severity;
   }

   public int getValue() {
      return this.severity;
   }
}
