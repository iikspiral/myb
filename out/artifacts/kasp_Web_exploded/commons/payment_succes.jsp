<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>支付成功</title>
		<link rel="stylesheet" href="${staticserver}/common/css/ibase.css">
		<link rel="stylesheet" href="${staticserver}/common/css/common.css">
		<link rel="stylesheet" href="${staticserver}/common/css/new_user.css">
		<link rel="stylesheet" type="text/css"
			href="${staticserver}/common/css/5istyle.css">
		<link rel="stylesheet" type="text/css"
			href="${staticserver}/common/css/kd.ui.css">
		<link rel="stylesheet" type="text/css"
			href="${staticserver}/common/css/kd.ui.plugin.css">
	</head>
	<body >
		<div align="center">
			<div style="height: 100px;"></div>
			<div class="kd-outer-box" style="height: 200px">
				<div class="kd-outer-box-title">
					<h>
					非常感谢您的投资
					</h>
					<div>
						<div>
							支付成功,支付金额：${Amount}元
						</div>

						<div style="font-size: 13px">
							系统将会在<span id="second">&nbsp;5&nbsp;</span>秒后自动跳回，如果浏览器不能自动跳转，请<a style="text-decoration: underline;color: #F50;" href="${staticserver}/../my_orderlist.html">点击此处</a>回到订单列表。
						</div>

					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var n = 4, temp = setInterval(function() {
				if(n == 0) {
					clearInterval(temp);
					window.location.href = "${staticserver}/../tx_order_deal_list.html";
				}
				document.getElementById("second").innerHTML = "&nbsp;" + n-- + "&nbsp;";
			}, 1000);
  		</script>
	</body>
</html>



