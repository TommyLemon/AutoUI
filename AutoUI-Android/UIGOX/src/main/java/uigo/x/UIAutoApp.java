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

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static uigo.x.Constant.*;
import static uigo.x.InputUtil.GRAVITY_BOTTOM;
import static uigo.x.InputUtil.GRAVITY_BOTTOM_LEFT;
import static uigo.x.InputUtil.GRAVITY_BOTTOM_RIGHT;
import static uigo.x.InputUtil.GRAVITY_CENTER;
import static uigo.x.InputUtil.GRAVITY_LEFT;
import static uigo.x.InputUtil.GRAVITY_RATIO;
import static uigo.x.InputUtil.GRAVITY_RATIO_BOTTOM;
import static uigo.x.InputUtil.GRAVITY_RATIO_LEFT;
import static uigo.x.InputUtil.GRAVITY_RATIO_RIGHT;
import static uigo.x.InputUtil.GRAVITY_RATIO_TOP;
import static uigo.x.InputUtil.GRAVITY_RIGHT;
import static uigo.x.InputUtil.GRAVITY_TOP;
import static uigo.x.InputUtil.GRAVITY_TOP_LEFT;
import static uigo.x.InputUtil.GRAVITY_TOP_RIGHT;
import static uigo.x.InputUtil.X_GRAVITIES;
import static uigo.x.InputUtil.Y_GRAVITIES;
import static unitauto.apk.UnitAutoApp.findActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.HttpException;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.SystemClock;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ActionMode;
import android.view.Display;
import android.view.Gravity;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import apijson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.ViewStateListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.ConnectException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import apijson.JSONResponse;
import unitauto.MethodUtil;
import unitauto.NotNull;
import unitauto.apk.UnitAutoApp;
import apijson.JSONRequest;


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

  private static final String BALL_GRAVITY = "BALL_GRAVITY";
  private static final String BALL_GRAVITY2 = "BALL_GRAVITY2";
  private static final String SPLIT_X = "SPLIT_X";
  private static final String SPLIT_Y = "SPLIT_Y";
  private static final String SPLIT_X2 = "SPLIT_X2";
  private static final String SPLIT_Y2 = "SPLIT_Y2";
  private static final String SPLIT_SIZE = "SPLIT_SIZE";
  private static final String SPLIT_COLOR = "SPLIT_COLOR";

  private static final String CLASS_BALL_CACHE_MAP = "CLASS_BALL_CACHE_MAP";

  private static double DENSITY = Resources.getSystem().getDisplayMetrics().density;
  private static float SCALED_DENSITY = Resources.getSystem().getDisplayMetrics().scaledDensity;

  public static boolean DEBUG = apijson.Log.DEBUG;
  public static long STEP_TIMEOUT = DEBUG ? 10*1000 : 60*1000;
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
    return getApp().getApplicationContext();
  }

  public String getPackageName() {
    return getApp().getPackageName();
  }
  public AssetManager getAssets() {
    return getApp().getAssets();
  }

  public boolean isShowing() {
    return isShowing;
  }
  public boolean isSplitShowing() {
    return isSplitShowing;
  }
  public boolean isRunning() {
    return isRunning;
  }
  public boolean isRecording() {
    return isShowing() && isRunning() && ! isReplay;
  }
  public boolean isReplaying() {
    return isShowing() && isRunning() && isReplay;
  }



  private boolean isTimeout = false;
  private final Runnable timeoutRunnable = new Runnable() {
    @Override
    public void run() {
      if (isTimeout && isShowing && isRunning && isReplay) {

        Set<Map.Entry<String, List<Node<InputEvent>>>> set = new LinkedHashMap<>(waitMap).entrySet();
        for (Map.Entry<String, List<Node<InputEvent>>> entry : set) {
          List<Node<InputEvent>> list = entry == null ? null : entry.getValue();
          if (list == null || list.isEmpty()) {
            continue;
          }

          list = new ArrayList<>(list);
          for (Node<InputEvent> node : list) {
            if (node == null || node.disable) {
              continue;
            }

            try {
                if (node.type == InputUtil.EVENT_TYPE_HTTP) {
                    Activity activity = getCurrentActivity();
                    Fragment fragment = getCurrentFragment();
                    DialogInterface dialog = getCurrentDialog();

                    JSONObject obj = node.obj;
                    String query = obj == null ? null : obj.getString("query");
                    String header = obj == null ? null : obj.getString("header");
                    String reqHeader = obj == null ? null : obj.getString("reqHeader");
                    String resHeader = obj == null ? null : obj.getString("resHeader");
                    String request = obj == null ? null : obj.getString("request");
                    String response = obj == null ? null : obj.getString("response");

                    if (node.action < 0 || node.action == InputUtil.HTTP_ACTION_RESPONSE) {
                        if (StringUtil.isEmpty(resHeader)) {
                            resHeader = header;
                        }

                        String key = getWaitKey(node);
                        HttpManager.OnHttpResponseListener listener = listenerMap.remove(key);
      //                if (listener == null) {
      //                  if (fragment instanceof HttpManager.OnHttpResponseListener) {
      //                    listener = (HttpManager.OnHttpResponseListener) fragment;
      //                  } else if (activity instanceof HttpManager.OnHttpResponseListener) {
      //                    listener = (HttpManager.OnHttpResponseListener) activity;
      //                  }
      //                }

                        if (listener != null) {
                            try {
                                listener.onHttpResponse(node.requestCode, response, node.exception);
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (StringUtil.isEmpty(reqHeader)) {
                        reqHeader = header;
                    }

                    try {
                        String url = StringUtil.trim(node.url);
                        onHTTPEvent(node.action, node.format, node.method, node.host, StringUtil.isEmpty(query) || url.endsWith(query) ? url : url + (url.contains("?") ? "&" : "?") + query
                                , reqHeader, request, node.status, response, resHeader, node.exception
                                , StringUtil.isEmpty(node.activity) || !Objects.equals(node.activity, activity.getClass().getName()) ? null : activity
                                , StringUtil.isEmpty(node.fragment) || !Objects.equals(node.fragment, fragment.getClass().getName()) ? null : fragment
                                , StringUtil.isEmpty(node.dialog) || !Objects.equals(node.dialog, dialog.getClass().getName()) ? null : dialog
                                , null
                        );
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                if (node.type == InputUtil.EVENT_TYPE_UI && StringUtil.isEmpty(node.fragment)) {
                    if (node.action == InputUtil.UI_ACTION_CREATE && sendActivityCreate(node)) {
                        continue;
                    }
                    if (node.action == InputUtil.UI_ACTION_RESULT && sendActivityResult(node)) {
                        continue;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
          }
        }

//        waitMap = new LinkedHashMap<>();

//        if (currentEventNode != null && currentEventNode.type == InputUtil.EVENT_TYPE_UI
//        ) { // 都超时了必须 mock  && (currentEventNode.mock == null || currentEventNode.mock)) {
//          if (currentEventNode.action == InputUtil.UI_ACTION_CREATE && StringUtil.isEmpty(currentEventNode.fragment) && sendActivityCreate(currentEventNode)) {
//            return;
//          }
//          if (currentEventNode.action == InputUtil.UI_ACTION_RESULT && StringUtil.isEmpty(currentEventNode.fragment) && sendActivityResult(currentEventNode)) {
//            return;
//          }
//        }

        forward(false);
      }
    }
  };

  private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("mm:ss");

  private final Handler mainHandler = new Handler(Looper.getMainLooper());

  private Map<String, List<Node<InputEvent>>> waitMap = new LinkedHashMap<>();
  private Node<InputEvent> firstUIWaitNode = null;
  private Node<InputEvent> lastHTTPWaitNode = null;

  private boolean isRunning = false;
  private boolean isReplay = false;
  private boolean isReplayingTouch = false;
  private boolean isReplayingEdit = false;
  public boolean isReplayingInput() {
    return isReplayingTouch || isReplayingEdit;
  }

  @SuppressLint("HandlerLeak")
  private final Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);

      if (isReplay && isSplitShowing) {
        //通过遍历数组来实现
        // if (currentTime >= System.currentTimeMillis()) {
        //     isReplaying = false;
        //     pbUIAutoSplitY.setVisibility(View.GONE);
        // }
        //
        // MotionEvent event = (MotionEvent) msg.obj;
        // dispatchEventToCurrentActivity(event);

        //根据递归链表来实现，能精准地实现两个事件之间的间隔，不受处理时间不一致，甚至卡顿等影响。还能及时终止

        Node<InputEvent> curNode = (Node<InputEvent>) msg.obj; // isReplayingInput() 导致有时候会回退步骤，需要跳过
        int stp = step;
        while (curNode != null && (curNode.disable || curNode.step < stp)) { // (curNode.disable || curNode.item == null)) {
          currentEventNode = curNode = curNode.next;
//          if (curNode != null && curNode.step > stp) {
//            step = Math.max(step + 1, curNode.step);
//          }

          // if (curNode != null && curNode.item != null) {
          //   output(null, curNode, activity);
          // }
        }
        currentEventNode = curNode;
        step = curNode == null ? step + 1 : Math.max(step, curNode.step);

        // output(null, curNode, activity);

        boolean isOver = step > allStep || curNode == null;
        if (isOver && step < allStep) {
          step = allStep + 1;
        }

        boolean canRefreshUI = isOver || curNode.type != InputUtil.EVENT_TYPE_TOUCH || curNode.action != MotionEvent.ACTION_MOVE;

        if (canRefreshUI) {
          tvControllerCount.setText(step + "/" + allStep);
          updateTime();
          onEventChange(step - 1, curNode == null ? 0 : curNode.type);  // move 时刷新容易卡顿
        }

        isTimeout = false;
        mainHandler.removeCallbacks(timeoutRunnable);

        if (isOver) {
          //TODO output()
          isRunning = false;

          tvControllerPlay.setText(R.string.replay);
          showCoverAndSplit(true, false);
          isSplitShowing = false;
          isSplit2Showing = false;
          waitMap = new LinkedHashMap<>();
          return;
        }

        InputEvent curItem = curNode.item;
        boolean isTouch = curNode.type == InputUtil.EVENT_TYPE_TOUCH && curItem instanceof MotionEvent;
        boolean isEdit = curNode.type == InputUtil.EVENT_TYPE_KEY && curItem instanceof KeyEvent;
        int action = isTouch ? ((MotionEvent) curItem).getAction() : -1;
        boolean isDown = action == (isTouch ? MotionEvent.ACTION_DOWN : KeyEvent.ACTION_DOWN);
        boolean isRT = isReplayingTouch;
        boolean isRE = isReplayingEdit;
        if (isTouch) {
          if (isDown) {
            isReplayingTouch = isRT = true;
          } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            isRT = false;
          }
        } else if (isEdit) {
          if (isDown) {
            isReplayingEdit = isRE = true;
          } else if (action == KeyEvent.ACTION_UP) {
            Node<InputEvent> nextNode = curNode.next;
            while (nextNode != null) {
              int type = nextNode.type;
              if (type == InputUtil.EVENT_TYPE_TOUCH || (type == InputUtil.EVENT_TYPE_UI
                      && (nextNode.disable == false || action != InputUtil.UI_ACTION_RESUME))
              ) {
                isRE = false;
                break;
              }

              if (nextNode.disable == false && (type == InputUtil.EVENT_TYPE_KEY
                      || (nextNode.targetId == curNode.targetId
                      || Objects.equals(nextNode.targetIdName, curNode.targetIdName)))
              ) {
                break;
              }

              nextNode = nextNode.next;
            }
          }
        }

        // isReplayingInput() = isReplayingTouch || isReplayingEdit;
        boolean isRI = isRT || isRE;
        //暂停，等待时机
        if (isRI == false && (curItem == null
                || (curNode.type == InputUtil.EVENT_TYPE_UI && StringUtil.isEmpty(curNode.fragment)
                && (action == InputUtil.UI_ACTION_CREATE || action == InputUtil.UI_ACTION_RESULT))
           || (curNode.type == InputUtil.EVENT_TYPE_HTTP && ! waitMap.isEmpty()))) { // curNode.type == InputUtil.EVENT_TYPE_UI || curNode.type == InputUtil.EVENT_TYPE_HTTP) {
          long timeout = curNode.timeout;
          if (curNode.type == InputUtil.EVENT_TYPE_UI && step <= 1 && curNode.action == InputUtil.UI_ACTION_RESUME) { // action 在上面可能赋值 -1 action == InputUtil.UI_ACTION_RESUME
              ensureCorrectActivity(activity, curNode);
              Node<InputEvent> nextNode = curNode.next;
              timeout = 2000 + (nextNode == null ? 0 : nextNode.time - curNode.time);
          }

          if (timeout <= 0) {
            timeout = STEP_TIMEOUT;
          }
          if (timeout <= 0) {
            return;
          }

          isTimeout = true;
          mainHandler.postDelayed(timeoutRunnable, timeout);
          return;
        }

        if (isTouch) { // canRefreshUI && isTouch && isDown) {
          isSplit2Showing = curNode.isSplit2Show;
          splitX = curNode.splitX;
          splitY = curNode.splitY;
          splitX2 = curNode.splitX2;
          splitY2 = curNode.splitY2;
          ballGravity = curNode.ballGravity;
          ballGravity2 = curNode.ballGravity2;
          gravityX = curNode.gravityX;
          gravityY = curNode.gravityY;

          double x = curNode.x;
          double y = curNode.y;
          double x2 = curNode.x2;
          double y2 = curNode.y2;

          boolean isPoint = x != 0 && y != 0;
          boolean isPoint2 = x2 != 0 && y2 != 0;
          boolean isSplit2Show = isPoint2 || isSplit2Showing;

          double sx = isPoint ? x : splitX;
          double sy = isPoint ? y : splitY;
          double sx2 = isPoint2 ? x2 : splitX2;
          double sy2 = isPoint2 ? y2 : splitY2;

//          if (isSplitShowing) { // && floatBall != null) {
            //居然怎么都不更新 vSplitX 和 vSplitY
            // floatBall.hide();
            // floatBall.updateX(windowX + splitX - splitRadius);
            // floatBall.updateY(screenY + splitY - splitRadius);
            // floatBall.show();

            //太卡  FIXME 改了之后还是这样吗？
//            if (floatBall.getX() != (curNode.splitX - splitRadius + windowWidth)
//              || floatBall.getY() != (curNode.splitY - splitRadius + windowHeight)) {
              // FloatWindow.destroy("floatBall");
              // floatBall = null;
              floatBall = showSplit(floatBall, true, sx, sy, "floatBall", vFloatBall, floatSplitX, floatSplitY, isDown);
//            }

//            if (isSplit2Showing) {
              floatBall2 = showSplit(floatBall2, isSplit2Show, sx2, sy2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2, isDown);
//            }
//          }
        }

        // 分拆为下面两条，都放在 UI 操作后，减少延迟
        // dispatchEventToCurrentActivity(curItem, false);

        Node<InputEvent> nextNode = curNode.next;
//        long firstTime = nextNode == null ? 0 : nextNode.time;
//        while (nextNode != null && nextNode.disable) {
//          // if (nextNode.item != null) {
//          //   output(null, nextNode, activity);
//          // }
//
//          nextNode = nextNode.next;
//          step ++;
//        }
//        step = curNode == null ? step + 1 : curNode.step;
        // long lastTime = nextNode == null ? 0 : nextNode.time;

        if (isRI == false) {
          waitMap = new LinkedHashMap<>();
          firstUIWaitNode = null;
          lastHTTPWaitNode = null;
        }

        //        int lastStep = step;
        //        int lastWaitStep = 0;
        Node<InputEvent> lastNextNode = nextNode;

        Activity activity = getCurrentActivity();
        while (lastNextNode != null && (lastNextNode.disable || (lastNextNode.item == null
//                && (lastNextNode.type != InputUtil.EVENT_TYPE_UI || lastNextNode.fragment != null || (
//                        lastNextNode.action != InputUtil.UI_ACTION_CREATE
//                        && lastNextNode.action != InputUtil.UI_ACTION_RESULT))
//                && StringUtil.isEmpty(curNode.fragment)
          ))
//                && (activity == null || Objects.equals(lastNextNode.activity, activity.getClass().getName()))
        ) {
          String url = lastNextNode.url;

          if (lastNextNode.disable) {
            nextNode = nextNode.next;
          }
          else if (lastNextNode.item == null
//                  && Objects.equals(lastNextNode.fragment, fragment == null ? null : fragment.getClass().getName())
                  && StringUtil.isNotEmpty(url, true)
          ) {

            if (lastNextNode.type == InputUtil.EVENT_TYPE_UI && StringUtil.isEmpty(lastNextNode.fragment)
                    && (lastNextNode.action == InputUtil.UI_ACTION_CREATE || lastNextNode.action == InputUtil.UI_ACTION_RESULT)) {
              if (firstUIWaitNode != null) {
                break;
              }

              firstUIWaitNode = lastNextNode;
            }

            String key = getWaitKey(lastNextNode);
            List<Node<InputEvent>> list = waitMap.get(key);
            if (list == null) {
              list = new ArrayList<>();
            }
            list.add(lastNextNode);
            waitMap.put(key, list);

            if (lastNextNode.type == InputUtil.EVENT_TYPE_HTTP) {
              lastHTTPWaitNode = lastNextNode;
            }
//            lastWaitStep = lastStep;
          }

          lastNextNode = lastNextNode.next;
          lastStep ++;
        }

        if (isRI == false && (firstUIWaitNode != null || lastHTTPWaitNode != null)) {
          if (lastHTTPWaitNode == null) {
            nextNode = firstUIWaitNode;
          } else if (firstUIWaitNode == null) {
            nextNode = lastHTTPWaitNode;
          } else {
            nextNode = lastHTTPWaitNode.step >= firstUIWaitNode.step ? lastHTTPWaitNode : firstUIWaitNode;
          }
//          step = lastWaitStep;
        }

        msg = new Message();
        msg.obj = nextNode;

        InputEvent nextItem = nextNode == null ? null : nextNode.item;
        //暂停，等待时机
        if (nextNode != null && nextItem == null) { // (nextNode.type == InputUtil.EVENT_TYPE_UI || nextNode.type == InputUtil.EVENT_TYPE_HTTP)) {
          // step --;

//          if (lastWaitStep > 0) {
//            step = lastWaitStep - 1;
//          }
// 导致重复添加到 waitMap          handleMessage(msg);

          isReplayingTouch = isRT;
          isReplayingEdit = isRE;
          dispatchEventToCurrentWindow(curNode, curItem, false);
//          isReplayingTouch = isRT;
          handleMessage(msg);
        }
        else {
          output(null, curNode, activity);
          isReplayingTouch = isRT;
          isReplayingEdit = isRE;
          dispatchEventToCurrentWindow(curNode, curItem, false);
//          isReplayingTouch = isRT;

          long duration = calcDuration(curNode, nextNode);

          if (duration <= 0) {
            handleMessage(msg);
//            dispatchEventToCurrentWindow(curItem, false);
          }
          else {
//            dispatchEventToCurrentWindow(curItem, false);
            sendMessageDelayed(msg, duration); // 相邻执行事件时间差本身就包含了  + (lastTime <= 0 || firstTime <= 0 ? 10 : lastTime - firstTime)  // 补偿 disable 项跳过的等待时间
          }
        }

      }
    }
  };

  private long calcDuration(Node<InputEvent> prevNode, Node<InputEvent> curNode) {
    // MotionEvent 是系统启动时间 326941454，UNKNOWN KeyEvent 是当前时间
    InputEvent prevItem = prevNode == null ? null : prevNode.item;
    InputEvent curItem = curNode == null ? null : curNode.item;

    long pet = prevItem == null ? 0 : prevItem.getEventTime();
    long cet = pet <= 0 || curItem == null ? 0 : curItem.getEventTime();
    long dur = cet - pet;
    long dur2 = curNode == null || prevNode == null ? 0 : curNode.time - prevNode.time;
    // dur = dur > 60*1000 ? 0 : dur;
    // dur2 = dur2 > 60*1000 ? 0 : dur2;
    long duration = dur <= 0 ? (dur2 <= 0 ? 0 : dur2) : (dur2 <= 0 || dur <= 60*1000 ? dur : Math.min(dur, dur2));

    return duration > 0 ? duration : (curItem == null ? 0 : 1);
  }

  private String getWaitKey(Node<InputEvent> node) {
    return getWaitKey(node.type, node.action, node.method, node.host, node.url);
  }
  private String getWaitKey(int type, int action, String method, String host, String url) {
    return type + ":" + action + ": " + method + " " + url;
  }

  public void post(@NonNull Runnable r) {
    handler.post(r);
  }
  public void postDelayed(@NonNull Runnable r, long delayMillis) {
    handler.postDelayed(r, delayMillis);
  }



  private Activity activity;
  private Fragment fragment;
//  private DialogInterface dialog;
  @NonNull
  private final Map<Activity, List<DialogInterface>> dialogMap = new LinkedHashMap<>();
  private JSONObject ignoreActivityViewListMap;
  public JSONObject getIgnoreActivityViewListMap() {
    return ignoreActivityViewListMap;
  }
  public UIAutoApp setIgnoreActivityViewListMap(JSONObject ignoreActivityViewListMap) {
    this.ignoreActivityViewListMap = ignoreActivityViewListMap;
    setIgnoreViewList();
    return this;
  }

  private UIAutoApp setIgnoreViewList() {
    return setIgnoreViewList(null);
  }
  private UIAutoApp setIgnoreViewList(String pageName) {
    DialogInterface dialog = ignoreActivityViewListMap == null ? null : getCurrentDialog();
    List<String> list = ignoreActivityViewListMap == null ? null : JSON.parseArray(
            ignoreActivityViewListMap.getString(
                    StringUtil.isNotEmpty(pageName) ? pageName : (dialog != null ? dialog : getCurrentActivity()).getClass().getName()
            ), String.class
    );

    if (list != null && ! list.isEmpty()) {
      if (ignoreFindViewIdList == null) {
        ignoreFindViewIdList = new ArrayList<>();
      }
      if (ignoreFindViewTypeList == null) {
        ignoreFindViewTypeList = new ArrayList<>();
      }
      if (ignoreFindViewGroupIdList == null) {
        ignoreFindViewGroupIdList = new ArrayList<>();
      }
      if (ignoreFindViewGroupTypeList == null) {
        ignoreFindViewGroupTypeList = new ArrayList<>();
      }

      for (String view : list) {
        if (StringUtil.isEmpty(view)) {
          continue;
        }

        int ind = view.indexOf("@");
        String id = ind < 0 ? view : view.substring(0, ind);
        String type = ind < 0 ? null : view.substring(ind + 1);
        // FIXME 还不如带上 [] 或直接分开处理 ignoreActivityViewGroupListMap
        boolean isGroup = view.endsWith("]"); // ! (type.contains("TextView") || type.contains("ImageView") || type.contains("Button"));
        if (isGroup) {
          if (StringUtil.isNotEmpty(id) && ! ignoreFindViewGroupIdList.contains(id)) {
            ignoreFindViewGroupIdList.add(id);
          }
          if (StringUtil.isNotEmpty(type) && ! ignoreFindViewGroupTypeList.contains(type)) {
            ignoreFindViewGroupTypeList.add(type);
          }

          continue;
        }

        if (StringUtil.isNotEmpty(id) && ! ignoreFindViewIdList.contains(id)) {
          ignoreFindViewIdList.add(id);
        }
        if (StringUtil.isNotEmpty(type) && ! ignoreFindViewTypeList.contains(type)) {
          ignoreFindViewTypeList.add(type);
        }
      }
    }

    return this;
  }

  int screenWidth;
  int screenHeight;

  Window.Callback callback;
  Window window;
  View decorView;
  View contentView;

  public double windowWidth;
  public double windowHeight;
  double windowX;
  double windowY;

  double statusHeight;
  double navigationHeight;
  boolean isNavigationShow = false;
  boolean isSeparatedStatus = false;
  double decorX;
  double decorY;
  double decorWidth;
  double decorHeight;
  double fragmentX;
  double fragmentY;
  double fragmentWidth;
  double fragmentHeight;
  double dialogX;
  double dialogY;
  double dialogWidth;
  double dialogHeight;
  double keyboardHeight;

  ViewGroup vFloatCover;
  View vFloatController;
  FloatBallView vFloatBall, vFloatBall2;
  ViewGroup vSplitX, vSplitX2;
  ViewGroup vSplitY, vSplitY2;

  TextView tvControllerX;
  TextView tvControllerDouble;
  TextView tvControllerReturn;
  TextView tvControllerCount;
  TextView tvControllerPlay;
  TextView tvControllerTime;
  TextView tvControllerForward;
  TextView tvControllerSetting;
  TextView tvControllerY;

  ViewGroup rlControllerGravity;
  TextView tvControllerGravityX;
  TextView tvControllerGravityY;
  TextView tvControllerGravityContainer;

  RecyclerView rvControllerTag;



  // 都取负数，表示相对于最右侧和最下方还差多少
  private int gravityX = GRAVITY_CENTER;
  private int gravityY = GRAVITY_CENTER;

  private int ballGravity = GRAVITY_BOTTOM_RIGHT;
  private int ballGravity2 = GRAVITY_TOP_LEFT;

  private double splitX, splitX2;
  private double splitY, splitY2;
  private double splitSize;
  private double splitRadius;
  private int splitColor;

  @NotNull
  private JSONArray eventList = new JSONArray();
  public JSONArray getEventList() {
      return eventList;
  }

  private RecyclerView.Adapter tagAdapter;

  SharedPreferences cache;
  private long flowId = 0;

  File parentDirectory;
//  @Override
//  public void onCreate() {
//    super.onCreate();
//    instance = this;
//    initUIAuto(this);
//  }


  public Resources getResources() {
    return getApp().getResources();
  }
  public SharedPreferences getSharedPreferences() {
    return getApp().getSharedPreferences(TAG, Context.MODE_PRIVATE);
  }



  public void onUIAutoActivityCreate() {
    onUIAutoActivityCreate(getCurrentActivity());
  }

  private Map<FragmentManager, Boolean> fragmentWatchedMap = new HashMap<>();
  public void onUIAutoActivityCreate(@NonNull Activity activity) {
    onUIAutoActivityCreate(activity, false);
  }
  public void onUIAutoActivityCreate(@NonNull Activity activity, boolean showToolBar) {
    onUIAutoWindowCreate(activity, activity.getWindow(), null, null, showToolBar);
  }

  public void onUIAutoDialogShow(@NonNull Dialog dialog) {
    onUIAutoWindowCreate(dialog, dialog == null ? null : dialog.getWindow(), dialog);
  }
//  public void onUIAutoDialogShow(@NonNull DialogInterface dialog) {
//    onUIAutoWindowCreate(
//      dialog instanceof Window.Callback ? (Window.Callback) dialog: null
//      , dialog instanceof Dialog ? ((Dialog) dialog).getWindow() : null
//    );
//  }
  public void onUIAutoPopupWindowShow(@NonNull PopupWindow pw, View view, Window window, Activity activity, Fragment fragment) {
      if (activity == null) {
          activity = fragment == null ? null : fragment.getActivity();
          activity = activity != null ? activity : getCurrentActivity();
      }
      List<PopupWindow> list = popupWindowMap.get(activity);
      if (list == null) {
          list = new ArrayList<>();
      } else if (list.contains(pw)) {
          return;
      }

      list.add(pw);
      popupWindowMap.put(activity, list);

      if (window == null) {
          window = activity.getWindow();
      }

      Window.Callback callback = window == null ? null : window.getCallback();
      setCurrentView(view, callback, activity, fragment, null, pw);
      onUIAutoWindowCreate(callback, window, pw);

      Activity _activity = activity;
      pw.setTouchInterceptor(new View.OnTouchListener() {
          @Override
          public boolean onTouch(View v, MotionEvent event) {
            addInputEvent(event, callback, _activity, fragment, null, pw);
//          pw.dismiss();

//          if (event.getAction() == MotionEvent.ACTION_UP) {
//            setCurrentPopupWindow(null, null, callback, activity, fragment);
//          }
            return false;
          }
      });
  }

  public void onUIAutoWindowCreate(@NonNull Window.Callback callback, @NonNull Window window) {
    onUIAutoWindowCreate(callback, window, null, null);
  }
  public void onUIAutoWindowCreate(@NonNull Window.Callback callback, @NonNull Window window, DialogInterface dialogInterface) {
    onUIAutoWindowCreate(callback, window, dialogInterface, null);
  }
  public void onUIAutoWindowCreate(@NonNull Window.Callback callback, @NonNull Window window, PopupWindow popupWindow) {
    onUIAutoWindowCreate(callback, window, null, popupWindow);
  }
  public void onUIAutoWindowCreate(@NonNull Window.Callback callback, @NonNull Window window, DialogInterface dialogInterface, PopupWindow popupWindow) {
    onUIAutoWindowCreate(callback, window, dialogInterface, popupWindow, false);
  }

  int min = dp2px(10);

  public void onUIAutoWindowCreate(@NonNull Window.Callback callback, @NonNull Window window, DialogInterface dialogInterface, PopupWindow popupWindow, boolean showToolBar) {
      if (window == null || ! isShowing()) {
          return;
      }

      DialogInterface dialog = dialogInterface != null ? dialogInterface : (callback instanceof DialogInterface ? (DialogInterface) callback : null);
      if (isRunning() && isReplayingInput() && callback instanceof DialogInterface) {
          try {
              ((DialogInterface) callback).dismiss();
          } catch (Throwable e) {
              e.printStackTrace();
          }
          return;
      }

//    if (window == null) {
//      if (callback instanceof Activity) {
//        window = ((Activity) callback).getWindow();
//      }
//      else if (callback instanceof DialogInterface) {
//        window = ((DialogInterface) callback).getWindow();
//      }
//      else if (callback instanceof PopupWindow) {
//        window = ((uigo.x.PopupWindow) callback).getWindow();
//      }
//    }

//    if (callback instanceof DialogInterface) {
////      onUIAutoActivityDestroy(activity, activity);
//    }
//    if (this.window != null) {
//      this.window.setCallback(this.callback);
//    }
//    onUIAutoWindowDestroy(this.callback, this.window);

    if (dialog != null) {
        Activity activity = dialog instanceof Dialog ? ((Dialog) dialog).getOwnerActivity() : getCurrentActivity();
        List<DialogInterface> list = dialogMap.get(activity);
        if (list == null) {
            list = new ArrayList<>();
            dialogMap.put(activity, list);
        }
        if (! list.contains(dialog)) {
          list.add(dialog);
          setIgnoreViewList(dialog.getClass().getName());
        }
    }

    //反而让 vFloatCover 与底部差一个导航栏高度 window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    decorView = window.getDecorView(); // activity.findViewById(android.R.id.content);  // decorView = window.getContentView();

    decorView.post(new Runnable() {
      @Override
      public void run() {
        decorWidth = decorView.getWidth();
        decorHeight = decorView.getHeight();
      }
    });
    decorView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
      @Override
      public void onGlobalLayout() {
        decorWidth = decorView.getWidth();
//        keyboardHeight = decorHeight - decorView.getHeight();
        decorHeight = decorView.getHeight();

        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);

        double wh = windowHeight;
        boolean changed = wh != windowHeight;

        keyboardHeight = decorView.getHeight() - rect.bottom - (isNavigationShow ? navigationHeight : 0);

        windowWidth = rect.right - rect.left;
        windowHeight = rect.bottom - rect.top;

        if (isShowing && changed) {
          if (splitY < 0 || InputUtil.isBottom(ballGravity)) {
            splitY += (wh - windowHeight); // -= keyboardHeight;
            floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
          }

          if (splitY2 < 0 || InputUtil.isBottom(ballGravity2)) {
            splitY2 += (wh - windowHeight); // -= keyboardHeight;
            floatBall2 = showSplit(floatBall2, isSplitShowing && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);
          }
        }

        addTextChangedListener(decorView);
      }
    });

    addTextChangedListener(decorView);

    contentView = decorView.findViewById(android.R.id.content);
    if (contentView == null && decorView instanceof ViewGroup) {
      contentView = ((ViewGroup) decorView).getChildAt(0);
    }

    View dlgView = contentView == null ? decorView : contentView;
    if (dialog != null && dlgView != null) {
      View dv = dlgView instanceof ViewGroup ? ((ViewGroup) dlgView).getChildAt(0) : null; // contentView.findViewById(android.R.id.content);
      View dialogView = dv == null ? dlgView : dv;

      if (dialogView != null) {
        dialogView.post(new Runnable() {
          @Override
          public void run() {
            updateDialogView(dialogView);
            double sx = splitX > 0 ? splitX : splitX + windowWidth;
            double sy = splitY > 0 ? splitY : splitY + windowHeight;
            double sx2 = splitX2 > 0 ? splitX2 : splitX2 + windowWidth;
            double sy2 = splitY2 > 0 ? splitY2 : splitY2 + windowHeight;

            double l = dialogX;
            double r = dialogX + dialogWidth;
            double t = dialogY;
            double b = dialogY + dialogHeight;

            if (sx < l || sx > r || sy < t || sy > b || (isSplit2Showing && (sx2 < l || sx2 > r || sy2 < t || sy2 > b))) {
              if (isSplit2Showing) {
                splitX = l;
                splitY = t - (isSeparatedStatus ? statusHeight : 0);
                splitX2 = r - windowWidth;
                splitY2 = b - windowHeight - (isSeparatedStatus ? statusHeight : 0);

                ballGravity = GRAVITY_TOP_LEFT;
                ballGravity2 = GRAVITY_BOTTOM_RIGHT;

                gravityX = GRAVITY_CENTER;
                gravityY = GRAVITY_CENTER;
              }
              else {
                splitX = l + dialogWidth/2 + (l/2 + r/2 < windowWidth/2 ? 0 : - windowWidth);
                splitY = t + dialogHeight/2 + (t/2 + b/2 < windowHeight/2 ? 0 : - windowHeight) - (isSeparatedStatus ? statusHeight : 0);
              }

              floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
              floatBall2 = showSplit(floatBall2, isSplitShowing && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);
            }

            dialogView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
              @Override
              public void onGlobalLayout() {
                updateDialogView(dialogView);
              }
            });
          }
        });
      }
    }

    Window.Callback windowCallback = window.getCallback();

    this.window = window;
    this.callback = windowCallback;
    window.setCallback(new Window.Callback() {
      @Override
      public boolean dispatchKeyEvent(KeyEvent event) {
//				dispatchEventToCurrentActivity(event);
        addInputEvent(event, callback, activity, fragment, dialog, popupWindow);
        return windowCallback != null && windowCallback.dispatchKeyEvent(event);
      }

      @Override
      public boolean dispatchKeyShortcutEvent(KeyEvent event) {
//				dispatchEventToCurrentActivity(event);
        addInputEvent(event, callback, activity, fragment, dialog, popupWindow);
        return windowCallback != null && windowCallback.dispatchKeyShortcutEvent(event);
      }

      @Override
      public boolean dispatchTouchEvent(MotionEvent event) {
//				dispatchEventToCurrentActivity(event);  FIXME 从 2026/1/24 2c58331f 开始发现 长按 回放失败！
        addInputEvent(event, callback, activity, fragment, dialog, popupWindow);
        return windowCallback != null && windowCallback.dispatchTouchEvent(event);
      }

      @Override
      public boolean dispatchTrackballEvent(MotionEvent event) {
//				dispatchEventToCurrentActivity(event);
        addInputEvent(event, callback, activity, fragment, dialog, popupWindow);
        return windowCallback != null && windowCallback.dispatchTrackballEvent(event);
      }

      @Override
      public boolean dispatchGenericMotionEvent(MotionEvent event) {
//				dispatchEventToCurrentActivity(event);
// 和 dispatchTouchEvent 重复                addInputEvent(event, activity);
        return windowCallback != null && windowCallback.dispatchGenericMotionEvent(event);
      }

      @Override
      public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return windowCallback != null && windowCallback.dispatchPopulateAccessibilityEvent(event);
      }

      @Nullable
      @Override
      public View onCreatePanelView(int featureId) {
        return windowCallback == null ? null : windowCallback.onCreatePanelView(featureId);
      }

      @Override
      public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return windowCallback != null && windowCallback.onCreatePanelMenu(featureId, menu);
      }

      @Override
      public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return windowCallback != null && windowCallback.onPreparePanel(featureId, view, menu);
      }

      @Override
      public boolean onMenuOpened(int featureId, Menu menu) {
        return windowCallback != null && windowCallback.onMenuOpened(featureId, menu);
      }

      @Override
      public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return windowCallback != null && windowCallback.onMenuItemSelected(featureId, item);
      }

      @Override
      public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onWindowAttributesChanged(attrs);
      }

      @Override
      public void onContentChanged() {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onContentChanged();
      }

      @Override
      public void onWindowFocusChanged(boolean hasFocus) {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onWindowFocusChanged(hasFocus);
      }

      @Override
      public void onAttachedToWindow() {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onAttachedToWindow();
      }

      @Override
      public void onDetachedFromWindow() {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onDetachedFromWindow();
      }

      @Override
      public void onPanelClosed(int featureId, Menu menu) {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onPanelClosed(featureId, menu);
      }

      @Override
      public boolean onSearchRequested() {
        return windowCallback != null && windowCallback.onSearchRequested();
      }

      @RequiresApi(api = Build.VERSION_CODES.M)
      @Override
      public boolean onSearchRequested(SearchEvent searchEvent) {
        return windowCallback != null && windowCallback.onSearchRequested(searchEvent);
      }

      @Nullable
      @Override
      public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return windowCallback == null ? null : windowCallback.onWindowStartingActionMode(callback);
      }

      @RequiresApi(api = Build.VERSION_CODES.M)
      @Nullable
      @Override
      public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return windowCallback == null ? null : windowCallback.onWindowStartingActionMode(callback, type);
      }

      @Override
      public void onActionModeStarted(ActionMode mode) {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onActionModeStarted(mode);
      }

      @Override
      public void onActionModeFinished(ActionMode mode) {
        if (windowCallback == null) {
          return;
        }
        windowCallback.onActionModeFinished(mode);
      }
    });

    updateScreenWindowContentSize();

    cache = cache != null ? cache : getSharedPreferences();

    splitColor = cache.getInt(SPLIT_COLOR, 0);
    splitSize = cache.getFloat(SPLIT_SIZE, 0);
    if (splitSize < min) {
      splitSize = dp2px(60);
    }
    splitRadius = splitSize/2;

    View view = popupWindow == null ? null : popupWindow.getContentView();
    boolean notPW = view == null; // || popupWindow == null || view.getWidth() < min || view.getHeight() < min;
    Object key = notPW ? (dialog != null ? dialog : (fragment != null ? fragment : activity)) : popupWindow;
    if (key == null) {
      key = getCurrentActivity();
    }

    String clsKey = null;
    BallPoint[] points = ballPositionMap.get(key);
    if (key != null && (points == null || points.length < 1)) {
      clsKey = key.getClass().getName() + (
              key instanceof Activity ? "" : "@" + getCurrentActivity().getClass().getName()
      );
      points = classBallPositionMap.get(clsKey);
    }
    BallPoint p = points == null || points.length < 1 ? null : points[0];
    if ((p == null || (p.x <= 0 && p.y <= 0)) && clsKey != null && ! notPW) {
      String _clsKey = clsKey;
      view.post(new Runnable() {
        @Override
        public void run() {
          int[] loc = new int[2];
          view.getLocationOnScreen(loc);

          // Rect rect = new Rect();
          // view.getWindowVisibleDisplayFrame(rect);

          int w = view.getWidth();
          int h = view.getHeight();

          int left = loc[0]; // 居然是 -100000 rect.left; //
          int right = left + w; // 居然是 -100000 rect.right;
          int top = loc[1]; // 居然是 -100000 rect.top;
          int bottom = top + h; // 居然是 -100000 rect.bottom;

          boolean isLeft = left < screenWidth - right && w < screenWidth/2;
          boolean isTop = top < screenHeight - bottom && h < screenHeight/2;

          ballGravity = isLeft ? (isTop ? GRAVITY_TOP_LEFT : GRAVITY_BOTTOM_LEFT) : (isTop ? GRAVITY_TOP_RIGHT : GRAVITY_BOTTOM_RIGHT);
          splitX = (isLeft ? right + splitRadius : left - screenWidth - splitRadius);
          splitY = (isTop ? bottom + splitRadius : top - screenHeight - splitRadius); // - (isSeparatedStatus ? statusHeight : 0);
          BallPoint p = new BallPoint(ballGravity, splitX, splitY);

          classBallPositionMap.put(_clsKey, new BallPoint[]{p});
          floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
        }
      });
    }

    if (p != null) {
      ballGravity = p.gravity;
      splitX = p.x;
      splitY = p.y;
    }
    else {
      ballGravity = cache.getInt(BALL_GRAVITY, GRAVITY_BOTTOM_RIGHT);
      splitX = cache.getFloat(SPLIT_X, 0);
      splitY = cache.getFloat(SPLIT_Y, 0);
    }

    if (Math.abs(splitX) < min || Math.abs(splitX) >= windowWidth - min) { // decorWidth) {
      splitX = -splitSize - (dialog != null ? dialogX + dialogWidth/2 : dp2px(50)); // 同一个 Window，没必要 (fragment != null ? fragmentX + fragmentWidth - windowWidth - dip2px(30) : dip2px(30)));
    }
    if (Math.abs(splitY) < min || Math.abs(splitY) >= windowHeight - min) { // decorHeight) {
      splitY = -splitSize - (dialog != null ? dialogY + dialogHeight/2 : dp2px(50)) - (isSeparatedStatus ? statusHeight : 0); // 同一个 Window，没必要 (fragment != null ? fragmentY + fragmentHeight - dip2px(30) : dip2px(30)));
    }

    if (key != null && (points == null || points.length < 2)) {
      points = classBallPositionMap.get(key.getClass().getName());
    }
    BallPoint p2 = points == null || points.length < 2 ? null : points[1];
    isSplit2Showing = p2 != null;
    if (isSplit2Showing) {
      ballGravity2 = p2.gravity;
      splitX2 = p2.x;
      splitY2 = p2.y;
    }
    else {
      ballGravity2 = cache.getInt(BALL_GRAVITY2, GRAVITY_TOP_LEFT);
      splitX2 = cache.getFloat(SPLIT_X2, 0);
      splitY2 = cache.getFloat(SPLIT_Y2, 0);
    }

    if (Math.abs(splitX2) < min || Math.abs(splitX2) >= windowWidth - min) { // decorWidth) {
      splitX2 = splitSize + dp2px(50);
    }
    if (Math.abs(splitY2) < min || Math.abs(splitY2) >= windowHeight - min) { // decorHeight) {
      splitY2 = splitSize + dp2px(50) - (isSeparatedStatus ? statusHeight : 0);
    }

    if (showToolBar) {
      showCover(true);
    }

    rlControllerGravity.setVisibility(isSplit2Showing ? View.VISIBLE : View.GONE);

    // if (isSplitShowing) {
    floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
    // if (isSplit2Showing) {

    floatBall2 = showSplit(floatBall2, isSplitShowing && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);
    // }

    if (isSplit2Showing == false) {
//      if (floatBall2 != null) {
////        floatBall2.show();
//        floatBall2.hide(); // FIXME 无效，FloatWindow bug，切换 Activity 时 hide 无效
//      }
//      if (floatSplitX2 != null) {
////        floatSplitX2.show();
//        floatSplitX2.hide();
//      }
//      if (floatSplitY2 != null) {
////        floatSplitY2.show();
//        floatSplitY2.hide();
//      }

      // FIXME 导致副悬浮球的分割线一直不显示
      try {
        FloatWindow.destroy("floatBall2");
      } catch (Throwable e) {
        e.printStackTrace();
      }
      try {
        FloatWindow.destroy("floatSplitX2"); // 这个才生效
      } catch (Throwable e) {
        e.printStackTrace();
      }
      try {
        FloatWindow.destroy("floatSplitY2"); // 这个才生效
      } catch (Throwable e) {
        e.printStackTrace();
      }

      floatBall2 = null;
      floatSplitX2 = null;
      floatSplitY2 = null;
    }

    setSplit();

    // FIXME 导致意外显示双分割球
