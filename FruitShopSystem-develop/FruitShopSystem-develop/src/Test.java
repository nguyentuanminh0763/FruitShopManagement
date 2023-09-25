
import controller.ManagingFruit;
import java.util.ArrayList;
import javax.crypto.AEADBadTagException;
import model.Fruit;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nguyen Quang Hau
 */
public class Test {
    public static void main(String[] args) {
        ManagingFruit programming = new ManagingFruit();
        
        // fake data
           ArrayList<Fruit> fruit = new ArrayList<>();
           fruit.add(new Fruit("f1", "Orange", 10, 78, "China"));
           fruit.add(new Fruit("f2", "Peach", 3, 889, "VietNam"));
           fruit.add(new Fruit("f3", "Mango", 24, 30, "Thailand"));
           fruit.add(new Fruit("f4", "Grape", 5, 80, "Indonesia"));
           programming.setList(fruit);
        // end fake data
        programming.run();
    }
}
