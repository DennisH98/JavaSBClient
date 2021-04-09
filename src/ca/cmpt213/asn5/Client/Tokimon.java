package ca.cmpt213.asn5.Client;

/**
 * This class is the Tokimon POJO, to create Tokimon Objects
 * @author Dennis Huynh
 * @author 3013279204
 */

public class Tokimon {
    private int id;
    private String name;
    private double weight;
    private double height;
    private String ability;
    private double strength;
    private String color;


    public Tokimon(int id, String name, double weight, double height, String ability, double strength, String color) {
        this.id=id;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.ability = ability;
        this.strength = strength;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public String getAbility() {
        return ability;
    }

    public double getStrength() {
        return strength;
    }

    public String getColor() {
        return color;
    }
}
