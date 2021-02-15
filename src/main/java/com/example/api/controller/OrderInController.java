package com.example.api.controller;

import com.example.api.common.constant.OrderTypeEnum;
import com.example.api.common.response.ResponseCode;
import com.example.api.common.response.ResponseModel;
import com.example.api.common.tools.Conv;
import com.example.api.common.tools.HttpParameterParser;
import com.example.api.common.tools.StringUtils;
import com.example.api.entity.bo.OrderIncomeBo;
import com.example.api.entity.bo.PayUnifiedorderDto;
import com.example.api.service.impl.unifiedorder.PayIncomeFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 入
 */
@RestController("/order/income")
@Api("入款下单相关接口")
@RequestMapping("/order/income")
public class OrderInController {

  /**
   *
   */
  @PostMapping("/unifiedorder.json")
  @ApiOperation(value = "下单", httpMethod = "POST", response = PayUnifiedorderDto.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "appId", value = "应用ID",
          required = true, paramType = "form", dataType = "string", defaultValue = "1"),
      @ApiImplicitParam(name = "merchantId", value = "商户ID",
          required = true, paramType = "form", dataType = "string", defaultValue = "700001"),
      @ApiImplicitParam(name = "outTradeNo", value = "商户订单号",
          required = true, paramType = "form", dataType = "string", defaultValue = "7"),
      @ApiImplicitParam(name = "merchantUserId", value = "商户用户ID",
          required = true, paramType = "form", dataType = "string", defaultValue = "7"),
      @ApiImplicitParam(name = "nonceStr", value = "随机字符串为4位字符串",
          required = true, paramType = "form", dataType = "string", defaultValue = "1234"),
      @ApiImplicitParam(name = "notifyUrl", value = "回调地址",
          required = true, paramType = "form", dataType = "string", defaultValue = "http://www.baidu.com"),
      @ApiImplicitParam(name = "orderAmt", value = "订单金额",
          required = true, paramType = "form", dataType = "string", defaultValue = "100"),
      @ApiImplicitParam(name = "payType", value = "支付方式",
          required = true, paramType = "form", dataType = "string", defaultValue = "1"),
      @ApiImplicitParam(name = "remark", value = "备注信息",
      required = false, paramType = "form", dataType = "string", defaultValue = "备注"),
      @ApiImplicitParam(name = "sign", value = "签名",
          required = true, paramType = "form", dataType = "string", defaultValue = "sign"),
  })
  public ResponseModel unifiedorder(HttpServletRequest request) {
    HttpParameterParser httpParameterParser = HttpParameterParser.newInstance(request);

    String appId = httpParameterParser.getString("appId");
    if (StringUtils.isNullOrEmpty(appId)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "应用ID为空");
    }

    String merchantId = httpParameterParser.getString("merchantId");
    if (StringUtils.isNullOrEmpty(merchantId)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "商户ID为空");
    }

    String outTradeNo = httpParameterParser.getString("outTradeNo");
    if (StringUtils.isNullOrEmpty(outTradeNo)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "商户订单号为空");
    }

    String merchantUserId = httpParameterParser.getString("merchantUserId");
    if (StringUtils.isNullOrEmpty(outTradeNo)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "商户用户ID为空");
    }

    String nonceStr = httpParameterParser.getString("nonceStr");
    if (StringUtils.isNullOrEmpty(nonceStr)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "随机字符串为空");
    }
    if (!StringUtils.isNullOrEmpty(nonceStr) && nonceStr.length() != 4) {
      return ResponseModel.getModel(ResponseCode.FAIL, "随机字符串为4位字符串");
    }

    String notifyUrl = httpParameterParser.getString("notifyUrl");
    if (StringUtils.isNullOrEmpty(notifyUrl)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "回调地址 不能为空");
    }

    String orderAmt = httpParameterParser.getString("orderAmt");
    if (StringUtils.isNullOrEmpty(orderAmt)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "下单金额为空");
    }

    BigDecimal amount = Conv.NDec(orderAmt);
    if (amount.compareTo(BigDecimal.ONE) == -1) {
      return ResponseModel.getModel(ResponseCode.FAIL, "交易币种数量需要大于1");
    }

    String payType = httpParameterParser.getString("payType");
    if (StringUtils.isNullOrEmpty(payType)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "支付方式为空");
    }

    OrderTypeEnum orderTypeEnum = OrderTypeEnum.getEnumByOrderType(payType);
    if (orderTypeEnum == null) {
      return ResponseModel.getModel(ResponseCode.FAIL, "暂不支持改支付方式");
    }

    String remark = httpParameterParser.getString("remark");

    String sign = httpParameterParser.getString("sign");
    if (StringUtils.isNullOrEmpty(sign)) {
      return ResponseModel.getModel(ResponseCode.FAIL, "sign为空");
    }

    OrderIncomeBo orderIncomeBo = new OrderIncomeBo(appId,merchantId,outTradeNo,merchantUserId,nonceStr,
        notifyUrl,orderAmt,payType, remark, sign);

    PayUnifiedorderDto payUnifiedorderDto =
        PayIncomeFactory.getPayEngineService(orderTypeEnum).unifiedorder(orderIncomeBo);

    if (null == payUnifiedorderDto) {
      return ResponseModel.getModel(ResponseCode.FAIL, "下单失败");
    }

    return ResponseModel.getModel(ResponseCode.SUCCESS, "下单成功", payUnifiedorderDto);
  }
}
