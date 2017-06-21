

var errAjax = 'Error connect to AJAX server!';

$(document).ready(function(){
	getCurrency("https://www.nbrb.by/API/ExRates/Rates/840?ParamMode=1",$('#usd_840'),'$ ');
	getCurrency("https://www.nbrb.by/API/ExRates/Rates/978?ParamMode=1",$('#eur_978'),'€ ');
	
	gismeteoWeather();
	
    $(".waiting").click(function() {
    	/*setTimeout(function() {
    		$('#productFilterWait').modal('show');}
		,700);
    	*/
    	$('#productFilterWait').modal('show');
    	setTimeout(function() {
		        	document.product_filter.submit();}
    	,100);
    });
    
});


(function (d, w, c) {
	        (w[c] = w[c] || []).push(function() {
	            try {
	                w.yaCounter44908546 = new Ya.Metrika({
	                    id:44908546,
	                    clickmap:true,
	                    trackLinks:true,
	                    accurateTrackBounce:true
	                });
	            } catch(e) { }
	        });

	        var n = d.getElementsByTagName("script")[0],
	            s = d.createElement("script"),
	            f = function () { n.parentNode.insertBefore(s, n); };
	        s.type = "text/javascript";
	        s.async = true;
	        s.src = "https://mc.yandex.ru/metrika/watch.js";

	        if (w.opera == "[object Opera]") {
	            d.addEventListener("DOMContentLoaded", f, false);
	        } else { f(); }
   })(document, window, "yandex_metrika_callbacks");
	
/*	<noscript><div><img src="https://mc.yandex.ru/watch/44908546" style="position:absolute; left:-9999px;" alt="" /></div></noscript> */


/* Gismeteo Informer (begin) */
var gismeteoWeather = function() {
    	var
		    d = this.document,
		    o = this.navigator.userAgent.match(/MSIE (6|7|8)/) ? true : false,
		    s = d.createElement('script');

			s.src  = 'https://www.gismeteo.by/informers/simple/install/';
			s.type = 'text/javascript';
			s[(o ? 'defer' : 'async')] = true;
			s[(o ? 'onreadystatechange' : 'onload')] = function() {
					    try {new GmI({
					        slug : 'd4e91d48c850d2242f9e2b659b2b0b26',
					        type : '88x31-1',
					        city : '4248',
					        lang : 'ru'
					    })} catch (e) {}
			}

		d.body.appendChild(s);
}

var getCurrency = function(url_, currency, text) {
	
	var arrData = {};
	var cookie_ = $.cookie(currency.attr('id'));

	$.get(url_, function(data) 
	{
			//alert('1  ' + currency.attr('id')+'  '+cookie_);
		$.cookie(currency.attr('id'),data);
		//alert(cookie_['Cur_Name']);
		//$.cookie('currency_usd',data['Cur_OfficialRate']);
	}).done(function(data) {
	   // alert( "2" );
		currency.html(text+data['Cur_OfficialRate']);
		currency.attr('title',data['Cur_Name']);
	}).fail(function() {
	    //alert( "3" );
	});
}

function increment(obj, value) {
	
	alert(obj+'  '+value);
}

function decrement(obj) {
	return --obj.value;
}

var digitMounthArray = {
		0:'01',
		1:"02",
		2:"03",
		3:"04",
		4:"05",
		5:"06",
		6:"07",
		7:"08",
		8:"09",
		9:"10",
		10:"11",
		11:"12"
}


var Product = {
		id: 0,
		result: 0,

		getDetail: function () {
				
				var data = {};
				data['id'] = Product.id;
				
				$.ajax({
					type : "GET",
					url : "productDetail?id="+Product.id,
					//timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=utf-8',
					success : function(data) 
					{
						$(".size_info").empty();
						Product.tailsArr = [];
						for(var i=0;i< data.length; ++i) {
							$(".size_info").append("<li>"+data[i].size+" - "+data[i].amountTail+" "+Product.localItemName+"</li> ");
							$(".sizes").append($('<option>', {
							    value: data[i].amountTail,
							    text: data[i].size,
							    id: data[i].id
							}));
						}
					},
					error : function(e) {
						
						display(e);
					}
				});
	
		},

		toOrder: function(size, amount) {

				var arrData = {};
				arrData['id'] = Product.id;
				
				
				var jqXHR = $.ajax({
					type : "GET",
					url : "toOrder?id="+Product.id+"&size="+size+"&amount="+amount,
					//timeout : 10000,
					async: false,
					dataType: 'json',
					data : JSON.stringify(arrData),
					contentType: 'application/json; charset=utf-8',
					success : function(data) {
						
					},
					error : function(e) {
						display(e);
					}
				});
				
				return jqXHR.responseText;
		},

		toOrder2: function(id_tails, amount) {

			var arrData = {};
			arrData['id'] = Product.id;
			
			
			var jqXHR = $.ajax({
				type : "GET",
				url : "toOrder2?id_tails="+id_tails+"&amount="+amount,
				//timeout : 10000,
				async: false,
				dataType: 'json',
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) {
					
				},
				error : function(e) {
					display(e);
				}
			});
			
			return jqXHR.responseText;
		},
		
		delOrder: function(id) {

			var data = {};
			

			$.ajax({
				type : "GET",
				url : "delOrder?id="+id,
				//timeout : 10000,
				data : JSON.stringify(data),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					
					/*$('.add_product_alert').show();*/
				},
				error : function(e) {
					
					display(e);
				}
			});
		}

};


