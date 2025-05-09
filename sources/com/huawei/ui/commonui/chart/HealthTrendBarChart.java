package com.huawei.ui.commonui.chart;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class HealthTrendBarChart extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8790a;
    private HealthTrendBar b;
    private HealthTrendBar c;
    private HealthTextView d;
    private b e;
    private HealthTextView f;
    private b g;
    private HealthTextView i;

    public HealthTrendBarChart(Context context) {
        super(context);
        this.e = new b();
        this.g = new b();
        a();
    }

    public HealthTrendBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new b();
        this.g = new b();
        a();
    }

    public HealthTrendBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new b();
        this.g = new b();
        a();
    }

    private void a() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.health_trend_bar_chart_layout, this);
        this.c = (HealthTrendBar) inflate.findViewById(R.id.primary_bar);
        this.b = (HealthTrendBar) inflate.findViewById(R.id.secondary_bar);
        this.f8790a = (HealthTextView) inflate.findViewById(R.id.primary_bar_inner_text);
        this.d = (HealthTextView) inflate.findViewById(R.id.primary_bar_outer_text);
        this.f = (HealthTextView) inflate.findViewById(R.id.secondary_bar_inner_text);
        this.i = (HealthTextView) inflate.findViewById(R.id.secondary_bar_outer_text);
        post(new Runnable() { // from class: com.huawei.ui.commonui.chart.HealthTrendBarChart.5
            @Override // java.lang.Runnable
            public void run() {
                if (HealthTrendBarChart.this.e == null && HealthTrendBarChart.this.g == null) {
                    return;
                }
                HealthTrendBarChart.this.b();
            }
        });
    }

    public void setValues(b bVar, b bVar2) {
        this.e = bVar;
        this.g = bVar2;
        if (getWidth() > 0) {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003f, code lost:
    
        if (r0 > r3) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b() {
        /*
            r6 = this;
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r0 = r6.e
            boolean r0 = r0.b()
            r1 = 0
            if (r0 == 0) goto L1c
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r0 = r6.g
            boolean r0 = r0.b()
            if (r0 == 0) goto L1c
            com.huawei.ui.commonui.chart.HealthTrendBar r0 = r6.c
            r0.setLength(r1, r1)
            com.huawei.ui.commonui.chart.HealthTrendBar r0 = r6.b
            r0.setLength(r1, r1)
            goto L7b
        L1c:
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r0 = r6.e
            int r0 = com.huawei.ui.commonui.chart.HealthTrendBarChart.b.c(r0)
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r2 = r6.e
            int r2 = com.huawei.ui.commonui.chart.HealthTrendBarChart.b.a(r2)
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r3 = r6.g
            int r3 = com.huawei.ui.commonui.chart.HealthTrendBarChart.b.c(r3)
            com.huawei.ui.commonui.chart.HealthTrendBarChart$b r4 = r6.g
            int r4 = com.huawei.ui.commonui.chart.HealthTrendBarChart.b.a(r4)
            r5 = 1
            if (r0 == r3) goto L42
            int r2 = java.lang.Math.min(r0, r3)
            int r4 = java.lang.Math.max(r0, r3)
            if (r0 <= r3) goto L52
            goto L4e
        L42:
            int r0 = java.lang.Math.min(r2, r4)
            int r3 = java.lang.Math.max(r2, r4)
            if (r2 <= r4) goto L50
            r2 = r0
            r4 = r3
        L4e:
            r1 = r5
            goto L52
        L50:
            r2 = r0
            r4 = r3
        L52:
            if (r4 == 0) goto L58
            float r0 = (float) r2
            float r2 = (float) r4
            float r0 = r0 / r2
            goto L59
        L58:
            r0 = 0
        L59:
            int r2 = r6.getWidth()
            if (r1 == 0) goto L60
            goto L63
        L60:
            float r2 = (float) r2
            float r2 = r2 * r0
            int r2 = (int) r2
        L63:
            if (r1 != 0) goto L6a
            int r0 = r6.getWidth()
            goto L71
        L6a:
            int r1 = r6.getWidth()
            float r1 = (float) r1
            float r1 = r1 * r0
            int r0 = (int) r1
        L71:
            com.huawei.ui.commonui.chart.HealthTrendBar r1 = r6.c
            r1.setLength(r2, r5)
            com.huawei.ui.commonui.chart.HealthTrendBar r1 = r6.b
            r1.setLength(r0, r5)
        L7b:
            r6.e()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.commonui.chart.HealthTrendBarChart.b():void");
    }

    private void e() {
        SpannableString cxG_ = cxG_(this.e.f8791a);
        if (this.e.a() > 0) {
            this.f8790a.setVisibility(0);
            this.d.setVisibility(8);
            this.f8790a.setText(cxG_);
        } else {
            this.f8790a.setVisibility(8);
            this.d.setVisibility(0);
            this.d.setText(cxG_);
        }
        if (LanguageUtil.b(BaseApplication.e())) {
            this.f8790a.setLayoutDirection(1);
        }
        SpannableString cxG_2 = cxG_(this.g.f8791a);
        if (this.g.a() > 0) {
            this.f.setVisibility(0);
            this.i.setVisibility(8);
            this.f.setText(cxG_2);
        } else {
            this.f.setVisibility(8);
            this.i.setVisibility(0);
            this.i.setText(cxG_2);
        }
    }

    private SpannableString cxG_(String str) {
        if ("--/-- --".equals(str)) {
            return new SpannableString("--");
        }
        SpannableString spannableString = new SpannableString(str);
        if (str.contains("/")) {
            spannableString.setSpan(new AbsoluteSizeSpan(12, true), str.indexOf("/"), str.length(), 33);
        }
        return spannableString;
    }

    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private String f8791a;
        private int b;
        private int c;

        public b() {
        }

        public b(int i, int i2, String str) {
            this.c = i;
            this.b = i2;
            this.f8791a = str;
        }

        public b(int i, String str) {
            this(i, 0, str);
        }

        public boolean b() {
            return this.c <= 0 && this.b <= 0;
        }

        public int a() {
            return this.c;
        }
    }
}
