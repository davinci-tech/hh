package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class TrainingResultsView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3811a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthDivider e;

    public TrainingResultsView(Context context) {
        super(context);
        e(context);
    }

    public TrainingResultsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        e(context);
    }

    public TrainingResultsView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        e(context);
    }

    private void e(Context context) {
        if (context == null) {
            ReleaseLogUtil.c("Track_TrainingResultsView", "context is null");
            return;
        }
        View inflate = View.inflate(context, R.layout.track_detail_training_results, this);
        ((HealthSubHeader) inflate.findViewById(R.id.sub_header_training)).setPaddingStartEnd(0.0f, 0.0f);
        this.f3811a = (HealthTextView) inflate.findViewById(R.id.training_course_name);
        this.b = (HealthTextView) inflate.findViewById(R.id.training_course_difficulty);
        this.c = (HealthTextView) inflate.findViewById(R.id.training_course_duration);
        this.d = (HealthTextView) inflate.findViewById(R.id.training_course_finish);
        this.e = (HealthDivider) inflate.findViewById(R.id.health_divider_training_result);
    }

    public void setCourseName(String str) {
        HealthTextView healthTextView = this.f3811a;
        if (healthTextView == null) {
            LogUtil.h("Track_TrainingResultsView", "mCourseName is null");
        } else {
            healthTextView.setText(str);
        }
    }

    public void setCourseDifficulty(String str) {
        HealthTextView healthTextView = this.b;
        if (healthTextView == null) {
            LogUtil.h("Track_TrainingResultsView", "mCourseName is null");
        } else {
            healthTextView.setText(str);
        }
    }

    public void setCourseDuration(String str) {
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            LogUtil.h("Track_TrainingResultsView", "mCourseName is null");
        } else {
            healthTextView.setText(str);
        }
    }

    public void setCourseFinish(float f) {
        if (f <= 0.0f) {
            LogUtil.h("Track_TrainingResultsView", "courseFinish is 0");
            return;
        }
        HealthTextView healthTextView = this.d;
        if (healthTextView == null) {
            LogUtil.h("Track_TrainingResultsView", "mCourseName is null");
        } else {
            healthTextView.setText(BaseApplication.getContext().getString(R.string._2130840211_res_0x7f020a93, UnitUtil.e(f, 2, 0)));
        }
    }

    public void setHealthDivider(boolean z) {
        if (z) {
            this.e.setVisibility(8);
        }
    }
}
