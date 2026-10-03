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

import android.content.Context;

/**通用对话框类
 * @author Lemon
 * @use 把业务代码中 android.app.ProgressDialog 换成 uigo.x.ProgressDialog
 */
@Deprecated
public class ProgressDialog extends android.app.ProgressDialog {

	public ProgressDialog(Context context) {
		super(context);
	}

	public ProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static ProgressDialog show(Context context, CharSequence title,
												  CharSequence message) {
		return show(context, title, message, false);
	}

	/**
	 * Creates and shows a ProgressDialog.
	 *
	 * @param context the parent context
	 * @param title the title text for the dialog's window
	 * @param message the text to be displayed in the dialog
	 * @param indeterminate true if the dialog should be {@link #setIndeterminate(boolean)
	 *        indeterminate}, false otherwise
	 * @return the ProgressDialog
	 */
	public static ProgressDialog show(Context context, CharSequence title,
												  CharSequence message, boolean indeterminate) {
		return show(context, title, message, indeterminate, false, null);
	}

	/**
	 * Creates and shows a ProgressDialog.
	 *
	 * @param context the parent context
	 * @param title the title text for the dialog's window
	 * @param message the text to be displayed in the dialog
	 * @param indeterminate true if the dialog should be {@link #setIndeterminate(boolean)
	 *        indeterminate}, false otherwise
	 * @param cancelable true if the dialog is {@link #setCancelable(boolean) cancelable},
	 *        false otherwise
	 * @return the ProgressDialog
	 */
	public static ProgressDialog show(Context context, CharSequence title,
												  CharSequence message, boolean indeterminate, boolean cancelable) {
		return show(context, title, message, indeterminate, cancelable, null);
	}

	/**
	 * Creates and shows a ProgressDialog.
	 *
	 * @param context the parent context
	 * @param title the title text for the dialog's window
	 * @param message the text to be displayed in the dialog
	 * @param indeterminate true if the dialog should be {@link #setIndeterminate(boolean)
	 *        indeterminate}, false otherwise
	 * @param cancelable true if the dialog is {@link #setCancelable(boolean) cancelable},
	 *        false otherwise
	 * @param cancelListener the {@link #setOnCancelListener(OnCancelListener) listener}
	 *        to be invoked when the dialog is canceled
	 * @return the ProgressDialog
	 */
	public static ProgressDialog show(Context context, CharSequence title,
												  CharSequence message, boolean indeterminate,
												  boolean cancelable, OnCancelListener cancelListener) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.show();
		return dialog;
	}

}

