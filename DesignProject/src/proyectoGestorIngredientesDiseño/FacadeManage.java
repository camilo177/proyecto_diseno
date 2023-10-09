package proyectoGestorIngredientesDiseño;

import java.util.ArrayList;
import java.util.List;

public class FacadeManage {
    private List<Ingredient> ingredients;

    public FacadeManage() {
        this.ingredients = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public List<Ingredient> viewIngredients() {
        return ingredients;
    }

    public Ingredient getIngredientByName(String name) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getName().equalsIgnoreCase(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public void setIngredientName(String name, String newName) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setName(newName);
        }
    }

    public int getIngredientUnits(String name) {
        Ingredient ingredient = getIngredientByName(name);
        return (ingredient != null) ? ingredient.getUnit() : -1;
    }

    public void setIngredientUnits(String name, int unit) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setUnit(unit);
        }
    }

    // Continúa con los otros métodos siguiendo la misma lógica...

    public boolean deleteIngredient(String name) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            return ingredients.remove(ingredient);
        }
        return false;
    }
}
