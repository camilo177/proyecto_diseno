package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private String nameRecipe;
    private int preparationTime;  // en horas
    private int serves;
    private List<RecipeIngredient> ingredientList; //la lista de ingredientes tienen que ir creada en esta case o en recipeingredintes?
    private String preparationDescription;

    public Recipe() {
    }

    public Recipe(String nameRecipe, int preparationTime, int serves, List<RecipeIngredient> ingredientList, String preparationDescription) {
        this.nameRecipe = nameRecipe;
        this.preparationTime = preparationTime;
        this.serves = serves;
        this.ingredientList = ingredientList;
        this.preparationDescription = preparationDescription;
    }

    public String getNameRecipe() {
        return nameRecipe;
    }

    public void setNameRecipe(String nameRecipe) {
        this.nameRecipe = nameRecipe;
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

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        if (ingredientList == null) {
            ingredientList = new ArrayList<>();
        }

        ingredientList.add(recipeIngredient);
    }

    public void editRecipeIngredient(String oldIngredientName, RecipeIngredient newRecipeIngredient) {
    for (RecipeIngredient recipeIngredient : ingredientList) {
        if (recipeIngredient.ingredient.getNameIngredient().equals(oldIngredientName)) {
            // Reemplaza el ingrediente antiguo por el nuevo
            ingredientList.remove(recipeIngredient);
            ingredientList.add(newRecipeIngredient);
            break;
            }
        }
    }

    public boolean deleteRecipeIngredient(String ingredientName) {
    for (RecipeIngredient recipeIngredient : ingredientList) {
        if (recipeIngredient.ingredient.getNameIngredient().equals(ingredientName)) {
            ingredientList.remove(recipeIngredient);
            return true; // El ingrediente se eliminó con éxitoo
            }
        }
        return false; // No se encontró el ingrediente
    }

    public void prepareRecipe() {
        //Que hacemos con este metodo para que serves?
    }

    public List<RecipeIngredient> viewRecipeIngredient() {
        return ingredientList;
    }

    @Override
    public String toString() {
        return "Recipe{" + "nameRecipe=" + nameRecipe + ", preparationTime=" + preparationTime + ", serves=" + serves + ", ingredientList=" + ingredientList + ", preparationDescription=" + preparationDescription + '}';
    }
}
