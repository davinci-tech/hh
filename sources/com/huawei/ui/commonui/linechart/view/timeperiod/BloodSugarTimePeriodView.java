package com.huawei.ui.commonui.linechart.view.timeperiod;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView;
import defpackage.noh;
import java.util.ArrayList;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class BloodSugarTimePeriodView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8909a;
    private ArrayList<BloodSugarTimePeriod> b;
    private Context c;
    private LinearLayout d;
    private OnTimePeriodItemChangedListener e;

    public interface OnTimePeriodItemChangedListener {
        void onTimePeriodItemChanged(int i, BloodSugarTimePeriod bloodSugarTimePeriod);
    }

    public BloodSugarTimePeriodView(Context context) {
        this(context, null);
    }

    public BloodSugarTimePeriodView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BloodSugarTimePeriodView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList<>(4);
        this.c = context;
        LogUtil.c("BloodSugarTimePeriodView", "BloodSugarTimePeriodView ", context);
        c(context);
    }

    public void e(long j) {
        d(j, 0);
    }

    public void d(final long j, final int i) {
        post(new Runnable() { // from class: npr
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarTimePeriodView.this.a(j, i);
            }
        });
    }

    public /* synthetic */ void a(long j, int i) {
        if (this.c == null) {
            LogUtil.h("BloodSugarTimePeriodView", "notify, the context is invalid");
        } else {
            b(j, i);
        }
    }

    public void setOnTimePeriodItemChangedListener(OnTimePeriodItemChangedListener onTimePeriodItemChangedListener) {
        this.e = onTimePeriodItemChangedListener;
    }

    private void c(Context context) {
        setOrientation(1);
        setMinimumHeight(context.getResources().getDimensionPixelSize(R.dimen._2131362917_res_0x7f0a0465));
        inflate(context, R.layout.layout_blood_sugar_time_period_view, this);
        this.d = (LinearLayout) findViewById(R.id.layout_time_period_items);
        this.f8909a = (HealthTextView) findViewById(R.id.text_view_time_period_description);
        b(System.currentTimeMillis(), 0);
    }

    private void b(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i2 = calendar.get(11);
        this.b.clear();
        this.b.addAll(BloodSugarTimePeriod.getTimePeriodsByHours(i2));
        LinearLayout linearLayout = this.d;
        if (linearLayout == null) {
            LogUtil.h("BloodSugarTimePeriodView", "notifyImmediately: the mItemsLayout is null, return");
            return;
        }
        linearLayout.removeAllViews();
        HealthTextView healthTextView = this.f8909a;
        if (healthTextView != null) {
            healthTextView.setText((CharSequence) null);
        }
        int size = this.b.size();
        for (int i3 = 0; i3 < size; i3++) {
            BloodSugarTimePeriod bloodSugarTimePeriod = this.b.get(i3);
            BloodSugarTimePeriodItemView a2 = a(i3, bloodSugarTimePeriod);
            if (a2 != null) {
                cDL_(a2);
                if (i != 0 && bloodSugarTimePeriod.getCode() == i) {
                    a2.setChecked(true);
                    setDescriptionText(bloodSugarTimePeriod.getTimePeriodDescriptionRes());
                }
            }
        }
    }

    private BloodSugarTimePeriodItemView a(final int i, final BloodSugarTimePeriod bloodSugarTimePeriod) {
        if (bloodSugarTimePeriod == null) {
            LogUtil.h("BloodSugarTimePeriodView", "createTimePeriodItemView, the timePeriod is null");
            return null;
        }
        BloodSugarTimePeriodItemView bloodSugarTimePeriodItemView = new BloodSugarTimePeriodItemView(getContext());
        bloodSugarTimePeriodItemView.setIconDrawable(bloodSugarTimePeriod.getIconResId());
        bloodSugarTimePeriodItemView.setTimePeriodText(getResources().getString(bloodSugarTimePeriod.getTimePeriodNameRes()));
        bloodSugarTimePeriodItemView.setChecked(false);
        bloodSugarTimePeriodItemView.setOnClickListener(new View.OnClickListener() { // from class: npp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarTimePeriodView.this.cDM_(i, bloodSugarTimePeriod, view);
            }
        });
        return bloodSugarTimePeriodItemView;
    }

    public /* synthetic */ void cDM_(int i, BloodSugarTimePeriod bloodSugarTimePeriod, View view) {
        b(i, bloodSugarTimePeriod);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
        if (this.f8909a == null || this.d == null) {
            LogUtil.h("BloodSugarTimePeriodView", "select: the mDescriptionTextView or mItemsLayout is null, return");
            return;
        }
        setDescriptionText(bloodSugarTimePeriod.getTimePeriodDescriptionRes());
        int childCount = this.d.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.d.getChildAt(i2);
            if (childAt instanceof BloodSugarTimePeriodItemView) {
                BloodSugarTimePeriodItemView bloodSugarTimePeriodItemView = (BloodSugarTimePeriodItemView) childAt;
                if (i2 == i) {
                    bloodSugarTimePeriodItemView.setChecked(true);
                    c(i, bloodSugarTimePeriod);
                } else {
                    bloodSugarTimePeriodItemView.setChecked(false);
                }
            }
        }
    }

    private void c(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
        OnTimePeriodItemChangedListener onTimePeriodItemChangedListener = this.e;
        if (onTimePeriodItemChangedListener != null) {
            onTimePeriodItemChangedListener.onTimePeriodItemChanged(i, bloodSugarTimePeriod);
        }
    }

    private void setDescriptionText(int i) {
        HealthTextView healthTextView = this.f8909a;
        if (healthTextView != null) {
            healthTextView.setText(noh.b(getContext(), i));
        }
    }

    private void cDL_(View view) {
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, -2, 1.0f));
        this.d.addView(view);
    }

    public void a() {
        setMinimumHeight(0);
        findViewById(R.id.title).setVisibility(8);
        findViewById(R.id.text_view_time_period_description).setVisibility(8);
    }
}
