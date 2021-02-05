package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.OrderIn;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInMapper extends DefaultMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderIn record);

    int insertSelective(OrderIn record);

    OrderIn selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderIn record);

    int updateByPrimaryKey(OrderIn record);
}