//    if (isSplit2Showing == false) {
//      new Handler().postDelayed(new Runnable() {
//        @Override
//        public void run() {
//          vFloatBall2.setVisibility(View.GONE);
//          vSplitX2.setVisibility(View.GONE);
//          vSplitY2.setVisibility(View.GONE);
//        }
//      }, 500);
//    }
  }

  private Map<EditText, TextWatcher> editTextWatchedMap = new HashMap<>();
  public void clearTextChangedListener() {
    Set<Map.Entry<EditText, TextWatcher>> set = editTextWatchedMap.entrySet();
    for (Map.Entry<EditText, TextWatcher> entry : set) {
        entry.getKey().removeTextChangedListener(entry.getValue());
    }
  }

  public void addTextChangedListener(View view) {
    if (view instanceof EditText) {
      EditText et = (EditText) view;
      TextWatcher watcher = editTextWatchedMap.get(et);
      if (watcher == null) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
          et.addOnUnhandledKeyEventListener(new View.OnUnhandledKeyEventListener() {
            @Override
            public boolean onUnhandledKeyEvent(View v, KeyEvent event) {
              return false;
            }
          });
        }

        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (isSplitShowing == false || isReplay) {
                    return;
                }

                InputEvent ie = new EditTextEvent(KeyEvent.ACTION_UP, 0, et, EditTextEvent.WHEN_BEFORE
                        , StringUtil.getString(et.getText()), et.getSelectionStart(), et.getSelectionEnd(), s, start, count, after);
                addInputEvent(ie, callback, activity, fragment, getCurrentDialog(), null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isSplitShowing == false || isReplay) {
                    return;
                }

                InputEvent ie = new EditTextEvent(KeyEvent.ACTION_UP, 0, et, EditTextEvent.WHEN_ON
                        , StringUtil.getString(et.getText()), et.getSelectionStart(), et.getSelectionEnd(), s, start, count);
                addInputEvent(ie, callback, activity, fragment, getCurrentDialog(), null);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSplitShowing == false || isReplay) {
                    return;
                }

                InputEvent ie = new EditTextEvent(KeyEvent.ACTION_UP, 0, et, EditTextEvent.WHEN_AFTER
                        , StringUtil.getString(et.getText()), et.getSelectionStart(), et.getSelectionEnd(),s);
                addInputEvent(ie, callback, activity, fragment, getCurrentDialog(), null);
            }
        };
        et.addTextChangedListener(watcher);
        editTextWatchedMap.put(et, watcher);
      }
    }

    if (view instanceof ViewGroup) {
      ViewGroup vg = (ViewGroup) view;

      if (canScroll(vg)) {
        vg.post(new Runnable() {
          @Override
          public void run() {
            int[] loc = new int[2];
            vg.getLocationOnScreen(loc);
            int w = vg.getWidth();
            int h = vg.getHeight();

//            Rect rect = new Rect();
//            vg.getGlobalVisibleRect(rect);

            vg.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
              @Override
              public void onScrollChanged() {
                if (isShowing == false || isReplay || isTouching || ! isAutoMoveBall) {
                  return;
                }

                int[] loc2 = new int[2];
                vg.getLocationOnScreen(loc2);

//                Rect rect2 = new Rect();
//                vg.getGlobalVisibleRect(rect2);

                int w2 = vg.getWidth();
                int h2 = vg.getHeight();
                if (w2 + h2 <= dp2px(60) || Math.pow(loc2[0] - loc[0], 2) + Math.pow(loc2[1] - loc[1], 2)
                        + Math.pow(w2 - w, 2) + Math.pow(h2 - h, 2) > dp2px(10)) {
                  return;
                }

//                if (Math.pow(rect2.left - rect.left, 2) + Math.pow(rect2.right - rect.right, 2)
//                        + Math.pow(rect2.top - rect.top, 2) + Math.pow(rect2.bottom - rect.bottom, 2) > dip2px(4)) {
//                  return;
//                }

                currentScrollableView = vg;
                onViewReachParentBound(vg);
              }
            });

          }
        });

      }

      for (int i = 0; i < vg.getChildCount(); i++) {
        View cv = vg.getChildAt(i);
        addTextChangedListener(cv);
      }
    }
  }

  private boolean isTouching = false;
  private ViewGroup currentScrollableView;
  private void onViewReachParentBound(ViewGroup vg) {
    //            if (vg != lastScrollableView) {
//              return;
//            }
    if (vg == null || isShowing == false || isReplay || isTouching) { // onScrollChanged 只在触屏时回调  || lastDownEvent != null) {
      return;
    }

    boolean isMinLeft = ! vg.canScrollHorizontally(-1);
    boolean isMaxRight = ! vg.canScrollHorizontally(1);
    boolean isMinTop = ! vg.canScrollVertically(-1);
    boolean isMaxBottom = ! vg.canScrollVertically(1);
    if (isMinLeft == isMaxRight && isMinTop == isMaxBottom) {
      return;
    }
    int[] loc = new int[2];
    vg.getLocationOnScreen(loc);
    if (loc[0] < 0 || loc[1] < 0) {
      return;
    }

    ViewGroup sv = vg; // lastScrollableView; // findViewByPoint(decorView, null, event.getHistoricalX(0), event.getHistoricalY(0), FOCUS_ANY, false, CAN_SCROLL_UNSPECIFIED);
    ViewGroup pv = sv;
    if (pv instanceof ScrollView || pv instanceof HorizontalScrollView || pv instanceof NestedScrollView) {
      View v = pv.getChildCount() <= 0 ? null : pv.getChildAt(0);
      if (v instanceof ViewGroup) {
        pv = (ViewGroup) v;
      } else {
        return;
      }
    }

    int cc = pv.getChildCount();
    int lp = cc - 1;

//            double dx = sv.getScrollX() - lastScrollX; // FIXME 必须等停下来
//            double dy = sv.getScrollY() - lastScrollY; // FIXME 必须等停下来
//          if (dx <= 0 && dy <= 0 && lastDownEvent != null) {
//            dx = event.getX() - lastDownEvent.getX();
//            dy = event.getY() - lastDownEvent.getY();
//          }

    boolean canScrollHorizontally = isMinLeft != isMaxRight; // dx > dip2px(2); // && canScrollHorizontally(sv);
    boolean canScrollVertically = isMinTop != isMaxBottom; // dy > dip2px(2); // && canScrollVertically(sv);

    View fv = pv.getChildAt(0);
    View lv = pv.getChildAt(lp);

    int svl = sv.getPaddingLeft();
    int svr = sv.getWidth() - sv.getPaddingRight();
    int svt = sv.getPaddingTop();
    int svb = sv.getHeight() - sv.getPaddingBottom();

    boolean change = false;

    if (isSplit2Showing) {
      if (canScrollHorizontally) {
        if (isMinLeft || (gravityX == GRAVITY_RIGHT && fv != null && (fv.getLeft() == svl || fv.getRight() == svl))) {
          gravityX = GRAVITY_LEFT;
          change = true;
        } else if (gravityX == GRAVITY_LEFT && lv != null && (lv.getRight() == svr || lv.getLeft() == svr)) {
          gravityX = GRAVITY_RIGHT;
          change = true;
        }
      }

      if (canScrollVertically) {
        if (isMinTop || (gravityY == GRAVITY_BOTTOM && fv != null && (fv.getTop() == svt || fv.getBottom() == svt))) {
          gravityY = GRAVITY_TOP;
          change = true;
        } else if (gravityY == GRAVITY_TOP && lv != null && (lv.getBottom() == svb || lv.getTop() == svb)) {
          gravityY = GRAVITY_BOTTOM;
          change = true;
        }
      }

      if (change) {
        setGravityText(tvControllerGravityX, false, gravityX);
        setGravityText(tvControllerGravityY, true, gravityY);
      }
    } else {
      if (canScrollHorizontally) {
        if (isMinLeft || (fv != null && (fv.getLeft() == svl || fv.getRight() == svl))) { // XListView 等 Header
          splitX = loc[0] + svr - windowWidth;
          change = true;
        } else if (splitX < 0 && lv != null && (lv.getRight() == svr || lv.getLeft() == svr)) { // XListView 等 Footer
          splitX = loc[0] + svl;
          change = true;
        }
      }

      if (canScrollVertically) {
        if (isMinTop || (fv != null && (fv.getTop() == svt || fv.getBottom() == svt))) { // XListView 等 Header
          splitY = loc[1] + svb - windowHeight - (isSeparatedStatus ? statusHeight : 0);
          change = true;
        } else if (lv != null && (lv.getBottom() == svb || lv.getTop() == svb)) { // XListView 等 Footer
          splitY = loc[1] + svt - (isSeparatedStatus ? statusHeight : 0);
          change = true;
        }
      }

      if (change) {
        floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
      }
    }
  }


  private void updateScreenWindowContentSize() {
    DisplayMetrics dm = getResources().getDisplayMetrics();
    DENSITY = dm.density;

    // WindowManager windowManager = window.getWindowManager();
    // Point point = new Point();
    // windowManager.getDefaultDisplay().getRealSize(point);

    activity = getCurrentActivity();
    if (window == null) { // 可能是弹窗的
      window = activity.getWindow();
    }

    DisplayMetrics metric = new DisplayMetrics();
    Display display = activity.getWindowManager().getDefaultDisplay();
    display.getRealMetrics(metric);

    //    boolean isLand = activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    // 居然是相对屏幕方向的
    screenWidth = metric.widthPixels; // isLand ? metric.widthPixels : metric.heightPixels; // 宽度（PX）
    screenHeight = metric.heightPixels; // isLand ? metric.heightPixels : metric.widthPixels; // 高度（PX）

    // 保持和 FloatWindow 内 Util.getScreenWidth, Util.getScreenHeight 一致
    WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
    Point point = new Point();
    wm.getDefaultDisplay().getSize(point);

    // 居然是绝对的，不是相对屏幕方向的
    windowWidth = point.x;
    windowHeight = point.y;
    if (windowWidth <= 0) {
      windowWidth = metric.widthPixels;
    }
    if (windowHeight <= 0) {
      windowHeight = metric.heightPixels;
    }

    double sum = windowHeight + statusHeight + navigationHeight;
    if (sum > screenHeight) {
      windowHeight -= statusHeight;
    } else if (sum < screenHeight) {
      if (statusHeight <= 0) {
        statusHeight = screenHeight - sum;
      }
      else if (isNavigationShow == false) {
        windowHeight = screenHeight - statusHeight;
      }
      else if (navigationHeight <= 0) {
        navigationHeight = screenHeight - sum;
        isNavigationShow = true;
      }
    }

    windowX = getWindowX(activity);
    windowY = getWindowY(activity);

    if (decorView == null) {
      decorView = window.getDecorView(); // activity.findViewById(android.R.id.content);
    }

    decorX = decorView == null ? 0 : decorView.getX();
    decorY = decorView == null ? 0 : decorView.getY();
    decorWidth = decorView == null ? screenWidth : decorView.getWidth();
    decorHeight = decorView == null ? screenHeight : decorView.getHeight();
  }

  private void updateDialogView(View dialogView) {
    //  目前 view 仅用于 PopupWindow  setCurrentView(dialogView, callback, activity, fragment, dialog);
    if (dialogView == null) {
      dialogX = dialogY = dialogWidth = dialogHeight = 0;
      return;
    }

    int[] loc = new int[2];
    dialogView.getLocationOnScreen(loc);

    dialogX = loc[0];
    dialogY = loc[1];
    dialogWidth = dialogView.getWidth();
    dialogHeight = dialogView.getHeight();
  }

  public static final String KEY_ENABLE_PROXY = "KEY_ENABLE_PROXY";
  public static final String KEY_PROXY_SERVER = "KEY_PROXY_SERVER";

  public void initUIAuto(Application app) {
    APP = app;

    final MethodUtil.InstanceGetter ig = MethodUtil.INSTANCE_GETTER;
    MethodUtil.INSTANCE_GETTER = new MethodUtil.InstanceGetter() {

      @Override
      public Object getInstance(@NotNull Class<?> clazz, List<MethodUtil.Argument> classArgs, Boolean reuse) throws Exception {
        if (reuse == null || reuse) {  // 环境相关类都默认取现有的值
          try {
          //环境与上下文相关的类 <<<<<<<<<<<<<<<<<<<<<<<<
          Activity activity = getCurrentActivity();

          if (Activity.class.isAssignableFrom(clazz)) {
            if (activity != null && clazz.isAssignableFrom(activity.getClass())) {
              return activity;
            }

            Activity a = findActivity(clazz);
            if (a != null) {
              return a;
            }

            // JSONObject obj = new JSONObject();
            Class[] types = new Class[classArgs == null || classArgs.isEmpty() ? 1 : 1 + classArgs.size()];
            types[0] = Context.class;
            Object[] args = new Object[types.length];
            args[0] = activity == null ? getApp() : activity;
            if (classArgs != null) {
              for (int i = 0; i < classArgs.size(); i++) {
                MethodUtil.Argument arg = classArgs.get(i);
                // obj.put(arg.getName(), ) // FIXME Argument 支持 Type:name=value
                args[i + i] = arg.getValue();
              }
            }
            // Intent intent = parseIntent(, clazz.getName());
            Intent intent;
            try {
              Method createIntent = clazz.getDeclaredMethod("createIntent", types);
              intent = (Intent) createIntent.invoke(null, args);
            } catch (Exception e) {
              try {
                types[0] = Activity.class;
                Method createIntent = clazz.getDeclaredMethod("createIntent", types);
                intent = (Intent) createIntent.invoke(null, args);
              } catch (Exception e2) {
                e2.printStackTrace();
                intent = new Intent(activity == null ? getApp() : activity, clazz);
              }
            }
            startActivity(intent);

            a = findActivity(clazz);
            if (a != null) {
              return a;
            }

            throw new ClassNotFoundException("Did not find alive " + clazz.getName() + "!");
          }
          } catch (Throwable e) {
            e.printStackTrace();
          }
        }

        return ig.getInstance(clazz, classArgs, reuse);
      }
    };

    UnitAutoApp.init(app);

    UnitAutoApp unitIns = UnitAutoApp.getInstance();
    unitIns.setInterfaceClass(HttpManager.OnHttpResponseListener.class);
    unitIns.setCallbackSign("onHttpResponse(int,String,Throwable)");

    Log.d(TAG, "项目启动 >>>>>>>>>>>>>>>>>>>> \n\n");

    parentDirectory = app.getExternalFilesDir(Environment.DIRECTORY_PICTURES); // new File(screenshotDirPath);
    if (parentDirectory.exists() == false) {
      try {
        parentDirectory.mkdir();
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }

    cache = getSharedPreferences();
    isProxy = cache.getBoolean(KEY_ENABLE_PROXY, false);
    proxyServer = cache.getString(KEY_PROXY_SERVER, null);

    try {
      Map<String, BallPoint[]> map = com.alibaba.fastjson.JSON.parseObject(
              cache.getString(CLASS_BALL_CACHE_MAP, "{}")
              , new TypeReference<Map<String, BallPoint[]>>() {}.getType()
      );
      if (map != null && map.isEmpty() == false) {
        classBallPositionMap.putAll(map);
      }
    } catch (Throwable e) {
      e.printStackTrace();
    }

    app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {

      @Override
      public void onActivityStarted(Activity activity) {
        Log.v(TAG, "onActivityStarted  activity = " + activity.getClass().getName());
        onUIEvent(InputUtil.UI_ACTION_START, activity, activity);
      }

      @Override
      public void onActivityStopped(Activity activity) {
        Log.v(TAG, "onActivityStopped  activity = " + activity.getClass().getName());
        onUIEvent(InputUtil.UI_ACTION_STOP, activity, activity);
      }

      @Override
      public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.v(TAG, "onActivitySaveInstanceState  activity = " + activity.getClass().getName());
      }

      @Override
      public void onActivityResumed(Activity activity) {
        Log.v(TAG, "onActivityResumed  activity = " + activity.getClass().getName());
        setCurrentActivity(activity);
        curFocusView = null;
        tvControllerGravityContainer.setText("");

        if (isShowing) {
          onUIAutoActivityCreate(activity);
        }
//        setCurrentPopupWindow(popupWindowMap.get(activity), viewMap.get(activity), null, activity, null);
        onUIEvent(InputUtil.UI_ACTION_RESUME, activity, activity);

        viewPropertyListMap = pageViewListMap.get(activity);
        if (viewPropertyListMap == null) {
          viewPropertyListMap = new LinkedHashMap<>();
        }
        if (viewPropertyListMap.isEmpty()) {
          allView2Properties(getCurrentContentView(), viewPropertyListMap);
        }

        setIgnoreViewList(activity.getClass().getName());
      }

      @Override
      public void onActivityPaused(Activity activity) {
        Log.v(TAG, "onActivityPaused  activity = " + activity.getClass().getName());
        // setCurrentActivity(activityList.isEmpty() ? null : activityList.get(activityList.size() - 1));
        onUIEvent(InputUtil.UI_ACTION_PAUSE, activity, activity);
        isSplit2Showing = floatBall2 != null && floatBall2.isShowing();
        curFocusView = null;

        setGravityText(tvControllerGravityX, false, gravityX);
        setGravityText(tvControllerGravityY, true, gravityY);
        saveBallPosition(activity);
      }

      @Override
      public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.v(TAG, "onActivityCreated  activity = " + activity.getClass().getName());
        activityList.add(activity);
        //TODO 按键、键盘监听拦截和转发
        onUIEvent(InputUtil.UI_ACTION_CREATE, activity, activity);

        onUIAutoFragmentCreate(activity);
      }

      @Override
      public void onActivityDestroyed(Activity activity) {
        Log.v(TAG, "onActivityDestroyed  activity = " + activity.getClass().getName());
        activityList.remove(activity);
        onUIEvent(InputUtil.UI_ACTION_DESTROY, activity, activity);
        dialogMap.remove(activity);
        popupWindowMap.remove(activity);
        ballPositionMap.remove(activity);

//        setCurrentPopupWindow(null, null, null, activity, null);

        if (activityList == null || activityList.isEmpty()) { // Application.onTerminate 只在模拟器调用，真机不调用
          saveAllBallPositions();
        }

        pageViewListMap.remove(activity);
      }

    });


    isShowing = false;

    statusHeight = DisplayUtil.getStatusBarHeight(getApp());
    isSeparatedStatus = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P; // RomUtil.checkIsMiuiRom() || RomUtil.checkIsVivoRom() || RomUtil.checkIsOppoRom();
    if (statusHeight <= 0) {
      int statusResourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
      if (statusResourceId > 0) {
        statusHeight = getResources().getDimensionPixelSize(statusResourceId);
      }
    }

    isNavigationShow = DisplayUtil.hasNavigationBar(getApp());
    if (isNavigationShow) {
      navigationHeight = DisplayUtil.getNavigationBarHeight(getApp());
      if (navigationHeight <= 0) {
        int navigationResourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (navigationResourceId > 0) {
          navigationHeight = getResources().getDimensionPixelSize(navigationResourceId);
        }
      }
    } else {
      navigationHeight = 0;
    }

    statusHeight = cache.getInt(KEY_STATUS_HEIGHT, (int) Math.round(statusHeight));
    isSeparatedStatus = cache.getBoolean(KEY_STATUS_SHOW, isSeparatedStatus);

    navigationHeight = cache.getInt(KEY_NAV_HEIGHT, (int) Math.round(navigationHeight));
    isNavigationShow = cache.getBoolean(KEY_NAV_SHOW, isNavigationShow);

    ignoreActivityViewListMap = JSON.parseObject(cache.getString(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP, null));

    // vFloatCover = new FrameLayout(getInstance());
    // ViewGroup.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
    // vFloatCover.setLayoutParams(lp);

    vFloatCover = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_auto_cover_layout, null);
    vFloatController = getLayoutInflater().inflate(R.layout.ui_auto_controller_layout, null);
    vFloatBall = (FloatBallView) getLayoutInflater().inflate(R.layout.ui_auto_split_ball_layout, null);
    vFloatBall2 = (FloatBallView) getLayoutInflater().inflate(R.layout.ui_auto_split_ball_layout, null);
    vSplitX = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_auto_split_x_layout, null);
    vSplitX2 = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_auto_split_x_layout, null);
    vSplitY = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_auto_split_y_layout, null);
    vSplitY2 = (ViewGroup) getLayoutInflater().inflate(R.layout.ui_auto_split_y_layout, null);

    tvControllerX = vFloatController.findViewById(R.id.tvControllerX);
    tvControllerDouble = vFloatController.findViewById(R.id.tvControllerDouble);
    tvControllerReturn = vFloatController.findViewById(R.id.tvControllerReturn);
    tvControllerCount = vFloatController.findViewById(R.id.tvControllerCount);
    tvControllerPlay = vFloatController.findViewById(R.id.tvControllerPlay);
    tvControllerTime = vFloatController.findViewById(R.id.tvControllerTime);
    tvControllerForward = vFloatController.findViewById(R.id.tvControllerForward);
    tvControllerSetting = vFloatController.findViewById(R.id.tvControllerSetting);
    tvControllerY = vFloatController.findViewById(R.id.tvControllerY);

    rlControllerGravity = vFloatController.findViewById(R.id.rlControllerGravity);
    rlControllerGravity.setVisibility(View.GONE);
    tvControllerGravityX = vFloatController.findViewById(R.id.tvControllerGravityX);
    tvControllerGravityY = vFloatController.findViewById(R.id.tvControllerGravityY);
    tvControllerGravityContainer = vFloatController.findViewById(R.id.tvControllerGravityContainer);

    rvControllerTag = vFloatController.findViewById(R.id.rvControllerTag);
    tagAdapter = new RecyclerView.Adapter() {
      @Override
      public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(getLayoutInflater().inflate(R.layout.ui_auto_tag_layout, null, false)) {};
      }

      @Override
      public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // final 问题 position = holder.getAdapterPosition();  // IDE 警告用这个方法替代参数

        JSONObject item = getItem(position);
        int type = item.getIntValue("type");
        boolean disable = item.getBooleanValue("disable");
        int index = position + 1; // eventList == null ? -1 : eventList.indexOf(item);
        // boolean isAdded = index >= 0;

        String action = InputUtil.getActionName(type, item.getIntValue("action"));
        String name = InputUtil.getShowName(item, httpHostList, webHostList);

        ((TextView) holder.itemView).setText((disable ? "-" : "") + index + ". " + action + name);
        //位置数字区分，避免暗色背景显示不明显
        ((TextView) holder.itemView).setTextColor(getResources().getColor(index == step ? android.R.color.holo_red_dark : android.R.color.white));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            // if (isAdded) {
            //     // removeEvent(item);
            // }
            // else {
            //     // addEvent(item, getCurrentActivity());
            // }

            item.put("disable", ! disable);
            onBindViewHolder(holder, position);
          }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
          @Override
          public boolean onLongClick(View v) {
            int index = position + 1;
            Node<InputEvent> curNode = firstEventNode;
            for (int i = 0; i < index; i++) {
              if (curNode == null) {
                break;
              }

              curNode = curNode.next;
            }

            currentEventNode = curNode;
            step = index;

            tvControllerCount.setText(step + "/" + allStep);
            onEventChange(step - 1, curNode == null ? 0 : curNode.type);  // move 时刷新容易卡顿
            return true;
          }
        });
      }

      @Override
      public int getItemCount() {
        // return tagList == null ? 0 : tagList.size();
        return eventList == null ? 0 : eventList.size();
      }
      @NotNull
      JSONObject getItem(int position) {
        // return tagList == null || tagList.isEmpty() ? new JSONObject() : tagList.getJSONObject(position);
        return eventList == null || eventList.isEmpty() ? new JSONObject() : eventList.getJSONObject(position);
      }
    };
    rvControllerTag.setAdapter(tagAdapter);

    // vFloatCover.addView(vSplitX);
    // vFloatCover.addView(vSplitY);
    // vFloatCover.addView(vSplitX2);
    // vFloatCover.addView(vSplitY2);

    // vSplitY.post(new Runnable() {
    //     @Override
    //     public void run() {
    //         vSplitY.setY(splitY - vSplitY.getHeight()/2);
    //         vFloatCover.setVisibility(View.GONE);
    //     }
    // });
    //
    // vSplitY.setBackgroundColor(Color.parseColor(cache.getString(SPLIT_COLOR, "#10000000")));

//         vFloatCover.setOnTouchListener(new View.OnTouchListener() {
//             @Override
//             public boolean onTouch(View v, MotionEvent event) {
//                 Log.d(TAG, "onTouchEvent  " + Calendar.getInstance().getTime().toLocaleString() +  " action:" + (event.getAction()) + "; x:" + event.getX() + "; y:" + event.getY());
//                 dispatchEventToCurrentActivity(event, true);
// //死循环                llTouch.dispatchTouchEvent(event);
// //                vDispatchTouch.dispatchTouchEvent(event);
// //                vDispatchTouch.dispatchTouchEvent(event);
//                 //onTouchEvent 不能处理事件 vDispatchTouch.onTouchEvent(event);
// //                vTouch.setOnTouchListener(this);
//                 return true;  //连续记录只能 return true
//             }
//         });

    // vFloatBall.setOnLongClickListener(new View.OnLongClickListener() {
    // 	@Override
    // 	public boolean onLongClick(View v) {
    //
    // 		return true;
    // 	}
    // });

    // vFloatBall2.setOnLongClickListener(new View.OnLongClickListener() {
    // 	@Override
    // 	public boolean onLongClick(View v) {
    // 		return vFloatBall.performLongClick();
    // 	}
    // });
    //
    // vFloatBall.setOnTouchListener(new View.OnTouchListener() {
    //   @Override
    //   public boolean onTouch(View v, MotionEvent event) {
    //     // 都不动了 if (event.getY() - event.getRawY() >= 10) {
    //     // if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_HOVER_MOVE) {
    //     // 	moved = true;
    //     // 	vSplitY.setY(event.getY());
    //     //       // vSplitY.invalidate();
    //     // } else {
    //     // 	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    //     // 		moved = false;
    //     // 	}
    //     // 	else if (event.getAction() == MotionEvent.ACTION_UP) {
    //     // 		if (! moved) {
    //     // 			ivUIAutoSplitY.performClick();
    //     // 		}
    //     // 	}
    //     // }
    //     //   // }
    //     // return true;
    //
    //
    //     if (event.getAction() == MotionEvent.ACTION_DOWN) {
    //       vSplitX.setVisibility(View.VISIBLE);
    //       vSplitY.setVisibility(View.VISIBLE);
    //       vSplitX2.setVisibility(vFloatBall2.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
    //       vSplitY2.setVisibility(vFloatBall2.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
    //     }
    //     else if (event.getAction() == MotionEvent.ACTION_UP) {
    //       vSplitX.setVisibility(View.GONE);
    //       vSplitY.setVisibility(View.GONE);
    //       vSplitX2.setVisibility(View.GONE);
    //       vSplitY2.setVisibility(View.GONE);
    //     }
    //     return false;
    //   }
    // });

    // vFloatBall2.setOnTouchListener(new View.OnTouchListener() {
    //   @Override
    //   public boolean onTouch(View v, MotionEvent event) {
    //     if (event.getAction() == MotionEvent.ACTION_DOWN) {
    //       vSplitX.setVisibility(vFloatBall.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
    //       vSplitY.setVisibility(vFloatBall.getVisibility() == View.VISIBLE ? View.VISIBLE : View.GONE);
    //       vSplitX2.setVisibility(View.VISIBLE);
    //       vSplitY2.setVisibility(View.VISIBLE);
    //     }
    //     else if (event.getAction() == MotionEvent.ACTION_UP) {
    //       vSplitX2.setVisibility(View.GONE);
    //       vSplitY2.setVisibility(View.GONE);
    //       vSplitX2.setVisibility(View.GONE);
    //       vSplitY2.setVisibility(View.GONE);
    //     }
    //     return false;
    //   }
    // });

    // ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    // root.addView(vFloatCover, lp);


    tvControllerDouble.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isSplitShowing == false || floatBall == null) {
          Toast.makeText(getCurrentActivity(), R.string.please_firstly_record_or_replay, Toast.LENGTH_SHORT).show();
          return;
        }

        isSplit2Showing = floatBall2 != null && floatBall2.isShowing();
        isSplit2Showing = ! isSplit2Showing;
        rlControllerGravity.setVisibility(isSplit2Showing ? View.VISIBLE : View.GONE);
        gravityX = GRAVITY_CENTER;
        gravityY = GRAVITY_CENTER;

        // FloatWindow.destroy("floatBall2");
        // floatBall2 = null;
        // if (isSplit2Showing) {
        floatBall2 = showSplit(floatBall2, isSplit2Showing,
                floatBall.getX() + splitRadius - dp2px(0.5)
                , floatBall.getY() + splitRadius - dp2px(0.5) // - (isSeparatedStatus ? statusHeight : 0)
                , "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2
        );
        // }

        setSplit();

        if (isSplit2Showing) {
          tvControllerGravityContainer.performClick();
        }
        else if (floatBall2 != null) {
          floatBall = showSplit(floatBall, isSplitShowing,
                  (floatBall.getX() + floatBall2.getX())/2 + splitRadius
                  , (floatBall.getY() + floatBall2.getY())/2 + splitRadius
                  , "floatBall", vFloatBall, floatSplitX, floatSplitY
          );
        }

        toast(isSplit2Showing ? R.string.long_press_ball_to_finish : R.string.click_ball_to_finish);
      }
    });

    tvControllerReturn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        handler.removeMessages(0);
        if (step > 1) {
          step --;
          tvControllerCount.setText(step + "/" + allStep);
          onEventChange(step - 1, 0L);
        }

        Message msg = handler.obtainMessage();
        msg.obj = currentEventNode == null ? null : currentEventNode.prev;
        handler.sendMessage(msg);
      }
    });
    tvControllerReturn.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        handler.removeMessages(0);
        isTimeout = false;
        mainHandler.removeCallbacks(timeoutRunnable);

        if (step != 0) {
          step = 0;
          tvControllerCount.setText(step + "/" + allStep);
          onEventChange(0, 0L);
        }
        return true;
      }
    });

    // tvControllerCount.setOnClickListener(new View.OnClickListener() {
    //     @Override
    //     public void onClick(View v) {
    //         startActivity(UIAutoListActivity.createIntent(getApp(), flowId));
    //     }
    // });

    tvControllerPlay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        onClickPlay();
      }
    });

    // tvControllerTime.setOnClickListener(new View.OnClickListener() {
    // @Override
    //     public void onClick(View v) {
    //         startActivity(UIAutoListActivity.createIntent(getApp(), true));
    //     }
    // });

    tvControllerForward.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        forward(true);
      }
    });
    tvControllerForward.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        handler.removeMessages(0);
        if (step != allStep + 1) {
          step = allStep + 1;
          tvControllerCount.setText(step + "/" + allStep);
          onEventChange(allStep - 1, 0L);
        }
        return true;
      }
    });

    tvControllerSetting.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
        startActivity(UIAutoActivity.createIntent(getApp()));
      }
    });

    setGravity(tvControllerGravityX, false);
    setGravity(tvControllerGravityY, true);

    tvControllerGravityContainer.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        tvControllerGravityContainer.setText("");
        toast(R.string.unselected_touch_scope);

        DialogInterface dialog = getCurrentDialog();
        String pageName = (dialog != null ? dialog : getCurrentActivity()).getClass().getName();

        if (curFocusView != null) {
          String id = getResIdName(curFocusView.getId());
          String type = curFocusView.getClass().getName();

          if (ignoreActivityViewListMap == null) {
            ignoreActivityViewListMap = new JSONObject();
          }
          JSONArray list = ignoreActivityViewListMap.getJSONArray(pageName);
          if (list == null) {
            list = new JSONArray(1);
            ignoreActivityViewListMap.put(pageName, list);
          }

          String key = id + "@" + type + (curFocusView instanceof ViewGroup ? "[]" : "");
          if (! list.contains(key)) {
            list.add(key);
          }

          cache.edit().remove(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP)
                  .putString(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP, JSON.toJSONString(ignoreActivityViewListMap))
                  // .putString(KEY_VIEW_LIST, JSON.toJSONString(viewPropertyListMap.values()))
                  .commit();
          curFocusView = null;
        }

        onClickPlay();
        startActivity(UIAutoListActivity.createIntent(getApp(), true
                , android.R.id.content, getResIdName(android.R.id.content)
                , contentView == null ? FrameLayout.class.getName() : contentView.getClass().getName()
                , pageName
        ));
        return true;
      }
    });

    tvControllerGravityContainer.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ViewParent vp = curFocusView == null ? null : curFocusView.getParent();
        curFocusView = vp instanceof View ? (View) vp : null;
        if (curFocusView == null) {
          double focusX = (isSplit2Showing ? (floatBall.getX() + floatBall2.getX())/2 : floatBall.getX()) + splitRadius;
          double focusY = (isSplit2Showing ? (floatBall.getY() + floatBall2.getY())/2 : floatBall.getY()) + splitRadius + (isSeparatedStatus ? statusHeight : 0);
          curFocusView = findViewByPoint(callback instanceof DialogInterface ? getCurrentContentView() : getCurrentDecorView(), null, focusX, focusY);
        }
        if (curFocusView == null) {
          tvControllerGravityContainer.setText("");
          return;
        }

        String in = getResIdName(curFocusView);
        tvControllerGravityContainer.setText(
                "[]: " + (StringUtil.isEmpty(in, true) ? curFocusView.getId() : in)
                + "@" + curFocusView.getClass().getSimpleName()
                + (curFocusView instanceof ViewGroup ? "[" + ((ViewGroup) curFocusView).getChildCount() + "]" : "")
        );

        int[] loc = new int[2];
        curFocusView.getLocationOnScreen(loc);

        splitX2 = loc[0] + curFocusView.getPaddingLeft();
        splitY2 = loc[1] + curFocusView.getPaddingTop() - statusHeight; //(isSeparatedStatus ? statusHeight : 0);

        splitX = loc[0] + curFocusView.getWidth() - curFocusView.getPaddingRight(); // - windowWidth;
        splitY = loc[1] + curFocusView.getHeight() - curFocusView.getPaddingBottom() - statusHeight; // (isSeparatedStatus ? statusHeight : 0); // - windowHeight;

        floatBall.updateX((int) Math.round(splitX - splitRadius));
        floatBall.updateY((int) Math.round(splitY - splitRadius));

        floatBall2.updateX((int) Math.round(splitX2 - splitRadius));
        floatBall2.updateY((int) Math.round(splitY2 - splitRadius));

