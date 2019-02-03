package net.prosavage.hospitalclient.struct;

public enum Injuries {

   HEAD("head"),
   LEFT_ARM("left_arm"),
   RIGHT_ARM("right_arm"),
   RIGHT_LEG("right_leg"),
   TORSO("right_leg"),
   LEFT_LEG("left_leg");








   private String injury;
   private Injuries(String injury) {
      this.injury = injury;
   }


}
