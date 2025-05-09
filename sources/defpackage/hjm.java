package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.healthcloud.plugintrack.ui.activity.HorizontalDetailActivity;
import com.huawei.healthcloud.plugintrack.ui.fragment.HeartRateFrag;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.VastTag;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class hjm {
    private Context b;
    private List<gxs> c;
    private HealthColumnSystem d;
    private boolean e;
    private HealthTextView f;
    private HealthRingChart g;
    private int[] h;
    private HealthTableWidget i;
    private LinearLayout j;
    private int k;
    private int l;
    private hgf m;
    private float[] o;

    /* renamed from: a, reason: collision with root package name */
    private List<gxs> f13187a = new ArrayList();
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> n = new nre<>();

    private int e(int i) {
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public hjm(MotionPath motionPath, MotionPathSimplify motionPathSimplify, Context context, int i) {
        this.c = new ArrayList();
        this.l = 0;
        this.l = i;
        this.b = context;
        this.c = hjq.e(motionPath, motionPathSimplify);
        LogUtil.c("Track_PaceIntervalManager", "zoneStatisticList", new Gson().toJson(this.c));
        d(motionPath, motionPathSimplify);
        this.k = motionPathSimplify.requestTrackType();
    }

    private void d(MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        int[] e = hjq.e(motionPathSimplify.getExtendDataString("runPaceZoneConfig"), motionPathSimplify.getExtendDataString("runPaceZoneStatistics"), motionPath.requestRealTimePaceList(), motionPathSimplify.requestStartTime());
        this.h = e;
        LogUtil.c("Track_PaceIntervalManager", "initDuration : ", Arrays.toString(e));
        int[] iArr = this.h;
        if (iArr == null || iArr.length != 5) {
            LogUtil.h("Track_PaceIntervalManager", "mPaceRingData ==null");
            this.e = false;
        } else {
            this.e = true;
            d(this.c);
        }
    }

    public void bgO_(View view, float[] fArr, HealthColumnSystem healthColumnSystem, boolean z) {
        if (view == null) {
            LogUtil.h("Track_PaceIntervalManager", "rootView == null");
            return;
        }
        if (fArr != null) {
            this.o = (float[]) fArr.clone();
        }
        this.d = healthColumnSystem;
        this.j = (LinearLayout) view.findViewById(R.id.speed_interval_layout_show);
        this.i = (HealthTableWidget) view.findViewById(R.id.speed_interval_table_layout);
        this.f = (HealthTextView) view.findViewById(R.id.speed_interval_table_tips);
        this.g = (HealthRingChart) this.j.findViewById(R.id.ring_view);
        this.f.setText(this.b.getString(R.string._2130843949_res_0x7f02192d));
        this.f.setVisibility(8);
        g();
    }

    private void g() {
        int i = this.l;
        boolean z = i == 264 || i == 258 || i == 280;
        if (!z || !this.e || (!f() && koq.b(this.c))) {
            LogUtil.h("Track_PaceIntervalManager", "Scenario 1 no time", Boolean.valueOf(z));
            this.g.setVisibility(8);
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            this.f.setVisibility(8);
            return;
        }
        h();
    }

    private boolean f() {
        int i = this.k;
        return i == 0 || i == 1;
    }

    private void i() {
        List<nkz> c = c();
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.b, new nld().c(false).b(true), c);
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: hjp
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return hjm.this.a(nkzVar);
            }
        });
        this.j.setVisibility(0);
        this.g.setAdapter(healthRingChartAdapter);
        if (LanguageUtil.j(this.b)) {
            this.g.setDesc(this.b.getResources().getString(R.string._2130843948_res_0x7f02192c));
        }
    }

    /* synthetic */ String a(nkz nkzVar) {
        return HeartRateFrag.a((int) nkzVar.i(), this.b);
    }

    private List<nkz> c() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(a(5, this.b.getResources().getString(R.string._2130844136_res_0x7f0219e8)));
        arrayList.add(a(4, this.b.getResources().getString(R.string._2130844135_res_0x7f0219e7)));
        arrayList.add(a(3, this.b.getResources().getString(R.string._2130844134_res_0x7f0219e6)));
        arrayList.add(a(2, this.b.getResources().getString(R.string._2130844133_res_0x7f0219e5)));
        arrayList.add(a(1, this.b.getResources().getString(R.string._2130844132_res_0x7f0219e4)));
        ArrayList arrayList2 = new ArrayList(10);
        arrayList2.add(Integer.valueOf(e(this.h[4])));
        arrayList2.add(Integer.valueOf(e(this.h[3])));
        arrayList2.add(Integer.valueOf(e(this.h[2])));
        arrayList2.add(Integer.valueOf(e(this.h[1])));
        arrayList2.add(Integer.valueOf(e(this.h[0])));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298929_res_0x7f090a71)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298927_res_0x7f090a6f)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298925_res_0x7f090a6d)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298931_res_0x7f090a73)));
        arrayList3.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298933_res_0x7f090a75)));
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298928_res_0x7f090a70)));
        arrayList4.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298926_res_0x7f090a6e)));
        arrayList4.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298924_res_0x7f090a6c)));
        arrayList4.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298930_res_0x7f090a72)));
        arrayList4.add(Integer.valueOf(ContextCompat.getColor(this.b, R.color._2131298932_res_0x7f090a74)));
        int size = arrayList.size();
        ArrayList arrayList5 = new ArrayList(size);
        int i = 0;
        while (i < size) {
            arrayList5.add(new nkz(i < arrayList.size() ? (String) arrayList.get(i) : "", ((Integer) arrayList2.get(i)).intValue(), i < arrayList3.size() ? ((Integer) arrayList3.get(i)).intValue() : 0, i < arrayList4.size() ? ((Integer) arrayList4.get(i)).intValue() : 0));
            i++;
        }
        return arrayList5;
    }

    private String a(int i, String str) {
        return this.b.getResources().getString(R.string._2130844131_res_0x7f0219e3, Integer.valueOf(i), str);
    }

    public void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        gwi.c(nreVar, this.c);
        for (int i = 5; i >= 1; i--) {
            gwi.c(nreVar, i, this.c);
        }
    }

    private void n() {
        d(this.n);
        LogUtil.a("Track_PaceIntervalManager", "setPaceTableAdapter");
        this.m = new hgf(this.b, this.n, false, LanguageUtil.bi(this.b) ? 38.5f : 35.5f) { // from class: hjm.1
            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                return 1;
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                if (hjm.this.o != null && i >= 0 && i < hjm.this.o.length) {
                    return nsn.c(hjm.this.b, hjm.this.o[i]);
                }
                return super.getColumnWidth(i);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                return hjm.this.d.f() > 4 ? Math.min(super.getColumnCount(), Math.min(hjm.this.c.size() + 2, 10)) : Math.min(super.getColumnCount(), Math.min(hjm.this.c.size() + 1, 6));
            }

            @Override // defpackage.hgf
            public int a() {
                return hjm.this.b.getResources().getDimensionPixelSize(R.dimen._2131362564_res_0x7f0a0304);
            }
        };
        LogUtil.a("Track_PaceIntervalManager", "setAdapter");
        int c = nsn.c(this.b, 156.0f);
        if (LanguageUtil.bi(this.b)) {
            c = nsn.c(this.b, 159.0f);
        }
        if (this.i.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, c);
            layoutParams2.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin, layoutParams.bottomMargin);
            this.i.setLayoutParams(layoutParams2);
        }
        this.i.setAdapter(this.m);
        this.i.setVisibility(0);
    }

    private void a(final boolean z) {
        this.f.setOnClickListener(new View.OnClickListener() { // from class: hjm.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!z) {
                    hjm.this.j();
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("Track_PaceIntervalManager", "onClick more");
                Intent intent = new Intent();
                intent.putExtra(BleConstants.SPORT_TYPE, hjm.this.l);
                intent.putExtra("fragment_tag", "pace");
                intent.setClass(hjm.this.b, HorizontalDetailActivity.class);
                try {
                    hjm.this.b.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("Track_PaceIntervalManager", "initMorePace() exception: ", LogAnonymous.b((Throwable) e));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("Track_PaceIntervalManager", "onClick Dialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.b).e(this.b.getResources().getString(R.string._2130843952_res_0x7f021930)).czE_(this.b.getString(R.string._2130841554_res_0x7f020fd2), new View.OnClickListener() { // from class: hjm.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private void h() {
        if (koq.b(this.c)) {
            i();
            this.i.setVisibility(8);
            this.f.setVisibility(8);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 2");
            return;
        }
        if (this.c.size() == 1 && "STEP_RATE".equals(this.c.get(0).g())) {
            i();
            e();
            n();
            this.f.setText(this.b.getString(R.string._2130843950_res_0x7f02192e));
            this.f.setVisibility(0);
            a(false);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 3");
            return;
        }
        if (this.c.size() == 2 && e(ObserveLabels.HEART_RATE) && e("STEP_RATE")) {
            i();
            n();
            this.f.setText(this.b.getString(R.string._2130843950_res_0x7f02192e));
            this.f.setVisibility(0);
            a(false);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 4");
            return;
        }
        if (this.c.size() >= 5) {
            i();
            n();
            this.f.setVisibility(0);
            a(true);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 5");
            return;
        }
        if (this.c.size() == 1 && ObserveLabels.HEART_RATE.equals(this.c.get(0).g())) {
            i();
            this.i.setVisibility(8);
            this.f.setVisibility(8);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 6");
            return;
        }
        if (this.c.size() >= 2) {
            i();
            n();
            this.f.setText(this.b.getString(R.string._2130843950_res_0x7f02192e));
            this.f.setVisibility(0);
            a(false);
            LogUtil.a("Track_PaceIntervalManager", "Scenario 7");
            return;
        }
        LogUtil.a("Track_PaceIntervalManager", "Scenario 8");
    }

    private void e() {
        gxs gxsVar = new gxs();
        gxsVar.d(ObserveLabels.HEART_RATE);
        gxsVar.e(0.0f);
        gxsVar.d(0.0f);
        gxsVar.b(0.0f);
        gxsVar.a(0.0f);
        gxsVar.c(0.0f);
        this.c.add(0, gxsVar);
    }

    private boolean e(String str) {
        for (gxs gxsVar : this.c) {
            if (gxsVar != null && str.equals(gxsVar.g())) {
                return true;
            }
        }
        return false;
    }

    private void d(List<gxs> list) {
        gxs gxsVar = new gxs();
        gxsVar.d(VastTag.DURATION);
        gxsVar.e(this.h[0]);
        gxsVar.d(this.h[1]);
        gxsVar.b(this.h[2]);
        gxsVar.a(this.h[3]);
        gxsVar.c(this.h[4]);
        for (gxs gxsVar2 : list) {
            if (gxsVar2 != null) {
                this.f13187a.add(gxsVar2);
            }
        }
        this.f13187a.add(0, gxsVar);
        LogUtil.c("Track_PaceIntervalManager", "mAllDataList", new Gson().toJson(this.f13187a));
    }

    public List<gxs> a() {
        return this.f13187a;
    }

    public float[] c(int i, HealthColumnSystem healthColumnSystem, boolean z) {
        if (i == 258) {
            if (healthColumnSystem.f() > 4) {
                LogUtil.a("Track_PaceIntervalManager", "getTotalColumnCount > 4) ");
                return new float[]{78.25f, 62.25f, 68.5f, 71.75f, 65.0f, 65.25f, 58.25f, 61.5f, 61.5f, 77.0f};
            }
            LogUtil.a("Track_PaceIntervalManager", "getTotalColumnCount < 4) ");
            return z ? new float[]{0.9f, 1.0f, 1.0f, 0.9f, 0.9f, 0.9f} : new float[]{1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        }
        return new float[0];
    }

    public void b() {
        HealthTextView healthTextView = this.f;
        if (healthTextView != null) {
            healthTextView.setVisibility(8);
        }
    }

    public void d() {
        hgf hgfVar = this.m;
        if (hgfVar != null) {
            hgfVar.notifyLayoutChanged();
        }
    }
}
