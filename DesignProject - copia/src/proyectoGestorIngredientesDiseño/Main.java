package proyectoGestorIngredientesDiseño;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			IngredientManager ingredientManager = new IngredientManager();

			// Crear una lista de ingredientes predefinida
			ArrayList<Ingredient> predifinedIngredients = new ArrayList<>();
			predifinedIngredients.add(new Ingredient("Azúcar", 2, 1.5, "Supermercado A", 50));
			predifinedIngredients.add(new Ingredient("Sal", 1, 0.5, "Supermercado B", 0));
			predifinedIngredients.add(new Ingredient("Harina", 3, 2.0, "Supermercado A", 100));
			ingredientManager.addIngredients(predifinedIngredients);
			
			while (true) {
			    System.out.println("Menú:");
			    System.out.println("1. Añadir ingrediente");
			    System.out.println("2. Modificar ingrediente");
			    System.out.println("3. Eliminar ingrediente");
			    System.out.println("4. Mirar lista de ingredientes");
			    System.out.print("Selecciona una opción (1/2/3/4): ");
			    String option = scanner.nextLine();

			    if (option.equals("1")) {
			        System.out.println("Por favor, responde las siguientes preguntas para añadir un nuevo ingrediente:");
			        System.out.print("Nombre del ingrediente: ");
			        String name = scanner.nextLine();
			        System.out.print("Número de unidades: ");
			        int unit = Integer.parseInt(scanner.nextLine());
			        System.out.print("Valor por unidad: ");
			        double valuePerUnit = Double.parseDouble(scanner.nextLine());
			        System.out.print("Lugar de compra: ");
			        String purchasePlace = scanner.nextLine();
			        System.out.print("Calorías por unidad: ");
			        double caloriesPerUnit = Double.parseDouble(scanner.nextLine()); //"String to double"
			        Ingredient nuevoIngrediente = new Ingredient(name, unit, valuePerUnit, purchasePlace, caloriesPerUnit);
			        ingredientManager.addIngredient(nuevoIngrediente);
			    }
			     else if (option.equals("3")) {
			        System.out.println("Selecciona un ingrediente para eliminar:");
			        ArrayList<Ingredient> ingredients = ingredientManager.viewIngredients();
			        for (int i = 0; i < ingredients.size(); i++) {
			            System.out.println((i + 1) + ". " + ingredients.get(i).getName()); //Index for each ingredient
			        }
			        System.out.print("Ingresa el número correspondiente al ingrediente que deseas eliminar: ");
			        int selection = Integer.parseInt(scanner.nextLine()) - 1;
			        if (selection >= 0 && selection < ingredients.size()) {
			            ingredientManager.deleteIngredient(ingredients.get(selection).getName());
			        } else {
			            System.out.println("Selección inválida.");
			        }
			    } else if (option.equals("4")) {
			        System.out.println("Lista de ingredientes:");
			        ArrayList<Ingredient> ingredients = ingredientManager.viewIngredients();
			        for (Ingredient ingredient : ingredients) {
			            System.out.println(ingredient);
			        }
			    } else {
			        System.out.println("Opción inválida.");
			    }

			    System.out.print("¿Deseas realizar otra operación? (si/no): ");
			    String continuar = scanner.nextLine();
			    if (!continuar.equalsIgnoreCase("si")) {
			        break;
			    }
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


