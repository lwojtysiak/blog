/**
 * Gets dates from span elements with specified id-ending and convert them to
 * users local timezone.
 * 
 * @param id
 *            id of span elements which contains dates to convert
 */
function convertDateToUserTimezone(id) {
	$("span[id$='" + id + "']").each(function(index, element) {
		var elementDate = new Date($(element).html());
		$(element).html(elementDate.toLocaleString());
	});
}