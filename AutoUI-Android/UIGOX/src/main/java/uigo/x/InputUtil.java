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

import static uigo.x.Constant.*;
import android.content.res.Configuration;

import apijson.JSON;
import apijson.Log;

import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import apijson.JSONResponse;

public class InputUtil {
  public static final String TAG = "InputUtil";

  public static final int EVENT_TYPE_TOUCH = 0;
  public static final int EVENT_TYPE_KEY = 1;
  public static final int EVENT_TYPE_UI = 2;
  public static final int EVENT_TYPE_HTTP = 3;

  public static final int LAYOUT_TYPE_DENSITY = 0;
  public static final int LAYOUT_TYPE_RATIO = 1;
  public static final int LAYOUT_TYPE_ABSOLUTE = 2;


  public static String getTouchActionName(int action) {
        String s = StringUtil.getTrimedString(MotionEvent.actionToString(action));
        return s.startsWith("ACTION_") ? s.substring("ACTION_".length()) : s;
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                return "DOWN";
//            case MotionEvent.ACTION_MOVE:
//                return "MOVE";
//            case MotionEvent.ACTION_SCROLL:
//                return "SCROLL";
//            case MotionEvent.ACTION_UP:
//                return "UP";
//            case MotionEvent.ACTION_MASK:
//                return "MASK";
//            case MotionEvent.ACTION_OUTSIDE:
//                return "OUTSIDE";
//            default:
//                return "CANCEL";
//        }
    }

    public static String getOrientationName(int orientation) {
        return orientation == Configuration.ORIENTATION_LANDSCAPE ? "HORIZONTAL" : "VERTICAL";
    }

    public static String getKeyActionName(int keyCode) {
        return getTouchActionName(keyCode);
    }
    public static String getKeyCodeName(int keyCode) {
        String s = StringUtil.getTrimedString(KeyEvent.keyCodeToString(keyCode));
        return s.startsWith("KEYCODE_") ? s.substring("KEYCODE_".length()) : s;
    }

    public static String getScanCodeName(int scanCode) {
        return "" + scanCode;  //它是 hardware key id  KeyEvent.keyCodeToString(scanCode);
    }


    public static final int HTTP_ACTION_REQUEST = 0;
    public static final int HTTP_ACTION_RESPONSE = 1;
    public static final int HTTP_ACTION_GET = 2;
    public static final int HTTP_ACTION_POST = 3;
    public static final int HTTP_ACTION_PUT = 4;
    public static final int HTTP_ACTION_DELETE = 5;
    public static final int HTTP_ACTION_HEAD = 6;
    public static final int HTTP_ACTION_OPTION = 7;
    public static final int HTTP_ACTION_TRACE = 8;
    public static final String HTTP_ACTION_REQUEST_NAME = "REQUEST";
    public static final String HTTP_ACTION_RESPONSE_NAME = "RESPONSE";
    public static final String HTTP_ACTION_GET_NAME = "GET";
    public static final String HTTP_ACTION_POST_NAME = "POST";
    public static final String HTTP_ACTION_PUT_NAME = "PUT";
    public static final String HTTP_ACTION_DELETE_NAME = "DELETE";
    public static final String HTTP_ACTION_HEAD_NAME = "HEAD";
    public static final String HTTP_ACTION_OPTION_NAME = "OPTION";
    public static final String HTTP_ACTION_TRACE_NAME = "TRACE";

    public static final String HTTP_HEADER_NAME = "HEADER";
    public static final String HTTP_CONTENT_NAME = "CONTENT";

    public static final String[] HTTP_ACTION_NAMES = new String[] {
            HTTP_ACTION_REQUEST_NAME, HTTP_ACTION_RESPONSE_NAME, HTTP_ACTION_GET_NAME, HTTP_ACTION_POST_NAME
            , HTTP_ACTION_PUT_NAME, HTTP_ACTION_DELETE_NAME, HTTP_ACTION_HEAD_NAME, HTTP_ACTION_OPTION_NAME
            , HTTP_ACTION_TRACE_NAME
    };
    public static final List<String> HTTP_ACTION_NAME_LIST = Arrays.asList(HTTP_ACTION_NAMES);

