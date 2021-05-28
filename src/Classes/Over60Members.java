package Classes;

import Controllers.Validation;

import java.io.Serializable;

public class Over60Members extends DefaultMember implements Serializable {
    private int age;
    private int emergencyContact;

    public Over60Members(String membershipId, String memberName, Date joinDate, String gender,int age, int emergencyContact) {
        super(membershipId, memberName, joinDate, gender);
        setAge(age);
        setEmergencyContact(emergencyContact);
    }
    public Over60Members(String membershipId, String memberName, Date joinDate, String gender,int age) {
        super(membershipId, memberName, joinDate, gender);
        setAge(age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age<60 || age>100){
            throw new IllegalArgumentException("Invalid age(60-100)");
        }
        else{
            this.age = age;
        }
    }

    public int getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(int emergencyContact) {
        if(99999999<emergencyContact)
            this.emergencyContact = emergencyContact;
        else
            throw new IllegalArgumentException("Number must have at least 9 digits.");
    }

    @Override
    public String toString() {
        String membershipId = this.getMembershipId();
        Date joinDate = this.getJoinDate();
        String memberName = this.getMemberName();
        return "Over60Member  : "+membershipId+ Validation.spaceRepeat(membershipId,6)+ memberName +Validation.spaceRepeat(memberName,12)+joinDate;
    }
}
