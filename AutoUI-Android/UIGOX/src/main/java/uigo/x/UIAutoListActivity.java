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

import static uigo.x.Constant.KEY_APP_CACHE;
import static uigo.x.Constant.KEY_APP_CACHE_NAME_CONFIG_MAP;
import static uigo.x.Constant.KEY_CHILD_LIST;
import static uigo.x.Constant.KEY_DISABLE;
import static uigo.x.Constant.KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP;
import static uigo.x.Constant.KEY_TYPE;
import static uigo.x.Constant.KEY_VIEW_ID;
import static uigo.x.Constant.KEY_VIEW_ID_NAME;
import static uigo.x.Constant.KEY_VIEW_LIST;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import apijson.JSON;
import apijson.JSONRequest;
import apijson.JSONResponse;


/** 操作流程 Flow /操作步骤 Input 列表
 * https://github.com/TommyLemon/UIGOX
 * @author Lemon
 */
public class UIAutoListActivity extends Activity implements HttpManager.OnHttpResponseListener {
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

    public static final String CACHE_FLOW = "CACHE_FLOW";
    public static final String CACHE_TOUCH = "KEY_TOUCH";


    private Activity context;

    private String pageName;
    private int parentViewId;
    private String parentViewIdName;
    private String parentViewType;
    private long deviceId = 0;
    private long systemId = 0;
    private long flowId = 0;
    private boolean isView = false;
    private boolean isEvent = false;
    private boolean isLocal = false;
    private boolean hasTempTouchList = false;
    private JSONArray eventList = null;

    private View llUIAutoListFilter;

    private EditText etUIAutoListProject;
    private EditText etUIAutoListAppName;
    private CheckBox cbUIAutoListAppName;
    private CheckBox cbUIAutoListProject;
    private TextView tvUIAutoListCompare;
    private CheckBox cbUIAutoListVersion;
    private CheckBox cbUIAutoListDisable;

    private CheckBox cbUIAutoListDevice;
    private TextView tvUIAutoListPixelCompare;
    private CheckBox cbUIAutoListPixel;
    private TextView tvUIAutoListSysCompare;
    private CheckBox cbUIAutoListSystem;

    private CheckBox cbUIAutoListAccount;
    private TextView etUIAutoListAccount;
    private TextView etUIAutoListAccountName;
    private TextView tvUIAutoListSignIn;

    private CheckBox cbUIAutoListPassword;
    private TextView etUIAutoListPassword;
    private CheckBox cbUIAutoListID;
    private TextView etUIAutoListID;
    private View pbUIAutoListSignIn;
    private View pbUIAutoListSignUp;

    private EditText etUIAutoListName;
    private TextView tvUIAutoListCount;
    private ListView lvUIAutoList;
    // private View llUIAutoListBar;

    private TextView btnUIAutoListReplay;
    private ProgressBar pbUIAutoList;
    private EditText etUIAutoListUrl;
    private Button btnUIAutoListGet;

    List<String> compareList = Arrays.asList("=", "<=", "<", ">=", ">", "!=");

