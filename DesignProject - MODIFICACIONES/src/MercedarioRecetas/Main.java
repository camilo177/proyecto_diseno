package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // Crear ingredientes
        Ingredientes ingrediente1 = new Ingredientes();
        ingrediente1.setNameIngredient("Harina");
        ingrediente1.setUnit(500);
        ingrediente1.setValuePerUnit(2.5);
        ingrediente1.setPurchasePlace("Supermercado");
        ingrediente1.setCaloriesPerUnit(350);

        Ingredientes ingrediente2 = new Ingredientes();
        ingrediente2.setNameIngredient("Azúcar");
        ingrediente2.setUnit(250);
        ingrediente2.setValuePerUnit(1.0);
        ingrediente2.setPurchasePlace("Supermercado");
        ingrediente2.setCaloriesPerUnit(400);

        // Crear ingredientes para recetas
        RecipeIngredient recipeIngredient1 = new RecipeIngredient(300, ingrediente1);
        RecipeIngredient recipeIngredient2 = new RecipeIngredient(150, ingrediente2);

        // Crear lista de ingredientes para una receta
        List<RecipeIngredient> ingredientList = new ArrayList<>();
        ingredientList.add(recipeIngredient1);
        ingredientList.add(recipeIngredient2);

        // Crear una receta
        Recipe receta = new Recipe();
        receta.setNameRecipe("Torta de Chocolate");
        receta.setPreparationTime(2);
        receta.setServes(8);
        receta.setIngredientList(ingredientList);
        receta.setPreparationDescription("Instrucciones de preparación...");

        // Agregar receta a la lista de recetas
        List<Recipe> listaRecetas = new ArrayList<>();
        listaRecetas.add(receta);

        // Imprimir la información de la receta
        System.out.println("Nombre de la receta: " + receta.getNameRecipe());
        System.out.println("Tiempo de preparación (horas): " + receta.getPreparationTime());
        System.out.println("Porciones: " + receta.getServes());
        System.out.println("Ingredientes:");
        for (RecipeIngredient recipeIngredient : receta.getIngredientList()) {
            System.out.println("- " + recipeIngredient.calculateIngredients() + ": " + recipeIngredient.getQuantity());
        }
        System.out.println("Descripción de preparación: " + receta.getPreparationDescription());
    }
    
}
