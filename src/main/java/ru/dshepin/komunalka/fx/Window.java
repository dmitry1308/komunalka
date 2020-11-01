package ru.dshepin.komunalka.fx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public abstract class Window {
	@FXML
	public Button calculateButton;


	public TextField inputHotWater;
	public TextField inputColdWater;
	public TextField inputElectricityWater;

	public TextField inputPrevHotWater;
	public TextField inputPrevColdWater;
	public TextField inputPrevElectricityWater;

	public TextField expenseHotWater;
	public TextField expenseColdWater;
	public TextField expenseElectricity;


	public TextField costHotWater;
	public TextField costColdWater;
	public TextField costElectricity;
	public TextField shit;
	public Label sumCost;

	@FXML
	public abstract void handle();
}
