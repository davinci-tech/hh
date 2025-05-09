package com.huawei.ui.main.stories.health.temperature.chart;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.health.R;
import com.huawei.health.manager.util.TimeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureWarningActivity;
import com.huawei.ui.main.stories.health.temperature.adapter.TemperatureHomeWarningAdapter;
import com.huawei.ui.main.stories.health.temperature.chart.TemperatureRemindListView;
import defpackage.gge;
import defpackage.gnm;
import defpackage.jll;
import defpackage.koq;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.qoi;
import defpackage.qpm;
import defpackage.qpr;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes6.dex */
public class TemperatureRemindListView extends LinearLayout implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f10237a;
    private TemperatureHomeWarningAdapter b;
    private HealthRecycleView c;
    private HealthTextView d;
    private final List<qoi> e;
    private View g;
    private CustomViewDialog j;

    public TemperatureRemindListView(Context context) {
        this(context, null);
    }

    public TemperatureRemindListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new ArrayList();
        this.f10237a = context;
        a();
    }

    private void a() {
        Drawable drawable;
        LayoutInflater.from(this.f10237a).inflate(R.layout.temperature_warning_view, this);
        this.g = findViewById(R.id.warning_title);
        this.c = (HealthRecycleView) findViewById(R.id.warning_recycle_view);
        this.d = (HealthTextView) findViewById(R.id.warning_history_text);
        ImageView imageView = (ImageView) findViewById(R.id.warning_arrow);
        if (LanguageUtil.bc(this.f10237a)) {
            drawable = nrz.cKn_(this.f10237a, R.drawable._2131429721_res_0x7f0b0959);
        } else {
            drawable = this.f10237a.getResources().getDrawable(R.drawable._2131429721_res_0x7f0b0959);
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        imageView.setBackground(drawable);
        findViewById(R.id.warning_tip_img).setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.c.setIsScroll(false);
        this.b = new TemperatureHomeWarningAdapter(this.e, R.layout.temperature_warning_home_view);
        this.c.setLayoutManager(new LinearLayoutManager(this.f10237a) { // from class: com.huawei.ui.main.stories.health.temperature.chart.TemperatureRemindListView.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.c.setAdapter(this.b);
    }

    public void e(List<HiHealthData> list, long j) {
        if (koq.b(list)) {
            return;
        }
        this.e.clear();
        Collections.sort(list, new Comparator() { // from class: qou
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((HiHealthData) obj2).getEndTime(), ((HiHealthData) obj).getEndTime());
                return compare;
            }
        });
        if (list.size() > 2) {
            list = list.subList(0, 2);
        }
        long b = qpm.b();
        List<qoi> a2 = qpr.a(list);
        long j2 = b;
        for (qoi qoiVar : a2) {
            if (TimeUtil.b(j, qoiVar.b())) {
                qoiVar.b(qoiVar.b() > b);
                j2 = Math.max(j2, qoiVar.b());
            }
        }
        this.e.addAll(a2);
        this.b.notifyDataSetChanged();
        qpm.e(j2);
    }

    private void c() {
        HealthTextView healthTextView = new HealthTextView(this.f10237a);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        String str = this.f10237a.getString(R$string.IDS_temperature_warning_tip) + " ";
        String string = this.f10237a.getString(R$string.IDS_temperature_warning_set);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) str);
        if (jll.c()) {
            spannableStringBuilder.append((CharSequence) string);
            spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.huawei.ui.main.stories.health.temperature.chart.TemperatureRemindListView.1
                @Override // android.text.style.ClickableSpan
                public void onClick(View view) {
                    if (TemperatureRemindListView.this.j != null) {
                        Intent intent = new Intent();
                        intent.setClassName(TemperatureRemindListView.this.f10237a, "com.huawei.ui.device.activity.temperature.TemperatureMonitoringActivity");
                        gnm.aPB_(TemperatureRemindListView.this.f10237a, intent);
                        TemperatureRemindListView.this.j.cancel();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }

                @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(ContextCompat.getColor(TemperatureRemindListView.this.f10237a, R.color._2131296651_res_0x7f09018b));
                    textPaint.setUnderlineText(false);
                }
            }, str.length(), spannableStringBuilder.length(), 17);
        }
        healthTextView.setText(spannableStringBuilder);
        healthTextView.setTextAppearance(this.f10237a, R.style.CustomDialog_message);
        CustomViewDialog e = new CustomViewDialog.Builder(this.f10237a).czg_(healthTextView).cze_(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: qov
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureRemindListView.this.dGL_(view);
            }
        }).e();
        this.j = e;
        e.show();
    }

    public /* synthetic */ void dGL_(View view) {
        CustomViewDialog customViewDialog = this.j;
        if (customViewDialog != null) {
            customViewDialog.cancel();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public void a(boolean z) {
        if (z) {
            this.g.setVisibility(0);
            this.c.setVisibility(0);
            this.d.setVisibility(8);
        } else {
            this.g.setVisibility(8);
            this.c.setVisibility(8);
            this.d.setVisibility(0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.warning_title) {
            gge.e(AnalyticsValue.TEMPERATURE_REMIND_2060078.value());
            gnm.aPB_(this.f10237a, new Intent(this.f10237a, (Class<?>) TemperatureWarningActivity.class));
        } else if (id == R.id.warning_history_text) {
            gge.e(AnalyticsValue.TEMPERATURE_REMIND_2060078.value());
            gnm.aPB_(this.f10237a, new Intent(this.f10237a, (Class<?>) TemperatureWarningActivity.class));
        } else if (id == R.id.warning_tip_img) {
            gge.e(AnalyticsValue.TEMPERATURE_REMIND_TIPS_2060077.value());
            c();
        } else {
            LogUtil.c("TemperatureRemindListView", "onClick unKnow");
        }
        ViewClickInstrumentation.clickOnView(view);
    }
}
