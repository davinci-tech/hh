package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hji;
import defpackage.hjy;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes4.dex */
public class SkippingPerformanceView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f3794a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private HealthTextView e;
    private boolean g;
    private SkippingPerformanceRadarView h;
    private HealthTextView j;

    public SkippingPerformanceView(Context context) {
        this(context, null, 0);
    }

    public SkippingPerformanceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkippingPerformanceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        a(context);
    }

    private void a(final Context context) {
        if (context == null) {
            LogUtil.b("Track_SkippingPerformanceView", "context is null");
            return;
        }
        View inflate = View.inflate(getContext(), R.layout.track_skipping_performance_layout, this);
        this.j = (HealthTextView) nsy.cMd_(inflate, R.id.skipping_performance_title_tv);
        this.d = (HealthTextView) nsy.cMd_(inflate, R.id.skipping_performance_best_rank_tv);
        this.c = (HealthTextView) nsy.cMd_(inflate, R.id.skipping_performance_best_rank_des_tv);
        this.h = (SkippingPerformanceRadarView) nsy.cMd_(inflate, R.id.skipping_performance_radar_view);
        ImageView imageView = (ImageView) nsy.cMd_(inflate, R.id.skipping_performance_indicator_iv);
        this.f3794a = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.SkippingPerformanceView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.a("Track_SkippingPerformanceView", "click too fast");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    hji.d(9, context);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
        this.e = (HealthTextView) nsy.cMd_(inflate, R.id.skipping_performance_best_of_history_label);
        this.b = (HealthTextView) nsy.cMd_(inflate, R.id.skipping_performance_achievement_label);
    }

    public void setTitle(int i) {
        if (i != 0) {
            this.j.setText(i);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        this.j.setText(charSequence);
    }

    public void setIsSingleRecord(boolean z) {
        this.g = z;
    }

    public boolean e() {
        return this.g;
    }

    public SkippingPerformanceRadarView getRadarView() {
        return this.h;
    }

    public void a() {
        this.f3794a.setVisibility(8);
    }

    public void setData(float[] fArr, float[] fArr2, boolean z) {
        if (fArr == null || fArr2 == null || fArr.length != fArr2.length) {
            LogUtil.b("Track_SkippingPerformanceView", "input not allowed");
            return;
        }
        String[] strArr = new String[fArr.length];
        int length = fArr2.length;
        float[] fArr3 = new float[length];
        for (int i = 0; i < length; i++) {
            if (i == 1) {
                strArr[i] = UnitUtil.e(fArr[i], 1, 2);
            } else {
                strArr[i] = UnitUtil.e(fArr[i], 1, 0);
            }
            fArr3[i] = fArr2[i] / 100.0f;
        }
        this.h.setData(strArr, fArr3, z);
        b();
    }

    private void b() {
        if (this.h.c()) {
            this.e.setVisibility(0);
            this.b.setVisibility(8);
            int a2 = hjy.a((float[]) this.h.getHistoryData().second);
            setBestRankScore(a2, ((float[]) this.h.getHistoryData().second)[a2] * 100.0f);
            return;
        }
        if (this.h.d()) {
            this.e.setVisibility(0);
            this.b.setVisibility(0);
            if (e()) {
                this.b.setText(getContext().getString(R.string._2130847691_res_0x7f0227cb));
            } else {
                this.b.setText(getContext().getString(R.string._2130847662_res_0x7f0227ae));
            }
            int a3 = hjy.a((float[]) this.h.getData().second);
            setBestRankScore(a3, ((float[]) this.h.getData().second)[a3] * 100.0f);
            return;
        }
        LogUtil.a("Track_SkippingPerformanceView", "Other cases");
    }

    public void setBestRankScore(int i, float f) {
        if (f <= 0.0f) {
            LogUtil.h("Track_SkippingPerformanceView", "The score is less than or equal to 0.");
            return;
        }
        String e = UnitUtil.e(f, 2, 1);
        String e2 = hjy.e(i);
        String format = String.format(Locale.ROOT, getContext().getString(R.string._2130847660_res_0x7f0227ac), e);
        if (!TextUtils.isEmpty(e2)) {
            format = String.format(Locale.ROOT, getContext().getString(R.string._2130847862_res_0x7f022876), e2, format);
        }
        this.d.setText(bjm_(e, format, (int) getResources().getDimension(R.dimen._2131362955_res_0x7f0a048b), ContextCompat.getColor(getContext(), R.color._2131296651_res_0x7f09018b)));
        this.c.setText(getContext().getString(R.string._2130847861_res_0x7f022875));
    }

    private SpannableString bjm_(String str, String str2, int i, int i2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.h("Track_SkippingPerformanceView", "acquireSpannableStrUtil: content/contentStr empty");
            return new SpannableString("");
        }
        SpannableString spannableString = new SpannableString(str2);
        int indexOf = str2.indexOf(str);
        if (indexOf < 0) {
            return spannableString;
        }
        spannableString.setSpan(new StyleSpan(1), indexOf, str.length() + indexOf, 18);
        spannableString.setSpan(new ForegroundColorSpan(i2), indexOf, str.length() + indexOf, 18);
        spannableString.setSpan(new AbsoluteSizeSpan(i), indexOf, str.length() + indexOf, 18);
        return spannableString;
    }
}
