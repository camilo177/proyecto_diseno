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


    @Override
    public String toString() {
        return "Recipe{" + "nameRecipe=" + nameRecipe + ", preparationTime=" + preparationTime + ", serves=" + serves + ", ingredientList=" + ingredientList + ", preparationDescription=" + preparationDescription + '}';
    }
}
