/**
 * 
 */
var add = {
	showChild : function(val) {
		if (val === "solo")
			$("#chl_sel").show();
		else
			$("#chl_sel").hide();
	},

	loadClass : function(val) {
		$.ajax({
			url : "/photographer/loadClasses/" + val,
			success : function(data) {
				let tab = data.split("|");
				$("#cls").empty();
				$("#cls").append('<option value=""></option>');

				for (let i = 0; i < tab.length; i++) {
					let subTab = tab[i].split("=");
					$("#cls").append(
							'<option value="' + subTab[0] + '">' + subTab[1]
									+ '</option>');
				}
			}
		});
	},

	loadChild : function(val) {
		$.ajax({
			url : "/photographer/loadChilds/" + val,
			success : function(data) {
				let tab = data.split("|");
				$("#chl").empty();
				for (let i = 0; i < tab.length; i++) {
					let subTab = tab[i].split("=");
					$("#chl").append(
							'<option value="' + subTab[0] + '">' + subTab[1]
									+ '</option>');
				}
			}
		});
	},

	checkValues : function() {

		let type = $("#type").val();
		let file = document.getElementById('file');
		if (type == "class") {
			if ($("#cls").val() != "" && file.files.length != 0)
				$("#form").submit();
			else
				alert("Please choose a class or select a photo");
		} else {
			if ($("#chl").val() != "" && file.files.length != 0)
				$("#form").submit();
			else
				alert("Please choose a child or select a photo");
		}
	}

};

$(document).ready(function() {

	$("#sch").change(function() {
		if ($(this).val() !== "") {
			add.loadClass($(this).val());
		}
	});

	$("#cls").change(function() {
		if ($(this).val() !== "") {
			add.loadChild($(this).val());
		}
	});

	$("#type").change(function() {
		add.showChild($(this).val());
	});

	$("#btn-add").click(function() {
		add.checkValues();
	});

});