//        floatBall = showSplit(true, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
//        floatBall2 = showSplit(true, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);

        onUpdateBallPosition(floatBall, vFloatBall, floatSplitX, floatSplitY, false, floatBall.getX(), floatBall.getY());
        onUpdateBallPosition(floatBall2, vFloatBall2, floatSplitX2, floatSplitY2, true, floatBall2.getX(), floatBall2.getY());

        toast(R.string.selected_touch_scope_long_press_to_unselect);
      }
    });
  }


  public void saveAllBallPositions() {
    setGravityText(tvControllerGravityX, false, gravityX);
    setGravityText(tvControllerGravityY, true, gravityY);

    String s = JSON.toJSONString(classBallPositionMap);
    cache.edit()
            .remove(SPLIT_SIZE).putFloat(SPLIT_SIZE, (float) splitSize)
            .remove(SPLIT_COLOR).putInt(SPLIT_COLOR, splitColor)
            .remove(BALL_GRAVITY).putInt(BALL_GRAVITY, ballGravity)
            .remove(BALL_GRAVITY2).putInt(BALL_GRAVITY2, ballGravity2)
            .remove(SPLIT_X).putFloat(SPLIT_X, (float) splitX)
            .remove(SPLIT_Y).putFloat(SPLIT_Y, (float) splitY)
            .remove(SPLIT_X2).putFloat(SPLIT_X2, (float) splitX2)
            .remove(SPLIT_Y2).putFloat(SPLIT_Y2, (float) splitY2)
            .remove(CLASS_BALL_CACHE_MAP).putString(CLASS_BALL_CACHE_MAP, s)
            .commit();
//                  .apply();
  }

  private void setSplit() {
    setGravityImageAndText(vFloatBall, ballGravity2, tvControllerGravityX, false, gravityX);
    setGravityImageAndText(vFloatBall, ballGravity2, tvControllerGravityY, true, gravityY);
    setGravityImageAndText(vFloatBall, ballGravity, tvControllerGravityX, false, gravityX);
    setGravityImageAndText(vFloatBall, ballGravity, tvControllerGravityY, true, gravityY);
  }

  private void updateTime() {
    currentTime = System.currentTimeMillis();
    duration = currentTime - startTime;
    tvControllerTime.setText(TIME_FORMAT.format(new Date(duration)));
  }

  private View curFocusView;

  private void setGravity(TextView tv, boolean isY) {
    setGravityText(tv, isY, isY ? gravityY : gravityX);
    tv.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (isY) {
          gravityY = (gravityY + 1)%Y_GRAVITIES.length;
          setGravityText(tv, isY, gravityY);
        } else {
          gravityX = (gravityX + 1)%X_GRAVITIES.length;
          setGravityText(tv, isY, gravityX);
        }
      }
    });
  }

  private void setGravityText(TextView tv, boolean isY, int gravity) {
    if (isY) {
      double sy = floatBall == null ? splitY : floatBall.getY() + splitRadius - windowHeight;
      boolean isBottom = InputUtil.isBottom(ballGravity);
      Double ratioY = null;
      if (InputUtil.isRatio(ballGravity)) {
        ratioY = sy/windowHeight;
      }
      else if (sy >= 0 && isBottom) {
        sy -= windowHeight;
      }
      else if (sy < 0 && InputUtil.isTop(ballGravity)) {
        sy += windowHeight;
      }

      double sy2 = floatBall2 == null ? splitY2 : floatBall2.getY() + splitRadius - windowHeight;
      boolean isBottom2 = InputUtil.isBottom(ballGravity2);
      Double ratioY2 = null;
      if (InputUtil.isRatio(ballGravity2)) {
        ratioY2 = sy2/windowHeight;
      }
      else if (sy2 >= 0 && isBottom2) {
        sy2 -= windowHeight;
      }
      else if (sy2 < 0 && InputUtil.isTop(ballGravity2)) {
        sy2 += windowHeight;
      }

      splitY = sy;
      splitY2 = sy2;

      long rsy = Math.round(sy);
      long rsy2 = Math.round(sy2);

      tv.setText("Y: " + (ratioY2 == null ? (isBottom2 && rsy2 == 0 ? "-" : "") + rsy2 : DECIMAL_FORMAT.format(ratioY2*100) + "%")
              + "/" + (ratioY == null ? (isBottom && rsy == 0 ? "-" : "") + rsy : DECIMAL_FORMAT.format(ratioY*100) + "%")
              + ", " + getResources().getString(gravity == GRAVITY_RATIO ? R.string.ratio : (gravity == GRAVITY_TOP ? R.string.top : (gravity == GRAVITY_BOTTOM ? R.string.bottom : R.string.center))));
    } else {
      double sx = floatBall == null ? splitX : floatBall.getX() + splitRadius;
      boolean isRight = InputUtil.isRight(ballGravity);
      Double ratioX = null;
      if (InputUtil.isRatio(ballGravity)) {
        ratioX = sx/windowWidth;
      }
      else if (sx >= 0 && isRight) {
        sx -= windowWidth;
      }
      else if (sx < 0 && InputUtil.isLeft(ballGravity)) {
        sx += windowWidth;
      }

      double sx2 = floatBall2 == null ? splitX2 : floatBall2.getX() + splitRadius;
      boolean isRight2 = InputUtil.isRight(ballGravity2);
      Double ratioX2 = null;
      if (InputUtil.isRatio(ballGravity2)) {
        ratioX2 = sx2/windowWidth;
      }
      else if (sx2 >= 0 && isRight2) {
        sx2 -= windowWidth;
      }
      else if (sx2 < 0 && InputUtil.isLeft(ballGravity2)) {
        sx2 += windowWidth;
      }

      splitX = sx;
      splitX2 = sx2;

      long rsx = Math.round(sx);
      long rsx2 = Math.round(sx2);

      tv.setText("X: " + (ratioX2 == null ? (isRight2 && rsx2 == 0 ? "-" : "") + rsx2 : DECIMAL_FORMAT.format(ratioX2*100) + "%")
              + "/" +  (ratioX == null ? (isRight && rsx == 0 ? "-" : "") + rsx : DECIMAL_FORMAT.format(ratioX*100) + "%")
              + ", " + getResources().getString(gravity == GRAVITY_RATIO ? R.string.ratio : (gravity == GRAVITY_LEFT ? R.string.left : (gravity == GRAVITY_RIGHT ? R.string.right : R.string.center))));
    }
  }


    private void onUIAutoFragmentCreate(Activity activity) {
    if (activity instanceof FragmentActivity) {
      FragmentActivity fa = (FragmentActivity) activity;
      FragmentManager sfm = fa.getSupportFragmentManager();

      Boolean watched = fragmentWatchedMap.get(sfm);
      if (watched == null || watched == false) {
        fragmentWatchedMap.put(sfm, true);

        sfm.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
          @Override
          public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentPreAttached(fm, f, context);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentPreAttached  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_PREATTACH, f.getActivity(), f);
          }

          @Override
          public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
            super.onFragmentAttached(fm, f, context);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentAttached  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_ATTACH, f.getActivity(), f);
          }

          @Override
          public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentPreCreated(fm, f, savedInstanceState);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentPreCreated  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_PRECREATE, f.getActivity(), f);
          }

          @Override
          public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentCreated(fm, f, savedInstanceState);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentCreated  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_CREATE, f.getActivity(), f);
          }

          @Override
          public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
            super.onFragmentActivityCreated(fm, f, savedInstanceState);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentActivityCreated  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_ACTIVITY_CREATED, f.getActivity(), f);
          }

          @Override
          public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentViewCreated  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_CREATE_VIEW, f.getActivity(), f);
          }

          @Override
          public void onFragmentStarted(FragmentManager fm, Fragment f) {
            super.onFragmentStarted(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentStarted  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_START, f.getActivity(), f);
          }

          @Override
          public void onFragmentResumed(FragmentManager fm, Fragment f) {
            super.onFragmentResumed(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentResumed  fragment = " + f.getClass().getName());

            if (f != null && f.getParentFragment() == null) {
              setCurrentFragment(f);

              FragmentActivity act = f.getActivity();
              FragmentManager xafm = act == null ? null : act.getSupportFragmentManager();
              List<Fragment> xafs = xafm == null ? null : xafm.getFragments();
              if (xafs != null) {
                for (Fragment xf : xafs) {
                  View v = xf != null && xf.isVisible() ? xf.getView() : null;
                  if (v == null || v.getWidth() < windowWidth/2 || v.getHeight() < windowHeight/2) {
                    continue;
                  }

                  setCurrentFragment(xf);
                  break;
                }
              }
            }

            onUIEvent(InputUtil.UI_ACTION_RESUME, f == null ? null : f.getActivity(), f);
            addTextChangedListener(decorView);
          }

          @Override
          public void onFragmentPaused(FragmentManager fm, Fragment f) {
            super.onFragmentPaused(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentPaused  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_PAUSE, f.getActivity(), f);
            // 没必要，Fragment 肯定都在 Activity 内    saveBallPosition(f);
            if (f == null || f.getParentFragment() == null) {
               setCurrentFragment(null);
            }
          }

          @Override
          public void onFragmentStopped(FragmentManager fm, Fragment f) {
            super.onFragmentStopped(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentStopped  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_STOP, f.getActivity(), f);
          }

          @Override
          public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
            super.onFragmentSaveInstanceState(fm, f, outState);
          }

          @Override
          public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentViewDestroyed(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentViewDestroyed  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_DESTROY_VIEW, f.getActivity(), f);
          }

          @Override
          public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
            super.onFragmentDestroyed(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentDestroyed  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_DESTROY, f.getActivity(), f);
          }

          @Override
          public void onFragmentDetached(FragmentManager fm, Fragment f) {
            super.onFragmentDetached(fm, f);
            if (isIgnoreFragment(f)) {
              return;
            }
            Log.v(TAG, "onFragmentDetached  fragment = " + f.getClass().getName());
            onUIEvent(InputUtil.UI_ACTION_DETACH, f.getActivity(), f);
          }
        }, true);
      }

      // TODO deprecated     android.app.FragmentManager fm = fa.getFragmentManager();
    }

  }

  public boolean isIgnoreFragment(Fragment f) {
    return f == null || f instanceof DialogFragment || f.getClass().getName().contains("SupportRequestManagerFragment");
  }

  public void onClickPlay() {
    isSplitShowing = ! isSplitShowing;
    tvControllerPlay.setText(isReplay ? (isSplitShowing ? R.string.replaying : R.string.replay) : (isSplitShowing ? R.string.recording : R.string.record));
    floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
    floatBall2 = showSplit(floatBall2, isSplitShowing && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);

    // FloatWindow.destroy("floatBall2");
    // floatBall2 = null;

    currentTime = System.currentTimeMillis();

    if (isSplitShowing) {
      if (isReplay) {
//        rvControllerTag.scrollToPosition(step - 1);
        replay(step);
      }
      else {
        record();
        toast(isSplit2Showing ? R.string.long_press_ball_to_finish : R.string.click_ball_to_finish);
      }
    }
  }

  public void forward(boolean skip) {
    //        handler.removeMessages(0);
    if (step <= allStep) {
      step ++;
      tvControllerCount.setText(step + "/" + allStep);
      onEventChange(step - 1, 0L);
    }

    if (skip) {
      Collection<List<Node<InputEvent>>> values = waitMap.values();
      for (List<Node<InputEvent>> list : values) {
        if (list == null || list.isEmpty()) {
          continue;
        }

        for (Node<InputEvent> node : list) {
          if (node == null) {
            continue;
          }

          node.disable = true;
        }
      }
    }

    waitMap = new LinkedHashMap<>();
    firstUIWaitNode = null;
    lastHTTPWaitNode = null;

    Node<InputEvent> node = currentEventNode == null ? null : currentEventNode.next;
    if (skip && node != null) {
      node.disable = true;
    }

    Message msg = handler.obtainMessage();
    msg.obj = node;
    handler.sendMessage(msg);
  }

  public void toast(int id) {
    toast(getResources().getString(id));
  }
  private void toast(String s) {
    toast(s, false);
  }
  private void toast(String s, boolean isLong) {
    if (StringUtil.isEmpty(s, true)) {
      return;
    }
    mainHandler.post(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(getApp(), s, Toast.LENGTH_SHORT).show();
      }
    });
  }

  private void dismiss() {
    count = 0;

    isShowing = false;
    isSplitShowing = false;
    // ((ViewGroup) v.getParent()).removeView(v);
    tvControllerPlay.setText(isReplay ? (isSplitShowing ? R.string.replaying : R.string.replay) : (isSplitShowing ? R.string.recording : R.string.record));

    floatCover = null;
    floatController = null;
    floatBall = null;
    floatBall2 = null;
    FloatWindow.destroy("floatCover");
    FloatWindow.destroy("floatController");
    FloatWindow.destroy("floatBall");
    FloatWindow.destroy("floatBall2");

    try {
      FloatWindow.destroy("floatSplitX");
      FloatWindow.destroy("floatSplitY");
      FloatWindow.destroy("floatSplitX2");
      FloatWindow.destroy("floatSplitY2");
    }
    catch (Throwable e) {
      e.printStackTrace();
    }
  }


  public void onUIAutoDialogDismiss(Dialog dialog) {
    onUIAutoWindowDestroy(dialog, dialog == null ? null : dialog.getWindow(), dialog, null);
  }
//  public void onUIAutoDialogDismiss(DialogInterface dialog) {
//    onUIAutoWindowDestroy(
//      dialog instanceof Window.Callback ? (Window.Callback) dialog: null
//      , dialog instanceof Dialog ? ((Dialog) dialog).getWindow() : null
//    );
//  }

  public void onUIAutoPopupWindowDismiss(@NonNull PopupWindow pw, View view, Window window, Activity activity, Fragment fragment) {
      // if (activity == null) {
      //     activity = fragment == null ? null : fragment.getActivity();
      //     activity = activity != null ? activity : getCurrentActivity();
      // }
      // List<PopupWindow> list = popupWindowMap.get(activity);
      // if (list == null || list.isEmpty()) {
      //     return;
      // }
      //
      // int index = list.indexOf(pw);
      // if (index < 0) {
      //     return;
      // }
      //
      // window = window != null ? window : activity.getWindow();
      onUIAutoWindowDestroy(window == null ? null : window.getCallback(), window, null, pw);
      // try {
      //   list.remove(pw);
      // } catch (Throwable e) {
      //   e.printStackTrace();
      // }
  }

  public void onUIAutoWindowDestroy(Window.Callback callback, Window window, DialogInterface dialogInterface, PopupWindow popupWindow) {
    if (activity == null) {
      activity = getCurrentActivity();
    }

    if (dialogInterface == null && callback instanceof DialogInterface) {
      dialogInterface = (DialogInterface) callback;
    }

    if (popupWindow != null) {
      PopupWindow pw = getCurrentPopupWindow(null, null);
      saveBallPosition(popupWindow);

      if (pw != null && pw == callback) {
        pw.dismiss();
      }

      setCurrentView(null, callback, activity, fragment, dialogInterface, null);
      if (activity == null) {
        activity = dialogInterface instanceof Dialog ? ((Dialog) dialogInterface).getOwnerActivity() : null;
        setCurrentActivity(activity);
      }

      List<PopupWindow> list = popupWindowMap.get(activity);
      if (list != null) {
          list.remove(popupWindow);
      }

      // onUIAutoActivityCreate(activity);
    }
    else if (dialogInterface != null) {
      DialogInterface dialog = getCurrentDialog();
      saveBallPosition(callback);
      if (dialog != null && dialog == callback) {
        dialog.dismiss();
      }
      updateDialogView(null);

      if (activity == null) {
        activity = dialogInterface instanceof Dialog ? ((Dialog) dialogInterface).getOwnerActivity() : null;
        setCurrentActivity(activity);
      }

      List<DialogInterface> list = dialogMap.get(activity);
      if (list != null) {
        list.remove(dialogInterface);
      }
    }
    else {
      this.window = activity == null ? null : activity.getWindow();
    }

//    this.popupWindow = null;
    this.view = null;
    clearTextChangedListener();

    dialogInterface = getCurrentDialog();
    popupWindow = getCurrentPopupWindow(null, null);
    // window = popupWindow != null ? popupWindow.
    activity = getCurrentActivity();
    window = dialogInterface instanceof Dialog ? ((Dialog) dialogInterface).getWindow() : activity.getWindow();
    callback = window == null ? (dialogInterface instanceof Dialog ? ((Dialog) dialogInterface) : activity) : window.getCallback();
    onUIAutoWindowCreate(callback, window, dialogInterface, popupWindow);
  }

  private void saveBallPosition(Object key) {
    if (key == null) {
      key = getCurrentActivity();
    }

    BallPoint[] points = new BallPoint[] {
            new BallPoint(ballGravity, splitX, splitY)
            , isSplit2Showing == false ? null : new BallPoint(ballGravity2, splitX2, splitY2)
    };
    ballPositionMap.put(key, points);
    classBallPositionMap.put(key.getClass().getName(), points);
  }


  public void onUIAutoActivityDestroy(Window.Callback callback, Activity activity) {
    onUIAutoWindowDestroy(callback, activity == null ? null : activity.getWindow(), null, null);
  }

  private LayoutInflater inflater;
  public LayoutInflater getLayoutInflater() {
    if (inflater == null) {
      try {
        inflater = LayoutInflater.from(getApp());
      }
      catch (Exception e) {
        inflater = LayoutInflater.from(getCurrentActivity());
      }
    }
    return inflater;
  }


  private List<Activity> activityList = new LinkedList<>();

  private WeakReference<Activity> currentActivityWeakRef;
  public Activity getCurrentActivity() {
    return currentActivityWeakRef == null ? null : currentActivityWeakRef.get();
  }

  public void setCurrentActivity(Activity activity) {
    this.activity = activity;
    if (currentActivityWeakRef == null || ! activity.equals(currentActivityWeakRef.get())) {
      currentActivityWeakRef = new WeakReference<>(activity);
    }

    UnitAutoApp.setCurrentActivity(activity);
  }

  private WeakReference<Fragment> currentFragmentWeakRef;
  public Fragment getCurrentFragment() {
    return currentFragmentWeakRef == null ? null : currentFragmentWeakRef.get();
  }
  public void setCurrentFragment(Fragment fragment) {
    this.fragment = fragment;
    if (fragment != null && (currentFragmentWeakRef == null || ! fragment.equals(currentFragmentWeakRef.get()))) {
      currentFragmentWeakRef = new WeakReference<>(fragment);
    }
  }

  public DialogInterface getCurrentDialog() {
      return getCurrentDialog(null);
  }
  public DialogInterface getCurrentDialog(String className) {
    List<DialogInterface> list = dialogMap.get(getCurrentActivity());
    if (list == null || list.isEmpty()) {
        return null;
    }

    for (int i = list.size() - 1; i >= 0; i--) {
        DialogInterface dialog = list.get(i);
        if (dialog != null && (StringUtil.isEmpty(className) || className.endsWith(dialog.getClass().getName()))) {
            return dialog;
        }
    }

    return null;
  }

  private Map<Object, List<PopupWindow>> popupWindowMap = new LinkedHashMap<>(); // FIXME List<PopupWindow>
//  private PopupWindow popupWindow; // FIXME 根据触摸位置或 popupWindow.view.setOnTouchListener 来确定使用哪个
  public PopupWindow getCurrentPopupWindow(Double x, Double y) {
    List<PopupWindow> list = popupWindowMap.get(activity);
    if (list == null || list.isEmpty()) {
      return null;
    }

    PopupWindow curPw = null;
    View curCv = null;
    for (int i = list.size() - 1; i >= 0; i--) {
      PopupWindow pw = list.get(i);
      View cv = pw == null ? null : pw.getContentView();
      if (cv == null) {
          continue;
      }

      if (x == null || y == null) {
          return pw;
      }

      float x1 = cv.getX();
      float y1 = cv.getY();
      if (x >= x1 && y >= y1 || x <= x1 + cv.getWidth() || y <= y1 + cv.getHeight()) {
        if (curCv == null || curCv.getZ() < cv.getZ()) {
          curPw = pw;
          curCv = pw.getContentView();
        }
      }
    }

    return curPw;
  }

//  public void setCurrentPopupWindow(PopupWindow pw, View v, Window.Callback callback, @NotNull Activity activity, Fragment fragment) {
//    this.popupWindow = pw;
//
////    if (activity == null) {
////      activity = fragment == null ? getCurrentActivity() : fragment.getActivity();
////    }
//
//    if (pw == null) {
////      popupWindowMap.remove(activity);
//    }
//    else {
//      List<PopupWindow> list = popupWindowMap.get(activity);
//      if (list == null) {
//          list = new ArrayList<>();
//      }
//      list.add(pw);
//      popupWindowMap.put(activity, list);
//
//      pw.setTouchInterceptor(new View.OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//          addInputEvent(event, callback, activity, fragment, getCurrentDialog());
////          pw.dismiss();
//
////          if (event.getAction() == MotionEvent.ACTION_UP) {
////            setCurrentPopupWindow(null, null, callback, activity, fragment);
////          }
//          return false;
//        }
//      });
//    }
//
//    setCurrentView(v, callback, activity, fragment, null, pw);
//  }


  private Map<Object, View> viewMap = new LinkedHashMap<>();
  private View view;
  public void setCurrentView(View v, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    this.view = v;

    if (v == null) {
      viewMap.remove(activity);
    }
    else {
      if (v instanceof WebView == false) {
        viewMap.put(activity, v);
      }

      v.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (popupWindow != null) {
            return false;
          }

          addInputEvent(event, callback, activity, fragment, dialog, popupWindow);

//          if (event.getAction() == MotionEvent.ACTION_UP) {
//            setCurrentPopupWindow(null, null, callback, activity, fragment);
//          }
          return false;
        }
      });
    }
  }





//  public boolean onTouchEvent(@NotNull MotionEvent event, @NotNull Activity activity) {
//    return onTouchEvent(event, activity, null);
//  }
//  public boolean onTouchEvent(@NotNull MotionEvent event, @NotNull Fragment fragment) {
//    return onTouchEvent(event, fragment.getActivity(), fragment);
//  }
  public boolean onTouchEvent(@NotNull MotionEvent event, @NotNull Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    addInputEvent(event, activity, activity, fragment, dialog, popupWindow);
    return true;
  }
  public boolean onKeyDown(int keyCode, @NotNull KeyEvent event, @NotNull Activity activity, Fragment fragment) {
    return onKeyDown(keyCode, event, activity, fragment, null, null);
  }
  public boolean onKeyDown(int keyCode, @NotNull KeyEvent event, @NotNull Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    addInputEvent(event, activity, activity, fragment, dialog, popupWindow);
    return true;
  }

  public boolean onKeyUp(int keyCode, @NotNull KeyEvent event, @NotNull Activity activity, Fragment fragment) {
    return onKeyUp(keyCode, event, activity, fragment, null, null);
  }
  public boolean onKeyUp(int keyCode, @NotNull KeyEvent event, @NotNull Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    addInputEvent(event, activity, activity, fragment, dialog, popupWindow);
    return true;
  }

  public void record() {
    showCoverAndSplit(true, true);
    isAutoMoveBall = true;

    if (step <= 0) {
      lastKeyDownEventObj = null;
      lastKeyUpEventObj = null;
      outputList.clear();
    //  onUIEvent(InputUtil.UI_ACTION_CREATE, callback, activity, fragment, dialog, webView, webView == null ? null : webView.getUrl());
//      onUIEvent(InputUtil.UI_ACTION_RESUME, callback, activity, fragment, dialog, webView, webView == null ? null : webView.getUrl());
      onUIEvent(InputUtil.UI_ACTION_RESUME, callback, activity, null, null, webView, webView == null ? null : webView.getUrl());
    }
  }


  private int lastOrientation;
  // LifecycleOwner 只覆盖 Activity, Fragment, 而 Window.Callback 只覆盖 Activity, DialogInterface
  private final Map<Object, BallPoint[]> ballPositionMap = new HashMap<>();
  private final Map<String, BallPoint[]> classBallPositionMap = new HashMap<>();
//  @Override
  public void onConfigurationChanged(Configuration newConfig) {
//    super.onConfigurationChanged(newConfig);

    if (newConfig == null || lastOrientation == newConfig.orientation) {
      return;
    }
    lastOrientation = newConfig.orientation;
    updateScreenWindowContentSize();

    postDelayed(new Runnable() {
      @Override
      public void run() {
        updateScreenWindowContentSize();

        if (isShowing) {
//          FloatWindow.destroy("floatBall");
//          FloatWindow.destroy("floatBall2");
//          FloatWindow.destroy("floatCover");
//          FloatWindow.destroy("floatController");
//          try {
//            FloatWindow.destroy("floatSplitX");
//            FloatWindow.destroy("floatSplitY");
//            FloatWindow.destroy("floatSplitX2");
//            FloatWindow.destroy("floatSplitY2");
//          }
//          catch (Throwable e) {
//            e.printStackTrace();
//          }

          showCover(true);
          // if (isSplitShowing) {
            floatBall = showSplit(floatBall, isSplitShowing, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
            // if (isSplit2Showing) {
              floatBall2 = showSplit(floatBall2, isSplitShowing && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY);

        }
      }
    }, 1000);
  }

  public void showCoverAndSplit(boolean showCover, boolean showSplit) {
    showCover(showCover);
    floatBall = showSplit(floatBall, showSplit, splitX, splitY, "floatBall", vFloatBall, floatSplitX, floatSplitY);
    floatBall2 = showSplit(floatBall2, showSplit && isSplit2Showing, splitX2, splitY2, "floatBall2", vFloatBall2, floatSplitX2, floatSplitY2);
  }

  //TODO 仅在触摸 ball 时显示分割线，重写 onTouchEvent
  private IFloatWindow floatCover;
  private IFloatWindow floatController;
  private IFloatWindow floatBall, floatBall2;
  private IFloatWindow floatSplitX;
  private IFloatWindow floatSplitY;
  private IFloatWindow floatSplitX2;
  private IFloatWindow floatSplitY2;

  private boolean isShowing = false;
  public void showCover(boolean show) {
    isShowing = show;

//    导致遮挡触摸，试了几个方法都不能很好地解决，还不如 4 条分割线单独放 FloatWindow
//     floatCover = FloatWindow.get("floatCover");
//     if (floatCover == null) {
//       FloatWindow
//         .with(getApplicationContext())
//         .setTag("floatCover")
//         .setView(vFloatCover)
//         .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)      //设置控件宽高
//         .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
//         // .setX(windowX)                                   //设置控件初始位置
//         // .setY(windowY)
//         .setMoveType(MoveType.inactive)
//         .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏                        //桌面显示
// //                .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
// //                .setPermissionListener(mPermissionListener)  //监听权限申请结果
//         .build();
//
//       floatCover = FloatWindow.get("floatCover");
//     }
//     floatCover.show();
//     floatCover.hide();

    floatController = FloatWindow.get("floatController");
    if (floatController == null) {
      FloatWindow
        .with(getApplicationContext())
        .setTag("floatController")
        .setView(vFloatController)
        .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)  // windowWidth - windowX)                               //设置控件宽高
//					.setHeight(windowHeight)
//                     .setX(windowX)                                   //设置控件初始位置
        .setY(dp2px(isReplay ? -8 : 120))
        .setMoveType(MoveType.slide)
        .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏                        //桌面显示
//                .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
//                .setPermissionListener(mPermissionListener)  //监听权限申请结果
        .build();

      floatController = FloatWindow.get("floatController");
    }



    floatSplitX = getSplitX(false);
    // floatSplitX.show();

    floatSplitY = getSplitY(false);
    // floatSplitY.show();

    floatSplitX2 = getSplitX(true);
    // floatSplitX2.show();

    floatSplitY2 = getSplitY(true);
    // floatSplitY2.show();


    if (show) {
      floatController.show();
//      floatSplitX.show();
//      floatSplitX2.show();
//      floatSplitY.show();
//      floatSplitY2.show();
    } else {
      floatController.hide();
    }

//    if (floatBall != null) {
//      floatSplitX.updateX(floatBall.getX() - splitRadius);
//      floatSplitY.updateY(floatBall.getY() - splitRadius);
//    }
//    if (floatBall2 != null) {
//      floatSplitX2.updateX(floatBall2.getX() - splitRadius);
//      floatSplitY2.updateY(floatBall2.getY() - splitRadius);
//    }
    floatSplitX.hide();
    if (floatSplitX2 != null) {
      floatSplitX2.hide();
    }
    floatSplitY.hide();
    if (floatSplitY2 != null) {
      floatSplitY2.hide();
    }
  }


  private IFloatWindow getSplitX(boolean isVice) {
    return getSplitX(isVice ? "floatSplitX2" : "floatSplitX", isVice ? vSplitX2 : vSplitX);
  }
  private IFloatWindow getSplitX(String name, View vSplitX) {
    IFloatWindow floatSplitX = FloatWindow.get(name);
    if (floatSplitX == null) {
      FloatWindow
              .with(getApplicationContext())
              .setTag(name)
              .setView(vSplitX)
              .setHeight(ViewGroup.LayoutParams.MATCH_PARENT)                    //设置控件宽高
              .setMoveType(MoveType.inactive)
              .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏                        //桌面显示
              .build();

      floatSplitX = FloatWindow.get(name);
    }
    return floatSplitX;
  }

  private IFloatWindow getSplitY(boolean isVice) {
    return getSplitY(isVice ? "floatSplitY2" : "floatSplitY", isVice ? vSplitY2 : vSplitY);
  }
  private IFloatWindow getSplitY(String name, View vSplitY) {
    IFloatWindow floatSplitY = FloatWindow.get(name);
    if (floatSplitY == null) {
      FloatWindow
              .with(getApplicationContext())
              .setTag(name)
              .setView(vSplitY)
              .setWidth(ViewGroup.LayoutParams.MATCH_PARENT)                    //设置控件宽高
              .setMoveType(MoveType.inactive)
              .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏                        //桌面显示
              .build();

      floatSplitY = FloatWindow.get(name);
    }
    return floatSplitY;
  }


  private IFloatWindow showFloatView(boolean show, String tag, View view, int width, int height, int x, int y, int moveType) {
    IFloatWindow fw = FloatWindow.get(tag);
    if (show == false) {
      if (fw != null) {
        fw.hide();
      }
      return fw;
    }

    if (fw == null) {
      FloatWindow
        .with(getApplicationContext())
        .setTag(tag)
        .setView(view)
        .setWidth(width)                               //设置控件宽高
        .setHeight(height)
        .setX(x)                                   //设置控件初始位置
        .setY(y)
        .setMoveType(moveType)
        .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏                        //桌面显示
//                .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
//                .setPermissionListener(mPermissionListener)  //监听权限申请结果
        .build();

      fw = FloatWindow.get(tag);
    }
    fw.show();

    return fw;
  }

//  private MotionEvent lastDownEvent, lastUpEvent;
  private float lastBallDownX, lastBallDownY, lastBallUpX, lastBallUpY;

  public boolean isSplitShowing, isSplit2Showing;
  private boolean isAutoMoveBall = true;
  public IFloatWindow showSplit(IFloatWindow floatBall_, boolean show, double splitX, double splitY, String ballName, FloatBallView vFloatBall, IFloatWindow floatSplitX_, IFloatWindow floatSplitY_) {
    return showSplit(floatBall_, show, splitX, splitY, ballName, vFloatBall, floatSplitX_, floatSplitY_, false);
  }
  public IFloatWindow showSplit(IFloatWindow floatBall_, boolean show, double splitX, double splitY, String ballName, FloatBallView vFloatBall, IFloatWindow floatSplitX_, IFloatWindow floatSplitY_, boolean isDown) {
    // vSplitX.setVisibility(View.GONE);
    // vSplitY.setVisibility(View.GONE);
    // vSplitX2.setVisibility(View.GONE);
    // vSplitY2.setVisibility(View.GONE);

    // floatCover.hide();

    // showFloatView(true, "splitX", vSplitX_, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, splitX, 0, MoveType.inactive);
    // showFloatView(true, "splitY", vSplitY_, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 0, splitY, MoveType.inactive);
    // showFloatView(true, "splitX2", vSplitX2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT, splitX2, 0, MoveType.inactive);
    // showFloatView(true, "splitY2", vSplitY2, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 0, splitY2, MoveType.inactive);

    boolean canRefresh = isDown || ! (isReplayingInput() && isReplay);
    boolean isBall2 = "floatBall2".equals(ballName);
    int x = (int) Math.round(splitX - splitRadius + (splitX > 0 ? 0 : windowWidth)); // 只有贴边才会自动处理 decorWidth); // 已被 FloatWindow 处理 windowX + decorX
    int y = (int) Math.round(splitY - splitRadius + (splitY > 0 ? 0 : windowHeight)); // - (isSeparatedStatus ? 0 : statusHeight))); // + navigationHeight)); // 只有贴边才会自动处理  decorHeight); // 已被 FloatWindow 处理 windowY + decorY

    if (canRefresh) {
      setGravityImage(vFloatBall, isBall2 ? ballGravity2 : ballGravity);
      if (floatSplitX_ != null) {
        try {
          floatSplitX_.updateX((int) Math.round(x + splitRadius - dp2px(0.5f)));
        } catch (Throwable e) {
          e.printStackTrace();
        }
      }
      if (floatSplitY_ != null) {
        try {
          floatSplitY_.updateY((int) Math.round(y + splitRadius - dp2px(0.5f)));
        } catch (Throwable e) {
          e.printStackTrace();
        }
      }
      vSplitX.setVisibility(View.GONE);
      vSplitY.setVisibility(View.GONE);
      vSplitX2.setVisibility(View.GONE);
      vSplitY2.setVisibility(View.GONE);

      if (floatBall2 != null) {
//      floatBall2.show();
        floatBall2.hide();
      }
    }

    IFloatWindow ball = floatBall_ != null ? floatBall_ : FloatWindow.get(ballName);
    if (show == false) {
      if (ball != null) {
//        ball.show();
        ball.hide();
      }
      return ball;
    }

    if (ball != null) {
      ball.updateX(x);
      ball.updateY(y);
    }
    else {
      vFloatBall.setExtraOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          if (event.getAction() == MotionEvent.ACTION_DOWN) {
            isAutoMoveBall = false; // isReplay
//            lastDownEvent = event;
            lastBallDownX = event.getX();
            lastBallDownY = event.getY();

            // 虽然也能实现，但线条区域拦截了触摸事件
            vSplitX.setVisibility(floatBall != null && floatBall.isShowing() ? vFloatBall.getVisibility() : View.GONE);
            vSplitY.setVisibility(vSplitX.getVisibility());
            vSplitX2.setVisibility(floatBall2 != null && floatBall2.isShowing() ? vFloatBall2.getVisibility() : View.GONE);
            vSplitY2.setVisibility(vSplitX2.getVisibility());

            tvControllerX.setVisibility(v.getVisibility());
            tvControllerY.setVisibility(v.getVisibility());

            // floatCover.show();

            // 太卡了 // 避免线条区域拦截了触摸事件
            // if (vFloatBall.getVisibility() == View.VISIBLE) {
            //   if (floatSplitX != null) {
            //     floatSplitX.show();
            //   }
            //   if (floatSplitY != null) {
            //     floatSplitY.show();
            //   }
            // } else {
            //   if (floatSplitX != null) {
            //     floatSplitX.hide();
            //   }
            //   if (floatSplitY != null) {
            //     floatSplitY.hide();
            //   }
            // }
            //
            // if (vFloatBall2.getVisibility() == View.VISIBLE) {
            //   if (floatSplitX2 != null) {
            //     floatSplitX2.show();
            //   }
            //   if (floatSplitY != null) {
            //     floatSplitY2.show();
            //   }
            // } else {
            //   if (floatSplitX != null) {
            //     floatSplitX2.hide();
            //   }
            //   if (floatSplitY != null) {
            //     floatSplitY2.hide();
            //   }
            // }

//            floatSplitX2 = getSplitX(true);
//            floatSplitX2.hide();
//            floatSplitX2.show();
//            floatSplitY2 = getSplitY(true);
//            floatSplitY2.hide();
//            floatSplitY2.show();
          }
          else if (event.getAction() == MotionEvent.ACTION_UP) {
//            lastUpEvent = event;
            lastBallUpX = event.getX();
            lastBallUpY = event.getY();

            // floatCover.hide();

            // 虽然也能实现，但线条区域拦截了触摸事件
            vSplitX.setVisibility(View.GONE);
            vSplitY.setVisibility(View.GONE);
            vSplitX2.setVisibility(View.GONE);
            vSplitY2.setVisibility(View.GONE);

            tvControllerX.setVisibility(View.GONE);
            tvControllerY.setVisibility(View.GONE);

            // 太卡了 // 避免线条区域拦截了触摸事件
            // if (floatSplitX != null) {
            //   floatSplitX.hide();
            // }
            // if (floatSplitY != null) {
            //   floatSplitY.hide();
            // }
            //
            // if (floatSplitX2 != null) {
            //   floatSplitX2.hide();
            // }
            // if (floatSplitY2 != null) {
            //   floatSplitY2.hide();
            // }
          }

          return false;
        }
      });

      vFloatBall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//          lastDownEvent = null;