    public static int getHTTPActionCode(String action) {
        return HTTP_ACTION_NAME_LIST.indexOf(action);
    }
    public static String getHTTPActionName(int action) {
        action = Math.abs(action);
        return action >= HTTP_ACTION_NAME_LIST.size() ? "" : HTTP_ACTION_NAME_LIST.get(action);
    }



    public static final int UI_ACTION_ATTACH = 0;
    public static final int UI_ACTION_CREATE = 1;
    public static final int UI_ACTION_CREATE_VIEW = 2;
    public static final int UI_ACTION_ACTIVITY_CREATED = 3;
    public static final int UI_ACTION_START = 4;
    public static final int UI_ACTION_RESUME = 5;
    public static final int UI_ACTION_PAUSE = 6;
    public static final int UI_ACTION_STOP = 7;
    public static final int UI_ACTION_DESTROY_VIEW = 8;
    public static final int UI_ACTION_DESTROY = 9;
    public static final int UI_ACTION_DETACH = 10;
    public static final int UI_ACTION_RESTART = 11;
    public static final int UI_ACTION_PREATTACH = 12;
    public static final int UI_ACTION_PRECREATE = 13;
    public static final int UI_ACTION_RESULT = 14;

    public static final String UI_ACTION_ATTACH_NAME = "ATTACH";
    public static final String UI_ACTION_CREATE_NAME = "CREATE";
    public static final String UI_ACTION_CREATE_VIEW_NAME = "CREATE_VIEW";
    public static final String UI_ACTION_ACTIVITY_CREATED_NAME = "ACTIVITY_CREATED";
    public static final String UI_ACTION_START_NAME = "START";
    public static final String UI_ACTION_RESUME_NAME = "RESUME";
    public static final String UI_ACTION_PAUSE_NAME = "PAUSE";
    public static final String UI_ACTION_STOP_NAME = "STOP";
    public static final String UI_ACTION_DESTROY_VIEW_NAME = "DESTROY_VIEW";
    public static final String UI_ACTION_DESTROY_NAME = "DESTROY";
    public static final String UI_ACTION_DETACH_NAME = "DETACH";
    public static final String UI_ACTION_RESTART_NAME = "RESTART";
    public static final String UI_ACTION_PREATTACH_NAME = "PREATTACH";
    public static final String UI_ACTION_PRECREATE_NAME = "PRECREATE";
    public static final String UI_ACTION_RESULT_NAME = "RESULT";

    public static final String[] UI_ACTION_NAMES = new String[] {
            UI_ACTION_ATTACH_NAME, UI_ACTION_CREATE_NAME, UI_ACTION_CREATE_VIEW_NAME, UI_ACTION_ACTIVITY_CREATED_NAME
            , UI_ACTION_START_NAME, UI_ACTION_RESUME_NAME, UI_ACTION_PAUSE_NAME, UI_ACTION_STOP_NAME
            , UI_ACTION_DESTROY_VIEW_NAME, UI_ACTION_DESTROY_NAME, UI_ACTION_DETACH_NAME, UI_ACTION_RESTART_NAME
            , UI_ACTION_PREATTACH_NAME, UI_ACTION_PRECREATE_NAME, UI_ACTION_RESULT_NAME
    };
    public static final List<String> UI_ACTION_NAME_LIST = Arrays.asList(UI_ACTION_NAMES);

    public static int getUIActionCode(String action) {
        return UI_ACTION_NAME_LIST.indexOf(action);
    }
    public static String getUIActionName(int action) {
        return action < 0 || action >= UI_ACTION_NAME_LIST.size() ? "" : UI_ACTION_NAME_LIST.get(action);
    }


    public static String getActionName(int type, int action) {
        switch (type) {
            case EVENT_TYPE_KEY:
                return getKeyActionName(action);
            case EVENT_TYPE_UI:
                return getUIActionName(action);
            case EVENT_TYPE_HTTP:
                return getHTTPActionName(action);
            default:
                return getTouchActionName(action);
        }
    }


