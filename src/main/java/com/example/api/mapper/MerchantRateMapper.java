package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.MerchantRate;

public interface MerchantRateMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRate record);

    int insertSelective(MerchantRate record);

    MerchantRate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRate record);

    int updateByPrimaryKey(MerchantRate record);
}
