Project = Project || {};

Project.Upload = {
		init: function () {
			
			$("#uploadImage").click(function() {
				Project.Upload.uploadImage();
			});
			
		}
,
		uploadImage: function() {
			var options = {
					url : "/mall/fileUpload!uploadCoverImage.do?ajax=yes",
					type : "POST",
					//dataType : "json",
					success : function(data) {
						alert("data="+data);
						if( data.result == 0) {
							$("#ConverImgSrc").attr("src",data.relativeurl);
							$("#coverimagehidden").val(data.relativeurl);
							
						} else {
						alert(data.message);
						}
					},
					error : function(e) {
						alert("o,error.");
					}

				};
				alert('before upload submit');
				$("#uploadForm").ajaxSubmit(options);
			
		}
}

$(function() {
	Project.Upload.init();
});