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

/**通用列表对话框类
 * @author Lemon
 * @use 把业务代码中 com.google.android.material.bottomsheet.BottomSheetDialogFragment 换成 uigo.x.BottomSheetDialogFragment
 */
public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {

    public void onUIAutoDialogShow() {}

    public void onUIAutoDialogDismiss() {}

    public BottomSheetDialogFragment() {
        super();
    }

    public BottomSheetDialogFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

}
