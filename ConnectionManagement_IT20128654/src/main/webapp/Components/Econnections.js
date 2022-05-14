
$(document).ready(function()
{
$("#alertSuccess").hide();
$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateEconnectionForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidEconnectionIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "EconnectionsAPI", 
 type : type, 
 data : $("#formEconnection").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEconnectionSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onEconnectionSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divEconnectionsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidEconnectionIDSave").val(""); 
$("#formEconnection")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidEconnectionIDSave").val($(this).data("econnectionid")); 
		 $("#userName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#address").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#mainTown").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#postalCode").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#postNumber").val($(this).closest("tr").find('td:eq(4)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "EconnectionsAPI", 
		 type : "DELETE", 
		 data : "connectionNo=" + $(this).data("econnectionid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onEconnectionDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onEconnectionDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divEconnectionsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateEconnectionForm()
{
	// CODE
	
	// NAME
	if ($("#userName").val().trim() == "")
	{
	return "Insert  Name.";
}

// Address
	if ($("#address").val().trim() == "")
	{
	return "Insert  Address.";
}

// Main Town
	if ($("#mainTown").val().trim() == "")
	{
	return "Insert Town name.";
}

// Postal Code
	if ($("#postalCode").val().trim() == "")
	{
	return "Insert postal code.";
}


// Post Number------------------------
if ($("#postNumber").val().trim() == ""){
	
	return "Insert post number.";
}
	return true;
}