    public static final int GRAVITY_DEFAULT = 0; // left|top
    public static final int GRAVITY_RATIO = 0; // ratio

    public static final int GRAVITY_CENTER = 3; // center
    public static final int GRAVITY_LEFT = 1; // left
    public static final int GRAVITY_RIGHT = 2; // right
    public static final int GRAVITY_TOP = 1; // top
    public static final int GRAVITY_BOTTOM = 2; // bottom

    public static final int GRAVITY_TOP_LEFT = 1; // top|left
    public static final int GRAVITY_TOP_RIGHT = 2; // top|right
    public static final int GRAVITY_BOTTOM_LEFT = 3; //  bottom|left
    public static final int GRAVITY_BOTTOM_RIGHT = 4; //  bottom|right
    public static final int GRAVITY_RATIO_LEFT = 5; // ratio|left
    public static final int GRAVITY_RATIO_RIGHT = 6; // ratio|right
    public static final int GRAVITY_RATIO_TOP = 7; //  ratio|top
    public static final int GRAVITY_RATIO_BOTTOM = 8; //  ratio|bottom

    public static final int[] X_GRAVITIES = new int[] {
            GRAVITY_RATIO, GRAVITY_LEFT, GRAVITY_RIGHT, GRAVITY_CENTER
    };
    public static final int[] Y_GRAVITIES = new int[] {
            GRAVITY_RATIO, GRAVITY_TOP, GRAVITY_BOTTOM, GRAVITY_CENTER
    };
    public static final int[] BALL_GRAVITIES = new int[] {
            GRAVITY_RATIO, GRAVITY_TOP_LEFT, GRAVITY_TOP_RIGHT, GRAVITY_BOTTOM_LEFT, GRAVITY_BOTTOM_RIGHT
            , GRAVITY_RATIO_LEFT, GRAVITY_RATIO_RIGHT, GRAVITY_RATIO_TOP, GRAVITY_RATIO_BOTTOM
    };

    public static int getXGravityImageResource(int gravity) {
        switch (gravity) {
            case GRAVITY_CENTER:
                return R.drawable.center_light;
            case GRAVITY_RATIO:
                return R.drawable.percent_light;
            case GRAVITY_LEFT:
                return R.drawable.back2_light;
            case GRAVITY_RIGHT:
                return R.drawable.forward2_light;
            default:
                return 0;
        }
    }

    public static int getYGravityImageResource(int gravity) {
        switch (gravity) {
            case GRAVITY_CENTER:
                return R.drawable.center_light;
            case GRAVITY_RATIO:
                return R.drawable.percent_light;
            case GRAVITY_TOP:
                return R.drawable.up2_light;
            case GRAVITY_BOTTOM:
                return R.drawable.down2_light;
            default:
                return 0;
        }
    }

    public static int getBallGravityImageResource(int gravity) {
        switch (gravity) {
            case GRAVITY_RATIO:
                return R.drawable.ratio; // R.drawable.percent_light;
            case GRAVITY_TOP_LEFT:
                return R.drawable.top_left;
            case GRAVITY_TOP_RIGHT: // top|right
                return R.drawable.top_right;
            case GRAVITY_BOTTOM_LEFT:
                return R.drawable.bottom_left;
            case GRAVITY_BOTTOM_RIGHT: // top|right
                return R.drawable.bottom_right;
            case GRAVITY_RATIO_LEFT: // ratio|left
                return R.drawable.ratio_left;
            case GRAVITY_RATIO_RIGHT: // ratio|right
                return R.drawable.ratio_right;
            case GRAVITY_RATIO_TOP: // ratio|left
                return R.drawable.ratio_top;
            case GRAVITY_RATIO_BOTTOM: // ratio|right
                return R.drawable.ratio_bottom;
            default:
                return 0;
        }
    }


