package com.hns.iups.base;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;


public class GenSqlMapUtil {


	public GenSqlMapUtil(String tableName) {
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private  String parseDao(String tableName) {
		String packagePath = HelpUtils.getpackagePath(tableName, "daoImpl");
		StringBuffer sb = new StringBuffer();
		String poName = HelpUtils.getPoClassName(tableName);
		String pk = HelpUtils.getpackagePath(tableName, "po");
		pk = pk + "."+HelpUtils.getPoClassName(tableName);
		String STR = "//";
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\" >\n");
		sb.append("<mapper namespace=\""+HelpUtils.getPoClassName(tableName)+"\">");
		sb.append("<resultMap id=\""+HelpUtils.getPoClassName(tableName)+"Map\" type=\""+HelpUtils.getpackagePath(tableName, "po")+"."+HelpUtils.getPoClassName(tableName)+"\">\n");
		//结果集
		HashMap<String, String> map;
		try {
			map = HelpUtils.getProperty(tableName);
			for (Entry<String, String> entry : map.entrySet()) {
				entry.getKey();
				entry.getValue();
				sb.append("<result column=\""+entry.getValue()+"\" property=\""+entry.getKey()+"\" />\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sb.append("</resultMap>");

		System.out.println(sb.toString());
		return sb.toString();

	}

	public static void main(String[] args) {

		String tableName = "eai_product";
		GenSqlMapUtil dao = new GenSqlMapUtil(tableName);
		dao.parseDao(tableName);
	}


}