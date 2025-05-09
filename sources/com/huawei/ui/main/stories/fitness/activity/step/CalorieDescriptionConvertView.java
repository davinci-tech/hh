package com.huawei.ui.main.stories.fitness.activity.step;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.step.CalorieDescriptionConvertView;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.pvm;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class CalorieDescriptionConvertView extends RelativeLayout implements ScrollChartObserverTotalDataView.OnDataChange {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f9887a;
    private HealthTextView c;
    private HealthTextView d;
    private View e;

    public CalorieDescriptionConvertView(Context context) {
        super(context);
        b(context);
    }

    private void b(Context context) {
        View inflate = View.inflate(context, R.layout.day_step_transfer_layout, this);
        this.e = inflate.findViewById(R.id.transfer_data_layout);
        this.f9887a = (HealthTextView) inflate.findViewById(R.id.transfer_data_description);
        this.c = (HealthTextView) inflate.findViewById(R.id.transfer_data_content);
        this.d = (HealthTextView) inflate.findViewById(R.id.transfer_data_title);
    }

    private void d(final float f) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: puz
            @Override // java.lang.Runnable
            public final void run() {
                CalorieDescriptionConvertView.this.c(f);
            }
        });
    }

    public /* synthetic */ void c(float f) {
        final double c = pvm.c(f);
        LogUtil.a("Step_CalorieDescriptionConvertView", "updateDistanceDescription totalStep ", Float.valueOf(f), "stepCalorie ", Double.valueOf(c));
        HandlerExecutor.a(new Runnable() { // from class: pvd
            @Override // java.lang.Runnable
            public final void run() {
                CalorieDescriptionConvertView.this.a(c);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(double d) {
        HealthTextView healthTextView = this.d;
        if (healthTextView == null) {
            LogUtil.b("Step_CalorieDescriptionConvertView", "mTransferDataTitle is null");
            return;
        }
        healthTextView.setText(nsf.h(R$string.IDS_plugin_achievement_calorie_desc));
        int round = Math.round(((float) d) / 1000.0f);
        this.f9887a.setText(pvm.b(round));
        String e = UnitUtil.e(round, 1, 0);
        SpannableString spannableString = new SpannableString(nsf.a(R.plurals._2130903083_res_0x7f03002b, round, e));
        nsi.cKL_(spannableString, e, R$string.textFontFamilyMedium);
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKJ_(spannableString, e, nsf.b(R.dimen._2131362955_res_0x7f0a048b));
        this.c.setText(spannableString);
        jcf.bEB_(this.e, nsf.b(R$string.accessibility_step_calorie, nsf.a(R.plurals._2130903522_res_0x7f0301e2, round, e), this.f9887a.getText()), false);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.OnDataChange
    public void onChange(float f) {
        d(f);
    }
}
