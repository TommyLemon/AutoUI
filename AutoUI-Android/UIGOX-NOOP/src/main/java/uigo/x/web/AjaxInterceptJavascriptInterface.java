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

package uigo.x.web;

import java.io.IOException;

import android.content.Context;

/**网页 HTTP 请求拦截接口
 * @author Lemon
 */
public class AjaxInterceptJavascriptInterface {
    public AjaxInterceptJavascriptInterface(WriteHandlingWebViewClient webViewClient) {}

    public static String enableIntercept(Context context, byte[] data) throws IOException {
        return null;
    }

    public void onHttpEvent(int action, String id, String item) {}

    public void onEditEvent(String id, int selectionStart, int selectionEnd, String text, int touchX, int touchY) {}

    public void onKeyEvent(String id, int action, String key, int keyCode) {}

    public void onTouchEvent(String id, int touchX, int touchY) {}

}
