$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//Create ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateNoticeForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "powerCon",
		type : t,
		data : $("#formPowerConsumption").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			powerConSaveComplete(response.responseText, status);
		}
	});
}); 

function powerConSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#btnSave").val("");
	$("#formPowerConsumption")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#btnSave").val($(this).closest("tr").find('#hidNotIDUpdate').val());     
	$("#Id").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#accountNo").val($(this).closest("tr").find('td:eq(1)').text());
	$("#invoiceNo").val($(this).closest("tr").find('td:eq(2)').text());
	$("#userName").val($(this).closest("tr").find('td:eq(3)').text());
	$("#usedUnit").val($(this).closest("tr").find('td:eq(4)').text());
	
	
});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "powerCon",
		type : "DELETE",
		data : "id=" + $(this).data("Id"),
		dataType : "text",
		complete : function(response, status)
		{
			powerConDeletedComplete(response.responseText, status);
		}
	});
});

function powerConDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL of Power Consumption
function validateNoticeForm() {  
	
	//PowerConsumption Id
	if ($("#Id").val().trim() == "")  {   
		return "Insert an id.";  
		
	} 
	
	// Account No
	if ($("#accountNo").val().trim() == "")  {   
		return "Insert an Account No.";  
		
	}
	
		// Invoice No 
	if ($("#invoiceNo").val().trim() == "")  {   
		return "Insert an Invoice No.";  
		
	}
	
		// User Name 
	if ($("#userName").val().trim() == "")  {   
		return "Insert User Name.";  
		
	}
	
		// Used Unit  
	if ($("#usedUnit").val().trim() == "")  {   
		return "Insert Used Unit.";  
		
	}	

	return true; 
}
