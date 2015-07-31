<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Cache-control" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="shortcut icon"
	href="http://i3.sdlcdn.com/img/icons/favicon.ico">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="<c:url value ="/resources/css/style.css" /> ">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<c:url value ="/resources/js/bootstrap-filestyle-min.js"/>"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script type="text/javascript">
	$(":file").filestyle();
	/* $(function() {
	    $( "#loginbox" ).draggable();
	  }); */
	  $(function() {  
	  $('.container').sortable({
	      revert: true
	    }); 
	  $( ".panel" ).resizable();
	  $('#sDate, #eDate').datepicker();
	  $('#sDate, #eDate').datepicker( "option", "dateFormat", 'yy-mm-dd' );
	  });
</script>
<!--         <script src="js/script.js"></script> -->
</head>
<body style="background-color: #E5E5E5">
	<div class="header-topbar">
		<div class="logo">
			<img src="<c:url value="/resources/img/snapdeal-logo.png" />"
				width="360" height="50" />
		</div>

		<div class="header-right-content">
			<span class="divider">&nbsp&nbspListing Recommendation</span>
		</div>
	</div>
	<div class="container" style="margin-top: 25px">
		<div class=" myAlert noDisplay alert alert-error alert-dismissible" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		  <div class="alertMsg"></div>
		</div>
		<div id="loginbox"
			class="mainbox col-md-6 col-sm-12 col-sm-offset-0">
			<div class="panel panel-info">
				<div class="whiteHeader panel-heading">
					<div class="panel-title">
						<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp;&nbsp;Listing Recommendation
					</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">
					
						<div style="margin-bottom: 25px" class="col-md-12 form-group">
							<label for="subCat">Select SubCategory</label> <select
								id="subCat" name="subcat_id" class="form-control">
								<c:forEach var="subcatmap" items="${subcatmap}">
									<option value="${subcatmap.value}">${subcatmap.key}</option>
								</c:forEach>
							</select>
						</div>
						
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label for="title"> Title</label><input class="form-control" name="title" type="text"
								id="title" placeholder="Product Recommendation"> 
						</div>
						
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label for="description"> Description </label><input class="form-control" name="description" type="text"
								id="description" placeholder="Start selling these best selling..."> 
						</div>
						
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label class="checkbox-inline"> <input name="selectedoption" type="radio"
								id="inlineCheckbox1" value="BestSelling" checked="checked"> <b>Best
									Selling</b>
							</label>
						</div>
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label class="checkbox-inline"> <input type="radio" name="selectedoption"
								id="inlineCheckbox1" value="Trending" disabled> <b>Trending</b>
							</label>
						</div>
						
						<div style="margin-bottom: 25px" class="col-md-4 form-group">
							<label class="checkbox-inline"> <input name="selectedoption1" type="radio"
								id="noBrand" value="1" checked="checked"> <b>No Brand Logic</b>
							</label>
						</div>
						<div style="margin-bottom: 25px" class="col-md-4 form-group">
							<label class="checkbox-inline"> <input type="radio" name="selectedoption1"
								id="includeBrand" value="2"> <b>Include Only These Brands</b>
							</label>
						</div>
						<div style="margin-bottom: 25px" class="col-md-4 form-group">
							<label class="checkbox-inline"> <input type="radio" name="selectedoption1"
								id="excludeBrand" value="3"> <b>Exclude These Brands</b>
							</label>
						</div>

						<div style="margin-bottom: 25px" class="col-md-12 form-group">
							<input class="form-control" name="brand" type="text"
								id="brand" placeholder="Brand Ids...." style="display:none"> 
						</div>
						
						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->
							
							<div class="col-sm-4 controls">
								<button id="btn-login" onclick="getDetails()" class="myButton">Submit
								</button>
								<span class="ls-loader"></span>
							</div>
							
							<div class="col-sm-4 controls">
								<a id="btn-download_email" href="/ListingRecommendation/DownloadEmailNot" class="myButton noDisplay"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>Email Template
								</a>

							</div>
							<div class="col-sm-4 controls">
								<a id="btn-download_mobile" href="/ListingRecommendation/DownloadMobileNot" class="myButton noDisplay"><span class="glyphicon glyphicon-save" aria-hidden="true"></span>Mobile Template
								</a>

							</div>
						</div>

				</div>
			</div>
		</div>
		
		
		<div id="loginBox"
			class="mainbox col-md-6 col-sm-12 col-sm-offset-0">
			<div class="panel panel-info">
				<div class="whiteHeader panel-heading">
					<div class="panel-title">
						<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp;&nbsp; Report of conversions
					</div>

				</div>

				<div style="padding-top: 30px" class="panel-body">
					<form id = "uploadForm"  method="post" enctype="multipart/form-data">
						<div style="margin-bottom: 25px" class="col-md-12 form-group">
							<input class="form-control filestyle" type="file" id = "brandImage" data-iconName="glyphicon-inbox" name="uploadFile"/>
						</div>
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label for="sDate"> StartDate</label><input class="form-control" name="sDate" type="text"
								id="sDate" placeholder="YYYY-MM-DD"> 
						</div>
						<div style="margin-bottom: 25px" class="col-md-6 form-group">
							<label for="eDate"> EndDate</label><input class="form-control" name="eDate" type="text"
								id="eDate" placeholder="YYYY-MM-DD"> 
						</div>
						<button type="submit" class="myButton" style="margin-top:25px">Download Report
						</button>
						<span class="ls-loader" style="margin-top: 18px;"></span>
					</form>
				</div>
			</div>
		</div>
	</div>

<script src="<c:url value ="/resources/js/listing.js" />">
    
</script>
</body>
</html>