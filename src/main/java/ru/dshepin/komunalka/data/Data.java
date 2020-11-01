package ru.dshepin.komunalka.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Data {
	private String inputHotWater;
	private String inputColdWater;
	private String inputElectricityWater;

	private String inputPrevHotWater;
	private String inputPrevColdWater;
	private String inputPrevElectricityWater;

	public String expenseHotWater;
	public String expenseColdWater;
	public String expenseElectricity;

	public String costHotWater;
	public String costColdWater;
	public String costElectricity;
	public String shit;
	public String sumCost;

	private boolean isFail;

}
