package com.hns.iups.base;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;


public class HelpUtils {

	public static String splitChar = "_";
	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		str = str.toLowerCase();//改成小写
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0]-32);
		}
		return HelpUtils.getCamelStr(new String(ch));
	}

	//例：user_name --> userName
	public static  String getCamelStr(String s){
		while(s.indexOf("_")>0){
			int index = s.indexOf("_");
			//System.out.println(s.substring(index+1, index+2).toUpperCase());
			s = s.substring(0, index) + s.substring(index+1, index+2).toUpperCase() + s.substring(index+2);
		}
		return s;
	}

	public static String  getpackagePath(String tableName,String type){
		String pk = tableName.split("_")[0];
		String packagePath = "";
		if("po".equals(type)){
			packagePath = "com.hns.iusp."+pk+".po";
		}
		if("dao".equals(type)){
			packagePath = "com.hns.iusp."+pk+".dao";
		}
		if("daoImpl".equals(type)){
			packagePath = "com.hns.iusp."+pk+".dao.impl";
		}
		if("service".equals(type)){
			packagePath = "com.hns.iusp."+pk+".service";
		}
		if("serviceImpl".equals(type)){
			packagePath = "com.hns.iusp."+pk+".service.impl";
		}
		return packagePath;
	}

	public static String getPoClassName(String tableName){
		return initcap(splitEnd(tableName));
	}
	public static String getDaoClassName(String tableName){
		return initcap(splitEnd(tableName))+"Dao";
	}
	public static String getDaoImplClassName(String tableName){
		return initcap(splitEnd(tableName))+"DaoImpl";
	}
	public static String getServiceClassName(String tableName){
		return initcap(splitEnd(tableName))+"Service";
	}
	public static String getServiceImplClassName(String tableName){
		return initcap(splitEnd(tableName))+"ServiceImpl";
	}
	/***
	 * 根据分隔符取头
	 * @param tableName
	 * @return
	 */
	private static String splitHead(String tableName){
		String result = "";
		if(tableName!=null){
			String[] str = tableName.split(splitChar);
			if(str!=null&&str.length>0){
				result = str[0];
			}
		}
		return result;
	}
	/**
	 * 根据分隔符取尾
	 * @param tableName
	 * @return
	 */
	private static String splitEnd(String tableName){
		String result = "";
		if(tableName!=null){
			String[] str = tableName.split(splitChar);
			if(tableName!=null){
				for(int i=0;i<str.length;i++){
					if(i>0){
						result = result + str[i];
					}
				}
			}
		}
		return result;
	}

	public static String getPoPropery(String property){
		return property.toLowerCase();
	}

	/**
	 * 获取BeanMap
	 * @param tableName
	 * @return
	 * @throws SQLException 
	 */
	public static HashMap<String, String> getProperty(String tableName) throws SQLException{

		Connection conn = DBUtils.getConnection(); // 得到数据库连接
		PreparedStatement pstmt = null;
		String strsql = "select * from " + tableName;
		HashMap<String, String> result = new HashMap<String, String>();
		try {
			pstmt = conn.prepareStatement(strsql);
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(strsql);
			ResultSetMetaData rsmd = rs.getMetaData();
			DatabaseMetaData dbMeta = conn.getMetaData(); 
			int size = rsmd.getColumnCount(); // 共有多少列
			String[] colnames = new String[size];
			int[] colSizes = new int[size];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				result.put(getPoPropery(getCamelStr(rsmd.getColumnName(i + 1))), getCamelStr(rsmd.getColumnName(i + 1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(conn, pstmt, null);
		}
		return result;
	}
}
