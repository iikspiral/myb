
//绑定各类事件
//

//修改内容
function updateAutoInvest(v){
		 $("#firststep").hide();
		 $("#secondstep").show();
		 
		 $.post("mall/autoInvest!getAutoInvest.do",{autoid:v},function(data){
		 
			 var item = data;
			 var deadlinestart = item.deadlinestart;
			 var bidamount = item.bidamount;
			 var bidstatus=item.bidstatus;
			 var rateend = item.rateend;
			 var remainamount= item.remainamount;
			 $("#autoid").val(item.auto_id);
			 var paymentmode = item.paymentmode;
			 var validdateend=item.validdateend;
			 var bidsettime = item.bidsettime;
			 var deadlineunit = item.deadlineunit ;
			 var bidsort = item.bidsort;
			 var ratestart = item.ratestart;
			 var deadlineend = item.deadlineend;
			 var producttype=item.producttype;
			 var validdatestart =item.validdatestart;
			 var channelno =item.channelno;
			 
			 //投标金额
			 $("#investCash").prop("checked",true);
			 $("#InvestCashType").attr("disabled",false).val(bidamount);
			 $("#InvestPercentType").attr("disabled",true);
			 
			  
			 //投资期限
			 if(deadlineunit=="天"){
				 if(deadlinestart=='-2'){
					$("#dayType").prop("checked",false); 
					$("#InvestDayLimitType").children().remove();
					$("#InvestDayLimitType").append("<option value='-2'>不选中</option>");
					$("#InvestDayMaxType").children().remove();
					$("#InvestDayMaxType").append("<option value='-2'>不选中</option>");
					$("#InvestDayLimitType").attr("disabled",true);
					$("#InvestDayMaxType").attr("disabled",true);
				 }else  {
					$("#InvestDayLimitType").attr("disabled",false);
					$("#InvestDayMaxType").attr("disabled",false);
					$("#InvestDayLimitType").children().remove();
					$("#InvestDayLimitType").append("<option value='-1'>不限制</option>");
					$("#InvestDayMaxType").children().remove();
					$("#InvestDayMaxType").append("<option value='-1'>不限制</option>");
					$("#InvestDayLimitType").append('<option value="45">45天</option>');
					$("#InvestDayLimitType").append('<option value="90">90天</option>');
					$("#InvestDayLimitType").append('<option value="180">180天</option>');
					$("#InvestDayLimitType").append('<option value="360">360天</option>');
					$("#InvestDayMaxType").append('<option value="45">45天</option>');
					$("#InvestDayMaxType").append('<option value="90">90天</option>');
					$("#InvestDayMaxType").append('<option value="180">180天</option>');
					$("#InvestDayMaxType").append('<option value="360">360天</option>');
					$("#InvestDayLimitType").val(deadlinestart);
					$("#InvestDayMaxType").val(deadlineend);
				 }
			 } 
			 
			 if(deadlineunit=='月'){
				 if(deadlinestart=='-2'){
					 $("#InvestMonthLimitType").children().remove();
					$("#InvestMonthLimitType").append("<option value='-2'>不选中</option>");
					$("#InvestMonthMaxType").children().remove();
					$("#InvestMonthMaxType").append("<option value='-2'>不选中</option>");
					$("#InvestMonthLimitType").attr("disabled",true);
					$("#InvestMonthMaxType").attr("disabled",true);
				 }else  {
			 
						$("#InvestMonthLimitType").attr("disabled",false);
						$("#InvestMonthMaxType").attr("disabled",false);
						$("#InvestMonthLimitType").children().remove();
						$("#InvestMonthLimitType").append("<option value='-1'>不限制</option>");
						$("#InvestMonthMaxType").children().remove();
						$("#InvestMonthMaxType").append("<option value='-1'>不限制</option>");
						for(var i = 1 ;i <=12;i++){
							$("#InvestMonthLimitType").append('<option value="'+i+'">'+i+'月</option>');
							$("#InvestMonthMaxType").append('<option value="'+i+'">'+i+'月</option>');
						}
				 }
				 
			 }
			//收益率
			$("#RateLimitType").val(ratestart);
			$("#RateMaxType").val(rateend);
			//还款方式
			var t =$("input[name=repayOrCash]");
			$.each(t,function(index,value){
				  var v = $(value).val();
				  if(v==paymentmode){
					$(value).prop("checked",true);
				  }
			});
			 //项目类型allowPrjType
			 var t =$("input[name=allowPrjType]");
			$.each(t,function(index,value){
				  var v = $(value).val();
				  if(v==producttype){
					$(value).prop("checked",true);
				  }
			});
			$("#remainmoney").val(remainamount);
			$("#channelno").val(channelno);
			
			
		 },"json");
		} 
