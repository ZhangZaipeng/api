package com.example.api.controller;

import com.example.api.common.response.ResponseCode;
import com.example.api.common.response.ResponseModel;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.HttpParameterParser;
import com.example.api.common.tools.StringUtils;
import com.example.api.entity.bo.PayUnifiedorderDto;
import com.example.api.service.OrderIncomeService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/orderin")
public class OrderInPageController {

  @Autowired
  private OrderIncomeService orderIncomeService;

  /**
   * @return
   */
  @RequestMapping("/page.htm")
  public ModelAndView page(HttpServletRequest request) {
    HttpParameterParser httpParameterParser = HttpParameterParser.newInstance(request);

    String token = httpParameterParser.getString("token");
    if (StringUtils.isNullOrEmpty(token)) {
      return new ModelAndView("public/404");
    }

    OrderInVo orderInVo = orderIncomeService.selectOrderInByToken(token);

    if (orderInVo == null) {
      return new ModelAndView("public/404");
    }

    Map<String, Object> data = new HashMap<>();
    data.put("orderInVo", orderInVo);

    return new ModelAndView("order/alipayscantobank", data);
  }
}
