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
import java.util.Map;

/**网页 HTTP 请求拦截器
 * @author Lemon
 */
public class WriteHandlingWebResourceRequest implements WebResourceRequest {
    final private Uri uri;
    final private WebResourceRequest originalWebResourceRequest;
    final private String requestBody;

    WriteHandlingWebResourceRequest(
            WebResourceRequest originalWebResourceRequest,
            String requestBody,
            Uri uri
    ){
        this.originalWebResourceRequest = originalWebResourceRequest;
        this.requestBody = requestBody;
        if (uri!=null) {
            this.uri = uri;
        }else{
            this.uri = originalWebResourceRequest.getUrl();
        }
    }

    @Override
    public Uri getUrl() {
        return this.uri;
    }

    @Override
    public boolean isForMainFrame() {
        return originalWebResourceRequest.isForMainFrame();
    }

    @Override
    public boolean isRedirect() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasGesture() {
        return originalWebResourceRequest.hasGesture();
    }

    @Override
    public String getMethod() {
        return originalWebResourceRequest.getMethod();
    }

    @Override
    public Map<String, String> getRequestHeaders() {
        return originalWebResourceRequest.getRequestHeaders();
    }
    public String getAjaxData(){
        return requestBody;
    }

    public boolean hasAjaxData(){
        return requestBody != null;
    }

}
