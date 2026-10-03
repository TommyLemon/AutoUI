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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**自动 UI 测试，需要用 UIAuto 发请求到这个设备
 * https://github.com/TommyLemon/UIGOX
 * @author Lemon
 */
public class UIAutoActivity extends Activity {
    private static final String TAG = "UIAutoActivity";

    public static final String INTENT_FLOW_ID = "INTENT_FLOW_ID";
    public static final String KEY_PLATFORM_ACCOUNT = "KEY_PLATFORM_ACCOUNT";
    public static final String KEY_PLATFORM_PASSWORD = "KEY_PLATFORM_PASSWORD";


    public static Intent createIntent(Context context) {
        return new Intent(context, UIAutoActivity.class); //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
}

