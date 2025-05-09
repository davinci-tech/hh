package com.huawei.ui.commonui.linechart.view.timeperiod;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.R$styleable;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;

/* loaded from: classes6.dex */
class BloodSugarTimePeriodItemView extends FrameLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8908a;
    private int b;
    private int d;
    private HealthCheckBox e;

    BloodSugarTimePeriodItemView(Context context) {
        this(context, null);
    }

    BloodSugarTimePeriodItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    BloodSugarTimePeriodItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        cDK_(context, attributeSet, i);
    }

    public void setChecked(boolean z) {
        HealthCheckBox healthCheckBox = this.e;
        if (healthCheckBox == null || this.f8908a == null) {
            LogUtil.h("BloodSugarTimePeriodItemView", "setChecked, the mTimePeriodIconCheckBox or mTimePeriodTextView is null");
            return;
        }
        healthCheckBox.setChecked(z);
        if (z) {
            this.f8908a.setTextColor(this.b);
        } else {
            this.f8908a.setTextColor(this.d);
        }
    }

    public void setIconDrawable(int i) {
        HealthCheckBox healthCheckBox = this.e;
        if (healthCheckBox != null) {
            healthCheckBox.setBackgroundResource(i);
        }
    }

    public void setTimePeriodText(CharSequence charSequence) {
        HealthTextView healthTextView = this.f8908a;
        if (healthTextView != null) {
            healthTextView.setText(charSequence);
        }
    }

    private void cDK_(Context context, AttributeSet attributeSet, int i) {
        Drawable drawable;
        inflate(context, R.layout.layout_blood_sugar_time_period_item_view, this);
        this.e = (HealthCheckBox) findViewById(R.id.checkbox_time_period_icon);
        this.f8908a = (HealthTextView) findViewById(R.id.text_view_time_period_name);
        this.b = ContextCompat.getColor(context, R$color.blood_sugar_text_primary);
        this.d = ContextCompat.getColor(context, R$color.blood_sugar_text_secondary);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BloodSugarTimePeriodItemView, i, 0);
        if (obtainStyledAttributes.hasValue(R$styleable.BloodSugarTimePeriodItemView_timePeriodText)) {
            String string = obtainStyledAttributes.getString(R$styleable.BloodSugarTimePeriodItemView_timePeriodText);
            if (!TextUtils.isEmpty(string)) {
                setTimePeriodText(string);
            }
        }
        if (obtainStyledAttributes.hasValue(R$styleable.BloodSugarTimePeriodItemView_timePeriodIcon) && (drawable = obtainStyledAttributes.getDrawable(R$styleable.BloodSugarTimePeriodItemView_timePeriodIcon)) != null) {
            this.e.setButtonDrawable(drawable);
        }
        if (obtainStyledAttributes.hasValue(R$styleable.BloodSugarTimePeriodItemView_timePeriodChecked)) {
            setChecked(obtainStyledAttributes.getBoolean(R$styleable.BloodSugarTimePeriodItemView_timePeriodChecked, false));
        }
        obtainStyledAttributes.recycle();
    }
}
