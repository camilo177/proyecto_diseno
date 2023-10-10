package proyectoGestorIngredientesDiseño;

import proyectoGestorIngredientesDiseño.Ingredient;

@SuppressWarnings("unused")
class RecipeIngredient {
    private double quantity;
    private Ingredient ingredient;  // Asumimos que ya tienes una clase Ingredient definida

    public RecipeIngredient() {
        // Constructor por defecto
    }

    public RecipeIngredient(double quantity, Ingredient ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double calculateCalories() {
        // Suponemos que Ingredient tiene un método getCaloriesPerUnit()
        return ingredient.getCaloriesPerUnit() * quantity;
    }

    public double calculateIngredients() {
        // Esta función no está claramente definida en tu pregunta, por lo que la he dejado como un esqueleto.
        return 0.0; // Modifica según tus necesidades.
    }

    @Override
    public String toString() {
        return ingredient.toString() + " - Quantity: " + quantity;
    }
}

