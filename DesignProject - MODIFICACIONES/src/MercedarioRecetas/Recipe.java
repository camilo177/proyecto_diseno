package MercedarioRecetas;

import java.util.List;

public class Recipe {

    private String nameRecipe;
    private int preparationTime;
    private int serves;
    private List<RecipeIngredient> ingredientList;
    private String preparationDescription;
    
    // Constructores
    public Recipe() {
    }

    public Recipe(String nameRecipe, int preparationTime, int serves, List<RecipeIngredient> ingredientList, String preparationDescription) {
        this.nameRecipe = nameRecipe;
        this.preparationTime = preparationTime;
        this.serves = serves;
        this.ingredientList = ingredientList;
        this.preparationDescription = preparationDescription;
    }
    
    // Getter and Setter 

    public String getNameRecipe() {
        return nameRecipe;
    }

     public void setNameRecipe(String nameRecipe) {
        if (nameRecipe != null && !nameRecipe.isEmpty()) {
            this.nameRecipe = nameRecipe;
        } else {
            throw new IllegalArgumentException("El nombre de la receta no puede estar vacio.");
        }
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        if (preparationTime >= 0) {
            this.preparationTime = preparationTime;
        } else {
            throw new IllegalArgumentException("El tiempo de preparacion debe ser un valor no negativo.");
        }
    }

    public int getServes() {
        return serves;
    }

    public void setServes(int serves) {
        if (serves >= 0) {
            this.serves = serves;
        } else {
            throw new IllegalArgumentException("El numero de porciones debe ser un valor no negativo.");
        }
    }

    public List<RecipeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RecipeIngredient> ingredientList) {
        if (ingredientList != null && !ingredientList.isEmpty()) {
            this.ingredientList = ingredientList;
        } else {
            throw new IllegalArgumentException("La lista de ingredientes no puede estar vacia.");
        }
    }

    public String getPreparationDescription() {
        return preparationDescription;
    }

    public void setPreparationDescription(String preparationDescription) {
        if (preparationDescription != null && !preparationDescription.isEmpty()) {
            this.preparationDescription = preparationDescription;
        } else {
            throw new IllegalArgumentException("La descripción de preparación no puede estar vacía.");
        }
    }

    // Método para obtener una representación en cadena de la receta
    @Override
    public String toString() {
        return "Recipe{" + "nameRecipe=" + nameRecipe + ", preparationTime=" + preparationTime + ", serves=" + serves + ", ingredientList=" + ingredientList + ", preparationDescription=" + preparationDescription + '}';
    }
}
