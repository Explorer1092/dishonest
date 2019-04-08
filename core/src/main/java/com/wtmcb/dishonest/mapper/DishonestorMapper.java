package com.wtmcb.dishonest.mapper;

import com.wtmcb.dishonest.entity.Dishonestor;

public interface DishonestorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dishonestor record);

    int insertSelective(Dishonestor record);

    Dishonestor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dishonestor record);

    int updateByPrimaryKey(Dishonestor record);
}