package day02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    @Test
    void createWithNameTest() {
        Recipe recipe = new Recipe("túrós tészta");
        assertEquals("túrós tészta", recipe.getName());
        assertEquals(0, recipe.getIngredients().size());
        assertNull(recipe.getDescription());
    }

    @Test
    void createWithNameAndDescriptionTest() {
        Recipe recipe = new Recipe("túrós tészta", "Főzzük meg a tésztát, keverjük össze a túróval és tálaljuk.");
        assertEquals("túrós tészta", recipe.getName());
        assertEquals(0, recipe.getIngredients().size());
        assertEquals("Főzzük meg a tésztát, keverjük össze a túróval és tálaljuk.", recipe.getDescription());
    }

    @Test
    void addOneIngredientTest() {
        Recipe recipe = new Recipe("túrós tészta");
        recipe.addIngredients("tészta");
        assertEquals(1, recipe.getIngredients().size());
        assertEquals("tészta", recipe.getIngredients().get(0));
    }

    @Test
    void addMTwoIngredientsTest() {
        Recipe recipe = new Recipe("túrós tészta");
        recipe.addIngredients("tészta", "túró");
        assertEquals(2, recipe.getIngredients().size());
        assertEquals("tészta", recipe.getIngredients().get(0));
        assertEquals("túró", recipe.getIngredients().get(1));
        assertArrayEquals(new String[]{"tészta", "túró"}, recipe.getIngredients().toArray());
    }

    @Test
    void addMoreIngredientsTest() {
        Recipe recipe = new Recipe("túrós tészta");
        recipe.addIngredients("tészta", "túró", "szalonna");
        assertEquals(3, recipe.getIngredients().size());
        assertEquals("tészta", recipe.getIngredients().get(0));
        assertEquals("szalonna", recipe.getIngredients().get(2));
        assertArrayEquals(new String[]{"tészta", "túró", "szalonna"}, recipe.getIngredients().toArray());
    }
}