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
	
	@JsonProperty("product")
	private void nutrimentEnergy(JsonNode product) { 
		JsonNode nutriments = product.get("nutriments");
		nutrimentsEnergy = nutriments.get("energy").asInt(); 
		energyKj = nutriments.get("energy-kj").asInt(); 
		energyKj100g = nutriments.get("energy-kj_100g").asInt();
		energyKjUnit = nutriments.get("energy-kj_unit").asText();
		energyKjValue = nutriments.get("energy-kj_value").asInt();
		energyKjValueComputed = nutriments.get("energy-kj_value_computed").asDouble();
		energy100g = nutriments.get("energy_100g").asInt();
		energyUnit = nutriments.get("energy_unit").asText();
		energyValue = nutriments.get("energy_value").asInt();
		//return nutrimentsEnergy;
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

	@Override
	public String toString() {
		return "Food [code=" + code + ", status=" + status + ", status_verbose=" + status_verbose
				+ ", nutrimentsEnergy=" + nutrimentsEnergy + ", energyKj=" + energyKj + ", energyKj100g=" + energyKj100g
				+ ", energyKjUnit=" + energyKjUnit + ", energyKjValue=" + energyKjValue + ", energyKjValueComputed="
				+ energyKjValueComputed + ", energy100g=" + energy100g + ", energyUnit=" + energyUnit + ", energyValue="
				+ energyValue + "]";
	}

}
