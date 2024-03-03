package com.sumerge.automation.task.dataModel;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {

    static FileInputStream fis;

    public FileInputStream getFileInputStream()
    {
        String filePath = System.getProperty("user.dir")+"/src/main/resources/TestData/TD.xlsx";
        File srcFile = new File(filePath);
        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return fis;


    }

    public Object[][] getExcelData()
    {
        fis = getFileInputStream();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int totalNumberofRows= (sheet.getLastRowNum()+1);
            int totalNumberofCoulmns=3;

            String [][] arrayOfExcelData = new String[totalNumberofRows][totalNumberofCoulmns];
            for (int i = 0; i < totalNumberofRows; i++) {
                for (int j = 0; j < totalNumberofCoulmns; j++) {
                    XSSFRow row = sheet.getRow(i);
                    arrayOfExcelData[i][j] = row.getCell(j).toString();
                }
            }
            workbook.close();
            return arrayOfExcelData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