$(function(){
	//投标金额事件
	$("#investCash").change(function(){
	$("#InvestPercentType").attr("disabled",true);
	$("#InvestCashType").attr("disabled",false);
	
	});

	$("#investPercent").change(function(){
		$("#InvestPercentType").attr("disabled",false);
		$("#InvestCashType").attr("disabled",true);
		
	});
	//投资期限事件
	 $("#monthType").change(function(){
		 if($("#monthType").prop("checked")){
			$("#InvestMonthLimitType").attr("disabled",false);
			$("#InvestMonthMaxType").attr("disabled",false);
			$("#InvestMonthLimitType").children().remove();
			$("#InvestMonthLimitType").append("<option value='-1'>不限制</option>");
			$("#InvestMonthMaxType").children().remove();
			$("#InvestMonthMaxType").append("<option value='-1'>不限制</option>");
			for(var i = 1 ;i <=12;i++){
				$("#InvestMonthLimitType").append('<option value="'+i+'">'+i+'月</option>');
				$("#InvestMonthMaxType").append('<option value="'+i+'">'+i+'月</option>');
			}
		 }else{
			 $("#InvestMonthLimitType").children().remove();
			$("#InvestMonthLimitType").append("<option value='-2'>不选中</option>");
			$("#InvestMonthMaxType").children().remove();
			$("#InvestMonthMaxType").append("<option value='-2'>不选中</option>");
			$("#InvestMonthLimitType").attr("disabled",true);
			$("#InvestMonthMaxType").attr("disabled",true);
			 
		 }
		 
		 
	 });
	 
	 
	 
	 
	  $("#dayType").change(function(){
		 if($("#dayType").prop("checked")){
			$("#InvestDayLimitType").attr("disabled",false);
			$("#InvestDayMaxType").attr("disabled",false);
			$("#InvestDayLimitType").children().remove();
			$("#InvestDayLimitType").append("<option value='-1'>不限制</option>");
			$("#InvestDayMaxType").children().remove();
			$("#InvestDayMaxType").append("<option value='-1'>不限制</option>");
			$("#InvestDayLimitType").append('<option value="45">45天</option>');
			$("#InvestDayLimitType").append('<option value="90">90天</option>');
			$("#InvestDayLimitType").append('<option value="180">180天</option>');
			$("#InvestDayLimitType").append('<option value="360">360天</option>');
			$("#InvestDayMaxType").append('<option value="45">45天</option>');
			$("#InvestDayMaxType").append('<option value="90">90天</option>');
			$("#InvestDayMaxType").append('<option value="180">180天</option>');
			$("#InvestDayMaxType").append('<option value="360">360天</option>');
		 }else{
			 $("#InvestDayLimitType").children().remove();
			$("#InvestDayLimitType").append("<option value='-2'>不选中</option>");
			$("#InvestDayMaxType").children().remove();
			$("#InvestDayMaxType").append("<option value='-2'>不选中</option>");
			$("#InvestDayLimitType").attr("disabled",true);
			$("#InvestDayMaxType").attr("disabled",true);
			 
		 }
 
	 });
	 
	$("#timeType").change(function(){
		if($("#timeType").prop("checked")){
			$("#beginTime").attr("disabled",false);
			$("#endTime").attr("disabled",false);
			
		} else{
			$("#beginTime").attr("disabled",true);
			$("#endTime").attr("disabled",true);
		}   
			  
	});
	//停用
	$("#delete_invest").bind("click",function(){
		all_save("1");
	 
	});
	//启用
	$("#save_invest").bind("click",function(){
		all_save("2");
	 
	});
	function all_save(bidstatus){
		var bidamount ;//投标金额
		var bidtype;
		var autoid =$("#autoid").val();
		
		
		if($("#investPercent").prop("checked")){
			bidtype="1" ;
			bidamount=$("#InvestPercentType").val();
		}else{
			bidtype="2";
			bidamount=$("#InvestCashType").val();
		}
		//投标期限
		var deadlinestart ;//日期开始
		var deadlineend;//日期结束
		var deadlineunit;//期限单位
		var isdeadline="0" ;
		if($("#monthType").prop("checked")){
			deadlinestart =$("#InvestMonthLimitType").val();
			deadlineend=$("#InvestMonthMaxType").val();
			deadlineunit = "月";
			isdeadline="1";
		} 
		if($("#dayType").prop("checked")){
			deadlinestart =$("#InvestDayLimitType").val();
			deadlineend=$("#InvestDayMaxType").val();
			deadlineunit = "天";
			isdeadline="1"
		}
		
		//利率范围
		var israte ="1";
		var ratestart;
		var rateend;
		 ratestart = $("#RateLimitType").val();
		 rateend = $("#RateMaxType").val();
		 
		 //有效期限
		 var validdatestart ;
		 var validdateend ;
		 var isvaliddate="0";
		 if($("#timeType").prop("checked")){
			 validdatestart=$("#beginTime").val();
			 validdateend =$("#endTime").val();
			 isvaliddate="1";
		 }
		//还款方式
		var paymentmode ;
		paymentmode = $("input[name='repayOrCash']:checked").val();
		
		//项目类型
		var producttype;
		producttype = "";
		$("input[name='allowPrjType']:checked").each(function(index,e){
			
			producttype+=$(e).attr('allowtype')
			
			});
		//保留金额
		var remainamount;
		remainamount = $("#remainmoney").val();
		//回款渠道
		var channelno ;
		channelno = $("#channelno").val();
		$.post("mall/autoInvest!addinvestproduct.do",{bidamount:bidamount,bidtype:bidtype,
			deadlinestart:deadlinestart,deadlineend:deadlineend,deadlineunit:deadlineunit,
			isdeadline:isdeadline,israte:israte,ratestart:ratestart,rateend:rateend,validdatestart:validdatestart,
			validdateend:validdateend,isvaliddate:isvaliddate,paymentmode:paymentmode,producttype:producttype,
			remainamount:remainamount,channelno:channelno,autoid:autoid,
			bidstatus:bidstatus
		},function(data){
			var kdjson = data.kdjson;
			var flag = kdjson.flag;
			if(flag=="1"){
				window.location.reload();
			}else{
				$.kd.kdAlert("添加失败！")
			}
			
		},"json");
		}	 
	 
});


