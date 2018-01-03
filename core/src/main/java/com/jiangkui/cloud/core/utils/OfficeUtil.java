package com.jiangkui.cloud.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.util.SheetUtil;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Office格式的软件的工具类
 * Created by yunai on 13-12-31.
 */
public class OfficeUtil {

    /**
     * 正则，用于匹配0串
     */
    private static final Pattern PATTERN_ZEROS = Pattern.compile("[0]*");

    /**
     * 读取Excel，并返回数据字符串数组
     *
     * @param file excel文件
     * @return 数据字符串数组
     * @throws IOException 当file为不支持的文件类型
     */
    public static List<List<String>> readExcel(File file) throws IOException {
        return readExcel(file, null, null);
    }

    public static List<List<String>> readExcel(File file, Integer index) throws IOException {
        return readExcel(file, index, null);
    }

    public static List<List<String>> readExcel(File file, String name) throws IOException {
        return readExcel(file, null, name);
    }

    public static List<List<String>> readExcel(File file, Integer index, String name) throws IOException {
        String fileName = file.getName();
        String extension = fileName.lastIndexOf('.') == -1 ? "" : fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (extension) {
            case "xls":
                return read2003Excel(file, index, name);
            case "xlsx":
                return read2007Excel(file, index, name);
            default:
                throw new IOException("不支持的文件类型");
        }
    }

