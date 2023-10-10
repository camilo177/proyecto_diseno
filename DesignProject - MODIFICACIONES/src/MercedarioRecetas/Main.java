package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Facade facade = new Facade();

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Crear una nueva receta");
            System.out.println("2. Ver todas las recetas");
            System.out.println("3. Salir");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    createRecipe(scanner, facade);
                    break;
                case 2:
                    viewAllRecipes(facade);
                    break;
                case 3:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static void createRecipe(Scanner scanner, Facade facade) {
        Recipe newRecipe = new Recipe();

        System.out.print("Ingrese el nombre de la receta: ");
        String recipeName = scanner.next();
        newRecipe.setNameRecipe(recipeName);

        System.out.print("Ingrese el tiempo de preparación (horas): ");
        int preparationTime = scanner.nextInt();
        newRecipe.setPreparationTime(preparationTime);

        System.out.print("Ingrese la cantidad de porciones: ");
        int serves = scanner.nextInt();
        newRecipe.setServes(serves);

        List<RecipeIngredient> ingredientList = new ArrayList<>();

        while (true) {
            RecipeIngredient recipeIngredient = createRecipeIngredient(scanner);
            ingredientList.add(recipeIngredient);

            System.out.print("¿Desea agregar otro ingrediente? (S/N): ");
            String continueAdding = scanner.next();
            if (!continueAdding.equalsIgnoreCase("S")) {
                break;
            }
        }

        newRecipe.setIngredientList(ingredientList);

        System.out.print("Ingrese la descripción de preparación: ");
        String preparationDescription = scanner.next();
        newRecipe.setPreparationDescription(preparationDescription);

        facade.addRecipe(newRecipe);
        System.out.println("Receta creada con éxito.");
    }

    private static RecipeIngredient createRecipeIngredient(Scanner scanner) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        System.out.print("Ingrese el nombre del ingrediente: ");
        String ingredientName = scanner.next();

        Ingredientes ingredient = new Ingredientes();
        ingredient.setNameIngredient(ingredientName);

        recipeIngredient.setIngredient(ingredient);

        System.out.print("Ingrese la cantidad: ");
        double quantity = scanner.nextDouble();
        recipeIngredient.setQuantity(quantity);

        return recipeIngredient;
    }

    private static void viewAllRecipes(Facade facade) {
        List<Recipe> recipes = facade.viewAllRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No hay recetas disponibles.");
        } else {
            System.out.println("Recetas disponibles:");
            for (Recipe recipe : recipes) {
                System.out.println("Nombre de la receta: " + recipe.getNameRecipe());
                System.out.println("Tiempo de preparación (horas): " + recipe.getPreparationTime());
                System.out.println("Porciones: " + recipe.getServes());
                System.out.println("Ingredientes:");
                for (RecipeIngredient recipeIngredient : recipe.getIngredientList()) {
                    System.out.println("- " + recipeIngredient.getIngredient().getNameIngredient() + ": " + recipeIngredient.getQuantity());
                }
                System.out.println("Descripción de preparación: " + recipe.getPreparationDescription());
                System.out.println();
            }
        }
    }
}

