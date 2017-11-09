/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: OracleClobTypeHandler.java
 * Author:   Tommy Xu
 * Date:     Nov 10, 2014 10:13:39 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.utils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.sql.CLOB;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class OracleClobTypeHandler implements TypeHandler<Object> {

    public Object valueOf(String param) {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
     */
    @Override
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        CLOB clob = (CLOB) arg0.getClob(arg1);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
     */
    @Override
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
     */
    @Override
    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object,
     *      org.apache.ibatis.type.JdbcType)
     */
    @Override
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
        CLOB clob = CLOB.getEmptyCLOB();
        clob.setString(1, (String) arg2);
        arg0.setClob(arg1, clob);
    }
}
