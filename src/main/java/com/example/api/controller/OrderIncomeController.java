package com.example.api.controller;

import com.example.api.common.response.ResponseCode;
import com.example.api.common.response.ResponseModel;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.HttpParameterParser;
import com.example.api.common.tools.StringUtils;
import com.example.api.entity.bo.PayUnifiedorderDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order/income")
public class OrderIncomeController {

  /**
   * @param request
   * @return
   */
  @PostMapping("/unifiedorder.json")
  @ApiOperation(value = "下单", httpMethod = "GET", response = PayUnifiedorderDto.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "appId", value = "应用ID",
          required = false, paramType = "form", dataType = "Int", defaultValue = "7"),
  })
  public ResponseModel recharge(HttpServletRequest request) {
    HttpParameterParser httpParameterParser = HttpParameterParser.newInstance(request);

    String appId = httpParameterParser.getString("appId");
    if (StringUtils.isNullOrEmpty(appId)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"应用ID为空");
    }

    String merchantId = httpParameterParser.getString("merchantId");
    if (StringUtils.isNullOrEmpty(merchantId)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"商户ID为空");
    }

    String outTradeNo = httpParameterParser.getString("outTradeNo");
    if (StringUtils.isNullOrEmpty(outTradeNo)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"商户订单号为空");
    }

    String nonceStr = httpParameterParser.getString("nonceStr");
    if (StringUtils.isNullOrEmpty(nonceStr)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"随机字符串为空");
    }
    if (!StringUtils.isNullOrEmpty(nonceStr) && nonceStr.length() != 4) {
      return ResponseModel.getModel(ResponseCode.FAIL, "随机字符串为4位字符串");
    }

    String notifyUrl = httpParameterParser.getString("notifyUrl");
    if (StringUtils.isNullOrEmpty(notifyUrl)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"回调地址 不能为空");
    }

    String orderAmt = httpParameterParser.getString("orderAmt");
    if (StringUtils.isNullOrEmpty(orderAmt)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"下单金额为空");
    }

    BigDecimal amount = Conv.NDec(orderAmt);
    if (amount.compareTo(BigDecimal.ZERO) == -1) {
      return ResponseModel.getModel(ResponseCode.FAIL,"交易币种数量需要大于0");
    }

    String payType = httpParameterParser.getString("payType");
    if (StringUtils.isNullOrEmpty(payType)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"支付方式为空");
    }

    String remark = httpParameterParser.getString("remark");

    String sign = httpParameterParser.getString("sign");
    if (StringUtils.isNullOrEmpty(sign)) {
      return ResponseModel.getModel(ResponseCode.FAIL,"sign为空");
    }

    PayUnifiedorderDto payUnifiedorderDto = null;

    if (null == payUnifiedorderDto) {
      return ResponseModel.getModel(ResponseCode.FAIL,"下单失败");
    }

    return ResponseModel.getModel(ResponseCode.SUCCESS,"下单成功", payUnifiedorderDto);
  }
}
