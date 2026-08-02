package uigo.x;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**全局替换 layout.xml 中标签源码？还不如运行时自动全局替换
 * @author Lemon
 */
public class AppCompatImageView extends androidx.appcompat.widget.AppCompatImageView implements StyleGetter, ImageGetter, BackgroundGetter {

    private int styleResId;
    private String styleResName;
    private int imageResId;
    private String imageResName;
    private int backgroundResId;
    private String backgroundResName;

    public AppCompatImageView(@NonNull Context context) {
        super(context);
    }

    public AppCompatImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            styleResId = attrs.getStyleAttribute();
        }
    }

    public AppCompatImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (attrs != null) {
            styleResId = attrs.getStyleAttribute();
        }
    }

    @Override
    public String getStyleName() {
        if (StringUtil.isEmpty(styleResName)) {
            styleResName = styleResId <= 0 ? null : getResources().getResourceEntryName(styleResId);
        }
        return styleResName;
    }

    @Override
    public void setImageResource(int resId) {
        this.imageResId = resId;
        super.setImageResource(resId);
    }

    @Override
    public void setBackgroundResource(int resId) {
        this.backgroundResId = resId;
        super.setBackgroundResource(resId);
    }

    @Override
    public String getImageResName() {
        if (StringUtil.isEmpty(imageResName)) {
            imageResName = imageResId <= 0 ? null : getResources().getResourceEntryName(imageResId);
        }
        return imageResName;
    }

    @Override
    public String getBackgroundResName() {
        if (StringUtil.isEmpty(backgroundResName)) {
            backgroundResName = backgroundResId <= 0 ? null : getResources().getResourceEntryName(backgroundResId);
        }
        return backgroundResName;
    }

}