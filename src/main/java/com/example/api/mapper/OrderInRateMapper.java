package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.OrderInRate;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInRateMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInRate record);

    int insertSelective(OrderInRate record);

    OrderInRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInRate record);

    int updateByPrimaryKey(OrderInRate record);
}