    /**
     * 读取Excel文件
     *
     * @param file
     * @param index
     * @param name
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    public static List<List<String>> readExcel(MultipartFile file, Integer index, String name) throws IOException, InvalidFormatException {
        //获取文件名称
        String fileName = file.getOriginalFilename();
        String extension = fileName.lastIndexOf('.') == -1 ? "" : fileName.substring(fileName.lastIndexOf('.') + 1);
        switch (extension) {
            case "xls":
                return read2003ExcelByType(file, index, name);
            case "xlsx":
                return read2007ExcelByType(file, index, name);
            default:
                throw new IOException("不支持的文件类型");
        }
    }


    /**
     * 读取 office 2003 excel
     */
    private static List<List<String>> read2003Excel(File file, Integer index, String name) throws IOException {
        List<List<String>> list = new LinkedList<>();
        HSSFWorkbook hwb = new HSSFWorkbook(new FileInputStream(file));
        HSSFSheet sheet = (HSSFSheet) getSheet(hwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            HSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                HSSFCell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }

    /**
     * 读取 office 2003 excel
     */
    public static List<List<String>> read2003Excel(MultipartFile file, Integer index, String name) throws IOException, InvalidFormatException {
        List<List<String>> list = new LinkedList<>();
        Workbook xwb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }

    /**
     * 读取Office 2007 excel
     */
    private static List<List<String>> read2007Excel(File file, Integer index, String name) throws IOException {
        List<List<String>> list = new LinkedList<>();
        XSSFWorkbook xwb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = (XSSFSheet) getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                XSSFCell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }

    /**
     *读取excel
     * 读取Office 2007 excel
     */
    public static List<List<String>> read2007Excel(MultipartFile file, Integer index, String name) throws IOException, InvalidFormatException {
        List<List<String>> list = new LinkedList<>();
        Workbook xwb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    String value = cell.toString();
                    String valueInt = parseInt(value);
                    if (valueInt != null) {
                        linked.add(valueInt);
                    } else {
                        linked.add(value);
                    }
                }
            }
            list.add(linked);
        }
        return list;
    }


     /**根据 单元格类型 读取
     * @author ming
     * @date 2017/8/2
     */
    public static List<List<String>> read2003ExcelByType(MultipartFile file, Integer index, String name) throws IOException, InvalidFormatException {
        List<List<String>> list = new LinkedList<>();
        Workbook xwb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                   readCellByType(cell,linked);
                }
            }
            list.add(linked);
        }
        return list;
    }

     /**
      * 读取Office 2007 excel  根据 不同的单元格类型 处理  避免出现科学计数法的数字字符串
      * @author ming
     * @date 2017/8/2
     */
    public static List<List<String>> read2007ExcelByType(MultipartFile file, Integer index, String name) throws IOException, InvalidFormatException {
        List<List<String>> list = new LinkedList<>();
        Workbook xwb = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = getSheet(xwb, index, name);
        int recordValueSize = sheet.getRow(sheet.getFirstRowNum()).getLastCellNum(); // 每行记录字段数，防止有些行，有很多空记录
        for (int i = sheet.getFirstRowNum(); i <= sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<String> linked = new LinkedList<>();
            for (int j = row.getFirstCellNum(); j < recordValueSize; j++) {
                Cell cell = row.getCell(j);
                if (cell == null) {
                    linked.add("");
                } else {
                    readCellByType(cell,linked);
                }
            }
            list.add(linked);
        }
        return list;
    }


     /**根据不同的单元格类型来处理 单元格类容
     * @author ming
     * @date 2017/8/2
     */
    private static void readCellByType(Cell cell,List<String> list){
        //根据 不同的单元格类型 处理
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC:
                //数值型
                list.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                break;
            case Cell.CELL_TYPE_STRING:
                list.add(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK:
                list.add("");
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                list.add(String.valueOf(cell.getBooleanCellValue()));
                break;
            case Cell.CELL_TYPE_ERROR:
                throw new RuntimeException("cell 类型错误");
            default:
                throw new RuntimeException("没有这种Cell类型");
        }
    }



    private static Sheet getSheet(Workbook book, Integer index, String name) {
        if (index != null && name != null) {
            throw new RuntimeException("index,name只能有一个不为空！");
        }
        Sheet sheet;
        if (index != null) {
            sheet = book.getSheetAt(index);
        } else if (name != null) {
            sheet = book.getSheet(name);
        } else {
            sheet = book.getSheetAt(0);
        }
        return sheet;
    }

    /**
     * 特殊方法。将象"123.0"类型的字符串的".0"去掉<br />
     * 若不符合这样的条件，返回null
     *
     * @param value 字符串
     * @return 字符串
     */
    private static String parseInt(String value) {
        String[] strs = value.split("\\.");
        if (strs.length != 2) {
            return null;
        }
        if (StringUtils.isNumeric(strs[0]) && PATTERN_ZEROS.matcher(strs[1]).matches()) {
            return strs[0];
        }
        return null;
    }

    public static XSSFWorkbook buildExcel2007(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<String>> values) {
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFCellStyle style = wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        XSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i++) {
            XSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                newRow.createCell(j).setCellValue(values.get(i).get(j));
            }
        }
        return wb;
    }

    public static HSSFWorkbook buildExcel2003(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values) {
        HSSFWorkbook wb = new HSSFWorkbook();
        buildExcelSheet2003(wb, sheetName, columnWidths, columnNames, values);
        return wb;
    }

    public static HSSFWorkbook buildExcelSheet2003(HSSFWorkbook wb, String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        HSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i++) {
            HSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).doubleValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
