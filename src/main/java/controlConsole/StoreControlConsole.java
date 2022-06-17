package controlConsole;

import lombok.RequiredArgsConstructor;
import storage.order.dao.OrderDao;
import storage.order.entity.Order;
import storage.order.entity.StatusOrder;

import java.util.Scanner;

import static util.Menu.menuRequestForOrder;

@RequiredArgsConstructor
public class StoreControlConsole {
    public final Scanner scanner;
    private OrderDao orderDao = new OrderDao();

    public void aboutOrder() {

        System.out.println(menuRequestForOrder);
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                    System.out.println("Place an order = " + placeAnOrder());
                    break;
                case "2":
                    System.out.println("Find purchase order = " + findOrderById());
                    break;
                case "3":
                    System.out.println("Delete purchase order = " + deleteOrderById());
                    break;
                case "4":
                    System.out.println("Returns pet inventories = " + returnsPetInventories());
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Incorect, please try again ");
            }
        }
    }

    private String placeAnOrder(){
        String responce = "";
        try {
            int id = Integer.parseInt(enterId());
            int petId = Integer.parseInt(enterIdPet());
            int quantity = Integer.parseInt(enterQuantity());
            StatusOrder statusOrder = enterStatusOrder();
            boolean complete = enterCompleteOrder();

            responce = orderDao.placeAnOrder(Order.builder().id(id).petId(petId).quantity(quantity).status(statusOrder).complete(complete).build());
        } catch (Exception e) {
            e.printStackTrace();
            aboutOrder();
        }
        return responce;
    }

    private String findOrderById(){
        String responce = "";
        try {
            int id = Integer.parseInt(enterId());
            responce = orderDao.findPurchaseOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            aboutOrder();
        }
        return responce;
    }
    private String deleteOrderById(){
        String responce = "";
        try {
            int id = Integer.parseInt(enterId());
            responce = orderDao.deletePurchaseOrderById(id);
        } catch (Exception e) {
            e.printStackTrace();
            aboutOrder();
        }
        return responce;
    }

    private String returnsPetInventories(){
        String responce = "";
        try {
            responce = orderDao.returnsPetInventoriesByStatus();
        } catch (Exception e) {
            e.printStackTrace();
            aboutOrder();
        }
        return responce;

    }

    private String enterId() {
        System.out.println("Enter id:");
        String id = "";

        if (scanner.hasNextLine()) {
            id = scanner.nextLine();
        }
        if (id.matches("\\d+"))
            System.out.println("Ok");
        else {
            System.out.println("Incorrect id");
            id = "";

        }
        return id;
    }
    private String enterIdPet() {
        System.out.println("Enter idPet:");
        String id = "";

        if (scanner.hasNextLine()) {
            id = scanner.nextLine();
        }
        if (id.matches("\\d+"))
            System.out.println("Ok");
        else {
            System.out.println("Incorrect id");
            id = "";

        }
        return id;
    }


    private String enterQuantity(){
        System.out.println("Enter quantity:");
        String quantity = "";

        if (scanner.hasNextLine()) {
            quantity = scanner.nextLine();
        }
        if (quantity.matches("\\d+"))
            System.out.println("Ok");
        else {
            System.out.println("Incorrect id");
            quantity = "";
        }
        return quantity;
    }

    private StatusOrder enterStatusOrder(){
        System.out.println("Enter status order (placed, approved, delivered):");
        String status = "";

        if (scanner.hasNextLine()) {
            status = scanner.nextLine();
        }
       StatusOrder statusOrder = StatusOrder.valueOf(status);
        return statusOrder;
    }

    private boolean enterCompleteOrder() throws Exception {
        System.out.println("Enter complete order (Y,N):");
        boolean complete = false;
        if (scanner.hasNextLine()) {
            String readData = scanner.nextLine();
            if(readData.equals("Y")){
                complete = true;}
            else if (readData.equals("N")){
                complete = false;}
            else{
                throw new Exception();
            }
        }
        return complete;

    }
}
