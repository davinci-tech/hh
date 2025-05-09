package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.views.HealthBodyDetailData;
import defpackage.qtj;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes7.dex */
public class qtj extends qta {

    /* renamed from: a, reason: collision with root package name */
    private double f16580a;
    private double b;
    private HealthBodyDetailData c;
    private int d;
    private HealthTextView f;
    private int g;
    private byte h;
    private View i;
    private HealthBodyDetailData j;
    private double k;
    private cfe m;

    public qtj(Context context, BodyReportRecycleItem bodyReportRecycleItem) {
        super(context, bodyReportRecycleItem);
        if (bodyReportRecycleItem != null) {
            this.m = bodyReportRecycleItem.b();
        }
    }

    @Override // defpackage.qta
    public String b() {
        if (this.e == null) {
            return super.b();
        }
        return BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_report_fat_analysis);
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
        if (this.m == null) {
            LogUtil.h("BodyReportFatAnalysisView", "getDetailView() mWeightBean is null.");
            return super.dIW_();
        }
        this.i = LayoutInflater.from(this.e).inflate(R.layout.view_body_report_body_muscle_fat, (ViewGroup) null);
        a();
        j();
        if (qsj.a(this.m)) {
            if (Utils.o()) {
                i();
                f();
            }
            h();
            return this.i;
        }
        return super.dIW_();
    }

    private void h() {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        if (currentUser.p()) {
            c(currentUser.g());
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new AnonymousClass2());
        }
    }

    /* renamed from: qtj$2, reason: invalid class name */
    class AnonymousClass2 implements WeightBaseResponseCallback<cfi> {
        AnonymousClass2() {
        }

        @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onResponse(final int i, final cfi cfiVar) {
            if (qtj.this.e == null || !(qtj.this.e instanceof Activity)) {
                LogUtil.h("BodyReportFatAnalysisView", "mContext is null or mContext is not Activity");
            } else {
                ((Activity) qtj.this.e).runOnUiThread(new Runnable() { // from class: qth
                    @Override // java.lang.Runnable
                    public final void run() {
                        qtj.AnonymousClass2.this.b(cfiVar, i);
                    }
                });
            }
        }

        /* synthetic */ void b(cfi cfiVar, int i) {
            if (cfiVar == null || i != 0) {
                LogUtil.h("BodyReportFatAnalysisView", "loadDataSuccess getCurrentUser: currentUser is null return");
                cfiVar = MultiUsersManager.INSTANCE.getCurrentUser();
            }
            qtj.this.c(cfiVar.g());
        }
    }

    private void a() {
        cfe cfeVar = this.m;
        if (cfeVar == null || this.i == null) {
            LogUtil.h("BodyReportFatAnalysisView", "initData WeightBean or View is null");
            return;
        }
        this.d = cfeVar.e();
        this.h = this.m.an();
        this.g = this.m.t();
        this.k = this.m.ax();
        this.b = this.m.a();
        double d = doj.d(this.m.c());
        this.f16580a = d;
        if (d <= 0.0d) {
            this.f16580a = doj.a(this.b, this.k, 1);
        }
        this.f16580a = UnitUtil.a(this.f16580a);
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_fat, qsj.e(this.f16580a, 1));
        HealthTextView healthTextView = (HealthTextView) this.i.findViewById(R.id.base_weight_segmental);
        this.f = healthTextView;
        healthTextView.setText(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        if (this.m == null || this.i == null) {
            LogUtil.h("BodyReportFatAnalysisView", "initSegmentalFat WeightBean or View is null");
            return;
        }
        cps e = e(i);
        double a2 = UnitUtil.a(this.m.v());
        double a3 = UnitUtil.a(this.m.u());
        double a4 = UnitUtil.a(this.m.am());
        double a5 = UnitUtil.a(this.m.ai());
        int[] b = qsj.b(doj.i(this.h, e.l(), this.d), doj.i(this.h, e.ad(), this.d), a2, a4);
        int[] b2 = qsj.b(doj.c(this.h, e.o(), this.d), doj.c(this.h, e.z(), this.d), a3, a5);
        double e2 = doj.e(this.f16580a, a2, a3, a4, a5);
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(Double.valueOf(e2));
        arrayList.add(Double.valueOf(a2));
        arrayList.add(Double.valueOf(a4));
        arrayList.add(Double.valueOf(a3));
        arrayList.add(Double.valueOf(a5));
        ArrayList<Integer> d = qsj.d(e, b, b2, this.m);
        if (Utils.o()) {
            HealthBodyDetailData healthBodyDetailData = (HealthBodyDetailData) this.i.findViewById(R.id.base_weight_segmental_body_detail_data_oversea);
            this.j = healthBodyDetailData;
            healthBodyDetailData.setBodyDetailType(0);
            qsj.a(this.j, (ArrayList<Double>) arrayList, d);
            return;
        }
        HealthBodyDetailData healthBodyDetailData2 = (HealthBodyDetailData) this.i.findViewById(R.id.base_weight_segmental_body_detail_data);
        this.c = healthBodyDetailData2;
        healthBodyDetailData2.setBodyDetailType(0);
        qsj.a(this.c, (ArrayList<Double>) arrayList, d);
    }

    private cps e(int i) {
        if (cpa.c(this.m)) {
            return new cps(this.g, (float) this.k, this.h, this.d, i, 8, this.m.ag(), 2, this.m.q());
        }
        return new cps(this.g, (float) this.k, this.h, this.d, i, this.m.ag());
    }

    private void j() {
        cfe cfeVar = this.m;
        if (cfeVar == null || this.i == null) {
            LogUtil.h("BodyReportFatAnalysisView", "initResult WeightBean or View is null");
            return;
        }
        int d = doj.d(this.h, this.d, this.b, cfeVar.l());
        if (d >= 3) {
            d = 3;
        }
        HealthTextView healthTextView = (HealthTextView) this.i.findViewById(R.id.base_weight_segmental_type);
        healthTextView.setText(qqy.l(0, d));
        healthTextView.setTextColor(qrf.e(d));
        int o = (int) this.m.o();
        ((HealthTextView) this.i.findViewById(R.id.base_weight_segmental_result_title)).setText(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_fat_balance_results_content, qqy.j(o)));
        ((HealthTextView) this.i.findViewById(R.id.base_weight_segmental_result_content)).setText(qqy.g(o));
    }

    private void f() {
        if (this.f.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
            layoutParams.setMargins(0, qrp.a(BaseApplication.getContext(), 27.0f), 0, 0);
            this.f.setLayoutParams(layoutParams);
            this.f.setTextSize(1, 10.0f);
        }
    }

    private void i() {
        View view = this.i;
        if (view == null) {
            LogUtil.h("BodyReportFatAnalysisView", "setEvaluationIndexGoneInOversea view is null");
            return;
        }
        view.findViewById(R.id.base_weight_segmental_type).setVisibility(8);
        this.i.findViewById(R.id.base_weight_segmental_include_body_standard_horizontal).setVisibility(8);
        this.i.findViewById(R.id.base_weight_segmental_result_title).setVisibility(8);
        this.i.findViewById(R.id.base_weight_segmental_result_content).setVisibility(8);
        this.i.findViewById(R.id.base_weight_segmental_body_detail_data).setVisibility(8);
        this.i.findViewById(R.id.base_weight_segmental_body_detail_data_oversea).setVisibility(0);
    }
}
