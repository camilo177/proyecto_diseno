package MercedarioRecetas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Facade {

    // Lista de recetas
    private List<Recipe> recipeList;

     // Constructor de la clase Facade
    public Facade() {
         this.recipeList = new ArrayList<>();
    }

     // Obtener la lista de recetas
    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    // Establecer la lista de recetas
    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

     // Método para eliminar un ingrediente de una receta por su nombre
    public boolean deleteRecipeIngredient(String ingredientName, String recipeName) {
        for (Recipe recipe : recipeList) {
            if (recipe.getNameRecipe().equals(recipeName)) {
                Iterator<RecipeIngredient> iterator = recipe.getIngredientList().iterator();
                while (iterator.hasNext()) {
                    RecipeIngredient recipeIngredient = iterator.next();
                    if (recipeIngredient.getIngredient().getNameIngredient().equals(ingredientName)) {
                        iterator.remove();
                        System.out.println("Ingrediente '" + ingredientName + "' eliminado de la receta '" + recipeName + "'.");
                        return true;  // Ingrediente eliminado exitosamente
                    }
                }
            }
        }
        System.out.println("No se encontro la receta '" + recipeName + "' o el ingrediente '" + ingredientName + "'.");
        return false; // No se encontró la receta o el ingrediente
    }

    // Método para agregar una receta a la lista de recetas, el metodo evita que se creen recetas con el mismo nombre. 
    public void addRecipe(Recipe newRecipe) {
        boolean recipeExists = false;

        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(newRecipe.getNameRecipe())) {
                recipeExists = true;
                break;
            }
        }

        if (!recipeExists) {
            recipeList.add(newRecipe);
            System.out.println("Receta agregada exitosamente.");
        } else {
            System.out.println("Ya existe una receta con el mismo nombre. La receta no se agrego.");
        }
    }

    // Método para eliminar una receta por su nombre
    public boolean deleteRecipe(String recipeName) {
        Iterator<Recipe> iterator = recipeList.iterator();
        while (iterator.hasNext()) {
            Recipe existingRecipe = iterator.next();
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                iterator.remove();
                return true; // Receta eliminada con éxito
            }
        }
        return false; // Receta no encontrada
    }

    // Método para editar una receta
    public void editRecipe(String oldRecipeName, Recipe newRecipe) {
        for (int i = 0; i < recipeList.size(); i++) {
            Recipe existingRecipe = recipeList.get(i);
            if (existingRecipe.getNameRecipe().equals(oldRecipeName)) {
                recipeList.set(i, newRecipe);
                break;
            }
        }
    }

    //Metodo Preparar recetas
    public void prepareRecipe(String recipeName) {
        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                // Implementación para preparar la receta aquí
                System.out.println("Preparing " + recipeName + "...");
                // Ejemplo: Mostrar los pasos de preparación
                System.out.println("Step 1: Start preparing");
                System.out.println("Step 2: Recipe in progress");
                System.out.println("Recipe prepared!");
                return;
            }
        }
        System.out.println("Recipe not found.");
    }

    // Método para ver la lista de ingredientes en una receta
    public List<RecipeIngredient> viewRecipeIngredient(String recipeName) {
        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                return existingRecipe.getIngredientList();
            }
        }
        return null; // La receta no se encontró
    }

    //Metodo para mirar todas las recetas creadas
    public List<Recipe> viewAllRecipes() {
        return new ArrayList<>(recipeList);
    }

    //Metodo para mirar la lista de ingredientes completa
    public List<RecipeIngredient> viewAllIngredients() {
        List<RecipeIngredient> allIngredients = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            if (recipe.getIngredientList() != null) {
                allIngredients.addAll(recipe.getIngredientList());
            }
        }
        return allIngredients;
    }

}