$(function(){
	$("#secondstep").hide();
	
	$("#add_auto_invest_btn").bind("click",function(){
		$("#firststep").hide();
		$("#secondstep").show();
	
	});
	$("#auto_invest_list_btn").bind("click",function(){
		$("#firststep").show();
		$("#secondstep").hide();
	
	});
	
});
//初始化页面各项参数
$(function(){
	
	
	$("#beginTime").attr("disabled",true);
	$("#endTime").attr("disabled",true);
	$("#investPercent").prop("checked",true);
	$("#InvestPercentType").attr("disabled",false);
	$("#InvestCashType").attr("disabled",true);
	
	$("#repaydefault").attr("checked",true);
	$("#InvestPercentType").children().remove();
	$("#InvestPercentType").append("<option selected='selected' value='-1'>无</option>");
	$("#InvestMonthLimitType").children().remove();
	$("#InvestMonthLimitType").append("<option value='-1'>不限制</option>");
	
	$("#InvestMonthMaxType").children().remove();
	$("#InvestMonthMaxType").append("<option value='-1'>不限制</option>");
	
	$("#InvestDayLimitType").children().remove();
	$("#InvestDayLimitType").append("<option value='-1'>不限制</option>");
	
	
	$("#InvestDayMaxType").children().remove();
	$("#InvestDayMaxType").append("<option value='-1'>不限制</option>");
 
	$("#RateLimitType").children().remove();
	$("#RateLimitType").append("<option value='-1'>不限制</option>");
	
	$("#RateMaxType").children().remove();
	$("#RateMaxType").append("<option value='-1'>不限制</option>");
	for(var i = 1 ;i <=10;i++){
		$("#InvestPercentType").append('<option value="'+i+'0">'+i+'0%</option>');
	}
	for(var i = 1 ;i <=12;i++){
		$("#InvestMonthLimitType").append('<option value="'+i+'">'+i+'月</option>');
		$("#InvestMonthMaxType").append('<option value="'+i+'">'+i+'月</option>');
	}
	for(var i = 1 ;i <=30;i++){
		$("#InvestDayLimitType").append('<option value="'+i+'">'+i+'天</option>');
		$("#InvestDayMaxType").append('<option value="'+i+'">'+i+'天</option>');
	}
	for(var i = 1 ;i <=24;i++){
		$("#RateLimitType").append('<option value="'+i+'">'+i+'%</option>');
		$("#RateMaxType").append('<option value="'+i+'">'+i+'%</option>');
	}
	$("#InvestDayLimitType").append('<option value="45">45天</option>');
	$("#InvestDayLimitType").append('<option value="90">90天</option>');
	$("#InvestDayLimitType").append('<option value="180">180天</option>');
	$("#InvestDayLimitType").append('<option value="360">360天</option>');
	$("#InvestDayMaxType").append('<option value="45">45天</option>');
	$("#InvestDayMaxType").append('<option value="90">90天</option>');
	$("#InvestDayMaxType").append('<option value="180">180天</option>');
	$("#InvestDayMaxType").append('<option value="360">360天</option>');
	
});