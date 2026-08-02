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
import org.jsoup.Jsoup;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**网页 HTTP 请求拦截接口，TODO 支持 fetch
 * @author Lemon
 */
public class AjaxInterceptJavascriptInterface {

    private static String interceptHeader = null;
    private WriteHandlingWebViewClient mWebViewClient = null;

    public AjaxInterceptJavascriptInterface(WriteHandlingWebViewClient webViewClient) {
        mWebViewClient = webViewClient;
    }

    public static String enableIntercept(Context context, byte[] data) throws IOException {
        if (interceptHeader == null) {
//            interceptHeader = new String(
//                    Utils.consumeInputStream(context.getAssets().open("interceptheader.html"))
//            );

            interceptHeader = "<script src=\"https://unpkg.com/vconsole@latest/dist/vconsole.min.js\"></script>\n";
        }

        org.jsoup.nodes.Document doc = Jsoup.parse(new String(data));
        doc.outputSettings().prettyPrint(true);

        // Prefix every script to capture submits
        // Make sure our interception is the first element in the
        // header
        org.jsoup.select.Elements element = doc.getElementsByTag("head");
        if (element.size() > 0) {
            element.get(0).prepend(interceptHeader);
        }

        String pageContents = doc.toString();
        return pageContents;
    }

    @JavascriptInterface
    public void onHttpEvent(int action, String id, String item) {
        mWebViewClient.onHttpEvent(action, id, item);
    }

    @JavascriptInterface
    public void onEditEvent(String id, int selectionStart, int selectionEnd, String text, int touchX, int touchY) { // 不兼容 Integer touchX, Integer touchY
        boolean isNull = touchX == 0 && touchY == 0;
        mWebViewClient.onEditEvent(id, selectionStart, selectionEnd, text, isNull ? null : touchX, isNull ? null : touchY);
    }

    @JavascriptInterface
    public void onKeyEvent(String id, int action, String key, int keyCode) {
        mWebViewClient.onKeyEvent(id, action, key, keyCode);
    }

    @JavascriptInterface
    public void onTouchEvent(String id, int touchX, int touchY) { // 不兼容 Integer touchX, Integer touchY
        boolean isNull = touchX == 0 && touchY == 0;
        mWebViewClient.onTouchEvent(id, isNull ? null : touchX, isNull ? null : touchY);
    }

}
