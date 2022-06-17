package controlConsole;

import java.util.Scanner;

import static util.Menu.startMenu;

public class StartControlConsole {
    Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println(startMenu);

        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                    new PetControlConsol(scanner).aboutPets();
                    start();
                case "2":
                    new StoreControlConsole(scanner).aboutOrder();
                    start();
                    break;
                case "3":
                    new UserControlConsole(scanner).aboutUser();
                    start();
                    break;
            }
        }
    }

}
