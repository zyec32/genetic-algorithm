import java.util.ArrayList;
import java.util.Random;

public class Chromosom {
    /*
    0 - go
    1 - eat
    2 - turn
     */
    private static final int maxComand = 3;
    ArrayList<Integer> memory;
    private int position;

    Chromosom(ArrayList<Integer> memory) {
        position = 0;
        this.memory = memory;
    }

    Chromosom(Chromosom chr){
        position = 0;
        this.memory = chr.memory;
    }
    Chromosom() {
        position = 0;
        Random rand = new Random();
        memory = new ArrayList<Integer>();
        for (int i = 0; i < 32; i++) {
            memory.add(rand.nextInt(maxComand));
        }
    }

    public Chromosom mutation(int i) {
        Chromosom newChrom = new Chromosom(this);
        Random randPosition = new Random();
        Random randComand = new Random();
        newChrom.memory.set(randPosition.nextInt(newChrom.memory.size()), randComand.nextInt(maxComand));
        return newChrom;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }
    public void addToPosition(int add) {
        position += add;
        if (position >= memory.size()){
            position -= memory.size();
        }
    }

}
