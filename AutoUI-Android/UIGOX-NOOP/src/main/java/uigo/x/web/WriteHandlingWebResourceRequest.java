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

import android.net.Uri;
import android.webkit.WebResourceRequest;

import java.util.Collections;
import java.util.Map;

/**网页 HTTP 请求拦截器
 * @author Lemon
 */
public class WriteHandlingWebResourceRequest implements WebResourceRequest {
    WriteHandlingWebResourceRequest(
            WebResourceRequest originalWebResourceRequest,
            String requestBody,
            Uri uri
    ) {}

    public String getAjaxData() {
        return null;
    }

    public boolean hasAjaxData() {
        return false;
    }

    @Override
    public String getMethod() {
        return "";
    }

    @Override
    public Map<String, String> getRequestHeaders() {
        return Collections.emptyMap();
    }

    @Override
    public Uri getUrl() {
        return null;
    }

    @Override
    public boolean hasGesture() {
        return false;
    }

    @Override
    public boolean isForMainFrame() {
        return false;
    }

    @Override
    public boolean isRedirect() {
        return false;
    }
}