//          lastUpEvent = null;
          lastBallUpX = lastBallDownX = lastBallUpY = lastBallDownY = 0;

          if (isSplit2Showing == false) {
            v.performLongClick();
            return;
          }

          int[] gravities = InputUtil.BALL_GRAVITIES;
          int gty;
          if (isBall2) {
            gty = ballGravity2 = (ballGravity2 + 1)%gravities.length;
            setGravityImageAndText(vFloatBall, ballGravity2, tvControllerGravityX, false, gravityX);
            setGravityImageAndText(vFloatBall, ballGravity2, tvControllerGravityY, true, gravityY);
          } else {
            gty = ballGravity = (ballGravity + 1)%gravities.length;
            setGravityImageAndText(vFloatBall, ballGravity, tvControllerGravityX, false, gravityX);
            setGravityImageAndText(vFloatBall, ballGravity, tvControllerGravityY, true, gravityY);
          }

          toast(InputUtil.getBallGravityNameResId(gty));
        }
      });
      vFloatBall.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
          isRunning = false;

          double dx = lastBallUpX - lastBallDownX; // lastUpEvent == null ? 0 : lastUpEvent.getX() - (lastDownEvent == null ? lastUpEvent.getRawX() : lastDownEvent.getX());
          double dy = lastBallUpY - lastBallDownY; // lastUpEvent == null ? 0 : lastUpEvent.getY() - (lastDownEvent == null ? lastUpEvent.getRawY() : lastDownEvent.getY());
//          lastDownEvent = null;
//          lastUpEvent = null;

          lastBallUpX = lastBallDownX = lastBallUpY = lastBallDownY = 0;

          if (Math.pow(dx, 2) + Math.pow(dy, 2) > Math.pow(dp2px(8), 2)) {
            return true;
          }

          saveAllBallPositions();

          pageViewListMap = new LinkedHashMap<>();
          viewPropertyListMap = new LinkedHashMap<>();

          dismiss();

          new Thread(new Runnable() {
            @Override
            public void run() {
              String cacheKey = UIAutoListActivity.CACHE_TOUCH;
              if (eventList != null && eventList.isEmpty() == false) {
                SharedPreferences cache = getSharedPreferences();
                // JSONArray allList = null; // JSON.parseArray(cache.getString(cacheKey, null));
                //
                // if (allList == null || allList.isEmpty()) {
                //     allList = eventList;
                // } else {
                //     allList.addAll(eventList);
                // }


                JSONArray allList = eventList;

                // JSONArray allList = new JSONArray();  //  eventList; //
                // if (eventList != null) {
                //     for (int i = 0; i < eventList.size(); i++) {
                //         JSONObject obj = eventList.getJSONObject(i);
                //         if (obj != null && obj.getBooleanValue("disable") == false) {
                //             allList.add(obj);
                //         }
                //     }
                // }

                cache.edit().remove(cacheKey).putString(cacheKey, toJSONString(allList)).commit();
              }

              mainHandler.post(new Runnable() {
                @Override
                public void run() {
                  //                startActivity(UIAutoListActivity.createIntent(DemoApplication.getApp(), flowId));  // eventList == null ? null : eventList.toJSONString()));
//                startActivityForResult(UIAutoListActivity.createIntent(DemoApplication.getApp(), eventList == null ? null : eventList.toJSONString()), REQUEST_UI_AUTO_LIST);
                  count = 0;
                  startUIAutoListActivity(cacheKey);

                  new Thread(new Runnable() {
                    @Override
                    public void run() {
                      try {
                        String s = cache.getString(KEY_APP_SHARED_PREFERENCES, null);
                        JSONObject sharedPreferences = JSON.parseObject(s);
                        if (sharedPreferences != null && ! sharedPreferences.isEmpty()) {
                          JSONObject map = JSON.parseObject(cache.getString(KEY_APP_CACHE_NAME_CONFIG_MAP, null));
                          Set<Map.Entry<String, Object>> set = map == null ? null : map.entrySet();
                          if (set != null) {
                            for (Map.Entry<String, Object> entry : set) {
                              try {
                                String name = entry.getKey();
                                Object val = entry.getValue();
                                Integer mode = val instanceof Number || val instanceof String ? Integer.parseInt(String.valueOf(val)) : null;

                                Set<String> longKeys = new LinkedHashSet<String>();
                                Set<String> floatKeys = new LinkedHashSet<String>();
                                Set<String> stringSetKeys = new LinkedHashSet<String>();
                                if (mode == null && val instanceof JSONObject) {
                                  JSONObject obj = ((JSONObject) val);
                                  mode = obj.getInteger(KEY_MODE);

                                  JSONArray lks = obj.getJSONArray(KEY_LONG_KEYS);
                                  if (lks != null && ! lks.isEmpty()) {
                                    for (Object k : lks) {
                                      String key = k == null ? null : String.valueOf(k);
                                      if (key != null && ! longKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                                        longKeys.add(key);
                                      }
                                    }
                                  }

                                  JSONArray fks = obj.getJSONArray(KEY_FLOAT_KEYS);
                                  if (fks != null && ! fks.isEmpty()) {
                                    for (Object k : fks) {
                                      String key = k == null ? null : String.valueOf(k);
                                      if (key != null && ! floatKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                                        floatKeys.add(key);
                                      }
                                    }
                                  }

                                  JSONArray keys = obj.getJSONArray(KEY_STRING_SET_KEYS);
                                  if (keys != null && ! keys.isEmpty()) {
                                    for (Object k : keys) {
                                      String key = k == null ? null : String.valueOf(k);
                                      if (key != null && ! stringSetKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                                        stringSetKeys.add(key);
                                      }
                                    }
                                  }
                                }

                                SharedPreferences spf = getApp().getSharedPreferences(name, mode != null ? mode : Context.MODE_PRIVATE);
                                spf.edit().clear().commit();
                                putSharedPreferences(spf, sharedPreferences.getJSONObject(name), longKeys, floatKeys, stringSetKeys);
                              } catch (Throwable e) {
                                e.printStackTrace();
                              }
                            }

                            // getSharedPreferences().edit()
                            //         .remove(KEY_APP_SHARED_PREFERENCES)
                            //         .commit();
                          }
                        }
                      } catch (Throwable e) {
                        e.printStackTrace();
                      }
                    }
                  }).start();
                }
              });
            }
          }).start();

          return true;
        }
      });

      int size = (int) Math.round(splitSize);

      FloatWindow
        .with(getApplicationContext())
        .setTag(ballName)
        .setView(vFloatBall)
        .setWidth(size)                       //设置控件宽高
        .setHeight(size)
        .setX(x)                                   //设置控件初始位置
        .setY(y)
        .setMoveType(MoveType.active)
        .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏 //桌面显示
        .setViewStateListener(new ViewStateListener() {
          @Override
          public void onPositionUpdate(int x, int y) {
            IFloatWindow fb = floatBall_ != null ? floatBall_ : FloatWindow.get(ballName);
            onUpdateBallPosition(fb, vFloatBall, floatSplitX_, floatSplitY_, isBall2, x, y);

//            IFloatWindow floatBall = FloatWindow.get(ballName);
//            if (x != floatBall.getX() || y != floatBall.getY()) {
            curFocusView = null;
            tvControllerGravityContainer.setText("");
//            }

          }

          @Override
          public void onShow() {
            IFloatWindow fb = floatBall_ != null ? floatBall_ : FloatWindow.get(ballName);
            onPositionUpdate(fb == null ? x : fb.getX(), fb == null ? y : fb.getY());
          }

          @Override
          public void onHide() {
            if (floatSplitX_ != null) {
              floatSplitX_.hide();
            }
            if (floatSplitY_ != null) {
              floatSplitY_.hide();
            }
          }

          @Override
          public void onDismiss() {
            onHide();
          }

          @Override
          public void onMoveAnimStart() { }
          @Override
          public void onMoveAnimEnd() { }
          @Override
          public void onBackToDesktop() { }
        })    //监听悬浮控件状态改变
