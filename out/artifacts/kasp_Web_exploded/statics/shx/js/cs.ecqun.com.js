var EC_SS_FUNCTION =1;
var EC_CS_STATUS = new Object();
EC_CS_STATUS["1451084"] = '4';
EC_CS_STATUS["1451057"] = '1';
EC_CS_STATUS["1513988"] = '0';
var EC_PC_STATUS = new Object();
EC_PC_STATUS["1451084"] = '4';
EC_PC_STATUS["1451057"] = '1';
var EC_CS_FOLDER='cl/v4011min/';
var EC_CS_LAST_EDIT='1467284599';
var EC_CS_PIC_OLD_STATUS='0';
if(typeof window.EC_CS_LOADED=="undefined"){
	var EC_CS_SSL = 0; ("https:" == document.location.protocol) && (EC_CS_SSL = 1);
	
	//check if ie or not 
	var regpie=/msie [678]\.0/;
	var regpxp=/nt 5\.1/;
	var EC_IS_XPIE = 1;
	var EC_USER_AGENT = window.navigator.userAgent.toLowerCase();
	if (!regpie.test(EC_USER_AGENT) || !regpxp.test(EC_USER_AGENT)) {
		EC_CS_SSL = 1;
		EC_IS_XPIE = 0;
	}
	
	var EC_CS_Protocol = (EC_CS_SSL == 1? "https://": "http://");
	var EC_CS_ISPREVIEW = '';					//预览标识
	var EC_CS_OPEN = 1;										//在线客服标志
	var EC_CS_ISPREVIEW = '0';
	var EC_CS_PREVIEW = '';//在线客服预览
	var EC_CS_GURL = encodeURIComponent(document.location.href);		//当前页面URL
	var EC_CS_REFERRER = encodeURIComponent(document.referrer);		//来源页面URL
	var EC_CS_DOCTYPE = document.firstChild.nodeValue;
	// var EC_CS_SERVER= EC_CS_Protocol + ((EC_CS_SSL == 1 )? "cs2.ecqun.com/" : "cs.ecqun.com/");
	var EC_CS_SERVER= EC_CS_Protocol +"cs.ecqun.com/" ;

	var EC_CS_STATIC=(EC_CS_SSL==1)?'https://www.staticec.com/cs/':'//www.staticec.com/cs/';  //静态资源地址
	var EC_CS_STATIC_UPLOAD=(EC_CS_SSL==1)?'https://www.staticec.com/':'//www.staticec.com/';  //静态资源地址

	var EC_THUME_FOLDER='/themes/v4011';
	var EC_VERSION='4.0.0.0';
	var EC_CS_ID='1440955';
	
	var EC_CS_THEME='7';				//
	var EC_CS_SHOWTITLE='高新普惠';				//标题头
	var EC_CS_PICSKIN='7'; 			//skin 0 蓝色 1红色 2绿色 3橙色 4红色
	var EC_CS_FLOAT='1';  					//布局 0左 1右
	var EC_CS_FIXED='0';  					//布局 固定
	var EC_CS_LANG='0'; 					//0中文 1英文 2繁体
	var EC_CS_SCHEME='0'; 					//第几套样式
	var EC_CS_AUTO_HIDE='1'; 			//0不自动隐藏 1自动隐藏
	
	//辅助工具显示
	var EC_AIDS_SHOWTYPE='0';		//横排竖排
	var EC_AIDS_QQ='1';
	var EC_AIDS_CRMQQ='0';
	var EC_AIDS_SKYPE='0';
	var EC_AIDS_YAHOO='0';
	var EC_AIDS_ALIWW='0';
	var EC_AIDS_ALIBB='0';
	var EC_AIDS_ALIGJ='0';
	var EC_AIDS_MSN='0'; 
	
	
	var EC_CS_SHOW_STYLE='0'; 			//显示风格 0-列表 1-图片
	var EC_CS_LIST_RAND='1'; 			//随机显示
	var EC_CS_SHOW_PICON=''; 				//在线图片
	var EC_CS_SHOW_PICOFF=''; 			//离线图片
	var EC_CS_SHOW_PHONE='2'; 			//电话回拨开启
	
	var EC_CS_TOP='100';						//高度
	var EC_CS_MARGIN='10';					//边距
	var EC_CS_HIDEOFF='0';				//离线隐藏
	var EC_CS_QQOPEN='0';				//QQ客服开启
	var EC_CS_ALLSET = '[{"id":"84761","name":"\u5728\u7ebf\u5ba2\u670d","expand":"0","item":[{"f_cs_id":"1451084","f_corp_id":"1440955","f_valid":"1","f_show_mobile":"0","f_show_name":"\u5c0f\u666e@\u9ad8\u65b0\u666e\u60e0","f_group_id":"84761","f_name":"\u5c0f\u666e@\u9ad8\u65b0\u666e\u60e0","f_title":"\u5ba2\u670d","f_qq":"2317728824","f_scheme":"0","f_mobile":"13007010775","f_show_qq":"1","f_sms_online":"0","f_msn":"","f_show_msn":"0","f_email":"service@gaoxinzb.com","f_tel":"","f_telno":"4000784888","f_show_tel":"0","f_contact":"\u5ba2\u6237\u670d\u52a1","f_cs_mobile":"13007010775","f_position":"1","f_other1":"0","f_other2":"0","f_other_text1":"","f_other_text2":"","f_qq_first":"0","f_status":"1","f_aids_open":"0","f_aids_qq":"","f_aids_crmqq":"","f_aids_skype":"","f_aids_aliww":"","f_aids_yahoo":"","f_aids_msn":"","f_aids_alibaba":"","f_aids_alitrade":"","uqq":"1"},{"f_cs_id":"1513988","f_corp_id":"1440955","f_valid":"1","f_show_mobile":"0","f_show_name":"\u5c0f\u60e0@\u9ad8\u65b0\u666e\u60e0","f_group_id":"84761","f_name":"\u5c0f\u60e0@\u9ad8\u65b0\u666e\u60e0","f_title":"\u5ba2\u6237\u670d\u52a1","f_qq":"794870195","f_scheme":"0","f_mobile":"15135125589","f_show_qq":"1","f_sms_online":"0","f_msn":"","f_show_msn":"0","f_email":"","f_tel":"","f_telno":"4000784888","f_show_tel":"0","f_contact":"\u5ba2\u6237\u670d\u52a1","f_cs_mobile":"15536077671","f_position":"2","f_other1":"0","f_other2":"0","f_other_text1":"","f_other_text2":"","f_qq_first":"0","f_status":"0","f_aids_open":"0","f_aids_qq":"","f_aids_crmqq":"","f_aids_skype":"","f_aids_aliww":"","f_aids_yahoo":"","f_aids_msn":"","f_aids_alibaba":"","f_aids_alitrade":"","uqq":"1"},{"f_cs_id":"1451057","f_corp_id":"1440955","f_valid":"1","f_show_mobile":"0","f_show_name":"\u5c0f\u82b1@\u9ad8\u65b0\u666e\u60e0","f_group_id":"84761","f_name":"\u5c0f\u82b1@\u9ad8\u65b0\u666e\u60e0","f_title":"\u5ba2\u6237\u7ba1\u7406","f_qq":"3027488936","f_scheme":"0","f_mobile":"15903406099","f_show_qq":"1","f_sms_online":"0","f_msn":"","f_show_msn":"0","f_email":"service@gaoxinzb.com","f_tel":"","f_telno":"4000784888","f_show_tel":"0","f_contact":"\u5ba2\u6237\u670d\u52a1","f_cs_mobile":"0351-7330251","f_position":"3","f_other1":"0","f_other2":"0","f_other_text1":"","f_other_text2":"","f_qq_first":"0","f_status":"0","f_aids_open":"0","f_aids_qq":"","f_aids_crmqq":"","f_aids_skype":"","f_aids_aliww":"","f_aids_yahoo":"","f_aids_msn":"","f_aids_alibaba":"","f_aids_alitrade":"","uqq":"0"}]}]';		 					//客服列表
	var EC_CS_TRACK=''; 						//主动跟踪			
	var EC_CS_TRACKID='';
	var EC_CS_TRACKKEY='';
	
	var EC_CS_BTN_STYLE = '0';//按钮样式
	var EC_CS_BTN_BG = "/themes/v4011/fad/default";//邀请框按钮样式
	var EC_CS_BTN_TXT = '';//按钮文字颜色
	var EC_CS_THEME_CUS = '';//主题自定义图片
	var EC_CS_THEME_CUS_MIN = '';//最小化自定义图片
	
	var EC_CS_LA_ENABLE= '0';//列表广告开启:1-开启，0-关闭
	var EC_CS_LA_IMG = '';//列表广告图片
	EC_CS_LA_IMG != '' && (EC_CS_LA_IMG += '?'+EC_CS_LAST_EDIT);
	var EC_CS_LA_IMGLINK = '';//列表广告图片链接地址
	
	var EC_CS_BBTN_BG = "/themes/v4011/fad/default";//邀请框按钮样式
	var EC_CS_BTHEME='7';				//
	var EC_CS_DIALOG_TITLE='高新普惠'; 	//主动标题
	var EC_CS_ENABLE_TITLE='1'; 	//主动标题
	var EC_CS_DIALOG_CONTENT='您好！欢迎来到我们的网站 ! 请问有什么可以帮助您吗？'; //主动会话内容
	var EC_CS_ENABLE_CONTENT='1'; //主动会话内容
	var EC_CS_DIALOG='0';				//0 不主动会话 1 主动会话
	var EC_CS_DIALOG_TIME='5';			//0 主动会话窗口延迟时间
	var EC_CS_BBTN_STYLE = '0';//邀请框按钮样式
	var EC_CS_BBTN_TXT = '#333333';//邀请框按钮文字颜色
	var EC_CS_BTHEME_CUS = '';//邀请框主题自定义图片
	EC_CS_BTHEME_CUS != '' && (EC_CS_BTHEME_CUS += '?'+EC_CS_LAST_EDIT);
	var EC_CS_DIALOG_POSITION='0';//邀请框显示位置(0居中,1右下角)
	var EC_CS_BA_ENABLE= 0;//'0';//邀请框广告开启:1-开启，0-关闭
	var EC_CS_BA_IMG = '';//邀请框广告图片
	var EC_CS_BA_IMGLINK = '';//邀请框广告图片链接地址
	var EC_CS_BA_BCOLOR='#aaaaaa';//邀请框广告边框颜色
	
	var EC_CS_SKIN_STYLE;
			EC_CS_SKIN_STYLE = 2;//简约列表
		//邀请框
	var EC_CS_BOX_STYLE;
			EC_CS_BOX_STYLE = 2;//简约列表
		
	var EC_CS_BOX_FACE 		= '1';
    //头像，针对加static后企业管理中预览有问题的情况，添加过滤
    var EC_CS_BFU_IMG='/upload/cs/1440955/0picthemeavatar.gif';
    
    
    if (EC_CS_BFU_IMG.indexOf('www.staticec.com')!='-1') {
        var EC_CS_BOX_FACE_URL	= EC_CS_BFU_IMG;
    }else{
        var EC_CS_BOX_FACE_URL	= EC_CS_STATIC+ EC_CS_BFU_IMG;
        (EC_CS_BFU_IMG.indexOf('upload')!='-1')&&(EC_CS_BOX_FACE_URL	= EC_CS_STATIC_UPLOAD+ EC_CS_BFU_IMG);
    }
    
    
	
	function ec_initJS(b,a){var s=document.createElement("script");s.type="text/javascript";s.charset=typeof(a)!="undefined"?a:"utf-8";s.src=b;document.getElementsByTagName("head").item(0).appendChild(s)}
	
	if(EC_CS_OPEN==1&&typeof window.EC_CS_LOADED=="undefined"){
		window.EC_CS_LOADED=1;
		ec_initJS(EC_CS_STATIC+EC_CS_FOLDER+"/c.js");
	}
	
}