    SharedPreferences cache;
    String cacheKey;
    String tempKey;
    String name;
    String packageName = "";
    String project = "";
    String appName = "UIGOX";
    String versionCompare = "=";
    String pixelCompare = "=";
    String sysCompare = "<=";
    long versionCode;
    String versionName;
    String testAccount;
    String testPassword;
    String accountId;
    String accountName;
    DisplayMetrics metric = new DisplayMetrics();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_auto_list_activity);

        context = this;
        cache = UIAutoApp.getInstance().getSharedPreferences();

        isLocal = getIntent().getBooleanExtra(INTENT_IS_LOCAL, isLocal);
        pageName = getIntent().getStringExtra(INTENT_PAGE_NAME);
        parentViewId = getIntent().getIntExtra(INTENT_PARENT_VIEW_ID, parentViewId);
        parentViewIdName = getIntent().getStringExtra(INTENT_PARENT_VIEW_ID_NAME);
        if (StringUtil.isEmpty(parentViewIdName) && parentViewId > 0) {
            parentViewIdName = UIAutoApp.getInstance().getResIdName(parentViewId);
        }

        parentViewType = getIntent().getStringExtra(INTENT_PARENT_VIEW_TYPE);
        isView = StringUtil.isNotEmpty(parentViewIdName) || StringUtil.isNotEmpty(parentViewType);

        flowId = getIntent().getLongExtra(INTENT_FLOW_ID, flowId);
        tempKey = getIntent().getStringExtra(INTENT_TEMP_KEY);
        name = getIntent().getStringExtra(INTENT_NAME);
        if (StringUtil.isNotEmpty(tempKey, true)) {
            eventList = JSON.parseArray(cache.getString(tempKey, null));
        }

        if (StringUtil.isEmpty(name, true)) {
            name = isView ? StringUtil.trim(StringUtil.isEmpty(parentViewIdName) ? parentViewId : parentViewIdName) + "@" + StringUtil.trim(parentViewType)
                    : (isEvent ? getString(R.string.temp_flow) + " " + DateFormat.getDateTimeInstance().format(new Date()) : "");
        }

        packageName = UIAutoApp.getApp().getPackageName();
        project = cache.getString(KEY_PROJECT, project);
        if (StringUtil.isEmpty(project)) {
            project = packageName;
        }
        appName = cache.getString(KEY_APP_NAME, appName);
        if (StringUtil.isEmpty(project)) {
            appName = project;
        }

        testAccount = cache.getString(KEY_ACCOUNT, testAccount);
        if (StringUtil.isEmpty(testAccount)) {
            testAccount = "1300082001";
        }
        testPassword = cache.getString(KEY_PASSWORD, testPassword);
        if (StringUtil.isEmpty(testPassword)) {
            testPassword = "123456";
        }

        accountId = cache.getString(KEY_ACCOUNT_ID, accountId);
        accountName = cache.getString(KEY_ACCOUNT_NAME, accountName);

        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            versionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? pi.getLongVersionCode() : pi.versionCode;
            versionName = pi.versionName;
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (isView) {
            array = JSON.parseArray(cache.getString(KEY_VIEW_LIST, null));
            if (array == null) {
                Map<View, JSONObject> map = UIAutoApp.getInstance().getViewPropertyListMap();
                array = map == null ? new JSONArray(0) : new JSONArray(new ArrayList<>(map.values()));
            }
        }

        hasTempTouchList = eventList != null && eventList.isEmpty() == false;
        // 断点发现能在 asTempTouchList = true 时也返回 false  isTouch = flowId > 0 || asTempTouchList;
        isEvent = isLocal || hasTempTouchList || flowId != 0;

        cacheKey = isEvent ? CACHE_TOUCH : CACHE_FLOW;
        if (isLocal) {
            JSONArray allList = cacheKey.equals(tempKey) ? eventList : JSON.parseArray(cache.getString(cacheKey, null));

            if (hasTempTouchList) {
                if (! cacheKey.equals(tempKey)) {
                    if (allList == null || allList.isEmpty()) {
                        allList = eventList;
                    } else {
                        allList.addAll(eventList);
                    }
                    cache.edit().remove(cacheKey).putString(cacheKey, UIAutoApp.toJSONString(allList)).apply();
                }
            }
            else {
                hasTempTouchList = true;
                if (flowId == 0) {
                    eventList = allList;
                } else {
                    eventList = new JSONArray();
                    if (allList != null) {
                        for (int i = 0; i < allList.size(); i++) {
                            JSONObject obj = allList.getJSONObject(i);
                            if (obj != null && obj.getLongValue("flowId") == flowId) {
                                eventList.add(obj);
                            }
                        }
                    }
                }
            }
        }

        Display display = getWindowManager().getDefaultDisplay();
        display.getRealMetrics(metric);

        llUIAutoListFilter = findViewById(R.id.llUIAutoListFilter);

        cbUIAutoListAppName = findViewById(R.id.cbUIAutoListAppName);
        cbUIAutoListProject = findViewById(R.id.cbUIAutoListProject);
        tvUIAutoListCompare = findViewById(R.id.tvUIAutoListCompare);
        cbUIAutoListVersion = findViewById(R.id.cbUIAutoListVersion);
        cbUIAutoListDisable = findViewById(R.id.cbUIAutoListDisable);

        cbUIAutoListDevice = findViewById(R.id.cbUIAutoListDevice);
        tvUIAutoListPixelCompare = findViewById(R.id.tvUIAutoListPixelCompare);
        cbUIAutoListPixel = findViewById(R.id.cbUIAutoListPixel);
        tvUIAutoListSysCompare = findViewById(R.id.tvUIAutoListSysCompare);
        cbUIAutoListSystem = findViewById(R.id.cbUIAutoListSystem);

        llUIAutoListFilter.setVisibility(isView ? View.GONE : View.VISIBLE);

        boolean isEnableFilter = isLocal == false && isEvent == false;
        cbUIAutoListAppName.setEnabled(isEnableFilter);
        cbUIAutoListAppName.setText(appName);
        cbUIAutoListProject.setEnabled(isEnableFilter);
        cbUIAutoListProject.setText(project);
        tvUIAutoListCompare.setText(versionCompare);
        cbUIAutoListVersion.setEnabled(isEnableFilter);
        cbUIAutoListVersion.setText(versionName + "(" + versionCode + ")");
        cbUIAutoListDisable.setChecked(isLocal);

        cbUIAutoListDevice.setEnabled(isEnableFilter);
        cbUIAutoListDevice.setText(Build.BRAND + " " + Build.MODEL);
        tvUIAutoListPixelCompare.setText(pixelCompare);
        cbUIAutoListPixel.setEnabled(isEnableFilter);
        cbUIAutoListPixel.setText(metric.widthPixels + "X" + metric.heightPixels);
        tvUIAutoListSysCompare.setText(sysCompare);
        cbUIAutoListSystem.setEnabled(isEnableFilter);
        // cbUIAutoListSystem.setText(Build.BRAND + " " + Build.VERSION.RELEASE);
        cbUIAutoListSystem.setText("Android " + Build.VERSION.RELEASE);

        etUIAutoListProject = findViewById(R.id.etUIAutoListProject);
        etUIAutoListAppName = findViewById(R.id.etUIAutoListAppName);

        cbUIAutoListAccount = findViewById(R.id.cbUIAutoListAccount);
        cbUIAutoListAccount.setEnabled(isEnableFilter);
        etUIAutoListAccount = findViewById(R.id.etUIAutoListAccount);
        etUIAutoListAccount.setEnabled(isLocal);
        etUIAutoListAccountName = findViewById(R.id.etUIAutoListAccountName);
        etUIAutoListAccountName.setEnabled(isLocal);
        pbUIAutoListSignIn = findViewById(R.id.pbUIAutoListSignIn);
        tvUIAutoListSignIn = findViewById(R.id.tvUIAutoListSignIn);

        cbUIAutoListPassword = findViewById(R.id.cbUIAutoListPassword);
        cbUIAutoListPassword.setEnabled(isEnableFilter);
        etUIAutoListPassword = findViewById(R.id.etUIAutoListPassword);
        etUIAutoListPassword.setEnabled(isLocal);
        cbUIAutoListID = findViewById(R.id.cbUIAutoListID);
        cbUIAutoListID.setEnabled(isEnableFilter);
        etUIAutoListID = findViewById(R.id.etUIAutoListID);
        etUIAutoListID.setEnabled(isLocal);
        pbUIAutoListSignUp = findViewById(R.id.pbUIAutoListSignUp);

        etUIAutoListName = findViewById(R.id.etUIAutoListName);
        tvUIAutoListCount = findViewById(R.id.tvUIAutoListCount);
        lvUIAutoList = findViewById(R.id.lvUIAutoList);
        // llUIAutoListBar = findViewById(R.id.llUIAutoListBar);

        btnUIAutoListReplay = findViewById(R.id.btnUIAutoListReplay);
        pbUIAutoList = findViewById(R.id.pbUIAutoList);
        etUIAutoListUrl = findViewById(R.id.etUIAutoListUrl);
        btnUIAutoListGet = findViewById(R.id.btnUIAutoListGet);

        etUIAutoListProject.setVisibility(isLocal ? View.VISIBLE : View.GONE);
        etUIAutoListAppName.setVisibility(isLocal ? View.VISIBLE : View.GONE);
        cbUIAutoListAppName.setVisibility(isLocal ? View.GONE : View.VISIBLE);
        cbUIAutoListProject.setVisibility(isLocal ? View.GONE : View.VISIBLE);
        tvUIAutoListCompare.setVisibility(isEvent || isLocal ? View.GONE : View.VISIBLE);
        cbUIAutoListDisable.setVisibility(isEvent ? View.VISIBLE : View.GONE);
        tvUIAutoListPixelCompare.setVisibility(isEvent || isLocal ? View.GONE : View.VISIBLE);
        tvUIAutoListSysCompare.setVisibility(isEvent || isLocal ? View.GONE : View.VISIBLE);
        // cbUIAutoListVersion.setVisibility(isTouch ? View.GONE : View.VISIBLE);
        // cbUIAutoListPixel.setVisibility(isTouch ? View.GONE : View.VISIBLE);
        // cbUIAutoListSystem.setVisibility(isTouch ? View.GONE : View.VISIBLE);

        btnUIAutoListReplay.setVisibility(isEvent ? View.VISIBLE : View.GONE);
        // etUIAutoListName.setVisibility(isTouch ? View.VISIBLE : View.GONE);
        btnUIAutoListReplay.setText(isView ? R.string.record : R.string.replay);
        etUIAutoListName.setEnabled(! isView);

