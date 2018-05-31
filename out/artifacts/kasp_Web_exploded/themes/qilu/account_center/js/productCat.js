var Project = Project || {};
Project.Basic = {init:function () {
	var self = this;
	$("#project_org").change(function () {
		self.changeCity();
	});
	self.initSelect();
}, initSelect:function () {
	var self = this;
	jQuery("#project_org").val(jQuery("#project_org").attr("data-value"));
	self.changeCity();
}, changeCity:function () {
	var selectedOrg = $("#project_org").children("option:selected").val();
	var selectedOrgText = $("#project_org").children("option:selected").text();
	$("#project_orgname").val(selectedOrgText);
	if (selectedOrg != undefined || selectedOrg != -1) {
		$.get("/mall/hbv1bond!queryPmCat.do", {ajax:"yes", orgid:selectedOrg}, function (data) {
			var result = $.parseJSON(data);
			if (result.flag == 1) {
				var cities = [];
				cities = result.items;
				var options = " ";
				for (var c in cities) {
					options += "<option value='" + cities[c].p1_prod_cat_id + "' >" + cities[c].p1_prod_cat_name + "</option>";
				}
				$("#project_cat").append(options);
				jQuery("#project_cat").val(jQuery("#project_cat").attr("data-value"));
			}
		});
	}
}};
$(function () {
	Project.Basic.init();
});

