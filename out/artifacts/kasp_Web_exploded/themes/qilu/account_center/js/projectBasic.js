var Project = Project || {};
Project.Basic = {init:function () {
	var self = this;
	$("#provinceSelect").change(function () {
		self.changeCity();
	});
	self.initSelect();
}, initSelect:function () {
	var self = this;
	jQuery("#provinceSelect").val(jQuery("#provinceSelect").attr("data-value"));
	self.changeCity();
}, changeCity:function () {
	$("#citySelect").html("<option value='-1'>--\u8bf7\u9009\u62e9--</option>");
	var selectedProvince = $("#provinceSelect").children("option:selected").val();
	var selectedProvinceText = $("#provinceSelect").children("option:selected").text();
	if (selectedProvince != undefined || selectedProvince != -1) {
		$.get("/mall/provincedict!getCitiesByProvince.do", {ajax:"yes", ref_key:selectedProvince}, function (data) {
			var result = $.parseJSON(data);
			if (result.flag == 1) {
				var cities = [];
				cities = result.items;
				var options = " ";
				for (var c in cities) {
					options += "<option value='" + cities[c].dict_key + "' >" + cities[c].dict_value + "</option>";
				}
				$("#citySelect").append(options);
				jQuery("#citySelect").val(jQuery("#citySelect").attr("data-value"));
			}
		});
	}
}};
$(function () {
	Project.Basic.init();
});