//                .setPermissionListener(mPermissionListener)  //监听权限申请结果
        .build();

      ball = FloatWindow.get(ballName);
    }

    ball.show();

    if (canRefresh) {
      tvControllerX.setText(splitX + "\n" + DECIMAL_FORMAT.format(splitX / windowWidth) + "%");
      tvControllerY.setText(splitY + "\n" + DECIMAL_FORMAT.format(splitY / windowHeight) + "%");

      if (floatSplitX_ != null && floatSplitX_.isShowing()) {
        floatSplitX_.updateX((int) (x + Math.round(splitRadius) - dp2px(0.5f)));
        floatSplitX_.hide();
      }
      if (floatSplitY_ != null && floatSplitY_.isShowing()) {
        floatSplitY_.updateY((int) (y + Math.round(splitRadius) - dp2px(0.5f)));
        floatSplitY_.hide();
      }
    }

    return ball;
  }

  private void onUpdateBallPosition(IFloatWindow floatBall, FloatBallView vFloatBall
          , IFloatWindow floatSplitX_, IFloatWindow floatSplitY_, boolean isBall2, int x, int y) {
    double splitX = x + splitRadius;
    double splitY = y + splitRadius;

    boolean out = false;
    if (splitX > windowWidth) {
      splitX = windowWidth;
      out = true;
    }
    else if (splitX < 0) {
      splitX = 0;
      out = true;
    }

    if (splitY > windowHeight) {
      splitY = windowHeight;
      out = true;
    }
    else if (splitY < 0) {
      splitY = 0;
      out = true;
    }

    if (out) {
      if (floatBall != null) {
        floatBall.updateX((int) Math.round(splitX - splitRadius));
        floatBall.updateY((int) Math.round(splitY - splitRadius));
      }
      return;
    }

    if (floatSplitX_ != null) { //  && floatSplitX_.isShowing()) {
      try {
        floatSplitX_.updateX((int) Math.round(splitX - dp2px(0.5f)));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }
    if (floatSplitY_ != null) { //   && floatSplitY_.isShowing()) {
      try {
        floatSplitY_.updateY((int) Math.round(splitY - dp2px(0.5f)));
      } catch (Throwable e) {
        e.printStackTrace();
      }
    }

    double xr = 100f*splitX/windowWidth;
//            double yr = 100f*(splitY + (isSeparatedStatus ? 0 : statusHeight))/windowHeight;
    double yr = 100f*splitY/windowHeight;

    tvControllerX.setText(DECIMAL_FORMAT.format(xr) + "%" + "\n" + DECIMAL_FORMAT.format(yr) + "%");
    tvControllerY.setText(Math.round(splitX) + "\n" + Math.round(splitY));

    int bg = isBall2 ? ballGravity2 : ballGravity;
    if (xr <= 50) {
      if (bg == GRAVITY_TOP_RIGHT) {
        bg = GRAVITY_TOP_LEFT;
      } else if (bg == GRAVITY_BOTTOM_RIGHT) {
        bg = GRAVITY_BOTTOM_LEFT;
      } else if (bg == GRAVITY_RATIO_RIGHT) {
        bg = GRAVITY_RATIO_LEFT;
      }
    } else {
      if (bg == GRAVITY_TOP_LEFT) {
        bg = GRAVITY_TOP_RIGHT;
      } else if (bg == GRAVITY_BOTTOM_LEFT) {
        bg = GRAVITY_BOTTOM_RIGHT;
      } else if (bg == GRAVITY_RATIO_LEFT) {
        bg = GRAVITY_RATIO_RIGHT;
      }
    }

    if (yr <= 50) {
      if (bg == GRAVITY_BOTTOM_LEFT) {
        bg = GRAVITY_TOP_LEFT;
      } else if (bg == GRAVITY_BOTTOM_RIGHT) {
        bg = GRAVITY_TOP_RIGHT;
      } else if (bg == GRAVITY_RATIO_BOTTOM) {
        bg = GRAVITY_RATIO_TOP;
      }
    } else {
      if (bg == GRAVITY_TOP_LEFT) {
        bg = GRAVITY_BOTTOM_LEFT;
      } else if (bg == GRAVITY_TOP_RIGHT) {
        bg = GRAVITY_BOTTOM_RIGHT;
      } else if (bg == GRAVITY_RATIO_TOP) {
        bg = GRAVITY_RATIO_BOTTOM;
      }
    }

    if (isBall2) {
      ballGravity2 = bg;
    } else {
      ballGravity = bg;
    }

    setGravityImageAndText(vFloatBall, bg, tvControllerGravityX, false, gravityX);
    setGravityImageAndText(vFloatBall, bg, tvControllerGravityY, true, gravityY);
  }

  private void setGravityImageAndText(FloatBallView vFloatBall, int ballGravity, TextView tv, boolean isY, int gravity) {
    setGravityImage(vFloatBall, ballGravity);
    setGravityText(tv, isY, gravity);
  }

  private void setGravityImage(FloatBallView vFloatBall, int ballGravity) {
    vFloatBall.setImageResource(isReplayingTouch ? R.drawable.add_light : InputUtil.getBallGravityImageResource(isSplit2Showing ? ballGravity : -1));
  }

  public static String toJSONString(Object obj) {
    if (obj == null || obj instanceof String) {
      return (String) obj;
    }

    return com.alibaba.fastjson.JSON.toJSONString(obj, new PropertyFilter() {
      @Override
      public boolean apply(Object object, String name, Object value) {
        if (value == null) {
          return true;
        }

        if (value instanceof Context
                || value instanceof Fragment
                || value instanceof android.app.Fragment
                || value instanceof Annotation  // Android 客户端中 fastjon 怎么都不支持 Annotation
                || value instanceof WindowManager
                || value instanceof PowerManager
                || value instanceof View
                || value instanceof ViewParent
                || value instanceof Drawable
                || value instanceof Bitmap
        ) {
          return false;
        }

        return Modifier.isPublic(value.getClass().getModifiers());
      }
    });
  }

  public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.0");


  public int getWindowX(Activity activity) {
    return 0;
    // View decorView = activity.getWindow().getContentView();
    //
    // Rect rectangle = new Rect();
    // decorView.getWindowVisibleDisplayFrame(rectangle);
    // return rectangle.left;
  }

  public int getWindowY(Activity activity) {
    return 0;
    // View decorView = activity.getWindow().getContentView();
    //
    // Rect rectangle = new Rect();
    // decorView.getWindowVisibleDisplayFrame(rectangle);
    // return rectangle.top;
  }

  double deltaX, deltaY, ballDeltaX, ballDeltaY, lastBallDeltaX, lastBallDeltaY;
  Window.Callback lastDeltaCallback;
  View lastDeltaView;
  MotionEvent lastDeltaEvent;

  public boolean dispatchEventToCurrentWindow(Node<InputEvent> node, InputEvent ie, boolean record) {
    if (ie == null) {
      ie = node == null ? null : node.item;
    }
    if (ie == null || node == null) {
      return false;
    }

//    activity = getCurrentActivity();
//    if (activity != null) {
//      if (ie instanceof MotionEvent) {
//        MotionEvent event = (MotionEvent) ie;
////        int windowX = getWindowX(activity);
////        int windowY = getWindowY(activity) + statusHeight;
////
////        if (windowX > 0 || windowY > 0) {
////          event = MotionEvent.obtain(event);
////          event.offsetLocation(windowX, windowY);
////        }
//
//        try {
//          activity.dispatchTouchEvent(event);
//        } catch (Throwable e) {  // java.lang.IllegalArgumentException: tagerIndex out of range
//          e.printStackTrace();
//        }
//      }
//      else if (ie instanceof KeyEvent) {
//        KeyEvent event = (KeyEvent) ie;
//        activity.dispatchKeyEvent(event);
//      }
//    }

    int type = node.type;
    int action = node.action;
    if (type == InputUtil.EVENT_TYPE_UI && StringUtil.isEmpty(node.fragment)) {
      if (action == InputUtil.UI_ACTION_CREATE) {
        return isReplayingInput() == false && sendActivityCreate(node);
      }
      if (action == InputUtil.UI_ACTION_RESULT) {
        return isReplayingInput() == false && sendActivityResult(node);
      }
    }

    if (isReplayingInput() == false && ((node.step <= 1 && action == InputUtil.UI_ACTION_RESUME)
      || (type == InputUtil.EVENT_TYPE_TOUCH && action == MotionEvent.ACTION_DOWN)
      || (type == InputUtil.EVENT_TYPE_KEY && action == KeyEvent.ACTION_DOWN))) {
      ensureCorrectActivity(null, node);
    }

    Window.Callback callback_ = callback;
    boolean isDialog = callback_ instanceof DialogInterface;

    View view_ = view;
    double x = node.x;
    double y = node.y;
    if (callback_ != null || view_ != null) {
      if (ie instanceof MotionEvent) {
        MotionEvent event = (MotionEvent) ie;
        int evtAction = event.getAction();

        boolean isPopupWindow = false;

        MotionEvent viewEvent = event;

//        int windowX = getWindowX(activity);
//        int windowY = getWindowY(activity) + statusHeight;
//
//        if (windowX > 0 || windowY > 0) {
//          event = MotionEvent.obtain(event);
//          event.offsetLocation(windowX, windowY);
//        }
        try {
          int gy = node.gravityY;
          if (gy < 0 && y < 0) {
            gy = GRAVITY_BOTTOM;
          }

          JSONObject obj = node.obj;
          boolean isKeyboardChange = keyboardHeight > 0 && gy >= 0 && gy != GRAVITY_TOP;
          PopupWindow popupWindow = isDialog ? null : getCurrentPopupWindow(x, y);
          isPopupWindow = view_ != null && popupWindow != null && popupWindow.isShowing();

          if (obj == null || obj.isEmpty()) {
            // FIXME DialogInterface/PopupWindow 内输入?
            if (isKeyboardChange) { // 重新算能兼容 && (callback instanceof DialogInterface == false)) {
              // FIXME 不完全一样，要考虑悬浮球位置
              double ny = gy == GRAVITY_BOTTOM ? (y <= 0 ? y : y - node.windowHeight)*node.ratio + windowHeight // 重新计算比这样更可靠  - keyboardHeight
                      : (gy == GRAVITY_RATIO ? windowHeight*(y >= 0 ? y : node.windowHeight + y)/node.windowHeight
                      : (gy == GRAVITY_CENTER ? windowHeight/2 : 0));
              if (ny > 0) { // 基本不会出现键盘把目标位置顶出屏幕的情况
                event = MotionEvent.obtain(event);
                event.offsetLocation(0f, (float) (ny + (isSeparatedStatus ? statusHeight : 0) - event.getY()));
              }
            }
          }
          else if (isDialog || isKeyboardChange) {
            node = obj2EventNode(obj, node, node.step);
            if (node.item instanceof MotionEvent) {
              event = (MotionEvent) node.item;
            }
          }

          float rx = event.getX();
          float ry = event.getY();

          if (isPopupWindow) {
            viewEvent = MotionEvent.obtain(event);
            viewEvent.offsetLocation(0f, - (float) statusHeight);

            ry = event.getY();
          }

//          double nry = ry;

          boolean isNotDown = evtAction != MotionEvent.ACTION_DOWN;
          if (isNotDown == false) {
            deltaX = 0;
            deltaY = 0;

            if (lastDeltaEvent != null && (lastBallDeltaX != 0 || lastBallDeltaY != 0)) { // && (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL)) {
              double dx = lastBallDeltaX;
              double dy = lastBallDeltaY;
              Window.Callback dcb = lastDeltaCallback;
              View dv = lastDeltaView;

              MotionEvent event0 = lastDeltaEvent;
              event0.offsetLocation((float) dx, (float) dy);
              event0.setAction(MotionEvent.ACTION_DOWN);

              dispatchTouchEvent(dcb, dv, event0, event0);

              MotionEvent event1 = MotionEvent.obtain(event0);
              event1.offsetLocation((float) -dx, (float) -dy);
              event1.setAction(MotionEvent.ACTION_MOVE);
              dispatchTouchEvent(dcb, dv, event1, event1);

              event1 = MotionEvent.obtain(event1);
              dispatchTouchEvent(dcb, dv, event1, event1);

              MotionEvent event2 = MotionEvent.obtain(event1);
              event2.setAction(MotionEvent.ACTION_UP);
              dispatchTouchEvent(dcb, dv, event2, event2);

//              ballDeltaX = - dx;
//              ballDeltaY = - dy;
//              rx -= dx;
//              ry -= dy;

              lastBallDeltaX = 0;
              lastBallDeltaY = 0;
              lastDeltaCallback = null;
              lastDeltaView = null;
              lastDeltaEvent = null;

//              try {
//                Thread.sleep(1);
//              } catch (Throwable e) {
//                e.printStackTrace();
//              }
            }

            if (node != null && node.isSplit2Show == false && obj != null) {
              double sx = obj.getDoubleValue("splitX");
              double sy = obj.getDoubleValue("splitY");

              double ratio = getScale(node.windowWidth, node.windowHeight, node.layoutType, node.density);
              if (ratio <= 0.1 || ratio >= 10) {
                ratio = 1;
              }

//              double left = floatBall == null ? (splitX >= 0 ? splitX - windowWidth : splitX + windowWidth) : floatBall.getX() + splitRadius;
              double left = (splitX > 0 ? splitX - windowWidth : splitX + windowWidth) + (x >= 0 ? 0 : windowWidth);
              double right = ratio*(sx > 0 ? sx - node.windowWidth : sx + node.windowWidth) + (x >= 0 ? 0 : windowWidth);
//              double top = floatBall == null ? (splitY >= 0 ? splitY - windowHeight : splitY + windowHeight) : floatBall.getY() + splitRadius + (isSeparatedStatus ? statusHeight : 0);
              double top = (splitY > 0 ? splitY - windowHeight : splitY + windowHeight) + (y >= 0 ? 0 : windowHeight) + (isSeparatedStatus ? statusHeight : 0);
              double bottom = ratio*(sy > 0 ? sy - node.windowHeight : sy + node.windowHeight) + (y >= 0 ? 0 : windowHeight) + (isSeparatedStatus ? statusHeight : 0);
//
//              double oy = (isSeparatedStatus ? statusHeight : 0);
//              double dx = rx >= 0 && rx <= windowWidth ? 0 : (windowWidth - ratio*node.windowWidth)*(rx < 0 ? -1 : 1);
//              double dy = ry >= oy && ry <= windowHeight + oy ? 0 : (windowHeight - ratio*node.windowHeight)*(ry < oy ? -1 : 1);

              // 本质上都是超出了当前 Window 显示区域
               if ((rx > left && rx < right) || (rx < left && rx > right) || (ry > top && ry < bottom) || (ry < top && ry > bottom)) {
//              if (dx != 0 || dy != 0) {
                double dx = left - right;
                double dy = top - bottom;

                MotionEvent event0 = MotionEvent.obtain(isPopupWindow ? viewEvent : event);
                event0.offsetLocation((float) dx, (float) dy);
//                event0.setAction(MotionEvent.ACTION_DOWN);
                dispatchTouchEvent(callback_, view_, event0, event0);

                MotionEvent event1 = MotionEvent.obtain(event0);
                event1.offsetLocation((float) dx, (float) dy);
                event1.setAction(MotionEvent.ACTION_MOVE);
                dispatchTouchEvent(callback_, view_, event1, event1);

                event1 = MotionEvent.obtain(event1);
                dispatchTouchEvent(callback_, view_, event1, event1);

                MotionEvent event2 = MotionEvent.obtain(event1);
                event2.setAction(MotionEvent.ACTION_UP);
                dispatchTouchEvent(callback_, view_, event2, event2);

                ballDeltaX += dx;
                ballDeltaY += dy;

                rx += dx;
                ry += dy;
//                event = MotionEvent.obtain(event0);

                lastBallDeltaX = dx;
                lastBallDeltaY = dy;
                lastDeltaCallback = callback_;
                lastDeltaView = view_;
                lastDeltaEvent = MotionEvent.obtain(event0);
              }
            }

          }

          View rv = isDialog && contentView != null ? contentView : decorView;

          View v = isDialog || isNotDown ? null : findViewByPoint(rv, null, rx, ry, FOCUS_ANY, true);
          int vid = v == null ? 0 : v.getId();
          String vidName = isNotDown ? null : getResIdName(vid);

          int tid = isNotDown || obj == null ? 0 : obj.getIntValue("targetId");
          String tidName = isNotDown || obj == null ? null : obj.getString("targetIdName");
          int tid2 = isNotDown || tidName == null ? 0 : getResId(tidName);

          int childCount = obj == null ? 0 : obj.getIntValue("childCount");
          Integer childIndex = obj == null ? null : obj.getInteger("childIndex");
          childIndex = childIndex == null ? null : (childIndex >= 0 ? childIndex : childIndex + childCount);

          ViewParent vp = v == null ? null : v.getParent();
          ViewGroup vg = vp instanceof ViewGroup ? (ViewGroup) vp : null;
          int ind = vg == null ? -1 : vg.indexOfChild(v);

          boolean ignore = isDialog || isNotDown || obj == null || (
                  ( (vid > 0 && vid == tid) || (vidName != null && Objects.equals(vidName, tidName)) )
                          && ( childIndex == null || ind < 0 || (childIndex >= 0 && childIndex == ind)
                          || (childIndex < 0 && childIndex + vg.getChildCount() == ind) )
          );

          NearestView<View> nv = ignore ? null : findNearestView(rv, null, rx, ry, true, tid2 > 0 ? tid2 : tid, childIndex, null);
          if (ignore == false && nv == null && childIndex != null) {
            nv = findNearestView(rv, null, rx, ry, true, tid2 > 0 ? tid2 : tid, null, null);
          }

          View tv = nv == null ? null : nv.view;
          if (ignore == false && tv == null) {
            int fid = obj.getIntValue("focusId");
            String fidName = obj.getString("focusIdName");

            int focusChildCount = obj.getIntValue("focusChildCount");
            Integer focusChildIndex = obj.getInteger("focusChildIndex");
            focusChildIndex = focusChildIndex == null ? focusChildIndex : (focusChildIndex >= 0 ? focusChildIndex : focusChildIndex + focusChildCount);

            int fid2 = fidName == null ? 0 : getResId(fidName);
            nv = findNearestView(rv, null, rx, ry, true, fid2 > 0 ? fid2 : fid, focusChildIndex, null);
            if (nv == null && focusChildIndex != null) {
              nv = findNearestView(rv, null, rx, ry, true, fid2 > 0 ? fid2 : fid, null, null);
            }

            tv = nv == null ? null : nv.view;

            if (tv == null) {
              int pid = obj.getIntValue("parentId");
              String pidName = obj.getString("parentIdName");
              int parentChildCount = obj.getIntValue("parentChildCount");
              Integer parentChildIndex = obj.getInteger("parentChildIndex");
              parentChildIndex = parentChildIndex == null ? null : (parentChildIndex >= 0 ? parentChildIndex : parentChildIndex + parentChildCount);

              int pid2 = pidName == null ? 0 : getResId(pidName);
              nv = findNearestView(rv, null, rx, ry, true, pid2 > 0 ? pid2 : pid, parentChildIndex, null);
              if (nv == null && parentChildIndex != null) {
                nv = findNearestView(rv, null, rx, ry, true, pid2 > 0 ? pid2 : pid, null, null);
              }

              tv = nv == null ? null : nv.view;
            }
          }

          boolean isTV = v instanceof TextView;
          if (tv == null && ignore && isTV) {
            tv = v;
          }

          if (tv != null) {
            int d = dp2px(1.1);

            Integer textIndex = obj != null && tv instanceof TextView ? obj.getInteger("textIndex") : null;
            String txt = textIndex == null || textIndex < 0 ? null : StringUtil.get((TextView) tv);
            int len = txt == null ? 0 : txt.length();
            Rect rect = len <= 0 ? null : TextViewUtil.getSelectionRect((TextView) tv, textIndex < len - 1 ? textIndex : len - 1);

            float l, r, t, b;
            if (rect != null && Math.abs(rect.right - rect.left) >= d && Math.abs(rect.bottom - rect.top) >= d) {
              int[] tLoc = new int[2];
              tv.getLocationOnScreen(tLoc);

              l = rect.left + tLoc[0];
              r = rect.right + tLoc[0];
              t = rect.top + tLoc[1];
              b = rect.bottom + tLoc[1];
            }
            else {
              if (nv == null) {
                nv = new NearestView<>(tv);
              }

              if (nv.left * nv.right * nv.top * nv.bottom == 0) {
                int[] tLoc = new int[2];
                tv.getLocationOnScreen(tLoc);
                nv.left = tLoc[0];
                nv.right = nv.left + tv.getWidth();
                nv.top = tLoc[1];
                nv.bottom = nv.top + tv.getHeight();

                nv.paddingLeft = tv.getPaddingLeft();
                nv.paddingRight = tv.getPaddingRight();
                nv.paddingTop = tv.getPaddingTop();
                nv.paddingBottom = tv.getPaddingBottom();
              }

              int p = dp2px(10);
              l = nv.left + (nv.paddingLeft >= p ? 0 : nv.paddingLeft);
              r = nv.right - (nv.paddingRight >= p ? 0 : nv.paddingRight);
              t = nv.top + (nv.paddingTop >= p ? 0 : nv.paddingTop);
              b = nv.bottom - (nv.paddingBottom >= p ? 0 : nv.paddingBottom);
            }

            float dx = 0;
            if (rx < l) {
              dx = l + d - rx;
            }
            else if (rx > r) {
              dx = r - d - rx;
            }

            float dy = 0;
            if (ry < t) {
              dy = t + d - ry;
            }
            else if (ry > b) {
              dy = b - d - ry;
            }

            deltaX = dx;
            deltaY = dy;

//            if (view_ == null && obj.getIntValue("pointerCount") <= 1) {
//              view_ = tv;
//            }
          }

          if (deltaX + ballDeltaX != 0 || deltaY + ballDeltaY != 0) {
            event = MotionEvent.obtain(isPopupWindow ? viewEvent : event);
            event.offsetLocation((float) (deltaX + ballDeltaX), (float) (deltaY + ballDeltaY));

            if (isPopupWindow) {
              viewEvent = event;
            }
          }

          dispatchTouchEvent(callback_, view_, event, viewEvent);
        }
        catch (Throwable e) {  // java.lang.IllegalArgumentException: targetIndex out of range
          e.printStackTrace();
        }
        finally { // FIXME 惯性划动会被 制止 还原位置，避免点击其它区域时错位
          if ((ballDeltaX != 0 || ballDeltaY != 0) && (evtAction == MotionEvent.ACTION_UP || evtAction == MotionEvent.ACTION_CANCEL)) {
//            double dx = ballDeltaX;
//            double dy = ballDeltaY;
//
//            MotionEvent event0 = MotionEvent.obtain(isPopupWindow ? viewEvent : event);
//            event0.offsetLocation((float) dx, (float) dy);
//            event0.setAction(MotionEvent.ACTION_DOWN);
//
//            postDelayed(new Runnable() {
//              @Override
//              public void run() {
//                dispatchTouchEvent(callback_, view_, event0, event0);
//
//                MotionEvent event1 = MotionEvent.obtain(event0);
//                event1.offsetLocation((float) -dx, (float) -dy);
//                event1.setAction(MotionEvent.ACTION_MOVE);
//                dispatchTouchEvent(callback_, view_, event1, event1);
//
//                event1 = MotionEvent.obtain(event1);
//                dispatchTouchEvent(callback_, view_, event1, event1);
//
//                MotionEvent event2 = MotionEvent.obtain(event1);
//                event2.setAction(MotionEvent.ACTION_UP);
//                dispatchTouchEvent(callback_, view_, event2, event2);
//
                ballDeltaX = 0;
                ballDeltaY = 0;
//              }
//            }, 1 + calcDuration(node, node == null ? null : node.next)); // FIXME next down event or not motion event
          }
        }
      }
      else if (ie instanceof KeyEvent) {
        if (ie instanceof EditTextEvent) {
          EditTextEvent ete = (EditTextEvent) ie;
          if (ete.getWhen() == EditTextEvent.WHEN_ON) {
            EditText target = ete.getTarget();
            if (target != null) {
              if (target.hasFocus() == false) {
                target.requestFocus();
              }

              String text = StringUtil.getString(target.getText());
              int l = text.length();
              int start = Math.min(l, Math.max(0, ete.getSelectStart()));
              int end = Math.min(l, Math.max(0, ete.getSelectEnd()));
              target.setSelection(start, end);

              target.setText(ete.getText());

              String text2 = StringUtil.getString(target.getText());
              int l2 = text2.length();
              int start2 = Math.min(l2, Math.max(0, ete.getSelectStart()));
              int end2 = Math.min(l2, Math.max(0, ete.getSelectEnd()));
              if (end2 <= 0) {
                start2 = end2 = l2;
              }

              target.setSelection(start2, end2);
            }

            String targetWebId = ete.getTargetWebId();
            if (webView != null && (StringUtil.isNotEmpty(targetWebId, true) || (ete.getX() != null && ete.getY() != null))) {
              String script = "" + // ""(function() {\n" +
                      "  var map = document.uiautoEditTextMap || {};\n" +
                      "  var targetWebId = '" + targetWebId + "';\n" +
                      "  var et = map[targetWebId] || document.getElementById(targetWebId);\n" +
                      "  var ae = document.activeElement;\n" +
                      "  if (et == null /* && (ae instanceof HTMLInputElement || ae instanceof HTMLTextAreaElement) */ && ['input', 'textarea'].indexOf(ae.localName) >= 0 && ['INPUT', 'TEXTAREA'].indexOf(ae.tagName) >= 0) {\n" +
                      "    et = ae;\n" +
                      "  }\n" +
                      "  var x = " + ete.getX() + ";\n" +
                      "  var y = " + ete.getY() + ";\n" +
                      "  if (et == null) {\n" +
                      "    et = map[x + ',' + y];\n" +
                      "  }\n" +
                      "  if (et == null) {\n" +
                      "    function findEditText(x, y) {\n" +
                      "\n" +
                      "      var inputs = document.getElementsByTagName('input');\n" +
                      "      var textareas = document.getElementsByTagName('textarea');\n" +
                      "\n" +
                      "      function getZIndex(e) {\n" +
                      "        if (e instanceof HTMLElement == false) {\n" +
                      "          return null;\n" +
                      "        }\n" +
                      "\n" +
                      "        var style = document.defaultView.getComputedStyle(e);\n" +
                      "        var z = style == null ? null : style.getPropertyValue('z-index');\n" +
                      "        return z == null || Number.isNaN(z) ? getZIndex(e.parentNode) : z;\n" +
                      "      }\n" +
                      "\n" +
                      "      function findItem(editTexts, target) {\n" +
                      "        if (editTexts == null || editTexts.length <= 0) {\n" +
                      "          return target;\n" +
                      "        }\n" +
                      "\n" +
                      "        var tz = getZIndex(target);\n" +
                      "        for (var i = 0; i < editTexts.length; i ++) {\n" +
                      "          var et = editTexts.item(i);\n" +
                      "\n" +
                      "          var rect = et == null || et.disabled /* || (ae instanceof HTMLInputElement == false && ae instanceof HTMLTextAreaElement == false) */ || ['INPUT', 'TEXTAREA'].indexOf(et.tagName) < 0 ? null : et.getBoundingClientRect();\n" +
                      "          var left = rect == null ? null : rect.left;\n" +
                      "          var right = left == null ? null : rect.right;\n" +
                      "          var top = right == null ? null : rect.top;\n" +
                      "          var bottom = top == null ? null : rect.bottom;\n" +
                      "          if (bottom == null || x < left || x > right || y < top || y > bottom) {\n" +
                      "            continue;\n" +
                      "          }\n" +
                      "\n" +
                      "          if (target == null) {\n" +
                      "            target = et;\n" +
                      "            continue;\n" +
                      "          }\n" +
                      "\n" +
                      "          var z = getZIndex(et);\n" +
                      "          if (tz == null || (z != null && z > tz)) {\n" +
                      "            target = et;\n" +
                      "            tz = z;\n" +
                      "          }\n" +
                      "        }\n" +
                      "\n" +
                      "        return target;\n" +
                      "      }\n" +
                      "\n" +
//                      "      var target = findItem(inputs, null, true);\n" +
//                      "      if (target instanceof HTMLElement) {\n" +
//                      "        return target;\n" +
//                      "      }\n" +
//                      "\n" +
//                      "      var target2 = findItem(textareas, null, true);\n" +
//                      "      if (target2 instanceof HTMLElement) {\n" +
//                      "        return target2;\n" +
//                      "      }\n" +
                      "\n" +
                      "      var target = findItem(inputs, null);\n" +
                      "      var target2 = findItem(textareas, target);\n" +
                      "\n" +
                      "      console.log(\"findViewByPoint(\" + x + \", \" + y + \") = \" + (target2 == null ? null : target2.id));\n" +
//                      "      alert(\"findViewByPoint(\" + x + \", \" + y + \") = \" + (target2 == null ? null : target2.id));\n" +
                      "      return target2;\n" +
                      "    }\n" +
                      "    \n" +
                      "    et = findEditText(x, y);\n" +
                      "    map[x + ',' + y] = et;\n" +
                      "  }\n" +
                      "  \n" +
                      "  if (et == null) {\n" +
                      "    et = inputs == null || inputs.length <= 0 ? null : inputs.item(0);\n" +
                      "  }\n" +
                      "  if (et == null) {\n" +
                      "    et = textareas == null || textareas.length <= 0 ? null : textareas.item(0);\n" +
                      "  }\n" +
                      "  try {\n" +
                      "    et.value = '" + StringUtil.getString(ete.getText()).replaceAll("'", "\\'") + "';\n" +
                      "    et.focus();\n" +
                      "  } catch (e) {\n" +
                      "    console.log(e);\n" +
                      "  }\n" +
//                      "})();\n" +
//                      "var ret = 'document.uiautoEditTextMap = ' + JSON.stringify(document.uiautoEditTextMap);\n" +
                      "  et";
              webView.evaluateJavascript(script , new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                  Log.d(TAG, "dispatchEventToCurrentWindow webView.evaluateJavascript >> onReceiveValue value = " + value);
                }
              });
            }
          }
        }
        else if ((view_ == null || view_.dispatchKeyEvent((KeyEvent) ie) == false) && callback_ != null) {
          callback_.dispatchKeyEvent((KeyEvent) ie);
        }
      }
    }

    if (record) {
      addInputEvent(ie, callback_, activity, fragment, isDialog ? (DialogInterface) callback_ : null, isDialog ? null : getCurrentPopupWindow(x, y));
    }

    return callback_ != null || view_ != null;
  }

  public boolean ensureCorrectActivity(Activity activity, Node<InputEvent> curNode) {
    if (
//            isReplayingInput() ||
            curNode == null || curNode.disable || curNode.type == InputUtil.EVENT_TYPE_HTTP
            || (curNode.type == InputUtil.EVENT_TYPE_UI && (
                    curNode.action == InputUtil.UI_ACTION_DESTROY || curNode.action == InputUtil.UI_ACTION_STOP
            ))
            || (curNode.type == InputUtil.EVENT_TYPE_TOUCH && curNode.action != MotionEvent.ACTION_DOWN)
            || (curNode.type == InputUtil.EVENT_TYPE_KEY && curNode.action != KeyEvent.ACTION_DOWN)
    ) {
      return true;
    }

    String targetActivity = curNode.activity;
    if (StringUtil.isEmpty(targetActivity) || Objects.equals(targetActivity
            , activity == null ? null : activity.getClass().getName())) {
        if (curNode.type == InputUtil.EVENT_TYPE_TOUCH && curNode.action == MotionEvent.ACTION_DOWN) {

            List<DialogInterface> list = dialogMap.get(activity);
            int last = (list == null ? 0 : list.size()) - 1;
//            List<DialogInterface> list2 = new ArrayList<>();

            for (int i = last; i >= 0; i--) {
                DialogInterface dialog = list.get(i);
                if (dialog == null) {
                    continue;
                }

                if (dialog.getClass().getName().equals(curNode.dialog)) {
                    // 可能乱序 list2 = list.subList(0, i); // 保留底部的
                    if (! ((Dialog) dialog).isShowing()) {
                        ((Dialog) dialog).show();
                    }
                    break;
                }

                try {
                    dialog.dismiss(); // 可能直接移除？
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

//            if (list2.size() < last + 1) {
//                dialogMap.put(activity, list2);
//            }
        }

        return true;
    }

    List<Activity> list = curNode.step <= 1 && ! StringUtil.isEmpty(targetActivity) ? activityList : null;
    if (list != null) { // TODO 改成从 Activity A 》B 》C 这种路径来 初始化 intent: [intentA, intentB, intentC]
      boolean mock = curNode.mock != null ? curNode.mock : isProxyEnabled();
      while (! list.isEmpty()) {
        int i = list.size() - 1;
        Activity act = list.get(i);
        if (act == null) {
          list.remove(i);
          continue;
        }

        String name = act.getClass().getName();
        boolean isSame = Objects.equals(targetActivity, name);
        if (isSame && ! mock) {
          return true;
        }

        if (i == 0 && ! (mock && isSame)) {
          break;
        }

        try {
          act.finish(); // 光是 FLAG_ACTIVITY_REORDER_TO_FRONT 也不能保证返回上页后还是正确的上页
        } catch (Throwable e) {
          e.printStackTrace();
        }
        list.remove(act);

        if (act.equals(activity)) {
          activity = null;
        }

        if (isSame) {
          break;
        }
      }

    }

    boolean isCreate = curNode.type == InputUtil.EVENT_TYPE_UI && curNode.action == InputUtil.UI_ACTION_CREATE
            && StringUtil.isEmpty(curNode.fragment);
//    boolean show = isSplitShowing;

    if (isCreate && activity != null) {
//      while (isReplayingInput()) {}

//      isSplitShowing = false;
      try {
        activity.finish();
      } catch (Throwable e) {
        e.printStackTrace();
      }
//      isSplitShowing = show;
    }

    Activity curAct = getCurrentActivity();
    if (isCreate == false && (curAct == null || ! Objects.equals(curAct.getClass().getName(), targetActivity))) {
      try {
        Intent intent = new Intent(curAct == null ? getApp() : curAct, Class.forName(targetActivity));
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        isSplitShowing = false;

//        while (isReplayingInput()) {}
        startActivity(intent);
      } catch (Throwable e) {
        e.printStackTrace();
      }

//      isSplitShowing = show;

      curAct = getCurrentActivity();
    }

    if (! Objects.equals(curAct.getClass().getName(), targetActivity)) {
//      while (isReplayingInput()) {}

      return sendActivityCreate(curNode);
    }

    return true;
  }

  private Map<String, Activity> activityCreatMap = new HashMap<>();

  public boolean sendActivityCreate(Node<InputEvent> node) {
    try {
      Activity activity = getCurrentActivity();
      Intent intent = node.intent;
      if (intent == null) {
        Context ctx = activity == null ? getApp() : activity;
        intent = new Intent(ctx, Class.forName(node.activity));

        JSONObject obj = node.obj;
        Intent itt = parseIntent(obj.getJSONObject("intent"), node.activity);
        if (itt != null) {
          if (intent.getFlags() == 0) {
            intent.setFlags(itt.getFlags());
          }
          if (itt.getData() != null && intent.getData() == null) {
            intent.setDataAndType(itt.getData(), itt.getType());
          }
          if (itt.getExtras() != null && intent.getExtras() == null) {
            intent.putExtras(itt.getExtras());
          }
        }
      }

      if (activityCreatMap.get(node.activity) != null) {
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // 解决重复创建
      }

      if (activity != null) {
        activity.startActivityForResult(intent, node.requestCode);
      } else {
        getApp().startActivity(intent);
      }

      activityCreatMap.put(node.activity, getCurrentActivity());
      return true;
    } catch (Throwable e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean sendActivityResult(Node<InputEvent> node) {
    try {
      Activity activity = getCurrentActivity();
//      try {
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//      } catch (Throwable e) {
//        e.printStackTrace();
//      }

      if (! isForeground(activity)) {
        Intent intent = new Intent(getApp(), activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);

//        if (! isForeground(activity)) {
//          Window window = getCurrentWindow();
//
//          KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK);
////          activity.onKeyDown(KeyEvent.KEYCODE_BACK, event);
////        event.dispatch(activity);
//          window.superDispatchKeyEvent(event);
//
//          KeyEvent event2 = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK);
////          activity.onKeyUp(KeyEvent.KEYCODE_BACK, event2);
//          window.superDispatchKeyEvent(event2);
//        }
      }

      Method method = Activity.class.getDeclaredMethod("onActivityResult", int.class, int.class, Intent.class);
      method.setAccessible(true);
      method.invoke(activity, node.requestCode, node.resultCode, node.intent);
      return true;
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static boolean isForeground(Activity activity) {
    ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
    List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
    ActivityManager.RunningTaskInfo task = tasks == null || tasks.isEmpty() ? null : tasks.get(0);
    ComponentName topActivity = task == null ? null : task.topActivity;
    return topActivity != null && Objects.equals(topActivity.getClassName(), activity.getClass().getName());
  }

  private void dispatchTouchEvent(Window.Callback callback_, View view_, MotionEvent event, MotionEvent viewEvent) {
    if (viewEvent == null) {
      viewEvent = event;
    }
    if (viewEvent == null) {
      return;
    }

    if ((view_ == null || (view_.dispatchTouchEvent(viewEvent) == false)) && callback_ != null) {
      callback_.dispatchTouchEvent(event == null ? viewEvent : event);
    }
  }


  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public int dp2px(double dpValue) {
    final double scale = DENSITY;
    return (int) Math.round(dpValue*scale);  // + 0.5f 是为了让结果四舍五入
  }

  /**
   * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
   */
  public int px2dp(float pxValue) {
    return (int) (pxValue / DENSITY + 0.5f);  // + 0.5f 是为了让结果四舍五入
  }

  /**
   * 根据字体大小从 px(像素) 的单位 转成为 sp
   */
  public int px2sp(float pxValue) {
    return (int) (pxValue / SCALED_DENSITY + 0.5f);  // + 0.5f 是为了让结果四舍五入
  }
  /**
   * 根据字体大小从 sp 的单位 转成为 px(像素)
   */
  public int sp2px(float spValue) {
    return (int) (spValue * SCALED_DENSITY + 0.5f);  // + 0.5f 是为了让结果四舍五入
  }

  private Node<InputEvent> firstEventNode;
  public Node<InputEvent> getFirstEventNode() {
    return firstEventNode;
  }
  private Node<InputEvent> currentEventNode;
  public Node<InputEvent> getCurrentEventNode() {
    return currentEventNode;
  }

  private long duration = 0;
  private int allStep = 0;
  private int step = 0;
  private int lastStep = 0;
//  private int lastWaitStep = 0;

  private long startTime, currentTime;
  public void replay() {
    replay(0);
  }
  public void replay(int step) {
    isRunning = true;
    isReplay = true;
    isReplayingTouch = false;
    isReplayingEdit = false;
    isAutoMoveBall = true;

//        List<InputEvent> list = new LinkedList<>();
    boolean isFirst = step <= 0 || step >= allStep || currentEventNode == null;
    if (isFirst) {
      step = 0;
      duration = 0;
      startTime = System.currentTimeMillis();

      currentEventNode = firstEventNode;
      outputList.clear();
    }
    else if (step != currentEventNode.step){
      Node<InputEvent> curNode = firstEventNode;
      for (int i = 0; i < step; i++) {
        curNode = curNode == null ? null : curNode.next;
        if (curNode == null) {
          curNode = firstEventNode;
          step = 0;
          break;
        }
      }

      currentEventNode = curNode;
    }

    this.step = step;
    currentTime = System.currentTimeMillis();

    JSONObject first = allStep <= 0 ? null : eventList.getJSONObject(0);
    long firstTime = first == null ? 0 : first.getLongValue("time");

    if (firstTime <= 0) {
      Toast.makeText(getApp(), R.string.finished_because_of_no_step, Toast.LENGTH_SHORT).show();
      tvControllerPlay.setText(R.string.replay);
      showCoverAndSplit(true, false);
    }
    else {
      tvControllerPlay.setText(R.string.replaying);
      showCoverAndSplit(true, true);

      lastBallDeltaX = 0;
      lastBallDeltaY = 0;
      lastDeltaCallback = null;
      lastDeltaView = null;
      lastDeltaEvent = null;

      //通过递归链表来实现
      Message msg = handler.obtainMessage();
      msg.obj = currentEventNode;
      handler.sendMessage(msg);

      if (isFirst) {
        onUIEvent(InputUtil.UI_ACTION_RESUME, callback, activity, fragment, null, webView, webView == null ? null : webView.getUrl());
      }
    }

  }

  public void prepareAndSendEvent(@NotNull JSONArray eventList) {
    prepareAndSendEvent(eventList, 0);
  }
  public void prepareAndSendEvent(@NotNull JSONArray eventList, int step) {
    currentEventNode = null;
    if (eventList == null || eventList.isEmpty()) {
      firstEventNode = null;
      return;
    }

    Node<InputEvent> eventNode = new Node<>(null, null, null);
    for (int i = 0; i < eventList.size(); i++) {
      JSONObject obj = eventList.getJSONObject(i);
      eventNode = obj2EventNode(obj, eventNode, i + 1);
      if (eventNode == null) { // || obj.getBooleanValue("disable")) {
        continue;
      }

      // if (i <= 0) {
      //   firstEventNode = new Node<>(null, null, null);
      //   eventNode = firstEventNode;
      // }

      eventNode.next = new Node<>(eventNode, null, null);
      if (i <= 0) {
        firstEventNode = eventNode;
      }
      if (i == step - 1) {
        currentEventNode = eventNode;
      }

      eventNode = eventNode.next;
    }

    if (currentEventNode == null) {
      currentEventNode = firstEventNode;
    }
  }

  public Node<InputEvent> obj2EventNode(JSONObject obj, Node<InputEvent> eventNode, int step) {
    if (eventNode == null) {
      eventNode = new Node<>(null, null, null);
    }

    int type = obj.getIntValue("type");
    int action = obj.getIntValue("action");

    InputEvent event;
    if (type == InputUtil.EVENT_TYPE_KEY) {
      if (obj.getBooleanValue("edit")) {
        String idName = obj.getString("targetIdName");
        int id = getResId(idName);
        if (id <= 0) {
          id = obj.getIntValue("targetId");
        }

        View target = activity.findViewById(id);
        String webId = obj.getString("targetWebId");
        if (! (target instanceof EditText)) {
          target = findView(webId);
          if (!(target instanceof EditText)) {
            int id2 = obj.getIntValue("targetId");
            if (id2 != id && id2 > 0) {
              target = findView(id);
            }
          }
        }

        EditTextEvent ete = new EditTextEvent(
                obj.getLongValue("downTime"),
                obj.getLongValue("eventTime"),
                obj.getIntValue("action"),
                obj.getIntValue("keyCode"),
                obj.getIntValue("repeatCount"),
                obj.getIntValue("metaState"),
                obj.getIntValue("deviceId"),
                obj.getIntValue("scanCode"),
                obj.getIntValue("flags"),
                obj.getIntValue("source"),
                target instanceof EditText ? (EditText) target : null,
                obj.getIntValue("when"),
                obj.getString("text"),
                obj.getIntValue("selectStart"),
                obj.getIntValue("selectEnd"),
                obj.getString("s"),
                obj.getIntValue("start"),
                obj.getIntValue("length"),
                obj.getIntValue("after")
        );
        ete.setTargetWebId(webId);
        ete.setX(obj.getInteger("x"));
        ete.setY(obj.getInteger("y"));
        event = ete;
      } else {
        /**
         public KeyEvent(long downTime, long eventTime, int action,
         int code, int repeat, int metaState,
         int deviceId, int scancode, int flags, int source) {
         mDownTime = downTime;
         mEventTime = eventTime;
         mAction = action;
         mKeyCode = code;
         mRepeatCount = repeat;
         mMetaState = metaState;
         mDeviceId = deviceId;
         mScanCode = scancode;
         mFlags = flags;
         mSource = source;
         mDisplayId = INVALID_DISPLAY;
         }
         */
        event = new KeyEvent(
                obj.getLongValue("downTime"),
                obj.getLongValue("eventTime"),
                obj.getIntValue("action"),
                obj.getIntValue("keyCode"),
                obj.getIntValue("repeatCount"),
                obj.getIntValue("metaState"),
                obj.getIntValue("deviceId"),
                obj.getIntValue("scanCode"),
                obj.getIntValue("flags"),
                obj.getIntValue("source")
        );
      }
    }
    else if (type == InputUtil.EVENT_TYPE_TOUCH) {
      /**
       public static MotionEvent obtain(long downTime, long eventTime, int action,
       double x, double y, double pressure, double size, int metaState,
       double xPrecision, double yPrecision, int deviceId, int edgeFlags, int source,
       int displayId)
       */

      //居然编译报错，和
      // static public MotionEvent obtain(long downTime, long eventTime,
      //    int action, int tagerCount, PointerProperties[] tagerProperties,
      //    PointerCoords[] tagerCoords, int metaState, int buttonState,
      //    double xPrecision, double yPrecision, int deviceId,
      //    int edgeFlags, int source, int displayId, int flags)
      //冲突，实际上类型没传错

      //                    event = MotionEvent.obtain(obj.getLongValue("downTime"),  obj.getLongValue("eventTime"),  obj.getIntValue("action"),
      //                    obj.getDoubleValue("x"),  obj.getDoubleValue("y"),  obj.getDoubleValue("pressure"),  obj.getDoubleValue("size"),  obj.getIntValue("metaState"),
      //                    obj.getDoubleValue("xPrecision"),  obj.getDoubleValue("yPrecision"),  obj.getIntValue("deviceId"),  obj.getIntValue("edgeFlags"),  obj.getIntValue("source"),
      //                    obj.getIntValue("displayId"));

      eventNode.splitSize = splitSize;  // 只是本地显示  Math.round(obj.getIntValue("splitSize")*ratio);
      eventNode.orientation = obj.getIntValue("orientation");

      int layoutType = obj.getIntValue("layoutType");
      double density = obj.getDoubleValue("density");

      double ww = obj.getDoubleValue("windowWidth");
      double wh = obj.getDoubleValue("windowHeight");
      double sh = obj.getDoubleValue("statusHeight");
      double kh = obj.getDoubleValue("keyboardHeight");
      double nh = obj.getDoubleValue("navigationHeight");
      double dw = obj.getDoubleValue("decorWidth"); // DialogInterface 下和弹窗布局一致
      double dh = obj.getDoubleValue("decorHeight"); // DialogInterface 下和弹窗布局一致

      String dialog = obj.getString("dialog");
      double dlgX = obj.getDoubleValue("dialogX");
      double dlgY = obj.getDoubleValue("dialogY");
      double dlgW = obj.getDoubleValue("dialogWidth");
      double dlgH = obj.getDoubleValue("dialogHeight");

      boolean isDialog = StringUtil.isNotEmpty(dialog, true);
      double cw = isDialog ? dlgW : ww; // obj.getDoubleValue("decorWidth");
      double ch = isDialog ? dlgH : wh; // - sh; // obj.getDoubleValue("decorHeight") - sh - nh;
      if (cw <= 100) {
        cw = dw;
      }
      if (ch <= 100) {
        ch = dh; // - sh; // - nh;
      }

      double ratio = getScale(cw, ch, layoutType, density);
      if (ratio <= 0.1) {
        ratio = 1;
      }

      eventNode.layoutType = layoutType;
      eventNode.ratio = ratio;
      eventNode.density = density;
      eventNode.windowWidth = ww;
      eventNode.windowHeight = wh;
      eventNode.keyboardHeight = ratio*kh;

      boolean isSplit2Show = obj.getBooleanValue("isSplit2Show");
      Integer gravityViewId = obj.getInteger("gravityViewId");
      String gravityViewIdName = obj.getString("gravityViewIdName");

      Integer gravityX = obj.getInteger("gravityX"); // 数据库字段默认值设置为 null // - 1;
      Integer gravityY = obj.getInteger("gravityY"); // 数据库字段默认值设置为 null // - 1;
      Integer ballGravity = obj.getInteger("ballGravity"); // 数据库字段默认值设置为 null // - 1;
      Integer ballGravity2 = obj.getInteger("ballGravity2"); // 数据库字段默认值设置为 null // - 1;
      if (gravityViewId == null) {
        gravityViewId = -1;
      }
      if (gravityX == null) {
        gravityX = -1;
      }
      if (gravityY == null) {
        gravityY = -1;
      }
      if (ballGravity == null) {
        ballGravity = -1;
      }
      if (ballGravity2 == null) {
        ballGravity2 = -1;
      }

      int pc = obj.getIntValue("pointerCount");
      double x = obj.getDoubleValue("x");
      double y = obj.getDoubleValue("y");
      double x2 = obj.getDoubleValue("x2");
      double y2 = obj.getDoubleValue("y2");

      boolean isCur = isSplit2Show && (step == this.step || Objects.equals(getCurrentActivity().getClass().getName(), obj.getString("activity")));
      int id = isCur ? getResId(gravityViewIdName) : 0;
      View curView = isCur ? findView(id > 0 ? id : gravityViewId) : null;
      //      Rect curRect = curView == null ? null : new Rect();

      int[] loc = curView == null ? null : new int[2];
      if (loc != null) {
        // 居然不是 curView，而是窗口的 0, 81 - 1080, 2400  curView.getWindowVisibleDisplayFrame(curRect);
        curView.getLocationOnScreen(loc);
      }

      double sttH = isDialog ? 0 : statusHeight;
      double ccw = isDialog ? dialogWidth : windowWidth;
      double cch = isDialog ? dialogHeight : windowHeight;

      double sx = 0; // loc == null ? obj.getDoubleValue("splitX") : loc[0] + curFocusView.getWidth() - curFocusView.getPaddingRight(); //curRect.right - curView.getPaddingRight();
      double sy = 0; // = loc == null ? obj.getDoubleValue("splitY") : loc[1] + curFocusView.getHeight() - curFocusView.getPaddingBottom() - statusHeight; //curRect.bottom - curView.getPaddingBottom();
      double sx2 = 0; // = loc == null ? obj.getDoubleValue("splitX2") : loc[0] + curFocusView.getPaddingLeft(); // curRect.left + curView.getPaddingLeft();
      double sy2 = 0; // = loc == null ? obj.getDoubleValue("splitY2") : loc[1] + curFocusView.getPaddingTop() - statusHeight; // curRect.top + curView.getPaddingTop();
      if (loc != null) {
        sx = loc[0] + curView.getWidth() - curView.getPaddingRight(); //curRect.right - curView.getPaddingRight();
        sy = loc[1] + curView.getHeight() - curView.getPaddingBottom() - sttH; //curRect.bottom - curView.getPaddingBottom();
        sx2 = loc[0] + curView.getPaddingLeft(); // curRect.left + curView.getPaddingLeft();
        sy2 = loc[1] + curView.getPaddingTop() - sttH; // curRect.top + curView.getPaddingTop();
      }

      if (sx2 <= 0 || sx >= ccw || sy2 <= 0 || sy >= cch || Math.abs(sx - sx2) < 30 || Math.abs(sy - sy2) < 30) {
        sx = transSplitX(obj.getDoubleValue("splitX"), cw, ballGravity, ratio);
        sy = transSplitY(obj.getDoubleValue("splitY"), ch, ballGravity, ratio);
        sx2 = transSplitX(obj.getDoubleValue("splitX2"), cw, ballGravity2, ratio);
        sy2 = transSplitY(obj.getDoubleValue("splitY2"), ch, ballGravity2, ratio);
      }

      eventNode.x = x;
      eventNode.y = y;
      eventNode.x2 = x2;
      eventNode.y2 = y2;
      eventNode.isSplit2Show = isSplit2Show;
      eventNode.splitX = sx;
      eventNode.splitY = sy;
      eventNode.splitX2 = sx2;
      eventNode.splitY2 = sy2;
      eventNode.gravityX = gravityX;
      eventNode.gravityY = gravityY;

      // double ratio = getScale(ww, ) //  1f*windowWidth/ww;  //始终以显示时宽度比例为准，不管是横屏还是竖屏   1f*Math.min(windowWidth, windowHeight)/Math.min(ww, wh);

      // 既然已经存了 上下 绝对坐标、屏幕像素 等完整信息，没必要用负值？负值保证稳定，因为 18:9 和 16:9 的分割线高度不一样
      sx = sx > 0 ? sx : ww + sx; // 转为正数
      double minSX = sx2 <= 0 ? sx : Math.min(sx, sx2);
      double maxSX = sx2 <= 0 ? sx : Math.max(sx, sx2);

      sy = sy > 0 ? sy : wh + sy; // 转为正数
      double minSY = sy2 <= 0 ? sy : Math.min(sy, sy2);
      double maxSY = sy2 <= 0 ? sy : Math.max(sy, sy2);

      if (isDialog) {
        minSX -= dlgX;
        maxSX -= dlgX;

        minSY = minSY - dlgY + (isSeparatedStatus ? statusHeight : 0);
        maxSY = maxSY - dlgY + (isSeparatedStatus ? statusHeight : 0);
      }

      double rx;
      if (gravityX == GRAVITY_RATIO) {
        double maxSX2 = ccw + ratio*(maxSX - ww);
        rx = minSX + (maxSX2 - ratio*minSX)*(x - minSX)/(maxSX - minSX);
      }
      else if (gravityX == GRAVITY_CENTER || (gravityX < 0 && x > minSX && x < maxSX)) { //居中，一般是弹窗
        double mid = (minSX + maxSX)/2f; // minSX + (maxSX2 - minSX)*(x - minSX)/(maxSX - minSX)
//          rx = x < mid ? ratio*x : decorWidth*mid/cw + ratio*(x - maxSX); // 居中靠左/靠右，例如关闭按钮
//        rx = windowWidth*mid/cw + ratio*(x - mid); // 居中靠左/靠右，例如关闭按钮

        double maxSX2 = ccw + ratio*(maxSX - ww);
        double mid2 = (ratio*minSX + maxSX2)/2f;
        rx = mid2 + ratio*(x - mid); // 居中靠上/靠下，例如 取消、确定 按钮
      }
      else if (gravityX == GRAVITY_RIGHT || (gravityX < 0 && (x < 0 || (x >= maxSX && (isDialog == false || x < dlgW))))) { // 靠右，例如列表项右侧标记已读、添加、删除、数量输入框等按钮
        rx = ccw + ratio*(x < 0 ? x : x - cw);
      }
      else { // if (gravityX == GRAVITY_LEFT || (x >= 0 && x <= minSX)) { // 靠左
        rx = ratio*x;
      }


      // 不一定这样，例如 小米 12 Pro 因为有摄像头挖孔所以横屏过来会默认不显示左侧摄像头占的宽度 // 进一步简化上面的，横向是所有都一致 rx = ratio*x + decorView.getX();

      double ry;
      if (gravityY == GRAVITY_RATIO) {
        double maxSY2 = cch + ratio*(maxSY - wh);
        ry = minSY + (maxSY2 - ratio*minSY)*(y - minSY)/(maxSY - minSY);
      }
      else if (gravityY == GRAVITY_CENTER || (gravityY < 0 && y > minSY && y < maxSY)) { //居中，一般是弹窗
        double mid = (minSY + maxSY)/2f;
//        ry = (windowHeight /* - (isSeparatedStatus ? 0 : statusHeight) */)*mid/ch + ratio*(y - mid); // 居中靠上/靠下，例如 取消、确定 按钮

        double maxSY2 = cch + ratio*(maxSY - wh);
        double mid2 = (ratio*minSY + maxSY2)/2f;
        ry = mid2 + ratio*(y - mid); // 居中靠上/靠下，例如 取消、确定 按钮
      }
      else if (gravityY == GRAVITY_BOTTOM || (gravityY < 0 && (y < 0 || (y >= maxSY && (isDialog == false || y < dlgH))))) { // 靠下，例如底部 tab、菜单按钮、悬浮按钮等
        ry = cch /* - (isSeparatedStatus ? 0 : statusHeight) */ + ratio*(y < 0 ? y : y - ch); // decorHeight + ratio*(y < 0 ? y : y - ch);
      }
      else { // if (gravityY == GRAVITY_TOP || (y >= 0 && y <= minSY)) { // 靠上
        ry = ratio*y;
      }

      rx += windowX + decorX;
      ry += windowY + decorY + sttH; // 此时不能确定 (view != null && popupWindow != null && popupWindow.isShowing() ? 0 : statusHeight); // + (isSeparatedStatus ? statusHeight : 0);

      eventNode.rx = rx;
      eventNode.ry = ry;

      if (x2 == 0 || y2 == 0) {
        event = MotionEvent.obtain(
                obj.getLongValue("downTime"),
                obj.getLongValue("eventTime"),
                obj.getIntValue("action"),
//                            obj.getIntValue("targetCount"),
                (float) rx,
                (float) ry,
                obj.getFloatValue("pressure"),
                obj.getFloatValue("size"),
                obj.getIntValue("metaState"),
                obj.getFloatValue("xPrecision"),
                obj.getFloatValue("yPrecision"),
                obj.getIntValue("deviceId"),
                obj.getIntValue("edgeFlags")
//                            obj.getIntValue("source"),
//                            obj.getIntValue("displayId")
        );

        ((MotionEvent) event).setSource(obj.getIntValue("source"));
//                    ((MotionEvent) event).setEdgeFlags(obj.getIntValue("edgeFlags"));
      }
      else {
        MotionEvent.PointerCoords p1 = new MotionEvent.PointerCoords();
        p1.x = (float) rx;
        p1.y = (float) ry;

        MotionEvent.PointerCoords p2 = new MotionEvent.PointerCoords();
        p2.x = (float) (rx + x2 - x);
        p2.y = (float) (ry + y2 - y);

        eventNode.rx2 = p2.x;
        eventNode.ry2 = p2.y;

        int[] ids = obj.getObject("pointerIds", int[].class); // 有时 getString 取出来是 "L[I@123" 这种内存地址
        JSONArray pointers = obj.getJSONArray("pointers");

        // int[] ids = pointerIds instanceof int[] ? (int[]) pointerIds
        //         : JSON.parseObject(JSON.toJSONString(pointerIds), int[].class);
        int len = ids == null ? 0 : ids.length;
        int size = pointers == null ? 0 : pointers.size();
        pc = Math.max(2, size); // > 2 || len < size ? size : Math.min(len, size));

        MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[pc];
        pointerCoords[0] = p1;
        pointerCoords[1] = p2;

        if (ids == null || len != pc) {
          ids = Arrays.copyOf(len <= 0 ? new int[]{0, 1} : ids, pc);
        }

        double lastX = (p1.x + p2.x)/2.0, lastY = (p1.y + p2.y)/2.0;
        for (int i = 2; i < pc; i++) {
          JSONObject oi = pointers.getJSONObject(i);
          if (oi == null) {
            oi = new JSONObject();
          }
          int id_ = oi.getIntValue("id");
          ids[i] = Math.max(id_, i);

          Double xi = oi.getDouble("x");
          Double yi = oi.getDouble("y");
          if (xi == null || xi == 0) {
            xi = lastX;
          }
          if (yi == null || yi == 0) {
            yi = lastY;
          }
          lastX = lastX + xi/2.0;
          lastY = lastY + yi/2.0;

          MotionEvent.PointerCoords pi = pointerCoords[i] = new MotionEvent.PointerCoords();
          pi.x = (float) (rx + xi - x);
          pi.y = (float) (ry + yi - y);
          pi.size = oi.getIntValue("size");
          pi.pressure = oi.getIntValue("pressure");
        }

        event = MotionEvent.obtain(
                obj.getLongValue("downTime"),
                obj.getLongValue("eventTime"),
                obj.getIntValue("action"),
                pc,
                ids, // ids != null && ids.length >= 2 ? ids : new int[]{0, 1},
                pointerCoords, // new MotionEvent.PointerCoords[] {p1, p2},
                obj.getIntValue("metaState"),
//                obj.getIntValue("buttonState"),
                obj.getFloatValue("xPrecision"),
                obj.getFloatValue("yPrecision"),
                obj.getIntValue("deviceId"),
                obj.getIntValue("edgeFlags"),
                obj.getIntValue("source"),
                obj.getIntValue("flags")
        );
      }

    }
    else {
      event = null;
    }

    String host = obj.getString("host");
    String url = obj.getString("url");
    if (StringUtil.isEmpty(host)) {
      host = StringUtil.getHost(url);
    }
    if (StringUtil.isNotEmpty(host) && url != null && url.startsWith(host)) {
      url = url.substring(host.length());
    }

    int ind = url == null ? -1 : url.indexOf("?");
    String query = ind < 0 ? null : url.substring(ind + 1);
    url = ind < 0 ? url : url.substring(0, ind);

//                list.add(event);

    eventNode.step = step;
    eventNode.id = obj.getLongValue("id");
    eventNode.flowId = obj.getLongValue("flowId");
    eventNode.targetId = obj.getLongValue("targetId");
    eventNode.targetIdName = obj.getString("targetIdName");
    eventNode.disable = obj.getBooleanValue("disable");
    eventNode.type = type;
    eventNode.action = action;
    eventNode.time = obj.getLongValue("time");
    eventNode.timeout = obj.getLongValue("timeout");
    eventNode.activity = obj.getString("activity");
    eventNode.fragment = obj.getString("fragment");
    eventNode.dialog = obj.getString("dialog");
    eventNode.format = obj.getString("format");
    eventNode.status = obj.getString("status");
    eventNode.method = obj.getString("method");
    eventNode.host = host;
    eventNode.url = url;
//    eventNode.header = obj.getString("header");
//    eventNode.request = obj.getString("request");
//    eventNode.response = obj.getString("response");

    String thrw = obj.getString("throw");
    String exception = obj.getString("exception");
    if (StringUtil.isNotEmpty(thrw) || StringUtil.isNotEmpty(exception)) {
      if (StringUtil.isEmpty(thrw) || Objects.equals(thrw, Exception.class.getName())) {
        eventNode.exception = new Exception(exception);
      } else {
        try {
          eventNode.exception = (Throwable) Class.forName(thrw).getDeclaredConstructor(String.class).newInstance(exception);
        } catch (Throwable e) {
          e.printStackTrace();
          try {
            eventNode.exception = (Throwable) Class.forName(thrw).getDeclaredConstructor(String.class, Throwable.class).newInstance(exception, null);
          } catch (Throwable e2) {
            e2.printStackTrace();
            try {
              eventNode.exception = (Throwable) Class.forName(thrw).getDeclaredConstructor(Throwable.class).newInstance(new Exception(exception));
            } catch (Throwable e3) {
              e3.printStackTrace();
              try {
                eventNode.exception = (Throwable) Class.forName(thrw).newInstance();
              } catch (Throwable e4) {
                e4.printStackTrace();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                  eventNode.exception = new HttpException(exception, null);
                } else {
                  eventNode.exception = new ConnectException(exception);
                }
              }
            }
          }
        }
      }
    }

    eventNode.requestCode = obj.getIntValue("requestCode");
    eventNode.resultCode = obj.getIntValue("resultCode");

    Intent intent = parseIntent(obj.getJSONObject("intent"), eventNode.activity);
    eventNode.intent = intent;

    eventNode.windowX = obj.getIntValue("windowX");
    eventNode.windowY = obj.getIntValue("windowY");
    eventNode.decorX = obj.getDoubleValue("decorX");
    eventNode.decorY = obj.getDoubleValue("decorY");

    eventNode.item = event;
    eventNode.obj = obj;

    return eventNode;
  }

  public Intent parseIntent(Object obj_, String activity) {
    if (obj_ == null || obj_ instanceof Intent) {
      return (Intent) obj_;
    }

    JSONObject obj = obj_ instanceof JSONObject ? (JSONObject) obj_ : JSON.parseObject(obj_);

    String cls = obj.getString("class");
    if (StringUtil.isEmpty(cls)) {
      cls = activity;
    }

    String pkg = obj.getString("package");
    if (StringUtil.isEmpty(pkg)) {
      String[] keys = cls == null ? null : StringUtil.split(cls, ".");
      int len = keys == null ? 0 : keys.length;
      String[] pkgKeys = len < 2 ? null : Arrays.copyOf(keys, Math.max(2, Math.min(3, len - 2)));
      pkg = pkgKeys == null || pkgKeys.length < 2 ? getCurrentActivity().getPackageName() : StringUtil.get(pkgKeys, ".");
    }

    return parseIntent(
            obj.getString("action"),
            obj.getString("data"),
            obj.getString("type"),
            obj.get("extras"),
            pkg,
            StringUtil.isEmpty(cls) ? null : new ComponentName(pkg, cls),
            obj.getInteger("flags"),
            obj.getString("identifier"),
            obj.get("categories")
    );
  }

  public static Intent parseIntent(String action, String uri, String dataType, Object ext
          , String pkg, ComponentName componentName, Integer flags, String identifier, Object cats) {
//    if (itt == null) {
//      return null;
//    }
//
//    if (itt instanceof Intent) {
//      return (Intent) itt;
//    }

    Uri[] uris = new Uri[1];
    try {
      uris[0] = Uri.parse(uri);
    } catch (Throwable e) {
      e.printStackTrace();
    }

//    String s = itt instanceof String ? (String) itt : JSON.toJSONString(itt);
//      if (uris[0] == null) {
// com.alibaba.fastjson.JSONException: default constructor not found. class android.content.ComponentName
//    Intent intent = JSON.parseObject(s, Intent.class, new PropertyProcessable() {
//      @Override
//      public Type getType(String s) {
//        return "data".equals(s) ? Uri.class : null;
//      }
//
//      @Override
//      public void apply(String s, Object o) {
//        if ("data".equals(s)) {
////            Uri uri = new Uri(); // Uri.parse()
//          uris[0] = Uri.parse(o.toString());
//        }
//      }
//    });
////      }

//    if (intent == null) {
      Intent intent = new Intent();
//    }

    if (StringUtil.isNotEmpty(action)) {
      intent.setAction(action);
    }

    if (uris[0] != null) {
      if (StringUtil.isEmpty(intent.getType(), true)) {
        intent.setDataAndType(uris[0], dataType);
      } else {
        intent.setDataAndType(uris[0], intent.getType());
      }
    }

    Bundle extras = intent.getExtras();
    if (extras == null || extras.isEmpty()) {
      Bundle ex = parseBundle(ext);
      if (ex != null && ! ex.isEmpty()) {
        intent.putExtras(ex);
      }
    }

    if (StringUtil.isNotEmpty(pkg)) {
      intent.setPackage(pkg);
    }
    if (componentName != null) {
      intent.setComponent(componentName);
    }
    if (flags != null) {
      intent.setFlags(flags);
    }

    if (StringUtil.isNotEmpty(identifier)) {
      intent.setIdentifier(identifier);
    }

    JSONArray categories = cats != null ? null : (cats instanceof JSONArray ? (JSONArray) cats : JSON.parseArray(cats));
    if (categories != null) {
      for (int i = 0; i < categories.size(); i++) {
        String cat = StringUtil.trim(categories.getString(i));
        if (StringUtil.isEmpty(cat) || intent.hasCategory(cat)) {
          continue;
        }

        intent.addCategory(cat);
      }
    }

    return intent;
  }

  private static Bundle parseBundle(Object ext) {
    if (ext == null || ext instanceof Bundle) {
      return (Bundle) ext;
    }

    Bundle extras = new Bundle();
    ArrayMap<String, Object> arrMap = JSON.parseObject(JSON.toJSONString(ext), ArrayMap.class);

//    try {
//      Method putAll = BaseBundle.class.getDeclaredMethod("putAll", ArrayMap.class);
//      putAll.setAccessible(true);
//      putAll.invoke(extras, arrMap);
//    } catch (Throwable e) {
//      e.printStackTrace();
//
//      try {
//        Method putObject = null;
//        try {
//          putObject = BaseBundle.class.getDeclaredMethod("putObject", String.class, Object.class);
//          putObject.setAccessible(true);
//        } catch (Throwable e2) {
//          e2.printStackTrace();
//        }

        Set<Map.Entry<String, Object>> set = arrMap == null ? null : arrMap.entrySet();
        if (set != null) {
          for (Map.Entry<String, Object> entry : set) {
            String key = entry == null ? null : entry.getKey();
            Object value = entry == null ? null : entry.getValue();

//            try {
//              putObject.invoke(extras, key, value);
//            } catch (Throwable e2) {
//              e2.printStackTrace();
              if (value == null) {
                extras.putString(key, null);
              } else if (value instanceof Boolean) {
                extras.putBoolean(key, (Boolean) value);
              } else if (value instanceof Integer) {
                extras.putInt(key, (Integer) value);
              } else if (value instanceof Long) {
                extras.putLong(key, (Long) value);
              } else if (value instanceof Double) {
                extras.putDouble(key, (Double) value);
              } else if (value instanceof String) {
                extras.putString(key, (String) value);
              } else if (value instanceof boolean[]) {
                extras.putBooleanArray(key, (boolean[]) value);
              } else if (value instanceof int[]) {
                extras.putIntArray(key, (int[]) value);
              } else if (value instanceof long[]) {
                extras.putLongArray(key, (long[]) value);
              } else if (value instanceof double[]) {
                extras.putDoubleArray(key, (double[]) value);
              } else if (value instanceof String[]) {
                extras.putStringArray(key, (String[]) value);
              } else {
                extras.putString(key, value.toString());
//                throw new IllegalArgumentException("Unsupported type " + value.getClass());
              }
//            }
          }
        }
//      } catch (Throwable e2) {
//        e2.printStackTrace();
//      }
//    }

    return extras;
  }


  private double transSplitX(double sx, double cw, int ballGravity, double ratio) {
    if (Math.abs(sx) > cw) {
      sx = cw;
    }

    if (ballGravity == GRAVITY_TOP_RIGHT || ballGravity == GRAVITY_BOTTOM_RIGHT) {
      sx = sx < 0 ? sx : sx - cw;
    }
    else if (ballGravity == GRAVITY_TOP_LEFT || ballGravity == GRAVITY_BOTTOM_LEFT) {
      sx = sx < 0 ? sx + cw : sx;
    }

    Double ratioX = null;
    if (ballGravity == GRAVITY_RATIO || ballGravity == GRAVITY_RATIO_TOP || ballGravity == GRAVITY_RATIO_BOTTOM) {
      ratioX = sx/cw;
    }

    sx = ratioX == null ? sx*ratio : ratioX*(getCurrentDialog() == null ? windowWidth : dialogWidth);

    return sx;
  }
  private double transSplitY(double sy, double ch, int ballGravity, double ratio) {
    if (Math.abs(sy) > ch) {
      sy = ch;
    }

    if (ballGravity == GRAVITY_BOTTOM_LEFT || ballGravity == GRAVITY_BOTTOM_RIGHT) {
      sy = sy < 0 ? sy : sy - ch;
    }
    else if (ballGravity == GRAVITY_TOP_LEFT || ballGravity == GRAVITY_TOP_RIGHT) {
      sy = sy < 0 ? sy + ch : sy;
    }

    Double ratioY = null;
    if (ballGravity == GRAVITY_RATIO || ballGravity == GRAVITY_RATIO_LEFT || ballGravity == GRAVITY_RATIO_RIGHT) {
      ratioY = sy/ch;
    }

    sy = ratioY == null ? sy*ratio : ratioY*windowHeight;

    return sy;
  }

  private double getScale(double ww, double wh, int layoutType, double density) {
//    if (decorWidth <= 0) {
      if (windowWidth <= 0) {
        windowWidth = screenWidth;
      }
//      decorWidth = windowWidth;
//    }
//    if (decorHeight <= 0) {
      if (windowHeight <= 0) {
        windowHeight = screenHeight;
      }
//      decorHeight = windowHeight;
//    }

    double curWW = Math.min(windowWidth, windowHeight); //  - (isSeparatedStatus ? 0 : statusHeight)); // decorWidth, decorHeight - statusHeight - navigationHeight);
    double targetWw = Math.min(ww, wh);
    if (curWW == targetWw || layoutType == InputUtil.LAYOUT_TYPE_ABSOLUTE) {  // 同宽像素或绝对位置
      return 1.0f;
    }

    if (density > 0.1 && layoutType == InputUtil.LAYOUT_TYPE_DENSITY) {  // 默认，相对位置像素密度比
      return DENSITY/density;
    }

    if (layoutType == InputUtil.LAYOUT_TYPE_RATIO) {  // 相对位置宽度比
      return curWW/targetWw;
    }

    return 1.0f;
  }


  protected Map<Window.Callback, Map<View, JSONObject>> pageViewListMap = new LinkedHashMap<>();
  protected Map<View, JSONObject> viewPropertyListMap = new LinkedHashMap<>();
  public Map<View, JSONObject> getViewPropertyListMap() {
    return viewPropertyListMap;
  }

  public JSONObject allView2Properties(View view, Map<View, JSONObject> viewPropertyListMap) {
    if (view == null || view.getVisibility() != View.VISIBLE || view.getWidth() == 0 || view.getHeight() == 0) {
      return null;
    }

    JSONArray cl = null;
    if (view instanceof ViewGroup) {
      ViewGroup vg = (ViewGroup) view;
      int size = vg.getChildCount(); // vg instanceof RecyclerView || vg instanceof AbsListView ? Math.min(3, vg.getChildCount()) : vg.getChildCount();

      if (vg instanceof RecyclerView) {
        // RecyclerView.LayoutManager lm = ((RecyclerView) vg).getLayoutManager();
      } else if (vg instanceof AbsListView) {
        int first = ((AbsListView) vg).getFirstVisiblePosition();
        int last = ((AbsListView) vg).getLastVisiblePosition();
        size = last - first + 1;
      }
      // size = vg instanceof RecyclerView || vg instanceof AbsListView
      //         ? Math.min(10, vg.getChildCount()) : vg.getChildCount();
      if (size <= 0) {
        return null;
      }

      cl = new JSONArray(size);
      for (int i = 0; i < size; i++) {
        JSONObject ppty = allView2Properties(vg.getChildAt(i), viewPropertyListMap);
        // if (ppty == null || ppty.isEmpty()) {
        //   continue;
        // }
        cl.add(ppty); // null 有效
      }
    }

    JSONObject properties = ui2propertyArray(view, viewPropertyListMap);
    if (cl != null) { // && ! cl.isEmpty()) {
      if (properties == null) {
        properties = new JSONObject(true);
      }
      properties.put(KEY_CHILD_LIST, cl);
    }

    return properties;
  }

  private JSONObject ui2propertyArray(View view, Map<View, JSONObject> viewPropertyListMap) {
    if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
      return null;
    }
    int visibility = view.getVisibility();
    if (visibility != View.VISIBLE) { // visibility == View.GONE || (visibility != View.VISIBLE && visibility != View.INVISIBLE)) {
      return null;
    }

    File dty = directory != null && directory.exists() ? directory : parentDirectory;

    boolean isViewGroup = view instanceof ViewGroup;
    boolean isImage = isViewGroup == false && view instanceof ImageView;
    boolean isCheck = isViewGroup == false && view instanceof CheckBox;
    boolean isText = isViewGroup == false && isImage == false && view instanceof TextView;
    boolean canScroll = canScroll(view);

    JSONObject propertyMap = viewPropertyListMap == null ? null : viewPropertyListMap.get(view);
    boolean noCache = propertyMap == null;
    if (noCache) {
      propertyMap = new JSONObject(true);
    }

    int id = view.getId();

    ViewParent vp = view.getParent();
    float parentWidth = vp instanceof View ? ((View) vp).getWidth() : 0;
    float parentHeight = vp instanceof View ? ((View) vp).getHeight() : 0;

    ViewGroup.LayoutParams lp = view.getLayoutParams();
    int width = lp == null ? WRAP_CONTENT : lp.width; // view.getWidth();
    int height = lp == null ? WRAP_CONTENT : lp.height; // view.getHeight();

    if (noCache) {
      propertyMap.put(KEY_TYPE, view.getClass().getName());
      propertyMap.put(KEY_VIEW_ID, id);
      propertyMap.put(KEY_VIEW_ID_NAME, getResIdName(id));
    }

    boolean focusable = view.isFocusable() || view.isFocusableInTouchMode();
    if (focusable) {
      propertyMap.put(KEY_FOCUSABLE, focusable);
    }

    String visibilityStr = visibility == View.GONE ? KEY_GONE : (visibility == View.INVISIBLE ? KEY_HIDDEN : KEY_VISIBLE);
    if (visibility != View.VISIBLE) {
      propertyMap.put(KEY_VISIBILITY, visibilityStr);
    }

    if (view instanceof StyleGetter) {
      propertyMap.put(KEY_STYLE, ((StyleGetter) view).getStyleName());
    }

    Drawable bkgd = view.getBackground();
    if (bkgd != null && isOutput) {
      String name = view instanceof BackgroundGetter ? ((BackgroundGetter) view).getBackgroundResName() : null;
      propertyMap.put(KEY_BACKGROUND, StringUtil.isNotEmpty(name) ? name : trySaveImage(bkgd, dty.getAbsolutePath() + "/uiauto_background_" + id + "_time_" + System.currentTimeMillis() + ".png"));
    }

    float x = view.getX();
    if (x != 0) {
      propertyMap.put(KEY_X, pixel2Obj(x));
    }
    float tx = view.getTranslationX();
    if (tx != 0) {
      propertyMap.put(KEY_TX, pixel2Obj(tx));
    }

    float y = view.getY();
    if (y != 0) {
      propertyMap.put(KEY_Y, pixel2Obj(y));
    }
    float ty = view.getTranslationY();
    if (ty != 0) {
      propertyMap.put(KEY_TY, pixel2Obj(ty));
    }

    float z = view.getY();
    if (z != 0) {
      propertyMap.put(KEY_Z, pixel2Obj(z));
    }
    float tz = view.getTranslationZ();
    if (tz != 0) {
      propertyMap.put(KEY_TZ, pixel2Obj(tz));
    }

    if (width != 0) {
      propertyMap.put(KEY_WIDTH, width < 0 ? new JSONRequest("px", width).puts(KEY_REAL, view.getWidth()) : pixel2Obj(width, parentWidth));
    }
    if (height != 0) {
      propertyMap.put(KEY_HEIGHT, height < 0 ? new JSONRequest("px", height).puts(KEY_REAL, view.getHeight()) : pixel2Obj(height, parentHeight));
    }

    if (noCache && lp instanceof ViewGroup.MarginLayoutParams) {
      ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;

      if (mlp instanceof LinearLayoutCompat.LayoutParams) {
        float weight = ((LinearLayoutCompat.LayoutParams) mlp).weight;
        if (weight != 0) {
          propertyMap.put(KEY_WEIGHT, weight);
        }
      }

      if (mlp.leftMargin != 0) {
        propertyMap.put(KEY_MARGIN_LEFT, pixel2Obj(mlp.leftMargin, parentWidth));
      }
      if (mlp.rightMargin != 0) {
        propertyMap.put(KEY_MARGIN_RIGHT, pixel2Obj(mlp.rightMargin, parentWidth));
      }
      if (mlp.topMargin != 0) {
        propertyMap.put(KEY_MARGIN_TOP, pixel2Obj(mlp.topMargin, parentHeight));
      }
      if (mlp.bottomMargin != 0) {
        propertyMap.put(KEY_MARGIN_BOTTOM, pixel2Obj(mlp.bottomMargin, parentHeight));
      }
    }

    int pl = noCache ? view.getPaddingLeft() : 0;
    if (pl != 0) {
      propertyMap.put(KEY_PADDING_LEFT, pixel2Obj(pl, parentWidth));
    }
    int pr = noCache ? view.getPaddingRight() : 0;
    if (pr != 0) {
      propertyMap.put(KEY_PADDING_RIGHT, pixel2Obj(pr, parentWidth));
    }
    int pt = noCache ? view.getPaddingTop() : 0;
    if (pt != 0) {
      propertyMap.put(KEY_PADDING_TOP, pixel2Obj(pt, parentHeight));
    }
    int pb = noCache ? view.getPaddingBottom() : 0;
    if (pb != 0) {
      propertyMap.put(KEY_PADDING_BOTTOM, pixel2Obj(pb, parentHeight));
    }

    if (isText) {
      TextView tv = (TextView) view;

      if (noCache && tv.getGravity() != 0) {
        propertyMap.put(KEY_GRAVITY, tv.getGravity());
      }
      if (tv.getText() != null) {
        propertyMap.put(KEY_TEXT, tv.getText());
      }
      if (noCache && tv.getHint() != null) {
        propertyMap.put(KEY_HINT, tv.getHint());
      }

      if (noCache) {
        propertyMap.put(KEY_TEXT_SIZE, pixel2Obj(tv.getTextSize()));
      }

      if (tv.getCurrentTextColor() != 0) {
        propertyMap.put(KEY_TEXT_COLOR, color2Obj(tv.getCurrentTextColor()));
      }
      if (noCache && tv.getCurrentHintTextColor() != 0) {
        propertyMap.put(KEY_HINT_COLOR, color2Obj(tv.getCurrentHintTextColor()));
      }

      if (isCheck && ((CheckBox) view).isChecked()) {
        propertyMap.put(KEY_CHECK, true);
      }
    }
    else if (isImage) {
      Drawable img = isOutput ? ((ImageView) view).getDrawable() : null;
      if (img != null) {
        String name = view instanceof ImageGetter ? ((ImageGetter) view).getImageResName() : null;
        propertyMap.put(KEY_IMAGE, StringUtil.isNotEmpty(name) ? name : trySaveImage(img, dty.getAbsolutePath() + "/uiauto_image_" + id + "_time_" + System.currentTimeMillis() + ".png"));
      }
    }
    else if (noCache && view instanceof RelativeLayout) {
      if (((RelativeLayout) view).getGravity() != 0) {
        propertyMap.put(KEY_GRAVITY, ((RelativeLayout) view).getGravity());
      }
    }
    else if (noCache && (canScroll || view instanceof LinearLayout)) {
      boolean isLl = view instanceof LinearLayout;
      if (isLl && ((LinearLayout) view).getGravity() != 0) {
        propertyMap.put(KEY_GRAVITY, ((LinearLayout) view).getGravity());
      }

      boolean csh = isLl
              ? ((LinearLayout) view).getOrientation() == LinearLayout.HORIZONTAL
              : canScrollHorizontally(view);
      propertyMap.put(KEY_ORIENTATION, csh ? KEY_HORIZONTAL : KEY_VERTICAL);
    }

    if (viewPropertyListMap != null) {
      viewPropertyListMap.put(view, propertyMap);
    }
    return propertyMap;
  }

  private JSONObject pixel2Obj(float pixel) {
    return pixel2Obj(pixel, 0);
  }
  private JSONObject pixel2Obj(float pixel, float parentPixel) {
    if (pixel == 0) {
      return null;
    }

    JSONObject obj = new JSONObject(true);
    obj.put("px", pixel);
    obj.put("dp", px2dp(pixel));
    obj.put("sp", px2sp(pixel));
    if (parentPixel > 0) {
      obj.put("pp", pixel/parentPixel);
    }
    obj.put("wp", pixel/windowWidth);
    return obj;
  }

  private JSONObject color2Obj(int color) {
    if (color == 0) {
      return null;
    }

    JSONObject obj = new JSONObject(true);
    obj.put("value", color);
    try {
      obj.put("string", Color.valueOf(color).getColorSpace().getName());
    } catch (Throwable e) {
      e.printStackTrace();
    }
    obj.put("idName", getResIdName(color));

    return obj;
  }


  private static final String KEY_VISIBLE = "visible";
  private static final String KEY_HIDDEN = "hidden";
  private static final String KEY_GONE = "gone";

  private static final JSONArray COLOR_NAME_LIST;
  private static final Map<String, Integer> COLOR_NAME_VALUE_MAP;
  private static final Map<Integer, String> COLOR_VALUE_NAME_MAP;
  static {
    COLOR_NAME_VALUE_MAP = new LinkedHashMap<>();
    COLOR_NAME_VALUE_MAP.put("black", Color.BLACK);
    COLOR_NAME_VALUE_MAP.put("darkgray", Color.DKGRAY);
    COLOR_NAME_VALUE_MAP.put("gray", Color.GRAY);
    COLOR_NAME_VALUE_MAP.put("lightgray", Color.LTGRAY);
    COLOR_NAME_VALUE_MAP.put("white", Color.WHITE);
    COLOR_NAME_VALUE_MAP.put("red", Color.RED);
    COLOR_NAME_VALUE_MAP.put("green", Color.GREEN);
    COLOR_NAME_VALUE_MAP.put("blue", Color.BLUE);
    COLOR_NAME_VALUE_MAP.put("yellow", Color.YELLOW);
    COLOR_NAME_VALUE_MAP.put("orange", 0xFFFFA500);
    COLOR_NAME_VALUE_MAP.put("cyan", Color.CYAN);
    COLOR_NAME_VALUE_MAP.put("magenta", Color.MAGENTA);
    COLOR_NAME_VALUE_MAP.put("aqua", 0xFF00FFFF);
    COLOR_NAME_VALUE_MAP.put("fuchsia", 0xFFFF00FF);
    COLOR_NAME_VALUE_MAP.put("darkgrey", Color.DKGRAY);
    COLOR_NAME_VALUE_MAP.put("grey", Color.GRAY);
    COLOR_NAME_VALUE_MAP.put("lightgrey", Color.LTGRAY);
    COLOR_NAME_VALUE_MAP.put("lime", 0xFF00FF00);
    COLOR_NAME_VALUE_MAP.put("maroon", 0xFF800000);
    COLOR_NAME_VALUE_MAP.put("navy", 0xFF000080);
    COLOR_NAME_VALUE_MAP.put("olive", 0xFF808000);
    COLOR_NAME_VALUE_MAP.put("purple", 0xFF800080);
    COLOR_NAME_VALUE_MAP.put("silver", 0xFFC0C0C0);
    COLOR_NAME_VALUE_MAP.put("teal", 0xFF008080);

    COLOR_NAME_LIST = new JSONArray(new ArrayList<>(COLOR_NAME_VALUE_MAP.keySet()));

    COLOR_VALUE_NAME_MAP = new LinkedHashMap<>();
    for (int i = 0; i < COLOR_NAME_LIST.size(); i++) {
      String key = COLOR_NAME_LIST.getString(i);
      COLOR_VALUE_NAME_MAP.put(COLOR_NAME_VALUE_MAP.get(key), key);
    }
  }

  public static JSONArray GRAVITY_NAME_LIST;
  public static Map<String, Integer> GRAVITY_NAME_VALUE_MAP;
  public static Map<Integer, String> GRAVITY_VALUE_NAME_MAP;
  static {
    GRAVITY_NAME_VALUE_MAP = new LinkedHashMap<>();
    GRAVITY_NAME_VALUE_MAP.put("center", Gravity.CENTER);
    GRAVITY_NAME_VALUE_MAP.put("center_horizontal", Gravity.CENTER_HORIZONTAL);
    GRAVITY_NAME_VALUE_MAP.put("center_vertical", Gravity.CENTER_VERTICAL);
    GRAVITY_NAME_VALUE_MAP.put("left", Gravity.LEFT);
    GRAVITY_NAME_VALUE_MAP.put("top", Gravity.TOP);
    GRAVITY_NAME_VALUE_MAP.put("right", Gravity.RIGHT);
    GRAVITY_NAME_VALUE_MAP.put("bottom", Gravity.BOTTOM);
    GRAVITY_NAME_VALUE_MAP.put("left,top", Gravity.LEFT | Gravity.TOP);
    GRAVITY_NAME_VALUE_MAP.put("left,bottom", Gravity.LEFT | Gravity.BOTTOM);
    GRAVITY_NAME_VALUE_MAP.put("right,top", Gravity.RIGHT | Gravity.TOP);
    GRAVITY_NAME_VALUE_MAP.put("right,bottom", Gravity.RIGHT | Gravity.BOTTOM);
    GRAVITY_NAME_VALUE_MAP.put("left,center_vertical", Gravity.LEFT | Gravity.CENTER_VERTICAL);
    GRAVITY_NAME_VALUE_MAP.put("right,center_vertical", Gravity.RIGHT | Gravity.CENTER_VERTICAL);
    GRAVITY_NAME_VALUE_MAP.put("top,center_horizontal", Gravity.TOP | Gravity.CENTER_HORIZONTAL);
    GRAVITY_NAME_VALUE_MAP.put("bottom,center_horizontal", Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);

    GRAVITY_NAME_LIST = new JSONArray(new ArrayList<>(GRAVITY_NAME_VALUE_MAP.keySet()));
    GRAVITY_VALUE_NAME_MAP = new LinkedHashMap<>();
    for (int i = 0; i < GRAVITY_NAME_LIST.size(); i++) {
      String key = GRAVITY_NAME_LIST.getString(i);
      GRAVITY_VALUE_NAME_MAP.put(GRAVITY_NAME_VALUE_MAP.get(key), key);
    }
  }

  private String gravityInt2Str(Integer gravity) {
    return GRAVITY_VALUE_NAME_MAP.get(gravity);
  }
  private int gravityStr2Int(String gravity) {
    Integer g = GRAVITY_NAME_VALUE_MAP.get(gravity);
    return g == null ? 0 : g;
  }

  private JSONObject putUIGravity(JSONObject obj) {
    return putUIGravity(obj, null);
  }
  private JSONObject putUIGravity(JSONObject obj, int gravity) {
    return putUIGravity(obj, gravityInt2Str(gravity));
  }
  private JSONObject putUIGravity(JSONObject obj, String gravity) {
    if (obj == null) {
      obj = new JSONObject();
    }
    obj.put(KEY_KEY, KEY_GRAVITY);
    obj.put(KEY_NAME, "Gravity");
    obj.put(KEY_VALUE, gravity); // gravity == null ? "center" : gravity);
    // obj.put(KEY_VALUE_OPTIONS, GRAVITY_NAME_LIST);
    return obj;
  }

  /* 非触屏、非按键的 其它事件，例如 Activity.onResume, HTTP Response 等
   */
  public void onEventChange(int position, int type) {
//    onEventChange(position, type == InputUtil.EVENT_TYPE_TOUCH ? 0L : 500L);
    onEventChange(position, 0L);
  }
  public void onEventChange(int position, long delayMillis) {
    if (tagAdapter == null) {
      return;
    }

    // tagAdapter.notifyItemRangeChanged(position - 1, position + 1);
    tagAdapter.notifyDataSetChanged();
    if (position < 0 || position >= tagAdapter.getItemCount()) {
      Log.e(TAG, "onEventChange  position < 0 || position >= tagAdapter.getItemCount() >> return;");
      return;
    }

    rvControllerTag.postDelayed(new Runnable() {
      @Override
      public void run() {
//        rvControllerTag.smoothScrollToPosition(position);
        rvControllerTag.scrollToPosition(position);
      }
    }, delayMillis);
  }

  public void onUIAutoActivityResult(@NonNull Fragment fragment, int requestCode, int resultCode, Intent data, Boolean mock) {
    onUIAutoActivityResult(fragment.getActivity(), fragment, requestCode, resultCode, data, mock);
  }
  public void onUIAutoActivityResult(@NonNull Activity activity, int requestCode, int resultCode, Intent data, Boolean mock) {
    onUIAutoActivityResult(activity, null, requestCode, resultCode, data, mock);
  }
  public void onUIAutoActivityResult(@NonNull Activity activity, Fragment fragment, int requestCode, int resultCode, Intent intent, Boolean mock) {
    if (isSplitShowing == false) {
      Log.e(TAG, "onUIAutoActivityResult  isSplitShowing == false >> return null;");
      return;
    }

    if (fragment != null && isIgnoreFragment(fragment)) {
      if (activity == null) {
        return;
      }
      fragment = null;
    }

    if (activity == null && fragment != null) {
      activity = fragment.getActivity();
    }

    output(null, currentEventNode, activity);
    if (isReplay) {
      Node<InputEvent> curNode = firstUIWaitNode == null ? currentEventNode : firstUIWaitNode;
      boolean disable = curNode == null || curNode.disable;

      boolean isMatch = disable || (curNode.action == InputUtil.UI_ACTION_RESULT
              && (
              (StringUtil.isEmpty(curNode.activity) || Objects.equals(curNode.activity, activity == null ? null : activity.getClass().getName())
//                              || Objects.equals(curAct.getClass(), activity.getClass())
              )
//                && (Objects.equals(curNode.fragment, fragment == null ? null : fragment.getClass().getName()))
      ));

      if (disable == false && curNode.type == InputUtil.EVENT_TYPE_UI && curNode.action == InputUtil.UI_ACTION_RESULT) {
        String key = getWaitKey(curNode);
        List<Node<InputEvent>> list = waitMap.get(key);
        if (list != null && list.isEmpty() == false) {
          list.remove(0);
          isMatch = true;
        }
        if (list == null || list.isEmpty()) {
          waitMap.remove(key);
        }
      }

//      Activity curAct = getCurrentActivity();
      if (isReplayingInput() == false && waitMap.isEmpty() && isMatch) {
        firstUIWaitNode = null;

        Node<InputEvent> nextNode = currentEventNode != null ? currentEventNode.next : (curNode == null ? null : curNode.next);
        long duration = calcDuration(curNode, nextNode);

        Message msg = handler.obtainMessage();
        msg.obj = nextNode;
        handler.sendMessageDelayed(msg, duration);
      }
    }
    else {
      JSONObject obj = newEvent(callback, activity, fragment, getCurrentDialog(), null); // , popupWindow); // FIXME 记录最后的 PopupWindow
      obj.put("type", InputUtil.EVENT_TYPE_UI);
      obj.put("action", InputUtil.UI_ACTION_RESULT);
      obj.put("mock", mock);
//      obj.put("currentActivity", getCurrentActivity());
//      obj.put("timeout", STEP_TIMEOUT/2);
      obj.put("requestCode", requestCode);
      obj.put("resultCode", resultCode);
      obj.put("intent", parseJSONObject(intent));
      obj.put("url", activity == null ? null : activity.getClass().getName());

      addEvent(obj);
    }
  }

  public void onUIEvent(int action, Window.Callback callback, Activity activity) {
    onUIEvent(action, callback, activity, null);
  }
  public void onUIEvent(int action, Window.Callback callback, Activity activity, DialogInterface dialog) {
    onUIEvent(action, callback, activity, null, dialog);
  }
  public void onUIEvent(int action, Window.Callback callback, Fragment fragment) {
    onUIEvent(action, callback, fragment, null);
  }
  public void onUIEvent(int action, Window.Callback callback, Fragment fragment, DialogInterface dialog) {
    onUIEvent(action, callback, null, fragment, dialog);
  }
  public void onUIEvent(int action, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog) {
    onUIEvent(action, callback, activity, fragment, dialog, null, null);
  }

  private List<String> httpHostList;
  public List<String> getHttpHostList() {
    return httpHostList;
  }
  public void setHttpHostList(List<String> httpHostList) {
    this.httpHostList = httpHostList;
  }
  private List<String> webHostList = new ArrayList<>();
  public List<String> getWebHostList() {
    return webHostList;
  }
  public void setWebHostList(List<String> webHostList) {
    this.webHostList = webHostList;
  }

  public synchronized void onUIEvent(int action, Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, WebView webView, String url) {
    if (activity != null && activity.isFinishing() == false
            && activity.isDestroyed() == false && activity.getWindow() != null) {
      window = activity.getWindow();
    }

    if (isSplitShowing == false) {
      Log.e(TAG, "onUIEvent  isSplitShowing == false >> return null;");
      return;
    }

    if (fragment != null && isIgnoreFragment(fragment)) {
      if (activity == null) {
        return;
      }
      fragment = null;
    }

    if (activity == null && fragment != null) {
      activity = fragment.getActivity();
    }

    output(null, currentEventNode, activity);

    if (isReplay) {
      Node<InputEvent> curNode = firstUIWaitNode == null ? currentEventNode : firstUIWaitNode;
      boolean disable = curNode == null || curNode.disable;

      boolean isMatch = disable || (curNode.type == InputUtil.EVENT_TYPE_UI && curNode.action == action
              && (StringUtil.isEmpty(curNode.activity)
              || Objects.equals(curNode.activity, activity == null ? null : activity.getClass().getName())
//                && (Objects.equals(curNode.fragment, fragment == null ? null : fragment.getClass().getName()))
      ));

      if (disable == false && curNode.type == InputUtil.EVENT_TYPE_UI
              && curNode.action == InputUtil.UI_ACTION_CREATE && StringUtil.isEmpty(curNode.fragment)) {
        String key = getWaitKey(curNode);
        List<Node<InputEvent>> list = waitMap.get(key);
        if (list != null && list.isEmpty() == false) {
          list.remove(0);
          isMatch = true;
        }
        if (list == null || list.isEmpty()) {
          waitMap.remove(key);
        }
      }

      Node<InputEvent> nextNode = currentEventNode != null ? currentEventNode.next : (curNode == null ? null : curNode.next);
      if (isReplayingInput() == false && waitMap.isEmpty() && (isMatch && ensureCorrectActivity(activity, nextNode))) { // curNode))) {
        firstUIWaitNode = null;

        long duration = calcDuration(curNode, nextNode);

        Message msg = handler.obtainMessage();
        msg.obj = nextNode;
        handler.sendMessageDelayed(msg, duration);
      }
    }
    else {
      String format = webView == null ? null : "WEB";
      String host = StringUtil.getHost(url);
      if (StringUtil.isNotEmpty(host) && url != null && url.startsWith(host)) {
        url = url.substring(host.length());
      }

      int ind = url == null ? -1 : url.indexOf("?");
      String query = ind < 0 ? null : url.substring(ind + 1);
      url = ind < 0 ? url : url.substring(0, ind);

      JSONObject obj = newEvent(callback, activity, fragment, dialog, null); // , popupWindow); // FIXME 记录最后的 PopupWindow

      JSONObject lastItem = eventList == null || eventList.isEmpty() ? null : eventList.getJSONObject(eventList.size() - 1);
      if (lastItem != null) { // 避免重复，目前发现开源中国网页会重复 resume web page url
        if (Objects.equals(lastItem.getInteger("action"), action)
                && Objects.equals(lastItem.getString("url"), url)
                && Objects.equals(lastItem.getString("activity"), obj.getString("activity"))
                && Objects.equals(lastItem.getString("fragment"), obj.getString("fragment"))
                && Objects.equals(lastItem.getString("dialog"), obj.getString("dialog"))
        ) {
          return;
        }
      }

      obj.put("type", InputUtil.EVENT_TYPE_UI);
      obj.put("action", action);
      // 总是导致停止后续动作，尤其是返回键相关的事件  obj.put("disable", action != InputUtil.UI_ACTION_RESUME);
      obj.put("disable", action != InputUtil.UI_ACTION_RESUME || webView == null || StringUtil.isEmpty(url, true));
      obj.put("format", format);
      obj.put("name", getShowPath(url, host));
      obj.put("host", host);
      obj.put("url", url);
      obj.put("query", query);

      if (action == InputUtil.UI_ACTION_CREATE && activity != null && fragment == null && webView == null) {
        obj.put("disable", false);
//        obj.put("timeout", STEP_TIMEOUT/2);

        Intent intent = activity.getIntent();
        obj.put("intent", parseJSONObject(intent));
//        if (StringUtil.isEmpty(url)) {
          obj.put("url", activity.getClass().getName());
//        }
      }

      addEvent(obj);
    }
  }

  public String getShowPath(String url, String host) {
    return InputUtil.getShowPath(url, host, httpHostList, webHostList);
  }

  private JSONObject parseJSONObject(Intent intent) {
    if (intent == null) {
      return null;
    }

    JSONObject obj = new JSONObject();
    obj.put("flags", intent.getFlags());
    obj.put("action", intent.getAction());
    obj.put("type", intent.getType());

    Uri data = intent.getData();
    obj.put("data", data == null ? null : data.toString());

    obj.put("package", intent.getPackage());
    ComponentName cn = intent.getComponent();
    obj.put("class", cn == null ? null : cn.getClassName());

    Bundle extras = intent.getExtras();
    Set<String> set = extras == null ? null : extras.keySet();
    if (set != null) {
      JSONObject extObj = new JSONObject();
      for (String s : set) {
        extObj.put(s, extras.get(s));
      }
      obj.put("extras", extObj);
    }

    obj.put("identifier", intent.getIdentifier());
    obj.put("categories", intent.getCategories());

    return obj;
  }

//  public void onHTTPEvent(int action, String format, String url, String request, String response, Activity activity) {
//    onHTTPEvent(action, format, url, request, response, activity, null);
//  }
//  public void onHTTPEvent(int action, String format, String url, String request, String response, Fragment fragment) {
//    onHTTPEvent(action, format, url, request, response, null, fragment);
//  }

  public synchronized void onHTTPEvent(int action, String method, String format, String host, String url
          , String reqHeader, String request, Activity activity, Fragment fragment) {
    onHTTPEvent(action, method, format, host, url, reqHeader, request, null, null, null, null, activity, fragment, null, null);
  }

  private List<String> ignoreUrlList = null;
  public void setIgnoreUrlList(List<String> ignoreUrlList) {
    this.ignoreUrlList = ignoreUrlList;
  }
  private List<String> ignoreHostList = null;
  public void setIgnoreHostList(List<String> ignoreHostList) {
    this.ignoreHostList = ignoreHostList;
  }
  private List<String> ignorePathList = null;
  public void setIgnorePathList(List<String> ignorePathList) {
    this.ignorePathList = ignorePathList;
  }
  private List<String> ignoreSuffixList = Arrays.asList(
    ".png", ".jpg", ".jpeg", ".gif", ".webp",
    ".svg", ".mp3", ".mp4", ".js", ".css",
    ".zip", ".tar", ".gz", ".rar", ".7z", ".bz2"
  );
  public void setIgnoreSuffixList(List<String> ignoreSuffixList) {
    this.ignoreSuffixList = ignoreSuffixList;
  }

  private Map<String, HttpManager.OnHttpResponseListener> listenerMap = new HashMap<>();
  public synchronized void onHTTPEvent(int action, String method, String format, String host, String url
          , String reqHeader, String request, String status, String resHeader, String response, Throwable e
          , Activity activity, Fragment fragment, DialogInterface dialog, HttpManager.OnHttpResponseListener listener) {
    if (StringUtil.isEmpty(host)) {
      host = StringUtil.getHost(url);
    }
    if (StringUtil.isNotEmpty(host) && url != null && url.startsWith(host)) {
      url = url.substring(host.length());
    }

    int ind = url == null ? -1 : url.indexOf("?");
    String query = ind < 0 ? null : url.substring(ind + 1);
    url = ind < 0 ? url : url.substring(0, ind);

    if (isSplitShowing == false || StringUtil.isEmpty(url)) {
      Log.e(TAG, "onHTTPEvent  isSplitShowing == false || StringUtil.isEmpty(url) >> return;");
      return;
    }

    if (ignoreUrlList != null && isIgnoreApi(method, host, url)) {
      Log.d(TAG, "onHTTPEvent  ignoreUrlList != null && isIgnoreApi(" + host + ", " + host + ", " + url + ") >> return;");
      return;
    }

    if (activity == null && fragment != null) {
      activity = fragment.getActivity();
    }

    output(null, currentEventNode, activity);

    boolean isRes = action < 0 || action == InputUtil.HTTP_ACTION_RESPONSE;
    String listenKey = getWaitKey(InputUtil.EVENT_TYPE_HTTP, isRes ? action : -action, method, host, url);

    if (isReplay) {
      Node<InputEvent> curNode = lastHTTPWaitNode == null ? currentEventNode : lastHTTPWaitNode;

      if (isRes) {
        listenerMap.remove(listenKey);
      }

      boolean disable = curNode == null || curNode.disable;
      if (disable || /** ((activity == null || Objects.equals(curNode.activity, activity.getClass().getName()))
//                && (Objects.equals(curNode.fragment, fragment == null ? null : fragment.getClass().getName()))
              && */ (StringUtil.isNotEmpty(url, true) && ! waitMap.isEmpty()) // ) // 避免过多调用
      ) {
        String key = isRes ? listenKey : getWaitKey(InputUtil.EVENT_TYPE_HTTP, action, method, host, url);

        List<Node<InputEvent>> list = waitMap.get(key);
        if (list != null && list.isEmpty() == false) {
          list.remove(0);
        }
        if (list == null || list.isEmpty()) {
          waitMap.remove(key);
//          step = lastWaitStep;
        }

        // if (curNode != null // && curNode.type == InputUtil.EVENT_TYPE_HTTP && curNode.action == action
//        && (url != null && url.equals(curNode.url))
        if (disable || (isReplayingInput() == false && waitMap.isEmpty())) {
          lastHTTPWaitNode = null;

          InputEvent curItem = disable ? null : curNode.item;
          Node<InputEvent> nextNode = currentEventNode != null ? currentEventNode.next : (curNode == null ? null : curNode.next);
          long duration = calcDuration(curNode, nextNode);

          Message msg = handler.obtainMessage();
          msg.obj = curNode == null ? null : (curItem != null ? curNode : nextNode);
          handler.sendMessageDelayed(msg, duration);
        }
      }
    }
    else {
      JSONObject obj = newEvent(activity, fragment, dialog);
      obj.put("type", InputUtil.EVENT_TYPE_HTTP);
      obj.put("action", action);
      obj.put("disable", action >= 0 && action != InputUtil.HTTP_ACTION_RESPONSE);
      obj.put("format", format);
      obj.put("status", status);
      obj.put("method", method);
      obj.put("host", host);
      obj.put("url", url);
      obj.put("query", query);
      // obj.put("header", action >= 0 && action != InputUtil.HTTP_ACTION_RESPONSE ? reqHeader : resHeader);
      obj.put("reqHeader", reqHeader);
      obj.put("resHeader", resHeader);
      obj.put("request", request);
      obj.put("response", response);
      if (e != null) {
        obj.put("throw", e.getClass().getName());
        obj.put("exception", e.getMessage());
      }
      obj.put("name", "");

      if (listener != null) {
        listenerMap.put(listenKey, listener);
      }

      addEvent(obj);
    }
  }

  public boolean isIgnoreApi(String method, String host, String url) {
    if (StringUtil.isEmpty(url)) {
      return true;
    }
    if (ignoreUrlList != null && ignoreUrlList.contains(host + url)) {
      return true;
    }
    if (ignoreHostList != null && ignoreHostList.contains(host)) {
      return true;
    }
    if (ignorePathList != null && ignorePathList.contains(url)) {
      return true;
    }

    if (ignoreSuffixList != null) {
      for (String suffix : ignoreSuffixList) {
        if (url.endsWith(suffix)) {
          return true;
        }
      }
    }

    return false;
  }


  public static List<Object> getEventList(int limit, int offset) {
    return getEventList(null, limit, offset);
  }
  public static List<Object> getEventList(UIAutoApp app, int limit, int offset) {
        if (app == null) {
            app = getInstance();
        }

        JSONArray eventList = app.getEventList();
        int size = eventList == null ? 0 : eventList.size();
        if (size <= 0 || offset >= size) {
            return app.isRunning ? null : new JSONArray();
        }

        List<Object> list = eventList.subList(offset, Math.min(offset + limit, size));
        return list.isEmpty() ? null : list;
  }

  private final JSONArray outputList = new JSONArray();
  public JSONArray getOutputList() {
    return outputList;
  }
  public static List<Object> getOutputList(int limit, int offset) {
    return getOutputList(null, limit, offset);
  }
  public static List<Object> getOutputList(UIAutoApp app, int limit, int offset) {
    if (app == null) {
      app = getInstance();
    }

    JSONArray outputList = app.getOutputList();
    int size = outputList == null ? 0 : outputList.size();
    if (size <= 0 || offset >= size) {
      return app.isRunning ? null : new JSONArray();
    }

    List<Object> list = outputList.subList(offset, Math.min(offset + limit, size));
    return list.isEmpty() ? null : list;
  }

  protected ExecutorService executorService = Executors.newSingleThreadExecutor();
  public boolean isOutput = false;
  public void output(JSONObject out, Node<?> eventNode, Activity activity) {
    if (eventNode == null || isOutput == false) { // || isReplay == false) {
      return;
    }

    Window window = this.window != null ? this.window : (activity == null ? null : activity.getWindow());

//    executorService.execute(new Runnable() {
//      @Override
//      public void run() { //TODO 截屏等记录下来
        Node<?> node = eventNode;

        long now = System.currentTimeMillis();
        long id = -1 - eventList.size();
        Long inputId;
        Long toInputId;
        // if (eventNode.item == null) {  // 自动触发
        inputId = node.id;
        toInputId = node.prev == null || node.prev.disable ? null : node.prev.id;
        // }
        // else {  // 手动触发
        //   inputId = eventNode == null || (eventNode.prev) == null ? null : eventNode.prev.id;
        //   toInputId = eventNode == null ? null : eventNode.id;
        // }

        JSONObject obj = out != null ? out : new JSONObject(true);
        obj.put("id", id);
        obj.put("step", node.step);
        obj.put("inputId", inputId == 0 ? -1 - node.step : inputId);
        obj.put("toInputId", toInputId);
        obj.put("orientation", node.orientation);
        obj.put("time", now);  // TODO 如果有录屏，则不需要截屏，只需要记录时间点
        obj.put("activity", node.activity);
        obj.put("fragment", node.fragment);
        obj.put("dialog", node.dialog);
        if (node.disable == false) {
          int type = node.type;
          int action = node.action;
          if (type == InputUtil.EVENT_TYPE_HTTP) {
            obj.put("requestCode", node.requestCode);
            obj.put("resultCode", node.resultCode);
            obj.put("method", node.method);
            obj.put("format", node.format);
            obj.put("status", node.status);
            obj.put("host", node.host);
            obj.put("url", node.url);

            JSONObject item = node.obj;
            if (item != null) {
              obj.put("query", item.get("query"));
              obj.put("header", item.get("header"));
              obj.put("request", item.get("request"));
              obj.put("reqHeader", item.get("reqHeader"));
              obj.put("response", item.get("response"));
              obj.put("resHeader", item.get("resHeader"));
            }
          }
          else if (window != null && (action == MotionEvent.ACTION_DOWN && type == InputUtil.EVENT_TYPE_TOUCH)) {
            // 同步或用协程来上传图片
            long start = System.currentTimeMillis();
            obj.put("screenshot", screenshot(directory == null || directory.exists() == false ? parentDirectory : directory, window, inputId, toInputId, node.orientation));
            long end = System.currentTimeMillis();
            Log.d(TAG, "\n\noutput screenshot start = " + start + "; duration = " + (end - start) + "; end = " + start + "\n\n\n");

            start = System.currentTimeMillis();

            com.alibaba.fastjson.JSON properties = null;
            contentView = getCurrentContentView();
            synchronized (contentView) {
               properties = allView2Properties(contentView, viewPropertyListMap);
            }

            String str = JSON.toJSONString(properties);
            executorService.execute(new Runnable() {
              @Override
              public void run() {
                obj.put("viewTree", JSON.parse(str));
              }
            });

            end = System.currentTimeMillis();
            Log.d(TAG, "\n\noutput allView2Properties start = " + start + "; duration = " + (end - start) + "; end = " + start + "\n\n\n");
          }
          else {
            return;
            // obj = null; // obj.clear();
          }

        }

//        if (outputList == null) {
//          outputList = new JSONArray();
//        }
        synchronized (outputList) { // 居然出现 java.lang.ArrayIndexOutOfBoundsException: length=49; index=49
        	outputList.add(obj);
        }
//      }
//    });
  }

  /**屏幕截图
   * @return
   */
  public String screenshot(File directory, Window window, Long inputId, Long toInputId, int orientation) {
    View decorView = window == null ? null : window.getDecorView();
    if (decorView == null) {
      return null;
    }

    try {
      synchronized (decorView) { // 必须，且只能是 Window，用 Activity 或 decorView 都不行 解决某些界面会报错 cannot find container of decorView
        boolean isCache = decorView.isDrawingCacheEnabled();
        Bitmap bitmap = decorView.getDrawingCache(); // 截屏等记录下来
        if (bitmap == null) {
          decorView.setDrawingCacheEnabled(true);
          bitmap = decorView.getDrawingCache();

          if (bitmap == null) {
            decorView.buildDrawingCache(true);
            bitmap = decorView.getDrawingCache();
          }
        }

        int w = bitmap == null || bitmap.isRecycled() ? 0 : bitmap.getWidth();
        int h = w <= 1 ? 0 : bitmap.getHeight();
        if (h <= 1) {
          return null;
        }

//        synchronized (bitmap) {
//          Matrix matrix = new Matrix();
//          matrix.postRotate(w <= h ? 0 : -90);
//          bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
//        }
        String filePath = saveImage(bitmap, directory.getAbsolutePath() + "/uiauto_screenshot_" + System.currentTimeMillis() + ".jpg");

        if (isCache == false) {
          decorView.setDrawingCacheEnabled(isCache);
        }

        // 宽居然不是和高一样等比缩放，貌似没缩放
        // double scale = 720f/w;
        // int nw = 720;
        // int nh = Math.round(h*scale);
        // matrix.postScale(scale, scale);
        // bitmap = Bitmap.createBitmap(bitmap, 0, 0, nw, nh, matrix, false);

//        decorView.destroyDrawingCache();
//        decorView.setDrawingCacheEnabled(false);

        return filePath;
      }
    }
    catch (Throwable e) {
      Log.e(TAG, "screenshot 截屏异常：" + e.getMessage());
    }

    return null;
  }

  public static Bitmap drawableToBitmap(Drawable drawable) {
    Bitmap bitmap = null;

    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        bitmap = bitmapDrawable.getBitmap();
      if (bitmap != null) {
        return bitmap;
      }
    }

    if(drawable.getIntrinsicWidth() <= 1 || drawable.getIntrinsicHeight() <= 1) {
      return null;
//      bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
    } else {
      bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    }

    if (bitmap == null || bitmap.isRecycled()) {
      return null;
    }

    synchronized (bitmap) {
      Canvas canvas = new Canvas(bitmap);
      drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      drawable.draw(canvas);
    }

    return bitmap;
  }

  private String trySaveImage(Drawable drawable, String filePath) {
    try {
      return saveImage(drawable, filePath);
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  private String saveImage(Drawable drawable, String filePath) throws Exception {
//    executorService.execute(new Runnable() {
//      @Override
//      public void run() {
        try {
//          Bitmap bitmap = drawableToBitmap(drawable);
//          int w = bitmap == null || bitmap.isRecycled() ? 0 : bitmap.getWidth();
//          int h = w <= 1 ? 0 : bitmap.getHeight();
//          if (h <= 1) {
//            return null;
//          }
//
//          synchronized (bitmap) {
//            Matrix matrix = new Matrix();
//            matrix.postRotate(w <= h ? 0 : -90);
//            bitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, false);
//          }

           // FIXME 总是报错 bitmap is recycled, cannot use it
           //  return saveImage(bitmap, filePath);
        } catch (Throwable e) {
          e.printStackTrace();
//          throw new RuntimeException(e);
        }
//      }
//    });

    return filePath;
  }

//  private String saveImage(Bitmap bitmap, String fileName, String fileSuffix) throws Exception {
//    // 保存图片
//    File file = new File(fileName + fileSuffix); // File.createTempFile(fileName, fileSuffix);
//    String filePath = file.getAbsolutePath();
//    return saveImage(bitmap, filePath);
//  }

  private String saveImage(Bitmap bitmap, String filePath) throws Exception {
    int w = bitmap == null || bitmap.isRecycled() ? 0 : bitmap.getWidth();
    int h = w <= 1 ? 0 : bitmap.getHeight();
    if (h <= 1) {
      return null;
    }

    // 在异步线程中创建 Bitmap 副本，避免原 Bitmap 被回收
    // 创建 Bitmap 的副本
    Bitmap.Config cfg = bitmap.getConfig();
    Bitmap bitmapCopy = bitmap.copy(cfg == null ? Bitmap.Config.ALPHA_8 : cfg, false);
    if (bitmapCopy == null) {
      return null;
    }

//    synchronized (bitmap) {
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          FileOutputStream fos = null;
          try {
            File file = new File(filePath);
            if (file.exists()) {
              file.delete();
            }
            file.createNewFile();

            fos = new FileOutputStream(filePath);

            if (bitmapCopy == null || bitmapCopy.isRecycled()) {
              return;
            }

//            synchronized (bitmapCopy) {
                bitmapCopy.compress(filePath.endsWith(".png") ? Bitmap.CompressFormat.PNG : Bitmap.CompressFormat.JPEG, 100, fos);
//            }
          } catch (Throwable e) {
            Log.e(TAG, "保存图片异常：" + e.getMessage());
          } finally {
            if (bitmapCopy != null && bitmapCopy.isRecycled() == false) {
              try {
                  bitmapCopy.recycle();
              } catch (Throwable e) {
                e.printStackTrace();
              }
            }

            if (fos != null) {
              try {
                fos.flush();
              } catch (Throwable e) {
                e.printStackTrace();
              }
              try {
                fos.close();
              } catch (Throwable e) {
                e.printStackTrace();
              }
            }
          }
        }
      });
//    }

//    if (bitmap.isRecycled() == false) {
        try {
            bitmap.recycle();
        } catch (Throwable e) {
            e.printStackTrace();
        }
//    }

    return filePath; // filePath = directory.getName() + "/" + file.getName();  // 返回相对路径
  }


//  public JSONObject addInputEvent(@NotNull InputEvent ie, @NotNull Window.Callback callback, @NotNull Activity activity) {
//    return addInputEvent(ie, callback, activity, null);
//  }
//  public JSONObject addInputEvent(@NotNull InputEvent ie, @NotNull Window.Callback callback, @NotNull Fragment fragment) {
//    return addInputEvent(ie, callback, null, fragment);
//  }

  JSONObject lastKeyDownEventObj = null;
  JSONObject lastKeyUpEventObj = null;

  List<String> ignoreViewIdList = null;
  List<String> ignoreViewTypeList = null;
  List<String> ignoreViewParentIdList = null;
  List<String> ignoreViewParentTypeList = null;

  @SuppressLint("ResourceType")
  public JSONObject addInputEvent(@NotNull InputEvent ie, @NotNull Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    if (isSplitShowing == false || vSplitX == null || vSplitY == null || isReplay) {
      Log.e(TAG, "addInputEvent  isSplitShowing == false || vSplitX == null || vSplitY == null || isReplay >> return null;");
      return null;
    }

    if (fragment != null && isIgnoreFragment(fragment)) {
      if (activity == null) {
        return null;
      }
      fragment = null;
    }

    // 直接在上面判断会导致少录制触屏事件
    if (activity == null) {
      if (fragment != null) {
        activity = fragment.getActivity();
      }

      if (activity == null) {
        if (dialog instanceof Dialog) {
          activity = ((Dialog) dialog).getOwnerActivity();
//        } else if (dialog instanceof DialogFragment) {
//          activity = ((DialogFragment) dialog).getActivity();
//        } else if (dialog instanceof androidx.fragment.app.DialogFragment) {
//          activity = ((androidx.fragment.app.DialogFragment) dialog).getActivity();
        }
      }
    }

    JSONObject obj = newEvent(callback, activity, fragment, dialog, popupWindow);

    int type = 0;
    int action = 0;

    long eventTime = ie.getEventTime();
    if (eventTime <= 0) {
      eventTime = SystemClock.uptimeMillis(); // System.currentTimeMillis();
    }

    JSONObject lastItem = eventList == null || eventList.isEmpty() ? null : eventList.getJSONObject(eventList.size() - 1); // currentEventNode == null ? null : currentEventNode.item;
    boolean isOutput = false;

    if (ie instanceof KeyEvent) {
      KeyEvent event = (KeyEvent) ie;
      type = InputUtil.EVENT_TYPE_KEY;
      action = event.getAction();
      int keyCode = event.getKeyCode();

      obj.put("type", type);

      //虽然 KeyEvent 和 MotionEvent 都有，但都不在父类 InputEvent 中 <<<<<<<<<<<<<<<<<<
      obj.put("action", action);
      long downTime = event.getDownTime();
      if (downTime <= 0) {
        downTime = eventTime;
      }

      obj.put("downTime", downTime);
      obj.put("metaState", event.getMetaState());
      obj.put("source", event.getSource());
      obj.put("deviceId", event.getDeviceId());
      //虽然 KeyEvent 和 MotionEvent 都有，但都不在父类 InputEvent 中 >>>>>>>>>>>>>>>>>>

      obj.put("keyCode", keyCode);
      obj.put("scanCode", event.getScanCode());
      obj.put("repeatCount", event.getRepeatCount());
      //通过 keyCode 获取的            obj.put("number", event.getNumber());
      obj.put("flags", event.getFlags());
      //通过 mMetaState 获取的 obj.put("modifiers", event.getModifiers());
      //通过 mKeyCode 获取的 obj.put("displayLabel", event.getDisplayLabel());
      //通过 mMetaState 获取的 obj.put("unicodeChar", event.getUnicodeChar());
      if (ie instanceof EditTextEvent) {
        EditTextEvent mke = (EditTextEvent) ie;
        EditText et = mke.getTarget();
        boolean disable = mke.getWhen() != EditTextEvent.WHEN_ON;

        if (lastKeyDownEventObj != null && et != null && ! (disable || lastKeyDownEventObj.getBooleanValue("disable"))) {
          disable = ignoreViewIdList != null && ignoreViewIdList.contains(getResIdName(et));
          disable = disable || (ignoreViewTypeList != null && ignoreViewTypeList.contains(et.getClass().getName()));
          ViewParent vp = disable ? null : et.getParent();
          while (vp instanceof View) {
              disable = ignoreViewParentIdList != null && ignoreViewParentIdList.contains(getResIdName((View) vp));
              disable = disable || (ignoreViewParentTypeList != null && ignoreViewParentTypeList.contains(vp.getClass().getName()));
              vp = disable ? null : vp.getParent();
          }
        }

        // 解决 EditTextEvent 和普通 KeyEvent 都回放可能导致的重复输入
        if (lastKeyDownEventObj != null) {
          lastKeyDownEventObj.put("disable", ! disable);
        }
        if (lastKeyUpEventObj != null) {
          lastKeyUpEventObj.put("disable", lastKeyDownEventObj == null || lastKeyDownEventObj.getBooleanValue("disable"));
//          lastKeyUpEventObj = null;
//          lastKeyDownEventObj = null;
        }

//        if (mke.getWhen() != EditTextEvent.WHEN_ON) {
//          return null;
//        }

        obj.put("disable", disable);
        obj.put("edit", true);
        obj.put("target", mke.getTarget());
        obj.put("targetType", mke.getTargetType());
        obj.put("targetId", mke.getTargetId());
        obj.put("targetIdName", getResIdName(mke.getTargetId()));
        obj.put("targetWebId", mke.getTargetWebId());
        obj.put("when", mke.getWhen());
        obj.put("s", mke.getS());
        obj.put("text", mke.getText());
        obj.put("start", mke.getStart());
        obj.put("length", mke.getLength());
        obj.put("after", mke.getAfter());

        Node<InputEvent> prevNode = webView == null || currentEventNode == null || (mke.getX() != null && mke.getY() != null) ? null : currentEventNode.prev;
        InputEvent prevItem = prevNode == null ? null : prevNode.item;
        if (prevItem instanceof MotionEvent) {
          double ratio = 980f/webView.getWidth();

          int[] loc = new int[2];
          webView.getLocationOnScreen(loc);

          obj.put("x", ratio*((MotionEvent) prevItem).getX());
          obj.put("y", ratio*(((MotionEvent) prevItem).getY() - loc[1]));
        }
      }
      else { // 解决录制网页的一次返回按键等录到连续的返回键 DOWN, DOWN, UP, UP
        if (lastItem != null && Objects.equals(lastItem.getInteger("action"), action) && Objects.equals(lastItem.getIntValue("keyCode"), keyCode)) {
          return null;
        }

        if (event.isPrintingKey()) {
          if (action == KeyEvent.ACTION_DOWN) {
            lastKeyDownEventObj = obj;
            lastKeyUpEventObj = null;
          } else if (action == KeyEvent.ACTION_UP) {
            if (lastKeyDownEventObj == null) {
              // obj.put("disable", false);
              lastKeyUpEventObj = null;
            } else {
              obj.put("disable", lastKeyDownEventObj.get("disable"));
              lastKeyUpEventObj = obj;
              lastKeyDownEventObj = null;
            }
          } else { // not possible
            lastKeyDownEventObj = null;
            lastKeyUpEventObj = null;
          }
        }
      }

      isOutput = action == KeyEvent.ACTION_DOWN;
    }
    else if (ie instanceof MotionEvent) {
      isAutoMoveBall = true;

      lastKeyDownEventObj = null;
      lastKeyUpEventObj = null;
      MotionEvent event = (MotionEvent) ie;

      type = InputUtil.EVENT_TYPE_TOUCH;
      action = event.getAction();

      double x = event.getX();
      double y = event.getY();

      int childCount = 0;
      int childIndex = -1;
      ViewGroup parentView = null;

      int focusChildCount = 0;
      int focusChildIndex = -1;
      ViewGroup focusParentView = null;

      int parentChildCount = 0;
      int parentChildIndex = -1;
      ViewGroup grandParentView = null;

      boolean isDown = action == MotionEvent.ACTION_DOWN;
      if (isDown) { // || action == MotionEvent.ACTION_UP) {
        isTouching = true;
        View rv = contentView != null && callback instanceof DialogInterface ? contentView : decorView;

        View v = findViewByPoint(rv, null, x, y, FOCUS_ANY, true);
        obj.put("targetType", v == null ? null : v.getClass().getName());
        obj.put("targetId", v == null ? null : v.getId());
        obj.put("targetIdName", getResIdName(v));

        ViewParent p = v == null ? null : v.getParent();
        if (p instanceof ViewGroup) {
          childCount = ((ViewGroup) p).getChildCount();
          obj.put("childCount", childCount);

          childIndex = ((ViewGroup) p).indexOfChild(v);
          if (childIndex >= 0) {
            obj.put("childIndex", childIndex);
          }
        }

        while (p instanceof View && ((View) p).getId() <= 0) {
          p = p.getParent();
        }
        ViewGroup pv = parentView = p instanceof ViewGroup ? (ViewGroup) p : null;
        obj.put("parentType", pv == null ? null : pv.getClass().getName());
        obj.put("parentId", pv == null ? null : pv.getId());
        obj.put("parentIdName", getResIdName(pv));

        ViewParent gpv = pv == null ? null : pv.getParent();
        if (gpv instanceof ViewGroup) {
          grandParentView = (ViewGroup) gpv;

          parentChildCount = grandParentView.getChildCount();
          obj.put("parentChildCount", parentChildCount);

          parentChildIndex = grandParentView.indexOfChild(pv);
          if (parentChildIndex >= 0) {
            obj.put("parentChildIndex", parentChildIndex);
          }
        }

        View fv = findViewByPoint(rv, null, x, y, FOCUS_ABLE, true); // FOCUS_HAS 找不到
        obj.put("focusType", fv == null ? null : fv.getClass().getName());
        obj.put("focusId", fv == null ? null : fv.getId());
        obj.put("focusIdName", getResIdName(fv));

        ViewParent fp = fv == null ? null : fv.getParent();
        if (fp instanceof ViewGroup) {
          focusParentView = ((ViewGroup) fp);

          focusChildCount = focusParentView.getChildCount();
          obj.put("focusChildCount", focusChildCount);

          focusChildIndex = focusParentView.indexOfChild(fv);
          if (focusChildIndex >= 0) {
            obj.put("focusChildIndex", focusChildIndex);
          }
        }

        TextView tv = v instanceof TextView ? (TextView) v : (fv instanceof TextView ? (TextView) fv : null);
        String txt = StringUtil.get(tv);
        int len = txt == null ? 0 : txt.length();

        int[] loc = new int[2];
        if (len > 0 && tv != null) {
          tv.getLocationOnScreen(loc);
        }

        Integer textIndex = len <= 0 ? null : TextViewUtil.getTouchIndex(tv, (int) Math.round(x - loc[0]), (int) Math.round(y - loc[1]));
        boolean noTxt = textIndex == null || textIndex < 0 || textIndex >= len;

        String leftText = textIndex == null || textIndex <= 0 ? null : txt.substring(0, Math.min(textIndex, len));
        String rightText = noTxt ? null : txt.substring(textIndex);
        obj.put("textIndex", textIndex);
        obj.put("leftText", leftText);
        obj.put("rightText", rightText);
      }
      else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
        onViewReachParentBound(currentScrollableView);
        isTouching = false;
      }

      int pc = event.getPointerCount();
      obj.put("pointerCount", pc);

      Float x2 = pc < 2 ? null : event.getX(1);
      Float y2 = pc < 2 ? null : event.getY(1);
      if (pc > 2) {
        JSONArray pointerIds = new JSONArray(pc);
        JSONArray pointers = new JSONArray(pc);
        for (int i = 0; i < pc; i++) {
          int id = event.getPointerId(i);
          pointerIds.add(id);

          float size = event.getSize(i);
          float pressure = event.getPressure(i);

          JSONObject oi = new JSONObject()
                  .fluentPut("x", event.getX(i))
                  .fluentPut("y", event.getY(i));
                  // .fluentPut("rawX", event.getRawX(i))
                  // .fluentPut("rawY", event.getRawY(i))
          if (id != i) {
            oi.put("id", id);
          }
          if (Math.abs(size) >= 0.0001) {
            oi.put("size", size);
          }
          if (Math.abs(pressure - 1) >= 0.01) {
            oi.put("pressure", pressure);
          }
          pointers.add(oi);
        }

        obj.put("pointerIds", pointerIds);
        obj.put("pointers", pointers);
      }

      if (view != null && webView == null) { // PopupWindow 等小窗口不需要分割？
        obj.put("x", x);
        obj.put("y", y);
        obj.put("x2", x2);
        obj.put("y2", y2);
      }
      else { // FIXME 根据 ballGravity, ballGravity2 和 gravityX, gravityY 计算
        boolean isDialog = dialog != null;
        double rx = x - windowX - decorX;
        double ry = y - windowY - decorY - (isDialog || popupWindow != null && popupWindow.isShowing() ? 0 : statusHeight); // (isSeparatedStatus ? 0 : statusHeight);

//        if (callback instanceof DialogInterface) {
//          DialogInterface dialog = (DialogInterface) callback;
//          // TODO
//        }
//
//        View decorView = window.getDecorView();
//        double dx = decorView.getX();
//        double dy = decorView.getY();
//        double dw = decorView.getWidth();
//        double dh = decorView.getHeight();

        // 只在回放前一处处理逻辑
        isSplit2Showing = floatBall2 != null && floatBall2.isShowing();
//      double minX = (isSplit2Showing ? Math.min(floatBall.getX(), floatBall2.getX()) : floatBall.getX()) - splitRadius;
        double maxX = (isSplit2Showing ? Math.max(floatBall.getX(), floatBall2.getX()) : floatBall.getX()) + splitRadius;
//      double avgX = (minX + maxX)/2;
//      double minY = (isSplit2Showing ? Math.min(floatBall.getY(), floatBall2.getY()) : floatBall.getY()) - splitRadius;
        double maxY = (isSplit2Showing ? Math.max(floatBall.getY(), floatBall2.getY()) : floatBall.getY()) + splitRadius;
//      double avgY = (minY + maxY)/2;

        if (isDialog) {
          maxX -= dialogX;
          maxY = maxY - dialogY + (isSeparatedStatus ? statusHeight : 0);

          // 避免和从下往上的定位坐标冲突，反正弹窗外触屏唯一有效的作用就是隐藏弹窗，保证最终为负数即可
          if (rx < 0) {
            rx -= dialogWidth;
          }
          else if (rx > dialogWidth) {
            rx += dialogWidth;
          }

          if (ry < 0) {
            ry -= 2*dialogHeight;
          }
          else if (ry > dialogHeight) {
            ry += 2*dialogHeight;
          }
        }

        rx = rx < maxX ? rx : rx - (isDialog ? dialogWidth : windowWidth); // dw + dx); // Math.round(x - windowX - decorX - (x < avgX ? 0 : decorWidth)));
        ry = ry < maxY ? ry : ry - (isDialog ? dialogHeight : windowHeight); // + (isSeparatedStatus ? 0 : statusHeight)); // dh + dy + statusHeight + navigationHeight); // Math.round(y - windowY - decorY - (y < avgY ? 0 : decorHeight)));

        obj.put("x", rx);
        obj.put("y", ry);
        obj.put("x2", x2 == null ? null : rx + x2 - x);
        obj.put("y2", y2 == null ? null : ry + y2 - y);

        if (isDown && ((rx < 0 && canScrollHorizontally(parentView)) || (ry < 0 && canScrollVertically(parentView)))) {
          if (childIndex >= 0) {
            obj.put("childIndex", childIndex - childCount);
          }
          if (focusChildIndex >= 0) {
            obj.put("focusChildIndex", focusChildIndex - focusChildCount);
          }
          if (parentChildIndex >= 0) {
            obj.put("parentChildIndex", parentChildIndex - parentChildCount);
          }
        }
      }

      obj.put("rawX", event.getRawX());
      obj.put("rawY", event.getRawY());
      if (pc >= 2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        obj.put("rawX2", event.getRawX(1));
        obj.put("rawY2", event.getRawY(1));
      }

      // 导致录制不到最初的下拉刷新？
      if (lastItem != null) { // 避免重复，尤其是 ACTION_UP
        if (Objects.equals(lastItem.get("x"), obj.get("x")) && Objects.equals(lastItem.get("y"), obj.get("y"))
                && Objects.equals(lastItem.getInteger("action"), action)) {
          return null;
        }
      }

      obj.put("type", type);
      //虽然 KeyEvent 和 MotionEvent 都有，但都不在父类 InputEvent 中 <<<<<<<<<<<<<<<<<<
      obj.put("action", action);
      obj.put("downTime", event.getDownTime());
      obj.put("metaState", event.getMetaState());
      obj.put("source", event.getSource());
      obj.put("deviceId", event.getDeviceId());
      //虽然 KeyEvent 和 MotionEvent 都有，但都不在父类 InputEvent 中 >>>>>>>>>>>>>>>>>>

      obj.put("size", event.getSize());
      obj.put("pressure", event.getPressure());
      obj.put("xPrecision", event.getXPrecision());
      obj.put("yPrecision", event.getYPrecision());
//      obj.put("pointerCount", event.getPointerCount());
      obj.put("edgeFlags", event.getEdgeFlags());

      isOutput = isDown;
    }

    obj.put("eventTime", eventTime);

    if (this.isOutput) {
      if (isOutput) { // FIXME 太卡！干脆只在回放时导出，以第一次回放为准
        Node<InputEvent> node = currentEventNode = obj2EventNode(obj, currentEventNode, step);
        output(null, node, activity);
//       } else {
//         JSONObject out = null; // new JSONObject(true);
// //        out.put("inputId", obj.get("id"));
// //        out.put("toInputId", currentEventNode == null ? null : currentEventNode.id);
// //        out.put("orientation", obj.get("orientation"));
// //        out.put("time", System.currentTimeMillis());
//         outputList.add(out);
      }
    }

    return addEvent(obj, type != InputUtil.EVENT_TYPE_TOUCH || action != MotionEvent.ACTION_MOVE);
  }


  public Window getCurrentWindow() {
    if (window == null) {
      window = getCurrentActivity().getWindow();
    }
    return window;
  }

  public View getCurrentDecorView() {
    if (decorView == null) {
      decorView = getCurrentWindow().getDecorView();
    }
    return decorView;
  }
  public View getCurrentContentView() {
    if (contentView == null) {
      contentView = getCurrentDecorView().findViewById(android.R.id.content);
    }
    return contentView;
  }

  private boolean isAlignLeft(MotionEvent event) {
    return ! isAlignRight(event);
  }
  private boolean isAlignLeft(double x) {
    return ! isAlignRight(x);
  }

  private boolean isAlignRight(MotionEvent event) {
    return event != null && isAlignRight(event.getX());
  }
  private boolean isAlignRight(double x) {
    if (floatSplitX == null) {
      return isFloatBallShowing() ? floatBall.getX() != 0 && floatBall.getY() != 0 && x > floatBall.getX() + splitSize/2 : false;
    }
    return floatSplitX.getX() != 0 && x > floatSplitX.getX();
  }
  private boolean isFloatBallShowing() {
    return floatBall != null && floatBall.isShowing();
  }

  private boolean isAlignTop(MotionEvent event) {
    return ! isAlignBottom(event);
  }
  private boolean isAlignTop(double y) {
    return ! isAlignBottom(y);
  }

  private boolean isAlignBottom(MotionEvent event) {
    return event != null && isAlignBottom(event.getY());
  }
  private boolean isAlignBottom(double y) {
    if (floatSplitY == null) {
      return isFloatBallShowing() ? floatBall.getX() != 0 && floatBall.getY() != 0 && y > floatBall.getY() + splitSize/2 : false;
    }
    return floatSplitY != null && floatSplitY.getY() != 0 && y > floatSplitY.getY();
  }


  public <V extends View> V findView(@IdRes int id) {
    return getCurrentWindow().findViewById(id);
  }
  public <V extends View> V findView(String id) {
    String url = webUrl;
    if (StringUtil.isEmpty(url, true)) {
      url = webView == null ? null : webView.getUrl();
    }
    Map<String, EditText> map = editTextMap.get(url);
    EditText et = map == null ? null : map.get(id);

    return (V) et;
  }

  public <V extends View> V findViewByFocus(View view, Class<V> clazz) {
    if (view == null) {
      return null;
    }

    if (view instanceof ViewGroup) {
      ViewGroup vg = (ViewGroup) view;

      for (int i = vg.getChildCount() - 1; i >= 0; i--) {
        View v = findViewByFocus(vg.getChildAt(i), clazz);
        if (v != null) {
          return (V) v;
        }
      }
    }

    return view.hasFocus() && (clazz == null || clazz.isAssignableFrom(view.getClass())) ? (V) view : null;
  }

  public <V extends View> V findViewByPoint(View view, Class<V> clazz, double x, double y) {
    return findViewByPoint(view, clazz, x, y, false);
  }
  public <V extends View> V findViewByPoint(View view, Class<V> clazz, double x, double y, boolean onlyFocusable) {
    return findViewByPoint(view, clazz, x, y, onlyFocusable ? FOCUS_HAS : FOCUS_ANY, false);
  }

  private static final int FOCUS_ANY = 0;
  private static final int FOCUS_ABLE = 1;
  private static final int FOCUS_HAS = 2;

  private static final int CAN_SCROLL_ANY = 0;
  private static final int CAN_SCROLL_UNSPECIFIED = 1;
  private static final int CAN_SCROLL_VERTICALLY = 2;
  private static final int CAN_SCROLL_HORIZONTALLY = 3;


  public <V extends View> V findViewByPoint(View view, Class<V> clazz, double x, double y, int focus, boolean hasId) {
    return findViewByPoint(view, clazz, x, y, focus, hasId, CAN_SCROLL_ANY);
  }

  List<String> ignoreFindViewIdList = null;
  List<String> ignoreFindViewTypeList = null;
  List<String> ignoreFindViewGroupIdList = null;
  List<String> ignoreFindViewGroupTypeList = null;
  @SuppressLint("ResourceType")
  public <V extends View> V findViewByPoint(View view, Class<V> clazz, double x, double y, int focus, boolean hasId, int scrollable) {
//    if (view == null || x < view.getX() || x > view.getX() + view.getWidth()
//            || y < view.getY() || y > view.getY() + view.getHeight()) {
//      return null;
//    }

    int[] loc = view == null || view.getVisibility() != View.VISIBLE ? null : new int[2];
    if (loc == null) {
      return null;
    }

    String idName = getResIdName(view);
    if (ignoreFindViewIdList != null && ignoreFindViewIdList.contains(idName)) {
      return null;
    }

    String type = view.getClass().getName();
    if (ignoreFindViewTypeList != null && ignoreFindViewTypeList.contains(type)) {
      return null;
    }

    view.getLocationOnScreen(loc); // view.getLocationInWindow(loc);

    int w = view.getWidth();
    int h = view.getHeight();
    if (x < loc[0] || x > loc[0] + w
            || y < loc[1] || y > loc[1] + h) {
      return null;
    }

    if (view instanceof ViewGroup) {
      ViewGroup vg = (ViewGroup) view;

      for (int i = vg.getChildCount() - 1; i >= 0; i--) {
        View v = findViewByPoint(vg.getChildAt(i), clazz, x, y, focus, hasId, scrollable);
        if (v != null) {
          return (V) v;
        }
      }

      if (StringUtil.isNotEmpty(idName) && ! "content".equals(idName)) {
        if (w > 0.8 * windowWidth && h >= 0.5 * windowHeight) {
          return null;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P && ! view.isScreenReaderFocusable()) {
          return null;
        }
      }

      if (ignoreFindViewGroupIdList != null && ignoreFindViewGroupIdList.contains(idName)) {
        return null;
      }
      if (ignoreFindViewGroupTypeList != null && ignoreFindViewGroupTypeList.contains(type)) {
        return null;
      }
    }

    // FIXME view.getId() > 0  some resource types set top bit which turns the value negative
    return (hasId == false || view.getId() > 0) // (view.getId() != View.NO_ID && view.getId() != 0))
            && (focus == FOCUS_ANY || view.hasFocus() || (focus == FOCUS_ABLE && (view.isFocusable() || view.isFocusableInTouchMode())))
            && (clazz == null || clazz.isAssignableFrom(view.getClass()))
            && (scrollable == CAN_SCROLL_ANY || (scrollable == CAN_SCROLL_UNSPECIFIED && canScroll(view))
            || (scrollable == CAN_SCROLL_VERTICALLY && canScrollVertically(view))
            || (scrollable == CAN_SCROLL_HORIZONTALLY && canScrollHorizontally(view)))
            ? (V) view : null;
  }

  public <V extends View> NearestView<V> findNearestView(View view, Class<V> clazz, double x, double y, boolean onlyFocusable, int id, Integer childIndex, NearestView<V> nearestView) {
    if (view instanceof ViewGroup) {
      ViewGroup vg = (ViewGroup) view;

      for (int i = vg.getChildCount() - 1; i >= 0; i--) {
        NearestView<V> nv = findNearestView(vg.getChildAt(i), clazz, x, y, onlyFocusable, id, childIndex, nearestView);
        if (nv != null) {
          nearestView = nv;
        }
      }
    }

    if (nearestView != null && nearestView.distance <= 0) {
      return nearestView;
    }

    if (view != null && view.getVisibility() == View.VISIBLE
            && (id <= 0 || view.getId() == id) && (clazz == null || clazz.isAssignableFrom(view.getClass()))
            && (onlyFocusable == false || view.hasFocus() || view.isFocusable() || view.isFocusableInTouchMode())
    ) {
      ViewParent vp = childIndex == null ? null : view.getParent();
      if (vp instanceof ViewGroup) {
        ViewGroup vg = (ViewGroup) vp;
        int ind = vg.indexOfChild(view);
        if ((childIndex >= 0 && childIndex != ind) || (childIndex < 0 && childIndex + vg.getChildCount() != ind)) {
          return nearestView;
        }
      }


      int[] loc = new int[2];
      view.getLocationOnScreen(loc); // nearestView.getLocationInWindow(loc);

      int l = loc[0];
      int r = l + view.getWidth();
      int t = loc[1];
      int b = t + view.getHeight();

      double d = Math.sqrt(
              Math.pow(Math.min(Math.abs(x - l), Math.abs(x - r)), 2)
                      + Math.pow(Math.min(Math.abs(y - t), Math.abs(y - b)), 2)
      );

      int pl = view.getPaddingLeft();
      int pr = view.getPaddingRight();
      int pt = view.getPaddingTop();
      int pb = view.getPaddingBottom();

      if (x >= l && x <= r && y >= t && y <= b && (nearestView == null || nearestView.distance > 0 || nearestView.z < view.getZ())) {
        nearestView = new NearestView<>((V) view, 0, l, r, t, b, pl, pr, pt, pb);
      }
      else {
        if (nearestView == null || (nearestView.distance > d && nearestView.z <= view.getZ())) {
          nearestView = new NearestView<>((V) view, d, l, r, t, b, pl, pr, pt, pb);
        }
      }
    }

    return nearestView;
  }


  private boolean canScroll(View view) {
    return view != null && (view instanceof AdapterView || view instanceof RecyclerView || view instanceof ScrollView
            || view instanceof HorizontalScrollView || view instanceof NestedScrollView);
    // && (view.canScrollHorizontally(View.LAYOUT_DIRECTION_UNDEFINED) || view.canScrollHorizontally(View.LAYOUT_DIRECTION_RTL) || view.canScrollHorizontally(View.LAYOUT_DIRECTION_LTR)
    // || view.canScrollVertically(View.LAYOUT_DIRECTION_UNDEFINED) || view.canScrollVertically(View.LAYOUT_DIRECTION_INHERIT) || view.canScrollVertically(View.LAYOUT_DIRECTION_LOCALE));
  }
  private boolean canScrollHorizontally(View view) {
    if (view instanceof HorizontalScrollView) {
      return true;
    }

    if (view instanceof NestedScrollView) {
      return view.canScrollHorizontally(-1) || view.canScrollHorizontally(1);
    }

    if (view instanceof RecyclerView) {
      RecyclerView.LayoutManager lm = ((RecyclerView) view).getLayoutManager();
      return lm != null && lm.canScrollHorizontally();
    }

    return false;
    // && (view.canScrollHorizontally(View.LAYOUT_DIRECTION_RTL) || view.canScrollHorizontally(View.LAYOUT_DIRECTION_LTR));
  }
  private boolean canScrollVertically(View view) {
    if (view instanceof AdapterView || view instanceof ScrollView) {
      return true;
    }

    if (view instanceof NestedScrollView) {
      return view.canScrollVertically(-1) || view.canScrollVertically(1);
    }

    if (view instanceof RecyclerView) {
      RecyclerView.LayoutManager lm = ((RecyclerView) view).getLayoutManager();
      return lm != null && lm.canScrollVertically();
    }

    return false;
    // && (view.canScrollVertically(View.LAYOUT_DIRECTION_INHERIT) || view.canScrollVertically(View.LAYOUT_DIRECTION_LOCALE));
  }

  int count = 0;

  public synchronized JSONObject addEvent(JSONObject event) {
    return addEvent(event, true);
  }
  public synchronized JSONObject addEvent(JSONObject event, boolean refreshUI) {
    if (event == null || isReplay) {
      Log.e(TAG, "addEvent  event == null >> return null;");
      return event;
    }

    count ++;

    step ++;
    allStep ++;
    tvControllerCount.setText(step + "/" + allStep);

    updateTime();

    if (step <= 1 && event.get("intent") == null) {
        event.put("disable", false);
        Activity activity = getCurrentActivity();
        Intent intent = activity == null ? null : activity.getIntent();
        event.put("intent", parseJSONObject(intent));
//        if (StringUtil.isEmpty(url)) {
        event.put("url", activity == null ? null : activity.getClass().getName());
//        }
    }

    // if (eventList == null) {
    //     eventList = new JSONArray();
    // // }
    // if (step > 0 && step < allStep) {
    //     eventList.add(step - 1, event);
    // } else {
    eventList.add(event);
    // }

    if (currentEventNode != null) {
      Node<InputEvent> node = obj2EventNode(event, currentEventNode, step);
      currentEventNode = node.next = new Node<>(node, null, null);
    }

    if (refreshUI) {
      onEventChange(tagAdapter.getItemCount() - 1, event.getIntValue("type"));
    }

    return event;
  }

  public synchronized JSONArray removeEvent(JSONObject event) {
    if (event == null) {
      Log.e(TAG, "addEvent  event == null >> return null;");
      return null;
    }

    count --;

    step --;
    allStep --;
    tvControllerCount.setText(step + "/" + allStep);

    long curTime = System.currentTimeMillis();
    duration -= curTime - currentTime;
    currentTime = curTime;

    tvControllerTime.setText(TIME_FORMAT.format(new Date(duration)));

    // if (eventList == null) {
    //     eventList = new JSONArray();
    // }
    eventList.remove(event);

    return eventList;
  }

  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Activity activity) {
    return newEvent(callback, activity, null, null, null);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Activity activity, DialogInterface dialog) {
    return newEvent(callback, activity, null, dialog, null);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Activity activity, PopupWindow popupWindow) {
    return newEvent(callback, activity, null, null, popupWindow);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Fragment fragment) {
    return newEvent(callback, null, fragment, null, null);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Fragment fragment, DialogInterface dialog) {
    return newEvent(callback, null, fragment, dialog, null);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, @NotNull Fragment fragment, PopupWindow popupWindow) {
    return newEvent(callback, null, fragment, null, popupWindow);
  }
  public JSONObject newEvent(@NotNull Window.Callback callback, Activity activity, Fragment fragment, DialogInterface dialog, PopupWindow popupWindow) {
    if (activity == null && fragment != null) {
      activity = fragment.getActivity();
    }
    return newEvent(
            activity == null ? Configuration.ORIENTATION_PORTRAIT : activity.getResources().getConfiguration().orientation
            , callback
            , activity == null ? null : activity.getPackageName()
            , activity == null ? null : activity.getClass().getName()
            , fragment == null ? null : fragment.getClass().getName()
            , dialog == null ? null : dialog.getClass().getName()
            , popupWindow == null ? null : popupWindow.getClass().getName()
    );
  }

  private long lastId = 0;
  public JSONObject newEvent(int orientation, @NotNull Window.Callback callback, String pkg, String activity, String fragment, String dialog, String popupWindow) {
    decorX = decorView == null ? 0 : decorView.getX();
    decorY = decorView == null ? 0 : decorView.getY();
    decorWidth = decorView == null ? windowWidth : decorView.getWidth();
    decorHeight = decorView == null ? windowHeight : decorView.getHeight();

    splitX = Math.round(floatBall.getX() + splitRadius - windowWidth); // decorWidth); // - decorX
    splitY = Math.round(floatBall.getY() + splitRadius - windowHeight); //  + (isSeparatedStatus ? 0 : statusHeight)); // + navigationHeight); // decorHeight); // - decorY

    isSplit2Showing = floatBall2 != null && floatBall2.isShowing();
    splitX2 = isSplit2Showing ? Math.round(floatBall2.getX() + splitRadius) : 0; // decorWidth) : 0; //  - decorX - decorWidth) : 0;
    splitY2 = isSplit2Showing ? Math.round(floatBall2.getY() + splitRadius) : 0; // decorHeight) : 0; // - decorY - decorHeight) : 0;

    long time = System.currentTimeMillis();
    // if (lastId < time) {
    //   lastId = time;
    // } else {
      lastId --;
    // }

    JSONObject event = new JSONObject(true);
    event.put("id", -step); // -1 - eventList.size()); // lastId); //
    event.put("flowId", flowId);
    event.put("step", step); // Math.abs(lastId); // count);
    event.put("time", time);
    event.put("orientation", orientation);
    event.put("splitX", isSplit2Showing || Math.abs(splitX) < windowWidth/2d ? splitX : (splitX < 0 ? splitX + windowWidth : splitX - windowWidth));
    event.put("splitY", isSplit2Showing || Math.abs(splitY) < windowHeight/2d ? splitY : (splitY < 0 ? splitY + windowHeight : splitX - windowHeight));
    if (isSplit2Showing) {
      event.put("isSplit2Show", 1);
      event.put("splitX2", splitX2);
      event.put("splitY2", splitY2);
      event.put("gravityViewId", curFocusView == null ? null : curFocusView.getId());
      event.put("gravityViewIdName", getResIdName(curFocusView));
      event.put("gravityX", gravityX);
      event.put("gravityY", gravityY);
      event.put("ballGravity", ballGravity);
      event.put("ballGravity2", ballGravity2);
    }
    event.put("windowX", windowX);
    event.put("windowY", windowY);
    event.put("windowWidth", windowWidth);
    event.put("windowHeight", windowHeight); // - (isSeparatedStatus ? 0 : statusHeight));
    event.put("statusHeight", statusHeight);
    event.put("keyboardHeight", keyboardHeight);
    event.put("navigationHeight", navigationHeight);
    event.put("decorX", decorX);
    event.put("decorY", decorY);
    event.put("decorWidth", decorWidth);
    event.put("decorHeight", decorHeight);
    event.put("dialogX", dialogX);
    event.put("dialogY", dialogY);
    event.put("dialogWidth", dialogWidth);
    event.put("dialogHeight", dialogHeight);
    event.put("package", pkg);
    event.put("activity", activity);
    event.put("fragment", fragment);
    //    event.put("popupWindow", popupWindow);
    event.put("dialog", dialog);
    event.put("popupWindow", popupWindow);

    if (event.get("name") == null) {
      String name = StringUtil.isEmpty(fragment, true) ? activity : fragment;
      int ind = name == null ? -1 : name.lastIndexOf(".");
      event.put("name", ind < 0 ? name : name.substring(ind + 1));
    }

    return event;
  }

  public int getResId(String gravityViewIdName) {
    try {
      return getResources().getIdentifier(gravityViewIdName, "id", getPackageName());
    }
    catch (Throwable e) {
      e.printStackTrace();
    }
    return 0;
  }
  public String getResIdName(View v) {
    return v == null ? null : getResIdName(v.getId());
  }
  public String getResIdName(@IdRes int id) {
    try {
      return getResources().getResourceEntryName(id);
    }
    catch (Throwable e) {
      e.printStackTrace();
    }
    return null;
  }

  public void setEventList(JSONArray eventList) {
    setEventList(eventList, 0);
  }
  public void setEventList(JSONArray eventList, int step) {
    this.eventList = eventList == null ? new JSONArray() : eventList;
    onEventChange(step, 0L);
  }

  private File directory;
  public void prepareReplay(JSONArray eventList) {
    prepareReplay(eventList, null);
  }
  public void prepareReplay(JSONArray eventList, JSONObject flow) {
    prepareReplay(eventList, 0, false, false, flow);
  }
  public void prepareReplay(JSONArray eventList, int step, boolean start, boolean output) {
    prepareReplay(eventList, step, start, output, null);
  }
  JSONObject flow;
  public void prepareReplay(JSONArray eventList, int step, boolean start, boolean output, JSONObject flow) {
    setEventList(eventList, step);
    isShowing = true;
    isReplay = true;
    isOutput = output;
    this.step = step;
    this.lastId = 0;
    allStep = eventList == null ? 0 : eventList.size();
    duration = 0;
    flowId = - System.currentTimeMillis();
    this.flow = flow;

    tvControllerPlay.setText(R.string.replay);
    tvControllerCount.setText(step + "/" + allStep);
    tvControllerTime.setText("00:00");

    waitMap = new LinkedHashMap<>();

    new Thread(new Runnable() {
      @Override
      public void run() {
        prepareAndSendEvent(eventList, step);

        try {
          directory = new File(parentDirectory.getAbsolutePath() + "/flowId_" + Math.abs(flowId));
          if (directory.exists()) {
            if (! directory.isDirectory()) {
                directory.delete();
                directory.mkdir();
            }
          } else {
              directory.mkdir();
          }
        } catch (Throwable e) {
          e.printStackTrace();
          directory = parentDirectory;
        }

        try {
          JSONObject sharedPreferences = flow == null ? null : flow.getJSONObject("sharedPreferences");
          if (sharedPreferences != null && ! sharedPreferences.isEmpty()) {
            JSONObject map = JSON.parseObject(cache.getString(KEY_APP_CACHE_NAME_CONFIG_MAP, null));
            Set<Map.Entry<String, Object>> set = map == null ? null : map.entrySet();
            if (set != null) {
              JSONObject allMap = new JSONObject();

              for (Map.Entry<String, Object> entry : set) {
                try {
                  String name = entry.getKey();
                  Object val = entry.getValue();
                  Integer mode = val instanceof Number || val instanceof String ? Integer.parseInt(String.valueOf(val)) : null;
                  Set<String> longKeys = new LinkedHashSet<String>();
                  Set<String> floatKeys = new LinkedHashSet<String>();
                  Set<String> stringSetKeys = new LinkedHashSet<String>();
                  if (mode == null && val instanceof JSONObject) {
                    JSONObject obj = ((JSONObject) val);
                    mode = obj.getInteger(KEY_MODE);

                    JSONArray lks = obj.getJSONArray(KEY_LONG_KEYS);
                    if (lks != null && ! lks.isEmpty()) {
                      for (Object k : lks) {
                        String key = k == null ? null : String.valueOf(k);
                        if (key != null && ! longKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                          longKeys.add(key);
                        }
                      }
                    }

                    JSONArray fks = obj.getJSONArray(KEY_FLOAT_KEYS);
                    if (fks != null && ! fks.isEmpty()) {
                      for (Object k : fks) {
                        String key = k == null ? null : String.valueOf(k);
                        if (key != null && ! floatKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                          floatKeys.add(key);
                        }
                      }
                    }

                    JSONArray keys = obj.getJSONArray(KEY_STRING_SET_KEYS);
                    if (keys != null && ! keys.isEmpty()) {
                      for (Object k : keys) {
                        String key = k == null ? null : String.valueOf(k);
                        if (key != null && ! stringSetKeys.contains(key)) { // StringUtil.isNotEmpty(key) &&
                          stringSetKeys.add(key);
                        }
                      }
                    }
                  }

                  SharedPreferences spf = getApp().getSharedPreferences(name, mode != null ? mode : Context.MODE_PRIVATE);

                  allMap.put(name, spf == null ? null : spf.getAll());
                  spf.edit().clear().commit();
                  JSONObject cch = sharedPreferences.getJSONObject(name);
                  if (cch == null || cch.isEmpty()) {
                    cch = sharedPreferences.getJSONObject(JSONResponse.formatObjectKey(name));
                  }
                  putSharedPreferences(spf, cch, longKeys, floatKeys, stringSetKeys);
                } catch (Throwable e) {
                  e.printStackTrace();
                }
              }

              cache.edit()
                      .remove(KEY_APP_SHARED_PREFERENCES).putString(KEY_APP_SHARED_PREFERENCES, JSON.toJSONString(allMap))
                      .commit();
            }
          }
        } catch (Throwable e) {
          e.printStackTrace();
        }

        mainHandler.post(new Runnable() {
          @Override
          public void run() {
            showCover(true);

            if (start) {
              isSplitShowing = false;
              onClickPlay();
            }
          }
        });
      }
    }).start();
  }

  public void putSharedPreferences(SharedPreferences spf, Map<String, Object> map) {
    Set<Map.Entry<String, Object>> set = map == null ? null : map.entrySet();
    putSharedPreferences(spf, set);
  }
  public void putSharedPreferences(SharedPreferences spf, Map<String, Object> map, Set<String> longKeys, Set<String> floatKeys, Set<String> stringSetKeys) {
    Set<Map.Entry<String, Object>> set = map == null ? null : map.entrySet();
    putSharedPreferences(spf, set, longKeys, floatKeys, stringSetKeys);
  }
  public void putSharedPreferences(SharedPreferences spf, Set<Map.Entry<String, Object>> set) {
    putSharedPreferences(spf, set, null, null, null);
  }

  public static boolean IS_AUTO_TYPE = true;
  public void putSharedPreferences(SharedPreferences spf, Set<Map.Entry<String, Object>> set, Set<String> longKeys, Set<String> floatKeys, Set<String> stringSetKeys) {
    if (spf == null || set == null || set.isEmpty()) {
      return;
    }

    SharedPreferences.Editor editor = spf.edit();
    for (Map.Entry<String, Object> entry : set) {
      String key = entry.getKey();
      Object val = entry.getValue();
      editor.remove(key);
      if (val instanceof Boolean) {
        editor.putBoolean(key, (boolean) val);
      } else if (val instanceof Number && floatKeys != null && floatKeys.contains(key)) {
        editor.putFloat(key, ((Number) val).floatValue());
      } else if (val instanceof Long) {
        editor.putLong(key, (long) val);
      } else if (val instanceof Integer || val instanceof Short) {
        if ((longKeys != null && longKeys.contains(key)) || (IS_AUTO_TYPE && (key.endsWith("_id") || key.endsWith("_ID") || key.endsWith("Id")))) {
          editor.putLong(key, (long) val);
        } else {
          editor.putInt(key, (int) val);
        }
      } else if (val instanceof Number) {
        editor.putFloat(key, ((Number) val).floatValue());
      }
      // else if (val instanceof BigDecimal) {
      //   editor.putFloat(key, ((BigDecimal) val).floatValue());
      // }
      else if (val instanceof Collection<?> && (stringSetKeys != null && stringSetKeys.contains(key))) {
        Collection<?> coll = (Collection<?>) val;
        Set<String> sSet = new LinkedHashSet<>();
        for (Object obj : coll) {
          if (obj instanceof String) {
            sSet.add((String) obj);
          } else {
            sSet = null;
            break;
          }
        }

        if (sSet != null) {
          editor.putStringSet(key, sSet);
        } else {
          editor.putString(key, JSON.toJSONString(val));
        }
      }
      else {
        editor.putString(key, JSON.toJSONString(val));
      }
    }

    editor.commit();
  }

  public void prepareRecord() {
    prepareRecord(true, false);
  }
  public void prepareRecord(boolean clear, boolean start) {
    prepareRecord(clear, start, false);
  }
  public void prepareRecord(boolean clear, boolean start, boolean output) {
    isShowing = true;
    isRunning = true;
    isReplay = false;
    isOutput = output;

    Node<InputEvent> eventNode = new Node<>(null, null, null);
    if (clear) {
	    setEventList(null);
	    firstEventNode = currentEventNode = eventNode;
	    step = 0;
        lastId = 0;
	    allStep = 0;
	    duration = 0;
        currentTime = startTime = System.currentTimeMillis();
	    flowId = - currentTime;
        tvControllerTime.setText("00:00");
    }
    else {
      if (currentEventNode == null) {
        currentEventNode = firstEventNode;
      }
      eventNode.prev = currentEventNode;
      if (currentEventNode != null) {
        currentEventNode.next = eventNode;
      }
    }

    tvControllerPlay.setText(R.string.record);
    tvControllerCount.setText(step + "/" + allStep);

    showCover(true);
    toast(R.string.click_sharp_to_double_ball_click_dollar_to_go_settings);

    if (start) {
      isSplitShowing = false;
      onClickPlay();
    }
  }



  public void startUIAutoActivity() {
    Activity act = getCurrentActivity();
    startActivity(UIAutoActivity.createIntent(act != null ? act : getApp()));
  }
  public void startUIAutoListActivity(String cacheKey) {
    Activity act = getCurrentActivity();
    startActivity(UIAutoListActivity.createIntent(act != null ? act : getApp(), cacheKey, flowId));
  }
  //  @Override
  public void startActivity(Intent intent) {
    Activity activity = getCurrentActivity();
    if (activity != null) {
      activity.startActivity(intent);
    } else {
      getApp().startActivity(intent);
    }
  }
