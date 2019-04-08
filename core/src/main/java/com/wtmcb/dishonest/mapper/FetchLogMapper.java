package com.wtmcb.dishonest.mapper;

import com.wtmcb.dishonest.entity.FetchLog;

public interface FetchLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FetchLog record);

    int insertSelective(FetchLog record);

    FetchLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FetchLog record);

    int updateByPrimaryKey(FetchLog record);
}