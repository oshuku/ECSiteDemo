/**
 * itemList
 */

$(document).ready(function() {
	showList();
});


function showList() {
	$.ajax({
		type: "get",
		url: root + "/getList",
		success: function(data) {
			var result = data.data;
			if (result && result.length > 0) {
				var str = "";
				for (var i = 0; i < result.length; i++) {
					var row = result[i];
					str += render(row);
				}
				$('#grid').html(str);
				bindEvent();
			}
		}
	});
}

function render(row) {
	var str = "";
	str += '<div class="gridCell">';
	str += '<input name="itemCode" type="hidden" value=' + row.itemCode + '>';
	str += '<img src="images/item.jpg">';
	str += '<p class="itemName">' + row.itemName + '</p>';
	str += '<p class="priceTag">価格</p>';
	str += '<p class="itemPrice">￥' + row.itemPrice + '</p>';
	str += '<button class="buyBtn">購入</button>';
	str += '</div>';

	return str;
}

function bindEvent() {
	$('.gridCell').click(function() {
		itemCode = $(this).find('input[name="itemCode"]').val();
		window.location.href = root + "/itemDetail.html?id=" + itemCode;
	});
}