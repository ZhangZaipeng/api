package com.example.api.controller;

import com.example.api.common.response.ResponseCode;
import com.example.api.common.response.ResponseModel;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.HttpParameterParser;
import com.example.api.common.tools.StringUtils;
import com.example.api.entity.bo.PayUnifiedorderDto;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller("/page")
@RequestMapping("/page")
public class OrderIncomePageController {

  /**
   * @return
   */
  @RequestMapping("/1.htm")
  public ModelAndView recharge() {
    return new ModelAndView("alipayscantobank");
  }
}
