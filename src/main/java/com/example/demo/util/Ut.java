package com.example.demo.util;

public class Ut {
	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	public static String f(String str, Object... args) {
		return String.format(str, args);
	}
	
	public static String jsReplace(String resultCode, String msg, String replaceUri) {

		if (resultCode == null) {
			resultCode = "";
		}

		if (msg == null) {
			msg = "";
		}

		if (replaceUri == null) {
			replaceUri = "/";
		}

		String resultMsg = resultCode + " / " + msg;

		return Ut.f("""
				<script>
					let resultMsg = '%s'.trim();

					if(resultMsg.length > 0){
						alert(resultMsg);
					}

					location.replace('%s');
				</script>
				""", resultMsg, replaceUri);
	}

	public static String jsHistoryBack(String resultCode, String msg) {
		if (resultCode == null) {
			resultCode = "";
		}

		if (msg == null) {
			msg = "";
		}

		String resultMsg = resultCode + " / " + msg;

		return Ut.f("""
				<script>
					let resultMsg = '%s'.trim();

					if(resultMsg.length > 0){
						alert(resultMsg);
					}

					history.back();
				</script>
				""", resultMsg);
	}
}
