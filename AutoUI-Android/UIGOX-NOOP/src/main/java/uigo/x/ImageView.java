package uigo.x;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

/**全局替换 layout.xml 中标签源码？还不如运行时自动全局替换
 * @author Lemon
 */
@SuppressLint("AppCompatCustomView")
public class ImageView extends android.widget.ImageView {

    public ImageView(Context context) {
        super(context);
    }

    public ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}