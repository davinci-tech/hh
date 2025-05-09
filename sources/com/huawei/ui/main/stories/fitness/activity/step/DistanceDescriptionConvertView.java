package com.huawei.ui.main.stories.fitness.activity.step;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView;
import defpackage.jcf;
import defpackage.nsf;
import defpackage.nsi;
import defpackage.pvm;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes6.dex */
public class DistanceDescriptionConvertView extends RelativeLayout implements ScrollChartObserverTotalDataView.OnDataChange {

    /* renamed from: a, reason: collision with root package name */
    private View f9896a;
    private HealthTextView b;
    private HealthTextView c;
    private HealthTextView e;

    public DistanceDescriptionConvertView(Context context) {
        super(context);
        d(context);
    }

    private void d(Context context) {
        View inflate = View.inflate(context, R.layout.day_step_transfer_layout, this);
        this.f9896a = inflate.findViewById(R.id.transfer_data_layout);
        this.e = (HealthTextView) inflate.findViewById(R.id.transfer_data_description);
        this.b = (HealthTextView) inflate.findViewById(R.id.transfer_data_content);
        this.c = (HealthTextView) inflate.findViewById(R.id.transfer_data_title);
        if (LanguageUtil.j(context)) {
            return;
        }
        this.e.setVisibility(4);
    }

    private void c(float f) {
        double d;
        int i;
        int i2;
        LogUtil.a("Step_DistanceDescriptionConvertView", "updateDistanceView totalStep ", Float.valueOf(f));
        double d2 = pvm.d(f);
        this.c.setText(nsf.h(R$string.IDS_sport_distance));
        double a2 = UnitUtil.a(d2 / 400.0d, 2);
        this.e.setText(nsf.a(R.plurals._2130903296_res_0x7f030100, UnitUtil.e(a2, Locale.getDefault()), UnitUtil.e(a2, 1, 2)));
        if (UnitUtil.h()) {
            d = UnitUtil.e(d2 / 1000.0d, 3);
            i = R.plurals._2130903302_res_0x7f030106;
            i2 = R.plurals._2130903143_res_0x7f030067;
        } else {
            d = d2 / 1000.0d;
            i = R.plurals._2130903301_res_0x7f030105;
            i2 = R.plurals._2130903142_res_0x7f030066;
        }
        String e = UnitUtil.e(d, 1, 2);
        SpannableString spannableString = new SpannableString(nsf.a(i, UnitUtil.e(UnitUtil.a(d, 2), Locale.getDefault()), e));
        nsi.cKL_(spannableString, e, R$string.textFontFamilyMedium);
        nsi.cKI_(spannableString, e, R.color._2131299236_res_0x7f090ba4);
        nsi.cKJ_(spannableString, e, nsf.b(R.dimen._2131362955_res_0x7f0a048b));
        this.b.setText(spannableString);
        jcf.bEB_(this.f9896a, nsf.b(R$string.accessibility_step_distance, nsf.a(i2, UnitUtil.e(UnitUtil.a(d, 2), Locale.getDefault()), e), this.e.getText()), false);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverTotalDataView.OnDataChange
    public void onChange(float f) {
        c(f);
    }
}
