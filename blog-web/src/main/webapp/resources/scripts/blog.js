function convertDateToUserTimezone(id) {
	$("span[id$='"+id+"']").each(function(index,element) {
		var elementDate = new Date($(element).html());
		$(element).html(elementDate.toLocaleString());
	});
}