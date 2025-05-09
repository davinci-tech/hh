package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.views.HealthBodyRingData;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class qtf extends qta {
    private cfe c;

    public qtf(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        if (bodyReportRecycleItem == null) {
            LogUtil.h("BodyReportBodyCompositionView", "BodyReportBodyCompositionView data is null");
        } else {
            this.c = bodyReportRecycleItem.b();
        }
    }

    private float[] a() {
        double ap = this.c.ap();
        if (!dph.t(ap)) {
            ap = this.c.al();
        }
        double a2 = this.c.a();
        int fractionDigitByType = this.c.getFractionDigitByType(0);
        double ax = this.c.ax();
        double a3 = doj.a(a2, ax, 2);
        double e = doj.e(ap, ax, 2);
        double c = UnitUtil.c(ax, fractionDigitByType);
        double c2 = UnitUtil.c(a3, 2);
        double c3 = UnitUtil.c(e, 2);
        double c4 = UnitUtil.c(this.c.i(), 2);
        return new float[]{(float) c3, (float) UnitUtil.a(((c - c3) - c2) - c4, 2), (float) c2, (float) c4};
    }

    @Override // defpackage.qta
    public String b() {
        if (this.e == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_weight_body_composition_analysis);
    }

    @Override // defpackage.qta
    public String e() {
        if (this.e == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_motiontrack_show_chart_unit_string, qsj.e());
    }

    @Override // defpackage.qta
    public View dIW_() {
        View inflate;
        if (this.c == null) {
            LogUtil.h("BodyReportBodyCompositionView", "getDetailView WeightBean is null");
            return super.dIW_();
        }
        if (Utils.o()) {
            inflate = LayoutInflater.from(this.e).inflate(R.layout.view_body_oversea_report_body_composition, (ViewGroup) null);
        } else {
            inflate = LayoutInflater.from(this.e).inflate(R.layout.view_body_report_body_composition, (ViewGroup) null);
        }
        ((HealthBodyRingData) inflate.findViewById(R.id.health_body_ring_data)).setBodyCircleViewData(a(), UnitUtil.a(this.c.ax()), 2);
        return inflate;
    }
}
