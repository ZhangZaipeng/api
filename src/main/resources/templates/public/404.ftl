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
    <div style="font-size: 24px;font-weight: bold;text-align: center;margin:12px 0;">
      无效订单
    </div>
  </div>
</div>
</body>
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

</script>
</html>
