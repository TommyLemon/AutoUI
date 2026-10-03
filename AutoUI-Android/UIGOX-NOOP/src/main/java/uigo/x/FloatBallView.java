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

import android.annotation.SuppressLint;
import android.content.Context;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**悬浮球 View
 * @author Lemon
 */
@SuppressLint("AppCompatCustomView")
public class FloatBallView extends ImageView {

  public FloatBallView setExtraOnTouchListener(View.OnTouchListener extraOnTouchListener) {
    return this;
  }

  public FloatBallView(Context context) {
    super(context);
  }

  public FloatBallView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public FloatBallView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

}
