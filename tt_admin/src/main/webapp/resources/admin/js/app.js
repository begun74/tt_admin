var errAjax = 'Error connect to AJAX server!';


var MonitorLoads = {
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
						//$("#monitorLoadFile").empty();
						$(".monitorProgress").show();
						$(".monitorProgress").text(""+data);
						//alert(data);
					},
					error : function(e) {
						$("#monitorLoadFile").hide();
						//display(e);
					}
				});
	
		}
}

$(document).ready(function(){
    var progress = 0;
	
    
    
    $("#loadFileProvider").click(function() {
    	//alert("Click loadFileProvider");
    });
    
    //alert($(".progressBar").attr("id"));
    
    try {
		setInterval( function() { 
			//alert(this.name);
			$(".monitorProgress").text( MonitorLoads.progress($(".monitorProgress").attr("id")) );
		} , 1000);
    }
	catch(Error ) {}
    
    /*
    $(".progressBar").each(function() {
			setInterval( function() { 
				//alert(this.name);
				$(".monitorProgress").text( MonitorLoads.progress(this.id) );
			} , 1000);
	});
	*/
    
});





(function (d, w, c) {
	
	$("#monitorLoadFileProvider").hide();	/*
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
		url : "clearErrors",
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
	
function checkboxAny(flag,clazz)
{
	/* flag - boolean */
	//alert(flag,clazz);
	if(flag)
		$('.'+clazz).attr("checked","checked");
	else
		$('.'+clazz).attr("checked","");

}


