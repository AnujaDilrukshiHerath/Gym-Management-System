package Controllers;

import Classes.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Validation {

    static Scanner sc = new Scanner(System.in);
    public static String getOption() {
        List<String> validOptions = Arrays.asList("A","D","P","S","F","G","Q");
        String option;
        while(true) {
            System.out.print("Enter your option : ");
            option = sc.next().toUpperCase();
            if(validOptions.contains(option))
                break;
            else if(option.isEmpty())
                System.out.println("Please select an option");
            else if (option.length() > 1)
                System.out.println("Please Type a single character");
            else if(option.length()==1)
                System.out.println("The character you have typed does'nt points any option");
        }
        return option;
    }

    public static String spaceRepeat(String word,int constantSpace){
        int noOfSpaces = word.length();
        String spacees ="";
        for (int i=0;i<(constantSpace-noOfSpaces);i++)
            spacees += " ";
        return spacees;
    }

    public static Date createDate(String date){
        int day   = Integer.parseInt(date.substring(0,2));
        int month = Integer.parseInt(date.substring(2,4));
        int year  = Integer.parseInt(date.substring(4));
        Date joinDate = new Date(day,month,year);
        joinDate.setDay(day);
        System.out.println(day +" "+month+" "+year);
        return joinDate;
    }

}
