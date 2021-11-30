package day02;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String name;
    private List<String> ingredients = new ArrayList<>();
    private String description;

    public Recipe(String name) {
        this.name = name;
    }

    public Recipe(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addIngredients(String ingredient, String... ingredients) {
        this.ingredients.add(ingredient);
        for (String actual : ingredients) {
            this.ingredients.add(actual);
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }
}
