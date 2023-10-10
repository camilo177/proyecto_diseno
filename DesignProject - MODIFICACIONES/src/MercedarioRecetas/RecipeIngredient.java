package MercedarioRecetas;

public class RecipeIngredient {

    private double quantity;
    protected Ingredientes ingredient;

    public RecipeIngredient() {
    }

    public RecipeIngredient(double quantity, Ingredientes ingredient) {
        this.quantity = quantity;
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double calculateCost() {
        return ingredient.getValuePerUnit() * quantity;
    }

    public double calculateCalories() {
        return ingredient.getCaloriesPerUnit() * quantity;
    }

    public String calculateIngredients() {
        return ingredient.getNameIngredient(); //revisar este metodo. 
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" + "quantity=" + quantity + ", ingredient=" + ingredient.toString() + '}';
    }

}
//notas. no tiene operaciones y faltaba un metodo. 

