package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import defpackage.hjm;

/* loaded from: classes4.dex */
public class PaceIntervalShare extends LinearLayout {
    private hjm b;
    private float[] c;
    private HealthColumnSystem d;

    public PaceIntervalShare(Context context, MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        super(context);
        c(context, motionPath, motionPathSimplify);
    }

    public PaceIntervalShare(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PaceIntervalShare(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private void c(Context context, MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (context == null) {
            LogUtil.h("Track_PaceIntervalShare", "context is null");
            return;
        }
        this.b = new hjm(motionPath, motionPathSimplify, context, motionPathSimplify.requestSportType());
        View inflate = View.inflate(context, R.layout.track_share_viewholder_pace_parent, this);
        this.d = new HealthColumnSystem(context);
        float[] c = this.b.c(motionPathSimplify.requestSportType(), this.d, true);
        this.c = c;
        this.b.bgO_(inflate, c, this.d, true);
        this.b.b();
    }
}
