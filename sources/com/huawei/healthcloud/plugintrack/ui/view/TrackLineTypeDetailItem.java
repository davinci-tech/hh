package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes8.dex */
public class TrackLineTypeDetailItem extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3805a;

    public TrackLineTypeDetailItem(Context context) {
        super(context);
        this.f3805a = null;
        b(context);
    }

    public TrackLineTypeDetailItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3805a = null;
        b(context);
    }

    private void b(Context context) {
        if (context == null) {
            return;
        }
        View.inflate(context, R.layout.track_share_nyx_swim_detail_item, this);
        this.f3805a = (HealthTextView) findViewById(R.id.track_share_line_type_detail_item_value);
    }
}
