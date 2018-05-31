<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html  >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title> </title> 
	</head>
	<body>
				<form id="onlinepayForm" name="onlinepayForm" action="${action}" method="post" style="display:none">
				<!--  
						<input id="inputCharset" name="inputCharset" value="${inputCharset}"/>
						<input id="language" name="language" value="${language}"/>
						<input id="merchantId" name="merchantId" value="${merchantId}"/>
						<input id="orderAmount" name="orderAmount" value="${orderAmount}"/>
						<input id="orderCurrency" name="orderCurrency" value="${orderCurrency}"/>
						<input id="orderDatetime" name="orderDatetime" value="${orderDatetime}"/>
						<input id="orderNo" name="orderNo" value="${orderNo}"/>
						<input id="payType" name="payType" value="${payType}"/>
						<input id="pickupUrl" name="pickupUrl" value="${pickupUrl}"/>
						<input id="receiveUrl" name="receiveUrl" value="${receiveUrl}"/>
						<input id="signType" name="signType" value="${signType}"/>
						<input id="strSignMsg" name="strSignMsg" value="${strSignMsg}"/>
						<input id="version" name="version" value="${version}"/>
						
				-->		
		<input type="hidden" name="inputCharset" value="${inputCharset}"/>
		<input type="hidden" name="pickupUrl" value="${pickupUrl}"/>
		<input type="hidden" name="receiveUrl" value="${receiveUrl}" />
		<input type="hidden" name="version" value="${version}"/>
		<input type="hidden" name="language" value="${language}" />
		<input type="hidden" name="signType" value="${signType}"/>
		<input type="hidden" name="merchantId" value="${merchantId}" />
		<input type="hidden" name="payerName" value=""/>
		<input type="hidden" name="payerEmail" value="" />
		<input type="hidden" name="payerTelephone" value="" />
		<input type="hidden" name="orderNo" value="${orderNo}" />
		<input type="hidden" name="orderAmount" value="${orderAmount}"/>
		<input type="hidden" name="orderCurrency" value="${orderCurrency}" />
		<input type="hidden" name="orderDatetime" value="${orderDatetime}" />
		
		
		<input type="hidden" name="productName" value="" />
		<input type="hidden" name="productPrice" value="" />
		<input type="hidden" name="productNum" value=""/>
		<input type="hidden" name="productId" value="" />
		<input type="hidden" name="productDesc" value="" />
		<input type="hidden" name="ext1" value="" />
		<input type="hidden" name="ext2" value="" />
		<input type="hidden" name="payType" value="${payType}" />
		<input type="hidden" name="tradeNature" value="" />
		<input type="hidden" name="signMsg" value="${strSignMsg}" />
		<input type="hidden" name="payerIDCard" value="" />
		<input type="hidden" name="pid" value=""/>
		<input type="hidden" name="orderExpireDatetime" value=""/>	
		<input type="hidden" name="issuerId" value="" />
		<input type="hidden" name="pan" value="" />
	   
						
			 </form>

		
	</body>
	<script type="text/javascript">
		
			document.getElementById("onlinepayForm").submit();
  	</script>
</html>



