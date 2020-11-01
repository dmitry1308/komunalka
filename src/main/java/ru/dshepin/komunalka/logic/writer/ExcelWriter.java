package ru.dshepin.komunalka.logic.writer;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

import static ru.dshepin.komunalka.constants.File.FILE_NAME;

public class ExcelWriter implements FileWriter<Workbook> {

	@Override
	public void write(Workbook book) {
		try {
			FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
			book.write(outputStream);
			book.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}