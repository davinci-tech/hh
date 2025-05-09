package com.huawei.ui.main.stories.health.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.adapter.BodyAnalysisReportViewAdapter;
import com.huawei.ui.main.stories.health.fragment.WeightBodyAnalysisReportFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.BodyReportRecycleItem;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.fdu;
import defpackage.jdv;
import defpackage.nrh;
import defpackage.qqw;
import defpackage.qrp;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class WeightBodyAnalysisReportFragment extends BaseFragment {
    private static final BodyReportRecycleItem.BodyReportType[] b = {BodyReportRecycleItem.BodyReportType.BODY_ANALYSIS, BodyReportRecycleItem.BodyReportType.PEER_COMPARISON, BodyReportRecycleItem.BodyReportType.MUSCLE_ANALYSIS, BodyReportRecycleItem.BodyReportType.FAT_ANALYSIS, BodyReportRecycleItem.BodyReportType.PHYSIQUE_PREDICTION, BodyReportRecycleItem.BodyReportType.OTHER_INDICATORS};
    private static final BodyReportRecycleItem.BodyReportType[] c = {BodyReportRecycleItem.BodyReportType.BODY_ANALYSIS, BodyReportRecycleItem.BodyReportType.MUSCLE_ANALYSIS, BodyReportRecycleItem.BodyReportType.FAT_ANALYSIS, BodyReportRecycleItem.BodyReportType.IMPORTANT_INDICATORS, BodyReportRecycleItem.BodyReportType.OTHER_INDICATORS};

    /* renamed from: a, reason: collision with root package name */
    private double f10180a;
    private Context d;
    private int e;
    private byte f;
    private View g;
    private double h;
    private int i;
    private cfe o = new cfe();
    private boolean j = false;

    public void dEk_(Activity activity, boolean z) {
        if (activity == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "initViewInActivity Activity is null");
            return;
        }
        this.j = z;
        this.d = activity;
        this.g = LayoutInflater.from(activity).inflate(R.layout.fragment_body_analysis_report, (ViewGroup) null);
    }

    public void a(cfe cfeVar) {
        if (cfeVar == null || this.g == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillScrollViewDataByBean input parameters is null");
            return;
        }
        this.o = cfeVar;
        g();
        b();
        a();
        e();
        if (this.j) {
            return;
        }
        f();
    }

    private void f() {
        MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback() { // from class: qiz
            @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
            public final void onResponse(int i, Object obj) {
                WeightBodyAnalysisReportFragment.this.e(i, (cfi) obj);
            }
        });
    }

    public /* synthetic */ void e(final int i, final cfi cfiVar) {
        Context context = this.d;
        if (!(context instanceof Activity)) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "mContext is null or mContext is not Activity");
        } else {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: qiv
                @Override // java.lang.Runnable
                public final void run() {
                    WeightBodyAnalysisReportFragment.this.a(cfiVar, i);
                }
            });
        }
    }

    public /* synthetic */ void a(cfi cfiVar, int i) {
        if (cfiVar != null && i == 0) {
            c(cfiVar);
        } else {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "WeightBodyAnalysisReportFragment initCurrentUser getCurrentUser fail");
        }
    }

    private void c(cfi cfiVar) {
        if (this.i <= 0 && cfiVar != null) {
            this.i = cfiVar.d();
        }
        d();
    }

    private void g() {
        cfe cfeVar = this.o;
        if (cfeVar == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "initData WeightBean is null");
            return;
        }
        this.f = cfeVar.an();
        this.e = this.o.e();
        this.i = this.o.t();
        this.h = this.o.ax();
        this.f10180a = this.o.h();
    }

    private void b() {
        String string;
        View view = this.g;
        if (view == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillHeadView mShareView is null");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.body_report_head_name);
        if (this.j) {
            healthTextView.setVisibility(8);
        } else {
            cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
            String h = currentUser == null ? null : currentUser.h();
            if (TextUtils.isEmpty(h)) {
                healthTextView.setVisibility(8);
            } else {
                healthTextView.setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_report_head_name, h));
            }
        }
        if (this.f == 0) {
            string = BaseApplication.getContext().getResources().getString(R$string.IDS_sns_girl);
        } else {
            string = BaseApplication.getContext().getResources().getString(R$string.IDS_sns_boy);
        }
        ((HealthTextView) this.g.findViewById(R.id.body_report_head_gender)).setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_report_head_gendar, string));
        Resources resources = BaseApplication.getContext().getResources();
        int i = R$string.IDS_weight_report_head_age;
        Resources resources2 = BaseApplication.getContext().getResources();
        int i2 = this.e;
        String string2 = resources.getString(i, resources2.getQuantityString(R.plurals._2130903043_res_0x7f030003, i2, Integer.valueOf(i2)));
        if (LanguageUtil.bf(this.d)) {
            Resources resources3 = BaseApplication.getContext().getResources();
            int i3 = this.e;
            string2 = resources3.getQuantityString(R.plurals._2130903043_res_0x7f030003, i3, Integer.valueOf(i3));
        }
        ((HealthTextView) this.g.findViewById(R.id.body_report_head_age)).setText(string2);
        j();
        if (this.i > 0) {
            d();
        }
    }

    private void j() {
        if (this.g == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillHeadViewForHeightWeight mShareView is null");
            return;
        }
        int fractionDigitByType = this.o.getFractionDigitByType(0);
        LogUtil.a("HealthWeight_WeightBodyAnalysisReportFragment", "fillHeadViewForHeightWeight fractionDigitNew = ", Integer.valueOf(fractionDigitByType));
        ((HealthTextView) this.g.findViewById(R.id.body_report_head_weight)).setText(BaseApplication.getContext().getResources().getString(R$string.IDS_hw_weight_report_head_weight, qsj.e(UnitUtil.a(this.h), fractionDigitByType)));
    }

    private void d() {
        String string;
        if (UnitUtil.h()) {
            int[] j = UnitUtil.j(this.i / 100.0d);
            string = BaseApplication.getContext().getResources().getString(R$string.IDS_ft_string, UnitUtil.e(j[0], 1, 0)) + " " + BaseApplication.getContext().getResources().getString(R$string.IDS_ins_string, UnitUtil.e(j[1], 1, 0));
        } else {
            string = BaseApplication.getContext().getResources().getString(R$string.IDS_hw_show_set_height_value_with_unit_cm, UnitUtil.e(this.i, 1, 0));
        }
        ((HealthTextView) this.g.findViewById(R.id.body_report_head_height)).setText(BaseApplication.getContext().getResources().getString(R$string.IDS_weight_report_head_height, string));
    }

    private void a() {
        BodyReportRecycleItem.BodyReportType[] bodyReportTypeArr;
        if (this.g == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillAllItem mShareView is null");
            return;
        }
        if (Utils.o()) {
            bodyReportTypeArr = c;
        } else {
            bodyReportTypeArr = b;
        }
        HealthRecycleView healthRecycleView = (HealthRecycleView) this.g.findViewById(R.id.report_item_recycle_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.d, 2, 1, false) { // from class: com.huawei.ui.main.stories.health.fragment.WeightBodyAnalysisReportFragment.4
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        ArrayList arrayList = new ArrayList(10);
        for (BodyReportRecycleItem.BodyReportType bodyReportType : bodyReportTypeArr) {
            arrayList.add(new BodyReportRecycleItem(bodyReportType, this.o));
        }
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.huawei.ui.main.stories.health.fragment.WeightBodyAnalysisReportFragment.5
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return (Utils.o() && i == 0) ? 2 : 1;
            }
        });
        healthRecycleView.setLayoutManager(gridLayoutManager);
        BodyAnalysisReportViewAdapter bodyAnalysisReportViewAdapter = new BodyAnalysisReportViewAdapter(this.d, arrayList);
        healthRecycleView.setAdapter(bodyAnalysisReportViewAdapter);
        healthRecycleView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.huawei.ui.main.stories.health.fragment.WeightBodyAnalysisReportFragment.2
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
                if (rect == null) {
                    LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillAllItem getItemOffsets outRect is null");
                    return;
                }
                super.getItemOffsets(rect, view, recyclerView, state);
                int a2 = qrp.a(WeightBodyAnalysisReportFragment.this.d, 4.0f);
                rect.set(a2, a2, a2, a2);
            }
        });
        bodyAnalysisReportViewAdapter.notifyDataSetChanged();
    }

    private void e() {
        View view = this.g;
        if (view == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "fillBodyScoreAndOverallAnalysis mShareView is null");
            return;
        }
        ((HealthTextView) view.findViewById(R.id.body_show_weight_score)).setText(UnitUtil.e(this.f10180a, 1, 0));
        ((HealthTextView) this.g.findViewById(R.id.body_show_fit_score_analysis)).setText(qqw.b(this.o));
        LinearLayout linearLayout = (LinearLayout) this.g.findViewById(R.id.report_body_show_weight_score_layout);
        if (this.o.isVisible(31, Utils.o())) {
            linearLayout.setVisibility(0);
        } else {
            linearLayout.setVisibility(8);
        }
    }

    public void c() {
        Context context;
        if (this.g == null || (context = this.d) == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "shareWeightFragment has no parent Activity or view null");
            nrh.e(this.d, R$string.IDS_motiontrack_share_fail_tip);
            return;
        }
        this.g.measure(View.MeasureSpec.makeMeasureSpec((int) context.getResources().getDimension(R.dimen._2131364801_res_0x7f0a0bc1), 1073741824), View.MeasureSpec.makeMeasureSpec((int) this.d.getResources().getDimension(R.dimen._2131364800_res_0x7f0a0bc0), Integer.MIN_VALUE));
        View view = this.g;
        view.layout(0, 0, view.getMeasuredWidth(), this.g.getMeasuredHeight());
        Bitmap bGg_ = jdv.bGg_(this.g);
        if (bGg_ == null) {
            LogUtil.h("HealthWeight_WeightBodyAnalysisReportFragment", "shareWeightFragment screenCut is null");
            nrh.e(this.d, R$string.IDS_motiontrack_share_fail_tip);
        } else {
            fdu fduVar = new fdu(7);
            fduVar.awp_(bGg_);
            CardConstants.d(fduVar, this.d, AnalyticsValue.BI_TRACK_WEIGHT_DATA_SHARE_BUTTON_2100012);
        }
    }
}
