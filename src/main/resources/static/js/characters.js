$(function(){
	buttonEvents();
});

function buttonEvents() {
	$(".btn-charsheet").off().click(function(){
		var btn = $(this);
		var url = btn.attr("data");
		loadFragment(url);
	});
}

function loadFragment(url) {
	$.ajax({
		url : url,
		method : 'GET',
		success : function( data, status, xhr) {
			displayPopup(data, status, xhr);
		},
		error : function() {
			markAsError(btn);
		}
	});
}

function displayPopup(data, textstatus, jqxhr) {
	$("#fragments").replaceWith(data);
	$("#char").modal('show');
}

function markAsError(btn) {
	btn.addClass("bg-danger");
}