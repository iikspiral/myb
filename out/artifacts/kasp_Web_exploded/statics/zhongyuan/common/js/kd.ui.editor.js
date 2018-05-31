function initScript(urls) {
	if(!urls || urls.length == 0) return;
	
	for (var i = urls.length - 1; i >= 0; i--) {
		if(hasScript(urls[i])) continue;
		
		sObj = document.createElement("script"); sObj.setAttribute("type", "text/javascript"); sObj.setAttribute("src", urls[i]);
		document.getElementsByTagName("head")[0].appendChild(sObj);
	}
}

function hasScript(url) {
	if(!url) return true;
	
	var flag = false;
	$("script").each(function(){
		if($(this).attr("src") && $(this).attr("src").indexOf(url) >= 0) {
			flag = true; return false;
		}
	});
	return flag;
}