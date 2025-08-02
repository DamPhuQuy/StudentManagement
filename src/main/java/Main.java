import com.mycompany.app.Services.ManipulationService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        do {
            int student_id = ManipulationService.startLoginService();

            if (student_id == -1) {
                System.out.println("Exiting...");
                break;
            }

            try {
                int end = ManipulationService.startMainMenu(student_id);
                if (end == -1) {
                    System.out.println("There was an error while trying to start the main menu.");
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } while (true);
    }
}
