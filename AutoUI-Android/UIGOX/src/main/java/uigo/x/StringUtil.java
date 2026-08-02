/*Copyright ©2025 TommyLemon(https://github.com/TommyLemon/UIGOX)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package uigo.x;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**String工具类
 * @author Lemon
 */
public class StringUtil extends apijson.StringUtil {

	public static String get(TextView tv) {
		CharSequence text = tv == null ? null : tv.getText();
		return get(text == null ? null : text.toString());
	}
	public static String trim(TextView tv) {
		return trim(get(tv));
	}
	public static String noBlank(TextView tv) {
		return noBlank(get(tv));
	}

	public static boolean isEmpty(TextView tv) {
		return isEmpty(get(tv));
	}
	public static boolean isEmpty(TextView tv, boolean trim) {
		return isNotEmpty(get(tv), trim);
	}

	public static boolean isNotEmpty(TextView tv) {
		return isNotEmpty(get(tv));
	}
	public static boolean isNotEmpty(TextView tv, boolean trim) {
		return isNotEmpty(get(tv), trim);
	}

	/**
	 * @param value
	 */
	public static void copyText(Context context, String value) {
		if (context == null || StringUtil.isNotEmpty(value, true) == false) {
			Log.e("StringUtil", "copyText  context == null || StringUtil.isNotEmpty(value, true) == false >> return;");
			return;
		}
		ClipData cD = ClipData.newPlainText("simple text", value);
		ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		clipboardManager.setPrimaryClip(cD);
		Toast.makeText(context, "已复制\n" + value, Toast.LENGTH_SHORT).show();
	}

	public static String getOrDefault(String s, String defaultVale) {
		return isEmpty(s, true) ? defaultVale : s;
	}

	public static String limitLength(String s) {
		return limitLength(s, null);
	}
	public static String limitLength(String s, Integer maxLen) {
		return limitLength(s, maxLen, null);
	}
    public static String limitLength(String s, Integer maxLen, String ellipsize) {
		return limitLength(s, maxLen, ellipsize, true);
	}
	public static String limitLength(String s, Integer maxLen, boolean trim) {
		return limitLength(s, maxLen, null, trim);
	}
	public static String limitLength(String s, Integer maxLen, String ellipsize, boolean trim) {
		return limitLength(s, maxLen, ellipsize, null, trim);
	}
	public static String limitLength(String s, Integer maxLen, String ellipsize, String ellipsis) {
		return limitLength(s, maxLen, ellipsize, ellipsis, true);
	}
	public static int MAX_LIMIT_LENGTH = 50;
	public static final String ELLIPSIZE_START = "start";
	public static final String ELLIPSIZE_MIDDlE = "middle";
	public static final String ELLIPSIZE_END = "end";
    public static String limitLength(String s, Integer maxLen, String ellipsize, String ellipsis, boolean trim) {
		var l = StringUtil.length(s, trim);
		if (maxLen == null) {
			maxLen = MAX_LIMIT_LENGTH;
		}
		if (maxLen <= 0 || l <= maxLen) {
			return s;
		}

		if (StringUtil.isEmpty(ellipsis)) {
			ellipsis = l <= 50 ? ".." : " ... ";
		}

		if (ELLIPSIZE_START.equals(ellipsize)) {
			return ellipsis + s.substring(l - maxLen);
		}
		if (ELLIPSIZE_MIDDlE.equals(ellipsize)) {
			int m = (int) Math.floor(maxLen/2);
			return s.substring(0, m) + ellipsis + s.substring(l - m);
		}
		return s.substring(0, maxLen) + ellipsis;
    }

	public static String getHost(String url) {
		// try {
		// 	Uri uri = Uri.parse(url);
		// 	return uri.getHost();
		// } catch (Throwable e) {
		// 	e.printStackTrace();
		// }

		int index = url == null ? -1 : url.indexOf("://");
		String prefix = index < 0 ? "" : url.substring(0, index + 3);
		String rest = index < 0 ? url : url.substring(index + 3);
		int index2 = rest == null ? -1 : rest.indexOf("/");
		return StringUtil.isEmpty(rest) ? null : prefix + (index2 < 0 ? rest : rest.substring(0, index2));
	}


	public static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");
	public static String formatDateTime(Date date) {
		return DATE_TIME_FORMAT.format(date);
	}
	public static String formatDateTime(long time) {
		return formatDateTime(new Date(time));
	}
	public static String formatDate(Date date) {
		return DATE_FORMAT.format(date);
	}
	public static String formatDate(long time) {
		return formatDate(new Date(time));
	}
	public static String formatTime(Date date) {
		return TIME_FORMAT.format(date);
	}
	public static String formatTime(long time) {
		return formatTime(new Date(time));
	}

}
