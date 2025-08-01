import com.mycompany.app.Services.MenuService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int student_id = -1;
        do {
            MenuService.displayLoginMenu();

            System.out.print("Your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    student_id = MenuService.LoginImplement();
                }
                case 2 -> {
                    boolean check = MenuService.RegisterImplement();
                    if (check) {
                        student_id = MenuService.LoginImplement();
                    }
                }
                default -> {
                    System.out.println("Invalid choice.");
                }
            }

            if (student_id != -1) {
                System.out.println("Logged in successfully.");
                break;
            } else {
                System.out.println("You have failed to log in. Please try again.");
            }

        } while (true);
    }
}
