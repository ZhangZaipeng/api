<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <meta name="keywords" content="{$site_info.site_seo_keywords|default=''}"/>
  <meta name="description" content="{$site_info.site_seo_description|default=''}"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"
  />
  <title>用户充值</title>
  <!-- 引入样式 -->
  <style>
    .pc {
      max-width: 800px;
      margin: 0 auto;
    }
  </style>
</head>
<body>
<div id="app">
  <div class="pc">
    <div style="
            font-weight: bold;
            font-size: 18px;
            color: #86c22d;
            margin-bottom: 20px;
            text-align: center;">
      订单支付
    </div>
    <div style="font-size:14px;">
      <span style="color: #8a8a8a;">订单号：</span>
      <span style="color: red;">${orderInVo.orderId}</span>
    </div>
    <div style="color:#8a8a8a;font-size: 14px; margin-top:12px;">
      请长按复制订单号，若5分钟内未到账，请联系客服。
    </div>
    <div style="font-size: 14px;color:red;margin-top: 12px;">
      请按<span style="font-size: 20px;color:black;font-weight: bold;margin: 0 8px;">${orderInVo.payAmt}</span>付款，请勿修改金额，否则一概不到账！
    </div>
    <div style="font-size: 14px;color:red;margin-top:8px;">
      订单5分钟内有效，请及时付款。
    </div>
    <div class="qcode">
      <div style="font-size: 24px;font-weight: bold;text-align: center;margin:12px 0;">
        扫码前往支付宝转账
      </div>
      <div>
        <div style="width:200px;height:200px;margin:0 auto;background: black;">
          <div id="qrid"></div>
        </div>
      </div>
    </div>
    <div style="font-size: 24px;font-weight: bold;text-align: center;margin:12px 0;">
      支付宝转银行卡
    </div>
    <div>
      <div style="color:black;font-size: 14px;">收款卡号：</div>

      <div onclick="copy('${orderInVo.account}')"
           style="width:100%;padding:20px 0;text-align: center;background: #409eff;cursor: pointer;font-weight: 400;color: white;">
          ${orderInVo.account}<span style="color: white;font-size: 14px;">(点击复制)</span>
      </div>
    </div>
    <div>
      <div style="color:black;font-size: 14px;">收款姓名：</div>
      <div onclick="copy('${orderInVo.realName}')"
           style="width:100%;padding:20px 0;text-align: center;background: #409eff;cursor: pointer;font-weight: 400;color: white;">
          ${orderInVo.realName}<span style="color: white;font-size: 14px;">(点击复制)</span>
      </div>
    </div>
    <div style="margin-top:24px;">
      <a href="alipays://platformapi/startapp?appId=20000691&url=http%3A%2F%2F7x5.co%2Fe8a8ae"
         target="_blank">
        <div
            style="width:100%;padding:20px 0;text-align: center;background: #F56C6C;cursor: pointer;font-weight: bold;color:white;border:1px solid #ccc;border-radius: 4px;font-size: 20px;"
            class="qcode_button">
          点我前往支付宝
        </div>
      </a>
    </div>
    <div style="color:red;font-size: 14px; margin-top:12px;">
      转账请设置“即时到账”，以免影响您上分等待安卓端如跳转不到，请按下面流程进行转账。
    </div>
    <div style="color:red;font-size: 14px; margin-top:12px;">
      请手动输入付款金额<span
          style="font-size: 20px;color:black;font-weight: bold;margin: 0 8px;">${orderInVo.payAmt}</span>，以减小风控
    </div>
    <div style="color:red;font-size: 14px; margin-top:12px;">
      第一步：打开支付宝，点击“<span
          style="font-size: 20px;color:black;font-weight: bold;margin: 0 8px;">转账</span>”
    </div>
    <!--<img src="https://m.ht5.co/layui/images/1.png" alt="">-->
  </div>
</div>
</body>

<script type='text/javascript'src='http://cdn.staticfile.org/jquery/2.1.1/jquery.min.js'></script>
<script type="text/javascript"src="http://cdn.staticfile.org/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>

<script>
  function copy(value) {
    let transfer = document.createElement('input');
    document.body.appendChild(transfer);
    transfer.value = value;  // 这里表示想要复制的内容
    transfer.focus();
    transfer.select();
    if (document.execCommand('copy')) {
      document.execCommand('copy');
    }
    transfer.blur();
    alert('复制成功!');
    document.body.removeChild(transfer);
  }

  if (document.body.offsetWidth < 425) {
    document.querySelector('.qcode').style.display = 'none';
  } else {
    document.querySelector('.qcode_button').style.display = 'none';
  }

  //
  $(function(){
    var qrstr = "alipays://platformapi/startapp?appId=20000691&url=http%3A%2F%2F7x5.co%2Fe8a8ae";
    $('#qrid').qrcode(qrstr);//不指定二维码大写
  });
</script>
</html>
