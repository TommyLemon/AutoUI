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
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.PopupWindow;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**Application
 * @author Lemon
 */
public class UIAutoApp { // extends Application {
  public static final String TAG = "UIAutoApp";

  public static final String KEY_STATUS_HEIGHT = "KEY_STATUS_HEIGHT";
  public static final String KEY_STATUS_UNIT_DP = "KEY_STATUS_UNIT_DP";
  public static final String KEY_STATUS_SHOW = "KEY_STATUS_SHOW";
  public static final String KEY_NAV_HEIGHT = "KEY_NAV_HEIGHT";
  public static final String KEY_NAV_UNIT_DP = "KEY_NAV_UNIT_DP";
  public static final String KEY_NAV_SHOW = "KEY_NAV_SHOW";

  public static boolean DEBUG = false;
  public static long STEP_TIMEOUT = 0;
//  public static long STEP_TIMEOUT = DEBUG ? 30*1000 : 60*1000;

  protected UIAutoApp() {}

  private static final UIAutoApp instance = new UIAutoApp();
  public static UIAutoApp getInstance() {
    return instance;
  }

  private static Application APP;
  public static Application getApp() {
    return APP;
  }
  public Context getApplicationContext() {
    return null;
  }

  public String getPackageName() {
    return null;
  }
  public AssetManager getAssets() {
    return null;
  }

  public boolean isShowing() {
    return false;
  }
  public boolean isSplitShowing() {
    return false;
  }
  public boolean isRunning() {
    return false;
  }
  public boolean isRecording() {
    return false;
  }
  public boolean isReplaying() {
    return false;
  }

  public boolean isReplayingInput() {
    return false;
  }


  public void post(Runnable r) {}
  public void postDelayed(Runnable r, long delayMillis) {}

  public Resources getResources() {
    return null;
  }
  public SharedPreferences getSharedPreferences() {
    return null;
  }



  public void onUIAutoActivityCreate() {}

  public void onUIAutoActivityCreate(Activity activity) {}
  public void onUIAutoActivityCreate(Activity activity, boolean showToolBar) {}

  public void onUIAutoDialogShow(Dialog dialog) {}
//  public void onUIAutoDialogShow(DialogInterface dialog) {}
  public void onUIAutoPopupWindowShow(PopupWindow pw, View view, Window window, Activity activity, Fragment fragment) {}

  public void onUIAutoWindowCreate(Window.Callback callback, Window window) {}
  public void onUIAutoWindowCreate(Window.Callback callback, Window window, DialogInterface dialogInterface) {}
  public void onUIAutoWindowCreate(Window.Callback callback, Window window, PopupWindow popupWindow) {}
  public void onUIAutoWindowCreate(Window.Callback callback, Window window, DialogInterface dialogInterface, PopupWindow popupWindow) {}

  public void onUIAutoWindowCreate(Window.Callback callback, Window window, DialogInterface dialogInterface, PopupWindow popupWindow, boolean showToolBar) {}

  public void clearTextChangedListener() {}

  public void addTextChangedListener(View view) {}


  public static final String KEY_ENABLE_PROXY = "KEY_ENABLE_PROXY";
  public static final String KEY_PROXY_SERVER = "KEY_PROXY_SERVER";

  public void initUIAuto(Application app) {}


  public void saveAllBallPositions() {}


  public boolean isIgnoreFragment(Fragment f) {
    return false;
  }

  public void onClickPlay() {}

  public void forward(boolean skip) {}

  public void toast(int id) {}

  public void onUIAutoDialogDismiss(Dialog dialog) {}
//  public void onUIAutoDialogDismiss(DialogInterface dialog) {}

  public void onUIAutoPopupWindowDismiss(PopupWindow pw, View view, Window window, Activity activity, Fragment fragment) {}

  public void onUIAutoWindowDestroy(Window.Callback callback, Window window, DialogInterface dialogInterface, PopupWindow popupWindow) {}

  public void onUIAutoActivityDestroy(Window.Callback callback, Activity activity) {}

  public LayoutInflater getLayoutInflater() {
    return null;
  }


  public Activity getCurrentActivity() {
    return null;
  }

  public void setCurrentActivity(Activity activity) {}

  public Fragment getCurrentFragment() {
    return null;
  }
  public void setCurrentFragment(Fragment fragment) {
  }

  public DialogInterface getCurrentDialog() {
    return null;
  }
  public DialogInterface getCurrentDialog(String className) {
    return null;
  }

  public PopupWindow getCurrentPopupWindow(Double x, Double y) {
    return null;
  }

//  public void setCurrentPopupWindow(PopupWindow pw, View v, Window.Callback callback, Activity activity, Fragment fragment) {}