    public static int getBallGravityNameResId(int gravity) {
        switch (gravity) {
            case GRAVITY_RATIO:
                return R.string.ratio; // R.drawable.percent_light;
            case GRAVITY_TOP_LEFT:
                return R.string.top_left;
            case GRAVITY_TOP_RIGHT: // top|right
                return R.string.top_right;
            case GRAVITY_BOTTOM_LEFT:
                return R.string.bottom_left;
            case GRAVITY_BOTTOM_RIGHT: // top|right
                return R.string.bottom_right;
            case GRAVITY_RATIO_LEFT: // ratio|left
                return R.string.ratio_left;
            case GRAVITY_RATIO_RIGHT: // ratio|right
                return R.string.ratio_right;
            case GRAVITY_RATIO_TOP: // ratio|left
                return R.string.ratio_top;
            case GRAVITY_RATIO_BOTTOM: // ratio|right
                return R.string.ratio_bottom;
            default:
                return 0;
        }
    }
    public static boolean isRatio(int ballGravity) {
        return ballGravity == GRAVITY_RATIO || ballGravity == GRAVITY_RATIO_LEFT || ballGravity == GRAVITY_RATIO_RIGHT;
    }

    public static boolean isBottom(int ballGravity) {
        return ballGravity == GRAVITY_RATIO_BOTTOM || ballGravity == GRAVITY_BOTTOM_LEFT || ballGravity == GRAVITY_BOTTOM_RIGHT;
    }

    public static boolean isTop(int ballGravity) {
        return ballGravity == GRAVITY_RATIO_TOP || ballGravity == GRAVITY_TOP_LEFT || ballGravity == GRAVITY_TOP_RIGHT;
    }

    public static boolean isRight(int ballGravity) {
        return ballGravity == GRAVITY_RATIO_RIGHT || ballGravity == GRAVITY_TOP_RIGHT || ballGravity == GRAVITY_BOTTOM_RIGHT;
    }

    public static boolean isLeft(int ballGravity) {
        return ballGravity == GRAVITY_RATIO_LEFT || ballGravity == GRAVITY_TOP_LEFT || ballGravity == GRAVITY_BOTTOM_LEFT;
    }

