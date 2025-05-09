package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;

/* loaded from: classes6.dex */
public class SleepShareBriefView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9978a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView d;
    private RelativeLayout e;
    private HealthTextView f;
    private HealthTextView g;
    private RelativeLayout h;
    private HealthTextView i;
    private ViewGroup j;

    public SleepShareBriefView(Context context) {
        super(context);
        d();
    }

    public SleepShareBriefView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d();
    }

    public SleepShareBriefView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        d();
    }

    private void d() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.item_for_share_sleep_brief_layout, (ViewGroup) this, true);
        this.e = (RelativeLayout) inflate.findViewById(R.id.sleep_scoring_area);
        this.c = (HealthTextView) inflate.findViewById(R.id.sleep_scoring_text);
        this.d = (HealthTextView) inflate.findViewById(R.id.sleep_scoring_desc_quality);
        this.i = (HealthTextView) inflate.findViewById(R.id.sleep_scoring_unit);
        this.j = (ViewGroup) inflate.findViewById(R.id.sleep_share_brief_star_container);
        this.g = (HealthTextView) inflate.findViewById(R.id.sleep_scoring_desc);
        this.h = (RelativeLayout) inflate.findViewById(R.id.sleep_total_time);
        this.f = (HealthTextView) inflate.findViewById(R.id.common_sleep_sleep_hour_time);
        this.b = (HealthTextView) inflate.findViewById(R.id.sleep_efficiency_desc);
        this.f9978a = (RelativeLayout) inflate.findViewById(R.id.sleep_share_brief_bottom_area);
    }

    public void setScoring(String str, String str2) {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("SleepShareBriefView", "setScoring failed cause score is not a valid number! ", str);
            i = 0;
        }
        this.h.setVisibility(8);
        this.c.setText(str);
        this.i.setVisibility(0);
        this.i.setText(getContext().getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, i));
        this.j.setVisibility(0);
        setStarNum(i);
        this.g.setText(str2);
    }

    private void setStarNum(int i) {
        int i2 = (i + 9) / 10;
        int i3 = i2 < 10 ? i2 : 10;
        if (i3 <= 0) {
            i3 = 0;
        }
        int i4 = i3 / 2;
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable._2131429905_res_0x7f0b0a11);
        Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable._2131429978_res_0x7f0b0a5a);
        Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable._2131429960_res_0x7f0b0a48);
        for (int i5 = 0; i5 < i4; i5++) {
            dxf_(this.j.getChildAt(i5), drawable3);
        }
        for (int i6 = i4; i6 < 5; i6++) {
            dxf_(this.j.getChildAt(i6), drawable);
        }
        if (i3 % 2 == 1) {
            dxf_(this.j.getChildAt(i4), drawable2);
        }
    }

    private void dxf_(View view, Drawable drawable) {
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        }
    }

    public void setStatus(String str) {
        this.i.setVisibility(8);
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.d.setVisibility(0);
        this.b.setVisibility(0);
        this.c.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen._2131362721_res_0x7f0a03a1));
        this.c.setText(str);
        this.g.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d));
        this.d.setText(getContext().getResources().getString(R$string.IDS_sleep_quality));
    }

    public void setSleepEfficiency(String str, String str2) {
        this.i.setVisibility(8);
        this.j.setVisibility(8);
        this.h.setVisibility(8);
        this.d.setVisibility(0);
        this.b.setVisibility(0);
        ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).addRule(14);
            this.c.setLayoutParams(layoutParams);
        }
        this.c.setText(str2);
        this.d.setText(getContext().getResources().getString(R$string.IDS_sleep_quality));
        this.b.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen._2131363094_res_0x7f0a0516));
        this.b.setText(str);
    }

    public void setSleepTime(String str) {
        this.e.setVisibility(8);
        this.j.setVisibility(8);
        if (!TextUtils.isEmpty(str)) {
            ViewGroup.LayoutParams layoutParams = this.f9978a.getLayoutParams();
            if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                layoutParams2.removeRule(3);
                this.f9978a.setLayoutParams(layoutParams2);
            }
            ViewGroup.LayoutParams layoutParams3 = this.h.getLayoutParams();
            if (layoutParams3 instanceof RelativeLayout.LayoutParams) {
                ((RelativeLayout.LayoutParams) layoutParams3).addRule(3, R.id.sleep_share_brief_bottom_area);
                this.h.setLayoutParams(layoutParams3);
            }
            this.f.setText(str);
        }
        this.f.setText(str);
        this.g.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen._2131362973_res_0x7f0a049d));
        this.g.setText(getContext().getResources().getString(R$string.IDS_fitness_total_sleep_data_title));
    }
}
