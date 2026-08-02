/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package zuo.biao.library.util;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**图片加载工具类
 * @author Lemon
 * @use ImageLoaderUtil.loadImage(...)
 */
public class ImageUtil {
	private static final String TAG = "ImageLoaderUtil";
	
	/** 存储权限请求码 */
	public static final int REQUEST_STORAGE_PERMISSION = 1001;

	/**加载图片
	 * 加载小图应再调用该方法前使用getSmallUri处理uri
	 * type = TYPE_DEFAULT
	 * @param iv
	 * @param uri 网址url或本地路径path
	 */
	public static void loadImage(ImageView iv, String uri) {
		loadImage(iv, uri, TYPE_DEFAULT);
	}

	public static final int TYPE_DEFAULT = 0;//矩形
	public static final int TYPE_ROUND_CORNER = 1;//圆角矩形
	public static final int TYPE_OVAL = 2;//圆形
	/**加载图片
	 * 加载小图应再调用该方法前使用getSmallUri处理uri
	 * @param type 图片显示类型
	 * @param iv
	 * @param uri 网址url或本地路径path
	 */
	public static void loadImage(final ImageView iv, String uri, final int type) {
		if (iv == null) {
			Log.i(TAG, "loadImage  iv == null >> return;");
			return;
		}
		Log.i(TAG, "loadImage  iv" + (iv == null ? "==" : "!=") + "null; uri=" + uri);

		uri = getCorrectUri(uri);
		
//		// 检查是否是本地文件路径，如果是则检查权限
//		if (isLocalFileUri(uri) && iv.getContext() instanceof Activity) {
//			Activity activity = (Activity) iv.getContext();
//			if (! CommonUtil.checkAndRequestStoragePermission(activity, REQUEST_STORAGE_PERMISSION)) {
//				Log.w(TAG, "Storage permission not granted, cannot load local image: " + uri);
//				return; // 权限未授予，不加载图片
//			}
//		}

		Glide.with(iv.getContext()).load(uri)
				.into(iv);

	}


	public static final String FILE_PATH_PREFIX = StringUtil.FILE_PATH_PREFIX;

	/**获取可用的uri
	 * @param uri
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getCorrectUri(String uri) {
		Log.i(TAG, "<<<<  getCorrectUri  uri = " + uri);
		uri = StringUtil.getNoBlankString(uri);

		if (uri.toLowerCase().startsWith(StringUtil.HTTP) == false) {
			uri = uri.startsWith(FILE_PATH_PREFIX) ? uri : FILE_PATH_PREFIX + uri;
		}

		Log.i(TAG, "getCorrectUri  return uri = " + uri + " >>>>> ");
		return uri;
	}

}
