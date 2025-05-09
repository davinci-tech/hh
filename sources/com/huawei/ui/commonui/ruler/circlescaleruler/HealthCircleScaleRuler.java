package com.huawei.ui.commonui.ruler.circlescaleruler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.imageview.HealthImageView;
import defpackage.nqg;
import defpackage.nsf;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes6.dex */
public class HealthCircleScaleRuler extends RelativeLayout {
    private HealthImageView b;
    private HealthTextView c;
    private HealthTextView d;
    private CircleScaleRuler e;

    public HealthCircleScaleRuler(Context context) {
        super(context);
        a();
    }

    public HealthCircleScaleRuler(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HealthCircleScaleRuler(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        View cKr_ = nsf.cKr_(BaseApplication.e(), R.layout.layout_health_circle_scale_ruler, this);
        if (cKr_ == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "init view is null");
            return;
        }
        this.e = (CircleScaleRuler) cKr_.findViewById(R.id.layout_health_circle_scale_ruler);
        this.c = (HealthTextView) cKr_.findViewById(R.id.layout_health_circle_scale_ruler_title);
        this.b = (HealthImageView) cKr_.findViewById(R.id.layout_health_circle_scale_ruler_icon);
        this.d = (HealthTextView) cKr_.findViewById(R.id.layout_health_circle_scale_ruler_value);
    }

    public void setCircleScaleRulerData(nqg nqgVar) {
        CircleScaleRuler circleScaleRuler = this.e;
        if (circleScaleRuler == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "setCircleScaleRulerData mCircleScaleRuler is null");
        } else {
            circleScaleRuler.setCircleScaleRulerData(nqgVar);
        }
    }

    public void setTitle(CharSequence charSequence) {
        HealthTextView healthTextView = this.c;
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "setTitle mTitle is null");
        } else {
            healthTextView.setText(charSequence);
        }
    }

    public void setIconOnClickListener(View.OnClickListener onClickListener) {
        HealthImageView healthImageView = this.b;
        if (healthImageView == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "setIconOnClickListener mIcon is null");
        } else {
            healthImageView.setOnClickListener(onClickListener);
        }
    }

    public void setValue(CharSequence charSequence) {
        HealthTextView healthTextView = this.d;
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "setValue mValue is null");
        } else {
            healthTextView.setText(charSequence);
        }
    }

    public void setValueTextDirection(int i) {
        HealthTextView healthTextView = this.d;
        if (healthTextView == null) {
            ReleaseLogUtil.a("HealthCircleScaleRuler", "setValueTextDirection mValue is null");
        } else {
            healthTextView.setTextDirection(i);
        }
    }
}
