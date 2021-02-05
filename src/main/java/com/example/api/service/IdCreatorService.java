package com.example.api.service;

import com.example.api.common.constant.OrderTypeEnum;

/**
 * 订单生成器
 */
public interface IdCreatorService {

  String nextValue(OrderTypeEnum recordTypeEnum, long orderTime);

}
