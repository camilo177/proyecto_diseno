package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Facade facade = new Facade();
        User user = new User();

        initializeUser(user); // Inicializa el usuario con valores predeterminados
        boolean iniciarSesion = false;

        // Display the customized restaurant banner
        printRestaurantBanner();
        while (true) {
            printMainMenuIniciarSesion(iniciarSesion);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    iniciarSesion = toggleSesion(iniciarSesion, user, scanner);
                    break;
                case 2:
                    createUserIfNotLoggedIn(iniciarSesion, scanner);
                case 3:
                    if (!iniciarSesion) {
                        System.out.println("Debes iniciar sesion para acceder a esta opcion.");
                    } else {
                        segundoMenuRecetas(scanner, facade, user);
                    }
                    break;
                case 4:
                    System.out.println(" HASTA LUEGO! ");
                    System.exit(0);
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opción valida.");
                    break;
            }
        }
    }

    private static void initializeUser(User user) {
        user.setUserName("DiegoChef");
        user.setPassword("123456789");
        user.setUserType(USER_TYPE.Chef);
    }

    private static boolean toggleSesion(boolean iniciarSesion, User user, Scanner scanner) {
        if (iniciarSesion) {
            iniciarSesion = false;
            System.out.println("Sesion cerrada.");
        } else {
            iniciarSesion = loginUser(scanner, user);
        }
        return iniciarSesion;
    }

    private static void createUserIfNotLoggedIn(boolean iniciarSesion, Scanner scanner) {
        if (iniciarSesion) {
            System.out.println("Debes cerrar sesion antes de crear un nuevo usuario.");
        } else {

            // Aquí está el ciclo para seleccionar el tipo de usuario
            boolean userCreated = false;
            while (!userCreated) {
                System.out.print("Ingresa un nuevo nombre de usuario: ");
                String newUserName = scanner.nextLine();
                System.out.print("Ingresa una nueva contrasena: ");
                String newPassword = scanner.nextLine();

                USER_TYPE userType;

                System.out.println("Selecciona un tipo de usuario:");
                System.out.println("1. Chef");
                System.out.println("2. Administrador");
                System.out.println("3. Otro");
                int userTypeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (userTypeChoice) {
                    case 1 ->
                        userType = USER_TYPE.Chef;
                    case 2 ->
                        userType = USER_TYPE.Administrator;
                    case 3 ->
                        userType = USER_TYPE.Other;
                    default -> {
                        System.out.println("Tipo de usuario no valido. Se asignara como 'Otro'.");
                        userType = USER_TYPE.Other;
                    }
                }

                User.createUser(newUserName, newPassword, userType);
                // Cambia el estado de iniciarSesion a true
                System.out.println("Usuario creado con EXITO!.");
                userCreated = true;
                iniciarSesion = true;
            }
        }
    }

    private static void printRestaurantBanner() {

        System.out.println("  ████████╗██╗  ██╗███████╗    ██████╗ ██████╗ ██████╗ ██╗     ██████╗ ");
        System.out.println("  ╚══██╔══╝██║  ██║██╔════╝   ██╔════╝██╔═══██╗██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ███████║███████╗   ██║     ██║   ██║██████╔╝██║     ██████╔╝");
        System.out.println("     ██║   ██╔══██║╚════██║   ██║     ██║   ██║██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ██║  ██║███████║   ╚██████╗╚██████╔╝██║  ██║███████╗██████╔╝");
        System.out.println("     ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ \n");
        System.out.println("   BIENVENIDO AL RESTAURANTE MERCEDARIO RECETAS!\n");

    }

    private static void printMainMenuIniciarSesion(boolean iniciarSesion) {
        System.out.println("┌─────────────────────────────-┐");
        System.out.println("│  Mercedario Recetas Menu     │");
        System.out.println("├─────────────────────────────-┤");
        System.out.println("│ Seleccione una opcion:       │");
        System.out.println("├─────────────────────────────-┤");
        if (iniciarSesion) {
            System.out.println("│ 1. Cerrar Sesion            │");
            System.out.println("│ 2. Crear Usuario             │");
        } else {
            System.out.println("│ 1. Iniciar Sesion            │");
            System.out.println("│ 2. Crear Usuario             │");
        }
        System.out.println("│ 3. Recetas                   │");
        System.out.println("│ 4. Salir                     │");
        System.out.println("└─────────────────────────────-┘");
        System.out.print("Ingrese su eleccion: ");
    }

    private static boolean loginUser(Scanner scanner, User user) {
        System.out.print("Ingresa tu nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Ingresa tu contrasena: ");
        String password = scanner.nextLine();

        return user.authenticate(USER_TYPE.Chef, password);
    }

    private static void segundoMenuRecetas(Scanner scanner, Facade facade, User user) {
        while (true) {
            System.out.println("\nMenu de Recetas:");
            System.out.println("1. Ver lista de recetas");
            System.out.println("2. Agregar una receta");
            System.out.println("3. Eliminar una receta");
            System.out.println("4. Preparar una receta");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opcion: ");

            int recipeMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (recipeMenuChoice) {
                case 1 ->
                    viewAllRecipes(facade);
                case 2 -> {
                    if (user.getUserType() == USER_TYPE.Chef) {
                        createRecipe(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para agregar recetas.");
                    }
                }
                case 3 -> {
                    if (user.getUserType() == USER_TYPE.Chef) {
                        deleteRecipe(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para eliminar recetas.");
                    }
                }
                case 4 ->
                    prepareRecipe(scanner, facade);
                case 5 -> {
                    System.out.println("Sesion cerrada.");
                    return;
                }
                default ->
                    System.out.println("Opcion no valida. Intentalo de nuevo.");
            }
        }
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

        Ingredient ingredient = new Ingredient();
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

}

//
//        User user = new User();
//        user.setUserName("DiegoChef");
//        user.setPassword("123456789");
//        user.setUserType(USER_TYPE.Chef);
//
//        System.out.println("BIENVENIDO AL RESTAURANTE MERCEDARIO!\n");
//
//        boolean loggedIn = false;
//
//        // Menú de inicio
//        while (true) {
//
//            if (loggedIn) {
//                System.out.println("¡Sesión iniciada como " + user.getUserType() + "!");
//                System.out.println("1. Cerrar Sesión");
//            } else {
//                System.out.println("1. Iniciar Sesión");
//                System.out.println("2. Crear Usuario");
//            }
//            System.out.println("3. Salir de la aplicación");
//            System.out.print("Selecciona una opción: ");
//
//            int option = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (option) {
//                case 1:
//                    if (loggedIn) {
//                        loggedIn = false;
//                        System.out.println("Sesión cerrada.");
//                    } else {
//                        // Iniciar Sesión
//                        System.out.print("Ingresa tu nombre de usuario: ");
//                        String userName = scanner.nextLine();
//                        System.out.print("Ingresa tu contraseña: ");
//                        String password = scanner.nextLine();
//
//                        if (user.authenticate(USER_TYPE.Chef, password)) {
//                            loggedIn = true;
//                            System.out.println("Sesión iniciada con éxito como " + user.getUserType() + ".");
//                        } else {
//                            System.out.println("Nombre de usuario o contraseña incorrectos.");
//                        }
//                    }
//                    break;
//
//                case 2:
//                    if (loggedIn) {
//                        System.out.println("Debes cerrar sesión antes de crear un nuevo usuario.");
//                    } else {
//                        System.out.print("Ingresa un nuevo nombre de usuario: ");
//                        String newUserName = scanner.nextLine();
//                        System.out.print("Ingresa una nueva contraseña: ");
//                        String newPassword = scanner.nextLine();
//
//                        System.out.println("Selecciona un tipo de usuario:");
//                        System.out.println("1. Chef");
//                        System.out.println("2. Administrador");
//                        System.out.println("3. Otro");
//                        int userTypeChoice = scanner.nextInt();
//                        scanner.nextLine();
//
//                        USER_TYPE userType;
//
//                        switch (userTypeChoice) {
//                            case 1:
//                                userType = USER_TYPE.Chef;
//                                break;
//                            case 2:
//                                userType = USER_TYPE.Administrator;
//                                break;
//                            case 3:
//                                userType = USER_TYPE.Other;
//                                break;
//                            default:
//                                System.out.println("Tipo de usuario no válido. Se asignará como 'Otro'.");
//                                userType = USER_TYPE.Other;
//                        }
//
//                        User.createUser(newUserName, newPassword, userType);
//                        System.out.println("Usuario creado con éxito.");
//
//                    }
//                    break;
//
//                case 3:
//                    // Salir de la aplicación
//                    System.out.println("Hasta luego!");
//                    System.exit(0);
//
//                default:
//                    System.out.println("Opcion no valida. Por favor, elige una opción valida.");
//            }
//
//            if (loggedIn) {
//                while (true) {
//                    System.out.println("\nSegundo Menú:");
//                    System.out.println("1. Ver lista de recetas");
//                    System.out.println("2. Agregar una receta");
//                    System.out.println("3. Eliminar una receta");
//                    System.out.println("4. Preparar una receta");
//                    System.out.println("5. Salir");
//                    System.out.print("Selecciona una opción: ");
//
//                    int secondMenuChoice = scanner.nextInt();
//                    scanner.nextLine(); // Consumir la nueva línea
//
//                    switch (secondMenuChoice) {
//                        case 1:
//                            // Ver lista de recetas
//                            List<Recipe> recipes = facade.viewAllRecipes();
//                            System.out.println("Lista de recetas:");
//                            for (Recipe recipe : recipes) {
//                                System.out.println(recipe.getNameRecipe());
//                            }
//                            break;
//                        case 2:
//                            // Agregar una receta
//                            System.out.print("Ingresa el nombre de la nueva receta: ");
//                            String recipeName = scanner.nextLine();
//                            System.out.print("Tiempo de preparación (horas): ");
//                            int preparationTime = scanner.nextInt();
//                            scanner.nextLine();
//                            System.out.print("Número de porciones: ");
//                            int serves = scanner.nextInt();
//                            scanner.nextLine();
//                            System.out.print("Descripción de preparación: ");
//                            String preparationDescription = scanner.nextLine();
//
//                            // Crea una lista de ingredientes para la receta (puedes implementar esta parte)
//                            List<RecipeIngredient> ingredients = new ArrayList<>();
//                            // Agregar lógica para crear ingredientes y agregarlos a la lista
//
//                            Recipe newRecipe = new Recipe(recipeName, preparationTime, serves, ingredients, preparationDescription);
//                            facade.addRecipe(newRecipe);
//                            System.out.println("Receta agregada exitosamente.");
//                            break;
//                        case 3:
//                            // Eliminar una receta
//                            System.out.print("Ingresa el nombre de la receta que deseas eliminar: ");
//                            String recipeToDelete = scanner.nextLine();
//                            boolean recipeDeleted = facade.deleteRecipe(recipeToDelete);
//                            if (recipeDeleted) {
//                                System.out.println("Receta eliminada exitosamente.");
//                            } else {
//                                System.out.println("No se encontró la receta.");
//                            }
//                            break;
//                        case 4:
//                            // Preparar una receta
//                            System.out.print("Ingresa el nombre de la receta que deseas preparar: ");
//                            String recipeToPrepare = scanner.nextLine();
//                            facade.prepareRecipe(recipeToPrepare);
//                            System.out.println("Receta preparada con éxito.");
//                            break;
//                        case 5:
//                            loggedIn = false; // Cerrar sesión y salir del bucle
//                            System.out.println("Sesión cerrada.");
//                            break;
//                        default:
//                            System.out.println("Opción no válida. Inténtalo de nuevo.");
//                    }
//                }
//            }
//        }
//    }
//}
