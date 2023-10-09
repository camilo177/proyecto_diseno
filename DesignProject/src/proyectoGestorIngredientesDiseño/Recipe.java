package proyectoGestorIngredientesDiseño;

import java.util.ArrayList;
import java.util.List;

class Recipe {
    private String name;
    private int preparationTime;  // en horas
    private int serves;
    private List<RecipeIngredient> ingredientList;
    private String preparationDescription;

    public Recipe() {
        this.ingredientList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        this.serves = serves;
    }

    public List<RecipeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RecipeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public void setPreparationDescription(String preparationDescription) {
        this.preparationDescription = preparationDescription;
    }

    public void addRecipe() {
        // Implementar según tus necesidades (por ejemplo, añadir esta receta a una lista de recetas en FacadeManager)
    }

    public void editRecipe() {
        // Implementar según tus necesidades
    }

    public boolean deleteRecipe() {
        // Implementar según tus necesidades
        return false; // Valor de retorno temporal
    }

    public void prepareRecipe(int serves) {
        // Implementar según tus necesidades
    }

    public List<Recipe> viewRecipes() {
        // Suponiendo que tienes una lista global de recetas en FacadeManager, devolverías esa lista aquí.
        return null;  // Valor de retorno temporal
    }

    @Override
    public String toString() {
        return name + ", Serves: " + serves;
    }
}
