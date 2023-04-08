/**
 * itemDetail
 */

$(document).ready(function() {
	$("#header").load("header.html");
	$("#footer").load("footer.html");

	var url = location.href;
	var params = url.split(/[\?&]+/);
	var paramms = params[1].split("=");
	var id = paramms[1];
	// console.log(id);
	getDetail(id);

});


function getDetail(id) {
	$.ajax({
		type: "get",
		url: root + "/detail?id=" + id,
		success: function(data) {
			var result = data.data;
			if (result) {
				var str = render(result);
				$('#container').html(str);
				bindEvent();
			}
		}
	});
}

function render(row) {
	var str = "";

	str += '<div class="detailImg">';
	str += '		<img src="images/item.jpg">';
	str += '	</div>';

	str += '	<div class="detailInfo">';
	str += '		<h1>' + row.itemName + '</h1>';

	str += '		<div class="infoBlock">';
	str += '			<div class="infoHeader">';
	str += '				<h4>' + showTimeFull(row.regDate) + ' 発売</h4>';
	str += '			</div>';

	str += '			<div class="infoContent">';
	str += '				<p>' + row.itemDescription + '</p>';
	str += '				<div class="trade">';
	str += '					<p>';
	str += '						<span class="tradePrice">￥' + row.itemPrice + '</span>';
	str += '						<span class="tradePriceTax">（税込み￥' + calTax(row.itemPrice) + '）</span>';
	str += '					</p>';
	str += '				</div>';

	str += '				<div class="btnArea">';
	str += '					<button class="orderBtn">One-Click注文</button>';
	str += '				    <input name="itemCode" type="hidden" value=' + row.itemCode + '>';
	str += '				</div>';
	str += '			</div>';
	str += '		</div>';
	str += '        <p id="message"></p>';
	str += '	</div>';

	return str;
}

function bindEvent() {
	$('.btnArea').click(function() {
		itemCode = $(this).find('input[name="itemCode"]').val();
		//console.log("order:"+itemCode);
		placeOrder(itemCode);
	});
}

function placeOrder() {
	var data = {};
	data['itemCode'] = itemCode;
	data['num'] = 1;
	$.ajax({
		type: "post",
		url: root + "/order",
		data: data,
		success: function(data) {
			if (data.status === 200) {
				window.location.href = root + "/finish.html";
			}

			if (data.status === 202) {
				$("#message").html("在庫不足。");
				$("#message").addClass("error");
			}


		},
		error: function() {
			$("#message").html("注文失敗しました。");
			$("#message").addClass("error");
		}
	});

}