//        etUIAutoListName.setEnabled(isLocal || hasTempTouchList);
        etUIAutoListProject.setText(project);
        etUIAutoListAppName.setText(appName);
        etUIAutoListAccount.setText(testAccount);
        etUIAutoListAccountName.setText(accountName);
        etUIAutoListPassword.setText(testPassword);
        etUIAutoListID.setText(accountId);
        etUIAutoListName.setText(name);
        etUIAutoListName.setHint(isEvent ? R.string.temp_flow : R.string.test_cases);

        String server = UIAutoApp.getInstance().getProxyServer();
        if (StringUtil.isEmpty(server, true)) {
            server = "http://apijson.cn:9090";
        }
        etUIAutoListUrl.setText(server.endsWith("/") ? server : server + "/");

//        llUIAutoListBar.setVisibility(isLocal ? View.GONE : View.VISIBLE);

        int count = eventList == null ? 0 : eventList.size();
        tvUIAutoListCount.setText((isLocal ? "0" : count + "/") + count);
        btnUIAutoListGet.setText(isLocal ? "post" : "get");

        isFilter = cbUIAutoListDevice.isEnabled() && cbUIAutoListDevice.isChecked();
        CompoundButton.OnCheckedChangeListener onCheckChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isLocal && buttonView == cbUIAutoListDisable) {
                    showList(array);
                    return;
                }

                isFilter = cbUIAutoListDevice.isEnabled() && cbUIAutoListDevice.isChecked();

                page = 0;
                send();
            }
        };

        cbUIAutoListProject.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListAppName.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListVersion.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListDisable.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListDevice.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListPixel.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListSystem.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListAccount.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListPassword.setOnCheckedChangeListener(onCheckChangeListener);
        cbUIAutoListID.setOnCheckedChangeListener(onCheckChangeListener);

        tvUIAutoListCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ind = compareList.indexOf(versionCompare);
                versionCompare = compareList.get((ind + 1)%compareList.size());
                tvUIAutoListCompare.setText(versionCompare);

                if (cbUIAutoListVersion.isChecked()) {
                    onCheckChangeListener.onCheckedChanged(cbUIAutoListVersion, true);
                }
            }
        });

        tvUIAutoListPixelCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ind = compareList.indexOf(pixelCompare);
                pixelCompare = compareList.get((ind + 1)%compareList.size());
                tvUIAutoListPixelCompare.setText(pixelCompare);

                if (cbUIAutoListPixel.isChecked()) {
                    onCheckChangeListener.onCheckedChanged(cbUIAutoListPixel, true);
                }
            }
        });

        tvUIAutoListSysCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ind = compareList.indexOf(sysCompare);
                sysCompare = compareList.get((ind + 1)%compareList.size());
                tvUIAutoListSysCompare.setText(sysCompare);

                if (cbUIAutoListSystem.isChecked()) {
                    onCheckChangeListener.onCheckedChanged(cbUIAutoListSystem, true);
                }
            }
        });

        etUIAutoListName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (isEvent) {
                        saveBaseInfo();
                    } else {
                        send();
                    }
                    return true;
                }
                return false;
            }
        });

        lvUIAutoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (array != null) {
                    JSONObject obj = array.getJSONObject(position);
                    if (isView || isEvent) {
                        boolean disable = obj.getBooleanValue("disable");
                        String state = isLocal ? stateMap.get(obj) : null;
                        if (isLocal && StringUtil.isEmpty(state, true)) {
                            state = isLocal ? "Local" : "Remote";
                        }

                        String msg = InputUtil.getShowContent(obj, state, isEvent, isView, position, true) + "\n\n\n" + JSON.format(obj);
                        new AlertDialog.Builder(context)
                                .setTitle(getString(disable ? R.string.enable : R.string.disable) + "?")
                                .setMessage(msg)
                                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        StringUtil.copyText(context, msg);
                                    }
                                })
                                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        obj.put("disable", disable ? 0 : 1);
                                        showList(array);
                                    }
                                })
                                .create()
                                .show();

                        JSONArray cl = isView ? obj.getJSONArray(KEY_CHILD_LIST) : null;
                        if (cl != null && ! cl.isEmpty()) {
                            cache.edit().remove(KEY_VIEW_LIST).putString(KEY_VIEW_LIST, JSON.toJSONString(cl)).commit();

                            int viewId = obj.getIntValue(KEY_VIEW_ID);
                            String idName = obj.getString(KEY_VIEW_ID_NAME);
                            String viewType = StringUtil.trim(obj.getString(KEY_TYPE));
                            startActivity(UIAutoListActivity.createIntent(context, isLocal, viewId, idName, viewType, pageName));
                        }
                    }
                    else {
                        flow = obj == null ? null : obj.getJSONObject("flow");
                        if (flow == null) {
                            flow = new JSONObject();
                        }

                        startActivityForResult(UIAutoListActivity.createIntent(
                                context, flow.getLongValue("id"), flow.getString("name")
                        ), REQUEST_EVENT_LIST);
                    }
                }
            }
        });

        lvUIAutoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog = new AlertDialog.Builder(context)
                        .setMessage(R.string.confirm_delete)
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(position);
                            }
                        })
                        .create();
                dialog.show();
                return true;
            }
        });

        if (isView) {
            loadViewList();
        } else if (hasTempTouchList) {
            showList(eventList);
        } else {
            send();
        }
    }

    AlertDialog dialog;
    private void loadViewList() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }

        JSONObject ignoreActivityViewIdListMap = JSON.parseObject(cache.getString(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP, null));
        List<String> ignoreViewIdList = ignoreActivityViewIdListMap == null ? null : JSON.parseArray(ignoreActivityViewIdListMap.getString(pageName), String.class);

        Map<View, JSONObject> map = UIAutoApp.getInstance().getViewPropertyListMap();
        Collection<JSONObject> coll = map == null ? null : map.values();
        List<Object> list = coll == null ? new ArrayList<>(0) : new ArrayList<>(coll);
        eventList = new JSONArray(sortWithDisable(list, ignoreViewIdList));
        showList(eventList);
    }

    private List<Object> sortWithDisable(List<Object> list, List<String> ignoreList) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == null) {
                    return o2 == null ? 0 : 1;
                }
                if (o2 == null) {
                    return -1;
                }

                if (o1 instanceof JSONObject && o2 instanceof JSONObject) {
                    JSONObject obj1 = (JSONObject) o1;
                    JSONObject obj2 =  (JSONObject) o2;

                    if (ignoreList != null && ! ignoreList.isEmpty()) {
                        if (ignoreList.contains(obj1.getString(KEY_VIEW_ID_NAME))) {
                            obj1.put(KEY_DISABLE, true);
                        }
                        if (ignoreList.contains(obj2.getString(KEY_VIEW_ID_NAME))) {
                            obj2.put(KEY_DISABLE, true);
                        }
                    }

                    return obj2.getIntValue(KEY_DISABLE) - obj1.getIntValue(KEY_DISABLE);
                }

                return 0;
            }
        });

        return list;
    }


    int total = 0;
    private ArrayAdapter<String> adapter;
    /** 示例方法 ：显示列表内容
     * @param list_
     */
    private void setList(List<String> list_) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> list = list_ == null ? new ArrayList<>() : list_;
                int allCount = list.size();

                tvUIAutoListCount.setText((isLocal ? remoteCount + "/" : allCount + "/") + (total <= 0 ? allCount : total));
                pbUIAutoList.setVisibility(View.GONE);
                if (adapter == null) {
                    adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, list) {
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if (isLocal == false && noMore == false && isLoading == false && allCount > 0 && position >= getCount() - 1) {
                                page ++;
                                send();
                            }
                            return super.getView(position, convertView, parent);
                        }
                    };

                    lvUIAutoList.setAdapter(adapter);
                } else {
                    adapter.clear();
                    adapter.addAll(list);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void showList(JSONArray array) {
        this.array = array;
        boolean hasDisable = cbUIAutoListDisable.isChecked();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                if (array != null) {
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        if (hasDisable == false && obj.getBooleanValue("disable")) {
                            continue;
                        }

                        String state = isLocal ? stateMap.get(obj) : null;
                        if (isLocal && StringUtil.isEmpty(state, true)) {
                            state = isLocal ? "Local" : "Remote";
                        }

                        String content = InputUtil.getShowContent(obj, state, isEvent, isView, i);
                        list.add(content);
                    }
                }

                setList(list);
            }
        }).start();
    }


    private int remoteCount = 0;
    private int count = 0;
    private int page = 0;
    private Map<JSONObject, String> stateMap = new HashMap<JSONObject, String>();

    public void onClickSend(View v) {
        if (isLoading) {
            return;
        }

        page ++; // page = 0;
        noMore = false;
        send();
    }

    private boolean isLoading = false;

    public void saveBaseInfo() {
        saveBaseInfo(null);
    }
    public void saveBaseInfo(Runnable runnable) {
        needLoading = true;

        final String host = StringUtil.trim(etUIAutoListUrl);
        testAccount = StringUtil.trim(etUIAutoListAccount);
        testPassword = StringUtil.trim(etUIAutoListPassword);
        accountId = StringUtil.trim(etUIAutoListID);
        accountName = StringUtil.trim(etUIAutoListAccountName);

        pbUIAutoList.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONRequest request = new JSONRequest();
                String table;

                if (deviceId <= 0) {
                    table = "Device";

                    request.put("width", metric.widthPixels);
                    request.put("height", metric.heightPixels);
                    request.put("maker", Build.MANUFACTURER);
                    request.put("brand", Build.BRAND);
                    request.put("model", Build.MODEL);

                    try {
                        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
                        request.put("imei", tm == null ? null : tm.getImei());
                    }
                    catch (Throwable e) {
                        e.printStackTrace();
                    }
                } else if (systemId <= 0) {
                    table = "System";
                    request.put("type", 0); // 类型：0 - Android OS, 1 - iOS, 3 - HarmonyOS, 4 - Tizen
                    request.put("brand", Build.BRAND);
                    request.put("versionCode", Build.VERSION.SDK_INT);
                    request.put("versionName", Build.VERSION.RELEASE);
                } else {
                    table = "Flow";
                    request.put("deviceId", deviceId);
                    request.put("systemId", systemId);
                    request.put("name", StringUtil.trim(etUIAutoListName));

                    Application app = UIAutoApp.getApp();
                    project = StringUtil.trim(etUIAutoListProject);
                    if (StringUtil.isEmpty(project)) {
                        project = app.getPackageName();
                    }
                    request.put("project", project);

                    appName = StringUtil.trim(etUIAutoListAppName);
                    if (StringUtil.isEmpty(project)) {
                        appName = project;
                    }

                    request.put("appName", appName);
                    request.put("package", packageName);
                    request.put("versionCode", versionCode);
                    request.put("versionName", versionName);
                    request.put("testAccount", testAccount);
                    request.put("testPassword", testPassword);
                    request.put("accountId", accountId);
                    request.put("accountName", accountName);

                    String appCache = cache.getString(KEY_APP_CACHE, null);
                    if (StringUtil.isNotEmpty(appCache)) {
                        request.put("appCache", appCache);
                    }

                    try {
                        JSONObject map = JSON.parseObject(cache.getString(KEY_APP_CACHE_NAME_CONFIG_MAP, null));
                        Set<Map.Entry<String, Object>> set = map == null ? null : map.entrySet();
                        if (set != null && ! set.isEmpty()) {
                            JSONObject allMap = new JSONObject();
                            for (Map.Entry<String, Object> entry : set) {
                                try {
                                    String name = entry.getKey();
                                    Object val = entry.getValue();
                                    int mode = Integer.parseInt(String.valueOf(val));
                                    SharedPreferences spf = UIAutoApp.getApp().getSharedPreferences(name, mode);

                                    allMap.put(name, spf == null ? null : spf.getAll());
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                }
                            }

                            String s = JSON.toJSONString(allMap);
                            if (StringUtil.isNotEmpty(s)) {
                                request.put("sharedPreferences", s);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                HttpManager.getInstance().post(host + (deviceId <= 0 || systemId <= 0 || flowId <= 0 ? "post/" : "put/") + table, request.toString(), new HttpManager.OnHttpResponseListener() {
                    @Override
                    public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pbUIAutoList.setVisibility(View.GONE);
                            }
                        });

                        JSONResponse response = new JSONResponse(resultJson);
                        if (response.isSuccess()) {

                            if (deviceId <= 0) {
                                JSONResponse resp = response.getJSONResponse("Device");
                                deviceId = resp == null ? 0 : resp.getId();
                                if (deviceId > 0) {
                                    if (runnable != null) {
                                        runOnUiThread(runnable);
                                    }
                                }
                            } else if (systemId <= 0) {
                                JSONResponse resp = response.getJSONResponse("System");
                                systemId = resp == null ? 0 : resp.getId();
                                if (systemId > 0) {
                                    if (runnable != null) {
                                        runOnUiThread(runnable);
                                    }
                                }
                            } else {
                                JSONResponse resp = response.getJSONResponse("Flow");
                                flowId = resp == null ? 0 : resp.getId();
                                if (flowId > 0) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            etUIAutoListName.setEnabled(false);
                                            if (runnable != null) {
                                                runnable.run();
                                            }
                                        }
                                    });
                                }
                            }
                        } else {
                            isLoading = false;

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, "Upload Device/System/Flow failed! " + response.getMsg(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });

            }
        }).start();
    }

    private boolean isFilter = false;
    int disableUploadCount = 0;

    public void send() {
        needLoading = true;
        isLoading = true;
        cbUIAutoListDisable.setEnabled(false);

        final String fullUrl = StringUtil.trim(etUIAutoListUrl) + StringUtil.get(btnUIAutoListGet).toLowerCase();

        pbUIAutoList.setVisibility(View.VISIBLE);

        name = StringUtil.trim(etUIAutoListName);
        appName = StringUtil.trim(etUIAutoListAppName);
        project = StringUtil.trim(etUIAutoListProject);
        String filterName = isEvent || StringUtil.isEmpty(name) ? null : name;
        String filterAppName = StringUtil.isEmpty(appName) || ! cbUIAutoListAppName.isChecked() ? null : appName;
        String filterProject = StringUtil.isEmpty(project) || ! cbUIAutoListProject.isChecked() ? null : project;
        Long filterVersionCode = cbUIAutoListVersion.isChecked() ? versionCode : null;
        String filterVersionName = cbUIAutoListVersion.isChecked() ? versionName : null;

        Integer filterSysVersionCode = cbUIAutoListSystem.isChecked() ? Build.VERSION.SDK_INT : null;
        String filterSysVersionName = cbUIAutoListSystem.isChecked() ? Build.VERSION.RELEASE : null;

        Integer filterWidth = cbUIAutoListPixel.isChecked() ? metric.widthPixels : null;
        Integer filterHeight = cbUIAutoListPixel.isChecked() ? metric.heightPixels : null;

        Integer[] disable = cbUIAutoListDisable.isChecked() ? null : new Integer[]{0, null};

        String filterAccount = StringUtil.isEmpty(testAccount) || ! cbUIAutoListAccount.isChecked() ? null : testAccount;
        String filterPassword = StringUtil.isEmpty(testPassword) || ! cbUIAutoListPassword.isChecked() ? null : testPassword;
        // String filterName = StringUtil.isEmpty(accountName) || ! cbUIAutoListName.isChecked() ? null : accountName;
        String filterId = StringUtil.isEmpty(accountId) || ! cbUIAutoListID.isChecked() ? null : accountId;

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (hasTempTouchList == false) {
                    hasTempTouchList = true;
                    cache.edit().remove(cacheKey).putString(cacheKey, UIAutoApp.toJSONString(eventList)).commit();
                }

                if (isLocal) {
                    if ((deviceId <= 0 || systemId <= 0 || flowId <= 0) && ! isView) {
                        saveBaseInfo(new Runnable() {
                            @Override
                            public void run() {
                                send();
                            }
                        });
                        return;
                    }

                    if (eventList == null || eventList.isEmpty()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pbUIAutoList.setVisibility(View.GONE);
                                Toast.makeText(context, "All is uploaded!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else {
                        for (int i = 0; i < eventList.size(); i++) {
                            JSONObject input = eventList.getJSONObject(i);
                            if (input == null || input.getLongValue("id") > 0) {
                                continue;
                            }

                            if (disable != null && input.getBooleanValue("disable")) {
                                disableUploadCount ++;
                                continue;
                            }

                            String state = stateMap.get(input);
                            if ("Remote".equals(state) || "Uploading".equals(state)) {
                                continue;
                            }

                            stateMap.put(input, "Uploading");

                            JSONObject obj = JSON.parseObject(UIAutoApp.toJSONString(input));
                            obj.remove("id");
                            Set<String> set = obj.keySet();
                            for (String k : set) {
                                Object v = obj.get(k);
                                if (v instanceof Map) {
                                    obj.put(k, JSON.toJSONString(v));
                                }
                            }

                            JSONRequest request = new JSONRequest();
                            if (isView) {
                                request.put("View", obj);
                                request.setTag("View");
                            } else {
                                obj.put("flowId", flowId);

                                if (obj.get("deviceId") == null) {
                                    obj.put("deviceId", 1);
                                }
                                if (obj.get("x") == null) {
                                    obj.put("x", 0);
                                }
                                if (obj.get("y") == null) {
                                    obj.put("y", 0);
                                }

                                request.put("Input", obj);
                                request.setTag("Input");
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    pbUIAutoList.setVisibility(View.VISIBLE);
                                }
                            });
                            HttpManager.getInstance().post(fullUrl, request.toString(), new HttpManager.OnHttpResponseListener() {
                                @Override
                                public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
                                    isLoading = false;

                                    JSONResponse response = new JSONResponse(resultJson);
                                    if (response.isSuccess()) {
                                        remoteCount ++;

                                        JSONResponse resp = response.getJSONResponse(isView ? "View" : "Input");
                                        input.put("id", resp == null ? 0 : resp.getId());
                                        stateMap.put(input, "Remote");
                                    }
                                    else {
                                        stateMap.put(input, isLocal ? "Local" : "Remote");
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    Toast.makeText(context, "Upload Input failed! "
                                                            + (StringUtil.isEmpty(response.getMsg(), true)
                                                            ? (e == null ? "" : e.getMessage())
                                                            : response.getMsg()
                                                    ), Toast.LENGTH_LONG).show();
                                                }
                                                catch (Throwable e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    }

                                    showList(array);
                                }
                            });
                        }

                    }
                }
                else {
                    JSONRequest request = new JSONRequest();

                    if (isEvent) {
                        {   // Flow <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            JSONRequest flow = new JSONRequest();
                            flow.put("id", flowId);
                            request.put("Flow", flow);
                        }   // Flow >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                        {   // Device <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            JSONRequest device = new JSONRequest();
                            device.put("id@", "Flow/deviceId");
                            request.put("Device", device);
                        }   // Device >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                        {   // System <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            JSONRequest system = new JSONRequest();
                            system.put("id@", "Flow/systemId");
                            system.put("@order", "versionCode-,versionName-");
                            request.put("System", system);
                        }   // System >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                        {   // Input[] <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            JSONRequest touchItem = new JSONRequest();
                            {   // Input <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                JSONRequest input = new JSONRequest();
                                input.put("@order", "step+,time+,downTime+,eventTime+");
                                if (flowId > 0) {
                                    input.put("flowId", flowId);
                                }
                                input.put("disable{}", disable);

                                touchItem.put("Input", input);
                            }   // Input >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                            if (page <= 0) {
                                touchItem.setQuery(JSONRequest.QUERY_ALL);
                            }
                            request.putAll(touchItem.toArray(count, page, "Input"));
                        }   // Input[] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    }
                    else {
                        {   // Flow[] <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                            JSONRequest item = new JSONRequest();
                            item.put(JSONRequest.KEY_JOIN, "&/Device,&/System");

                            {   // Flow <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                JSONRequest flow = new JSONRequest();
                                flow.put("name%$", filterName);
                                flow.put("project", filterProject);
                                flow.put("appName", filterAppName);
                                flow.put("testAccount", filterAccount);
                                flow.put("testPassword", filterPassword);
                                flow.put("accountId", filterId);
                                // flow.put("accountName", filterName);
                                // flow.put("package", filterPackage);
                                flow.put("versionCode" + getCompareChar(versionCompare), filterVersionCode);
                                flow.put("versionName" + getCompareChar(versionCompare), filterVersionName);
                                flow.put("@order", "time-");

                                item.put("Flow", flow);
                            }   // Flow >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                            {   // Device <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                JSONRequest device = new JSONRequest();
                                device.put("id@", "/Flow/deviceId");
                                if (isFilter) {
                                    device.put("maker", Build.MANUFACTURER);
                                    device.put("brand", Build.BRAND);
                                    device.put("model", Build.MODEL);
                                }
                                device.put("width" + getCompareChar(pixelCompare), filterWidth);
                                device.put("height" + getCompareChar(pixelCompare), filterHeight);
                                item.put("Device", device);
                            }   // Device >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                            {   // System <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                JSONRequest system = new JSONRequest();
                                system.put("id@", "/Flow/systemId");
                                if (isFilter) {
                                    system.put("type", 0); // Android
                                    // system.put("brand", Build.BRAND);
                                }
                                system.put("versionCode" + getCompareChar(sysCompare), filterSysVersionCode);
                                system.put("versionName" + getCompareChar(sysCompare), filterSysVersionName);
                                system.put("@order", "versionCode-,versionName-");
                                item.put("System", system);
                            }   // System >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                            {   // Input <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                                JSONRequest input = new JSONRequest();
                                input.put("flowId@", "/Flow/id");
                                input.put("@column", "count(*):count;sum(disable):disableCount");
                                input.put("@raw", "@column");

                                item.put("Input", input);
                            }   // Input >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

                            request.putAll(item.toArray(count, page));
                        }   // Flow[] >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                    }

                    HttpManager.getInstance().post(fullUrl, request.toString(), page, UIAutoListActivity.this);
                }

            }
        }).start();
    }

    public static String getCompareChar(String compare) {
        return "=".equals(compare) || "==".equals(compare) ? "" : ("!=".equals(compare) ? "!" : compare);
    }

    private void delete(int position) {
        JSONObject item = array == null || position < 0 || position >= array.size() ? null : array.getJSONObject(position);
        String table = isEvent ? "Input" : "Flow";
        JSONObject item2 = item == null ? null : item.getJSONObject(StringUtil.firstCase(table));
        String id = item2 == null ? null : item2.getString("id");
        if (StringUtil.isEmpty(id, true)) {
            UIAutoApp.getInstance().toast(R.string.pls_select_usable_item);
            return;
        }

        if (isLocal) {
            array.remove(position);
            showList(array);
            return;
        }

        final String host = StringUtil.trim(etUIAutoListUrl);

        pbUIAutoList.setVisibility(View.VISIBLE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONRequest request = new JSONRequest("id", id);
                HttpManager.getInstance().post(host + "delete/" + table, request.toString(), new HttpManager.OnHttpResponseListener() {
                    @Override
                    public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pbUIAutoList.setVisibility(View.GONE);
                            }
                        });

                        JSONResponse response = new JSONResponse(resultJson);
                        JSONResponse resp = response.getJSONResponse(table);
                        boolean ok = resp.isSuccess();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, getString(ok ? R.string.delete_succeed : R.string.delete_failed)
                                        + " " + response.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        });

                        if (ok) {
                            if (array != null && array.size() > position) {
                                array.remove(position);
                                showList(array);
                            }

                            send();
                        }
                    }
                });

            }
        }).start();
    }



    public void replay(View v) {
        if (isEvent) {
            replay(array, flow);
        } else {
            setResult(RESULT_OK, new Intent().putExtra(RESULT_LIST, UIAutoApp.toJSONString(array)));
            finish();
        }
    }

    public static final int RESULT_FINISH = 10;
    public void replay(JSONArray eventList) {
        replay(eventList, null);
    }
    public void replay(JSONArray eventList, JSONObject flow) {
        setResult(RESULT_FINISH);
        finish();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
              UIAutoApp.getInstance().onUIAutoActivityCreate();

              if (isView) {
                 UIAutoApp.getInstance().record();
                 return;
              }

              UIAutoApp.getInstance().prepareReplay(eventList, flow);
            }
        }, 1000);
    }




    private boolean noMore = false;
    private JSONArray array = new JSONArray();
    JSONObject flow;
    JSONObject device;
    JSONObject system;
    @Override
    public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
        isLoading = false;

        Log.d(TAG, "onHttpResponse  resultJson = " + resultJson);
        if (e != null) {
            Log.e(TAG, "onHttpResponse e = " + e.getMessage());
        }
        page = requestCode;
        JSONResponse response = new JSONResponse(resultJson);
        if (isLocal == false && isEvent) {
            flow = response.getJSONObject("flow");
            device = response.getJSONObject("device");
            system = response.getJSONObject("system");
            flow = flow == null ? new JSONObject() : flow;
            device = device == null ? new JSONObject() : device;
            system = system == null ? new JSONObject() : system;
            total = page > 0 ? total : response.getIntValue("listTotal");

            String brand = StringUtil.trim(device.getString("brand"));
            String model = StringUtil.trim(device.getString("model"));
            String deviceStr = StringUtil.trim(device.getString("brand") + " " + device.getString("model"));
            int width = device.getIntValue("width");
            int height = device.getIntValue("height");
            String sysVersion = StringUtil.trim(system.getString("versionName"));

            String appName_ = StringUtil.trim(flow.getString("appName"));
            String project_ = StringUtil.trim(flow.getString("project"));
            String versionName_ = StringUtil.trim(flow.getString("versionName"));
            String versionCode_ = StringUtil.trim(flow.getString("versionCode"));
            String testAccount_ = StringUtil.trim(flow.getString("testAccount"));
            String testPassword_ = StringUtil.trim(flow.getString("testPassword"));
            String accountId_ = StringUtil.trim(flow.getString("accountId"));
            String accountName_ = StringUtil.trim(flow.getString("accountName"));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    cbUIAutoListDevice.setChecked(brand.equals(Build.BRAND) && model.equals(Build.MODEL));
                    cbUIAutoListDevice.setText(deviceStr);
                    cbUIAutoListPixel.setChecked(width == metric.widthPixels && height == metric.heightPixels);
                    cbUIAutoListPixel.setText(width + "X" + height);
                    cbUIAutoListSystem.setChecked(sysVersion.equals(Build.VERSION.RELEASE));
                    cbUIAutoListSystem.setText("Android " + sysVersion);

                    cbUIAutoListAppName.setChecked(appName_.equals(appName));
                    cbUIAutoListAppName.setText(appName_);
                    cbUIAutoListProject.setChecked(project_.equals(project));
                    cbUIAutoListProject.setText(project_);
                    cbUIAutoListVersion.setChecked(versionName_.equals(versionName) && versionCode_.equals(versionCode));
                    cbUIAutoListVersion.setText(versionName_ + "(" + versionCode_ + ")");

                    cbUIAutoListAccount.setChecked(testAccount_.equals(testAccount));
                    etUIAutoListAccount.setText(testAccount_);
                    etUIAutoListAccountName.setText(accountName_);
                    cbUIAutoListPassword.setChecked(testPassword_.equals(testPassword));
                    etUIAutoListPassword.setText(testPassword_);
                    cbUIAutoListID.setChecked(accountId_.equals(accountId));
                    etUIAutoListID.setText(accountId_);

                    cbUIAutoListDisable.setEnabled(true);
                }
            });
        }

        JSONArray arr = response.getArray(isEvent ? "Input[]" : "[]");
        noMore = arr == null || arr.isEmpty();
        if (arr == null) {
            arr = new JSONArray();
        }
        stateMap = new HashMap<>();
        for (int i = 0; i < arr.size(); i++) {
            stateMap.put(arr.getJSONObject(i), "Remote");
        }

        synchronized (array) {
            if (page <= 0 || array == null || array.isEmpty()) {
                array = arr;
            } else if (noMore) {
                page --;
                UIAutoApp.getInstance().toast(R.string.already_loaded_all);
            } else {
                array.addAll(arr);
            }

            showList(array);

            if (noMore == false && array.size() < 1000) {
                page ++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        send();
                    }
                });
            }
        }
    }


    private boolean needLoading = false;
    @Override
    public void onBackPressed() {
        int size = eventList == null ? 0 : eventList.size();
        if (needLoading && size > 0) {
            boolean uploadDisable = cbUIAutoListDisable.isChecked();
            for (int i = 0; i < size; i++) {
                JSONObject obj = eventList.getJSONObject(i);
                if ("Remote".equals(stateMap.get(obj)) == false && ! (uploadDisable || obj.getBooleanValue("disable"))) {
                    Toast.makeText(this, R.string.remains_step_needs_uploading, Toast.LENGTH_SHORT).show();

                    if (size >= 50 && i < size / 2) {
                        lvUIAutoList.smoothScrollToPositionFromTop(i, 0);
                    } else {
                        lvUIAutoList.smoothScrollToPosition(i);
                    }
                    return;
                }
            }
        }

        super.onBackPressed();
    }

    private static final int REQUEST_EVENT_LIST = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_FINISH) {
            setResult(RESULT_FINISH);
            finish();
        }

        if (resultCode != RESULT_OK) {
            return;
        }

        if (isView) {
            loadViewList();
            return;
        }

        if (requestCode == REQUEST_EVENT_LIST) {
            replay(data == null ? null : JSON.parseArray(data.getStringExtra(UIAutoListActivity.RESULT_LIST)), flow);
        }
    }


    @Override
    protected void onDestroy() {
        JSONObject map = UIAutoApp.getInstance().getIgnoreActivityViewListMap();
        if (map == null) {
            map = new JSONObject(true);
            UIAutoApp.getInstance().setIgnoreActivityViewListMap(map);
        }
        map.put(pageName, getIgnoreViewIdList(array));

        SharedPreferences.Editor editor = cache.edit();
        editor.remove(KEY_PROJECT).putString(KEY_PROJECT, StringUtil.trim(etUIAutoListProject))
                .remove(KEY_APP_NAME).putString(KEY_APP_NAME, StringUtil.trim(etUIAutoListAppName))
                .remove(KEY_ACCOUNT).putString(KEY_ACCOUNT, StringUtil.trim(etUIAutoListAccount))
                .remove(KEY_PASSWORD).putString(KEY_PASSWORD, StringUtil.trim(etUIAutoListPassword))
                .remove(KEY_ACCOUNT_ID).putString(KEY_ACCOUNT_ID, StringUtil.trim(etUIAutoListID))
                .remove(KEY_ACCOUNT_NAME).putString(KEY_ACCOUNT_NAME, StringUtil.trim(etUIAutoListAccountName))
                .remove(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP).putString(KEY_IGNORE_ACTIVITY_VIEW_LIST_MAP, JSON.toJSONString(map));

        if (cacheKey.equals(tempKey)) {
            editor.commit();
        } else {
            editor.remove(tempKey).apply();
        }

        super.onDestroy();
    }

    private List<String> getIgnoreViewIdList(JSONArray array) {
        if (array == null || array.isEmpty()) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            JSONObject obj = array.getJSONObject(i);
            if (obj == null || obj.isEmpty()) {
                continue;
            }

            String id = obj.getBooleanValue(KEY_DISABLE) ? obj.getString(KEY_VIEW_ID_NAME) : null;
            String type = obj.getString(KEY_TYPE);
            JSONArray cl = obj.getJSONArray(KEY_CHILD_LIST);
            list.add(StringUtil.trim(id) + "@" + StringUtil.trim(type) + (cl == null ? "" : "[]"));
        }

        return list;
    }

}
