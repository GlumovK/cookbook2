package model;

import java.util.HashSet;
import java.util.Set;

public class Recipe extends AbstractNamedEntity {

    private String description;

    private String cookAlgorithm;

    private int rating;

    private User user;

    private int userId;

    private Set<Ingredient> ingredients = new HashSet<>();

    private Set<Catalog> catalogs = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String name, String description, String cookAlgorithm) {
        this(null, name, description, cookAlgorithm, 0);
    }

    public Recipe(Integer id, String name, String description, String cookAlgorithm, int rating) {
        super(id, name);
        this.description = description;
        this.cookAlgorithm = cookAlgorithm;
        this.rating = rating;
    }
    public Recipe(Integer id, String name, String description, String cookAlgorithm, int rating, int userId) {
        super(id, name);
        this.description = description;
        this.cookAlgorithm = cookAlgorithm;
        this.rating = rating;
        this.userId = userId;
    }
    public Recipe(Integer id, String name, String description, String cookAlgorithm, int rating, User user) {
        super(id, name);
        this.description = description;
        this.cookAlgorithm = cookAlgorithm;
        this.rating = rating;
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCookAlgorithm() {
        return cookAlgorithm;
    }

    public void setCookAlgorithm(String cookAlgorithm) {
        this.cookAlgorithm = cookAlgorithm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name=" + name +
                ", description='" + description +
                ", cookAlgorithm=" + cookAlgorithm +
                ", rating=" + rating +
                '}';
    }
}
