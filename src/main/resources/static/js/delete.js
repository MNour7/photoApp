/**
 * 
 */
var file = {
	
	deletePhoto : function(val){
		$.ajax({
			url: "/photo/deletePhoto/"+val,
			success: function(data){
				if(data == "OK"){
					$("#photo"+val).hide();
					alert("La photo a bien été supprimer");
				}
				else
					alert("Une Erreur est survenue lors de la suppression de la photo");
			},
			error: function(){
				alert("An error occured during deletion !!");
			}
		});
	}
};

$(document).ready(function(){
	
	$(".btn-danger").click(function(){
		file.deletePhoto($(this).find("input:first").val());
	});
});