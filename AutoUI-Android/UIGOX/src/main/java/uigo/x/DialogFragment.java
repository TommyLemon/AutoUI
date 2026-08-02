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
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**通用对话框类
 * @author Lemon
 * @use 把业务代码中 androidx.fragment.app.DialogFragment 换成 uigo.x.DialogFragment
 */
public class DialogFragment extends androidx.fragment.app.DialogFragment {

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

    /**
     * Constructor used by the default {@link FragmentFactory}. You must
     * {@link FragmentManager#setFragmentFactory(FragmentFactory) set a custom FragmentFactory}
     * if you want to use a non-default constructor to ensure that your constructor
     * is called when the fragment is re-instantiated.
     *
     * <p>It is strongly recommended to supply arguments with {@link #setArguments}
     * and later retrieved by the Fragment with {@link #getArguments}. These arguments
     * are automatically saved and restored alongside the Fragment.
     *
     * <p>Applications should generally not implement a constructor. Prefer
     * {@link #onAttach(Context)} instead. It is the first place application code can run where
     * the fragment is ready to be used - the point where the fragment is actually associated with
     * its context.
     */
    public DialogFragment() {
        super();
    }

    /**
     * Alternate constructor that can be called from your default, no argument constructor to
     * provide a default layout that will be inflated by
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     *
     * <pre class="prettyprint">
     * class MyDialogFragment extends DialogFragment {
     *   public MyDialogFragment() {
     *     super(R.layout.dialog_fragment_main);
     *   }
     * }
     * </pre>
     *
     * You must
     * {@link FragmentManager#setFragmentFactory(FragmentFactory) set a custom FragmentFactory}
     * if you want to use a non-default constructor to ensure that your constructor is called
     * when the fragment is re-instantiated.
     *
     * @see #DialogFragment()
     * @see #onCreateView(LayoutInflater, ViewGroup, Bundle)
     */
    public DialogFragment(@LayoutRes int contentLayoutId) {
        super(contentLayoutId);
    }


}
