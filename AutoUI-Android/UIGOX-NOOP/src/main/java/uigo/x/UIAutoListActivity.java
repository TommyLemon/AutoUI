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


/** 操作流程 Flow /操作步骤 Input 列表
 * https://github.com/TommyLemon/UIGOX
 * @author Lemon
 */
public class UIAutoListActivity extends Activity {
    public static final String TAG = "UIAutoListActivity";

    public static final String INTENT_IS_LOCAL = "INTENT_IS_LOCAL";
    public static final String INTENT_PARENT_VIEW_ID = "INTENT_PARENT_VIEW_ID";
    public static final String INTENT_PARENT_VIEW_ID_NAME = "INTENT_PARENT_VIEW_ID_NAME";
    public static final String INTENT_PARENT_VIEW_TYPE = "INTENT_PARENT_VIEW_TYPE";
    public static final String INTENT_FLOW_ID = "INTENT_FLOW_ID";
    public static final String INTENT_EVENT_LIST = "INTENT_EVENT_LIST";
    public static final String INTENT_TEMP_KEY = "INTENT_TEMP_KEY";
    public static final String INTENT_NAME = "INTENT_NAME";

    public static final String KEY_PROJECT = "KEY_PROJECT";
    public static final String KEY_APP_NAME = "KEY_APP_NAME";
    public static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    public static final String KEY_PASSWORD = "KEY_PASSWORD";
    public static final String KEY_ACCOUNT_ID = "KEY_ACCOUNT_ID";
    public static final String KEY_ACCOUNT_NAME = "KEY_ACCOUNT_NAME";
    public static final String KEY_IGNORE_VIEW_ID_LIST = "KEY_IGNORE_VIEW_ID_LIST";
    public static final String INTENT_PAGE_NAME = "INTENT_PAGE_NAME";

    public static final String RESULT_LIST = "RESULT_LIST";

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, boolean isLocal, int parentViewId, String parentViewIdName, String parentViewType, String pageName) {
        return createIntent(context, isLocal)
                .putExtra(INTENT_PARENT_VIEW_ID, parentViewId)
                .putExtra(INTENT_PARENT_VIEW_ID_NAME, parentViewIdName)
                .putExtra(INTENT_PARENT_VIEW_TYPE, parentViewType)
                .putExtra(INTENT_PAGE_NAME, pageName);
    }

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, boolean isLocal) {
        return createIntent(context, isLocal, null);
    }

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, boolean isLocal, String name) {
        return new Intent(context, UIAutoListActivity.class)
                .putExtra(INTENT_IS_LOCAL, isLocal)
                .putExtra(INTENT_NAME, name);
    }

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, long flowId) {
        return createIntent(context, flowId, null);
    }

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, long flowId, String name) {
        return new Intent(context, UIAutoListActivity.class)
                .putExtra(INTENT_FLOW_ID, flowId)
                .putExtra(INTENT_NAME, name);
    }

    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context, String tempKey, long flowId) {
        return createIntent(context, true)
                .putExtra(INTENT_TEMP_KEY, tempKey)
                .putExtra(INTENT_FLOW_ID, flowId);
    }


}