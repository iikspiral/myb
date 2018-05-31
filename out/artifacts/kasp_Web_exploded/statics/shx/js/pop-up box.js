/* 本文件放在jQuery后面 */

/* 往页面添加带有按钮的弹出框 */
var addPopUp = function(id,title,msg,choose){
	if(choose){//有函数，多显示取消按钮
		$("body")
		.append('<div class="modal fade" id="'+id+'" tabindex="-1" role="dialog" style="z-index: 900000;"  aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">'
			+'<div class="modal-dialog" style="width: 400px;z-index: 900000;">'
				+'<div class="modal-content">'
					+'<div class="modal-header">'
						+'<button type="button" class="close" data-dismiss="modal">'
							+'<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>'
						+'</button>'
						+'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>'
					+'</div>'
					+'<div class="modal-body" align="center" style="font-size: 16px">'
						+msg
					+'</div>'
					+'<div class="modal-footer">'
						+'<a type="button" class="btn-sm btn-orange btn-adjust" id="'+id+'_sure">确定</a>'
						+"&nbsp;&nbsp;"
						+'<a type="button" class="btn-sm btn-grey btn-adjust" id="'+id+'_cancel">取消</a>'
					+'</div>'
				+'</div>'
			+'</div>'
		+'</div>').css("overflow-x", "auto") ;
	}else{//有函数，不显示取消按钮
		$("body")
		.append('<div class="modal fade" id="'+id+'" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">'
			+'<div class="modal-dialog" style="width: 400px;">'
				+'<div class="modal-content">'
					+'<div class="modal-header">'
						+'<button type="button" class="close" data-dismiss="modal">'
							+'<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>'
						+'</button>'
						+'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>'
					+'</div>'
					+'<div class="modal-body" align="center" style="font-size: 16px">'
						+msg
					+'</div>'
					+'<div class="modal-footer">'
						+'<a type="button" class="btn-sm btn-orange btn-adjust" id="'+id+'_sure">确定</a>'
					+'</div>'
				+'</div>'
			+'</div>'
		+'</div>');
	}
}
/* 往页面添加无按钮的弹出框 */
var addPopUpConfig = function(id,title,msg,choose){
	if(choose){//有函数，无按钮
		$("body")
		.append('<div class="modal fade" id="'+id+'" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">'
			+'<div class="modal-dialog" style="width: 400px;">'
				+'<div class="modal-content">'
					+'<div class="modal-header">'
						+'<button type="button" class="close" data-dismiss="modal">'
							+'<span aria-hidden="true">&times;</span><span class="sr-only">关闭</span>'
						+'</button>'
						+'<h4 class="modal-title" id="myModalLabel">'+title+'</h4>'
					+'</div>'
					+'<div class="modal-body" align="center" style="font-size: 14px;padding:32px;">'
						+msg
					+'</div>'
					+'<div class="modal-footer">'
						+'<a type="button" class="btn-sm btn-orange btn-adjust" id="'+id+'_sure">立即支付</a>'
						+"&nbsp;&nbsp;"
						+'<a type="button" class="btn-sm btn-grey btn-adjust" id="'+id+'_cancel">支付遇到问题</a>'
					+'</div>'
				+'</div>'
			+'</div>'
		+'</div>');
	}
}
/* 定义弹出框调用方法 */
var zhejiang_confirm = function(title,msg,func,funcs){//标题，提示信息，点击确认执行的函数
	var id = 'confirmWindow';
	if( typeof(func) == 'function'){
		// 往页面添加弹出框
		addPopUp(id,title,msg,true);
		$('#'+id).modal({backdrop: 'static', keyboard: false});	
		// 显示弹出框
		$('#'+id).modal('show');
		// 绑定确定按钮事件
		$('#'+id+'_sure').click(function(){
			func();
			$('#'+id).modal('hide');
			$('#'+id).remove();
			$('body').removeClass("modal-open");
		});
		// 绑定取消按钮事件
		
			$('#'+id+'_cancel').click(function(){
				if(typeof(funcs)=='function'){
					funcs();
				}
				$('#'+id).modal('hide');
				$('#'+id).remove();
				$('body').removeClass("modal-open");
			});
		
	
	}else{
		// 往页面添加弹出框
		addPopUp(id,title,msg,true);
		$('#'+id).modal({backdrop: 'static', keyboard: false});
		// 显示弹出框
		$('#'+id).modal('show');
		// 绑定确定按钮事件
		$('#'+id+'_sure','#'+id+'_cancel').click(function(){
			$('#'+id).modal('hide');
			$('#'+id).remove();
			$('body').removeClass("modal-open");
		});
	}
}
var zhejiang_alert = function(title,msg,func){
	var id = 'alertWindow';
	if( typeof(func) == 'function'){
		// 往页面添加弹出框
		addPopUp(id,title,msg,false);
		$('#'+id).modal({backdrop: 'static', keyboard: false});
		// 显示弹出框
		$('#'+id).modal('show');
		// 绑定确定按钮事件
		$('#'+id+'_sure').click(function(){
			func();
			$('#'+id).modal('hide');
			$('#'+id).remove();
			$('body').removeClass("modal-open");
		});
	}else{
		// 往页面添加弹出框
		addPopUp(id,title,msg,false);
		$('#'+id).modal({backdrop: 'static', keyboard: false});
		// 显示弹出框
		$('#'+id).modal('show');
		// 绑定确定按钮事件
		$('#'+id+'_sure').click(function(){
			$('#'+id).modal('hide');
			$('#'+id).remove();
		$('body').removeClass("modal-open");
		});
	}
}
var sxi_config = function(title,msg,func,funcs){
	var id = 'confirmWindow';
	if( typeof(func) == 'function'){
		// 往页面添加弹出框
		addPopUpConfig(id,title,msg,true);
		$('#'+id).modal({backdrop: 'static', keyboard: false});
		// 显示弹出框
		$('#'+id).modal('show');
		// 绑定确定按钮事件
		$('#'+id+'_sure').click(function(){
			func();
			$('#'+id).modal('hide');
			$('#'+id).remove();
			$('body').removeClass("modal-open");
		});
		// 绑定取消按钮事件
		
			$('#'+id+'_cancel').click(function(){
				if(typeof(funcs)=='function'){
					funcs();
				}
				$('#'+id).modal('hide');
				$('#'+id).remove();
		 		$('body').removeClass("modal-open");
			});
	}
}

var refresh = function(){
	window.location.href = window.location.href;
}