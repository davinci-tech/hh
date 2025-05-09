package com.huawei.healthcloud.plugintrack.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes4.dex */
public class TrackShareDetailCustomTitleLayout extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private View f3807a;
    private HealthTextView b;
    private Context c;
    private View d;
    private HealthTextView e;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;

    public TrackShareDetailCustomTitleLayout(Context context) {
        super(context);
        this.e = null;
        this.b = null;
        this.i = null;
        this.j = null;
        this.h = null;
        a(context);
    }

    public TrackShareDetailCustomTitleLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.b = null;
        this.i = null;
        this.j = null;
        this.h = null;
        a(context);
    }

    private void a(Context context) {
        if (context == null) {
            return;
        }
        this.c = context;
        View.inflate(context, R.layout.track_share_detail_title_custom_view, this);
        this.e = (HealthTextView) findViewById(R.id.track_share_detail_title_chief_data);
        this.b = (HealthTextView) findViewById(R.id.track_share_detail_title_chief_unit);
        this.h = (HealthTextView) findViewById(R.id.track_share_detail_title_start_time);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.track_share_detail_title_username);
        this.i = healthTextView;
        healthTextView.setAutoTextInfo(9, 1, 1);
        if (LanguageUtil.f(context)) {
            HealthTextView healthTextView2 = this.e;
            healthTextView2.setTextSize(0, healthTextView2.getTextSize() * 0.75f);
            HealthTextView healthTextView3 = this.b;
            healthTextView3.setTextSize(0, healthTextView3.getTextSize() * 0.75f);
            HealthTextView healthTextView4 = this.h;
            healthTextView4.setTextSize(0, healthTextView4.getTextSize() * 0.75f);
        }
        if (LanguageUtil.b(context) && (this.b.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.b.getLayoutParams();
            layoutParams.gravity = 17;
            this.b.setLayoutParams(layoutParams);
        }
        this.d = findViewById(R.id.track_share_title_sport);
        this.f3807a = findViewById(R.id.track_share_title_right);
        this.j = (HealthTextView) findViewById(R.id.total_score);
        e();
    }

    private void e() {
        this.e.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.healthcloud.plugintrack.ui.view.TrackShareDetailCustomTitleLayout.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                TrackShareDetailCustomTitleLayout.this.e.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int height = (TrackShareDetailCustomTitleLayout.this.e.getHeight() - TrackShareDetailCustomTitleLayout.this.e.getBaseline()) - (TrackShareDetailCustomTitleLayout.this.h.getHeight() - TrackShareDetailCustomTitleLayout.this.h.getBaseline());
                int height2 = TrackShareDetailCustomTitleLayout.this.d.getHeight() + nsn.c(TrackShareDetailCustomTitleLayout.this.c, 13.8f);
                int height3 = TrackShareDetailCustomTitleLayout.this.f3807a.getHeight() + nsn.c(TrackShareDetailCustomTitleLayout.this.c, 28.0f) + height;
                if (height2 <= height3) {
                    height2 = height3;
                }
                if (TrackShareDetailCustomTitleLayout.this.d.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) TrackShareDetailCustomTitleLayout.this.d.getLayoutParams();
                    layoutParams.setMargins(0, height2 - TrackShareDetailCustomTitleLayout.this.d.getHeight(), 0, 0);
                    TrackShareDetailCustomTitleLayout.this.d.setLayoutParams(layoutParams);
                }
                if (TrackShareDetailCustomTitleLayout.this.f3807a.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) TrackShareDetailCustomTitleLayout.this.f3807a.getLayoutParams();
                    layoutParams2.setMargins(0, (height2 - TrackShareDetailCustomTitleLayout.this.f3807a.getHeight()) - height, 0, height);
                    TrackShareDetailCustomTitleLayout.this.f3807a.setLayoutParams(layoutParams2);
                }
            }
        });
    }

    public void b() {
        this.j.setVisibility(0);
        if (LanguageUtil.f(this.c)) {
            this.j.setTextSize(0, this.c.getResources().getDimension(R.dimen._2131363676_res_0x7f0a075c));
        }
    }

    public void setSportStartTime(String str) {
        this.h.setText(str);
    }

    public void setTextChiefData(CharSequence charSequence) {
        this.e.setText(charSequence);
    }

    public void setTextSportType(String str) {
        this.i.setText(str);
    }

    public void setTextChiefUnit(String str) {
        if (str == null || str.length() == 0) {
            this.b.setVisibility(8);
        } else {
            this.b.setText(str);
        }
    }

    public void setTextColor(int i) {
        this.e.setTextColor(i);
        this.i.setTextColor(i);
        this.h.setAlpha(1.0f);
        this.i.setAlpha(1.0f);
        this.j.setAlpha(1.0f);
    }
}
