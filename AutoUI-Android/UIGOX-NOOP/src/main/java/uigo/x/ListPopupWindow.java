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
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.annotation.AttrRes;
import androidx.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import java.lang.reflect.Field;

/**通用列表悬浮窗
 * @author Lemon
 * @use 把业务代码中 android.widget.ListPopupWindow 换成 uigo.x.ListPopupWindow
 */
public class ListPopupWindow extends android.widget.ListPopupWindow {

    public void onUIAutoPopupWindowShow() {}

    public void onUIAutoPopupWindowDismiss() {}

    public ListPopupWindow(Context context) {
        super(context);
    }

    public ListPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListPopupWindow(Context context, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListPopupWindow(Context context, AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
