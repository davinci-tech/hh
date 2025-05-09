package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class qti extends qta {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f16579a;
    private HealthBodyDetailData b;
    private HealthTextView c;
    private HealthBodyDetailData d;
    private HealthTextView g;
    private HealthTextView h;
    private cfe j;

    public qti(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        if (bodyReportRecycleItem == null) {
            LogUtil.h("BodyReportMuscleAnalysisView", "BodyReportMuscleAnalysisView() data is null.");
        } else {
            this.j = bodyReportRecycleItem.b();
        }
    }

    @Override // defpackage.qta
    public String b() {
        if (this.e == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_report_muscle_analysis);
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
        if (this.j == null) {
            LogUtil.h("BodyReportMuscleAnalysisView", "getDetailView() mWeightBean is null.");
            return super.dIW_();
        }
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.view_body_report_body_muscle_fat, (ViewGroup) null);
        this.d = (HealthBodyDetailData) inflate.findViewById(R.id.base_weight_segmental_body_detail_data);
        this.b = (HealthBodyDetailData) inflate.findViewById(R.id.base_weight_segmental_body_detail_data_oversea);
        this.h = (HealthTextView) inflate.findViewById(R.id.base_weight_segmental);
        this.g = (HealthTextView) inflate.findViewById(R.id.base_weight_segmental_type);
        this.c = (HealthTextView) inflate.findViewById(R.id.base_weight_segmental_result_title);
        this.f16579a = (HealthTextView) inflate.findViewById(R.id.base_weight_segmental_result_content);
        if (qsj.b(this.j)) {
            if (Utils.o()) {
                dIT_(inflate);
                a();
            } else {
                this.b.setVisibility(8);
                this.d.setVisibility(0);
            }
            e(this.j);
            c(this.j);
            return inflate;
        }
        return super.dIW_();
    }

    private void e(cfe cfeVar) {
        this.h.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_skeletal_muscle, qsj.e(UnitUtil.c(cfeVar.aj(), 1), 1)));
    }

    private void c(cfe cfeVar) {
        double a2 = UnitUtil.a(cfeVar.y());
        double a3 = UnitUtil.a(cfeVar.x());
        double a4 = UnitUtil.a(cfeVar.ak());
        double a5 = UnitUtil.a(cfeVar.ah());
        double c = doj.c(UnitUtil.a(cfeVar.aj()), a2, a3, a4, a5);
        if (Utils.o()) {
            this.b.setBodyDetailType(1);
        } else {
            this.d.setBodyDetailType(1);
        }
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(c));
        arrayList.add(Double.valueOf(a2));
        arrayList.add(Double.valueOf(a4));
        arrayList.add(Double.valueOf(a3));
        arrayList.add(Double.valueOf(a5));
        ArrayList<Integer> c2 = qsj.c(cfeVar);
        if (Utils.o()) {
            qsj.a(this.b, (ArrayList<Double>) arrayList, c2);
        } else {
            qsj.a(this.d, (ArrayList<Double>) arrayList, c2);
        }
        a(cfeVar);
    }

    private void a(cfe cfeVar) {
        int d = doj.d(cfeVar.an(), cfeVar.e(), cfeVar.t(), cfeVar.aj(), cfeVar.l());
        int e = qrf.e(d);
        this.g.setText(qqy.l(0, d));
        this.g.setTextColor(e);
        this.c.setText(BaseApplication.getContext().getString(R$string.IDS_weight_skeletal_muscle_balance_results, qqy.h((int) cfeVar.ac())));
        this.f16579a.setText(qqy.f((int) cfeVar.ac()) + System.lineSeparator() + qqy.k((int) cfeVar.ac()));
    }

    private void a() {
        if (this.h.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            layoutParams.setMargins(0, qrp.a(BaseApplication.getContext(), 27.0f), 0, 0);
            this.h.setLayoutParams(layoutParams);
            this.h.setTextSize(1, 10.0f);
        }
    }

    private void dIT_(View view) {
        if (view == null) {
            LogUtil.h("BodyReportMuscleAnalysisView", "setEvaluationIndexInvisibleInOversea view is null");
            return;
        }
        this.g.setVisibility(8);
        this.c.setVisibility(8);
        this.f16579a.setVisibility(8);
        this.d.setVisibility(8);
        this.b.setVisibility(0);
        view.findViewById(R.id.base_weight_segmental_include_body_standard_horizontal).setVisibility(8);
    }
}
