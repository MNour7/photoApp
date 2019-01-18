/**
 * 
 */
var add = {
	showChild : function(val){
		if(val === "solo")
			$("#chl_sel").show();
		else
			$("#chl_sel").hide();
	},
	
	loadClass : function(val){
		$.ajax({
			url: "/photographer/loadClasses/"+val,
			success: function(data){
				let tab = data.split("|");
				$("#cls").empty();
				$("#cls").append('<option value=""></option>');
				
				for(let i = 0; i < tab.length; i++){
					let subTab = tab[i].split("=");
					$("#cls").append('<option value="'+subTab[0]+'">'+subTab[1]+'</option>');
				}
			}
		});
	},
	
	loadChild : function(val){
		$.ajax({
			url: "/photographer/loadChilds/"+val,
			success: function(data){
				let tab = data.split("|");
				$("#chl").empty();
				$("#chl").append('<option value=""></option>');
				
				for(let i = 0; i < tab.length; i++){
					let subTab = tab[i].split("=");
					$("#chl").append('<option value="'+subTab[0]+'">'+subTab[1]+'</option>');
				}
			}
		});
	}
};

$(document).ready(function(){
	
	$("#sch").change(function(){
		if($(this).val() !== ""){
			add.loadClass($(this).val());
		}			
	});
	
	$("#cls").change(function(){
		if($(this).val() !== ""){
			add.loadChild($(this).val());
		}			
	});
	
	$("#type").change(function(){
		
		add.showChild($(this).val());
			
	});
	
});