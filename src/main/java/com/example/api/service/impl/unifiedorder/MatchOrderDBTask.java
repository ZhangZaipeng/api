package com.example.api.service.impl.unifiedorder;

import com.example.api.common.tools.YvanUtil;
import com.example.api.entity.bo.MatchMemberBo;
import com.example.api.service.OrderIncomeService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取最佳承兑商线程
 */
public class MatchOrderDBTask implements Runnable{

  private static final Logger logger = LoggerFactory.getLogger(MatchOrderDBTask.class);

  private String orderId;

  private String payType;

  private BigDecimal unitAmt;

  private IncomeUnifiedorder incomeUnifiedorder;

  public MatchOrderDBTask(String orderId, String payType, BigDecimal unitAmt, IncomeUnifiedorder incomeUnifiedorder) {
    this.payType = payType;
    this.orderId = orderId;
    this.unitAmt = unitAmt;
    this.incomeUnifiedorder = incomeUnifiedorder;
  }

  @Override
  public void run() {

    try {
      logger.info("MatchOrderDBTask nextDBPayUser 开始 ");
      long currTime = System.currentTimeMillis();
      // 承兑商池 获取承兑商
      MatchMemberBo ret = incomeUnifiedorder.nextDBMember(payType, unitAmt);
      logger.info("MatchOrderDBTask nextDBPayUser 时间--> " + (System.currentTimeMillis() - currTime) + "ms");

      logger.info("MatchOrderDBTask --> " + YvanUtil.toJson(ret));

      if (null == ret) {
        return;
      }

      // 更新订单为 已经匹配到
      boolean result = incomeUnifiedorder.updateOrderInMatchSuccess(orderId, ret);
      if (result) {
        logger.info("MatchOrderDBTask  订单 orderId ：" + orderId + " 分配收款员成功 收款员ID： " + ret.getMemberId());
        return;
      }
      logger.error("MatchOrderDBTask  订单 orderId ：" + orderId + " 收款员ID： " + ret.getMemberId() + "错误 错误 错误");
    } catch (Exception e) {
      // TODO 致命错误 是否需要补偿 ？
      logger.error("MatchOrderDBTask 错误 错误" + e.getMessage());
    }
  }

}
