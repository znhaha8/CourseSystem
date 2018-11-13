package main.com.WCZZ.util.excel;
import main.com.WCZZ.entity.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AS {
    public void readExcel(String fileName) throws Exception{
        InputStream is = new FileInputStream(new File(fileName));
       /* Workbook hssfWorkbook = null;
        if (fileName.endsWith("xlsx")){
            hssfWorkbook = new XSSFWorkbook(is);//Excel 2007
        }else if (fileName.endsWith("xls")){
            hssfWorkbook = new HSSFWorkbook(is);//Excel 2003

        }
        User user = null;
        List<User> list = new ArrayList<User>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet <hssfWorkbook.getNumberOfSheets(); numSheet++) {
            Sheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                Row hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    user = new User();
                    Cell name = hssfRow.getCell(0);
                    name.setCellType(1);
                    user.setUsername(name.toString());
                    list.add(user);
                }
            }
            for (User u:list
                 ) {
                System.out.println(u.getUsername());
            }
        }*/
    }
    public HSSFWorkbook outExcel(){
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
//建立新的sheet对象（excel的表单）
        HSSFSheet sheet=wb.createSheet("成绩表");
//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1=sheet.createRow(0);
//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cell=row1.createCell(0);
        //设置单元格内容
        cell.setCellValue("学员考试成绩一览表");
//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
//在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("姓名");
        row2.createCell(1).setCellValue("班级");
        row2.createCell(2).setCellValue("笔试成绩");
        row2.createCell(3).setCellValue("机试成绩");
        //在sheet里创建第三行
        HSSFRow row3=sheet.createRow(2);
        row3.createCell(0).setCellValue("李明");
        row3.createCell(1).setCellValue("As178");
        row3.createCell(2).setCellValue(87);
        row3.createCell(3).setCellValue(78);
        return wb;
    }
    public static void main(String[] args) throws Exception {
        AS s = new AS();
        s.readExcel("C:\\Users\\Wu\\Desktop\\user.xlsx");
    }
}
