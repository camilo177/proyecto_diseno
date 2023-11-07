package MercedarioRecetas;

public class Ingredient {

    private String nameIngredient;
    private int unit;
    private double valuePerUnit;
    private String purchasePlace;
    private double caloriesPerUnit;

    // Constructores
    public Ingredient() {
    }

    // Getter and Setter 
    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        if (nameIngredient != null && !nameIngredient.isEmpty()) {
            this.nameIngredient = nameIngredient;
        } else {
            throw new IllegalArgumentException("El nombre del ingrediente no puede estar vacio.");
        }
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        if (unit >= 0) {
            this.unit = unit;
        } else {
            throw new IllegalArgumentException("La cantidad de unidades debe ser un valor no negativo.");
        }
    }

    public double getValuePerUnit() {
        return valuePerUnit;
    }

    public void setValuePerUnit(double valuePerUnit) {
        if (valuePerUnit >= 0) {
            this.valuePerUnit = valuePerUnit;
        } else {
            throw new IllegalArgumentException("El valor por unidad debe ser un valor no negativo.");
        }
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        if (purchasePlace != null && !purchasePlace.isEmpty()) {
            this.purchasePlace = purchasePlace;
        } else {
            throw new IllegalArgumentException("El lugar de compra no puede estar vacio.");
        }
    }

    public double getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public void setCaloriesPerUnit(double caloriesPerUnit) {
        if (caloriesPerUnit >= 0) {
            this.caloriesPerUnit = caloriesPerUnit;
        } else {
            throw new IllegalArgumentException("Las calorias por unidad deben ser un valor no negativo.");
        }
    }

// Método para obtener una representación en cadena del ingrediente
    @Override
    public String toString() {
        return "Ingredientes{" + "nameIngredient=" + nameIngredient + ", unit=" + unit + ", valuePerUnit=" + valuePerUnit + ", purchasePlace=" + purchasePlace + ", caloriesPerUnit=" + caloriesPerUnit + '}';
    }
}
