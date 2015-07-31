
$('#includeBrand, #excludeBrand, #noBrand').change(function() { 
	if($('#includeBrand, #excludeBrand').is(':checked')){
		$('input[name=brand]').slideDown("fast");
	}else{
		$('input[name=brand]').slideUp("fast");
	}
});


	function getDetails(){
    	var subCatId = $('#subCat').val();
    	var subCatName = $('#subCat :selected').text();
    	var checkType = $('#inlineCheckbox1').val();
    	var title = $('input[name=title]').val();
    	var description = $('input[name=description]').val();
    	var brandRadio = $('input[name=selectedoption1]:checked').val();
        var brandIds = "";
        if(brandRadio=="2" || brandRadio == "3"){
        	brandIds = $('input[name=brand]').val();
        	if(brandIds.trim().length < 1){
        		alert("please enter brand ids");
        		return ;
        	}
        }
        $('.controls > span').css('display', 'inline-block');
    	$('#btn-login').prop('disabled', true);
        $.ajax({
        	method : "POST",
			data : {
				subcat_id: subCatId,
				subCatName: subCatName,
        		selectedoption: checkType,
        		title:title,
        		description: description,
        		brandIds: brandIds,
        		brandRadio: parseInt(brandRadio)
			},
            url: "getRecommendation",
            success: function (data) {
            	console.log(data);
            	$('.controls > span').hide();
            	$('#btn-login').prop('disabled', false);
                if(data.success){
                	$('#btn-download_email, #btn-download_mobile').show();
                	$('#btn-download_mobile').prop('href','/ListingRecommendation/DownloadMobileNot?mobilePath='+encodeURIComponent(data.mobilePath));
                	$('#btn-download_email').prop('href','/ListingRecommendation/DownloadEmailNot?emailPath='+encodeURIComponent(data.emailPath));
                }else{
                	$('.alert').show();
                	$('.alert div.alertMsg').html('<strong>Warning!</strong>&nbsp&nbsp'+data.message);
                }
            },
            error: function(response, status, error){
            	$('.alert').show();
            	$('.alert div.alertMsg').html('<strong>Error!</strong> &nbsp&nbspSome Error occured, try after some time');
            	$('.controls > span').hide();
            	$('#btn-login').prop('disabled', false);
            },
            complete: function(xhr, status){
            }
        });
	}
$(document).ready(function () {	
	$("#uploadForm").submit(function(event){
		//disable the default form submission
        event.preventDefault();
        //grab all form data  
        $('#uploadForm> span').css('display', 'inline-block');
        $('#uploadForm>button').prop('disabled', true);
        var sDate = $('#sDate').val();
        var eDate = $('#eDate').val();
        $.ajax({
            url: 'upload?sDate='+sDate+'&eDate='+eDate,
            type: 'POST',
            data:new FormData($(this)[0]),
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
            	console.log(data);
            	$('.alert').show();
            	if(data == 0){
            		$('.alert div.alertMsg').html('<strong>Warning!</strong> &nbsp&nbspEmpty Response');
            	}
            	else if(data > 0){
            		$('.alert div.alertMsg').html('<strong>Success!</strong> &nbsp&nbspSuccessfully downloaded');
            	}else{
            		$('.alert div.alertMsg').html('<strong>Error!</strong> &nbsp&nbspSome Error occured, try after some time');
            	}
            	$('#uploadForm> span').hide();
            	window.location.href = 'resources/downloads/ConversionReport.xlsx';
            	$('#uploadForm>button').prop('disabled', false);
            },
            error: function(response, status,error){
            	$('.alert').show();
            	$('.alert div.alertMsg').html('<strong>Error!</strong> &nbsp&nbspSome Error occured, try after some time');
            	$('#uploadForm> span').hide();
            	$('#uploadForm>button').prop('disabled', false);
            }
        });

        return false;
	});
});

        