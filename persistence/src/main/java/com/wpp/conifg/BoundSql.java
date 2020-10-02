package com.wpp.conifg;


import com.wpp.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by wpp25
 * @Classname BoundSql
 * @Description
 * @Date 2020/9/25 21:20
 */
public class BoundSql {

    private String sqlText;

    private List<ParameterMapping> parameterMappingList = new ArrayList<>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList) {
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

    public String getSqlText() {
        return sqlText;
    }

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public List<ParameterMapping> getParameterMappingList() {
        return parameterMappingList;
    }

    public void setParameterMappingList(List<ParameterMapping> parameterMappingList) {
        this.parameterMappingList = parameterMappingList;
    }
}
