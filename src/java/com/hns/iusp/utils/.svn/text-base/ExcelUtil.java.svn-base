/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: ExcelUtil.java
 * Author:   12030717
 * Date:     2013-7-17 下午2:23:29
 * Description: Excel导入导出功能操作
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 〈Excel导入导出功能操作〉
 * 
 * @author 12030717
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ExcelUtil {

    private static DecimalFormat df = new DecimalFormat("#");

    /**
     * 初始化一个实例
     */
    private static ExcelUtil excelInstance;

    /**
     * 私有构造方法
     */
    private ExcelUtil() {
    }

    /**
     * 功能描述：获取一个实例
     * 
     * @param 参数说明
     * @return 返回值
     * @throw 异常描述
     * @see 需要参见的其它内容
     */
    public static ExcelUtil getExcelInstance() {
        if (null == excelInstance) {
            excelInstance = new ExcelUtil();
        }
        return excelInstance;
    }

    /**
     * 
     * 功能描述: <br>
     * 〈获取excel的第一个sheet〉
     * 
     * @param inputStream
     * @return
     * @throws IOException
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public HSSFSheet getFirstSheet(InputStream inputStream) {
        /**
         * 打开HSSFWorkbook
         */
        POIFSFileSystem fs;
        HSSFWorkbook wk;
        HSSFSheet sheet = null;
        try {
            fs = new POIFSFileSystem(inputStream);
            wk = new HSSFWorkbook(fs);
            sheet = wk.getSheetAt(0);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        /**
         * 得到Excel工作表
         */
        return sheet;
    }

    /**
     * 功能描述: <br>
     * 〈获取某一行的所有值〉
     * 
     * @param sheet
     * @param i
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<?> getRowValues(HSSFSheet sheet, int rowCount) {

        /**
         * 获取Excel工作表的行
         */
        HSSFRow row = sheet.getRow(rowCount);
        if (null == row) {
            return null;
        }
        List<String> rowValues = new ArrayList<String>();
        /**
         * 取得Excel工作表一行的有效单元格个数
         */
        int totalCells = row.getLastCellNum();

        for (int cellCount = 0; cellCount < totalCells; cellCount++) {
            /**
             * 获取Excel工作表指定行的单元格
             */
            HSSFCell cell = row.getCell(cellCount);
            /**
             * 根据单元格不同属性返回字符串数值
             */
            String value = getStringCellValue(cell);
            if (value == null) {
                value = "";
            }
            rowValues.add(value);
        }

        return null;
    }

    /**
     * 功能描述: <br>
     * 〈根据单元格不同属性返回字符串数值〉
     * 
     * @param cell
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getStringCellValue(HSSFCell cell) {
        if (cell == null) {
            return null;
        } else {
            /**
             * 如果是数值类型.数据format后返回 其他返回字符串值
             */
            switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC: {
                    double value = cell.getNumericCellValue();
                    return df.format(value);
                }
                default: {
                    return cell.toString();
                }
            }

        }
    }
}