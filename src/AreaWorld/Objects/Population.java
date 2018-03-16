package AreaWorld.Objects;

public class Population {
    private People[] peoples;

    public People[] getPeoples() {
        return peoples;
    }

    public Population(People people) {
        peoples = new People[1];
        peoples[0] = people;
    }
    public Population(People[] peoples) {
        this.peoples = peoples;
    }

}
