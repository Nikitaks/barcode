package com.barcode.messageprocesor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class Food {

	private String code;
	private int status;
	private String status_verbose;
	
	private int nutrimentsEnergy;
	private int energyKj;
	private int energyKj100g;
	private String energyKjUnit;
	private int energyKjValue;
	private double energyKjValueComputed;
	private int energy100g;
	private String energyUnit;
	private int energyValue;
	private int proteins;
	private int proteins_100g;
	private int fat;
	private int fat_100g;
	private int carbohydrates;
	private int carbohydrates_100g;

	@JsonProperty("product")
	private void nutrimentEnergy(JsonNode product) { 
		JsonNode nutriments = product.get("nutriments");
		proteins = nutriments.get("proteins").asInt();
		proteins_100g = nutriments.get("proteins_100g").asInt();
		fat = nutriments.get("fat").asInt();
		fat_100g = nutriments.get("fat_100g").asInt();
		carbohydrates = nutriments.get("carbohydrates").asInt();
		carbohydrates_100g = nutriments.get("carbohydrates_100g").asInt();
		nutrimentsEnergy = nutriments.get("energy").asInt(); 
		energyKj = nutriments.get("energy-kj").asInt(); 
		energyKj100g = nutriments.get("energy-kj_100g").asInt();
		energyKjUnit = nutriments.get("energy-kj_unit").asText();
		energyKjValue = nutriments.get("energy-kj_value").asInt();
		energyKjValueComputed = nutriments.get("energy-kj_value_computed").asDouble();
		energy100g = nutriments.get("energy_100g").asInt();
		energyUnit = nutriments.get("energy_unit").asText();
		energyValue = nutriments.get("energy_value").asInt();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatus_verbose() {
		return status_verbose;
	}

	public void setStatus_verbose(String status_verbose) {
		this.status_verbose = status_verbose;
	}

	public int getNutrimentsEnergy() {
		return nutrimentsEnergy;
	}

	public void setNutrimentsEnergy(int nutrimentsEnergy) {
		this.nutrimentsEnergy = nutrimentsEnergy;
	}

	public int getEnergyKj() {
		return energyKj;
	}

	public void setEnergyKj(int energyKj) {
		this.energyKj = energyKj;
	}

	public int getEnergyKj100g() {
		return energyKj100g;
	}

	public void setEnergyKj100g(int energyKj100g) {
		this.energyKj100g = energyKj100g;
	}

	public String getEnergyKjUnit() {
		return energyKjUnit;
	}

	public void setEnergyKjUnit(String energyKjUnit) {
		this.energyKjUnit = energyKjUnit;
	}

	public int getEnergyKjValue() {
		return energyKjValue;
	}

	public void setEnergyKjValue(int energyKjValue) {
		this.energyKjValue = energyKjValue;
	}

	public double getEnergyKjValueComputed() {
		return energyKjValueComputed;
	}

	public void setEnergyKjValueComputed(double energyKjValueComputed) {
		this.energyKjValueComputed = energyKjValueComputed;
	}

	public int getEnergy100g() {
		return energy100g;
	}

	public void setEnergy100g(int energy100g) {
		this.energy100g = energy100g;
	}

	public String getEnergyUnit() {
		return energyUnit;
	}

	public void setEnergyUnit(String energyUnit) {
		this.energyUnit = energyUnit;
	}

	public int getEnergyValue() {
		return energyValue;
	}

	public void setEnergyValue(int energyValue) {
		this.energyValue = energyValue;
	}

	public int getProteins() {
		return proteins;
	}

	public void setProteins(int proteins) {
		this.proteins = proteins;
	}

	public int getProteins_100g() {
		return proteins_100g;
	}

	public void setProteins_100g(int proteins_100g) {
		this.proteins_100g = proteins_100g;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getFat_100g() {
		return fat_100g;
	}

	public void setFat_100g(int fat_100g) {
		this.fat_100g = fat_100g;
	}

	public int getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(int carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public int getCarbohydrates_100g() {
		return carbohydrates_100g;
	}

	public void setCarbohydrates_100g(int carbohydrates_100g) {
		this.carbohydrates_100g = carbohydrates_100g;
	}

	@Override
	public String toString() {
		return "Food [code=" + code + ", status=" + status + ", status_verbose=" + status_verbose
				+ ", nutrimentsEnergy=" + nutrimentsEnergy + ", energyKj=" + energyKj + ", energyKj100g=" + energyKj100g
				+ ", energyKjUnit=" + energyKjUnit + ", energyKjValue=" + energyKjValue + ", energyKjValueComputed="
				+ energyKjValueComputed + ", energy100g=" + energy100g + ", energyUnit=" + energyUnit + ", energyValue="
				+ energyValue + ", proteins=" + proteins + ", proteins_100g=" + proteins_100g + ", fat=" + fat
				+ ", fat_100g=" + fat_100g + ", carbohydrates=" + carbohydrates + ", carbohydrates_100g="
				+ carbohydrates_100g + "]";
	}

}
