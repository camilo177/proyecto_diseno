package MercedarioRecetas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("unused")
public class Facade {

    // Lista de recetas
    private List<Recipe> recipeList;
    private final List<Ingredient> ingredientList;
    private final List<Ingredient> allIngredients;

    // Constructor de la clase Facade
    public Facade() {
        this.recipeList = new ArrayList<>();
        this.ingredientList = new ArrayList<>();
        this.allIngredients = new ArrayList<>();
    }

    // Obtener la lista de recetas
    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    // Establecer la lista de recetas
    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
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
        } else {
            System.out.println("Ya existe una receta con el mismo nombre. La receta no se agrego.");
        }
    }

    // Método para Eliminar una receta a la lista de recetas, el metodo elimina los ingredintes sino se estan usando en ninguna receta.
    public boolean deleteRecipe(String recipeName) {
        Iterator<Recipe> iterator = recipeList.iterator();
        while (iterator.hasNext()) {
            Recipe existingRecipe = iterator.next();
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                // Obtenemos la lista de ingredientes de la receta que estamos eliminando
                List<RecipeIngredient> recipeIngredients = existingRecipe.getIngredientList();

                // Eliminamos cada uno de los ingredientes de la lista central de ingredientes
                for (RecipeIngredient recipeIngredient : recipeIngredients) {
                    Ingredient ingredient = recipeIngredient.getIngredient();
                    deleteIngredient(ingredient.getNameIngredient());
                }

                iterator.remove(); // Eliminamos la receta
                return true; // Receta eliminada con éxito
            }
        }
        return false; // Receta no encontrada
    }

    // Método para editar una receta
    public boolean editRecipe(String oldRecipeName, Recipe newRecipe) {
        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(oldRecipeName)) {
                existingRecipe.setNameRecipe(newRecipe.getNameRecipe());
                existingRecipe.setPreparationTime(newRecipe.getPreparationTime());
                existingRecipe.setServes(newRecipe.getServes());
                existingRecipe.setIngredientList(newRecipe.getIngredientList());
                existingRecipe.setPreparationDescription(newRecipe.getPreparationDescription());
                return true; // Receta editada con éxito
            }
        }
        return false; // Receta no encontrada
    }//Falta

    // Método para preparar recetas
    public void prepareRecipe(String recipeName) {
        boolean recipeFound = false;

        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                recipeFound = true;
                System.out.println("======================================");
                System.out.println("\u001B[36mPreparando la receta: " + existingRecipe.getNameRecipe() + "\u001B[0m");
                System.out.println("Tiempo de Preparación (horas): " + existingRecipe.getPreparationTime() + " horas");
                System.out.println("Porciones por Persona: " + existingRecipe.getServes());
                System.out.println("\u001B[36mIngredientes de la Receta:\u001B[0m");

                for (RecipeIngredient recipeIngredient : existingRecipe.getIngredientList()) {
                    Ingredient ingredient = recipeIngredient.getIngredient();
                    System.out.println("- " + ingredient.getNameIngredient() + ": " + recipeIngredient.getQuantity());
                    System.out.println("  Costo por unidad (pesos): $" + ingredient.getValuePerUnit());
                    System.out.println("  Calorias por unidad: " + ingredient.getCaloriesPerUnit());
                    System.out.println("  Calorias totales: " + recipeIngredient.calculateCalories());
                }

                System.out.println("1. Iniciar la preparacion.");
                System.out.println("Pasos de Preparacion:");
                System.out.println("\u001B[36mDescripcion de Preparacion:\u001B[0m " + existingRecipe.getPreparationDescription());
                System.out.println("======================================");
                // Agrega aquí los pasos de preparación si es necesario
                System.out.println("2. Seguir los pasos de la receta.");
                System.out.println("3. ¡Receta preparada!");

                break; // Salir del bucle una vez que se encuentra la receta
            }
        }

        if (!recipeFound) {
            System.out.println("Receta no encontrada.");
        }
    }

    //Metodo para mirar todas las recetas creadas
    public List<Recipe> viewAllRecipes() {
        return new ArrayList<>(recipeList);
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

    // Método para ver la lista de ingredientes en una receta
    public void viewRecipeIngredient(String recipeName) {
        for (Recipe existingRecipe : recipeList) {
            if (existingRecipe.getNameRecipe().equals(recipeName)) {
                System.out.println("======================================");
                System.out.println("\u001B[36mIngredientes de la Receta '" + recipeName + "':\u001B[0m");
                for (RecipeIngredient recipeIngredient : existingRecipe.getIngredientList()) {
                    Ingredient ingredient = recipeIngredient.getIngredient();
                    System.out.println("\u001B[36m- " + ingredient.getNameIngredient() + "\u001B[0m");
                    System.out.println("  Cantidad: " + recipeIngredient.getQuantity());
                    System.out.println("  Costo por unidad: $" + ingredient.getValuePerUnit());
                    System.out.println("  Costo total: $" + recipeIngredient.calculateCost());
                    System.out.println("  Calorías por unidad: " + ingredient.getCaloriesPerUnit());
                    System.out.println("  Calorias totales: " + recipeIngredient.calculateCalories());
                    System.out.println("--------------------------------------");
                }
                System.out.println("======================================");
                return;
            }
        }
        System.out.println("Receta no encontrada.");
    }//Falta

    //Metodo para agregar un ingrediente de manera individual
    public void addIngredient(Ingredient ingredient) {
        // Verificar si el ingrediente ya existe en la lista antes de agregarlo
        if (!ingredientList.contains(ingredient)) {
            ingredientList.add(ingredient);
            allIngredients.add(ingredient); // Agregar a la lista central
            System.out.println("Ingrediente Creado y agregado exitosamente: " + ingredient.getNameIngredient());
        } else {
            System.out.println("El ingrediente ya existe en la lista.");
        }
    }

    //Metodo para aEliminar un ingrediente que se agrego de manera individual (Si el ingrediente es usado en una receta no se puede eliminar) 
    public boolean deleteIngredient(String ingredientName) {
        Ingredient ingredientToRemove = null;
        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getNameIngredient().equals(ingredientName)) {
                ingredientToRemove = ingredient;
                break; // Sal del bucle una vez que encuentres el ingrediente
            }
        }

        if (ingredientToRemove != null) {
            // Verifica si el ingrediente está siendo utilizado en alguna receta
            boolean usedInRecipe = false;
            for (Recipe recipe : recipeList) {
                for (RecipeIngredient recipeIngredient : recipe.getIngredientList()) {
                    if (recipeIngredient.getIngredient().equals(ingredientToRemove)) {
                        usedInRecipe = true;
                        break;
                    }
                }
            }

            if (!usedInRecipe) {
                allIngredients.remove(ingredientToRemove);
                ingredientList.remove(ingredientToRemove);
                System.out.println("Ingrediente '" + ingredientName + "' eliminado exitosamente.");
                return true; // Ingrediente eliminado exitosamente
            } else {
                System.out.println("El ingrediente está siendo utilizado en una receta y no puede ser eliminado.");
                return false; // No se puede eliminar el ingrediente utilizado en una receta
            }
        } else {
            System.out.println("No se encontró el ingrediente '" + ingredientName + "'.");
            return false; // No se encontró el ingrediente
        }
    }

    // Método para editar un ingrediente
    public boolean editIngredient(String oldIngredientName, Ingredient newIngredient) {
        // Busca el ingrediente en la lista de ingredientes
        for (Ingredient existingIngredient : allIngredients) {
            if (existingIngredient.getNameIngredient().equals(oldIngredientName)) {
                // Verifica si el nuevo nombre ya está en uso
                if (!oldIngredientName.equals(newIngredient.getNameIngredient())
                        && allIngredients.stream().anyMatch(i -> i.getNameIngredient().equals(newIngredient.getNameIngredient()))) {
                    System.out.println("Ya existe un ingrediente con el nuevo nombre. No se puede editar.");
                    return false; // Ingrediente no editado
                }

                existingIngredient.setNameIngredient(newIngredient.getNameIngredient());
                existingIngredient.setValuePerUnit(newIngredient.getValuePerUnit());
                existingIngredient.setCaloriesPerUnit(newIngredient.getCaloriesPerUnit());
                System.out.println("Ingrediente editado con éxito.");
                return true; // Ingrediente editado con éxito
            }
        }
        System.out.println("No se encontró el ingrediente con el nombre '" + oldIngredientName + "'.");
        return false; // Ingrediente no encontrado
    }

    //Metodo para mirar la lista de ingredientes completa
    public List<Ingredient> viewAllIngredients() {
        return new ArrayList<>(allIngredients);
    }

    //Este metodo tiene como objetivo buscar una receta en la lista de recetas por su nombre y devolver la receta correspondiente si se encuentra
    public Recipe getRecipeByName(String recipeName) {
        for (Recipe recipe : recipeList) {
            if (recipe.getNameRecipe().equals(recipeName)) {
                return recipe; // Retorna la receta si se encuentra por nombre
            }
        }
        return null; // Retorna null si la receta no se encuentra
    }

}
