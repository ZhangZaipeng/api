package com.example.api.mapper;

import com.example.api.config.mybatis.DefaultMapper;
import com.example.api.entity.OrderIn;
import com.example.api.entity.bo.MatchMemberBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderInMapper extends DefaultMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(OrderIn record);

    int insertSelective(OrderIn record);

    OrderIn selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderIn record);

    int updateByPrimaryKey(OrderIn record);

    OrderIn selectByOutOrderIdAndMerchantId(
        @Param(value = "merchantId") String merchantId,
        @Param(value = "outTradeNo") String outTradeNo);

  int updateOrderDistributionInfo(@Param(value = "orderId")String orderId,
      @Param(value = "ret")MatchMemberBo ret);
}