var Order = {
		id: 0,
		
		getOrderItems: function(id,row) {
			var arrData = {};
			arrData['id'] = id;
			
			$('.tableDisplayOrderItems').empty();
			
			$.ajax({
				type : "GET",
				url : "getOrderItems?id="+id,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					Order.displayOrderItems(data,row);
				},
				error : function(e) {
					
					display(e);
				}
			});
		},
		
		displayOrderItems: function(arrData, row) {
			var numCols = 1;
			
			
			$('.tableDisplayOrderItems').empty();
			
			$.each(arrData, function(i) {
				  if(!(i%numCols)) tRow = $('<tr>');
				  
				  var destrDate = new Date(arrData[i].destruction_date);
				  
				  var destr_date = arrData[i].destruction_date != null?destrDate.getDate() +'.'+ digitMounthArray[destrDate.getMonth()] +'.'+destrDate.getFullYear()+'  '+destrDate.getHours()+":"+destrDate.getMinutes()+":"+destrDate.getSeconds() : "";

				  tCell = $('<td>').html(arrData[i].code);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].name);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].model);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].article);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].size);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].amount);
				  tRow.append(tCell);
				  
				  if(arrData[i].destruction_date != null)
					  tCell = $('<td>').html('<input type="checkbox" checked="checked" class="statusOrderItem" onClick="Order.setReadyOrderItem(this)" id='+arrData[i].id+'>  '+destr_date+'</input>');
				  else
					  tCell = $('<td>').html('<input type="checkbox" class="statusOrderItem" onClick="Order.setReadyOrderItem(this)" id='+arrData[i].id+'>  '+destr_date+'</input>');
				  
				  tRow.append(tCell);				  

				  $('.tableDisplayOrderItems').append(tRow);
			});
			
			//alert('row - ' +row);
			
		},
		
		closeOrder: function(id) {
			var arrData = {};
			arrData['id'] = id;
			
			$.ajax({
				type : "GET",
				url : "closeOrder?id="+id,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					//alert('Заказ №' +data['id'] + ' выполнен!')
					window.location.reload();
				},
				error : function(e) {
					
					display(e);
				}
			});
		},
		
		setReadyOrderItem: function(chekbox) {
			
			//alert('status - '+chekbox.checked);
			
			var arrData = {};
			arrData['id'] = chekbox.id;
			
			$.ajax({
				type : "GET",
				url : "setReadyOrderItem?id="+arrData['id']+"&status="+chekbox.checked,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					if(chekbox.checked)
					{
						//var destrDate = new Date(data);
						//var destr_date = data != null?destrDate.getDate() +'.'+ digitMounthArray[destrDate.getMonth()] +'.'+destrDate.getFullYear()+'  '+destrDate.getHours()+":"+destrDate.getMinutes()+":"+destrDate.getSeconds() : "";
						//$(chekbox).text(destr_date);
					}
					
					//alert(destr_date);
				},
				error : function(e) {
					
					display(e);
				}
			});
		},
		
		printOrder: function(orderId) {
			
			window.location="eshop/printOrder?orderId="+orderId;
		}
}

function clearSessErrors(error_name) {

	$.ajax({
		type : "GET",
		url : "clearSessErrors?error_name="+error_name,
		timeout : 100000,
		success : function() 
		{
			/*$("#compareItems").text(data.allItems);*/
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
}


function countsPerProdustsFilter(countName, tooltip) {
    /*          var $el = $(this);
    if( $el.data( "fetched" ) === undefined ) {
        $el.data( "fetched", true );
        $el.attr( "data-original-title", "<img src='images/wait.gif'/>" ).tooltip( "show" );
        $.ajax(
            {
                url: "ajax/tooltip_data.php",
                data:                                   {
                        lookup_id: $el.attr("lookup_id")
                    },
                success:
                    function( response ) {
                        $el.attr( "data-original-title", response );
                        if( $( "#" + $el.attr( "aria-describedby" ) ).is( ":visible" ) ) {
                            $el.tooltip( "show" );
                        }
                    },
                dataType: "html"
            }
            );
    } else {
        $(this).tooltip( "show" );
    } */


	$.ajax(
            {
                url: countName+"?id="+tooltip.attr("lookup_id"),
                success:
                    function( response ) {
                		tooltip.attr( "data-original-title", response );
                		tooltip.tooltip( "show" );
                    },
                dataType: "html"
            }
	);
	
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   //alert(charCode);
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function isPhoneNumberKey(evt)
{
	//var flag = true;
	var charCode = (evt.which) ? evt.which : event.keyCode;
	//var shiftPressed = (evt.which) ? evt.modifiers & Event.SHIFT_MASK : evt.shiftKey;
	
	//alert(event.keyCode);
	
	
	
	
	if (charCode != 189 && charCode != 43 && charCode > 31 && (charCode < 48 || charCode > 57))
		        return false;
	
	return true;

	/*
	var charCode = (evt.which) ? evt.which : event.keyCode;
	  var shiftPressed = (window.Event) ? e.modifiers & Event.SHIFT_MASK : e.shiftKey;

	  if ((shiftPressed && charCode == 187) || (charCode == 107))
	    return true;

	  else if ((charCode > 95) && (charCode < 106)) 
	    return true;
	  
	  else if (charCode > 31 && (charCode < 48 || charCode > 57) ) 
	    return false;
	  
	  else 
	    return true;
	    */
	  
}

function isNumberKeyDouble(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   //alert(charCode);
   if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46 )
      return false;

   return true;
}

function isNumberValue(value)
{
	var re = /[0-9999]/g;
	
	if (!re.test(value) || value==0)
	{	
	      return false;
	}

   return true;
}


function processSearchText(text) {
	//alert('processSearchText! - ' +text);
	window.location="find?text="+text;
}



