package ru.dshepin.komunalka.logic;


import ru.dshepin.komunalka.data.Data;

import static ru.dshepin.komunalka.constants.Price.*;


public class Calculator {
	private Data data;

	public Data calculate(Data data) {
		this.data = data;
		calculateExpense();
		calculateCost();
		return data;
	}

	private void calculateCost() {
		double expensiveColdWater = Double.parseDouble(data.getExpenseColdWater());
		double coldCost = expensiveColdWater * COLD_WATER_PRICE;
		data.setCostColdWater(String.valueOf(coldCost));

		double expensiveHotWater = Double.parseDouble(data.getExpenseHotWater());
		double hoCost = expensiveHotWater * HOT_WATER_PRICE;
		data.setCostHotWater(String.valueOf(hoCost));

		double shit = Double.parseDouble(data.getExpenseColdWater()) + Double.parseDouble(data.getExpenseHotWater());
		double shitCost = shit * SHIT;
		data.setShit(String.valueOf(shitCost));

		double electricity = Double.parseDouble(data.getExpenseElectricity());
		double electCost = electricity * ELECTRICITY_PRICE;
		data.setCostElectricity(String.valueOf(electCost));

		String summa = String.valueOf(coldCost + hoCost + shitCost + electCost);
		data.setSumCost(summa);
	}

	private void calculateExpense() {
		double hotWater = Double.valueOf(data.getInputHotWater());
		double prevHotWater = Double.valueOf(data.getInputPrevHotWater());
		data.setExpenseHotWater(String.valueOf(hotWater - prevHotWater));

		double ColdWater = Double.valueOf(data.getInputColdWater());
		double prevColdWater = Double.valueOf(data.getInputPrevColdWater());
		data.setExpenseColdWater(String.valueOf(ColdWater - prevColdWater));

		double electricity = Double.valueOf(data.getInputElectricityWater());
		double prevElectricity = Double.valueOf(data.getInputPrevElectricityWater());
		data.setExpenseElectricity(String.valueOf(electricity - prevElectricity));
	}
}
