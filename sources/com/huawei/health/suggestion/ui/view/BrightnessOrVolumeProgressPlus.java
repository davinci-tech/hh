package com.huawei.health.suggestion.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nrz;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class BrightnessOrVolumeProgressPlus extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3416a;
    private ImageView b;
    private BrightnessOrVolumeProgress d;

    public BrightnessOrVolumeProgressPlus(Context context) {
        super(context);
    }

    public BrightnessOrVolumeProgressPlus(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightnessOrVolumeProgressPlus(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater from = LayoutInflater.from(getContext());
        setGravity(17);
        from.inflate(R.layout.sug_coach_brightorvolumeplus, this);
        this.d = (BrightnessOrVolumeProgress) findViewById(R.id.sug_round_tp);
        this.b = (ImageView) findViewById(R.id.iv_center);
        this.f3416a = (HealthTextView) findViewById(R.id.tv_setting);
    }

    public void setProgressMax(float f) {
        this.d.setMax(f);
    }

    public void setProgress(float f) {
        this.d.setProgress(f);
    }

    public void a(int i) {
        if (LanguageUtil.bc(getContext())) {
            this.b.setImageDrawable(nrz.cKn_(getContext(), i));
        } else {
            this.b.setImageResource(i);
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3416a.setText(str);
    }
}
