package net.prosavage.carsclient.util;

public enum Body {

   HEAD(false), CHEST(false), RIGHTARM(false), LEFTARM(false), WAIST(false), RIGHTLEG(false), LEFTLEG(false);


   private boolean status;

   Body(boolean status) {
      this.status = status;
   }


   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }
}
