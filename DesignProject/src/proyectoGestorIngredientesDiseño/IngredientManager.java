package proyectoGestorIngredientesDiseño;
import java.util.ArrayList;

public class IngredientManager {

	private ArrayList<Ingredient> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public Ingredient getIngredientByName(String name) {
    	for (Ingredient ingredient : ingredients) {
    		if (ingredient.getName().equals(name)) {
                return ingredient;
            }
        }
        return null;
    }

    public void setIngredientName(String oldName, String newName) {
        Ingredient ingredient = getIngredientByName(oldName);
        if (ingredient != null) {
            ingredient.setName(newName);
        }
    }

    public int getIngredientUnits(String name) {
        Ingredient ingredient = getIngredientByName(name);

        if (ingredient != null) {
            return ingredient.getUnit();
        } else {
            return -1;
        }
    }

    public void setIngredientUnits(String name, int unit) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setUnit(unit);
        }
    }

    public double getIngredientValuePerUnit(String name) {
        Ingredient ingredient = getIngredientByName(name);

        if (ingredient != null) {
            return ingredient.getValuePerUnit();
        } else {
            return -1;
        }
    }

    public void setIngredientValuePerUnit(String name, double valuePerUnit) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setValuePerUnit(valuePerUnit);
        }
    }

    public String getIngredientPurchaseLocation(String name) {
        Ingredient ingredient = getIngredientByName(name);

        if (ingredient != null) {
            return ingredient.getPurchasePlace();
        } else {
            return null;
        }
    }

    public void setIngredientPurchaseLocation(String name, String purchasePlace) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setPurchasePlace(purchasePlace);
        }
    }

    public double getIngredientCaloriesPerUnit(String name) {
        Ingredient ingredient = getIngredientByName(name);

        if (ingredient != null) {
            return ingredient.getCaloriesPerUnit();
        } else {
            return -1;
        }
    }

    public void setIngredientCaloriesPerUnit(String name, double caloriesPerUnit) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredient.setCaloriesPerUnit(caloriesPerUnit);
        }
    }

    public void addIngredients(ArrayList<Ingredient> newIngredients) {
        ingredients.addAll(newIngredients);
    }


    public boolean deleteIngredient(String name) {
        Ingredient ingredient = getIngredientByName(name);
        if (ingredient != null) {
            ingredients.remove(ingredient);
            return true;
        }
        return false;
    }

    public ArrayList<Ingredient> viewIngredients() {
        return ingredients;
    }

}
