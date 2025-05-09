package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dpg;

/* loaded from: classes.dex */
public class RegularWorkAndRestTimeView extends FrameLayout {
    private HealthTextView b;
    private HealthTextView d;

    public RegularWorkAndRestTimeView(Context context) {
        super(context);
        d(context);
    }

    public RegularWorkAndRestTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d(context);
    }

    public RegularWorkAndRestTimeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d(context);
    }

    private void d(Context context) {
        LayoutInflater.from(context).inflate(R.layout.sleep_task_regular_work_and_rest_time, this);
        this.d = (HealthTextView) findViewById(R.id.sleep_time);
        this.b = (HealthTextView) findViewById(R.id.wakeup_time);
    }

    public void setupSleepWakeupTime(long j, long j2, boolean z, boolean z2) {
        b(j, z, this.d);
        b(j2, z2, this.b);
    }

    private void b(long j, boolean z, HealthTextView healthTextView) {
        if (j == 0) {
            return;
        }
        healthTextView.setText(dpg.b(j));
        Drawable drawable = ContextCompat.getDrawable(BaseApplication.e(), z ? R.drawable._2131430319_res_0x7f0b0baf : R.drawable._2131430318_res_0x7f0b0bae);
        if (drawable != null) {
            int dimension = (int) BaseApplication.e().getResources().getDimension(R.dimen._2131362914_res_0x7f0a0462);
            drawable.setBounds(0, 0, dimension, dimension);
        }
        healthTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }
}
