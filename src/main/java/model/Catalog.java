package model;

public class Catalog extends AbstractNamedEntity {

    public Catalog() {
    }

    public Catalog(String name) {
        this(null, name);
    }

    public Catalog(Catalog c) {
        this(c.getId(), c.getName());
    }

    public Catalog(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
