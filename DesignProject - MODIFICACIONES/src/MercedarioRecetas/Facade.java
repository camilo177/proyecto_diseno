package MercedarioRecetas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Facade {
    private Recipe recipe;
    private List<Recipe> recipeList;
    private List<RecipeIngredient> allIngredients; // List to store all ingredients

    public Facade() {
        this.recipe = new Recipe();
        this.recipeList = new ArrayList<>();
        this.allIngredients = new ArrayList<>();
    }

    // Methods for managing RecipeIngredients

    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        if (recipe.getIngredientList() == null) {
            recipe.setIngredientList(new ArrayList<>());
        }

        recipe.getIngredientList().add(recipeIngredient);
        allIngredients.add(recipeIngredient); // Add the ingredient to the allIngredients list
    }

    public void editRecipeIngredient(String oldIngredientName, RecipeIngredient newRecipeIngredient) {
        Iterator<RecipeIngredient> iterator = recipe.getIngredientList().iterator();
        while (iterator.hasNext()) {
            RecipeIngredient recipeIngredient = iterator.next();
            if (recipeIngredient.calculateIngredients().equals(oldIngredientName)) {
                iterator.remove();
                recipe.getIngredientList().add(newRecipeIngredient);
                allIngredients.remove(recipeIngredient); // Remove the old ingredient from allIngredients
                allIngredients.add(newRecipeIngredient); // Add the new ingredient to allIngredients
                break;
            }
        }
    }

    public boolean deleteRecipeIngredient(String ingredientName) {
        Iterator<RecipeIngredient> iterator = recipe.getIngredientList().iterator();
        while (iterator.hasNext()) {
            RecipeIngredient recipeIngredient = iterator.next();
            if (recipeIngredient.calculateIngredients().equals(ingredientName)) {
                iterator.remove();
                allIngredients.remove(recipeIngredient); // Remove the ingredient from allIngredients
                return true; // El ingrediente se eliminó con éxito
            }
        }
        return false; // No se encontró el ingrediente
    }

    // Methods for managing Recipes

    public void addRecipe(Recipe newRecipe) {
        recipeList.add(newRecipe);
    }

    public void editRecipe(String oldRecipeName, Recipe newRecipe) {
        for (int i = 0; i < recipeList.size(); i++) {
            Recipe existingRecipe = recipeList.get(i);
            if (existingRecipe.getNameRecipe().equals(oldRecipeName)) {
                recipeList.set(i, newRecipe);
                break;
            }
        }
    }

    public boolean deleteRecipe(String recipeName) {
        Iterator<Recipe> iterator = recipeList.iterator();
        while (iterator.hasNext()) {
            Recipe existingRecipe = iterator.next();
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                iterator.remove();
                return true; // Recipe deleted successfully
            }
        }
        return false; // Recipe not found
    }

    public void prepareRecipe(String recipeName) {
        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                // Implement logic to prepare the recipe here
                System.out.println("Preparing " + recipeName + "...");
                // Example: Print preparation steps
                System.out.println("Paso 1: Inicio de preparación");
                System.out.println("Paso 2: Receta en preparación");
                System.out.println("Receta preparada!");
                return;
            }
        }
        System.out.println("Recipe not found.");
    }

    public List<RecipeIngredient> viewRecipeIngredient() {
        return recipe.getIngredientList();
    }

    // Method to view all recipes
    public List<Recipe> viewAllRecipes() {
        return recipeList;
    }

    // Method to view all ingredients
    public List<RecipeIngredient> viewAllIngredients() {
        return allIngredients;
    }

}
