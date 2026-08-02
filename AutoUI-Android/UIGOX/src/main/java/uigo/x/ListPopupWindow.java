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

    private android.widget.PopupWindow popupWindow;
    private View view;
    public void onUIAutoPopupWindowShow() {
        if (view == null) {
            try {
                Field field = android.widget.ListPopupWindow.class.getDeclaredField("mPopup");
                field.setAccessible(true);
                popupWindow = (android.widget.PopupWindow) field.get(this);
//                popupWindow.setOutsideTouchable(false);

                Field dvField = android.widget.PopupWindow.class.getDeclaredField("mDecorView");
                dvField.setAccessible(true);
                view = (View) dvField.get(popupWindow);

                if (view == null) {
                    View cv = popupWindow.getContentView();
                    view = cv == null ? null : cv.getRootView();
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        if (view == null) {
            View cv = getListView();
            view = cv == null ? null : cv.getRootView();
        }

        Window w = getWindow();
        UIAutoApp.getInstance().onUIAutoPopupWindowShow(popupWindow, view, w, context, null);
    }

    public void onUIAutoPopupWindowDismiss() {
        Window w = getWindow();
        UIAutoApp.getInstance().onUIAutoPopupWindowDismiss(popupWindow, view, w, context, null);
    }

    @Override
    public void show() {
        super.show();
        onUIAutoPopupWindowShow();
    }

    private Window getWindow() {
        Activity ctx = view == null ? null : (Activity) view.getContext();
        if (ctx == null) {
            ctx = context;
        }
        return ctx.getWindow();
    }

    private Activity context;
    private void init(Context ctx) {
        this.context = (Activity) ctx;

        super.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (listener != null) {
                    listener.onDismiss();
                }

                onUIAutoPopupWindowDismiss();
            }
        });
    }

    private android.widget.PopupWindow.OnDismissListener listener;
    @Override
    public void setOnDismissListener(android.widget.PopupWindow.OnDismissListener listener) {
        this.listener = listener;
    }




    /**
     * Create a new, empty popup window capable of displaying items from a ListAdapter.
     * Backgrounds should be set using {@link #setBackgroundDrawable(Drawable)}.
     *
     * @param context Context used for contained views.
     */
    public ListPopupWindow(Context context) {
        super(context);
        init(context);
    }

    /**
     * Create a new, empty popup window capable of displaying items from a ListAdapter.
     * Backgrounds should be set using {@link #setBackgroundDrawable(Drawable)}.
     *
     * @param context Context used for contained views.
     * @param attrs Attributes from inflating parent views used to style the popup.
     */
    public ListPopupWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Create a new, empty popup window capable of displaying items from a ListAdapter.
     * Backgrounds should be set using {@link #setBackgroundDrawable(Drawable)}.
     *
     * @param context Context used for contained views.
     * @param attrs Attributes from inflating parent views used to style the popup.
     * @param defStyleAttr Default style attribute to use for popup content.
     */
    public ListPopupWindow(Context context, AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * Create a new, empty popup window capable of displaying items from a ListAdapter.
     * Backgrounds should be set using {@link #setBackgroundDrawable(Drawable)}.
     *
     * @param context Context used for contained views.
     * @param attrs Attributes from inflating parent views used to style the popup.
     * @param defStyleAttr Style attribute to read for default styling of popup content.
     * @param defStyleRes Style resource ID to use for default styling of popup content.
     */
    public ListPopupWindow(Context context, AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


}
