$(function() {
	initialize();
});

function initialize() {
	addValueButtonEvents();
	addItemEvents();
	addSpecializationEvents();
}

function addValueButtonEvents() {
	addIncBtnEvents();
	addDecBtnEvents();
}

function addSpecializationEvents() {
	addInputHandler();
}

function addInputHandler() {
	$('.spec').off().keyup(function(e) {
		e.preventDefault();
		var _this = $(this);
		var parent = getBtnParent(_this);
		var input = parent.find('input[type=hidden]');
		var value = _this.val();
		var name = input.attr('name');
		input.attr('name', name.replace(/\[.*\]/g, "[" + value + "]"));
	});
}

function addIncBtnEvents() {
	$(".inc").off().click(function(e) {
		e.preventDefault();
		var btn = $(this);
		addValuePoint(btn);
		btn.blur();
	});
}

function addDecBtnEvents() {
	$(".dec").off().click(function(e) {
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

function addItemEvents() {
	$(".addItem").off().click(function(e) {
		e.preventDefault();
		var btn = $(this);
		var input = btn.closest(".form-inline").find("select, input");
		var listItem = btn.closest(".form-inline").next();
		if (input.first().is('select')) {
			handleSelect(input.find("option"), listItem)
		} else {
			handleInput(input, listItem);
		}
	});
}

function handleInput(input, listItem) {
	if (input.size() > 1) {
		var values = [];
		while (input.length >= 1) {
			values.push(input.val());
			input.splice(0, 1);
		}
		addStaticItem(values[0], values[1], listItem);
	} else {
		addSkill(input.val(), listItem);
	}
}

function addStaticItem(name, value, list) {
	if (!isNaN(parseInt(value, 10))) {
		var template = '<div class="list-group-item">'
				+ '<span>ITEMNAME</span> <span class="pull-right">ITEMVALUE</span>'
				+ '<input type="hidden" name="meritName" value="ITEMNAME" />'
				+ '<input type="hidden" name="meritValue" value="ITEMVALUE" />'
				+ '</div>';

		template = template.replace(/ITEMNAME/g, name);
		template = template.replace(/ITEMVALUE/g, value);
		list.append(template);
	}
}

function handleSelect(options, listItem) {
	if (options.size() >= 1) {
		var option = getOption(options);
		var skill = option.attr("value");
		addSkill(skill, listItem);
		option.remove();
	}
}

function getOption(element) {
	if (element.size() <= 1) {
		return element;
	} else {
		return element.siblings(":selected");
	}
}

function addSkill(skill, list) {
	var template = '<div class="list-group-item">'
			+ '<label for="SKILLID">SKILLNAME</label>'
			+ '<span class="pull-right">'
			+ '<span class="btn-group btn-group-sm" role="group">'
			+ '<button type="button" class="btn btn-default dec"><span class="fa fa-minus-circle"></span></button>'
			+ '<button type="button" class="btn btn-default inc" data-max="5"><span class="fa fa-plus-circle"></span></button>'
			+ '</span> '
			+ '<span class="fa fa-circle"></span> <span class="fa fa-circle-thin"></span> <span class="fa fa-circle-thin"></span> <span class="fa fa-circle-thin"></span> <span class="fa fa-circle-thin"></span>'
			+ '</span>'
			+ '<input type="hidden" id="SKILLID" name="SKILLID" value="1" />'
			+ '</div>';
	template = template.replace(/SKILLID/g, skill);
	template = template.replace(/SKILLNAME/g, skill);
	list.append(template);
	addValueButtonEvents();
}
