var _app_path_ = '/arm-taxi-operator/';

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

var myLoaderStreet = function(param,success,error){
            var q = param.q || '';
            if (q.length <= 2){return false}
            $.ajax({
                url: _app_path_+'json/streets',
                dataType: 'json',
                data: {
                    q: q
                },
                success: function(data){
                    var items = $.map(data, function(item,index){
                        return {
                            id: index,
                            text: item.text
                        };
                    });
                    success(items);
                },
                error: function(){
                    error.apply(this, arguments);
                }
            });
        }
        
        

var myLoaderHouse1 = function(param,success,error){
            var q = param.q || '';
            var s = $('#addrFrom').value();
            //if (q.length <= 2){return false}
            $.ajax({
                url: _app_path_+'json/houses',
                dataType: 'json',
                data: {
                    q: q
                },
                success: function(data){
                    var items = $.map(data, function(item,index){
                        return {
                            id: index,
                            text: item.text
                        };
                    });
                    success(items);
                },
                error: function(){
                    error.apply(this, arguments);
                }
            });
        }        