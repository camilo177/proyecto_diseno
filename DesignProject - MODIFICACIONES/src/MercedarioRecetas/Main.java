package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Facade facade = new Facade();

        // Display the customized restaurant banner
        printRestaurantBanner();

        while (true) {
            printMainMenu();

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createRecipe(scanner, facade);
                    break;
                case 2:
                    viewAllRecipes(facade);
                    break;
                case 3:
                    editRecipe(scanner, facade);
                    break;
                case 4:
                    deleteRecipe(scanner, facade);
                    break;
                case 5:
                    prepareRecipe(scanner, facade);
                    break;
                case 6:
                    viewAllIngredients(facade);
                    break;
                case 7:
                    addIngredient(scanner, facade);
                    break;
                case 8:
                    editIngredient(scanner, facade);
                    break;
                case 9:
                    deleteIngredient(scanner, facade);
                    break;
                case 10:
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    // Customized ASCII art for the restaurant banner
    private static void printRestaurantBanner() {
        System.out.println("  ████████╗██╗  ██╗███████╗    ██████╗ ██████╗ ██████╗ ██╗     ██████╗ ");
        System.out.println("  ╚══██╔══╝██║  ██║██╔════╝   ██╔════╝██╔═══██╗██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ███████║███████╗   ██║     ██║   ██║██████╔╝██║     ██████╔╝");
        System.out.println("     ██║   ██╔══██║╚════██║   ██║     ██║   ██║██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ██║  ██║███████║   ╚██████╗╚██████╔╝██║  ██║███████╗██████╔╝");
        System.out.println("     ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ \n");
        System.out.println("  ¡Bienvenido al Restaurante Mercedario Recetas!\n");
    }

    // ASCII art for the main menu
    private static void printMainMenu() {
        System.out.println("┌─────────────────────────────-┐");
        System.out.println("│  Mercedario Recetas Menu     │");
        System.out.println("├─────────────────────────────-┤");
        System.out.println("│ Seleccione una opción:       │");
        System.out.println("├─────────────────────────────-┤");
        System.out.println("│ 1. Crear una nueva receta    │");
        System.out.println("│ 2. Ver todas las recetas     │");
        System.out.println("│ 3. Editar una receta         │");
        System.out.println("│ 4. Eliminar una receta       │");
        System.out.println("│ 5. Preparar una receta       │");
        System.out.println("│ 6. Ver todos los ingredientes│");
        System.out.println("│ 7. Agregar un ingrediente    │");
        System.out.println("│ 8. Editar un ingrediente     │");
        System.out.println("│ 9. Eliminar un ingrediente   │");
        System.out.println("│ 10. Salir                    │");
        System.out.println("└─────────────────────────────-┘");
        System.out.print("Ingrese su elección: ");
    }


    private static void createRecipe(Scanner scanner, Facade facade) {
        Recipe newRecipe = new Recipe();

        System.out.println("======================================");
        System.out.println("\u001B[36mIngrese el nombre de la receta:\u001B[0m");
        String recipeName = scanner.nextLine();
        newRecipe.setNameRecipe(recipeName);

        System.out.println("======================================");
        System.out.print("\u001B[36mIngrese el tiempo de preparación (horas):\u001B[0m");
        int preparationTime = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        newRecipe.setPreparationTime(preparationTime);

        System.out.println("======================================");
        System.out.print("\u001B[36mIngrese la cantidad de porciones:\u001B[0m");
        int serves = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        newRecipe.setServes(serves);


        List<RecipeIngredient> ingredientList = new ArrayList<>();

        while (true) {
            RecipeIngredient recipeIngredient = createRecipeIngredient(scanner);
            ingredientList.add(recipeIngredient);

            System.out.print("¿Desea agregar otro ingrediente? (S/N): ");
            String continueAdding = scanner.next();
            scanner.nextLine(); // Consume the newline character
            if (!continueAdding.equalsIgnoreCase("S")) {
                break;
            }
        }

        newRecipe.setIngredientList(ingredientList);

        System.out.print("Ingrese la descripción de preparación: ");
        String preparationDescription = scanner.nextLine();
        newRecipe.setPreparationDescription(preparationDescription);

        facade.addRecipe(newRecipe);
        System.out.println("\u001B[32mReceta creada con éxito.\u001B[0m");
    }

    private static RecipeIngredient createRecipeIngredient(Scanner scanner) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        System.out.println("======================================");
        System.out.print("\u001B[36mIngrese el nombre del ingrediente: \u001B[0m");
        String ingredientName = scanner.nextLine();

        Ingredientes ingredient = new Ingredientes();
        ingredient.setNameIngredient(ingredientName);

        System.out.print("Ingrese las unidades del ingrediente: ");
        int ingredientUnits = Integer.parseInt(scanner.nextLine());
        ingredient.setUnit(ingredientUnits);

        System.out.print("Ingrese el lugar de compra: ");
        String purchasePlace = scanner.nextLine();
        ingredient.setPurchasePlace(purchasePlace);

        System.out.print("Ingrese el valor por unidad: ");
        double ingredientsValue = Double.parseDouble(scanner.nextLine());
        ingredient.setValuePerUnit(ingredientsValue);

        System.out.print("Ingrese la cantidad: ");
        double quantity = Double.parseDouble(scanner.nextLine());
        recipeIngredient.setQuantity(quantity);

        System.out.print("Ingrese las calorías por unidad: ");
        double calories = Double.parseDouble(scanner.nextLine());
        ingredient.setCaloriesPerUnit(calories);

        recipeIngredient.setIngredient(ingredient);

        return recipeIngredient;
    }

    private static void viewAllRecipes(Facade facade) {
        List<Recipe> recipes = facade.viewAllRecipes();

        if (recipes.isEmpty()) {
            System.out.println("\u001B[33mNo hay recetas disponibles.\u001B[0m");
        } else {
            System.out.println("\u001B[32mRecetas disponibles:\u001B[0m");
            for (Recipe recipe : recipes) {
                System.out.println("======================================");
                System.out.println("\u001B[36mNombre de la receta: " + recipe.getNameRecipe() + "\u001B[0m");
                System.out.println("\u001B[36mTiempo de preparación (horas): " + recipe.getPreparationTime() + "\u001B[0m");
                System.out.println("\u001B[36mPorciones: " + recipe.getServes() + "\u001B[0m");
                System.out.println("\u001B[36mIngredientes:\u001B[0m");
                for (RecipeIngredient recipeIngredient : recipe.getIngredientList()) {
                    System.out.println("\u001B[36m- " + recipeIngredient.getIngredient().getNameIngredient() + ": " + recipeIngredient.getQuantity() + "\u001B[0m");
                }
                System.out.println("\u001B[36mDescripción de preparación: " + recipe.getPreparationDescription() + "\u001B[0m");
            }
            System.out.println("======================================");
        }
    }


    private static void editRecipe(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre de la receta que desea editar: ");
        String oldRecipeName = scanner.nextLine();

        Recipe newRecipe = new Recipe();

        System.out.println("======================================");
        System.out.print("\u001B[36mIngrese el nuevo nombre de la receta: \u001B[0m");
        String recipeName = scanner.nextLine();
        newRecipe.setNameRecipe(recipeName);

        System.out.print("\u001B[36mIngrese el nuevo tiempo de preparación (horas): \u001B[0m");
        int preparationTime = Integer.parseInt(scanner.nextLine());
        newRecipe.setPreparationTime(preparationTime);

        System.out.print("\u001B[36mIngrese la nueva cantidad de porciones: \u001B[0m");
        int serves = Integer.parseInt(scanner.nextLine());
        newRecipe.setServes(serves);

        List<RecipeIngredient> ingredientList = new ArrayList<>();

        while (true) {
            RecipeIngredient recipeIngredient = createRecipeIngredient(scanner);
            ingredientList.add(recipeIngredient);

            System.out.print("¿Desea agregar otro ingrediente? (S/N): ");
            String continueAdding = scanner.nextLine();
            if (!continueAdding.equalsIgnoreCase("S")) {
                break;
            }
        }

        newRecipe.setIngredientList(ingredientList);

        System.out.print("Ingrese la nueva descripción de preparación: ");
        String preparationDescription = scanner.nextLine();
        newRecipe.setPreparationDescription(preparationDescription);

        facade.editRecipe(oldRecipeName, newRecipe);
        System.out.println("\u001B[32mReceta editada con éxito.\u001B[0m");
    }

    private static void deleteRecipe(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre de la receta que desea eliminar: ");
        String recipeName = scanner.nextLine();

        boolean deleted = facade.deleteRecipe(recipeName);

        if (deleted) {
            System.out.println("Receta eliminada con éxito.");
        } else {
            System.out.println("No se encontró la receta.");
        }
    }

    private static void prepareRecipe(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre de la receta que desea preparar: ");
        String recipeName = scanner.nextLine();

        facade.prepareRecipe(recipeName);
    }

    private static void viewAllIngredients(Facade facade) {
        List<RecipeIngredient> ingredients = facade.viewAllIngredients();

        if (ingredients.isEmpty()) {
            System.out.println("No hay ingredientes disponibles.");
        } else {
            System.out.println("Ingredientes disponibles:");
            for (RecipeIngredient ingredient : ingredients) {
                System.out.println("- " + ingredient.getIngredient().getNameIngredient() + ": " + ingredient.getQuantity());
            }
        }
    }

    private static void addIngredient(Scanner scanner, Facade facade) {
        RecipeIngredient recipeIngredient = createRecipeIngredient(scanner);
        facade.addRecipeIngredient(recipeIngredient);
        System.out.println("Ingrediente agregado con éxito.");
    }

    private static void editIngredient(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre del ingrediente que desea editar: ");
        String oldIngredientName = scanner.nextLine();

        RecipeIngredient newRecipeIngredient = createRecipeIngredient(scanner);

        facade.editRecipeIngredient(oldIngredientName, newRecipeIngredient);
        System.out.println("Ingrediente editado con éxito.");
    }

    private static void deleteIngredient(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre del ingrediente que desea eliminar: ");
        String ingredientName = scanner.nextLine();

        boolean deleted = facade.deleteRecipeIngredient(ingredientName);

        if (deleted) {
            System.out.println("Ingrediente eliminado con éxito.");
        } else {
            System.out.println("No se encontró el ingrediente.");
        }
    }
}
