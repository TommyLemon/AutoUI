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

import android.content.Context;

import java.util.Map;


/**HTTP请求管理类
 * @author Lemon
 * @use HttpManager.getInstance().get(...)或HttpManager.getInstance().post(...)  > 在回调方法onHttpRequestSuccess和onHttpRequestError处理HTTP请求结果
 * @must 解决getToken，getResponseCode，getResponseData中的TODO
 */
public class HttpManager {
	public static int PAGE_NUM_0;

	private HttpManager(Context context) {}

	private static HttpManager instance;// 单例
	public static HttpManager getInstance() {
		if (instance == null) {
			synchronized (HttpManager.class) {
				if (instance == null) {
					instance = new HttpManager(UIAutoApp.getApp());
				}
			}
		}
		return instance;
	}

	public void get(final String url, final Map<String, Object> request, final OnHttpResponseListener listener) {}

	public void get(final String url, final Map<String, Object> request, int requestCode, final OnHttpResponseListener listener) {}


	public void post(final String url, final String request, final OnHttpResponseListener listener) {}

	public void post(final String url, final String request, final int requestCode, final OnHttpResponseListener listener) {}

	public void post(final String request, final String url, boolean isJson, int requestCode, final OnHttpResponseListener listener) {}


	public static final String KEY_COOKIE = "cookie";

	public String getCookie(String host) {
		return null;
	}

	public void saveCookie(String host, String value) {}


	/**网络请求回调接口
	 * @author Lemon
	 */
	public static interface OnHttpResponseListener {
		/**
		 * @param requestCode 请求码，自定义，在发起请求的类中可以用requestCode来区分各个请求
		 * @param resultJson 服务器返回的Json串
		 * @param e 异常
		 */
		void onHttpResponse(int requestCode, String resultJson, Throwable e);
	}
}