//                if (val instanceof String) {
//                    newRow.createCell(j).setCellValue((String) val);
//                } else if (val instanceof Double) {
//                    newRow.createCell(j).setCellType();
//                }
            }
        }
        return wb;
    }

    /**
     * 二级表头Excel
     *
     * @param sheetName    表头
     * @param columnWidths 列宽
     * @param values       表数据
     * @param jsonTitle    表头JSON字符串
     * @return
     */
    public static HSSFWorkbook buildTwoLevelTitleExcel2003(String sheetName, List<Integer> columnWidths, List<List<Object>> values, String jsonTitle) {
        LinkedHashMap<String, List<String>> jsonMap = JSON.parseObject(jsonTitle, new TypeReference<LinkedHashMap<String, List<String>>>() {
        });

        Map<Integer[], String> titleMap = new LinkedHashMap<>();
        int colIndex = 0;
        for (Map.Entry<String, List<String>> entry : jsonMap.entrySet()) {
            String firstRow = entry.getKey();
            List<String> secondRow = entry.getValue();
            if (secondRow.isEmpty()) {
                titleMap.put(new Integer[]{0, 1, colIndex, colIndex}, firstRow);
                colIndex++;
            } else {
                titleMap.put(new Integer[]{0, 0, colIndex, (colIndex + secondRow.size() - 1)}, firstRow);
                colIndex += secondRow.size();
            }
        }

        colIndex = 0;
        for (Map.Entry<String, List<String>> entry : jsonMap.entrySet()) {
            List<String> secondRow = entry.getValue();
            if (secondRow.isEmpty()) {
                colIndex++;
            } else {
                for (int i = 0; i < secondRow.size(); i++) {
                    titleMap.put(new Integer[]{1, 1, colIndex + i, colIndex + i}, secondRow.get(i));
                }
                colIndex += secondRow.size();
            }
        }

        return buildExcel2003ByTitle(sheetName, columnWidths, values, titleMap);
    }

    /**
     * 自定义表头
     *
     * @param sheetName
     * @param columnWidths
     * @param values
     * @param titleMap     Map<String,Integer[]>
     *                     key:[0]起始行索引 [1]结束行索引 [2]起始列索引 [3]结束列索引
     *                     value(Integer[])：需要显示在表格的内容
     *                     注：全部索引从0开始
     * @return
     */
    public static HSSFWorkbook buildExcel2003ByTitle(String sheetName, List<Integer> columnWidths, List<List<Object>> values, Map<Integer[], String> titleMap) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);

        for (int i = 0; i < columnWidths.size(); i++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }
        HSSFCellStyle columnHeadStyle = wb.createCellStyle();
        columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        int currentRowIndex = -1;
        HSSFRow row = null;
        HSSFCell cellTitle = null;
        for (Integer[] index : titleMap.keySet()) {
            String title = titleMap.get(index);
            sheet.addMergedRegion(new CellRangeAddress(index[0], index[1], index[2], index[3]));
            if (currentRowIndex != index[0] || row == null) {
                row = sheet.createRow(index[0]);
                currentRowIndex = index[0];
            }
            cellTitle = row.createCell(index[2]);
            cellTitle.setCellValue(new HSSFRichTextString(title));
            cellTitle.setCellStyle(columnHeadStyle);
        }

        for (int i = 0; i < values.size(); i++, currentRowIndex++) {
            HSSFRow newRow = sheet.createRow(currentRowIndex + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).doubleValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
                cell.setCellStyle(columnHeadStyle);
            }
        }
        return wb;
    }

    /**
     * 构建excel   当传入  列宽  则使用 传入参数  反之则自动调整
     * xu
     *
     * @param sheetName
     * @param columnNames
     * @param columnWidths
     * @param values
     * @return HSSDWWorkbook
     */
    public static HSSFWorkbook buildExcel(String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<String>> values) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle style = wb.createCellStyle();
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        HSSFSheet sheet = wb.createSheet(sheetName);
        for (int i = 0; i < columnWidths.size(); i++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < columnNames.size(); i++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }
        //记录大列数
        int maxWidth = 0;
        for (int i = 0; i < values.size(); i++) {
            HSSFRow newRow = sheet.createRow(i + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                newRow.createCell(j).setCellValue(values.get(i).get(j));
            }
            //获取每行的最大列数  自动设置列宽使用
            if (maxWidth<newRow.getPhysicalNumberOfCells()){
                maxWidth = newRow.getPhysicalNumberOfCells();
            }
        }
        // todo ming 如果没有设定列宽 自动调整列宽  如果有中文  无法自动设置列宽
        if (columnWidths.isEmpty()) {
            for (int i = 0; i < maxWidth; i++) {
                sheet.autoSizeColumn(i);
            }
        }
        return wb;
    }

    /**
     * 构建excel   当传入  列宽  则使用 传入参数  反之则自动调整
     *
     * @param sheetName
     * @param columnNames
     * @param columnWidths
     * @param values
     * @return HSSDWWorkbook
     */
    public static HSSFWorkbook buildExcel2(HSSFWorkbook wb,String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values) {
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        style.setFillPattern(HSSFCellStyle.ALIGN_LEFT);
        HSSFSheet sheet = wb.getSheet(sheetName);
        //记录大列数
        int maxWidth = sheet.getLastRowNum();
        HSSFRow row = null;
        row = sheet.createRow(maxWidth + 1);
        maxWidth +=1;
        for (int i = 0; i < columnWidths.size(); i++) {
            sheet.setColumnWidth(i, columnWidths.get(i));
        }

        for (int i = 0; i < columnNames.size(); i++) {
            row.createCell(i).setCellValue(columnNames.get(i));
        }

        for (int i = 0; i < values.size(); i++,maxWidth++) {
            HSSFRow newRow = sheet.createRow(maxWidth + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).intValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
            }
        }

        //如果没有设定列宽 自动调整列宽
        if (CollectionUtil.isEmpty(columnWidths)) {
            for (int i = 0; i < maxWidth; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        return wb;
    }

    /**
     * 添加sheet
     *
     * @param wb
     * @param sheetName    sheet名
     * @param headers      表头
     * @param columnWidths 列宽
     * @param columnNames  列名
     * @param values       值
     * @return
     */
    public static HSSFWorkbook addSheet(HSSFWorkbook wb, String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values, List<String> headers) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        HSSFSheet sheet = wb.createSheet(sheetName);


        int currentRowIndex = -1;
        HSSFRow row = null;

        for (int i = 0; i < headers.size(); i++, currentRowIndex++) {
            row = sheet.createRow(currentRowIndex + 1);
            HSSFCell cell = row.createCell(0);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, columnNames.size() - 1));
        }

        if (!CollectionUtil.isEmpty(columnWidths)) {
            for (int i = 0; i < columnWidths.size(); i++) {
                sheet.setColumnWidth(i, columnWidths.get(i));
            }
        }

        row = sheet.createRow(currentRowIndex + 1);
        currentRowIndex += 1;
        for (int i = 0; i < columnNames.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNames.get(i));
        }
        //统计列数
        int columnCount = columnNames.size();
        for (int i = 0; i < values.size(); i++, currentRowIndex++) {
            HSSFRow newRow = sheet.createRow(currentRowIndex + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                cell.setCellStyle(cellStyle);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).intValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
            }
        }

        if (CollectionUtil.isEmpty(columnWidths)) {
            Integer maxWiths = columnNames.size();
            for (int i = 0; i < maxWiths; i++) {
                //获取当前列的列宽
                double width = SheetUtil.getColumnWidth(sheet, i, true);
                if (width != -1) {
                    width *= 512;
                    int maxColumnWidth = 255 * 256;
                    if (width > maxColumnWidth) {
                        width = maxColumnWidth;
                    }
                }
                sheet.setColumnWidth(i, (int) width);
            }
        }
        return wb;
    }

    /**
     * 为sheet追加内容 针对动态表行数据的添加 普通的静态表可以考虑不需要这个方法
     *
     * @param wb
     * @param sheetName    sheet名
     * @param columnWidths 列宽
     * @param columnNames  列名
     * @param values       列值
     * @return
     */
    public static HSSFWorkbook addSheetContent(HSSFWorkbook wb, String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中


        HSSFSheet sheet = wb.getSheet(sheetName);
        Integer currentRowIndex = sheet.getLastRowNum();
        HSSFRow row = null;
        row = sheet.createRow(currentRowIndex + 1);
        currentRowIndex += 1;
        for (int i = 0; i < columnNames.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNames.get(i));
        }
        //统计列数
        int columnCount = columnNames.size();
        if (CollectionUtil.notEmpty(columnWidths)) {
            for (int i = 0; i < columnWidths.size(); i++) {
                sheet.setColumnWidth(i, columnWidths.get(i));
            }
        } else {
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }
        }


        for (int i = 0; i < values.size(); i++, currentRowIndex++) {
            HSSFRow newRow = sheet.createRow(currentRowIndex + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                cell.setCellStyle(cellStyle);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).intValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
            }
        }
        return wb;
    }

    /**
     * 对账记录导出
     *
     * @param wb
     * @param sheetName    sheet名
     * @param columnWidths 列宽
     * @param columnNames  列名
     * @param values       列值
     * @return
     */
    public static HSSFWorkbook buildExcelReconciliation(HSSFWorkbook wb, String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<Object>> values) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        cellStyle.setFont(font);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中


        HSSFSheet sheet = wb.getSheet(sheetName);
        Integer currentRowIndex = sheet.getLastRowNum();
        HSSFRow row = null;
        row = sheet.createRow(currentRowIndex + 1);
        currentRowIndex += 1;
        for (int i = 0; i < columnNames.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNames.get(i));
        }
        //统计列数
        int columnCount = columnNames.size();
        if (CollectionUtil.notEmpty(columnWidths)) {
            for (int i = 0; i < columnWidths.size(); i++) {
                sheet.setColumnWidth(i, columnWidths.get(i));
            }
        } else {
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }
        }


        for (int i = 0; i < values.size(); i++, currentRowIndex++) {
            HSSFRow newRow = sheet.createRow(currentRowIndex + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                Object val = values.get(i).get(j);
                HSSFCell cell = newRow.createCell(j);
                cell.setCellStyle(cellStyle);
                if (val instanceof Number) {
                    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(((Number) val).intValue());
                } else {
                    if (val == null) {
                        cell.setCellValue("");
                    } else {
                        cell.setCellValue(String.valueOf(val));
                    }
                }
            }
        }
        //设置对账信息样式
        HSSFCellStyle style1 = wb.createCellStyle();
        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        HSSFFont font1 = wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font1.setFontHeightInPoints((short) 12);
        style1.setFont(font1);
        //样式修改
        for(int i=0;i<6;i++){
            HSSFRow row1 = sheet.getRow(i+1);
            for(int j = 0;j<8;j++){
                HSSFCell cell1 = row1.getCell(j);
                if(cell1!=null){
                    cell1.setCellStyle(style1);
                }
            }
        }

        return wb;
    }

    /**
     * 添加行数据
     *
     * @param sheet
     * @param columnNamesAndValues 行数据
     */
    public static HSSFSheet addRow(HSSFSheet sheet, List<String> columnNamesAndValues) {
        Integer currentRowIndex = sheet.getLastRowNum();
        HSSFRow row = sheet.createRow(currentRowIndex + 1);
        HSSFCell cell = null;
        int currentColIndex = 0;
        for (int i = 0; i < columnNamesAndValues.size(); i++, currentColIndex++) {
            cell = row.createCell(currentColIndex);
            cell.setCellValue(columnNamesAndValues.get(i));
        }
        return sheet;
    }

    /**
     * 增加sheet 兼容2003和2007
     *
     * @param wb
     * @param sheetName
     * @param columnWidths
     * @param columnNames
     * @param values
     * @param headers
     * @return
     */
    public static Workbook addSheet(Workbook wb, String sheetName, List<Integer> columnWidths, List<String> columnNames, List<List<String>> values, List<String> headers) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

        Sheet sheet = wb.createSheet(sheetName);


        int currentRowIndex = -1;
        Row row = null;

        if (!CollectionUtil.isEmpty(headers)) {
            for (int i = 0; i < headers.size(); i++, currentRowIndex++) {
                row = sheet.createRow(currentRowIndex + 1);
                Cell cell = row.createCell(0);
                cell.setCellValue(headers.get(i));
                cell.setCellStyle(cellStyle);
                sheet.addMergedRegion(new CellRangeAddress(i, i, 0, columnNames.size() - 1));
            }
        }

        if (!CollectionUtil.isEmpty(columnWidths)) {
            for (int i = 0; i < columnWidths.size(); i++) {
                sheet.setColumnWidth(i, columnWidths.get(i));
            }
        }

        row = sheet.createRow(currentRowIndex + 1);
        currentRowIndex += 1;
        for (int i = 0; i < columnNames.size(); i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(columnNames.get(i));
        }
        //统计列数
        int columnCount = columnNames.size();
        for (int i = 0; i < values.size(); i++, currentRowIndex++) {
            Row newRow = sheet.createRow(currentRowIndex + 1);
            for (int j = 0; j < values.get(i).size(); j++) {
                String val = values.get(i).get(j);
                Cell cell = newRow.createCell(j);
                cell.setCellStyle(cellStyle);
                if (val == null) {
                    cell.setCellValue("");
                } else {
                    cell.setCellValue(String.valueOf(val));
                }
            }
        }

        if (CollectionUtil.isEmpty(columnWidths)) {
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }
        }
        return wb;
    }
    /**
     *        HSSFRow row = null;
     row = sheet.createRow(currentRowIndex + 1);
     currentRowIndex += 1;
     //标题行
     HSSFCell cell = row.createCell(0);
     cell.setCellValue(activity.getTitle() + "统计");
     sheet.addMergedRegion(new CellRangeAddress(currentRowIndex, currentRowIndex, 0, columnNames.size() - 1));
     OfficeUtil.addRow(sheet, ImmutableList.of("", "", "", "", "导出时间", DateUtil.format(new Date(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2), "制表人", userName));
     OfficeUtil.addRow(sheet, ImmutableList.of("活动开始时间", startTimeStr, "活动结束时间", endTimeStr, "浏览量", browseNumStr));
     OfficeUtil.addRow(sheet, ImmutableList.of("收藏量", favoriteNumStr, "分享量", shareNumStr, "活动报名人数", signUpCountStr, "活动签到人数", signInNumStr));

     List<List<Object>> values = new ArrayList<>();

     if (activityOrders != null) {
     for (ActivityOrder activityOrder : activityOrders) {
     String orderNo = (activityOrder.getOrderNo() == null) ? "" : activityOrder.getOrderNo();
     String signUpTime = (activityOrder.getSignUpTime() == null) ? "" : DateUtil.format(activityOrder.getSignUpTime(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2);
     String signInTime = (activityOrder.getSignInTime() == null) ? "" : DateUtil.format(activityOrder.getSignInTime(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND2);
     String joinUserName = (activityOrder.getJoinUserName() == null) ? "" : activityOrder.getJoinUserName();
     joinUserMobile = (activityOrder.getJoinUserMobile() == null) ? "" : activityOrder.getJoinUserMobile();
     String joinStatusStr = "";
     if (activityOrder.getJoinStatus() != null) {
     switch (activityOrder.getJoinStatus()) {
     case 1:
     joinStatusStr = "待参加";
     break;
     case 2:
     joinStatusStr = "已参加";
     break;
     }
     }
     String signInStatusStr = "";
     if (null != activityOrder.getSignInStatus()) {
     switch (activityOrder.getSignInStatus()) {
     case 1:
     signInStatusStr = "未签到";
     break;
     case 2:
     signInStatusStr = "已签到";
     break;
     case 3:
     signInStatusStr = "已补签";
     break;
     }
     }
     List<Object> tempList = ImmutableList.of(count, orderNo, signUpTime, joinUserName, joinUserMobile, joinStatusStr, signInTime, signInStatusStr);
     count++;
     values.add(tempList);
     }
     }
     OfficeUtil.addSheetContent(wb, activity.getTitle() + "统计", columnWidths, columnNames, values);
     String name = activity.getTitle() + "统计-" + DateUtil.format(new Date(), DateUtil.DATE_FORMAT_YEAR_MONTH_DAY2);
     CommonUtil.downLoadExcel(response, name, wb);
     */
}
