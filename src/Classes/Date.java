package Classes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Date implements Serializable {
    private int day;
    private int month;
    private int year;
    private static List<Integer> monthWith30 = Arrays.asList(4,6,9,11);

    public Date(int day, int month,int year){
        setMonth(month);
        setYear(year);
        setDay(day);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if(day<=0 || day>31){
            throw new IllegalArgumentException("Basically a month cannot hold more than 31 days.");
        }
        else if((this.year%4==0) && (this.month==2) && (day>29)){
            throw new IllegalArgumentException("29 Days in February Leap year.");
        }
        else if((this.year%4!=0) && (this.month==2) && (day>28)){
            throw new IllegalArgumentException("28 days a Basic February.");
        }
        else if(monthWith30.contains(this.month) && day>30){
            throw new IllegalArgumentException("This month doesn't hold 31 days.");
        }
        else{
            this.day = day;
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month<=0 || month>12){
            throw new IllegalArgumentException("Enter a valid month.");
        }
        else{
            this.month = month;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if(year<2000 || year>2099){
            throw new IllegalArgumentException("Year should be within 2000 to 2099");
        }
        else{
            this.year =year;
        }
    }

    @Override
    public String toString() {
        return day + "|" + month + "|" + year;
    }
}
