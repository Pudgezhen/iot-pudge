package com.pudge.cn.iot.common.utils.excel.operation;

import com.alibaba.fastjson.util.TypeUtils;
import com.pudge.cn.iot.common.utils.excel.annotation.EnableExport;
import com.pudge.cn.iot.common.utils.excel.annotation.EnableExportField;
import com.pudge.cn.iot.common.utils.excel.annotation.ImportIndex;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author mu_zhen
 * @description Excel 操作工具类
 * @Date 2023/2/20 10:39
 */
public class ExcelOperation {

    /**
     * 将Excel转换为对象集合
     *
     * @param excel Excel 文件
     * @param clazz pojo类型
     * @return
     */
    public static List<Object> parseExcelToList(File excel, Class clazz) {
        List<Object> res = new ArrayList<>();
        // 创建输入流，读取Excel
        InputStream is = null;
        Sheet sheet = null;
        try {
            is = new FileInputStream(excel.getAbsolutePath());
            if (is != null) {
                Workbook workbook = WorkbookFactory.create(is);
                //默认只获取第一个工作表
                sheet = workbook.getSheetAt(0);
                if (sheet != null) {
                    //前两行是标题
                    int i = 2;
                    String values[];
                    Row row = sheet.getRow(i);
                    while (row != null) {
                        //获取单元格数目
                        int cellNum = row.getPhysicalNumberOfCells();
                        values = new String[cellNum];
                        for (int j = 0; j <= cellNum; j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                //设置单元格内容类型
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                //获取单元格值
                                String value = cell.getStringCellValue() == null ? null : cell.getStringCellValue();
                                values[j] = value;
                            }
                        }
                        Field[] fields = clazz.getDeclaredFields();
                        Object obj = clazz.newInstance();
                        for (Field f : fields) {
                            if (f.isAnnotationPresent(ImportIndex.class)) {
                                ImportIndex annotation = f.getDeclaredAnnotation(ImportIndex.class);
                                int index = annotation.index();
                                f.setAccessible(true);
                                //此处使用了阿里巴巴的fastjson包里面的一个类型转换工具类
                                Object val = TypeUtils.cast(values[index], f.getType(), null);
                                f.set(obj, val);
                            }
                        }
                        res.add(obj);
                        i++;
                        row = sheet.getRow(i);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     *
     * @param clazz pojo类
     * @param dataList 需要导出的数据
     * @return  返回一个Excel
     * @throws IllegalAccessException 字段取值异常
     */
    public static HSSFWorkbook exportExcel(Class clazz, List<Object> dataList) throws IllegalAccessException {
        return exportExcel(clazz,dataList,null);
    }


        /**
         *
         * @param clazz pojo类
         * @param dataList 需要导出的数据
         * @param selectListMap  下拉框
         * @return  返回一个Excel
         * @throws IllegalAccessException 字段取值异常
         */
    public static HSSFWorkbook exportExcel(Class clazz, List<Object> dataList, Map<Integer, String[]> selectListMap) throws IllegalAccessException {
        //1. 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 建立一张sheet表
        HSSFSheet hssfSheet = workbook.createSheet();
        //3. 设置行高和列宽
        hssfSheet.setDefaultRowHeight((short) (200 * 200));
        //3.1 检查当前pojo是否允许导出
        if (clazz.isAnnotationPresent(EnableExport.class)) {
            EnableExport export = (EnableExport) clazz.getDeclaredAnnotation(EnableExport.class);
            //3.2 获取所有标题名称
            List<String> colNames = new ArrayList<>();
            //3.3 所有允许导出的字段
            List<Field> fieldList = new ArrayList<>();
            for (Field field : clazz.getDeclaredFields()) {
                EnableExportField enableExportField = field.getDeclaredAnnotation(EnableExportField.class);
                colNames.add(enableExportField.colName());
                fieldList.add(field);
            }
            //3.4
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                hssfSheet.setColumnWidth(i, field.getDeclaredAnnotation(EnableExportField.class).colWidth());
            }
            //4. 绘制标题和表头
            HSSFRow hssfRow = null;
            HSSFCell hssfCell = null;

            String fileName = export.fileName();
            //4.1 绘制标题
            createTitle(workbook, hssfRow, hssfCell, hssfSheet, colNames.size() - 1, fileName);
            //4.2 创建标题行（表头）
            createHeadRow(workbook, hssfRow, hssfCell, hssfSheet, colNames);

            // 5.写入数据到Excel
            HSSFCellStyle cellStyle = getBasicCellStyle(workbook);
            // 5.1 插入内容
            int i = 0;
            for (Object obj : dataList) {
                hssfRow = hssfSheet.createRow(i + 2);
                for (int j = 0; j < fieldList.size(); j++) {
                    Field field = fieldList.get(j);
                    field.setAccessible(true);
                    Object value = field.get(obj);
                    hssfCell = hssfRow.createCell(j);
                    hssfCell.setCellStyle(cellStyle);
                    hssfCell.setCellValue(String.valueOf(value));
                }
                i++;
            }

            createDataValidation(hssfSheet,selectListMap);

        }


        return workbook;
    }

    /**
     *
     * @param clazz pojo类
     * @param dataMap 需要导出的数据--多 sheet
     * @param selectListMap  下拉框
     * @return  返回一个Excel
     * @throws IllegalAccessException 字段取值异常
     */
    public static HSSFWorkbook exportExcelSheets(Class clazz, Map<String,List<Object>> dataMap, Map<Integer, String[]> selectListMap) throws IllegalAccessException {
        //1. 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 建立一张sheet表
        Set<String> keys = dataMap.keySet();
        for (String key:keys) {
            HSSFSheet hssfSheet = workbook.createSheet(key);
            List<Object> dataList = dataMap.get(key);
            //3. 设置行高和列宽
            hssfSheet.setDefaultRowHeight((short) (200 * 200));
            //3.1 检查当前pojo是否允许导出
            if (clazz.isAnnotationPresent(EnableExport.class)) {
                EnableExport export = (EnableExport) clazz.getDeclaredAnnotation(EnableExport.class);
                //3.2 获取所有标题名称
                List<String> colNames = new ArrayList<>();
                //3.3 所有允许导出的字段
                List<Field> fieldList = new ArrayList<>();
                for (Field field : clazz.getDeclaredFields()) {
                    EnableExportField enableExportField = field.getDeclaredAnnotation(EnableExportField.class);
                    colNames.add(enableExportField.colName());
                    fieldList.add(field);
                }
                //3.4
                for (int i = 0; i < fieldList.size(); i++) {
                    Field field = fieldList.get(i);
                    hssfSheet.setColumnWidth(i, field.getDeclaredAnnotation(EnableExportField.class).colWidth());
                }
                //4. 绘制标题和表头
                HSSFRow hssfRow = null;
                HSSFCell hssfCell = null;

                String fileName = export.fileName();
                //4.1 绘制标题
                createTitle(workbook, hssfRow, hssfCell, hssfSheet, colNames.size() - 1, fileName);
                //4.2 创建标题行（表头）
                createHeadRow(workbook, hssfRow, hssfCell, hssfSheet, colNames);

                // 5.写入数据到Excel
                HSSFCellStyle cellStyle = getBasicCellStyle(workbook);
                // 5.1 插入内容
                int i = 0;
                for (Object obj : dataList) {
                    hssfRow = hssfSheet.createRow(i + 2);
                    for (int j = 0; j < fieldList.size(); j++) {
                        Field field = fieldList.get(j);
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        hssfCell = hssfRow.createCell(j);
                        hssfCell.setCellStyle(cellStyle);
                        hssfCell.setCellValue(String.valueOf(value));
                    }
                    i++;
                }

                createDataValidation(hssfSheet, selectListMap);

            }
        }

        return workbook;
    }

    /**
     * 获取一个基本的带边框的单元格
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle getBasicCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();

        hssfcellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);

        hssfcellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);

        hssfcellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);

        hssfcellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        hssfcellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        hssfcellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }

    /**
     * 获取带有背景色的标题单元格
     *
     * @param workbook
     * @return
     */
    private static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = getBasicCellStyle(workbook);

        hssfcellstyle.setFillForegroundColor((short) HSSFColor.CORNFLOWER_BLUE.index); // 设置背景色

        hssfcellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        return hssfcellstyle;
    }

    /**
     * 创建一个跨列的标题行
     *
     * @param workbook
     * @param hssfRow
     * @param hssfcell
     * @param hssfsheet
     * @param allColNum
     * @param title
     */
    private static void createTitle(HSSFWorkbook workbook, HSSFRow hssfRow, HSSFCell hssfcell, HSSFSheet hssfsheet, int allColNum, String title) {
        //在sheet里增加合并单元格
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, allColNum);
        hssfsheet.addMergedRegion(cra);
        // 使用RegionUtil类为合并后的单元格添加边框

        RegionUtil.setBorderBottom(1, cra, hssfsheet, workbook); // 下边框
        RegionUtil.setBorderLeft(1, cra, hssfsheet, workbook); // 左边框
        RegionUtil.setBorderRight(1, cra, hssfsheet, workbook); // 有边框
        RegionUtil.setBorderTop(1, cra, hssfsheet, workbook); // 上边框
        //设置表头
        hssfRow = hssfsheet.getRow(0);
        hssfcell = hssfRow.getCell(0);
        hssfcell.setCellStyle(getTitleCellStyle(workbook));
        hssfcell.setCellType(HSSFCell.CELL_TYPE_STRING);
        hssfcell.setCellValue(title);
    }

    /**
     * 设置表头标题栏以及表格高度
     *
     * @param workbook
     * @param hssfRow
     * @param hssfcell
     * @param hssfsheet
     * @param colNames
     */
    private static void createHeadRow(HSSFWorkbook workbook, HSSFRow hssfRow, HSSFCell hssfcell, HSSFSheet hssfsheet, List<String> colNames) {
        //插入标题行
        hssfRow = hssfsheet.createRow(1);
        for (int i = 0; i < colNames.size(); i++) {
            hssfcell = hssfRow.createCell(i);
            hssfcell.setCellStyle(getTitleCellStyle(workbook));
            hssfcell.setCellType(HSSFCell.CELL_TYPE_STRING);
            hssfcell.setCellValue(colNames.get(i));
        }
    }

    /**
     * excel添加下拉数据校验
     *
     * @param sheet 哪个 sheet 页添加校验
     * @return
     */
    public static void createDataValidation(Sheet sheet, Map<Integer, String[]> selectListMap) {
        if (selectListMap != null) {
            selectListMap.forEach(
                    // 第几列校验（0开始）key 数据源数组value
                    (key, value) -> {
                        if (value.length > 0) {
                            CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(2, 65535, key, key);
                            DataValidationHelper helper =
                                    sheet.getDataValidationHelper();
                            DataValidationConstraint constraint =
                                    helper.createExplicitListConstraint(value);
                            DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
                            //处理Excel兼容性问题
                            if (dataValidation instanceof XSSFDataValidation) {

                                dataValidation.setSuppressDropDownArrow(true);

                                dataValidation.setShowErrorBox(true);
                            } else {

                                dataValidation.setSuppressDropDownArrow(false);
                            }

                            dataValidation.setEmptyCellAllowed(true);

                            dataValidation.setShowPromptBox(true);

                            dataValidation.createPromptBox("提示", "只能选择下拉框里面的数据");
                            sheet.addValidationData(dataValidation);
                        }
                    }
            );
        }
    }

}
