package uigo.x;

import android.content.Context;
import android.util.AttributeSet;

/**全局替换 layout.xml 中标签源码？还不如运行时自动全局替换
 * @author Lemon
 */
public class AppCompatImageView extends androidx.appcompat.widget.AppCompatImageView {

    public AppCompatImageView(Context context) {
        super(context);
    }

    public AppCompatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppCompatImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}