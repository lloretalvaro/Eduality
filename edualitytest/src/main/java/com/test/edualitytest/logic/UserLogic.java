package com.test.edualitytest.logic;

public class UserLogic {
    String email;
    String name;
    String password;
    boolean expert;
    int goldamount;
    int silveramount;
    int copperamount;

    public UserLogic(String e,String n,String p,boolean b) {
        email=e;
        name=n;
        password=p;
        expert=b;
    }

    public boolean isExpert() {
        return expert;
    }

    public void awardsAvaliable() {
        if(expert=false) {
            System.out.println("User is not an expert");
        }else {
            System.out.println("The user has "+goldamount+" gold awards");
            System.out.println("The user has "+silveramount+" gold awards");
            System.out.println("The user has "+copperamount+" copper awards");
        }
    }

    public String getemail() {
        return email;
    }

    public String getname() {
        return name;
    }

    public void giveAward(ContentLogic content,AwardLogic award) {
        if( isExpert() && content!=null && award!=null) {
        	award.applyAward(content);
        }
    }

}