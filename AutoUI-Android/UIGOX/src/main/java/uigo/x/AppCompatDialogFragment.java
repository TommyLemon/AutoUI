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

import android.app.Dialog;
import android.content.DialogInterface;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**通用对话框类
 * @author Lemon
 * @use 把业务代码中 androidx.appcompat.app.AppCompatDialogFragment 换成 uigo.x.AppCompatDialogFragment
 */
public class AppCompatDialogFragment extends androidx.appcompat.app.AppCompatDialogFragment {

    public void onUIAutoDialogShow() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIAutoApp.getInstance().onUIAutoDialogShow(dialog);
        }
    }

    public void onUIAutoDialogDismiss() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            UIAutoApp.getInstance().onUIAutoDialogDismiss(dialog);
        }
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
        onUIAutoDialogShow();
    }

    @Override
    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        int backStackId = super.show(transaction, tag);
        onUIAutoDialogShow();
        return backStackId;
    }

    @Override
    public void showNow(@NonNull FragmentManager manager, @Nullable String tag) {
        super.showNow(manager, tag);
        onUIAutoDialogShow();
    }

    @Override
    public void onResume() {
        super.onResume();
        onUIAutoDialogShow();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        onUIAutoDialogDismiss();
    }

    @Override
    public void dismissNow() {
        super.dismissNow();
        onUIAutoDialogDismiss();
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
        onUIAutoDialogDismiss();
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        onUIAutoDialogDismiss();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onUIAutoDialogDismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onUIAutoDialogDismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onUIAutoDialogDismiss();
    }


    public AppCompatDialogFragment() {
        super();
    }

    /** {@inheritDoc} */
    public AppCompatDialogFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }

}
