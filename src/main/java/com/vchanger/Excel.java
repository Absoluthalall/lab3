package com.vchanger;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Excel {
    HashMap<String,HashMap<String,Double>> dataCountry;
    HashMap<String,HashMap<String,Double>> dataRegion;
    HashMap<String,HashMap<String,Double>> dataOperator;
    public Excel(HashMap<String,HashMap<String,Double>> dataCountry,HashMap<String,HashMap<String,Double>> dataRegion,HashMap<String,HashMap<String,Double>> dataOperator){
        this.dataCountry = dataCountry;
        this.dataOperator = dataOperator;
        this.dataRegion = dataRegion;
    }
    public void createSheet(Sheet sheet,HashMap<String,HashMap<String,Double>> data,String type){
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue(type);
        headerRow.createCell(1).setCellValue("Объем");
        headerRow.createCell(2).setCellValue("Год");

        int rowIndex = 1;
        for (Map.Entry<String, HashMap<String, Double>> entry : data.entrySet()) {
            String region = entry.getKey();
            HashMap<String, Double> consumption = entry.getValue();

            for (Map.Entry<String, Double> entry2 : consumption.entrySet()) {
                String year = entry2.getKey();
                Double volume = entry2.getValue();

                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(region);
                row.createCell(1).setCellValue(volume);
                row.createCell(2).setCellValue(year);
            }
        }
    }
    public void createExcelFile() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet1 = workbook.createSheet("Данные по странам");
        createSheet(sheet1,dataCountry,"Страна");
        Sheet sheet2 = workbook.createSheet("Данные по регионам");
        createSheet(sheet2,dataRegion,"Регион");
        Sheet sheet3 = workbook.createSheet("Данные по команиям");
        createSheet(sheet3,dataOperator,"Компания");
        File file = new File("data.xlsx");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out = new FileOutputStream("data.xlsx");
        workbook.write(out);
        out.close();

        System.out.println("Файл Excel успешно создан.");
    }
}
