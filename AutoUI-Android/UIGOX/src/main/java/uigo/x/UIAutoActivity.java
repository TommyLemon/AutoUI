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

import static uigo.x.UIAutoApp.KEY_NAV_HEIGHT;
import static uigo.x.UIAutoApp.KEY_NAV_SHOW;
import static uigo.x.UIAutoApp.KEY_NAV_UNIT_DP;
import static uigo.x.UIAutoApp.KEY_STATUS_HEIGHT;
import static uigo.x.UIAutoApp.KEY_STATUS_SHOW;
import static uigo.x.UIAutoApp.KEY_STATUS_UNIT_DP;
import static uigo.x.UIAutoListActivity.RESULT_FINISH;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.ViewStateListener;

import unitauto.apk.UnitAutoActivity;
import apijson.JSON;
import apijson.JSONRequest;
import apijson.JSONResponse;


/**自动 UI 测试，需要用 UIAuto 发请求到这个设备
 * https://github.com/TommyLemon/UIGOX
 * @author Lemon
 */
public class UIAutoActivity extends UnitAutoActivity {
    private static final String TAG = "UIAutoActivity";

    public static final String INTENT_FLOW_ID = "INTENT_FLOW_ID";
    public static final String KEY_PLATFORM_ACCOUNT = "KEY_PLATFORM_ACCOUNT";
    public static final String KEY_PLATFORM_PASSWORD = "KEY_PLATFORM_PASSWORD";


