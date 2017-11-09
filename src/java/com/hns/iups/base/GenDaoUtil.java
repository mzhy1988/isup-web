package com.hns.iups.base;


public class GenDaoUtil {


	public GenDaoUtil(String tableName) {
		//parseDao(tableName);
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private  String parseDao(String tableName) {
		String packagePath = HelpUtils.getpackagePath(tableName, "dao");
		StringBuffer sb = new StringBuffer();
		String poName = HelpUtils.getPoClassName(tableName);
		String pk = HelpUtils.getpackagePath(tableName, "po");
		pk = pk + "."+HelpUtils.getPoClassName(tableName);
		sb.append("package " + packagePath + ";\r\n\r\n");
		sb.append("  import import java.util.*;\r\n");
		sb.append("  import "+pk+"\r\n");
		sb.append("  import com.hns.iusp.utils.QueryResult"+";\r\n");
		sb.append("  import com.hns.iusp.utils.QueryParam"+";\r\n");
		sb.append("  public interface " + HelpUtils.getDaoClassName(tableName) + " {\r\n\r\n");
		sb.append("      QueryResult<");sb.append(poName);sb.append("> query");sb.append(poName);sb.append("ForPage(QueryParam<");sb.append(poName);sb.append("> param);\r\n");
		sb.append("      int add");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean);\r\n");
		sb.append("      int update");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean);\r\n");
		sb.append("      int delete");sb.append(poName);sb.append("("+poName+" bean); \r\n");
		sb.append("\r\n}");
		System.out.println(sb.toString());
		return sb.toString();

	}

	public static void main(String[] args) {

		String tableName = "eai_product";
		GenDaoUtil dao = new GenDaoUtil(tableName);
		dao.parseDao(tableName);
	}


}