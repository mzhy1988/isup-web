package com.hns.iups.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hns.iusp.admin.bean.SchoolTermBean;
import com.hns.iusp.admin.dao.SchoolTermDao;

import com.hns.iusp.utils.QueryResult;


public class GenDaoImplUtil {


	public GenDaoImplUtil(String tableName) {
		//parseDao(tableName);
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
		sb.append("package " + packagePath + ";\r\n\r\n");
		sb.append("  import import java.util.*;\r\n");
		sb.append("  import "+pk+"\r\n");
		sb.append("  import org.springframework.stereotype.Component;\n");
		sb.append("  import com.hns.iusp.base.BaseDao;\n");
		sb.append("  import "+HelpUtils.getpackagePath(tableName, "dao")+"."+HelpUtils.getDaoClassName(tableName)+";\n");
		sb.append("  import com.hns.iusp.utils.QueryResult"+";\r\n");
		sb.append("  import com.hns.iusp.utils.QueryParam"+";\r\n\n");
		sb.append("  @Component\n");
		sb.append("  public class " + HelpUtils.getDaoImplClassName(tableName) + " extends BaseDao implements "+HelpUtils.getDaoClassName(tableName)+" {\r\n\r\n");
		
		sb.append("      @Override\n");
		sb.append("      QueryResult<");sb.append(poName);sb.append("> query");sb.append(poName);sb.append("ForPage(QueryParam<");sb.append(poName);sb.append("> param){\r\n");
		sb.append("               	"+HelpUtils.getPoClassName(tableName)+" queryParamObj = queryParam.getQueryParam(); \n");
		sb.append("               	Map<String, Object> paramMap = new HashMap<String, Object>();");
		sb.append("               	Integer count = this.getSqlSession().selectOne(\""+HelpUtils.getPoClassName(tableName)+"findCount\", paramMap);\n");
		sb.append("               	QueryResult<"+HelpUtils.getPoClassName(tableName)+"> queryResult = new QueryResult<"+HelpUtils.getPoClassName(tableName)+">(count, queryParam.getPageSize(), queryParam.getPageNumber());\n");
        sb.append("                 if (count == 0) { queryResult.setPageCount(0);  }\n");
		sb.append("                 List<"+HelpUtils.getPoClassName(tableName)+"> list = null;\n");
		sb.append("                 if (count > 0) { \n");
		sb.append("                      paramMap.put(\"startIndex\", queryResult.getIndexNumber());\n");
		sb.append("                      paramMap.put(\"endIndex\", queryResult.getPageNumber() * queryResult.getPageSize());\n");
		sb.append("                      list = this.getSqlSession().selectList(\""+HelpUtils.getPoClassName(tableName)+".query"+HelpUtils.getPoClassName(tableName)+"ForPage\", paramMap)\n");;
		sb.append("                      queryResult.setDatas(list);\n");
		sb.append("                 }\n");
		sb.append("                 return queryResult;\n");
		sb.append("       }\r\n");
		
		sb.append("      @Override\n");
		sb.append("      int add");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean){\r\n");
		sb.append("           return this.getSqlSession().insert(\""+HelpUtils.getPoClassName(tableName)+".add"+HelpUtils.getPoClassName(tableName)+"\""+", bean);\n");
		sb.append("      }\r\n");
		
		sb.append("      @Override\n");
		sb.append("      int update");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean){\r\n");
		sb.append("           return this.getSqlSession().update(\""+HelpUtils.getPoClassName(tableName)+".update"+HelpUtils.getPoClassName(tableName)+"\""+", bean);\n");
		sb.append("      }\r\n");

		sb.append("      @Override\n");
		sb.append("      int delete");sb.append(poName);sb.append("("+HelpUtils.getPoClassName(tableName)+" bean){ \r\n");
		sb.append("           return this.getSqlSession().delete(\""+HelpUtils.getPoClassName(tableName)+".delete"+HelpUtils.getPoClassName(tableName)+"\""+", bean);\n");
		sb.append("      }\r\n");
		
		System.out.println(sb.toString());
		return sb.toString();

	}

	public static void main(String[] args) {

		String tableName = "eai_product";
		GenDaoImplUtil dao = new GenDaoImplUtil(tableName);
		dao.parseDao(tableName);
	}


}