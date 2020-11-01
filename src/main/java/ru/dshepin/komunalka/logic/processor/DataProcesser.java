package ru.dshepin.komunalka.logic.processor;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import ru.dshepin.komunalka.data.Data;
import ru.dshepin.komunalka.logic.finder.FileFinder;
import ru.dshepin.komunalka.logic.reader.ExcelReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.dshepin.komunalka.constants.File.FILE_NAME;

public class DataProcesser {
	private static final String REGEX = "[^0-9]";
	private static final String EMPTY_STRING = "";
	private Data data;

	public Data process(Data data) {
		this.data = data;

		String inputColdWater = data.getInputColdWater().trim();
		if (isBad(inputColdWater)) {
			data.setInputColdWater(EMPTY_STRING);
		} else {
			data.setInputColdWater(inputColdWater);
		}


		String inputHotWater = data.getInputHotWater().trim();
		if (isBad(inputHotWater)) {
			data.setInputHotWater(EMPTY_STRING);
		} else {
			data.setInputHotWater(inputHotWater);
		}


		String inputElectricityWater = data.getInputElectricityWater().trim();
		if (isBad(inputElectricityWater)) {
			data.setInputElectricityWater(EMPTY_STRING);
		} else {
			data.setInputElectricityWater(inputElectricityWater);
		}


		boolean isFileFinded = new FileFinder().isFind("Директория файла");
		if (isFileFinded) {
			Sheet page = new ExcelReader().read(FILE_NAME);
			processExcelSheet(page, data);
			return data;
		} else {
			return processInputPrevData();
		}
	}

	private Data processInputPrevData() {
		String inputPrevColdWater = data.getInputPrevColdWater().trim();
		if (isBad(inputPrevColdWater)) {
			data.setInputPrevColdWater(EMPTY_STRING);
		} else {
			data.setInputPrevColdWater(inputPrevColdWater);
		}

		String inputPrevHotWater = data.getInputPrevHotWater().trim();
		if (isBad(inputPrevHotWater)) {
			data.setInputPrevHotWater(EMPTY_STRING);
		} else {
			data.setInputPrevHotWater(inputPrevHotWater);

		}

		String inputPrevElectricityWater = data.getInputPrevElectricityWater().trim();
		if (isBad(inputPrevElectricityWater)) {
			data.setInputPrevElectricityWater(EMPTY_STRING);
		} else {
			data.setInputPrevElectricityWater(inputPrevElectricityWater);
		}
		return data;
	}

	private boolean isBad(String input) {
		if (isHaveNotDigital(input) || isEmpty(input)) {
			data.setFail(true);
			return true;
		}
		return false;
	}

	private boolean isEmpty(String input) {
		return input.equals(EMPTY_STRING);
	}

	private boolean isHaveNotDigital(String input) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(input);
		return matcher.find();
	}

	public Data processExcelSheet(Sheet page, Data processedData) {
		int lastRowNum = page.getLastRowNum();
		Row row = page.getRow(lastRowNum);

		int nummerColumnColdWater = 0;
		int nummerColumnHotWater = 1;
		int nummerColumnElectricity = 2;

		processedData.setInputPrevColdWater(getValueCell(row, nummerColumnColdWater));
		processedData.setInputPrevHotWater(getValueCell(row, nummerColumnHotWater));
		processedData.setInputPrevElectricityWater(getValueCell(row, nummerColumnElectricity));

		return processedData;
	}

	private String getValueCell(Row row, int nummerColumn) {
		double numericCellValue = row.getCell(nummerColumn).getNumericCellValue();
		return String.valueOf(numericCellValue);
	}

}
