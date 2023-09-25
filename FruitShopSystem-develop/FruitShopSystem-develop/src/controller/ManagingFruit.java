/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Validation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Fruit;
import view.Menu;

/**
 *
 * @author Nguyen Quang Hau
 */
public class ManagingFruit extends Menu<String> {

    private ArrayList<Fruit> list;
    private HashMap<String, ArrayList<Fruit>> billList;

    public ManagingFruit() {
        super("=========Fruit System========", new String[]{"Create", "View Order", "Shopping", "Exit"});
        list = new ArrayList<>();
        billList = new HashMap<>();
        val = new Validation();
    }

    public ManagingFruit(ArrayList<Fruit> list, HashMap<String, ArrayList<Fruit>> billList) {
        this.list = list;
        this.billList = billList;
    }

    public void setList(ArrayList<Fruit> list) {
        this.list = list;
    }
    
    

    @Override
    public void excute(int n) {

        switch (n) {
            case 1:
                createFruit();
                break;
            case 2:
                viewOrder();
                break;
            case 3:
                buy();
                break;
        }
    }

    // create fruit 
    public void createFruit() {
        create();
        String choice = val.getString("Do you want to continue(Y/N)", "Y|N");

        if (choice.equals("Y")) {
            createFruit();
        }
    }

    private void create() {
        Fruit fruit = new Fruit();

        // get random id
        while (true) {
            fruit.setId("F" + val.random(1, 10000));
            if (!isIdExisted(fruit.getId())) {
                break;
            }
        }

        fruit.setName(val.getString("Fruit  name: "));
        fruit.setOrigin(val.getString("Country: "));
        fruit.setPrice(val.getDoubleData("Price: ", 0.1));
        fruit.setQuantity(val.getIntData("Quantity: ", 1));
        list.add(fruit);
    }

    private boolean isIdExisted(String id) {

        for (Fruit fruit : list) {
            if (fruit.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    // display bill
    private void viewOrder() {
        System.out.println("\n----------View Order----------");
        for (String customer : billList.keySet()) {
            System.out.println("Customer: " + customer);
            displayBills(billList.get(customer));
        }
        
        System.out.println("----------------------------------");
    }
    
    private void displayBills(ArrayList<Fruit> list) {
        int no = 0;
        double total = 0;
        System.out.printf("%10s%3s%10s%3s%10s%3s%10s", "Product", "|", "Quantity", "|", "Price", "|", "Amount");
        System.out.println("");
        for (Fruit fruit : list) {
            no++;
            total += fruit.getPrice()*fruit.getQuantity();
            System.out.printf("%11s%11s%13s%13s", no +". "+ fruit.getName(), "" + fruit.getQuantity(), fruit.getPrice() + "$", "" + fruit.getQuantity());
            System.out.println("");
        }
        System.out.println("Total: " + total+"$");
    }

    

    public void buy() {
        ArrayList<Fruit> selectedList = new ArrayList<>();

        do {
            System.out.println("\nList of fruit: ");
            fruitDisplay();
            int select = val.getIntData("Your selection: ", 1, list.size());
            Fruit selectedFruit = list.get(select - 1);
            int quantity = 0;
            System.out.println("You selected: " + selectedFruit.getName());
            quantity = val.getIntData("Please input quantity: ", 1, selectedFruit.getQuantity());
            selectedList.add(new Fruit(selectedFruit.getId(), selectedFruit.getName(), selectedFruit.getPrice(), quantity, selectedFruit.getOrigin()));
        } while (val.getString("Do you want to countinue(Y/N)", "Y|N").equalsIgnoreCase("Y"));
        
        displayBills(selectedList);
        String cus = val.getString("Input your name: ");
        billList.put(cus, selectedList);
    }

    private void fruitDisplay() {
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ | ");
        for (int i = 0; i < list.size(); i++) {
            Fruit f = list.get(i);
            System.out.printf("%6s%17s%19s%15s", (i + 1) + "", f.getName(), f.getOrigin(), f.getPrice() + "$");
            System.out.println("\n");
        }
    }

    
    
    public static void main(String[] args) {
        ManagingFruit fruitSystem = new ManagingFruit();
    }

}
