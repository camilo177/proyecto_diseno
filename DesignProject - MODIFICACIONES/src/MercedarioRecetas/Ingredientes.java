package MercedarioRecetas;

public class Ingredientes {
    
    private String nameIngredient;
    private int unit;
    private double valuePerUnit;
    private String purchasePlace;
    private double caloriesPerUnit;

    public Ingredientes() {
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public double getValuePerUnit() {
        return valuePerUnit;
    }

    public void setValuePerUnit(double valuePerUnit) {
        this.valuePerUnit = valuePerUnit;
    }

    public String getPurchasePlace() {
        return purchasePlace;
    }

    public void setPurchasePlace(String purchasePlace) {
        this.purchasePlace = purchasePlace;
    }

    public double getCaloriesPerUnit() {
        return caloriesPerUnit;
    }

    public void setCaloriesPerUnit(double caloriesPerUnit) {
        this.caloriesPerUnit = caloriesPerUnit;
    }

    @Override
    public String toString() {
        return "Ingredientes{" + "nameIngredient=" + nameIngredient + ", unit=" + unit + ", valuePerUnit=" + valuePerUnit + ", purchasePlace=" + purchasePlace + ", caloriesPerUnit=" + caloriesPerUnit + '}';
    }   
}

//Notas: Constructor vacio.