$(function() {
	datepicker();
});

function datepicker() {
	$('.datetimepicker').each(function() {
		var selector = $(this);
		var span = selector.siblings('span');
		selector.datepicker({
			dateFormat : 'd.mm.yy'
		});
		span.click(function(e){
			e.preventDefault();
			selector.focus();
		});
	});
}