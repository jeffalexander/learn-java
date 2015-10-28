package com.register.jaa.domain;

public class Item {
	private double itemId;
	private String description;
	private int centsPrice;	
	private Unit unit;
	private int unitCount;
	
	public float price;
	public String objectName;

	public Item(double itemId,
			String description,
			int centsPrice,
			Unit unit,
			int unitCount) {
		this.itemId = itemId;
		this.description = description;
		this.centsPrice = centsPrice;
		this.unit = unit;
		this.unitCount = unitCount;
	}

	public double getItemId() {
		return itemId;
	}

	public void setItemId(double itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCentsPrice() {
		return centsPrice;
	}

	public void setCentsPrice(int centsPrice) {
		this.centsPrice = centsPrice;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public int getUnitCount() {
		return unitCount;
	}

	public void setUnitCount(int unitCount) {
		this.unitCount = unitCount;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", description=" + description
				+ ", centsPrice=" + centsPrice + ", unit=" + unit
				+ ", unitCount=" + unitCount + ", price=" + price
				+ ", objectName=" + objectName + "]";
	}
}