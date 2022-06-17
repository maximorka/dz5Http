package util;

public class Menu {

    public static final String startMenu = "Choose what request you want do?(Enter number section menu)\n" +
            "1: Everything about Pet\n" +
            "2: Petstore orders\n" +
            "3: User operation\n"+
            "Any character: Exit\n";

    public static final String menuRequestForPet = "Ok! Choose:\n" +
            "1: Uploads image for pet\n" +
            "2: Add new pet to the store\n" +
            "3: Update an existing pet\n" +
            "4: Find pets by status\n" +
            "5: Find pet by id\n" +
            "6: Updates a pet in the store with form data\n" +
            "7: Deletes a pet\n" +
            "8: Exit\n";

    public static final String menuRequestForOrder = "Ok! Choose:\n" +
            "1: Place an order for a pet\n" +
            "2: Find purchase order by ID\n" +
            "3: Delete purchase order by ID\n" +
            "4: Returns pet inventories by status\n" +
            "5: Exit\n";

    public static final String menuRequestForUser = "Ok! Choose:\n" +
            "1: Creates list of users with given input array\n" +
            "2: Creates list of users with given input list\n" +
            "3: Get user by user name\n" +
            "4: Update user\n" +
            "5: Delete user\n" +
            "6: Logs user into the system\n" +
            "7: Logs out current logged in user session\n" +
            "8: Create user\n" +
            "9: Exit\n";
}
