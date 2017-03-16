var _app_path_ = '/arm-taxi-operator/';



function onShowBnalClick(){
if ($("#check_show_bnal").prop("checked")){
	$('#pBnal').panel('open',{	});    
}
else{
	$('#pBnal').panel('close',{	});    
}

}


function onAddAddressClick(index){
        //добавлять надо перед этим элементом
        //соответственно - пробежаться по всем кто выше и открыть одну скрытую
            console.log("onAddAddressClick");
            console.warn("onAddAddressClick");
            
            for (i=1;i<=4;i++){
                if (document.getElementById("div_dop_address"+i).style.display ==='none' ){
                    document.getElementById("div_dop_address"+i).style.display = 'block';
                    break;
                }
            }
            
            
            
}

function onRemoveAddressClick(index){
        //удалять надо именно этот элемент
            console.log("onAddAddressClick");
            console.warn("onAddAddressClick");
            document.getElementById("div_dop_address"+index).style.display = 'none';
            
            
            
}


var loaderOrganizationDetail = function( param,success,error){
            var q = param.q || '';
            var org_id = $(OrgId).val();
            //if (q.length <= 1){return false}
            $.ajax({
                url: _app_path_+'json/organization_details',
                dataType: 'json',
                data: {
                    q: q,
                    org_id: org_id
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
        };
        

var loaderOrganizationSubDetail   = function( param,success,error){
            var q = param.q || '';
            var org_detail_id = $(OrgDetailId).val();
            //if (q.length <= 1){return false}
            $.ajax({
                url: _app_path_+'json/organization_sub_details',
                dataType: 'json',
                data: {
                    q: q,
                    org_detail_id: org_detail_id
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
        };        

        


var loaderStreet = function(param,success,error){
            var q = param.q || '';
            if (q.length <= 1){return false}
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
        };
        
var loaderHouseForStreetFrom = function(param,success,error) {
    loaderHouseForStreet('#addrFrom', param,success,error);
};
var loaderHouseForStreetTo = function(param,success,error) {
    loaderHouseForStreet('#addrTo', param,success,error);
};
var loaderHouseForStreet1 = function(param,success,error) {
    loaderHouseForStreet('#inerpoint1_address', param,success,error);
};
var loaderHouseForStreet2 = function(param,success,error) {
    loaderHouseForStreet('#inerpoint2_address', param,success,error);
};
var loaderHouseForStreet3 = function(param,success,error) {
    loaderHouseForStreet('#inerpoint3_address', param,success,error);
};
var loaderHouseForStreet4 = function(param,success,error) {
    loaderHouseForStreet('#inerpoint4_address', param,success,error);
};

function loaderHouseForStreet(street_field_id, param,success,error){
            var q = param.q || '';
            var s = $(street_field_id).val();
            //if (q.length <= ){return false}
            $.ajax({
                url: _app_path_+'json/houses',
                dataType: 'json',
                data: {
                    q: q,
                    s: s
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
        };
        

        