//  @Override
  public void startActivity(Intent intent, Bundle options) {
    Activity activity = getCurrentActivity();
    if (activity != null) {
      activity.startActivity(intent, options);
    } else {
      getApp().startActivity(intent, options);
    }
  }

  public boolean isProxy = false;
  private String proxyServer = "";
  public void setHttpProxy(boolean isProxy, String server) {
    this.isProxy = isProxy;
    this.proxyServer = server;
  }

  public boolean isProxyEnabled() {
    return isProxy && isShowing();
  }
  public String getProxyServer() {
    return proxyServer;
  }

  private String delegateId = "";
  public String getDelegateId() {
    return delegateId;
  }
  public UIAutoApp setDelegateId(String delegateId) {
    this.delegateId = delegateId;
    return this;
  }

  public String getHttpUrl(String url_) throws UnsupportedEncodingException {
    String url = StringUtil.getNoBlankString(url_);
    String proxyServer = isProxyEnabled() && ! isIgnoreApi(null, null, url) ? getProxyServer() : null;

    if (StringUtil.isNotEmpty(proxyServer, true)) {
      String delegateId = getDelegateId();
      url = proxyServer + "/delegate?" + (isShowing && isSplitShowing ? ("$_record=" + (isReplay ? -1 : 1) + "&") : "")
              + (StringUtil.isEmpty(delegateId, true) ? "" : "$_delegate_id=" + delegateId + '&')
              + "$_delegate_url=" + URLEncoder.encode(url, "UTF-8");
    }

    return url;
  }

  protected WebView webView;
  public void setCurrentWebView(WebView webView, @NotNull Activity activity, @Nullable Fragment fragment) {
    this.webView = webView;
    setCurrentView(view, callback, activity, fragment, null, null);
  }
  protected String webUrl;
  public void setCurrentWebUrl(String webUrl) {
    this.webUrl = webUrl;
  }

  public Map<String, Map<String, EditText>> editTextMap = new LinkedHashMap<>();
  public Map<String, Map<Integer, String>> editTextIdMap = new LinkedHashMap<>();
  public JSONObject addWebEditTextEvent(@NotNull Activity activity, @Nullable Fragment fragment, @NotNull WebView webView
          , @NotNull String id, int selectionStart, int selectionEnd, String text, Integer touchX, Integer touchY) {
    if (isShowing == false || isSplitShowing == false || isReplay) {
      return null;
    }

    text = StringUtil.getString(text);

    String url = webUrl;
    if (StringUtil.isEmpty(url, true)) {
      url = webView == null ? null : webView.getUrl();
    }
    Map<String, EditText> map = editTextMap.get(url);

    EditText et = map == null ? null : map.get(id);
    if (et == null) {
      et = new EditText(activity);
      et.setId(View.generateViewId());

      if (map == null) {
        map = new LinkedHashMap<>();
        editTextMap.put(url, map);
      }
      map.put(id, et);

      Map<Integer, String> idMap = editTextIdMap.get(url);
      if (idMap == null) {
        idMap = new LinkedHashMap<>();
        editTextIdMap.put(url, idMap);
      }
      idMap.put(et.getId(), id);
    }

    et.setText(text);
    et.setSelection(selectionStart, selectionEnd);

    InputEvent ie = new EditTextEvent(KeyEvent.ACTION_UP, 0, et, EditTextEvent.WHEN_ON
            , text, selectionStart, selectionEnd, text)
            .setTargetWebId(id).setX(touchX).setY(touchY);
    return addInputEvent(ie, activity.getWindow().getCallback(), activity, fragment, null, null);
  }


  public String readAssetsText(String fileName) {
    try {
      byte[] buffer;
      try (InputStream is = getAssets().open(fileName)) {
        int length = is.available();
        buffer = new byte[length];
        is.read(buffer);
      } // FIXME FileNotFound
      String result = new String(buffer, "utf8");
      return result;
    } catch (Throwable e) {
      e.printStackTrace();
      return null;
    }
  }

  public void initWeb(@NotNull Activity activity, @Nullable Fragment fragment, @NotNull WebView webView, String webUrl) {
    setCurrentWebView(webView, activity, fragment);
    this.webUrl = webUrl;
//    editTextMap = new LinkedHashMap<>();
    if (isReplay == false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        String script = "" +
                "    function generateRandom() {\n" +
                "        return Math.floor((1 + Math.random()) * 0x10000)\n" +
                "            .toString(16)\n" +
                "            .substring(1);\n" +
                "    }\n" +
                "    (function() {\n" +
                "        if (window._uiautoHttpIntercepted) {\n" +
                "            return;\n" +
                "        }\n" +
                "        window._uiautoHttpIntercepted = true;\n" +
                "        var reqId = 0;\n" +
                "        function onHTTPEvent(action, id, item) {\n" +
                "            try {\n" +
                "                interception.onHttpEvent(action, id, JSON.stringify(item));\n" +
                "            } catch (e) {\n" +
                "                console.log(e);\n" +
                "                try {\n" +
                "                    item.error = e.message + (item.error == null ? '' : '\\n' + item.error);\n" +
                "                    item.status = typeof e;\n" +
                "                    interception.onHttpEvent(action, id, JSON.stringify(item));\n" +
                "                } catch (e2) {\n" +
                "                    console.log(e2);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        \n" +
                "        var _XHR = window.XMLHttpRequest;\n" +
                "        window.XMLHttpRequest = function() {\n" +
                "            var xhr = new _XHR();\n" +
                "            reqId ++;\n" +
                "            var _id = reqId\n" +
                "            var _method = '';\n" +
                "            var _action = 2;\n" +
                "            var _format = '';\n" +
                "            var _contentType = '';\n" +
                "            var _url = '';\n" +
                "            var _requestHeaders = {};\n" +
                "            var _requestBody = null;\n" +
                "    \n" +
                "            var _open = xhr.open;\n" +
                "            xhr.open = function(method, url) {\n" +
                "                _method = method;\n" +
                "                _url = url;\n" +
                "                _action = ['', '', 'GET', 'POST', 'PUT', 'DELETE', 'HEAD', 'OPTIONS', 'TRACE'].indexOf(_method);\n" +
                "                return _open.apply(xhr, arguments);\n" +
                "            };\n" +
                "    \n" +
                "            var _setRequestHeader = xhr.setRequestHeader;\n" +
                "            xhr.setRequestHeader = function(name, value) {\n" +
                "                name = name || '';\n" +
                "                _requestHeaders[name] = value;\n" +
                "    \n" +
                "                if (name.toLowerCase() == 'content-type') {\n" +
                "                    _contentType = value;\n" +
                "                    _format = _contentType.indexOf('json') >= 0 ? 'JSON' : (_contentType.indexOf('form-data') >= 0\n" +
                "                        ? 'DATA' : _contentType.indexOf('form') >= 0 || ['POST', 'PUT', 'DELETE'].indexOf(_method) >= 0 ? 'FORM' : 'PARAM');\n" +
                "                }\n" +
                "    \n" +
                "                return _setRequestHeader.apply(xhr, arguments);\n" +
                "            };\n" +
                "    \n" +
                "            var _send = xhr.send;\n" +
                "            xhr.send = function(body) {\n" +
                "                _requestBody = body;\n" +
                "                var item = {\n" +
                "                    method: _method,\n" +
                "                    url: _url,\n" +
                "                    requestHeader: _requestHeaders,\n" +
                "                    request: body,\n" +
                "                    status: 0,\n" +
                "                    statusText: ''\n" +
                "                };\n" +
                "                onHttpEvent(_action, _id, JSON.stringify(item));\n" +
                "                return _send.apply(xhr, arguments);\n" +
                "            };\n" +
                "    \n" +
                "            xhr.addEventListener('loadend', function() {\n" +
                "                var responseHeaders = {};\n" +
                "                try {\n" +
                "                    var headerStr = xhr.getAllResponseHeaders();\n" +
                "                    headerStr.split('\\r\\n').forEach(function(line) {\n" +
                "                        var idx = line.indexOf(': ');\n" +
                "                        if (idx > 0) {\n" +
                "                            responseHeaders[line.substring(0, idx)] = line.substring(idx + 2);\n" +
                "                        }\n" +
                "                    });\n" +
                "                } catch (e) {\n" +
                "                    console.log(e);\n" +
                "                }\n" +
                "                var item = {\n" +
                "                    method: _method,\n" +
                "                    url: _url,\n" +
                "                    requestHeader: _requestHeaders,\n" +
                "                    request: _requestBody,\n" +
                "                    status: xhr.status,\n" +
                "                    statusText: xhr.statusText,\n" +
                "                    responseType: xhr.responseType,\n" +
                "                    response: (xhr.responseType === '' || xhr.responseType === 'text') ? xhr.responseText : ('[' + xhr.responseType + ']'),\n" +
                "                    responseHeader: responseHeaders\n" +
                "                };\n" +
                "                onHttpEvent(- _action, _id, JSON.stringify(item));\n" +
                "            });\n" +
                "    \n" +
                "            return xhr;\n" +
                "        };\n" +
                "    \n" +
                "        var _fetch = window.fetch;\n" +
                "        window.fetch = function(input, init) {\n" +
                "            var _id = generateRandom() + generateRandom();\n" +
                "            var url = (typeof input === 'string') ? input : (input.url || '');\n" +
                "            var method = (init && init.method) || (input && input.method) || 'GET';\n" +
                "            var headers = (init && init.headers) || (input && input.headers) || {};\n" +
                "    \n" +
                "            var action = ['', '', 'GET', 'POST', 'PUT', 'DELETE', 'HEAD', 'OPTIONS', 'TRACE'].indexOf(method);\n" +
                "            var contentType = (headers['content-type'] || headers['Content-Type'] || '').toLowerCase();\n" +
                "    \n" +
                "            var format = contentType.indexOf('json') >= 0 ? 'JSON' : (contentType.indexOf('form-data') >= 0\n" +
                "                ? 'DATA' : contentType.indexOf('form') >= 0 || ['POST', 'PUT', 'DELETE'].indexOf(method) >= 0 ? 'FORM' : 'PARAM');\n" +
                "    \n" +
                "            var body = (init && init.body) || null;\n" +
                "            var item = {\n" +
                "                method: method,\n" +
                "                format: format,\n" +
                "                url: url,\n" +
                "                requestHeader: headers,\n" +
                "                request: body,\n" +
                "                status: 0,\n" +
                "                statusText: ''\n" +
                "            };\n" +
                "            onHttpEvent(action, _id, JSON.stringify(item));\n" +
                "    \n" +
                "            return _fetch.apply(window, arguments).then(function(response) {\n" +
                "                var resHeaders = {};\n" +
                "                try {\n" +
                "                    response.headers.forEach(function(v, k) {\n" +
                "                        resHeaders[k || ''] = v;\n" +
                "                    });\n" +
                "                } catch (e) {\n" +
                "                    console.log(e);\n" +
                "                }\n" +
                "    \n" +
                "                item.responseHeader = resHeaders;\n" +
                "                response.clone().text().then(function(text) {\n" +
                "                    item.status = response.status;\n" +
                "                    item.statusText = response.statusText;\n" +
                "                    item.response = text;\n" +
                "                    onHttpEvent(- action, _id, JSON.stringify(item));\n" +
                "                }).catch(function(e) {\n" +
                "                    console.log(e);\n" +
                "                    item.statusText = 'error';\n" +
                "                    item.error = e.message;\n" +
                "                    onHttpEvent(- action, _id, JSON.stringify(item));\n" +
                "                });\n" +
                "    \n" +
                "                return response;\n" +
                "            });\n" +
                "        };\n" +
                "    })();\n" +
                "    var onTouchEventCallback = function(event) {\n" +
                "        var target = event.target;\n" +
                "        if (target == null || ['input', 'textarea'].indexOf(target.localName) < 0 || ['INPUT', 'TEXTAREA'].indexOf(target.tagName) < 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        var id = target.id;\n" +
                "        if (id == null || id.trim().length <= 0) {\n" +
                "            /* target.id = */ id = generateRandom();\n" +
                "            var map = document.uiautoEditTextMap || {};\n" +
                "            map[id] = target;\n" +
                "            document.uiautoEditTextMap = map;\n" +
                "        }\n" +
                "        var touches = event.touches;\n" +
                "        var touch = touches == null ? null : touches[0];\n" +
                "        interception.onTouchEvent(id, touch == null ? 0 : touch.pageX || 0, touch == null ? 0 : touch.pageY || 0);\n" +
                "    };\n" +
                "    document.addEventListener('touchstart', onTouchEventCallback);\n" +
                "    var onEditEventCallback = function(event) {\n" +
                "        var target = event.target;\n" +
                "        if (target == null || ['input', 'textarea'].indexOf(target.localName) < 0 || ['INPUT', 'TEXTAREA'].indexOf(target.tagName) < 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        var id = target.id;\n" +
                "        if (id == null || id.trim().length <= 0) {\n" +
                "            /* target.id = */ id = generateRandom();\n" +
                "            var map = document.uiautoEditTextMap || {};\n" +
                "            map[id] = target;\n" +
                "            document.uiautoEditTextMap = map;\n" +
                "        }\n" +
                "        var touches = event.touches;\n" +
                "        var touch = touches == null ? null : touches[0];\n" +
                "        interception.onEditEvent(id, target.selectionStart, target.selectionEnd, target.value\n" +
                "            , touch == null ? 0 : touch.pageX || 0, touch == null ? 0 : touch.pageY || 0);\n" +
                "    };\n" +
                "    document.addEventListener('input', onEditEventCallback);\n" +
                "    // document.addEventListener('change', onEditEventCallback);\n" +
                "    var onKeyEventCallback = function(event) {\n" +
                "        var target = event.target;\n" +
                "        if (target == null || ['input', 'textarea'].indexOf(target.localName) < 0 || ['INPUT', 'TEXTAREA'].indexOf(target.tagName) < 0) {\n" +
                "            return;\n" +
                "        }\n" +
                "        var id = target.id;\n" +
                "        if (id == null || id.trim().length <= 0) {\n" +
                "            /* target.id = */ id = generateRandom();\n" +
                "            var map = document.uiautoEditTextMap || {};\n" +
                "            map[id] = target;\n" +
                "            document.uiautoEditTextMap = map;\n" +
                "        }\n" +
                "        interception.onKeyEvent('', event.type == 'keyup' ? 1 : 0, event.key, event.code || event.keyCode);\n" +
                "    };\n" +
                "    document.addEventListener('keydown', onKeyEventCallback);\n" +
                "    document.addEventListener('keyup', onKeyEventCallback);\n" +
                "    var ret = 'document.uiautoEditTextMap = ' + JSON.stringify(document.uiautoEditTextMap);\n" +
                "    ret";
      webView.evaluateJavascript(script, new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String value) {
          unitauto.Log.d(TAG, "wvWebView.evaluateJavascript value = " + value);
        }
      });
    }
  }


  public static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    JSONObject obj;

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


    public Node(Node<E> prev, E element, Node<E> next) {
      this.item = element;
      this.next = next;
      this.prev = prev;
    }

