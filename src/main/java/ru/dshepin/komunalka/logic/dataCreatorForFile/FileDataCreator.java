package ru.dshepin.komunalka.logic.dataCreatorForFile;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface FileDataCreator<V> {
	HSSFWorkbook create(V data);
}
