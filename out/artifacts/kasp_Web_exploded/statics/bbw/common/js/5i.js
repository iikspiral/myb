 (function($) {
	$(document).ready(function() {	
		$.fn.placeholder && $('[placeholder]').placeholder();
	});
}) (jQuery);

$(document).ready(function(){
	$("#back-to-top").css("right",(($(window).width()-1040)/2-75)+"px");
	$("#back-to-top").hide();
	$(function () {
		$(window).scroll(function(){
			if ($(window).scrollTop()>10){
			$("#back-to-top").fadeIn(300);
			}
			else
			{
				$("#back-to-top").fadeOut(300);
			}
		});
		$("#back-to-top").click(function(){
		$('body,html').animate({scrollTop:0},200);
			return false;
		});
	});
});

String.prototype.replaceAll  = function(s1,s2){   
return this.replace(new RegExp(s1,"gm"),s2);   
} 

function clearDefault(obj){
	if(obj.value == obj.defaultValue) {
	obj.value ='';
	obj.style.color = "";
	}
};
function resetValue(obj){
	if(obj.value=="") {
		obj.value=obj.defaultValue;
		obj.style.color = "#666";
	}
};
function hiddeaccountPanel(obj){
	$("#accountPanel").hide();
};
function hiddemsgPanel(obj){
	$("#msgPanel").hide();
};

var AccountInterVal = null;
var msgInterVal = null;
var wxInterVal = null;
$(function () {
	$("#wxBtn").mouseenter(function(){
		$(".bwx").show();
	})
	$("#wxBtn").mouseleave(function(){
		 wxInterVal = window.setTimeout(closeWx, 300);
	})
	$(".bwx").mouseenter(function(){
		 window.clearTimeout(wxInterVal);
	})
	
	$(".bwx").mouseleave(function(){
		$(".bwx").hide();
	})
	
	$("#topclose").click(function(){
		closeTopTip();
	})
  $("#actionAccount").live("mouseenter",function(){
		$("#accountPanel").show();
  })
   $("#actionMsg").live("mouseenter",function(){
		$("#msgPanel").show();
  })
  $("#actionAccount").live("mouseleave",function(){
		AccountInterVal = window.setTimeout(hiddeaccountPanel, 300);
  })
  $("#actionMsg").live("mouseleave"	,function(){
		msgInterVal = window.setTimeout(hiddemsgPanel, 300);
  })
  $("#accountPanel").live("mouseenter",function() {
        window.clearTimeout(AccountInterVal);
  })
  $("#actionMsg").live("mouseenter",function() {
        window.clearTimeout(msgInterVal);
  })
  setTopNavStyle();


});

function closeWx(){
	$(".bwx").hide();
}

function closeTopTip(){
	$(".top-tip").fadeOut("slow");
}

function openTopTip(className,msg){
	$("#topmessage").html(msg);
	$("#topmessage").removeClass("error").removeClass("warning").removeClass("info").addClass(className);
	$(".top-tip").fadeIn("normal");
}

function setHeadHtml(){

    $.ajax({
        url: "",
        data: { method: "GetHeadHtml",location:"head",rand:(new Date()).getTime() },
        dataType: "text",
        timeout: 10000,
        async: false,
        success: function(data) {
			$("#headInfo").html(data);
			if($("#a_Referer").attr("href")!=undefined && $("#a_Referer").attr("href").indexOf("referer=")<0 && location.href.indexOf("/login.html")<0 && location.href.indexOf("/register")<0){
				$("#a_Referer").attr("href", "http://www" + $("#jsDomain").val() + "/login.html?referer=" + encodeURIComponent(location.href));
			}
			if($("#a_Referer_logout").attr("href")!=undefined && $("#a_Referer_logout").attr("href").indexOf("referer=")<0 && location.href.indexOf("/login.html")<0 && location.href.indexOf("/register")<0){
				$("#a_Referer_logout").attr("href", "http://www" + $("#jsDomain").val() + "/logout.html?referer=" + encodeURIComponent(location.href));
			}
        }
    })
}

function setTopNavStyle() {
    $(".head-l a").removeClass("cur");
    if (location.href.indexOf("/projects.html") > 0 || location.href.indexOf("/project_view.html") > 0) {
        $("#n_projects").addClass("cur");
    } else if (location.href.indexOf("/investors.html") > 0) {
        $("#n_investors").addClass("cur");
    } else if (location.href.indexOf("/investors.html") > 0) {
        $("#n_investors").addClass("cur");
    } else if (location.href.indexOf("/activities.html") > 0) {
        $("#n_activities").addClass("cur");
    } else if (location.href.indexOf("/project.html") > 0) {
        $("#n_project").addClass("cur");
    } else if (location.href.indexOf("/index.html") > 0 || location.href.indexOf("/index.html") > 0 || location.href == ("http://www" + $("#jsDomain").val() + "/")) {
        $("#n_home").addClass("cur");
    }
}

function isEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
		return true;
	else
		return false;
}

function isMobile(strMobile) {
	var reg1 = /^13\d{9}$/; //130--139������7λ 
	var reg2 = /^15\d{9}$/;
	var reg3 = /^16\d{9}$/;
	var reg4 = /^18\d{9}$/;

	var my = false;
	if (reg1.test(strMobile)) my = true;
	if (reg2.test(strMobile)) my = true;
	if (reg3.test(strMobile)) my = true;
	if (reg4.test(strMobile)) my = true;
	if (!my) {
		return false;
	} else {
		return true;
	}

	return false;
}

//<%--������֤״̬--%>
function setValidState(id, state, text) {
	if (state == "tip") {
		$("#" + id).removeClass("error");
		$("span[name='" + id + "']").removeClass("error").removeClass("correct").addClass("tip").text(text);
	}
	else if (state == "correct") {
		$("#" + id).removeClass("error");
		$("span[name='" + id + "']").removeClass("error").removeClass("tip").addClass("correct").html(text);
	}
	else if (state == "error") {
		$("#" + id).addClass("error");
		$("span[name='" + id + "']").removeClass("correct").removeClass("tip").addClass("error").text(text);
	}
}
//<%--Ĭ����ʾ״̬--%>
function setValidState_tip(id, text) {
	setValidState(id, 'tip', text);
}
//<%--��ȷ״̬--%>
function setValidState_correct(id) {
	setValidState(id, 'correct', '&nbsp;');
}
function setValidState_correct2(id, text) {
	setValidState(id, 'correct', text);
}
//<%--����״̬--%>
function setValidState_error(id, text) {
	setValidState(id, 'error', text);
}