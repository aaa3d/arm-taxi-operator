function onShowInterpointClick(){
if ($("#check_show_interpoints").prop("checked")){
	$('#p2').panel('open',{	});    
}
else{
	$('#p2').panel('close',{	});    
}

}



function onShowBnalClick(){
if ($("#check_show_bnal").prop("checked")){
	$('#pBnal').panel('open',{	});    
}
else{
	$('#pBnal').panel('close',{	});    
}

}