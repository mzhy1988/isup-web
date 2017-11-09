package com.hns.iups.base;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

public class GenEntityUtil {

	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
	private String splitChar = "_";

	public GenEntityUtil(String packagePath, String tableName) throws SQLException {
		Connection conn = DBUtils.getConnection(); // 得到数据库连接
		PreparedStatement pstmt = null;
		packagePath =HelpUtils.getpackagePath(tableName, "po");
		String strsql = "select * from " + tableName;
		try {
			pstmt = conn.prepareStatement(strsql);
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(strsql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int size = rsmd.getColumnCount(); // 共有多少列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				colnames[i] = this.getCamelStr(rsmd.getColumnName(i + 1));
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image")
						|| colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			try {
				String content = parse(colnames, colTypes, colSizes, packagePath, tableName);
				String path = System.getProperty("user.dir") + "/src/java/" + packagePath.replaceAll("\\.", "/");
				File file = new File(path);
				if(!file.exists()){
					file.mkdirs();
				}
				String resPath = path+"/"+initcap(splitEnd(tableName)) + ".java";
				System.out.println("resPath=" + resPath);
				FileUtils.writeStringToFile(new File(resPath), content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.release(conn, pstmt, null);
		}
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private String parse(String[] colNames, String[] colTypes, int[] colSizes, String packagePath, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ";\r\n\r\n");
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n\r\n\r\n");
		}
		sb.append("import java.sql.Timestamp;\r\n\r\n\r\n");
		sb.append("public class " + initcap(splitEnd(tableName)) + " {\r\n\r\n");
		processAllAttrs(sb);
		sb.append("\r\n");
		processAllMethod(sb);
		sb.append("}\r\n");
		System.out.println(sb.toString());
		return sb.toString();

	}




	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			String colname = colnames[i].toLowerCase();

			sb.append("\tpublic void set" + initcap(colnames[i]) + "("
					+ sqlType2JavaType(colTypes[i]) + " " + colname
					+ "){\r\n");
			sb.append("\t\tthis." + colname + "=" + colname + ";\r\n");
			sb.append("\t}\r\n\r\n");

			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get"
					+ initcap(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + colname + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @return
	 */
	private void processAllAttrs(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			String colname = colnames[i].toLowerCase();
			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colname + ";\r\n");
		}
	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		str = str.toLowerCase();//改成小写
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0]-32);
		}
		return this.getCamelStr(new String(ch));
	}

	//例：user_name --> userName
	private String getCamelStr(String s){
		while(s.indexOf("_")>0){
			int index = s.indexOf("_");
			//System.out.println(s.substring(index+1, index+2).toUpperCase());
			s = s.substring(0, index) + s.substring(index+1, index+2).toUpperCase() + s.substring(index+2);
		}
		return s;
	}

	/***
	 * 根据分隔符取头
	 * @param tableName
	 * @return
	 */
	private String splitHead(String tableName){
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
	private String splitEnd(String tableName){
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

	private String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "bool";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal")
				|| sqlType.equalsIgnoreCase("number")
				|| sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar")
				|| sqlType.equalsIgnoreCase("varchar2")
				|| sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")|| sqlType.equalsIgnoreCase("date")) {
			return "Timestamp";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "Clob";
		}
		return null;
	}

	public static void main(String[] args) {
		String packagePath = "com.test.model";
		String tableName = "eai_product";
		try {
			new GenEntityUtil(packagePath,tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}