/**
 * 문자열 Byte 구하기
 * 
 * @param s
 * @returns {Number}
 **/
function getValueByte(str) {
	var nBytes = 0;
	for ( var i = 0, txtLen = str.length; i < txtLen; i++) {
		var len = escape(str.charAt(i)).length;
		nBytes += (len > 3) ? ((len / 3)) : 1;
	}
	return nBytes;
}
/**
 * 문자열 Byte 제한하기
 * 
 * @param str
 * @param limit_byte
 * @returns {String}
 **/
 function getValueByteLimit(str,limit_byte){    
	var result = "";
	var byte_value = limit_byte - getValueByte(str);
	var temp_str = str;
	if(byte_value < 0) {
		while(byte_value < 0) {
			temp_str = temp_str.substring(0, temp_str.length-1);
			result = temp_str;
			byte_value = limit_byte - getValueByte(temp_str);
		}
	}
	return result;
}