  public void setCurrentView(View v, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {}





//  public boolean onTouchEvent(MotionEvent event, Activity activity) {return false;}
//  public boolean onTouchEvent(MotionEvent event, Fragment fragment) {return false;}
  public boolean onTouchEvent(MotionEvent event, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    return false;
  }
  public boolean onKeyDown(int keyCode, KeyEvent event, Activity activity, Fragment fragment) {
    return false;
  }
  public boolean onKeyDown(int keyCode, KeyEvent event, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    return false;
  }

  public boolean onKeyUp(int keyCode, KeyEvent event, Activity activity, Fragment fragment) {
    return false;  
  }
  public boolean onKeyUp(int keyCode, KeyEvent event, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    return false;
  }

  public void record() {}



//  @Override
  public void onConfigurationChanged(Configuration newConfig) {}

  public void showCoverAndSplit(boolean showCover, boolean showSplit) {}

  public void showCover(boolean show) {}

  public Node<InputEvent> getFirstEventNode() {
    return null;
  }
  public Node<InputEvent> getCurrentEventNode() {
    return null;
  }

  public void replay() {
  }
  public void replay(int step) {}

  /* 非触屏、非按键的 其它事件，例如 Activity.onResume, HTTP Response 等
   */
  public void onEventChange(int position, int type) {}
  public void onEventChange(int position, long delayMillis) {}

  public void onUIAutoActivityResult(Fragment fragment, int requestCode, int resultCode, Intent data, Boolean mock) {}
  public void onUIAutoActivityResult(Activity activity, int requestCode, int resultCode, Intent data, Boolean mock) {}
  public void onUIAutoActivityResult(Activity activity, Fragment fragment, int requestCode, int resultCode, Intent intent, Boolean mock) {}

  public void onUIEvent(int action, Window.Callback callback, Activity activity) {}
  public void onUIEvent(int action, Window.Callback callback, Activity activity, DialogInterface dialog) {}
  public void onUIEvent(int action, Window.Callback callback, Fragment fragment) {}
  public void onUIEvent(int action, Window.Callback callback, Fragment fragment, DialogInterface dialog) {}
  public void onUIEvent(int action, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog) {}

  public List<String> getHttpHostList() {
    return null;
  }
  public void setHttpHostList(List<String> httpHostList) {}
  public List<String> getWebHostList() {
    return null;
  }
  public void setWebHostList(List<String> webHostList) {}

  public synchronized void onUIEvent(int action, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, WebView webView, String url) {}


//  public void onHTTPEvent(int action, String format, String url, String request, String response, Activity activity) {}
//  public void onHTTPEvent(int action, String format, String url, String request, String response, Fragment fragment) {}

  public synchronized void onHTTPEvent(int action, String method, String format, String host, String url
          , String reqHeader, String request, Activity activity, Fragment fragment) {}

  public void setIgnoreUrlList(List<String> ignoreUrlList) {}
  public void setIgnoreHostList(List<String> ignoreHostList) {}
  public void setIgnorePathList(List<String> ignorePathList) {}
  public void setIgnoreSuffixList(List<String> ignoreSuffixList) {}

  public synchronized void onHTTPEvent(int action, String method, String format, String host, String url
          , String reqHeader, String request, String status, String resHeader, String response, Throwable e
          , Activity activity, Fragment fragment, DialogInterface dialog, HttpManager.OnHttpResponseListener listener) {}

  public boolean isIgnoreApi(String method, String host, String url) {
    return false;
  }

  public void putSharedPreferences(SharedPreferences spf, Map<String, Object> map) {}
  public void putSharedPreferences(SharedPreferences spf, Map<String, Object> map, Set<String> longKeys, Set<String> floatKeys, Set<String> stringSetKeys) {}
  public void putSharedPreferences(SharedPreferences spf, Set<Map.Entry<String, Object>> set) {}

  public static boolean IS_AUTO_TYPE = true;
  public void putSharedPreferences(SharedPreferences spf, Set<Map.Entry<String, Object>> set, Set<String> longKeys, Set<String> floatKeys, Set<String> stringSetKeys) {}

  public void prepareRecord() {}
  public void prepareRecord(boolean clear, boolean start) {}
  public void prepareRecord(boolean clear, boolean start, boolean output) {}



  public void startUIAutoActivity() {}
  public void startUIAutoListActivity(String cacheKey) {}
  //  @Override
  public void startActivity(Intent intent) {}
//  @Override
  public void startActivity(Intent intent, Bundle options) {}

  public void setHttpProxy(boolean isProxy, String server) {}

  public boolean isProxyEnabled() {
    return false;
  }
  public String getProxyServer() {
    return null;
  }

  public String getDelegateId() {
    return null;
  }
  public UIAutoApp setDelegateId(String delegateId) {
    return this;
  }

  public String getHttpUrl(String url_) throws UnsupportedEncodingException {
    return url_; // null
  }

  public void setCurrentWebView(WebView webView, Activity activity, Fragment fragment) {}
  public void setCurrentWebUrl(String webUrl) {}


  public static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    // JSONObject obj;

    int step;

    long id;
    long flowId;
    long targetId;
    String targetIdName;
    boolean disable;
    public Boolean mock;
    int type;
    int action;
    long time;
    long timeout;
    boolean isSplit2Show;
    double splitX, splitX2;
    double splitY, splitY2;
    double splitSize;
    double windowX;
    double windowY;
    double decorX;
    double decorY;

    int layoutType;
    double ratio;
    double density;

    double windowWidth, windowHeight;
    double keyboardHeight;
    int orientation;
    int gravityX, gravityY;
    int ballGravity, ballGravity2;
    double x, y, x2, y2;
    double rx, ry, rx2, ry2;

    String activity;
    String fragment;
    String dialog;
    String format;
    String status;
    String method;
    //    String header;
    String host;
    String url;
    //    String request;
//    String response;
    Throwable exception;

    public int requestCode;
    public int resultCode;
    public Intent intent;


    public Node(Node<E> prev, E element, Node<E> next) {}

  }
}
