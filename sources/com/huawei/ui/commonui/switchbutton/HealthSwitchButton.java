package com.huawei.ui.commonui.switchbutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.uikit.phone.hwswitch.widget.HwSwitch;
import health.compact.a.CommonUtil;

/* loaded from: classes6.dex */
public class HealthSwitchButton extends HwSwitch {
    public HealthSwitchButton(Context context) {
        super(context);
        setTrackDrawable(context);
    }

    public HealthSwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setTrackDrawable(context);
    }

    public HealthSwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setTrackDrawable(context);
    }

    private void setTrackDrawable(Context context) {
        if (CommonUtil.bg()) {
            Drawable drawable = ContextCompat.getDrawable(BaseApplication.e(), R$drawable.hwswitch_selector_track_emui);
            if (drawable == null) {
                LogUtil.h("HealthSwitchButton", "trackDrawable is null");
                return;
            }
            if (drawable instanceof DrawableContainer) {
                Drawable.ConstantState constantState = ((DrawableContainer) drawable).getConstantState();
                if (constantState instanceof DrawableContainer.DrawableContainerState) {
                    DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) constantState;
                    Drawable[] children = drawableContainerState.getChildren();
                    if (children == null) {
                        LogUtil.h("HealthSwitchButton", "containerState children is null");
                        return;
                    }
                    int childCount = drawableContainerState.getChildCount();
                    if (childCount > children.length) {
                        LogUtil.h("HealthSwitchButton", "invalid childCount");
                        return;
                    }
                    for (int i = 0; i < childCount; i++) {
                        if (children[i] == null) {
                            LogUtil.h("HealthSwitchButton", "containerState child is null");
                            return;
                        }
                    }
                    LogUtil.a("HealthSwitchButton", "valid trackDrawable childCount: ", Integer.valueOf(childCount));
                }
            }
            setTrackDrawable(drawable);
        }
    }
}
