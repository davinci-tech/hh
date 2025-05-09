package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.HorizontalDetailActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.tablewidget.HealthTableWidget;
import defpackage.gnm;
import defpackage.gvv;
import defpackage.gwr;
import defpackage.hgf;
import defpackage.hjc;
import defpackage.hjj;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kwl;
import defpackage.kwp;
import defpackage.kwq;
import defpackage.kws;
import defpackage.kwt;
import defpackage.kwu;
import defpackage.kwv;
import defpackage.kxb;
import defpackage.nre;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class SegmentFrag extends SportResultBaseFragment {
    private static List<CommonSegment> d;
    private static CommonSegment e;
    private HealthColumnSystem f;
    private HealthButton i;
    private HealthTableWidget j;
    private LinearLayout k;
    private hgf l;
    private float[] n;
    private int o;
    private HealthTextView q;
    private HealthTextView t;
    private nre<hjj, hjc, hjc, hjc, hjc, hjc> m = new nre<>();
    private int g = 0;
    private boolean h = false;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            throw new RuntimeException("LayoutInflater not found.");
        }
        LogUtil.a("Track_SegmentFrag", "onCreateView");
        if (o()) {
            return null;
        }
        h();
        if (!k() || !m()) {
            return null;
        }
        if (this.b != null) {
            return this.b;
        }
        this.f = new HealthColumnSystem(this.c);
        this.b = layoutInflater.inflate(R.layout.track_segment_fragment, viewGroup, false);
        i();
        p();
        return this.b;
    }

    private void p() {
        n();
        g();
        s();
        l();
        f();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected int c() {
        return this.f3732a.e().requestSportType() == 258 ? 4162 : -1;
    }

    private void n() {
        this.k = (LinearLayout) this.b.findViewById(R.id.row_machine_lr_data_layout);
        if (gvv.c(this.f3732a.e()).equals("291")) {
            q();
        } else {
            this.k.setVisibility(8);
        }
    }

    private void g() {
        if (this.f3732a.e().requestSportType() != 283) {
            return;
        }
        q();
        HealthTextView healthTextView = (HealthTextView) this.b.findViewById(R.id.total_action_group_title);
        HealthTextView healthTextView2 = (HealthTextView) this.b.findViewById(R.id.total_action_count_title);
        healthTextView.setText(R.string._2130845828_res_0x7f022084);
        healthTextView2.setText(R.string._2130845858_res_0x7f0220a2);
    }

    private void q() {
        this.q = (HealthTextView) this.b.findViewById(R.id.total_action_group);
        this.t = (HealthTextView) this.b.findViewById(R.id.total_action_count);
        this.k.setVisibility(0);
        int extendDataInt = this.f3732a.e().getExtendDataInt("total_action_group");
        int requestTotalSteps = this.f3732a.e().requestTotalSteps();
        this.q.setText(UnitUtil.e(extendDataInt, 1, 0));
        this.t.setText(UnitUtil.e(requestTotalSteps, 1, 0));
    }

    private void s() {
        if (!gwr.a(this.o) && !gwr.d(this.o)) {
            LogUtil.a("Track_SegmentFrag", "this sport type can't need to view detail, sport type: ", Integer.valueOf(this.o));
            return;
        }
        this.b.findViewById(R.id.view_detail_layout).setVisibility(0);
        this.i = (HealthButton) this.b.findViewById(R.id.btn_view_horizontal_detail);
        if (nsn.cLh_(getActivity())) {
            this.i.setVisibility(8);
        } else {
            r();
        }
    }

    private void r() {
        HealthButton healthButton = this.i;
        if (healthButton == null) {
            LogUtil.h("Track_SegmentFrag", "setBtnViewDetailOnClickListener mBtnViewDetail is null.");
        } else {
            healthButton.setVisibility(0);
            this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SegmentFrag.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra(BleConstants.SPORT_TYPE, SegmentFrag.this.f3732a.e().requestSportType());
                    intent.putExtra("fragment_tag", "segmentation");
                    intent.setClass(SegmentFrag.this.c, HorizontalDetailActivity.class);
                    HashMap hashMap = new HashMap(2);
                    hashMap.put("click", 1);
                    hashMap.put(BleConstants.SPORT_TYPE, Integer.valueOf(SegmentFrag.this.o));
                    ixx.d().d(SegmentFrag.this.c, AnalyticsValue.BI_TRACK_ENTER_SEGMENT_DETAIL_1040063.value(), hashMap, 0);
                    gnm.aPB_(SegmentFrag.this.c, intent);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        HealthButton healthButton;
        super.onMultiWindowModeChanged(z);
        if (z && (healthButton = this.i) != null) {
            healthButton.setVisibility(8);
        } else {
            r();
        }
    }

    private void i() {
        d = this.f3732a.j().requestSegmentList();
        int requestSportType = this.f3732a.e().requestSportType();
        this.o = requestSportType;
        if (requestSportType == 217 || requestSportType == 218) {
            f(b());
            return;
        }
        if (requestSportType == 220) {
            b(b());
            return;
        }
        if (requestSportType == 274) {
            c(b());
            return;
        }
        if (requestSportType != 283) {
            if (requestSportType != 258) {
                if (requestSportType == 259) {
                    d(b());
                    return;
                } else if (requestSportType == 279) {
                    a(b());
                    return;
                } else if (requestSportType != 280) {
                    return;
                }
            }
            g(b());
            return;
        }
        e(b());
    }

    public static List<CommonSegment> b() {
        if (d != null) {
            return new ArrayList(d);
        }
        return Collections.EMPTY_LIST;
    }

    public static CommonSegment e() {
        return e;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        HealthColumnSystem healthColumnSystem = this.f;
        if (healthColumnSystem == null || this.l == null) {
            return;
        }
        healthColumnSystem.e(this.c);
        this.n = d(this.o);
        this.l.notifyLayoutChanged();
    }

    private void g(List<CommonSegment> list) {
        if (koq.e((Object) list, kwu.class)) {
            List asList = Arrays.asList((kwu[]) list.toArray(new kwu[list.size()]));
            int c = gwr.c(list);
            gwr.a(this.m, c);
            CommonSegment d2 = gwr.d(this.f3732a.e());
            e = d2;
            this.h = gwr.d((List<kwu>) asList, d2);
            kwu kwuVar = null;
            long j = 0;
            boolean z = false;
            for (int i = 0; i < asList.size(); i++) {
                if (((kwu) asList.get(i)).w()) {
                    z = true;
                }
                j += c(((kwu) asList.get(i)).f());
                if (i == asList.size() - 1) {
                    kwuVar = d((kwu) asList.get(i), j, this.f3732a.e());
                } else {
                    kwuVar = (kwu) asList.get(i);
                }
                gwr.b(this.m, kwuVar, c, this.h);
            }
            CommonSegment commonSegment = e;
            if (commonSegment != null) {
                gwr.e(this.m, (kwu) commonSegment, this.c.getString(R.string._2130839908_res_0x7f020964), c, this.h);
            } else {
                gwr.e(this.m, gwr.e((List<kwu>) asList), this.c.getString(R.string._2130839908_res_0x7f020964), c, this.h);
            }
            if (kwuVar != null) {
                int fieldNum = kwuVar.getFieldNum();
                if (!z) {
                    fieldNum -= kwuVar.a();
                }
                this.g = fieldNum;
                if (c == 1) {
                    this.g = fieldNum + 1;
                }
            }
        }
    }

    private long c(long j) {
        return (long) (UnitUtil.a(j / 100000.0d, 2) * 100.0d * 1000.0d);
    }

    private kwu d(kwu kwuVar, long j, MotionPathSimplify motionPathSimplify) {
        long requestTotalDistance = motionPathSimplify.requestTotalDistance() * 100;
        if (j > requestTotalDistance) {
            kwuVar.e(Math.max(0L, kwuVar.f() - (j - requestTotalDistance)));
        } else if (j < requestTotalDistance) {
            kwuVar.e(kwuVar.f() + (requestTotalDistance - j));
        } else {
            kwuVar.e(kwuVar.f());
        }
        return kwuVar;
    }

    private void c(List<CommonSegment> list) {
        if (!koq.e((Object) list, kwl.class)) {
            LogUtil.h("Track_SegmentFrag", "segmentList is not instanceof List<RowingMachineSegment>");
            return;
        }
        gwr.c(this.m);
        kwl kwlVar = null;
        for (CommonSegment commonSegment : list) {
            if (commonSegment instanceof kwl) {
                kwlVar = (kwl) commonSegment;
                gwr.d(this.m, kwlVar);
            }
        }
        if (kwlVar != null) {
            this.g = kwlVar.getFieldNum();
        }
    }

    private void e(List<CommonSegment> list) {
        if (!koq.e((Object) list, kwt.class)) {
            LogUtil.h("Track_SegmentFrag", "segmentList is not instanceof List<TrackJumpRopeSegment>");
            return;
        }
        LogUtil.a("Track_SegmentFrag", "initRopeSkippingData:", Integer.valueOf(list.size()));
        boolean d2 = gwr.d(list);
        gwr.b(this.m, d2);
        kwt kwtVar = null;
        for (CommonSegment commonSegment : list) {
            if (commonSegment instanceof kwt) {
                kwtVar = (kwt) commonSegment;
                gwr.e(this.m, kwtVar, d2);
            }
        }
        if (kwtVar != null) {
            this.g = kwtVar.getFieldNum();
        }
    }

    private void b(List<CommonSegment> list) {
        if (koq.e((Object) list, kws.class)) {
            gwr.b(this.m);
            Iterator<CommonSegment> it = list.iterator();
            kws kwsVar = null;
            while (it.hasNext()) {
                kwsVar = (kws) it.next();
                gwr.b(this.m, kwsVar);
            }
            if (kwsVar != null) {
                this.g = kwsVar.getFieldNum();
            }
        }
    }

    private void f(List<CommonSegment> list) {
        if (koq.e((Object) list, kwv.class)) {
            gwr.a(this.m);
            Iterator<CommonSegment> it = list.iterator();
            kwv kwvVar = null;
            while (it.hasNext()) {
                kwvVar = (kwv) it.next();
                gwr.a(this.m, kwvVar);
            }
            if (kwvVar != null) {
                this.g = kwvVar.getFieldNum();
            }
        }
    }

    private void d(List<CommonSegment> list) {
        if (koq.e((Object) list, kwq.class)) {
            gwr.d(this.m);
            ArrayList arrayList = new ArrayList();
            Iterator<CommonSegment> it = list.iterator();
            kwq kwqVar = null;
            while (it.hasNext()) {
                kwqVar = (kwq) it.next();
                gwr.c(this.m, kwqVar);
                arrayList.add(kwqVar);
            }
            if (kwqVar != null) {
                this.g = kwqVar.getFieldNum();
            }
            CommonSegment d2 = gwr.d(this.f3732a.e());
            e = d2;
            kwq kwqVar2 = (kwq) d2;
            if (d2 != null) {
                gwr.e(this.m, kwqVar2, this.c.getString(R.string._2130839908_res_0x7f020964));
            } else {
                gwr.e(this.m, gwr.a(arrayList), this.c.getString(R.string._2130839908_res_0x7f020964));
            }
        }
    }

    private void a(List<CommonSegment> list) {
        if (koq.e((Object) list, kwp.class)) {
            gwr.e(this.m);
            kwp kwpVar = null;
            for (CommonSegment commonSegment : list) {
                if (commonSegment instanceof kwp) {
                    kwpVar = (kwp) commonSegment;
                    gwr.a(this.m, kwpVar);
                }
            }
            if (kwpVar != null) {
                this.g = kwpVar.getFieldNum();
            }
        }
    }

    private void l() {
        this.j = (HealthTableWidget) this.b.findViewById(R.id.table_layout);
        this.n = d(this.o);
        hgf hgfVar = new hgf(getContext(), this.m, !LanguageUtil.h(BaseApplication.e())) { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.SegmentFrag.4
            @Override // com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidthValueType() {
                return 1;
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnWidth(int i) {
                if (SegmentFrag.this.n != null && i >= 0 && i < SegmentFrag.this.n.length) {
                    return nsn.c(SegmentFrag.this.c, SegmentFrag.this.n[i]);
                }
                return super.getColumnWidth(i);
            }

            @Override // defpackage.hgf, com.huawei.ui.commonui.tablewidget.BaseHealthTableAdapter, com.huawei.ui.commonui.tablewidget.HealthTableAdapter
            public int getColumnCount() {
                if (gwr.a(SegmentFrag.this.o) || gwr.d(SegmentFrag.this.o)) {
                    return SegmentFrag.this.f.f() > 4 ? Math.min(super.getColumnCount(), Math.min(SegmentFrag.this.g, 10)) : Math.min(super.getColumnCount(), Math.min(SegmentFrag.this.g, 6));
                }
                return super.getColumnCount();
            }

            @Override // defpackage.hgf
            public int a() {
                if (SegmentFrag.this.o == 220) {
                    return nsn.c(SegmentFrag.this.c, 12.0f);
                }
                return super.a();
            }
        };
        this.l = hgfVar;
        this.j.setAdapter(hgfVar);
    }

    private float[] d(int i) {
        if (i == 220) {
            return new float[]{54.0f, 64.0f, 64.0f, 64.0f, 64.0f};
        }
        if (kxb.c(i)) {
            return new float[]{58.5f, 55.0f, 57.5f, 58.0f, 59.0f};
        }
        if (gwr.d(i)) {
            return j();
        }
        return i == 279 ? new float[]{58.5f, 55.0f, 57.5f, 58.0f} : gwr.a(i) ? this.f.f() > 4 ? new float[]{78.25f, 62.25f, 68.5f, 71.75f, 65.0f, 65.25f, 58.25f, 61.5f, 61.5f, 77.0f} : j() : new float[0];
    }

    private float[] j() {
        if (LanguageUtil.h(BaseApplication.e())) {
            return this.h ? new float[]{35.0f, 65.0f, 44.0f, 46.0f, 45.0f, 40.0f} : new float[]{40.0f, 55.0f, 50.0f, 50.0f, 45.0f, 38.0f};
        }
        return new float[]{59.75f, 53.75f, 47.5f, 50.75f, 52.0f, 49.0f};
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a();
    }

    private static void a() {
        d = null;
        e = null;
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.fragment.SportResultBaseFragment
    protected String d() {
        return "Track_SegmentFrag";
    }
}
