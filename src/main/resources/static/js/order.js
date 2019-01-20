/**
 * 
 */
var file = {
	
		orderPhoto : function(val){
		$.ajax({
			url: "/photo/orderPhoto/"+val,
			success: function(data){
				if(data == "OK"){
					alert("Your order is registered successfuly");
				}
				else
					alert("An error occured during your order register !!");
			},
			error: function(){
				alert("An error occured during your order register !!");
			}
		});
	}
};

$(document).ready(function(){
	
	$(".btn-success").click(function(){
		file.orderPhoto($(this).find("input:first").val());
	});
});