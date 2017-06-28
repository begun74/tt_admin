var errAjax = 'Error connect to AJAX server!';


var Monitor = {
		result: "",
		id:  1,
		
		progress: function (id) {
				
				var data = {};
				
				$.ajax({
					type : "GET",
					url : "progress?id="+id,
					//timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=UTF-8',
					success : function(data) 
					{
						
						if(data.length > 0) {
							$("#"+id +".monitorProgress").show();
							for (i=0;i<data.length;i++) 
								$("#"+id +".monitorProgress").text(""+data[i]+"<br/>");
							//$("#"+id +".monitorProgress").text(""+data);
						}
						else
							$("#"+id +".monitorProgress").hide();
						//alert("'"+data+"'");
					},
					error : function(e) {
						$("#"+id +".monitorProgress").hide();
						//display(e);
					}
				});
	
		},
		errors: function() {
				var data = {};
				
				$.ajax({
					type : "GET",
					url : "monitorErrors",
					timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=UTF-8',
					success : function(data) 
					{
						
						if(data.length > 0) {
							$(".monitorErrors").show();
							for (i=0;i<data.length;i++) 
								$(".monitorErrors").append(""+data[i]+"<br/>");
						} else
							$(".monitorErrors").hide();
					},
					error : function(e) {
						//$("#"+id +".monitorProgress").hide();
						//display(e);
					}
				});
		}
}

$(document).ready(function(){
    var progress = 0;
	
    
    
//========== Мониторинг Загрузки файлов ==========================	
    $(".monitorProgress").each(function() {
    	var id = this.id;
    	setInterval( function() { 
			 Monitor.progress(id);
			 Monitor.errors();
		} , 1000);
			
	});

    $(".monitorProgress").click(function() {
    	clearMonitorProgress(this.id);
	});
    
    
    $(".monitorErrors").click(function() {
    	clearErrors();
	});
//======================================================    
    	
    
});





(function (d, w, c) {


	/*
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
    */
})(document, window, "yandex_metrika_callbacks");

function delObject(clazz,id,act)
{
	if(confirm('Удалить запись?'))location.href='admin/delObject?id='+id+'&clazz='+clazz+'&act='+act;
}

function delPhotoFile(code, file_number)
{
	if(confirm('Удалить фото?'))
	{
		
		$.ajax({
			type : "GET",
			url : "delPhotoFile?code="+code+"&file_number="+file_number,
			success : function(data) 
			{
				if(data === 'OK')
				{
					$('#div_pn'+file_number).append('<div class="" ></div>')
					$('#photoNomenclature'+file_number).fadeOut('slow');
					$('#btn-delete'+file_number).fadeOut('slow');
				}
			},
		});

	}
}

function checkAllCols(amount){
	
    $('#allCols').click(function(){
    	
    	if($('#allCols').attr("checked"))
    	{
	    	for (i = 0; i <= amount; i++) {
	            $('#col'+i).attr("checked","checked");
	        }
    	}
    	else 
    	{
	    	for (i = 0; i <= amount; i++) {
	            $('#col'+i).attr("checked","");
	        }
    	}
	});

}


function clearErrors() {

	$.ajax({
		type : "GET",
		url : "clearMonitorErrors",
		timeout : 100000,
		success : function(data) 
		{
			
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
}
	

function clearMonitorProgress(id) {

	$.ajax({
		type : "GET",
		url : "clearMonitorProgress?id="+id,
		timeout : 100000,
		success : function(data) 
		{
			
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
}

function checkboxAny(flag,clazz)
{
	/* flag - boolean */
	//alert(flag,clazz);
	if(flag)
		$('.'+clazz).attr("checked","checked");
	else
		$('.'+clazz).attr("checked","");

}


