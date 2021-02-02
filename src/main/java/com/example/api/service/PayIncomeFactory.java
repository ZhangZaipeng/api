package com.example.api.service;

import com.example.api.common.constant.OrderTypeEnum;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PayIncomeFactory {

  private static Map<OrderTypeEnum, IncomeUnifiedorder> payIncomeServiceMap = new ConcurrentHashMap<>(8);

  public static IncomeUnifiedorder getPayEngineService(OrderTypeEnum orderTypeEnum) {
    return payIncomeServiceMap.get(orderTypeEnum);
  }

  public static void registerPayService(OrderTypeEnum orderTypeEnum, IncomeUnifiedorder service) {
    payIncomeServiceMap.put(orderTypeEnum, service);
  }

}