    /**
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, UIAutoActivity.class); //.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }
    
    private UIAutoApp app = UIAutoApp.getInstance();
    Handler handler = new Handler();

    private Activity context;
    private long flowId = 0;

    @Override
    protected int getLayoutResId() {
        return R.layout.ui_auto_activity;
    }


    private TextView etUnitProxy;
    private ProgressBar pbUnitProxy;
    private TextView tvUnitProxy;

    private TextView etUIStatusHeight;
    private TextView tvUIStatusUnit;
    private TextView tvUIStatusShow;

    private TextView etUIAccount;
    private TextView tvUISignIn;
    private TextView etUIPassword;

    private TextView etUINavHeight;
    private TextView tvUINavUnit;
    private TextView tvUINavShow;

    private View pbUISignIn;
    private View pbUISignUp;

    int statusHeight;
    int navigationHeight;
    int screenWidth;
    int screenHeight;
    int size = app.dp2px(50);
    int radius = app.dp2px(25);
    int padding = app.dp2px(100);

    FloatBallView vFloatBall;
    IFloatWindow ball;

    boolean out = false;
    int nx = 0;
    int ny = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        app.onUIAutoActivityCreate(this);

        statusHeight = (int) Math.round(app.statusHeight);
        navigationHeight = (int) Math.round(app.navigationHeight);
        screenWidth = app.screenWidth;
        screenHeight = app.screenHeight;
        if (statusHeight < app.min) {
            statusHeight = DisplayUtil.getStatusBarHeight(app.getApplicationContext());
        }
        if (navigationHeight < app.min) {
            navigationHeight = DisplayUtil.getNavigationBarHeight(app.getApplicationContext());
        }
        if (screenWidth < app.dp2px(360)) {
            screenWidth = DisplayUtil.getScreenWidth(app.getApplicationContext());
        }
        if (screenHeight < app.dp2px(450)) {
            screenHeight = DisplayUtil.getScreenHeight(app.getApplicationContext());
        }

        flowId = getIntent().getLongExtra(INTENT_FLOW_ID, flowId);
        isProxy = ! app.isProxy; // app.isProxyEnabled();
        server = app.getProxyServer();
        if (StringUtil.isEmpty(server, true)) {
            server = "http://apijson.cn:9090";
        }

        cache = app.getSharedPreferences();
        account = cache.getString(KEY_PLATFORM_ACCOUNT, account);
        password = cache.getString(KEY_PLATFORM_PASSWORD, password);

        sttHeight = cache.getInt(KEY_STATUS_HEIGHT, statusHeight);
        isStatusUnitDp = cache.getBoolean(KEY_STATUS_UNIT_DP, isStatusUnitDp);
        isStatusShow = cache.getBoolean(KEY_STATUS_SHOW, app.isSeparatedStatus);

        navHeight = cache.getInt(KEY_NAV_HEIGHT, navigationHeight);
        isNavUnitDp = cache.getBoolean(KEY_NAV_UNIT_DP, isNavUnitDp);
        isNavShow = cache.getBoolean(KEY_NAV_SHOW, app.isNavigationShow);

        if (sttHeight <= app.min) {
            sttHeight = isStatusUnitDp ? app.dp2px(statusHeight) : statusHeight;
        }
        if (navHeight <= app.min) {
            navHeight = isNavUnitDp ? app.dp2px(navigationHeight) : navigationHeight;
        }

        etUnitProxy = findViewById(R.id.etUnitProxy);
        pbUnitProxy = findViewById(R.id.pbUnitProxy);
        tvUnitProxy = findViewById(R.id.tvUnitProxy);

        etUIStatusHeight = findViewById(R.id.etUIStatusHeight);
        tvUIStatusUnit = findViewById(R.id.tvUIStatusUnit);
        tvUIStatusShow = findViewById(R.id.tvUIStatusShow);

        etUIAccount = findViewById(R.id.etUIAccount);
        pbUISignIn = findViewById(R.id.pbUISignIn);
        tvUISignIn = findViewById(R.id.tvUISignIn);

        etUIPassword = findViewById(R.id.etUIPassword);
        pbUISignUp = findViewById(R.id.pbUISignUp);

        etUINavHeight = findViewById(R.id.etUINavHeight);
        tvUINavUnit = findViewById(R.id.tvUINavUnit);
        tvUINavShow = findViewById(R.id.tvUINavShow);

        pbUISignIn.setVisibility(isSignedIn ? View.VISIBLE : View.GONE);

        etUnitProxy.setText(server);
        etUIAccount.setText(account);
        tvUISignIn.setText(isSignedIn ? R.string.sign_out : R.string.sign_in);
        etUIPassword.setText(password);

        etUIStatusHeight.setText(String.valueOf(sttHeight));
        tvUIStatusUnit.setText(isStatusUnitDp ? "dp" : "px");
        tvUIStatusShow.setText(isStatusShow ? R.string.showing : R.string.hiding);

        etUINavHeight.setText(String.valueOf(navHeight));
        tvUINavUnit.setText(isNavUnitDp ? "dp" : "px");
        tvUINavShow.setText(isNavShow ? R.string.showing : R.string.hiding);

        switchProxy(tvUnitProxy);
        if (! getAsyncServer().isRunning()) {
            start(tvUnitStart);
        }

//        app.isSplitShowing = true;
//        app.isSplit2Showing = true;
//        app.onUIAutoActivityCreate(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                app.showCoverAndSplit(false, true);
//            }
//        }, 1000);

        tvUnitProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchProxy(v);
            }
        });

        if (isSignedIn == false) {
            signIn(tvUISignIn);
        }

        vFloatBall = (FloatBallView) getLayoutInflater().inflate(R.layout.ui_auto_split_ball_layout, null);
        vFloatBall.setImageResource(R.drawable.add_light);

        FloatWindow.destroy("ball");
        FloatWindow
                .with(getApplicationContext())
                .setTag("ball")
                .setView(vFloatBall)
                .setWidth(size)                       //设置控件宽高
                .setHeight(size)
                .setX(app.screenWidth/2 - radius)                                   //设置控件初始位置
                .setY(sttHeight - radius - statusHeight)
                .setMoveType(MoveType.active)
                .setDesktopShow(true) //必须为 true，否则切换 Activity 就会自动隐藏 //桌面显示
                .setViewStateListener(new ViewStateListener() {
                    @Override
                    public void onPositionUpdate(int x, int y) {
                        boolean o = false;
                        if (x < padding - radius) {
                            x = padding - radius;
                            o = true;
                        }
                        else if (x > app.screenWidth - padding - radius) {
                            x = app.screenWidth - padding - radius;
                            o = true;
                        }

                        if (isStatusBall) {
                            if (y < Math.round(- 1.5*radius)) { // - (isStatusShow ? getStatusHeightPx() : 0))) {
                                y = (int) Math.round(- 1.5*radius); // - (isStatusShow ? getStatusHeightPx() : 0));
                                o = true;
                            }
                            else if (y > padding - radius) {
                                y = padding - radius;
                                o = true;
                            }

                            sttHeight = isStatusUnitDp ? app.px2dp((float) (y + radius + statusHeight)) : y + radius + statusHeight;
                        } else {
                            if (y > app.screenHeight - radius) { // - (isNavShow ? getNavHeightPx() : 0))) {
                                y = app.screenHeight - radius; // - (isNavShow ? getNavHeightPx() : 0));
                                o = true;
                            } else if (y < app.screenHeight - 2*padding) { //  - (isNavShow ? getNavHeightPx() : 0)) {
                                y = app.screenHeight - 2*padding; // - (isNavShow ? getNavHeightPx() : 0);
                                o = true;
                            }

                            navHeight = isNavUnitDp ? app.px2dp((float) (app.screenHeight - y + radius)) : app.screenHeight - y + radius;
                        }

                        nx = x;
                        ny = y;

                        isStatusBall = ny < app.screenHeight/2;
                        if (isStatusBall) {
                            if (getStatusHeightPx() > padding) {
                                sttHeight = isStatusUnitDp ? app.px2dp(padding) : padding;
                            }
                            etUIStatusHeight.setText(String.valueOf(sttHeight));
                        } else {
                            if (getNavHeightPx() > 2*padding) {
                                navHeight = isNavUnitDp ? app.px2dp(2*padding) : 2*padding;
                            }
                            etUINavHeight.setText(String.valueOf(navHeight));
                        }

                        out = o;
                        if (o) {
                            int fx = x;
                            int fy = y;
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (fx != nx || fy != ny) {
                                        return;
                                    }

//                                    isStatusBall = ny < app.screenHeight/2;
                                    ball.updateX(nx);
                                    ball.updateY(ny);
                                }
                            }, 1000);
                        }
                    }

                    @Override
                    public void onShow() {
                        vFloatBall.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onHide() {
                        vFloatBall.setVisibility(View.GONE);
                    }

                    @Override
                    public void onDismiss() {
                        onHide();
                    }

                    @Override
                    public void onMoveAnimStart() {}
                    @Override
                    public void onMoveAnimEnd() {
//                        int x = ball.getX();
//                        int y = ball.getY();

//                        if (out) {
//                            out = false;
//                            ball.updateX(nx);
//                            ball.updateY(ny);
//                        }
//                        vFloatBall.performClick();
                    }
                    @Override
                    public void onBackToDesktop() {}
                })    //监听悬浮控件状态改变
//                .setPermissionListener(mPermissionListener)  //监听权限申请结果
                .build();
        ball = FloatWindow.get("ball");

//        vFloatBall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isStatusBall = ny < app.screenHeight/2;
//                if (isStatusBall) {
//                    etUIStatusHeight.setText(String.valueOf(statusHeight));
//                } else {
//                    etUINavHeight.setText(String.valueOf(navHeight));
//                }
//            }
//        });
    }

    private int getStatusHeightPx() {
        return isStatusUnitDp ? app.dp2px(sttHeight) : sttHeight;
    }
    private int getNavHeightPx() {
        return isNavUnitDp ? app.dp2px(navHeight) : navHeight;
    }

    public void onClick(View v) {
        Toast.makeText(context, "onClick BUTTON", Toast.LENGTH_SHORT).show();
        record(v);
//        finish();
    }

    public void toRemote(View v) {
        startActivityForResult(UIAutoListActivity.createIntent(context, false), 0);
    }

    public void toLocal(View v) {
        startActivityForResult(UIAutoListActivity.createIntent(context, true), 0);
    }

    public void admin(View v) {
        startActivity(UnitAutoActivity.createIntent(context));
    }

    protected boolean isProxy = false;
    protected String server = null;
    public void switchProxy(View v) {
        isProxy = ! isProxy;

        etUnitProxy.setEnabled(! isProxy);
        tvUnitProxy.setText(isProxy ? unitauto.apk.R.string.stop : unitauto.apk.R.string.start);
        pbUnitProxy.setVisibility(isProxy ? View.VISIBLE : View.GONE);

        server = StringUtil.trim(etUnitProxy);
        app.setHttpProxy(isProxy, server);
    }

    public void record(View v) {
        flowId = - System.currentTimeMillis();

//        cover.setVisibility(View.VISIBLE);
//        showCover(true, context);
//        finish();

        app.onUIAutoActivityCreate(this, true);
        app.prepareRecord();
        finish();
    }

    boolean isStatusBall = true;

    int sttHeight = 30;
    boolean isStatusUnitDp = false;
    public void changeStatusUnit(View v) {
        isStatusUnitDp = ! isStatusUnitDp;
        String heightStr = StringUtil.noBlank(etUIStatusHeight);
        sttHeight = StringUtil.isEmpty(heightStr) ? sttHeight : Integer.parseInt(heightStr);
        sttHeight = isStatusUnitDp ? app.px2dp(sttHeight) : app.dp2px(sttHeight);
        if (getStatusHeightPx() > 2*padding) {
            sttHeight = isStatusUnitDp ? app.px2dp(2*padding) : 2*padding;
        }

        etUIStatusHeight.setText(String.valueOf(sttHeight));
        tvUIStatusUnit.setText(isStatusUnitDp ? "dp" : "px");

        isStatusBall = true;
        ball.updateY(getStatusHeightPx() - radius - statusHeight);
    }

    boolean isStatusShow = false;
    public void showStatus(View v) {
        isStatusShow = ! isStatusShow;
        tvUIStatusShow.setText(isStatusShow ? R.string.showing : R.string.hiding);

        isStatusBall = true;
        ball.updateX(app.screenWidth/2 - radius);
        ball.updateY(getStatusHeightPx() - radius - statusHeight);
    }

    int navHeight = 50;
    boolean isNavUnitDp = false;
    public void changeNavUnit(View v) {
        isNavUnitDp = ! isNavUnitDp;
        String heightStr = StringUtil.noBlank(etUINavHeight);
        navHeight = StringUtil.isEmpty(heightStr) ? navHeight : Integer.parseInt(heightStr);
        navHeight = isNavUnitDp ? app.px2dp(navHeight) : app.dp2px(navHeight);
        if (getNavHeightPx() > 2*padding) {
            navHeight = isNavUnitDp ? app.px2dp(2*padding) : 2*padding;
        }

        etUINavHeight.setText(String.valueOf(navHeight));
        tvUINavUnit.setText(isNavUnitDp ? "dp" : "px");

        isStatusBall = false;
        ball.updateY(app.screenHeight - getNavHeightPx() - getStatusHeightPx() - radius);
    }

    boolean isNavShow = false;
    public void showNav(View v) {
        isNavShow = ! isNavShow;
        tvUINavShow.setText(isNavShow ? R.string.showing : R.string.hiding);

        isStatusBall = false;
        ball.updateX(app.screenWidth/2 - radius);
        ball.updateY(app.screenHeight - getNavHeightPx() - getStatusHeightPx() - radius);
    }



    private static boolean isSignedIn = false;
    public void signIn(View v) {
        signInOrUp(false, pbUISignIn);
    }

    public void signUp(View v) {
        signInOrUp(true, pbUISignUp);
    }


    private String account;
    private String password;
    private String verify;

    public void signInOrUp(boolean isSignUp, View pb) {
        boolean isSignIn = ! isSignUp;

        pb.setVisibility(View.VISIBLE);

        server = StringUtil.trim(etUnitProxy);
        String act = StringUtil.trim(etUIAccount);
        String pwd = StringUtil.trim(etUIPassword);

        account = StringUtil.isEmpty(act, true) ? "13000082001" : act;
        password = StringUtil.isEmpty(pwd, true) ? "123456" : pwd;

        JSONRequest request = new JSONRequest();
        if (isSignUp) {
            if (StringUtil.isEmpty(verify, true)) {
                request.put("type", "1");
                request.put("phone", account);
                String req = request.toJSONString();
                tvUnitRequest.setText(req);

                HttpManager.getInstance().post(server + "/post/verify", req, new HttpManager.OnHttpResponseListener() {
                    @Override
                    public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
                        JSONResponse response = new JSONResponse(resultJson);
                        String json = JSON.format(resultJson);
                        JSONObject verifyObj = response.getJSONObject("verify");
                        verify = verifyObj == null ? null : verifyObj.getString("verify");
                        boolean isOk = StringUtil.isNotEmpty(verify, true);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Get verify code" + (isOk ? " succeed! " : " failed! "
                                                + response.getMsg()), Toast.LENGTH_LONG).show();
                                pb.setVisibility(View.GONE);
                                tvUnitResponse.setText(json);

                                if (isOk) {
                                    signInOrUp(isSignUp, pb);
                                }
                            }
                        });
                    }
                });

                return;
            }

            {   // Privacy <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                JSONRequest privacy = new JSONRequest();
                privacy.put("phone", account);
                privacy.put("_password", password);
                request.put("Privacy", privacy);
            }   // Privacy >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            {   // User <<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                JSONRequest user = new JSONRequest();
                user.put("name", account);
                request.put("User", user);
            }   // User >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            request.put("verify", verify); // FIXME 调接口获取，弹窗输入
        }
        else if (isSignedIn == false) {
            request.put("phone", account);
            request.put("password", password);
        }

        String req = request.toString();

        tvUnitRequest.setText(req);

        HttpManager.getInstance().post(server + (isSignUp ? "/register" : (isSignedIn ? "/logout" : "/login")), req, new HttpManager.OnHttpResponseListener() {
            @Override
            public void onHttpResponse(int requestCode, String resultJson, Throwable e) {
                JSONResponse response = new JSONResponse(resultJson);
                String json = JSON.format(resultJson);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        boolean isOk = response.isSuccess();
                        if (isOk) {
                            if (isSignIn) {
                                isSignedIn = ! isSignedIn;
                            }

                            cache.edit()
                                    .putString(KEY_PLATFORM_ACCOUNT, account)
                                    .putString(KEY_PLATFORM_PASSWORD, password)
                                    .commit();
                        }
                        else {
                            verify = null;
                            isSignedIn = false;
                        }

                        Toast.makeText(context, (isSignUp ? R.string.sign_up : (isSignedIn ? R.string.sign_out : R.string.sign_in))
                                + (isOk ? " succeed! " : " failed! " + response.getMsg()), Toast.LENGTH_LONG)
                                .show();

                        if (isSignIn) {
                            tvUISignIn.setText(isSignedIn ? R.string.sign_out : R.string.sign_in);
                        }

                        pb.setVisibility(isSignIn || isSignUp ? View.GONE : View.VISIBLE);
                        tvUnitResponse.setText(json);
                    }
                });
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_FINISH) {
            setResult(RESULT_FINISH);
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        ball.show();
        vFloatBall.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        ball.hide();
        vFloatBall.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        FloatWindow.destroy("ball");
        cache.edit()
                .remove(UIAutoApp.KEY_ENABLE_PROXY).putBoolean(UIAutoApp.KEY_ENABLE_PROXY, isProxy)
                .remove(UIAutoApp.KEY_PROXY_SERVER).putString(UIAutoApp.KEY_PROXY_SERVER, server)
                .remove(KEY_STATUS_HEIGHT).putInt(KEY_STATUS_HEIGHT, sttHeight)
                .remove(KEY_STATUS_UNIT_DP).putBoolean(KEY_STATUS_UNIT_DP, isStatusUnitDp)
                .remove(KEY_STATUS_SHOW).putBoolean(KEY_STATUS_SHOW, isStatusShow)
                .remove(KEY_NAV_HEIGHT).putInt(KEY_NAV_HEIGHT, navHeight)
                .remove(KEY_NAV_UNIT_DP).putBoolean(KEY_NAV_UNIT_DP, isNavUnitDp)
                .remove(KEY_NAV_SHOW).putBoolean(KEY_NAV_SHOW, isNavShow)
                .commit();

        super.onDestroy();
    }

}

