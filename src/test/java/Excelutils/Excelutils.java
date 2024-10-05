package Excelutils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutils {

    public static String getCellValue(String xl, String sheet, int row, int column) {
        try (FileInputStream fis = new FileInputStream(xl);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Cell cell = workbook.getSheet(sheet).getRow(row).getCell(column);
            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        } catch (IOException e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return "";
        }
    }

    public static int getRowCount(String xl, String sheet) {
        try (FileInputStream fis = new FileInputStream(xl);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            return workbook.getSheet(sheet).getPhysicalNumberOfRows();
        } catch (IOException e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
            return 0;
        }
    }
}

