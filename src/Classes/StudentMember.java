package Classes;

import Controllers.Validation;

import java.io.Serializable;

public class StudentMember extends DefaultMember implements Serializable {
    private String school;
    private int parentContact;

    public StudentMember(String membershipId, String memberName, Date joinDate, String gender, String school, int parentContact) {
        super(membershipId, memberName, joinDate, gender);
        this.school = school;
        setParentContact(parentContact);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
            this.school = school;
    }

    public int getParentContact() {

        return parentContact;
    }

    public void setParentContact(int parentContact) {
        if(99999999<parentContact)
            this.parentContact = parentContact;
        else
            throw new IllegalArgumentException("Number must have at least 9 digits.");
    }

    @Override
    public String toString() {
        String membershipId = this.getMembershipId();
        Date joinDate = this.getJoinDate();
        String memberName = this.getMemberName();
        return "StudentMember : "+membershipId+ Validation.spaceRepeat(membershipId,6)+ memberName +Validation.spaceRepeat(memberName,12)+joinDate;
    }
}
