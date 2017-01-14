$(function(){
	initialize();
});

function initialize(){
	buttonEvents();

}

function buttonEvents() {
	$(".btn-charsheet").off().click(function(){
		var btn = $(this);
		var url = btn.attr("data");
		window.location.href=url;
	});
}

function loadFragment(url) {
	$.ajax({
		url : url,
		method : 'GET',
		success : function(data) {
			displayPopup(data);
			initialize();
		},
		error : function() {
			markAsError(btn);
		}
	});
}

function displayPopup(data) {
	$("#fragments").replaceWith(data);
	$("#char").modal('show');
}

function markAsError(btn) {
	btn.addClass("bg-danger");
}
