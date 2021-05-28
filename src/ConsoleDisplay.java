import Controllers.MyGymManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class ConsoleDisplay extends Application {
    public static void main(String[] args){
        launch(args);
    }
    public static MyGymManager gymManager = new MyGymManager();
    @Override
    public void start(Stage primaryStage) throws Exception {
        gymManager.startApplication();
        while(true) {
            String option = MyGymManager.display();
            if (option.equals("A")) {
                gymManager.addMember();
            } else if (option.equals("D")) {
                gymManager.delMember();
            } else if (option.equals("P")) {
                gymManager.printGymMembers();
            } else if (option.equals("S")) {
                gymManager.sortGymMmbers();
            } else if (option.equals("F")) {
                gymManager.saveGymMembers();
            } else if (option.equals("G")) {
                gymManager.displayGymMembers(primaryStage);
                break;
            } else if (option.equals("Q")) {
                primaryStage.show();
                primaryStage.close();
                break;
            }
        }
        System.out.println("Close");
    }
}

