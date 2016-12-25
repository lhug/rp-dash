$(function(){
	initialize();
});

function initialize(){
	buttonEvents();
	addIncBtnEvents();
	addDecBtnEvents();
	removeFragments();
	addItemEvents();
}

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

function addIncBtnEvents() {
	$(".inc").off().click(function(e){
		e.preventDefault();
		var btn = $(this);
		addValuePoint(btn);
		btn.blur();
	});
}

function addDecBtnEvents() {
	$(".dec").off().click(function(e){
		e.preventDefault();
		var btn = $(this);
		removeValuePoint(btn);
		btn.blur();
	});
}

function addValuePoint(btn) {
	var listItem = getBtnParent(btn);
	var max = btn.attr("data-max");
	var circle = getCircle(listItem, true);
	var input = listItem.find("input");
	var successful = changeValue(input, max, true);
	if (successful) {
		circle.attr("class", "fa fa-circle");
	}
}

function removeValuePoint(btn) {
	var listItem = getBtnParent(btn);
	var circle = getCircle(listItem, false);
	var input = listItem.find("input");
	var successful = changeValue(input, 0, false);
	if (successful) {
		circle.attr("class", "fa fa-circle-thin");
	}
}

function getBtnParent(btn) {
	return btn.closest(".list-group-item");
}

function getCircle(listItem, thin) {
	var cssClass = thin ? ".fa-circle-thin" : ".fa-circle";
	var circles = listItem.find(cssClass);
	return thin ? circles.first() : circles.last();
}

function changeValue(input, max, increase) {
	var value = Number(input.attr("value"));
	var ok;
	if (increase) {
		ok = value < max;
		value = ok ? value + 1 : value;
	} else {
		ok = value > 0;
		value = ok ? value - 1 : value;
	}
	setValue(input, value);
	return ok;
}

function setValue(input, value) {
	input.attr("value", value);
}

function removeFragments() {
	$(".modal.fade").on("hidden.bs.modal", function(){
		$(this).replaceWith('<div id="fragments" />');
	})
}

function addItemEvents() {
	$(".addItem").off().click(function(e){
		e.preventDefault();
		var btn = $(this);
		var option = btn.closest(".form-inline").find("select option:selected");
		var skill = option.attr("value");
		var listItem = btn.closest(".form-inline").next();
		addItem(skill, listItem);
		option.remove();
	});
}

function addItem(skill, list) {
	var template = 
		'<div class="list-group-item">' +
			'<label for="SKILLID">SKILLNAME</label>' +
			'<span class="pull-right">' +
				'<span class="btn-group btn-group-sm" role="group">' +
					'<button type="button" class="btn btn-default dec"><span class="fa fa-minus-circle"></span></button>' + 
					'<button type="button" class="btn btn-default inc" data-max="5"><span class="fa fa-plus-circle"></span></button>' +
				'</span>' +
				'<span th:each="int : ${#numbers.sequence(1,5)}" class="fa" th:classappend="${int le 5} ? \'fa-circle\' : \'fa-circle-thin\'"></span>' +
			'</span>' +
			'<input type="hidden" id="SKILLID" name="SKILLID" value="5" />' +
		'</div>';
	template = template.replace(/SKILLID/g, skill);
	template = template.replace(/SKILLNAME/g, skill);
	list.append(template);
}
