package MercedarioRecetas;

public class RecipeIngredient  {

    private double quantity;
    Ingredientes ingredient;


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

    public Ingredientes getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredientes ingredient) {
        this.ingredient = ingredient;
    }

    public double calculateCost() {
        return ingredient.getValuePerUnit() * quantity;
    }

    public double calculateCalories() {
        return ingredient.getCaloriesPerUnit() * quantity;
    }

    public String calculateIngredients() {
        return ingredient.getNameIngredient(); //Dejar este método
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" + "quantity=" + quantity + ", ingredient=" + ingredient.toString() + '}';
    }

}
//notas. no tiene operaciones y faltaba un metodo. 

