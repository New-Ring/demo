package com.mengma.customLog.dao;

import com.mengma.customLog.SystemLog;

public interface SystemLogMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemLog record);

    int insertSelective(SystemLog record);

    SystemLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemLog record);

    int updateByPrimaryKey(SystemLog record);
}
