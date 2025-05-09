package com.huawei.health.knit.section.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.ObserveLabels;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.knit.section.adapter.SectionExpandReportAdapter;
import com.huawei.health.servicesui.R$string;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import defpackage.edo;
import defpackage.edp;
import defpackage.edt;
import defpackage.edx;
import defpackage.nru;
import defpackage.nsy;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public class SectionExpandReport extends BaseSection {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Context> f2687a;
    private SectionExpandReportAdapter b;
    private boolean c;
    private edt d;
    private Context e;
    private Observer f;
    private HealthTextView g;
    private HealthDivider h;
    private HealthRecycleView i;
    private HealthDivider j;
    private int l;
    private HealthRecycleView m;
    private SectionExpandReportAdapter n;
    private View o;

    @Override // com.huawei.health.knit.section.view.BaseSection
    public boolean isShareNeedRelayout() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(edp edpVar, String str) {
        if (!(edpVar instanceof edo)) {
            ReleaseLogUtil.d("R_Sleep_SectionExpandReport", "expandReportBaseBean is not ExpandReportDetailBean");
            return false;
        }
        return str.equals(((edo) edpVar).e());
    }

    public SectionExpandReport(Context context) {
        this(context, null);
    }

    public SectionExpandReport(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SectionExpandReport(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.f = new Observer() { // from class: com.huawei.health.knit.section.view.SectionExpandReport.3
            @Override // com.huawei.haf.design.pattern.Observer
            public void notify(String str, final Object... objArr) {
                LogUtil.a("SectionExpandReport", "add wake up feeling");
                if (objArr == null || objArr.length == 0) {
                    return;
                }
                LogUtil.a("SectionExpandReport", "start to runOnUiThread");
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.knit.section.view.SectionExpandReport.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("SectionExpandReport", "attach wake up feeling view");
                        Context context2 = (Context) SectionExpandReport.this.f2687a.get();
                        if (context2 != null && SectionExpandReport.this.d != null && SectionExpandReport.this.d.j() != null) {
                            for (edp edpVar : SectionExpandReport.this.d.d()) {
                                if (edpVar instanceof edx) {
                                    edx edxVar = (edx) edpVar;
                                    if (edxVar.d().equals(context2.getString(R$string.IDS_wakeup_feel_title))) {
                                        Object[] objArr2 = objArr;
                                        if (objArr2.length == 1) {
                                            Object obj = objArr2[0];
                                            if (obj instanceof Integer) {
                                                edxVar.d(SectionExpandReport.c(((Integer) obj).intValue()));
                                            }
                                        }
                                    }
                                }
                                if (objArr.length == 2 && SectionExpandReport.this.c(edpVar, (String) objArr[1])) {
                                    edo edoVar = (edo) edpVar;
                                    edoVar.agF_((SpannableString) objArr[0]);
                                    edoVar.b(true);
                                }
                            }
                            LogUtil.a("SectionExpandReport", "bean is ", SectionExpandReport.this.d);
                            if (SectionExpandReport.this.n != null) {
                                SectionExpandReport.this.n.notifyDataSetChanged();
                            }
                            if (SectionExpandReport.this.b != null) {
                                SectionExpandReport.this.b.notifyDataSetChanged();
                                return;
                            }
                            return;
                        }
                        LogUtil.a("SectionExpandReport", "no expandReportBean");
                    }
                });
            }
        };
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public View onCreateView(Context context) {
        this.e = context;
        this.f2687a = new WeakReference<>(this.e);
        this.l = 2;
        d();
        return this.o;
    }

    private void d() {
        if (this.o != null) {
            LogUtil.h("SectionExpandReport", "initView mainView is not null");
            return;
        }
        LogUtil.h("SectionExpandReport", "initView mainView is null, start to inflate");
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.section_expand_report, (ViewGroup) this, false);
        this.o = inflate;
        this.g = (HealthTextView) inflate.findViewById(R.id.top_advice);
        this.h = (HealthDivider) this.o.findViewById(R.id.top_advice_divider);
        this.j = (HealthDivider) this.o.findViewById(R.id.item_divider);
        this.m = (HealthRecycleView) this.o.findViewById(R.id.expand_report_recyclerview_topN);
        this.i = (HealthRecycleView) this.o.findViewById(R.id.expand_report_recyclerview);
        setRecyclerView(this.m);
        setRecyclerView(this.i);
        LogUtil.a("SectionExpandReport", "registerObserver");
        ObserverManagerUtil.d(this.f, ObserveLabels.REFRESH_REPORT_BEAN);
    }

    private void setRecyclerView(HealthRecycleView healthRecycleView) {
        healthRecycleView.setHasFixedSize(true);
        healthRecycleView.setNestedScrollingEnabled(false);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.e));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(int i) {
        LogUtil.a("SectionExpandReport", "getWakeUpFeeling, feeling index: " + i);
        if (i == 1) {
            return BaseApplication.e().getString(R$string.IDS_wakeup_feel_energized);
        }
        if (i != 2) {
            return i != 3 ? "" : BaseApplication.e().getString(R$string.IDS_wakeup_feel_drowsy);
        }
        return BaseApplication.e().getString(R$string.IDS_wakeup_feel_sleepy);
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    protected void bindParamsToView(HashMap<String, Object> hashMap) {
        LogUtil.a("SectionExpandReport", "start to bindViewParams");
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.a("SectionExpandReport", "no need to bind");
            return;
        }
        edt edtVar = (edt) nru.c(hashMap, "EXPAND_LIST_BEAN", edt.class, null);
        this.d = edtVar;
        if (edtVar != null) {
            if (!TextUtils.isEmpty(edtVar.e())) {
                nsy.cMA_(this.g, 0);
                nsy.cMr_(this.g, this.d.e());
                nsy.cMA_(this.h, 0);
            } else {
                nsy.cMA_(this.g, 8);
                nsy.cMA_(this.h, 8);
            }
            a();
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public List<Bitmap> getShareBitmap() {
        ArrayList arrayList = new ArrayList();
        SectionExpandReportAdapter sectionExpandReportAdapter = this.n;
        if (sectionExpandReportAdapter != null || this.b != null) {
            arrayList.addAll(a(sectionExpandReportAdapter, this.b));
            return arrayList;
        }
        return super.getShareBitmap();
    }

    private List<Bitmap> a(SectionExpandReportAdapter... sectionExpandReportAdapterArr) {
        boolean z = this.c;
        setTopItemVisibility(0);
        for (SectionExpandReportAdapter sectionExpandReportAdapter : sectionExpandReportAdapterArr) {
            if (sectionExpandReportAdapter != null) {
                sectionExpandReportAdapter.c(8);
            }
        }
        List<Bitmap> shareBitmap = super.getShareBitmap();
        for (SectionExpandReportAdapter sectionExpandReportAdapter2 : sectionExpandReportAdapterArr) {
            if (sectionExpandReportAdapter2 != null) {
                sectionExpandReportAdapter2.c(0);
            }
        }
        setTopItemVisibility(z ? 0 : 8);
        return shareBitmap;
    }

    public void setTopItemVisibility(int i) {
        this.c = i == 0;
        if (this.i != null && this.j != null && this.m != null) {
            LogUtil.a("SectionExpandReport", "more analysis");
            nsy.cMA_(this.m, 0);
            SectionExpandReportAdapter sectionExpandReportAdapter = this.b;
            if (sectionExpandReportAdapter == null || sectionExpandReportAdapter.getItemCount() == 0) {
                nsy.cMA_(this.j, 8);
                nsy.cMA_(this.i, 8);
            } else {
                nsy.cMA_(this.j, i);
                nsy.cMA_(this.i, i);
            }
        }
        SectionExpandReportAdapter sectionExpandReportAdapter2 = this.n;
        if (sectionExpandReportAdapter2 == null || sectionExpandReportAdapter2.getItemCount() == 0) {
            nsy.cMA_(this.m, 8);
            nsy.cMA_(this.j, 8);
        }
    }

    public String getEfficientDesc() {
        if (this.d == null) {
            return "";
        }
        Context e = BaseApplication.e();
        for (edo edoVar : this.d.h()) {
            if (edoVar.e().contains(e.getString(R$string.IDS_manual_sleep_daily_sleep_efficiency)) || edoVar.e().contains(e.getString(R$string.IDS_manual_sleep_sleep_efficiency))) {
                return edoVar.e();
            }
        }
        return "";
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public void clear() {
        LogUtil.a("SectionExpandReport", "clear");
        super.clear();
        ObserverManagerUtil.e(this.f, ObserveLabels.REFRESH_REPORT_BEAN);
    }

    private void a() {
        this.b = new SectionExpandReportAdapter(this.e, this.d, this.l, false);
        if (VersionControlUtil.isSupportSleepManagement()) {
            SectionExpandReportAdapter sectionExpandReportAdapter = new SectionExpandReportAdapter(this.e, this.d, this.l, true);
            this.n = sectionExpandReportAdapter;
            b(sectionExpandReportAdapter, this.m);
            nsy.cMA_(this.j, 0);
        }
        b(this.b, this.i);
        LogUtil.a("SectionExpandReport", "bind finish");
    }

    private void b(SectionExpandReportAdapter sectionExpandReportAdapter, HealthRecycleView healthRecycleView) {
        if (healthRecycleView != null) {
            healthRecycleView.setVisibility(0);
            healthRecycleView.setAdapter(sectionExpandReportAdapter);
        }
    }

    @Override // com.huawei.health.knit.section.view.BaseSection
    public String getLogTag() {
        return "SectionExpandReport";
    }
}
