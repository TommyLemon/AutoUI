/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package uigox.demo.application;

import static uigo.x.Constant.KEY_APP_CACHE_NAME_CONFIG_MAP;
import static uigo.x.Constant.KEY_LONG_KEYS;
import static uigo.x.Constant.KEY_MODE;
import static uigo.x.UIAutoListActivity.KEY_APP_NAME;
import static uigo.x.UIAutoListActivity.KEY_PROJECT;
import static uigox.demo.manager.DataManager.PATH_USER;
import static zuo.biao.library.util.SettingUtil.APP_SETTING;

import uigox.demo.activity_fragment.LoginActivity;
import uigox.demo.manager.DataManager;
import uigox.demo.model.User;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
//import uigox.demo.BuildConfig;
import uigo.x.UIAutoApp;
import unitauto.apk.UnitAutoApp;
import zuo.biao.library.base.BaseApplication;
import zuo.biao.library.util.JSON;
import zuo.biao.library.util.SettingUtil;
import zuo.biao.library.util.StringUtil;
import android.util.Log;

import com.scwang.smart.refresh.layout.kernel.BuildConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**Application
 * @author Lemon
 */
public class DemoApplication extends BaseApplication {
	private static final String TAG = "DemoApplication";

	private static DemoApplication context;
	public static DemoApplication getInstance() {
		return context;
	}
	
	// 暂时以继承方式实现，后续改为支持静态调用（需要把 UIAutoApp 成员变量全改为 static）

	public static final List<String> ID_KEYS = Arrays.asList("id", "userId", "momentId", "commentId");
	public static final Map<String, Object> CONFIG_MAP = Map.of(KEY_MODE, Context.MODE_PRIVATE, KEY_LONG_KEYS, ID_KEYS);
	public static final String NAME_MODE_MAP;
	static {
		NAME_MODE_MAP = JSON.toJSONString(Map.of(APP_SETTING, CONFIG_MAP, PATH_USER, CONFIG_MAP));
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = this;

		UIAutoApp uiIns = UIAutoApp.getInstance();
		uiIns.initUIAuto(this);
		String serverAddr = SettingUtil.getCurrentServerAddress();
		uiIns.setHttpHostList(Arrays.asList(serverAddr.endsWith("/") ? serverAddr.substring(0, serverAddr.length() - 1) : serverAddr));
		uiIns.getSharedPreferences().edit()
				.remove(KEY_PROJECT).putString(KEY_PROJECT, getPackageName())
				.remove(KEY_APP_NAME).putString(KEY_APP_NAME, getAppName())
				.remove(KEY_APP_CACHE_NAME_CONFIG_MAP).putString(KEY_APP_CACHE_NAME_CONFIG_MAP, NAME_MODE_MAP)
				.apply();

		UnitAutoApp unitIns = UnitAutoApp.getInstance();
		unitIns.setLoginPageClass(LoginActivity.class);

		// unitIns.setInterfaceClass(HttpManager.OnHttpResponseListener.class);
		// unitIns.setCallbackSign("onHttpResponse(int,String,Throwable)");

		// JSONObject request = unitIns.getLoginInvokeReq();
		// String clsName = LoginActivity.class.getName();
		// int index = clsName.lastIndexOf(".");
		// String pkg = clsName.substring(0, index);
		// String cls = clsName.substring(index + 1);
		// request.put(MethodUtil.KEY_PACKAGE, pkg); // LoginActivity.class.getPackageName()); // getPackageName());
		// request.put(MethodUtil.KEY_CLASS, cls); // LoginActivity.class.getSimpleName());
		// unitIns.setLoginInvokeReq(request);
	
		Thread.UncaughtExceptionHandler handler = Thread.currentThread().getUncaughtExceptionHandler();
		Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
				if (BuildConfig.DEBUG) {
					if (handler != null) {
						handler.uncaughtException(t, e);
					} else {
//						t.stop();
//						t.stop(e);
						throw new RuntimeException(e);
					}
				} else {
					e.printStackTrace();
					// TODO 上传到 Bugly 等日志平台
				}
			}
		});
	}

//	public static List<Object> getOutputList(int limit, int offset) {
//		return getOutputList(UIAutoApp.getInstance(), limit, offset);
//	}
//	public static List<Object> getOutputList(DemoApplication app, int limit, int offset) {
//		return UIAutoApp.getOutputList(UIAutoApp.getInstance(), limit, offset);
//	}
//
//	public static List<Object> getOutputList(UIAutoApp app, int limit, int offset) {
//		if (app == null) {
//			app = UIAutoApp.getInstance();
//		}
//		return UIAutoApp.getOutputList(app, limit, offset);
//	}
//
//	public static void prepareReplay(JSONArray eventList) {
//		UIAutoApp.getInstance().prepareReplay(eventList);
//	}

	@Override
	public void onConfigurationChanged(@NonNull Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		UIAutoApp.getInstance().onConfigurationChanged(newConfig);
	}

	/**获取当前用户id
	 * @return
	 */
	public long getCurrentUserId() {
		currentUser = getCurrentUser();
		Log.d(TAG, "getCurrentUserId  currentUserId = " + (currentUser == null ? "null" : currentUser.getId()));
		return currentUser == null ? 0 : currentUser.getId();
	}
	/**获取当前用户phone
	 * @return
	 */
	public String getCurrentUserPhone() {
		currentUser = getCurrentUser();
		return currentUser == null ? null : currentUser.getPhone();
	}


	private static User currentUser = null;
	public User getCurrentUser() {
		if (currentUser == null) {
			currentUser = DataManager.getInstance().getCurrentUser();
		}
		return currentUser;
	}

	public void saveCurrentUser(User user) {
		if (user == null) {
			Log.e(TAG, "saveCurrentUser  currentUser == null >> return;");
			return;
		}
		if (user.getId() <= 0 && StringUtil.isNotEmpty(user.getName(), true) == false) {
			Log.e(TAG, "saveCurrentUser  user.getId() <= 0" +
					" && StringUtil.isNotEmpty(user.getName(), true) == false >> return;");
			return;
		}

		if (currentUser != null && user.getId().equals(currentUser.getId())
				&& StringUtil.isNotEmpty(user.getPhone(), true) == false) {
			user.setPhone(currentUser.getPhone());
		}
		currentUser = user;
		DataManager.getInstance().saveCurrentUser(currentUser);
	}

	public void logout() {
		currentUser = null;
		DataManager.getInstance().saveCurrentUser(currentUser);
	}
	
	/**判断是否为当前用户
	 * @param userId
	 * @return
	 */
	public boolean isCurrentUser(long userId) {
		return DataManager.getInstance().isCurrentUser(userId);
	}

	public boolean isLoggedIn() {
		return getCurrentUserId() > 0;
	}



}
