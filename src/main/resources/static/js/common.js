/**
 * 共通部分
 */

root = "https://" + location.hostname;
if (location.hostname === "localhost") {
	root = "http://localhost:80/mysite";
}

function showTimeFull(updateTime) {
	var str = updateTime.split(".");
	var tmp = str[0].split("T");
	return tmp[0] + " " + tmp[1];
}


function calTax(price) {
	var tax = 0.1;
	return price * (1 + tax);
}