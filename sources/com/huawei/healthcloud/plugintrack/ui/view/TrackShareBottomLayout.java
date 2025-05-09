package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;

/* loaded from: classes8.dex */
public class TrackShareBottomLayout extends RelativeLayout {
    public TrackShareBottomLayout(Context context) {
        super(context);
        c(context);
    }

    public TrackShareBottomLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    private void c(Context context) {
        if (context == null) {
            return;
        }
        View.inflate(context, R.layout.track_share_bottom_layout, this);
    }
}
