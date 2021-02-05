package com.example.api.service.impl;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.Id;
import com.example.api.service.IdCreatorService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IdCreatorServiceImpl implements IdCreatorService {

  Logger logger = LoggerFactory.getLogger(IdCreatorServiceImpl.class);
  // yyyy-MM-dd HH
  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyMMddHH");

  @Override
  public String nextValue(OrderTypeEnum recordTypeEnum, long orderTime) {
    String time = SDF.format(new Date(orderTime));

    Id.newInstance(1);
    long id = Id.nextId();

    return recordTypeEnum.getOrderFlag() + time + id;
  }

  public static void main(String[] args) {
    while (true) {
      String time = SDF.format(new Date(System.currentTimeMillis()));
      System.out.println(time);
    }
  }


}
