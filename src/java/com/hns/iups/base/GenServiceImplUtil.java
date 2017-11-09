package com.hns.iups.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.hns.iusp.admin.bean.SchoolTermBean;
import com.hns.iusp.admin.dao.SchoolTermDao;

import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;


public class GenServiceImplUtil {


	public GenServiceImplUtil(String tableName) {
		//parseDao(tableName);
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 */
	private  String parseDao(String tableName) {
		String packagePath = HelpUtils.getpackagePath(tableName, "serviceImpl");
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
		sb.append("  @Service\n");
		sb.append("  public class " + HelpUtils.getServiceImplClassName(tableName) + " implements "+HelpUtils.getServiceClassName(tableName)+" {\r\n\r\n");
		
		sb.append("      @Autowired\n");
		sb.append("      private "+HelpUtils.getDaoClassName(tableName)+" dao;\r\n\n");
		
		sb.append("      @Override\n");
		sb.append("      QueryResult<");sb.append(poName);sb.append("> query");sb.append(poName);sb.append("ForPage(");sb.append(poName);sb.append(" bean,Integer pageNumber, Integer pageSize);\r\n");
		sb.append("               	QueryParam<"+HelpUtils.getPoClassName(tableName)+"> param = new QueryParam<"+HelpUtils.getPoClassName(tableName)+">(); \n");
		sb.append("               	param.setQueryParam(bean);\n");
		sb.append("               	param.setPageNumber(pageNumber);\n");
		sb.append("               	param.setPageSize((null == pageNumber || pageNumber == 0) ? 10 : pageSize);\n");
        sb.append("                 if (count == 0) { queryResult.setPageCount(0);  }\n");
		sb.append("                 return dao.query"+HelpUtils.getPoClassName(tableName)+"ForPage(param);\n");
		sb.append("       }\r\n");
		
		sb.append("      @Override\n");
		sb.append("      int add");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean){\r\n");
		sb.append("           return this.getSqlSession().insert(\""+HelpUtils.getPoClassName(tableName)+".add"+HelpUtils.getPoClassName(tableName)+"\""+", bean);\n");
		sb.append("      }\r\n");
		
		sb.append("      @Override\n");
		sb.append("      int update");sb.append(poName);sb.append("(");sb.append(poName);sb.append(" bean){\r\n");
		sb.append("            return dao.update"+HelpUtils.getPoClassName(tableName)+"(bean);\n");
		sb.append("      }\r\n");

		sb.append("      @Override\n");
		sb.append("      int delete");sb.append(poName);sb.append("("+HelpUtils.getPoClassName(tableName)+" bean){ \r\n");
		sb.append("            return dao.delete"+HelpUtils.getPoClassName(tableName)+"(bean);\n");
		sb.append("      }\r\n");
		
		System.out.println(sb.toString());
		return sb.toString();

	}

	public static void main(String[] args) {

		String tableName = "eai_product";
		GenServiceImplUtil dao = new GenServiceImplUtil(tableName);
		dao.parseDao(tableName);
	}


}