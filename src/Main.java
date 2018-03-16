import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int H = 40;
        int W = 20;
        int sizeH = 15;
        int sizeW = 15;
        int spase = 1;
        int countH = 50;
        int countW = 90;
        JFrame frame = new JFrame("Main");
        Pole pole = new Pole(sizeH, sizeW, spase);
        frame.add(pole);
        frame.setSize(W+countW*(sizeW+spase),H+countH*(sizeH+spase));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        World world = new World(countW, countH, pole);
        world.createFood(20, 20);


//        while (people.nearFood() == null) {
//            Thread.sleep(10);
//            people.moveRandom();
//        }
//
//        Thread.sleep(1000);
//        people.eatInCourse(people.nearFood());


//        Random randNumber = new Random();
//
//        while (true) {
//            Thread.sleep(1000);
//            int Hrand = randNumber.nextInt(countH - 2) + 1;
//            int Wrand = randNumber.nextInt(countW - 2) + 1;
//            ArrayList<Point> inner = list.get(Wrand);
//            inner.set(Hrand, new Food());
//            list.set(Wrand, inner);
//            pole.setStore(list);
//            pole.repaint();
//        }

//        Random randNumber = new Random();
//        int Hrand = randNumber.nextInt(countH - 2) + 1;
//        int Wrand = randNumber.nextInt(countW - 2) + 1;
//        ArrayList<Point> inner = list.get(Wrand);
//        inner.set(Hrand, new People());
//        list.set(Wrand, inner);
//        pole.setStore(list);
//        pole.repaint();
//
//        while (true) {
//            Thread.sleep(1000);
//
//        }
    }
}
