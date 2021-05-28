package StylesAndInterfaces;

import javafx.stage.Stage;

import java.io.FileNotFoundException;

public abstract interface GymManager {
    public void addMember();
    public void delMember();
    public void printGymMembers();
    public void sortGymMmbers ();
    public void saveGymMembers() throws FileNotFoundException;
    public void displayGymMembers(Stage primaryStage);
}
