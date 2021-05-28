package Controllers;

import Classes.Date;
import Classes.DefaultMember;
import Classes.Over60Members;
import Classes.StudentMember;
import StylesAndInterfaces.GymManager;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyGymManager implements GymManager {
    static Scanner sc = new Scanner(System.in);
    private final List<DefaultMember> gymMembersList= new ArrayList<DefaultMember>();

    public static String display(){
        System.out.println("========================================================================");
        System.out.println("              Select an option a from the list given below");
        System.out.println("========================================================================");
        System.out.println("Type \"A\" | To add a members.");
        System.out.println("Type \"D\" | To remove a members.");
        System.out.println("Type \"P\" | To print the members.");
        System.out.println("Type \"S\" | To sort the members according to the name.");
        System.out.println("Type \"F\" | To save the members.");
        System.out.println("Type \"G\" | To search the memebrs in a table.");
        System.out.println("Type \"Q\" | To close the programme.");
        System.out.println("========================================================================");
        return Controllers.Validation.getOption();
    }

    @Override
    public void addMember() {
        DefaultMember check = new DefaultMember();
        String type = "";
        int iterate = 0;
        if (gymMembersList.size() < 100) {
            while (true) {
                System.out.println("Enter Member type |D - Default Member|S - Student Member|O - Over 60 Member|");
                type = sc.next().toUpperCase();
                if (type.equals("D") || type.equals("S") || type.equals("O")) {
                    break;
                }
            }
            sc = new Scanner(System.in);
            System.out.print("Enter Membership Id : ");
            String membershipId = sc.nextLine();
            System.out.print("Enter Member Name   : ");
            String memberName = sc.nextLine();
            Date joinDate = null;
            String gender = null;
            while (iterate!=5) {
                try {
                    while (iterate == 0) {
                        System.out.print("Enter Joined date(DDMMYYYY) : ");
                        joinDate = Validation.createDate(sc.next());
                        iterate += 1;
                    }
                    while (iterate == 1) {
                        sc = new Scanner(System.in);
                        System.out.print("Enter Gender (M/F) : ");
                        gender = sc.nextLine();
                        check.setGender(gender);
                        iterate += 1;
                    }
                    if (type.equals("S")) {
                        sc = new Scanner(System.in);
                        String school = null;
                        if (iterate == 2) {
                            System.out.print("Enter School Name : ");
                            school = sc.nextLine();
                            iterate += 1;
                        }
                        while (iterate == 3) {
                            sc = new Scanner(System.in);
                            System.out.print("Enter parent contact number (9 digits without 0): ");
                            int parentContact = sc.nextInt();
                            StudentMember studentMember = new StudentMember(membershipId, memberName, joinDate, gender, school, parentContact);
                            gymMembersList.add(studentMember);
                            iterate =5;
                        }
                    } else if (type.equals("O")) {
                        int age=0;
                        while (iterate == 2) {
                            sc = new Scanner(System.in);
                            System.out.print("Enter the age : ");
                            age = sc.nextInt();
                            iterate += 1;
                        }
                        Over60Members over60Members = new Over60Members(membershipId, memberName, joinDate, gender, age);
                        while (iterate == 3) {
                            System.out.print("Enter Emergency contact number (9 digits without 0): ");
                            int emergencyContact = sc.nextInt();
                            over60Members.setEmergencyContact(emergencyContact);
                            iterate = 5;
                        }
                        gymMembersList.add(over60Members);
                    } else {
                        DefaultMember defaultMember = new DefaultMember(membershipId, memberName, joinDate, gender);
                        System.out.println(defaultMember +"  Entry Successfully added.");
                        gymMembersList.add(defaultMember);
                        iterate=5;
                    }
                }catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Number of occupied Spaces : "+gymMembersList.size());
        System.out.println("Number of free Spaces     : "+(100-gymMembersList.size()));
    }

    @Override
    public void delMember() {
        System.out.print("Enter the membership number to delete : ");
        String membershipId = sc.nextLine();
        for(DefaultMember defaultMember : gymMembersList){
            if(defaultMember.getMembershipId().equals(membershipId)){
                System.out.println(defaultMember +"has been deleted successfully ! ");
                gymMembersList.remove(defaultMember);
                break;
            }
        }
        System.out.println("Number of occupied Spaces : "+gymMembersList.size());
        System.out.println("Number of free Spaces     : "+(100-gymMembersList.size()));
    }

    @Override
    public void printGymMembers() {
        System.out.println("Type          : MemId Member Name"+Validation.spaceRepeat("Member Name",12)+"Joined date");
        for(DefaultMember defaultMember : gymMembersList){
            System.out.println(defaultMember);
        }
    }

    @Override
    public void sortGymMmbers() {
        for(int x=0; x < gymMembersList.size()-1; x++){
            for(int y=0; y< gymMembersList.size()-(x+1); y++){
                if(gymMembersList.get(y).compareTo(gymMembersList.get(y+1))>0){
                    DefaultMember temp = gymMembersList.get(y+1);
                    gymMembersList.remove(gymMembersList.get(y+1));
                    gymMembersList.add(y,temp);
                }
            }
        }
        printGymMembers();
    }

    @Override
    public void saveGymMembers(){
        File file = new File("object.dat");
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for(DefaultMember defaultMember: gymMembersList){
                objectOutputStream.writeObject(defaultMember);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void displayGymMembers(Stage primaryStage) {
        int count = 0;
        VBox anchorPane = new VBox(10);
        for(DefaultMember defaultMember: gymMembersList){
            count++;
            Label contents = new Label(defaultMember.toString());
            if(count%2==0)
                contents.setId("one");
            else
                contents.setId("two");
            anchorPane.getChildren().add(contents);
        }
        Scene scene = new Scene(anchorPane);
        primaryStage.setTitle("Gym");
        primaryStage.setScene(scene);
        scene.getStylesheets().addAll("StylesAndInterfaces/tableStyles.css");
        primaryStage.show();
    }

    public void startApplication(){
        try{
            FileInputStream fileInputStream = new FileInputStream("object.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            while(true){
                DefaultMember defaultMember = (DefaultMember) objectInputStream.readObject();
                gymMembersList.add(defaultMember);
            }
        }catch(EOFException e){

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}