//    public Node(int ballGravity, double splitX, double splitY) {
//      this.ballGravity = ballGravity;
//      this.splitX = splitX;
//      this.splitY = splitY;
//    }
//
//    public Node(int ballGravity, double splitX, double splitY, int ballGravity2, double splitX2, double splitY2) {
//      this(ballGravity, splitX, splitY);
//      this.ballGravity2 = ballGravity2;
//      this.splitX2 = splitX2;
//      this.splitY2 = splitY2;
//    }

  }

  public static class BallPoint {
    int gravity;
    double x;
    double y;

    public BallPoint() {
    }

    public BallPoint(int gravity, double x, double y) {
      this.gravity = gravity;
      this.x = x;
      this.y = y;
    }

    public int getGravity() {
      return gravity;
    }
    public void setGravity(int gravity) {
      this.gravity = gravity;
    }

    public double getX() {
      return x;
    }
    public void setX(double x) {
      this.x = x;
    }

    public double getY() {
      return y;
    }
    public void setY(double y) {
      this.y = y;
    }
  }

  public static class NearestView<V extends View> {
    V view;
    float z;
    int left, right, top, bottom;
    int paddingLeft, paddingRight, paddingTop, paddingBottom;
    double distance;

    public NearestView(V view) {
      this(view, 0);
    }

    public NearestView(V view, double distance) {
      this(view, distance, 0, 0, 0, 0, 0, 0, 0, 0);
    }
    public NearestView(V view, double distance, int left, int right, int top, int bottom
            , int paddingLeft, int paddingRight, int paddingTop, int paddingBottom) {
      this.view = view;
      this.distance = distance;
      this.z = view.getZ();
      this.left = left;
      this.right = right;
      this.top = top;
      this.bottom = bottom;
    }

  }

}
