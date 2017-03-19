<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>    



<html lang="ru">
<head>
<title>Редактирование заказа</title>

<%@include file="styles.jsp" %>
<%@include file="frameworks_scripts.jsp" %>

<script type="text/javascript" src="<c:url value="/js/jquery-1.10.0.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/jquery.easyui.min.js"/>" ></script>
<script type="text/javascript" src="<c:url value="/js/dialog_edit_order.js"/>" ></script>

<!--
форма редактирования - часть html кода без обработки jsp.... или с обработкой
js скрипты - им нужен только путь - его реализовать глобальной переменной
-->


</head>
<body>

<form:form id="dialog_edit_form" method="POST"  modelAttribute="edited_order" action="/arm-taxi-operator/order/save" >
 

  
  <p>Телефон: <form:input path="phone" class="easyui-textbox" data-options="" style="width:100px"/> 
           Телефон 2: <form:input path="connectPhone"  class="easyui-textbox" data-options="" style="width:100px"/> 
	   Пассажир: <form:input path="clientName" class="easyui-textbox" data-options="" style="width:120px"/> 
	</p>
	<b>Откуда -> Куда:</b>
        
        
        <c:url var="address_request_url" value="/json/streets"/> 
        
        
	<p>
           <form:input path="addrFrom"  prompt="Улица" class="easyui-combobox" data-options="mode:'remote', loader: loaderStreet, method:'get',valueField:'text',textField:'text'" style="width:200px"/> 
	   <form:input path="houseFrom"  prompt="Дом" class="easyui-combobox" data-options="mode:'remote', loader: loaderHouseForStreetFrom, method:'get',valueField:'text',textField:'text'" style="width:100px"/> 
	   <form:input path="flatFrom" prompt="Квартира" class="easyui-textbox" data-options="" style="width:60px"/> 
	   <form:input path="addrFromName" prompt="Место" class="easyui-textbox" data-options="" style="width:120px"/> 
                    
	</p>
	<p>
                <c:forEach var="i" begin="1" end="4">
                <div id="div_dop_address${i}"   <c:if test="${i==3}">  style="display:none"  </c:if>>
		<form:input path="Interpointaddress${i}" prompt="Улица" class="easyui-combobox" data-options="mode:'remote', loader: loaderStreet, method:'get',valueField:'text',textField:'text'" style="width:200px"/> 
		<form:input path="interpointhouse${i}"  prompt="Дом" class="easyui-combobox" data-options="mode:'remote', loader: loaderHouseForStreet${i}, method:'get',valueField:'text',textField:'text'" style="width:100px"/> 
		<form:input path="interpointname${i}" prompt="Место" class="easyui-textbox" data-options="" style="width:120px"/>
                <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="onRemoveAddressClick(${i})"></a>
                        </div>
                    </c:forEach>
                    
                    

                    
                    <form:input path="addrTo"  prompt="Улица" class="easyui-combobox" data-options="mode:'remote', loader: loaderStreet, method:'get',valueField:'text',textField:'text'" style="width:200px"/> 
                    <form:input path="houseTo"  prompt="Дом" class="easyui-combobox" data-options="mode:'remote', loader: loaderHouseForStreetTo, method:'get',valueField:'text',textField:'text'" style="width:100px"/> 
	   <form:input path="addrToName" prompt="Место" class="easyui-textbox" data-options="" style="width:120px"/>
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="onAddAddressClick(0)"></a>
	</p>	
        <p>
            <br><input type="CheckBox" id="check_show_bnal" onclick="onShowBnalClick()">БНАЛ:</input>
        </p>
        
	
        
        <div id="pBnal" class="easyui-panel" style="padding:00px;background:#fafafa;width:305px;"
        data-options="collapsible:true, closed:true">

                <input id="OrgId" class="easyui-combobox" name="language" style="width:300px;" data-options="
                    url:'<c:url value="/json/organizations"/>',
                    method:'get',
                    valueField: 'id',
                    textField: 'text',
                    label: 'Организация',
                    labelPosition: 'top'
                ">
                
                <input id="OrgDetailId" class="easyui-combobox"  style="width:300px;" data-options="mode:'remote', loader: loaderOrganizationDetail, method:'get',valueField:'id',textField:'text',label: 'Сотрудник/отдел организации', labelPosition: 'top'">
                <input id="OrganizationSubDetailId" class="easyui-combobox" style="width:300px;" data-options="mode:'remote', loader: loaderOrganizationSubDetail, method:'get',valueField:'id',textField:'text',label: 'Сотрудник', labelPosition: 'top'">

        </div>
                    
           <select class="easyui-combobox" name="state"  style="width:100%;" data-options=" multiple:true,label: 'Доп опции:',labelPosition: 'top'" >
                <option value="0001">Отдаленный район(+20)</option>
                <option value="0010">Багаж(+30)</option>
                <option value="0100">Животное(+20)</option>
                <option value="1000">Плохая дорого/Грунт(+20)</option>
           </select>
    
  
  
  <!--<input id="form_submit_button"  type="button" value="Сохранить">
  <input id="form_submit_merge_button"  type="button" value="Merge"> -->
  <input type="submit"  value="Сохранить submit"> 
 
</form:form>

  
    

</body>
</html>

