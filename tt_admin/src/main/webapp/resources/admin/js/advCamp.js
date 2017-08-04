var app = angular.module("advCamp", []); 
var CLEditor;
$(document).ready(function(){
	
	/*$("#save").attr("checked","checked");*/
	
	/*  Выделяем строку в таблице */
	$( ".table_row" ).click(function(form, id, name, fromDate,toDate) {
		$(this).addClass("selected").siblings().removeClass("selected");
		
		//toEdit('formAdvCamp',$(this).attr("id"), $(this).children('#td_name').text() , $(this).children('#td_fromDate').text(), $(this).children('#td_toDate').text());
		
	});
	
	CLEditor = $("#text_area").htmlarea();

});


app.controller('advCamp_Ctrl', function($scope, $http) {
	$scope.tails=[];
	$scope.updTailsTable = function () {
		$http({
	        method : "GET",
	        url : "getTempTails"
	    }).then(function success(response) {
	        $scope.tails = response.data.ok;
	        if($scope.tails.length > 0)
	        	clearInterval(refreshTailsTable);

	    }, function error(response) {
	        $scope.tails = response.statusText;
	    });
	};
	
	
});


function toEdit(form, id, name, txt, fromDate,toDate, active, txt_to_slider) {
	//alert(text)
	
	document.forms[form].id.value = id;
	document.forms[form].name.value = name; 
	$("#text_area").htmlarea('html',txt);
	document.forms[form].fromDate.value = fromDate; 
	document.forms[form].toDate.value = toDate; 
	document.forms[form].active.checked = active;
	document.forms[form].text_to_slider.value = txt_to_slider; 

}


function delAdvCamp(id) {
	if(confirm('Удалить ?'))
		window.location = 'content?act=3&id='+id;
}