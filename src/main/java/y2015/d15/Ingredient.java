package y2015.d15;

public class Ingredient {


    private String name;
    private int capacity;
    private int durability;
    private int flavor;
    private int texture;
    private int calories;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public int getFlavor() {
        return flavor;
    }

    public void setFlavor(int flavor) {
        this.flavor = flavor;
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
