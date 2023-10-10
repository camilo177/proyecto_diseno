package proyectoGestorIngredientesDiseño;

public class Ingredient {
	
	private String name;
	private int unit;
	private double valuePerUnit;
	private String purchasePlace;
	private double caloriesPerUnit;

	public Ingredient(String name, int unit, double valuePerUnit, String purchasePlace, double caloriesPerUnit) {
		this.name = name;
		this.unit = unit;
		this.valuePerUnit = valuePerUnit;
		this.purchasePlace = purchasePlace;
		this.caloriesPerUnit = caloriesPerUnit;
	}
	
	@Override
	public String toString() {
		return "Nombre: " + name + ", Unidades: " + unit + ", Valor por Unidad: " + valuePerUnit + ", Lugar de Compra: " + purchasePlace + ", Calorías por Unidad: " + caloriesPerUnit;
	}
	 
// Getters y setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
}

