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

import android.app.Activity;

import androidx.fragment.app.Fragment;

import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

/**网页客户端
 * @author Lemon
 */
public class WriteHandlingWebViewClient extends WebViewClient {
    public WriteHandlingWebViewClient(WebView webView, Activity activity, Fragment fragment) {}

    public void initWeb(String webUrl) {}

    public WebResourceResponse shouldInterceptRequest(WebView view, WriteHandlingWebResourceRequest request){
        return null;
    }

    public void onHttpEvent(int action, String id, String item) {}

    public void onEditEvent(String id, int selectionStart, int selectionEnd, String text) {}
    public void onEditEvent(String id, int selectionStart, int selectionEnd, String text, Integer touchX, Integer touchY) {}

    public void onKeyEvent(String id, int action, String key, int keyCode) {}

    public void onTouchEvent(String id, Integer touchX, Integer touchY) {}


    public static byte[] consumeInputStream(InputStream inputStream) throws IOException {
        return null;
    }

}