package MercedarioRecetas;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mercedario {

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

            int printMainMenuIniciarSesionChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (printMainMenuIniciarSesionChoice) {
                case 1:
                    iniciarSesion = toggleSesion(iniciarSesion, user, scanner);
                    if (iniciarSesion) {
                        printSegundoMenu(scanner, facade, user);
                    } else {
                        System.out.println("Debes iniciar sesion para acceder a esta opcion.");
                    }

                    break;
                case 2:
                    createUserIfNotLoggedIn(iniciarSesion, scanner);
                    if (toggleSesion(iniciarSesion, user, scanner)) {
                        printSegundoMenu(scanner, facade, user);
                        iniciarSesion = true;
                    } else {
                        System.out.println("Debes iniciar sesion para acceder a esta opcion.");
                    }

                    break;
                case 3:
                    System.out.println("HASTA LUEGO!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opcion valida.");
                    break;
            }

        }
    }

    //Logo del restaurante
    private static void printRestaurantBanner() {

        System.out.println("  ████████╗██╗  ██╗███████╗    ██████╗ ██████╗ ██████╗ ██╗     ██████╗ ");
        System.out.println("  ╚══██╔══╝██║  ██║██╔════╝   ██╔════╝██╔═══██╗██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ███████║███████╗   ██║     ██║   ██║██████╔╝██║     ██████╔╝");
        System.out.println("     ██║   ██╔══██║╚════██║   ██║     ██║   ██║██╔══██╗██║     ██╔══██╗");
        System.out.println("     ██║   ██║  ██║███████║   ╚██████╗╚██████╔╝██║  ██║███████╗██████╔╝");
        System.out.println("     ╚═╝   ╚═╝  ╚═╝╚══════╝    ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚═════╝ \n");
        System.out.println("   BIENVENIDO AL RESTAURANTE MERCEDARIO RECETAS!\n");

    }

    //Usuario por defecto
    private static void initializeUser(User user) {
        user.setUserName("DiegoChef");
        user.setPassword("123456789");
        user.setUserType(USER_TYPE.Chef);
    }

    //Verifica si la sesion esta iniciada o no
    private static boolean toggleSesion(boolean iniciarSesion, User user, Scanner scanner) {
        if (iniciarSesion) {
            iniciarSesion = false;
            System.out.println("Sesion cerrada.");
        } else {
            iniciarSesion = loginUser(scanner, user);
        }
        return iniciarSesion;
    }

    //Meno Inicial
    private static void printMainMenuIniciarSesion(boolean iniciarSesion) {
        System.out.println("┌───────────────────────────────────┐");
        System.out.println("│   Mercedario Recetas Menu         │");
        System.out.println("├───────────────────────────────────┤");
        System.out.println("│ Seleccione una opcion:            │");
        System.out.println("├───────────────────────────────────┤");
        System.out.println("│ 1. Iniciar Sesion                 │");
        System.out.println("│ 2. Crear Usuario                  │");
        System.out.println("│ 3. Salir                          │");
        System.out.println("└───────────────────────────────────┘");
        System.out.print("Ingrese su eleccion: ");
    }

    //Metodo para iniciar sesion
    private static boolean loginUser(Scanner scanner, User user) {
        System.out.println("─────────────────────────");
        System.out.println("Iniciar Sesion");
        System.out.println("─────────────────────────");

        System.out.print("Ingresa tu nombre de usuario: ");
        String userName = scanner.nextLine();
        System.out.print("Ingresa tu contrasena: ");
        String password = scanner.nextLine();

        boolean authenticationResult = user.authenticate(USER_TYPE.Chef, password);

        if (authenticationResult) {
            System.out.println("Ingreso exitoso. Bienvenido, " + userName + "!");
        } else {
            System.out.println("Inicio de sesion fallido. Verifica tus credenciales.");
        }

        return authenticationResult;
    }

    //Metodo para crear un usuario nuevo
    private static void createUserIfNotLoggedIn(boolean iniciarSesion, Scanner scanner) {
        if (iniciarSesion) {
            System.out.println("Debes cerrar sesion antes de crear un nuevo usuario.");
        } else {
            System.out.println("─────────────────────────────────");
            System.out.println("  Crear un Nuevo Usuario");
            System.out.println("─────────────────────────────────");

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
                System.out.println("Usuario creado con exito!.");
                userCreated = true;
                iniciarSesion = true;
            }
        }
    }

    //Segundo Menu (Despues de iniciar sesion)
    private static void printSegundoMenu(Scanner scanner, Facade facade, User user) {
        while (true) {
            System.out.println("┌──────────────────────────────────────-┐");
            System.out.println("│  Mercedario Recetas Menu      │");
            System.out.println("├──────────────────────────────────── ──┤");
            System.out.println("│ Seleccione una opcion:        │");
            System.out.println("├───────────────────────────────────────┤");
            System.out.println("│ 1. Cerrar Sesion              │");
            System.out.println("│ 2. Recetas                    │");
            System.out.println("│ 3. Ingredientes               │");
            System.out.println("└───────────────────────────────────────┘");
            System.out.print("Ingrese su eleccion: ");

            int SegundoMenuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (SegundoMenuChoice) {
                case 1:
                    System.out.println("Sesion cerrada.");
                    return;
                case 2:
                    printMenuRecetas(scanner, facade, user);
                    break;
                case 3:
                    printMenuIngredientes(scanner, facade, user);
                    break;
                default:
                    System.out.println("Opción no valida. Intentalo de nuevo.");
            }
        }
    }

    //Tercer Menu opciones para Recetas
    private static void printMenuRecetas(Scanner scanner, Facade facade, User user) {
        while (true) {
            System.out.println("\u001B[34m┌─────────────────────────────-┐");
            System.out.println("│  Mercedario Recetas Menu     │");
            System.out.println("├─────────────────────────────-┤");
            System.out.println("│ Seleccione una opcion:       │");
            System.out.println("├─────────────────────────────-┤");
            System.out.println("│ 1. \u001B[36mVer Lista de Recetas      \u001B[34m│");
            System.out.println("│ 2. \u001B[36mAgregar Nueva Receta      \u001B[34m│");
            System.out.println("│ 3. \u001B[36mEliminar Receta           \u001B[34m│");
            System.out.println("│ 4. \u001B[36mPreparar Receta           \u001B[34m│");
            System.out.println("│ 5. \u001B[36mEditar Receta              \u001B[34m│");
            System.out.println("│ 6. \u001B[36mEliminar Ingrediente de Una Receta \u001B[34m│");
            System.out.println("│ 7. \u001B[31mSalir                   \u001B[34m│");
            System.out.println("\u001B[34m└─────────────────────────────-┘");
            System.out.print("Ingrese su eleccion: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1 -> viewAllRecipes(facade);
                case 2 -> {
                    if (user.getUserType() == USER_TYPE.Chef || user.getUserType() == USER_TYPE.Administrator) {
                        createRecipe(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para agregar recetas.");
                    }
                }
                case 3 -> {
                    if (user.getUserType() == USER_TYPE.Chef || user.getUserType() == USER_TYPE.Administrator) {
                        deleteRecipe(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para eliminar recetas.");
                    }
                }
                case 4 -> prepareRecipe(scanner, facade);
                case 5 -> editRecipe(scanner, facade);
                case 6 -> deleteRecipeIngredient(scanner, facade, user);
                case 7 -> printSegundoMenu(scanner, facade, user);
                default -> System.out.println("Opcion no valida. Intentalo de nuevo.");
            }
        }
    }

    //Metodo para imprimir Todas las Recetas y sus ingredientes.
    private static void viewAllRecipes(Facade facade) {
        List<Recipe> recipes = facade.viewAllRecipes();

        if (recipes.isEmpty()) {
            System.out.println("\u001B[33mNo Hay Recetas Disponibles.\u001B[0m");
        } else {
            for (Recipe recipe : recipes) {
                System.out.println("==========================================");
                System.out.println("\u001B[32mRECETA DISPONIBLE:\u001B[0m");
                System.out.println("==========================================");

                // Nombre de la Receta
                System.out.println("\u001B[36mNombre de la Receta:\u001B[0m");
                System.out.println(recipe.getNameRecipe());

                // Tiempo de Preparación
                System.out.println("\u001B[36mTiempo de Preparacion (horas):\u001B[0m");
                System.out.println(recipe.getPreparationTime() + " horas");

                // Porciones
                System.out.println("\u001B[36mPorciones por Persona:\u001B[0m");
                System.out.println(recipe.getServes());

                // Ingredientes
                System.out.println("------------------------------------------");
                System.out.println("\u001B[36mIngredientes de la Receta:\u001B[0m");

                for (RecipeIngredient recipeIngredient : recipe.getIngredientList()) {
                    System.out.println("--------------------------------------");
                    System.out.println("\u001B[36mIngrediente:\u001B[0m");
                    System.out.println(recipeIngredient.getIngredient().getNameIngredient());

                    // Cantidad, Unidades y Lugar de Compra
                    System.out.println("\u001B[36mCantidad (lb):\u001B[0m");
                    System.out.println(recipeIngredient.getQuantity());
                    System.out.println("\u001B[36mUnidades (lb):\u001B[0m");
                    System.out.println(recipeIngredient.getIngredient().getUnit());
                    System.out.println("\u001B[36mLugar de Compra:\u001B[0m");
                    System.out.println(recipeIngredient.getIngredient().getPurchasePlace());

                    // Valor y Calorías por Unidad
                    System.out.println("\u001B[36mValor por Unidad (Pesos):\u001B[0m");
                    System.out.println(recipeIngredient.getIngredient().getValuePerUnit());
                    System.out.println("\u001B[36mCalorias por Unidad (cal):\u001B[0m");
                    System.out.println(recipeIngredient.getIngredient().getCaloriesPerUnit());

                    // Costo y Calorías Totales por Ingrediente
                    double cost = recipeIngredient.calculateCost();
                    double calories = recipeIngredient.calculateCalories();
                    System.out.println("\u001B[36mCosto por Ingrediente (Pesos):\u001B[0m");
                    System.out.println(cost);
                    System.out.println("\u001B[36mCalorias Totales (cal):\u001B[0m");
                    System.out.println(calories);
                }

                // Descripción de Preparación
                System.out.println("------------------------------------------");
                System.out.println("\u001B[36mDescripción de Preparacion:\u001B[0m");
                System.out.println(recipe.getPreparationDescription());

                System.out.println("==========================================");
            }
        }
    }

    //Metodo para crear nuevas Recetas.
    private static void createRecipe(Scanner scanner, Facade facade) {
        Recipe newRecipe = new Recipe();

        System.out.println("--------------------------------------------------");
        System.out.println("\u001B[36mIngrese el nombre de la receta:\u001B[0m");
        String recipeName = scanner.nextLine();
        newRecipe.setNameRecipe(recipeName);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese el tiempo de preparacion (horas):\u001B[0m");
        int preparationTime = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        newRecipe.setPreparationTime(preparationTime);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese la cantidad de porciones:\u001B[0m");
        int serves = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        newRecipe.setServes(serves);

        List<RecipeIngredient> ingredientList = new ArrayList<>();

        while (true) {
            RecipeIngredient recipeIngredient = createRecipeIngredient(scanner, facade);
            ingredientList.add(recipeIngredient);

            System.out.println("----------------------------------------------");
            System.out.print("\u001B[36mDesea agregar otro ingrediente? (Si/No):\u001B[0m ");
            String continueAdding = scanner.next();
            scanner.nextLine(); // Consume the newline character
            if (!continueAdding.equalsIgnoreCase("Si")) {
                break;
            }
        }

        newRecipe.setIngredientList(ingredientList);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese la descripcion de preparacion:\u001B[0m ");
        String preparationDescription = scanner.nextLine();
        newRecipe.setPreparationDescription(preparationDescription);

        facade.addRecipe(newRecipe);
        System.out.println("\u001B[32mReceta creada con exito.\u001B[0m");
    }

    //Metodo que permite añadir ingredientes junto con recetas
    private static RecipeIngredient createRecipeIngredient(Scanner scanner, Facade facade) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese el nombre del ingrediente:\u001B[0m ");
        String ingredientName = scanner.nextLine();

        Ingredient ingredient = new Ingredient();
        ingredient.setNameIngredient(ingredientName);

        // Resto del código para configurar las propiedades del ingrediente
        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese las unidades del ingrediente (lb):\u001B[0m ");
        int ingredientUnits = Integer.parseInt(scanner.nextLine());
        ingredient.setUnit(ingredientUnits);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese el lugar de compra:\u001B[0m ");
        String purchasePlace = scanner.nextLine();
        ingredient.setPurchasePlace(purchasePlace);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese el valor por unidad (pesos):\u001B[0m ");
        double ingredientsValue = Double.parseDouble(scanner.nextLine());
        ingredient.setValuePerUnit(ingredientsValue);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese la cantidad (lb):\u001B[0m ");
        double quantity = Double.parseDouble(scanner.nextLine());
        recipeIngredient.setQuantity(quantity);

        System.out.println("--------------------------------------------------");
        System.out.print("\u001B[36mIngrese las calorías por unidad (cal):\u001B[0m ");
        double calories = Double.parseDouble(scanner.nextLine());
        ingredient.setCaloriesPerUnit(calories);

        recipeIngredient.setIngredient(ingredient);

        // Ahora, agrega el ingrediente a la lista central de ingredientes en la clase Facade
        facade.addIngredient(ingredient);

        return recipeIngredient;
    }

    //Metodo para eliminar Recetas por su nombre
    private static void deleteRecipe(Scanner scanner, Facade facade) {
        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese el nombre de la receta que desea eliminar:\u001B[0m ");
        String recipeName = scanner.nextLine();

        boolean deleted = facade.deleteRecipe(recipeName);

        System.out.println("==================================================");

        if (deleted) {
            System.out.println("\u001B[32mReceta eliminada con exito.\u001B[0m");
        } else {
            System.out.println("\u001B[33mNo se encontro la receta.\u001B[0m");
        }
    }

    //Metodo para imprimir informacion para preparar recetas
    private static void prepareRecipe(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre de la receta que desea preparar: ");
        String recipeName = scanner.nextLine();

        facade.prepareRecipe(recipeName); // Llama al método con la cadena del nombre de la receta
    }

    // Método para editar una receta
    private static void editRecipe(Scanner scanner, Facade facade) {
        System.out.print("Ingrese el nombre de la receta que desea editar: ");
        String recipeNameToEdit = scanner.nextLine();

        Recipe newRecipe = new Recipe();

        System.out.println("\u001B[36mIngrese los nuevos detalles de la receta:\u001B[0m");

        System.out.print("Nuevo nombre de la receta: ");
        newRecipe.setNameRecipe(scanner.nextLine());

        System.out.print("Nuevo tiempo de preparación (horas): ");
        newRecipe.setPreparationTime(scanner.nextInt());
        scanner.nextLine(); // Consumir la nueva línea

        System.out.print("Nueva cantidad de porciones: ");
        newRecipe.setServes(scanner.nextInt());
        scanner.nextLine(); // Consumir la nueva línea

        List<RecipeIngredient> ingredientList = new ArrayList();

        System.out.println("\u001B[36mAgregue los nuevos ingredientes:\u001B[0m");

        while (true) {
            RecipeIngredient recipeIngredient = createRecipeIngredient(scanner, facade);
            ingredientList.add(recipeIngredient);

            System.out.print("¿Desea agregar otro ingrediente? (Si/No): ");
            String continueAdding = scanner.nextLine();
            if (!continueAdding.equalsIgnoreCase("Si")) {
                break;
            }
        }

        newRecipe.setIngredientList(ingredientList);

        System.out.print("Nueva descripción de preparación: ");
        newRecipe.setPreparationDescription(scanner.nextLine());

        boolean editSuccess = facade.editRecipe(recipeNameToEdit, newRecipe);

        System.out.println("==================================================");

        if (editSuccess) {
            System.out.println("\u001B[32mReceta editada con éxito.\u001B[0m");
        } else {
            System.out.println("\u001B[33mLa receta no se encontró o no se pudo editar.\u001B[0m");
        }
    }

    //Metodo Para eliminar un ingrediente de una receta 
    private static void deleteRecipeIngredient(Scanner scanner, Facade facade, User user) {
        // Verificar si el usuario tiene permiso para eliminar ingredientes
        if (user.getUserType() != USER_TYPE.Chef && user.getUserType() != USER_TYPE.Administrator) {
            System.out.println("\u001B[33mNo tienes permiso para eliminar ingredientes.\u001B[0m");
            return;
        }

        // Solicitar el nombre de la receta y el nombre del ingrediente a eliminar
        System.out.println("==================================================");
        System.out.print("\u001B[36mIngresa el nombre de la receta de la que deseas eliminar un ingrediente:\u001B[0m ");
        String recipeName = scanner.nextLine();

        // Verificar si la receta existe
        Recipe recipe = facade.getRecipeByName(recipeName);
        if (recipe == null) {
            System.out.println("\u001B[33mLa receta '" + recipeName + "' no existe.\u001B[0m");
            return;
        }

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngresa el nombre del ingrediente que deseas eliminar:\u001B[0m ");
        String ingredientName = scanner.nextLine();

        // Intentar eliminar el ingrediente
        boolean ingredientDeleted = facade.deleteRecipeIngredient(ingredientName, recipeName);

        System.out.println("==================================================");

        if (ingredientDeleted) {
            System.out.println("\u001B[32mIngrediente '" + ingredientName + "' eliminado de la receta '" + recipeName + "'.\u001B[0m");
        } else {
            System.out.println("\u001B[33mEl ingrediente '" + ingredientName + "' no se encontró en la receta '" + recipeName + "'.\u001B[0m");
        }
    }

    //Cuarto Menu opciones para Ingredinetes
    private static void printMenuIngredientes(Scanner scanner, Facade facade, User user) {
        while (true) {
            System.out.println("┌──────────────────────────────────── ┐");
            System.out.println("│  Mercedario Recetas Menu      │");
            System.out.println("├─────────────────────────────────────- ┤");
            System.out.println("│ Seleccione una opcion:         │");
            System.out.println("├─────────────────────────────────────- ┤");
            System.out.println("│ 1. Ver Lista de Ingredientes   │");
            System.out.println("│ 2. Agregar Nuevo Ingrediente   │");
            System.out.println("│ 3. Eliminar Ingrediente        │");
            System.out.println("│ 4. Editar Ingrediente        │");
            System.out.println("│ 5. Salir                       │");
            System.out.println("└───────────────────────────────────────-┘");
            System.out.print("Ingrese su eleccion: ");

            int printMenuIngredientes = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (printMenuIngredientes) {
                case 1 -> {
                    viewAllIngredients(facade);
                }
                case 2 -> {
                    if (user.getUserType() == USER_TYPE.Chef || user.getUserType() == USER_TYPE.Administrator) {
                        createIngredient(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para agregar ingredientes.");
                    }
                }
                case 3 -> {
                    if (user.getUserType() == USER_TYPE.Chef || user.getUserType() == USER_TYPE.Administrator) {
                        deleteIngredient(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para eliminar ingredientes.");
                    }
                }
                case 4 -> {
                    if (user.getUserType() == USER_TYPE.Chef || user.getUserType() == USER_TYPE.Administrator) {
                        //(scanner, facade);
                    } else {
                        System.out.println("No tienes permiso para eliminar ingredientes.");
                    }
                }
                case 5 -> {
                    printSegundoMenu(scanner, facade, user);
                }
                default -> {
                    System.out.println("Opcion no valida. Intentalo de nuevo.");
                }
            }
        }
    }

    private static void viewAllIngredients(Facade facade) {
        List<Ingredient> allIngredients = facade.viewAllIngredients();

        if (allIngredients.isEmpty()) {
            System.out.println("\u001B[33mNo hay ingredientes disponibles en este momento.\u001B[0m");
        } else {
            System.out.println("Lista de Ingredientes Disponibles:");
            System.out.println("----------------------------------------------");

            for (Ingredient ingredient : allIngredients) {
                System.out.println("Nombre: " + ingredient.getNameIngredient());
                System.out.println("Unidades (lb): " + ingredient.getUnit());
                System.out.println("Lugar de Compra: " + ingredient.getPurchasePlace());
                System.out.println("Valor por Unidad (pesos): " + ingredient.getValuePerUnit());
                System.out.println("Calorías por Unidad (cal): " + ingredient.getCaloriesPerUnit());
                System.out.println("------------------------------------------");
            }
        }
    }

    private static void createIngredient(Scanner scanner, Facade facade) {
        Ingredient newIngredient = new Ingredient();

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese el nombre del nuevo ingrediente:\u001B[0m ");
        String ingredientName = scanner.nextLine();
        newIngredient.setNameIngredient(ingredientName);

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese las unidades del ingrediente (lb):\u001B[0m ");
        int ingredientUnits = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea
        newIngredient.setUnit(ingredientUnits);

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese el lugar de compra del ingrediente:\u001B[0m ");
        String purchasePlace = scanner.nextLine();
        newIngredient.setPurchasePlace(purchasePlace);

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese el valor por unidad del ingrediente (pesos):\u001B[0m ");
        double valuePerUnit = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea
        newIngredient.setValuePerUnit(valuePerUnit);

        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese las calorias por unidad del ingrediente (cal):\u001B[0m ");
        double caloriesPerUnit = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea
        newIngredient.setCaloriesPerUnit(caloriesPerUnit);

        // Agregar el nuevo ingrediente a la lista de ingredientes en Facade
        facade.addIngredient(newIngredient);

        System.out.println("==================================================");
        System.out.println("\u001B[32mIngrediente creado con exito.\u001B[0m");
    }

    private static void deleteIngredient(Scanner scanner, Facade facade) {
        System.out.println("==================================================");
        System.out.print("\u001B[36mIngrese el nombre del ingrediente que desea eliminar:\u001B[0m ");
        String ingredientName = scanner.nextLine();

        boolean deleted = facade.deleteIngredient(ingredientName);

        System.out.println("==================================================");

        if (deleted) {
            System.out.println("Ingrediente '" + ingredientName + "' eliminado exitosamente.");
        } else {
            System.out.println("No se encontro el ingrediente '" + ingredientName + "'.");
        }
    }

}
