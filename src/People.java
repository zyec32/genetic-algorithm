import java.awt.*;
import java.util.Random;

public class People extends Point {

    World world;

    private int fullness;
    public int getFullness() {
        return fullness;
    }
    public void setFullness(int fullness) {
        this.fullness = fullness;
    }

    Chromosom chromosom;

    Course Sight;

    People(int x, int y, World world, int fullness, Chromosom chromosom) {
        super(true, Color.magenta, PointTypes.PEOPLE, x, y);
        this.world = world;
        this.fullness = fullness;
        this.chromosom = chromosom;
        Sight = Course.UP;
    }

    public void move(Course course) {
        switch (course) {
            case UP:
                if (world.getPointTypes(x, y-1) == PointTypes.SPASE) {
                    world.movePoint(x, y-1, this);
                }
                break;
            case DOWN:
                if (world.getPointTypes(x, y+1) == PointTypes.SPASE) {
                    world.movePoint(x, y+1, this);
                }
                break;
            case RIGHT:
                if (world.getPointTypes(x+1, y) == PointTypes.SPASE) {
                    world.movePoint(x+1, y, this);
                }
                break;
            case LEFT:
                if (world.getPointTypes(x-1, y) == PointTypes.SPASE) {
                    world.movePoint(x-1, y, this);
                }
                break;
                default:
                    break;

        }

    }

    private boolean canMoveInCourse(Course course) {
        switch (course) {
            case UP:
                if (world.getPointTypes(x, y-1) == PointTypes.SPASE){
                    return true;
                }
                return false;
            case DOWN:
                if (world.getPointTypes(x, y+1) == PointTypes.SPASE) {
                    return true;
                }
                return false;
            case LEFT:
                if (world.getPointTypes(x-1, y) == PointTypes.SPASE) {
                    return true;
                }
                return false;
            case RIGHT:
                if (world.getPointTypes(x+1, y) == PointTypes.SPASE) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public boolean nearFoodInCourse(Course course) {
        switch (course) {
            case UP:
                if (world.getPointTypes(x, y-1) == PointTypes.FOOD){
                    return true;
                }
                return false;
            case DOWN:
                if (world.getPointTypes(x, y+1) == PointTypes.FOOD) {
                    return true;
                }
                return false;
            case LEFT:
                if (world.getPointTypes(x-1, y) == PointTypes.FOOD) {
                    return true;
                }
                return false;
            case RIGHT:
                if (world.getPointTypes(x+1, y) == PointTypes.FOOD) {
                    return true;
                }
                return false;
                default:
                    return false;
        }
    }

    public Course nearFood() {
        if (nearFoodInCourse(Course.UP)) {
            return Course.UP;
        }
        if (nearFoodInCourse(Course.DOWN)) {
            return Course.DOWN;
        }
        if (nearFoodInCourse(Course.RIGHT)) {
            return Course.RIGHT;
        }
        if (nearFoodInCourse(Course.LEFT)) {
            return Course.LEFT;
        }
        return null;
    }

    public void moveRandom() {
        Random randNumber = new Random();
        switch (randNumber.nextInt(4)) {
            case 1:
                move(Course.UP);
                break;
            case 2:
                move(Course.DOWN);
                break;
            case 3:
                move(Course.LEFT);
                break;
            case 0:
                move(Course.RIGHT);
                break;
                default:
                    break;
        }
    }

    public void eatInCourse(Course course) {
        switch (course) {
        case UP:
            if (world.getPointTypes(x, y-1) == PointTypes.FOOD){
                Food food = (Food)world.getPoint(x, y-1);
                fullness += food.getVolume();
                world.deletePoint(x, y-1);
            }
            break;
        case DOWN:
            if (world.getPointTypes(x, y+1) == PointTypes.FOOD) {
                Food food = (Food)world.getPoint(x, y+1);
                fullness += food.getVolume();
                world.deletePoint(x, y+1);
            }
            break;
        case LEFT:
            if (world.getPointTypes(x-1, y) == PointTypes.FOOD) {
                Food food = (Food)world.getPoint(x-1, y);
                fullness += food.getVolume();
                world.deletePoint(x-1, y);
            }
            break;
        case RIGHT:
            if (world.getPointTypes(x+1, y) == PointTypes.FOOD) {
                Food food = (Food)world.getPoint(x+1, y);
                fullness += food.getVolume();
                world.deletePoint(x+1, y);
            }
            break;
        default:
            break;
    }
    }

    private void hungry() {
        fullness--;
    }

    public void dead() {
        world.deletePoint(x, y);
    }

    public void turn() {
        switch (Sight) {
            case UP:
                Sight = Course.RIGHT;
                break;
            case DOWN:
                Sight = Course.LEFT;
                break;
            case LEFT:
                Sight = Course.UP;
                break;
            case RIGHT:
                Sight = Course.DOWN;
                break;
                default:
                    break;
        }
    }

    public void run() throws InterruptedException {
        chromosom.setPosition(0);
        boolean ending = false;
        int iter = 0;
        int maxIter = 32;
        while (!ending && iter < maxIter) {
            iter++;
            switch (chromosom.memory.get(chromosom.getPosition())) {
                case 0:
                    move(Sight);
                    chromosom.addToPosition(1);
                    ending = true;
                    break;
                case 1:
                    eatInCourse(Sight);
                    chromosom.addToPosition(1);
                    break;
                case 2:
                    turn();
                    chromosom.addToPosition(1);
                    break;
                    default:
                        break;
            }

        }
        hungry();
        world.setWorldPole();
    }

    //TODO add rotation
}
