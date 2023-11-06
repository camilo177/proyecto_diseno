package MercedarioRecetas;

public class RecipeIngredient {

    private double quantity;
    Ingredient ingredient;

    // Constructores
    public RecipeIngredient() {
    }

    public RecipeIngredient(double quantity, Ingredient ingredient) {
        // Validación de cantidad no negativa
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }

        // Validación de ingrediente no nulo
        if (ingredient == null) {
            throw new NullPointerException("El objeto Ingredient no puede ser nulo");
        }

        this.quantity = quantity;
        this.ingredient = ingredient;
    }
    
    // Getter and Setter 

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        // Validación de cantidad no negativa
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa");
        }

        this.quantity = quantity;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        // Validación de ingrediente no nulo
        if (ingredient == null) {
            throw new NullPointerException("El objeto Ingredient no puede ser nulo");
        }

        this.ingredient = ingredient;
    }
    
    // Método para calcular el costo total de este ingrediente en la receta
    public double calculateCost() {
        if (ingredient == null) {
            throw new IllegalStateException("El ingrediente no está definido para calcular el costo.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (ingredient.getValuePerUnit() < 0) {
            throw new IllegalArgumentException("El valor por unidad del ingrediente no puede ser negativo.");
        }

        return ingredient.getValuePerUnit() * quantity;
    }
    
    // Método para calcular el total de calorías de este ingrediente en la receta
    public double calculateCalories() {
        if (ingredient == null) {
            throw new IllegalStateException("El ingrediente no está definido para calcular las calorias.");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        if (ingredient.getCaloriesPerUnit() < 0) {
            throw new IllegalArgumentException("Las calorias por unidad del ingrediente no pueden ser negativas.");
        }

        return ingredient.getCaloriesPerUnit() * quantity;
    }

    public String calculateIngredients() {
        return ingredient.getNameIngredient();
    }

    // Método para obtener una representación en cadena del ingrediente de la receta
    @Override
    public String toString() {
        return "Ingredient: " + ingredient.getNameIngredient()
                + ", Quantity: " + quantity
                + ", Cost: " + calculateCost()
                + ", Calories: " + calculateCalories();
    }
}
