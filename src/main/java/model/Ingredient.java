package model;

public class Ingredient extends AbstractNamedEntity {

    public Ingredient() {
    }
    public Ingredient(String name) {
        this(null, name);
    }

    public Ingredient(Ingredient i) {
        this(i.getId(), i.getName());
    }

    public Ingredient(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}


