package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.MerchantConfig;
import com.example.api.entity.bo.MerchantConfigBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MerchantConfigMapper extends DefaultMapper {
    int deleteByPrimaryKey(Long configId);

    int insert(MerchantConfig record);

    int insertSelective(MerchantConfig record);

    MerchantConfig selectByPrimaryKey(Long configId);

    int updateByPrimaryKeySelective(MerchantConfig record);

    int updateByPrimaryKey(MerchantConfig record);

  MerchantConfigBo selectMerchantConfigBoByMerchantAndAppId(
      @Param("merchantId") String merchantId, @Param("appId")String appId,
      @Param("orderType")String orderType
  );
}
