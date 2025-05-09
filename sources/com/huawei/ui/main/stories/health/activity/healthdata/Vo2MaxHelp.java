package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.main.R$string;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Locale;

/* loaded from: classes9.dex */
public class Vo2MaxHelp {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10088a;
    private Context b;
    private int c;
    private HealthTextView d;
    private HealthSubHeader e;
    private HealthTextView f;
    private HealthTextView g;
    private Integer[] h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;

    public Vo2MaxHelp(Context context) {
        if (context == null) {
            LogUtil.a("Vo2MaxHelp", "Vo2MaxHelp ", "context is null");
        } else {
            this.b = context;
        }
    }

    public void dBm_(View view) {
        if (view == null) {
            LogUtil.a("Vo2MaxHelp", "onCreate ", "activity is null");
        } else {
            dBl_(view);
            e();
        }
    }

    private void dBl_(View view) {
        this.e = (HealthSubHeader) nsy.cMd_(view, R.id.vo2max_help_gender);
        this.f10088a = (HealthTextView) nsy.cMd_(view, R.id.vo2max_excellent);
        this.g = (HealthTextView) nsy.cMd_(view, R.id.vo2max_verygood);
        this.j = (HealthTextView) nsy.cMd_(view, R.id.vo2max_good);
        this.d = (HealthTextView) nsy.cMd_(view, R.id.vo2max_average);
        this.i = (HealthTextView) nsy.cMd_(view, R.id.vo2max_fair);
        this.f = (HealthTextView) nsy.cMd_(view, R.id.vo2max_poor);
        this.k = (HealthTextView) nsy.cMd_(view, R.id.vo2max_verypoor);
    }

    public void e() {
        String format;
        String c = c(LanguageUtil.r(this.b) ? "–" : Constants.LINK);
        int i = this.c;
        if (i == 0) {
            format = String.format(this.b.getString(R$string.IDS_hwh_health_vo2max_male_age), c);
        } else if (i == 1) {
            format = String.format(this.b.getString(R$string.IDS_hwh_health_vo2max_female_age), c);
        } else {
            format = String.format(this.b.getString(R$string.IDS_hwh_health_vo2max_peers_age), c);
        }
        if (LanguageUtil.au(this.b)) {
            format = format.toUpperCase(Locale.ENGLISH);
        }
        this.e.setHeadTitleText(format);
        d();
    }

    private String c(String str) {
        StringBuilder sb = new StringBuilder(16);
        if (this.h[8].intValue() == 0) {
            if (LanguageUtil.bc(this.b)) {
                sb.append("< ");
                sb.append(UnitUtil.e(this.h[9].intValue() + 1, 1, 0));
                return sb.toString();
            }
            sb.append(HiDataFilter.DataFilterExpression.LESS_THAN);
            sb.append(UnitUtil.e(this.h[9].intValue() + 1, 1, 0));
            return sb.toString();
        }
        if (this.h[9].intValue() == 0) {
            if (LanguageUtil.bc(this.b)) {
                sb.append("> ");
                sb.append(UnitUtil.e(this.h[8].intValue() - 1, 1, 0));
                return sb.toString();
            }
            sb.append(HiDataFilter.DataFilterExpression.BIGGER_THAN);
            sb.append(UnitUtil.e(this.h[8].intValue() - 1, 1, 0));
            return sb.toString();
        }
        sb.append("");
        sb.append(UnitUtil.e(this.h[8].intValue(), 1, 0));
        sb.append(str);
        sb.append(UnitUtil.e(this.h[9].intValue(), 1, 0));
        return sb.toString();
    }

    public void c(Integer[] numArr, int i) {
        if (numArr == null) {
            this.h = null;
        } else {
            this.h = (Integer[]) numArr.clone();
        }
        this.c = i;
    }

    private void d() {
        d(this.b, this.f10088a, 0, this.h[6].intValue() - 1, 2);
        d(this.b, this.g, this.h[5].intValue(), this.h[6].intValue() - 1, 0);
        d(this.b, this.j, this.h[4].intValue(), this.h[5].intValue() - 1, 0);
        d(this.b, this.d, this.h[3].intValue(), this.h[4].intValue() - 1, 0);
        d(this.b, this.i, this.h[2].intValue(), this.h[3].intValue() - 1, 0);
        d(this.b, this.f, this.h[1].intValue(), this.h[2].intValue() - 1, 0);
        d(this.b, this.k, this.h[1].intValue(), 0, 1);
    }

    private void d(Context context, HealthTextView healthTextView, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(16);
        if (i3 == 0) {
            sb.append("");
            sb.append(UnitUtil.e(i, 1, 0));
            sb.append(Constants.LINK);
            sb.append(UnitUtil.e(i2, 1, 0));
            healthTextView.setText(sb.toString());
            return;
        }
        if (i3 == 1) {
            if (LanguageUtil.bc(context)) {
                sb.append("");
                sb.append(UnitUtil.e(i, 1, 0));
                sb.append(" >");
                healthTextView.setText(sb.toString());
                return;
            }
            sb.append(HiDataFilter.DataFilterExpression.LESS_THAN);
            sb.append(UnitUtil.e(i, 1, 0));
            healthTextView.setText(sb.toString());
            return;
        }
        if (i3 == 2) {
            if (LanguageUtil.bc(context)) {
                sb.append("");
                sb.append(UnitUtil.e(i2, 1, 0));
                sb.append(" <");
                healthTextView.setText(sb.toString());
                return;
            }
            sb.append(HiDataFilter.DataFilterExpression.BIGGER_THAN);
            sb.append(UnitUtil.e(i2, 1, 0));
            healthTextView.setText(sb.toString());
            return;
        }
        LogUtil.h("Vo2MaxHelp", "setZone wrong type!");
    }
}
