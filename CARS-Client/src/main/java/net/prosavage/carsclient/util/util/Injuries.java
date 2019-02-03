package net.prosavage.carsclient.util.util;

public enum Injuries {

   HEAD("head"),
   CHEST("chest"),
   LEFT_ARM("left_arm"),
   RIGHT_ARM("right_arm"),
   RIGHT_LEG("right_leg"),
   TORSO("right_leg"),
   LEFT_LEG("left_leg");


   private String injury;

   Injuries(String injury) {
      this.injury = injury;
   }


}