    public static final KeyEvent KEY_EVENT;
    public static final KeyCharacterMap KEY_CHARACTER_MAP;
    static {
        KEY_EVENT = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ESCAPE);
        KEY_CHARACTER_MAP = KEY_EVENT.getKeyCharacterMap();
    }

    public static String getShowName(JSONObject item) {
        return getShowName(item, null, null);
    }
    public static String getShowName(JSONObject item, List<String> httpHostList, List<String> webHostList) {
        if (item == null || item.isEmpty()) {
            return "";
        }

        String name;

        int type = item.getIntValue(KEY_TYPE);
        int action = item.getIntValue(KEY_ACTION);
        boolean isKey = type == InputUtil.EVENT_TYPE_KEY;

        if (isKey || type == InputUtil.EVENT_TYPE_TOUCH) {
            boolean isDown = action == (isKey ? KeyEvent.ACTION_DOWN : MotionEvent.ACTION_DOWN);
            boolean isEdit = item.getBooleanValue("edit");
            int keyCode = item.getIntValue("keyCode");
            boolean isShowView = isDown && (isKey == false || isEdit || KEY_CHARACTER_MAP.isPrintingKey(keyCode));

            String idName = isShowView ? item.getString("targetIdName") : null;
            String viewType = isShowView ? item.getString("targetType") : null;
            if (isShowView && StringUtil.isEmpty(idName)) {
                idName = item.getString("focusIdName");
                viewType = item.getString("focusType");
                if (StringUtil.isEmpty(idName)) {
                    idName = item.getString("parentIdName");
                    viewType = item.getString("parentType");
                }
            }

            String[] ks = isShowView ? StringUtil.split(viewType, ".", true) : null;
            String lk = ks == null || ks.length <= 0 ? null : ks[ks.length - 1];
            String viewStr = isShowView ? (" " + StringUtil.trim(idName) + (StringUtil.isEmpty(lk) ? "" : "@" + StringUtil.trim(lk))) : "";

            if (isKey) {
                if (isEdit) {
                    String editAction = "EDIT " + EditTextEvent.getWhenName(item.getIntValue("when"))
                            + " [" + item.getIntValue("selectStart") + ", " + item.getIntValue("selectEnd") + "]";
                    String s = StringUtil.getString(item.getString("text"));
                    int l = s.length();
                    if (l > 20) {
                        int m = l/2;
                        s = s.substring(0, 7) + "..." + s.substring(m - 3, m + 3) + "..." + s.substring(l - 7);
                    }
                    name = " " + editAction + viewStr + "\n" + s;
                } else {
                    name = viewStr + "\n" + InputUtil.getKeyCodeName(keyCode);
                }
            } else {
                int pc = item.getIntValue("pointerCount");
                name = viewStr + "\n[" + item.getIntValue("x") + ", " + item.getIntValue(KEY_Y) + "]";

                int x2 = item.getIntValue(KEY_X2);
                int y2 = item.getIntValue(KEY_Y2);
                if (x2 != 0 || y2 != 0) {
                    name += ", [" + x2 + ", " + y2 + "]";
                }

                if (pc > 2) {
                    name += " .." + pc;
                }
            }
        }
        else if (type == InputUtil.EVENT_TYPE_UI) {
            name = item.getString("name");
            if (StringUtil.isEmpty(name, true)) {
                name = item.getString("fragment");
                if (StringUtil.isEmpty(name, true)) {
                    name = item.getString("activity");
                }

                int ind = name == null ? -1 : name.lastIndexOf(".");
                if (ind >= 0) {
                    name = name.substring(ind + 1);
                }
            }
            name = "\n" + name;
        }
        else if (type == InputUtil.EVENT_TYPE_HTTP) {
            String format = item.getString("format");
            String status = item.getString("status");
            String host = item.getString("host");
            String url = item.getString("url");

            boolean isReq = action >= 0 && action != InputUtil.HTTP_ACTION_RESPONSE;
            if (isReq) {
                try {
                    Integer.parseInt(format);
                    format = "";
                } catch (Throwable e) {
                    // e.printStackTrace();
                    Log.e(TAG, e.getMessage());
                }
            }
            else if (StringUtil.isEmpty(status)) {
                status = "200";
            }

            String showPath = getShowPath(url, host, httpHostList, webHostList);
            name = " " + (isReq ? format : status) + "\n" + showPath;
        }
        else {
            name = "UNKNOWN !!!";
        }

        return name;
    }

    public static String getShowPath(String url, String host, List<String> httpHostList, List<String> webHostList) {
        url = StringUtil.trim(url);
        host = StringUtil.trim(host);
        if (StringUtil.isEmpty(host)) {
            host = StringUtil.getHost(url);
        }
        if (StringUtil.isNotEmpty(host) && url != null && url.startsWith(host)) {
            url = url.substring(host.length());
        }

        return StringUtil.limitLength(
                StringUtil.isEmpty(host) || url.contains("://") || (httpHostList != null && httpHostList.contains(host))
                || (webHostList != null && webHostList.contains(host)) ? url : host + url
        );
    }

    public static String getShowContent(JSONObject obj, String state, boolean isEvent, boolean isView) {
        return getShowContent(obj, state, isEvent, isView, null);
    }
    public static String getShowContent(JSONObject obj, String state, boolean isEvent, boolean isView, Integer i) {
        return getShowContent(obj, state, isEvent, isView, i, false);
    }
    public static String getShowContent(JSONObject obj, String state, boolean isEvent, boolean isView, Integer i, boolean showHeader) {
        if (obj == null) {
            obj = new JSONObject();
        }

        if (isView) {
            String x = getPixel(obj.get(KEY_X));
            String y = getPixel(obj.get(KEY_Y));
            String w = getPixel(obj.get(KEY_WIDTH));
            String h = getPixel(obj.get(KEY_HEIGHT));
            JSONArray cl = obj.getJSONArray(KEY_CHILD_LIST);
            String hint = obj.getString(KEY_HINT);
            String text = obj.getString(KEY_TEXT);
            String image = obj.getString(KEY_IMAGE);
            String background = obj.getString(KEY_BACKGROUND);

            return (obj.getBooleanValue(KEY_DISABLE) ? "- " : "") + (i == null ? "" : (i + 1) + ". ")
                    + (cl == null ? "" : "[" + cl.size() + "] ") + "id: " + StringUtil.trim(obj.getString(KEY_VIEW_ID_NAME))
                    + "\ntype: " + StringUtil.trim(obj.getString(KEY_TYPE))
                    + "\nx: " + x + ", y: " + y + "\nwidth: " + w + ", height: " + h
                    + (cl == null || cl.isEmpty() ? "" : ", childCount: " + cl.size())
                    + (StringUtil.isEmpty(image) ? "" : "\nimage: " + image)
                    + (StringUtil.isEmpty(background) ? "" : "\nbackground: " + background)
                    + (StringUtil.isEmpty(hint) ? "" : "\nhint: " + hint)
                    + (StringUtil.isEmpty(text) ? "" : "\ntext: " + text)
                    ;
        }


        JSONObject flow = obj.getJSONObject("flow");
        if (flow == null) {
            flow = obj.getJSONObject("Flow");
            if (flow == null) {
                flow = new JSONObject();
            }
        }

        Integer step = obj.getInteger("step");
        if (step == null || step < 0) {
            step = i;
        }

        long time = (isEvent ? obj : flow).getLongValue(KEY_TIME);
        String prefix = (obj.getBooleanValue(KEY_DISABLE) ? "-" : "") + (step == null ? "" : step + 1) + "."
                + (StringUtil.isEmpty(state) ? " " : " [" + state + "] ")
                + (isEvent ? StringUtil.formatTime(time) : StringUtil.formatDateTime(time)) + "  ";
        if (isEvent) {
            int type = obj.getIntValue(KEY_TYPE);
            int action = obj.getIntValue(KEY_ACTION);
            boolean isKey = type == InputUtil.EVENT_TYPE_KEY;

            if (isKey || type == InputUtil.EVENT_TYPE_TOUCH) {
                boolean isDown = action == (isKey ? KeyEvent.ACTION_DOWN : MotionEvent.ACTION_DOWN);
                boolean isEdit = obj.getBooleanValue("edit");
                int keyCode = obj.getIntValue("keyCode");
                boolean isShowView = isDown && (isKey == false || isEdit || KEY_CHARACTER_MAP.isPrintingKey(keyCode));

                String idName = isShowView ? obj.getString("targetIdName") : null;
                String viewType = isShowView ? obj.getString("targetType") : null;
                String fidName = isShowView ? obj.getString("focusIdName") : null;
                String fviewType = isShowView ? obj.getString("focusType") : null;
                String pidName = isShowView ? obj.getString("parentIdName") : null;
                String pviewType = isShowView ? obj.getString("parentType") : null;

                String viewStr = isShowView ? ("\ntarget: " + StringUtil.trim(idName) + "@" + StringUtil.trim(viewType)
                        + "\nfocus: " + StringUtil.trim(fidName) + "@" + StringUtil.trim(fviewType)
                        + "\nparent: " + StringUtil.trim(pidName) + "@" + StringUtil.trim(pviewType)
                ) : "";

                if (isKey) {
                    if (obj.getBooleanValue("edit")) {
                        return prefix + "EDIT " + EditTextEvent.getWhenName(obj.getIntValue("when"))
                                + "\n[" + obj.getIntValue("selectStart") + ", " + obj.getIntValue("selectEnd") + "] "
                                + obj.getString("text") + viewStr;
                    } else {
                        return prefix + InputUtil.getKeyActionName(action)
                                + "\nrepeatCount: " + obj.getIntValue("repeatCount") + ", scanCode: "
                                + InputUtil.getScanCodeName(obj.getIntValue("scanCode"))
                                + "         " + InputUtil.getKeyCodeName(keyCode) + viewStr;
                    }
                }
                else {
                    int pc = obj.getIntValue("pointerCount");
                    int x2 = obj.getIntValue(KEY_X2);
                    int y2 = obj.getIntValue(KEY_Y2);
                    String pointerIds = obj.getString("pointerIds");
                    String pointers = obj.getString("pointers");

                    return prefix + InputUtil.getTouchActionName(action)
                            + "\nx: " + obj.getIntValue("x") + ", y: " + obj.getIntValue(KEY_Y)
                            + (x2 == 0 && y2 == 0 ? "" : ",  x2: " + x2 + ", y2: " + y2)
                            + "\nsplitX: " + obj.getIntValue("splitX") + ", splitY: " + obj.getIntValue("splitY")
                            + "           " + InputUtil.getOrientationName(obj.getIntValue("orientation"))
                            + "\npointerCount: " + pc + ", pointerIds: " + StringUtil.trim(pointerIds)
                            + (StringUtil.isEmpty(pointers) ? "" : "\npointers: " + StringUtil.trim(pointers))
                            + viewStr;
                }
            }
            else if (type == InputUtil.EVENT_TYPE_UI) {
                String fragment = obj.getString("fragment");

                return prefix + InputUtil.getUIActionName(action)
                        + "\nactivity: " + obj.getString("activity")
                        + (StringUtil.isEmpty(fragment, true) ? "" : "\nfragment: " + fragment);
            }
            else if (type == InputUtil.EVENT_TYPE_HTTP) {
                boolean isReq = action >= 0 && action != InputUtil.HTTP_ACTION_RESPONSE;
                String format = obj.getString("format");
                String status = obj.getString("status");

                // if (isReq) {
                    try {
                        Integer.parseInt(format);
                        format = "";
                    } catch (Throwable e) {
                        // e.printStackTrace();
                        Log.e(TAG, e.getMessage());
                    }
                // }
                if (StringUtil.isEmpty(status) && ! isReq) {
                    status = "200";
                }

                String host = StringUtil.trim(obj.getString("host"));
                String url = StringUtil.trim(obj.getString("url"));
                String query = StringUtil.trim(obj.getString("query"));
                String header = showHeader ? obj.getString("header") : null;
                String reqHeader = showHeader ? obj.getString("reqHeader") : null;
                String resHeader = showHeader ? obj.getString("resHeader") : null;
                if (action < 0 || action == InputUtil.HTTP_ACTION_RESPONSE) {
                    if (StringUtil.isEmpty(resHeader)) {
                        resHeader = header;
                    }
                } else if (StringUtil.isEmpty(reqHeader)) {
                    reqHeader = header;
                }

                return prefix + InputUtil.getHTTPActionName(action) + " " + (isReq ? format : status)
                        + "\nURL: " + (StringUtil.isEmpty(host) || url.contains("://") || url.startsWith(host) ? url : host + url)
                        + (StringUtil.isEmpty(query) || url.endsWith(query) ? "" : (url.contains("?") ? "&" : "?") + query)
                        + "\n\nREQUEST: " + format + (StringUtil.isEmpty(reqHeader)
                            ? "" : "\n" + StringUtil.limitLength(reqHeader, 200, StringUtil.ELLIPSIZE_MIDDlE) + "\n"
                        ) + "\n" + StringUtil.limitLength(obj.getString("request"), 300, StringUtil.ELLIPSIZE_MIDDlE)
                        + (isReq ? "" : "\n\nRESPONSE: " + status + (StringUtil.isEmpty(resHeader)
                            ? "" : "\n" + StringUtil.limitLength(resHeader, 200, StringUtil.ELLIPSIZE_MIDDlE) + "\n"
                        ) + "\n" + StringUtil.limitLength(obj.getString("response"), 1000, StringUtil.ELLIPSIZE_MIDDlE))
                        + "\n";
            }
            else {
                return prefix + "UNKNOWN !!!";
            }
        } else {
            JSONObject device = obj.getJSONObject("device");
            JSONObject system = obj.getJSONObject("system");

            JSONObject countInput = obj.getJSONObject("input");
            countInput = countInput == null ? new JSONObject() : countInput;
            int total = countInput.getIntValue(JSONResponse.KEY_COUNT);
            int disableCount = countInput.getIntValue("disableCount");

            return prefix + "(" + (total - disableCount) + "/" + total + ")\n" + flow.getString("name")
                            + (isEvent ? "" : "\n" + StringUtil.trim(flow.getString("project"))
                            + ", " + StringUtil.trim(flow.getString("appName"))
                            + ", " + StringUtil.trim(flow.getString("package"))
                            + ", " + StringUtil.trim(flow.getString("versionName"))
                            + "(" + StringUtil.trim(flow.getString("versionCode")) + ")"
                    ) + (isEvent ? "" : "\n" + getTitleText(device, system));
        }
    }

    public static String getPixel(Object obj) {
        return getPixel(obj, true);
    }
    public static String getPixel(Object obj, boolean isTry) {
        if (obj == null) {
            return isTry ? "0" : null;
        }

        try {
            if (obj instanceof Number) {
                return ((Number) obj).intValue() + "dp";
            }

            if (obj instanceof String) {
                return isTry && StringUtil.isEmpty(obj) ? "0" : (String) obj; // Integer.parseInt((String) obj);
            }

            // Integer pixel;
            if (obj instanceof JSONObject) {
                // pixel = ((JSONObject) obj).getInteger("sp");
                // if (StringUtil.isNotEmpty(pixel)) {
                //     return pixel + "sp";
                // }
                //
                // if (StringUtil.isEmpty(pixel)) {
                //     pixel = ((JSONObject) obj).getInteger("real");
                //
                //     if (StringUtil.isEmpty(pixel)) {
                //         pixel = ((JSONObject) obj).getInteger("dp");
                //         if (StringUtil.isEmpty(pixel)) {
                //             pixel = ((JSONObject) obj).getInteger("px");
                //         }
                //     }
                // }

                Set<Map.Entry<String, Object>> set = ((JSONObject) obj).entrySet();
                if (set == null || set.isEmpty()) {
                    return null;
                }

                boolean first = true;
                String s = "";
                for (Map.Entry<String, Object> entry : set) {
                    String k = entry == null ? null : entry.getKey();
                    Object v = entry == null ? null : entry.getValue();
                    boolean isNum = v instanceof Number;
                    boolean isPercent = isNum && ("wp".equals(k) || "pp".equals(k));
                    Integer n = isNum ? (int) Math.round(((Number) v).doubleValue()*(isPercent ? 100 : 1)) : null;
                    String ns = n == null ? null : String.valueOf(n);
                    if ("px".equals(k) && n < 0) {
                        if (n == ViewGroup.LayoutParams.MATCH_PARENT) {
                            ns = "MATCH";
                            k = "";
                        } else if (n == ViewGroup.LayoutParams.WRAP_CONTENT) {
                            ns = "WRAP";
                            k = "";
                        }
                    }

                    s += (first ? "" : ", ") + (ns != null ? (isPercent ? ns + "%" : ns) : JSON.toJSONString(v)) + (KEY_REAL.equals(k) ? "px" : StringUtil.trim(k));
                    first = false;
                }

                return s.contains(",") ? "[" + s + "]" : s;
            }

            throw new IllegalArgumentException("obj 的类型只能是 Integer/String/JSONObject !");
        } catch (Throwable e) {
            e.printStackTrace();
            if (isTry) {
                return JSON.toJSONString(obj);
            }

            throw e;
        }
    }

    public static String getTitleText(JSONObject device, JSONObject system) {
        if (device == null) {
            device = new JSONObject();
        }

        if (system == null) {
            system = new JSONObject();
        }

        return StringUtil.getOrDefault(device.getString("brand"), "[Brand]")
                + " " + StringUtil.getOrDefault(device.getString("model"), "[Model]")
                + ", " + StringUtil.getOrDefault(device.getString(KEY_WIDTH), "[Width]")
                + "X" + StringUtil.getOrDefault(device.getString(KEY_HEIGHT), "[Height]")
                + ", Android" // + StringUtil.getOrDefault(system.getString("brand"), "[System]")
                + " " + StringUtil.getOrDefault(system.getString("versionName"), "[Version]");
    }

}
