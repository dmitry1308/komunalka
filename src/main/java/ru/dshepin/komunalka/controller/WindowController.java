package ru.dshepin.komunalka.controller;

import org.apache.poi.ss.usermodel.Workbook;
import ru.dshepin.komunalka.data.Data;
import ru.dshepin.komunalka.fx.Window;
import ru.dshepin.komunalka.logic.Calculator;
import ru.dshepin.komunalka.logic.dataCreatorForFile.ExcelDataCreator;
import ru.dshepin.komunalka.logic.finder.FileFinder;
import ru.dshepin.komunalka.logic.processor.DataProcesser;
import ru.dshepin.komunalka.logic.reader.ExcelReader;
import ru.dshepin.komunalka.logic.writer.ExcelWriter;

import static ru.dshepin.komunalka.constants.File.FILE_NAME;

public class WindowController extends Window {
	private final DataProcesser dataProcessor;
	private final Calculator calculator;
	private Data data;


	public WindowController() {
		dataProcessor = new DataProcesser();
		calculator = new Calculator();
		data = new Data();
/*
		Sheet page = new ExcelReader().read(FILE_NAME);
		data = dataProcesser.processExcelSheet(page, new Data());
		setFXPartOfData(data);*/

	}

	@Override
	public void handle() {
		data = new Data();
		boolean findedExistFile = new FileFinder().isFind(FILE_NAME);

		Data data = setInputData();

		Data processedData = dataProcessor.process(data);
		if (isFailData(data, processedData)) return;

		Data newData = calculator.calculate(processedData);
		setFXData(newData);

		Workbook book = new ExcelReader().readBook(FILE_NAME);
		Workbook workbook = new ExcelDataCreator<>().writeRowWithDataToBook(book, newData);
		new ExcelWriter().write(workbook);


	}

	private boolean isFailData(Data data, Data checkedData) {
		if (checkedData.isFail()) {
			cleanCalculateData();
			correctFailedParametrs(checkedData);
			data.setFail(false);
			return true;
		}
		return false;
	}

	private void correctFailedParametrs(Data checkedData) {
		inputHotWater.setText(checkedData.getInputHotWater());
		inputColdWater.setText(checkedData.getInputColdWater());
		inputElectricityWater.setText(checkedData.getInputElectricityWater());
		inputPrevHotWater.setText(checkedData.getInputPrevHotWater());
		inputPrevColdWater.setText(checkedData.getInputPrevColdWater());
		inputPrevElectricityWater.setText(checkedData.getInputPrevElectricityWater());
	}

	private void setFXData(Data newData) {
		inputPrevColdWater.setText(newData.getInputPrevColdWater());
		inputPrevHotWater.setText(newData.getInputPrevHotWater());
		inputPrevElectricityWater.setText(newData.getInputPrevElectricityWater());

		expenseColdWater.setText(newData.getExpenseColdWater());
		expenseHotWater.setText(newData.getExpenseHotWater());
		expenseElectricity.setText(newData.getExpenseElectricity());

		costColdWater.setText(newData.getCostColdWater());
		costHotWater.setText(newData.getCostHotWater());
		costElectricity.setText(newData.getCostElectricity());
		shit.setText(newData.getShit());
		sumCost.setText(newData.getSumCost());
	}

	private void setFXPartOfData(Data newData) {
		inputPrevColdWater.setText(newData.getInputPrevColdWater());
		inputPrevHotWater.setText(newData.getInputPrevHotWater());
		inputPrevElectricityWater.setText(newData.getInputPrevElectricityWater());
	}

	private Data setInputData() {
		Data data = new Data();
		data.setInputColdWater(inputColdWater.getText());
		data.setInputHotWater(inputHotWater.getText());
		data.setInputElectricityWater(inputElectricityWater.getText());

		data.setInputPrevColdWater(inputPrevColdWater.getText());
		data.setInputPrevHotWater(inputPrevHotWater.getText());
		data.setInputPrevElectricityWater(inputPrevElectricityWater.getText());

		return data;
	}

	public void cleanAllParameter() {
		inputColdWater.clear();
		inputHotWater.clear();
		inputElectricityWater.clear();

		inputPrevHotWater.clear();
		inputPrevColdWater.clear();
		inputPrevElectricityWater.clear();

		cleanCalculateData();
	}

	private void cleanCalculateData() {
		expenseHotWater.clear();
		expenseColdWater.clear();
		expenseElectricity.clear();

		costHotWater.clear();
		costColdWater.clear();
		costElectricity.clear();
		shit.clear();

		sumCost.setText("");
	}
}
