package Classes;

import Controllers.Validation;

import java.io.Serializable;

public class DefaultMember implements Serializable,Comparable<DefaultMember> {
    private String membershipId;
    private String memberName;
    private Date joinDate;
    private String gender;

    public DefaultMember(String membershipId, String memberName, Date joinDate, String gender){
        this.membershipId = membershipId;
        setMemberName(memberName);
        this.joinDate = joinDate;
        this.gender = gender;
    }
    public DefaultMember(){
    }

    public String getMembershipId(){
        return membershipId;
    }

    public void setMembershipId(String membershipId){
        this.membershipId = membershipId;
    }

    public String getMemberName(){
        return this.memberName;
    }

    public void setMemberName(String memberName){
        this.memberName = memberName;
    }

    public Date getJoinDate(){
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.length()!=1){
            throw new IllegalArgumentException("Gender can hold only one value.");
        }
        else{
            this.gender = gender;
        }
    }

    @Override
    public int compareTo(DefaultMember defaultMember) {
        return this.memberName.compareTo(defaultMember.getMemberName());
    }

    @Override
    public String toString() {
        return "DefaultMember : "+membershipId+Validation.spaceRepeat(membershipId,6)+ memberName +Validation.spaceRepeat(memberName,12)+joinDate;
    }
}
