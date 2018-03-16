import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class World {
    ArrayList<ArrayList<Point>> worldStore = new ArrayList<ArrayList<Point>>();
    Pole pole;
    int countW, countH;
    ArrayList<People> peoples = new ArrayList<People>();

    World(int countW, int countH, Pole pole) throws InterruptedException {
        this.pole = pole;
        this.countW = countW;
        this.countH = countH;
        createPole();
        createPeoples(5);
        live();
    }

    void createPeoples(int count){
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            peoples.add(this.createPeople(rnd.nextInt(countW-2)+1,rnd.nextInt(countH-2)+1, new Chromosom()));
        }
    }

    void live() throws InterruptedException {
        Random rnd = new Random();
        AtomicReference<People> rem = new AtomicReference<People>();
        while (true) {
            Thread.sleep(100);
            peoples.forEach(people -> {
                try {
                    people.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (people.getFullness() <= 0) {
                    people.dead();
                    People newPeople = createPeople(people.x, people.y, people.chromosom.mutation(2));
                    peoples.add(newPeople);
                    rem.set(people);
                }
            });
            peoples.remove(rem);
            createFood(rnd.nextInt(countW-2)+1,rnd.nextInt(countH-2)+1);
        }
    }

    private void createPole() {
        for (int i = 0; i < countW; i++) {
            ArrayList<Point> inner = new ArrayList<Point>();
            for (int j = 0; j < countH; j++) {
                if (i == 0 || j == 0 || i == countW-1 || j == countH-1) {
                    inner.add(new Wall(i, j));
                }
                else {
                    inner.add(new Spase(i, j));
                }
            }
            worldStore.add(inner);
        }
        setWorldPole();
    }

    public void setWorldPole() {
        pole.setStore(worldStore);
        pole.repaint();
    }

    public PointTypes getPointTypes(int x, int y){
        return getPoint(x, y).getType();
    }

    public Point getPoint(int x, int y) {
        return worldStore.get(x).get(y);
    }

    private Point setPoint(int x, int y, Point point) {
        ArrayList<Point> inner = worldStore.get(x);
        inner.set(y, point);
        worldStore.set(x, inner);
        setWorldPole();
        return point;
    }

    public People createPeople(int x, int y, Chromosom crm) {
        return (People)setPoint(x, y, new People(x, y, this, 100, crm));
    }
    public Food createFood(int x, int y) {
        return (Food)setPoint(x, y, new Food(x, y, 10));
    }
    public Spase createSpase(int x, int y) {
        return  (Spase)setPoint(x, y, new Spase(x, y));
    }
    public Wall createWall(int x, int y) {
        return (Wall)setPoint(x, y, new Wall(x, y));
    }

    public void movePoint(int newX, int newY, Point point) {
        int x = point.getX();
        int y = point.getY();
        createSpase(x, y);
        point.setCoordinats(newX, newY);
        setPoint(newX, newY, point);
    }

    public void deletePoint(int x, int y) {
        createSpase(x, y);
    }
}
